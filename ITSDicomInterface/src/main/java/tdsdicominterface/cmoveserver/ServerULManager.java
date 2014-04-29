package tdsdicominterface.cmoveserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomInterfaceSettings;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIAssociationContentErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.slprotocol.SOP;
import tdsdicominterface.ulprotocol.AAbortMessage;
import tdsdicominterface.ulprotocol.AAssociateAcMessage;
import tdsdicominterface.ulprotocol.AAssociateRqMessage;
import tdsdicominterface.ulprotocol.AReleaseRpMessage;
import tdsdicominterface.ulprotocol.AReleaseRqMessage;
import tdsdicominterface.ulprotocol.AssociationSettings;
import tdsdicominterface.ulprotocol.AssociationSettings.PresentationContext;
import tdsdicominterface.ulprotocol.PDVItem;
import tdsdicominterface.ulprotocol.PDataTfMessage;
import tdsdicominterface.ulprotocol.PresentationContextItem;
import tdsdicominterface.ulprotocol.TransferSyntaxItem;

public class ServerULManager
{
	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedOutputStream out;
	private BufferedInputStream bis;
	private DIInputStreamReader in;

	private ArrayList<DicomDataSet> dataSetList;
	private boolean failure;

	private BufferedWriter diagOut;

	private ServerSLManager slManager;
	private AssociationSettings aSettings;
	private String moveOriginatorAET;
	private int cMoveRqMessageID;

	private ArrayList<PDVItem> earlyDataSetPdvItems;	// Ha az utolso Command PDV-t tartalmazo P-DATA-TF Data set PDV-(ke)t is tartalmaz,
														// akkor azok ebbe a listaba kerulnek eltarolasra a kovetkezo (Data set-et beolvaso)
														// receiveData() hivasig.

	public ServerULManager(DicomInterfaceSettings diSettings, int cMoveRqMessageID, BufferedWriter diagOut) throws DIException
	{
		try
		{
			this.diagOut = diagOut;
			this.cMoveRqMessageID = cMoveRqMessageID;
			this.moveOriginatorAET = diSettings.getAeTitle();
			this.dataSetList = new ArrayList<DicomDataSet>();
			this.failure = false;

			serverSocket = new ServerSocket(diSettings.getPort());
			earlyDataSetPdvItems = new ArrayList<PDVItem>();
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1005");
		}
	}

	public void runServer() throws DIException
	{
		try
		{
			slManager = new ServerSLManager(this);
			printDiag("Server is running.");

//			while(true)
//			{
				// Waiting for connection request...
				printDiag("Waiting for connection request...");
				socket = serverSocket.accept();

				// New connection created
				printDiag("Connection created.");
				out = new BufferedOutputStream(socket.getOutputStream());
				bis = new BufferedInputStream(socket.getInputStream());
				in = new DIInputStreamReader(AssociationSettings.EndianType, bis);

				// Association settings
				aSettings = new AssociationSettings(true);
				// TODO: ha majd be lehet allitani
//				aSettings.setPdvListMaxSizeForReceiving(256);

				String state = "idle";
				while(true)
				{
					if(state.equals("idle"))
					{
						// Waiting for input...
						int b = in.readByte();

						if(b == AAssociateRqMessage.ItemType)
						{
							AAssociateRqMessage mes = new AAssociateRqMessage(in);
							printDiag("AAssociateRqMessage received.");
							Object res = processAAssociateRqMessage(mes, aSettings);
							if(res instanceof AAssociateAcMessage)
							{
								out.write(((AAssociateAcMessage)res).getData());	// valasz elkuldese
								out.flush();
								printDiag("AAssociateAcMessage sent.");
								state = "waitingForData";
							}
							else throw new DIInternalErrorException("");
						}
						else if(b == AAbortMessage.ItemType)
						{
							handlingOfReceivedAbort();
							break;
						}
						else		// nem vart PDU
						{
							handlingOfReceivedUnexpectedPDU();
							break;
						}
					}
					else if(state.equals("waitingForData"))
					{
						// Waiting for input...
						int b = in.readByte();

						if(b == PDataTfMessage.ItemType)	// P-DATA-TF
						{
							printDiag("PDataTfMessage received.");
							slManager.processServiceRequest();
						}
						else if(b == AReleaseRqMessage.ItemType)
						{
							printDiag("AReleaseRqMessage received.");
							AReleaseRqMessage mes = new AReleaseRqMessage(in);
							AReleaseRpMessage resp = new AReleaseRpMessage();
							out.write(resp.getData());
							out.flush();
							printDiag("AReleaseRpMessage sent.");
							closeSocket();
							break;
						}
						else if(b == AAbortMessage.ItemType)
						{
							handlingOfReceivedAbort();
							break;
						}
						else		// nem vart PDU
						{
							handlingOfReceivedUnexpectedPDU();
							break;
						}
					}
				}
//			}

			serverSocket.close();
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
		}
	}

	private Object processAAssociateRqMessage(AAssociateRqMessage mes, AssociationSettings aSettings) throws DIException, IOException
	{
		aSettings.setCalledAETitle(mes.getCalledAETitle());
		aSettings.setCallingAETitle(mes.getCallingAETitle());

		// presentation contexts
		ArrayList<PresentationContextItem> pcItems = mes.getPresentationContextItems();
		for(int i = 0; i < pcItems.size(); i++)
		{
			PresentationContextItem pcItem = pcItems.get(i);
			String abstractSyntax = pcItem.getRqAbstractSyntaxItem().getAbstactSyntax();
			ArrayList<TransferSyntaxItem> transferSyntaxItems = pcItem.getRqTransferSyntaxItems();
			ArrayList<String> transferSyntaxes = new ArrayList<String>();
			for(int k = 0; k < transferSyntaxItems.size(); k++)
				transferSyntaxes.add(transferSyntaxItems.get(k).getTransferSyntax());

			PresentationContext pc = new PresentationContext();
			pc.setAbstractSyntax(abstractSyntax);
			pc.setTransferSyntaxes(transferSyntaxes);
			pc.setId(pcItem.getRqPresentationContextID());
			aSettings.addPresentationContext(pc);

			if(SOP.CR_ImageStorage.equals(abstractSyntax))		// Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.CT_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.EnhancedCT_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.MR_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.EnhancedMR_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.MG_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else if(SOP.DX_ImageStorage.equals(abstractSyntax)) // Verification
			{
				// Prioritas szerint vegigmegyunk a tamogatott transfer syntax-okon:
				if(transferSyntaxes.contains(TransferSyntax.JPEG2000_LosslessOnly))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000_LosslessOnly);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRLittleEndian)) // Explicit VR Little Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRLittleEndian);
				else if(transferSyntaxes.contains(TransferSyntax.ExplicitVRBigEndian))		// Explicit VR Big Endian
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.ExplicitVRBigEndian);
				else if(transferSyntaxes.contains(TransferSyntax.JPEG2000))
					aSettings.updatePresentationContext(pc.getId(), 0, TransferSyntax.JPEG2000);
				else
					aSettings.updatePresentationContext(pc.getId(), 4, "");		// transfer syntax-ok nem tamogatottak
			}
			else	// nem tamogatott
			{
				aSettings.updatePresentationContext(pc.getId(), 3, "");		// abstract syntax nem tamogatott
			}
		}

		// User information
		aSettings.setPdvListMaxSizeForSending((int) mes.getUserInformationItem().getMaximumLengthItem().getPdvListMaxSizeForSending());

		Object result = null;

		// Create A-ASSOCIATE-AC Message
		AAssociateAcMessage acMes = new AAssociateAcMessage(aSettings, mes);
		result = acMes;

		return result;
	}

	public byte[] receiveData(boolean isCommand) throws DIException
	{
//		DIInputStreamReader in = new DIInputStreamReader(AssociationSettings.EndianType, bis);

		try
		{
			ArrayList<PDVItem> pdvItems = new ArrayList<PDVItem>();
			boolean lastFragment = false;
			boolean firstByteIsRead = false;
//			ArtimTimer slTimer = new ArtimTimer(this, TimerForServiceLevel);			// Service Level Timer
//			ArtimTimer pdataTimer = new ArtimTimer(this, TimerForPData);				// Timer a P-DATA-TF-ek kozti idohoz

			if(isCommand)
			{
				earlyDataSetPdvItems.clear();
//				slTimer.startTimer(aSettings.getServiceMessageResponseTimeout());		// service level timer inditasa
				firstByteIsRead = true;
				printDiag("command message receiving started...");
			}
			else	// Data set
			{
				pdvItems.addAll(earlyDataSetPdvItems);
				if(pdvItems.size() > 0 && pdvItems.get(pdvItems.size() - 1).isLastFragment()) lastFragment = true;
				printDiag("waiting for data message...");
			}

			// P-DATA-TF message-ek beolvasasa
			while(!lastFragment)
			{
				int b = 0;
				if(firstByteIsRead) b = PDataTfMessage.ItemType;		// az elso P-DATA-TF-nel az elso byte mar be van olvasva!
				else b = in.readByte();									// a tobbinel be kell olvasni az elso byte-ot is.
				printDiag("First byte of P-DATA-TF message is received.");
				firstByteIsRead = false;

				if(b == PDataTfMessage.ItemType)
				{
					printDiag("  P-DATA-TF is being read...");
					PDataTfMessage mes = new PDataTfMessage(in, diagOut);
					printDiag("  P-DATA-TF has been read.");
					int i = 0;
					for(; i < mes.getPdvItems().size(); i++)	// PDV-k beolvasasa
					{
						PDVItem item = mes.getPdvItems().get(i);
						if(isCommand && item.getInformationType() != PDVItem.Command)
							DIException.createAndThrow("ERR_1402", i + 1);
						else if(!isCommand && item.getInformationType() != PDVItem.DataSet)
							DIException.createAndThrow("ERR_1403", i + 1);
						pdvItems.add(item);
						if(item.isLastFragment())
						{
							lastFragment = true;
							break;
						}
					}

					// Ha olyan P-DATA-TF jott amiben Command es Data set is van
					if(isCommand && i < mes.getPdvItems().size() - 1)
					{
						// akkor beolvassuk a Data set-hez tartozo PDV-ket es eltaroljuk a kovetkezo receiveData(false) hivashoz.
						i++;
						for(; i < mes.getPdvItems().size(); i++)
						{
							PDVItem item = mes.getPdvItems().get(i);
							if(item.getInformationType() != PDVItem.DataSet)
								DIException.createAndThrow("ERR_1403", i + 1);
							earlyDataSetPdvItems.add(item);
							if(item.isLastFragment()) break;
						}
					}

//					slTimer.stopTimer();
//					pdataTimer.stopTimer();
				}
				else if(b == AAbortMessage.ItemType)
				{
					handlingOfReceivedAbort();
					DIException.createAndThrow("ERR_1450");
				}
				else		// nem vart PDU
				{
					handlingOfReceivedUnexpectedPDU();
					DIException.createAndThrow("ERR_1451");
				}
			}
			printDiag("All P-DATA-TF messages are received.");

			// data tomb osszeallitasa
			int length = 0;
			for(int i = 0; i < pdvItems.size(); i++) length += pdvItems.get(i).getFragmentData().length;
			byte[] data = new byte[length];
			int offs = 0;
			for(int i = 0; i < pdvItems.size(); i++)	// fragment-ek bemasolasa a vegso data tombbe
			{
				byte[] fragment = pdvItems.get(i).getFragmentData();
				System.arraycopy(fragment, 0, data, offs, fragment.length);
				offs += fragment.length;
			}

			return data;
		}
//		catch (SocketException ex)
//		{
//			if("socket closed".equals(ex.getMessage()))		// ha a beallitott timeout letelt
//			{
//				handlingOfTimeout();
//			}
//
//			isAssociationCreated = false;
//			DIException.createAndThrow(ex, "ERR_1004");
//			return null;
//		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
	}

	public void sendData(byte[] commandData, byte[] dataSetData, int pcID) throws DIException
	{
		// P-DATA-TF elemek letrehozasa es kuldese
		//----------------------------------------
		try
		{
			int pdvListMaxSize = aSettings.getPdvListMaxSizeForSending();
			if(pdvListMaxSize == 0) pdvListMaxSize = Integer.MAX_VALUE;		// ha a PDV list max. size 0, akkor nincs limitalva
			int cOffs = 0;		// command fragment offset
			int cLen = pdvListMaxSize - 6;	// command fragment length
			int dOffs = 0;		// data set fragment offset
			int dLen = 0;		// data set fragment length

			// Command PDV-k letrehozasa, kiveve az utolso PDV-t
			while(commandData.length - cOffs > cLen)
			{
				PDataTfMessage mes = new PDataTfMessage(commandData, cOffs, cLen, null, 0, 0, pcID);
				out.write(mes.getData());
				out.flush();
				printDiag("PDataTfMessage sent.");
				cOffs += cLen;
			}

			// Utolso command PDV + esetleg elso data set PDV
			if(commandData.length - cOffs <= pdvListMaxSize - 14		// 14: 6 btye command header + 6 byte dataset header + 2 byte dataset
				&& dataSetData != null)
			{
				cLen = commandData.length - cOffs;
				dLen = pdvListMaxSize - (cLen + 12);		// 12: 6 btye command header + 6 byte dataset header
				if(dLen > dataSetData.length) dLen = dataSetData.length;
				PDataTfMessage mes = new PDataTfMessage(commandData, cOffs, cLen, dataSetData, dOffs, dLen, pcID);
				out.write(mes.getData());
				out.flush();
				printDiag("PDataTfMessage sent.");
				dOffs += dLen;
			}
			else		// Utolso command PDV (dataSet PDV nelkul)
			{
				cLen = commandData.length - cOffs;
				PDataTfMessage mes = new PDataTfMessage(commandData, cOffs, cLen, null, 0, 0, pcID);
				out.write(mes.getData());
				out.flush();
				printDiag("PDataTfMessage sent.");
			}

			if(dataSetData != null && dataSetData.length > dOffs)		// ha van dataSet es meg van nem elkuldott resze
			{
				// dataSet PDV-k letrehozasa, kiveve az utolso PDV-t
				dLen = pdvListMaxSize - 6;
				while(dataSetData.length - dOffs > dLen)
				{
					PDataTfMessage mes = new PDataTfMessage(null, 0, 0, dataSetData, dOffs, dLen, pcID);
					out.write(mes.getData());
					out.flush();
					printDiag("PDataTfMessage sent.");
					dOffs += dLen;
				}

				// Utolso dataSet PDV
				dLen = dataSetData.length - dOffs;
				PDataTfMessage mes = new PDataTfMessage(null, 0, 0, dataSetData, dOffs, dLen, pcID);
				out.write(mes.getData());
				out.flush();
				printDiag("PDataTfMessage sent.");
			}
		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
		}
	}

	//-----------------------------

	private void handlingOfReceivedAbort() throws IOException, DIAssociationContentErrorException
	{
		AAbortMessage mes = new AAbortMessage(in);
		printDiag("Abort message received.");
		closeSocket();
	}

	private void handlingOfReceivedUnexpectedPDU() throws IOException
	{
		// Unexpected PDU received.
		printDiag("Unexpected PDU received.");
		AAbortMessage aAbortMessage = new AAbortMessage(2, 2);	// DICOM UL service provider, unexpected PDU
		out.write(aAbortMessage.getData());
		out.flush();
		closeSocket();
	}

	private void closeSocket() throws IOException
	{
		if(bis != null) bis.close();
		bis = null;
		if(out != null) out.close();
		out = null;
		if(socket != null) socket.close();
		socket = null;
	}

	public void printDiag(String text)
	{
		if(diagOut != null)
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				String s = String.format("%02d:%02d:%02d.%03d  CMoveServer: %s", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
						cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND), text);
				diagOut.write(s);
				diagOut.newLine();
			}
			catch(IOException ex)
			{
				throw new RuntimeException("I/O exception occured while writing to diagOut.", ex);
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------------------

	/**
	 * @return the aSettings
	 */ public AssociationSettings getASettings() {
		return aSettings;
	}

	/**
	 * @return the moveOriginatorAET
	 */ public String getMoveOriginatorAET() {
		return moveOriginatorAET;
	}

	/**
	 * @return the cMoveRqMessageID
	 */ public int getcMoveRqMessageID() {
		return cMoveRqMessageID;
	}

	/**
	 * @return the failure
	 */ public boolean isFailure() {
		return failure;
	}

	/**
	 * @param failure the failure to set
	 */ public void setFailure(boolean failure) {
		this.failure = failure;
	}

	/**
	 * @return the dataSetList
	 */ public ArrayList<DicomDataSet> getDataSetList() {
		return dataSetList;
	}

	/**
	 * @return the diagOut
	 */ public BufferedWriter getDiagOut() {
		return diagOut;
	}
}
