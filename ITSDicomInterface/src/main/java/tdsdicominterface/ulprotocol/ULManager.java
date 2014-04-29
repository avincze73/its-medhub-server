// Upper Layer Manager
package tdsdicominterface.ulprotocol;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DicomInterfaceSettings;
import tdsdicominterface.exception.DIAssociationContentErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.exception.DITimeoutException;

public class ULManager
{
	public static final int TimerForAssociationLevel = 1;
	public static final int TimerForServiceLevel = 2;
	public static final int TimerForPData = 3;				// fogadott P-DATA-TF message-ek kozti idohoz

	private Socket clientSocket;
	private BufferedOutputStream out;
	private BufferedInputStream bis;

	private AssociationSettings aSettings;
	private boolean isAssociationCreated;
	private volatile int timerType;			// timeout eseten ez a valtozo tartalmazza, hogy milyen tipusu timer jart eppen le

	private ArrayList<PDVItem> earlyDataSetPdvItems;	// Ha az utolso Command PDV-t tartalmazo P-DATA-TF Data set PDV-(ke)t is tartalmaz,
														// akkor azok ebbe a listaba kerulnek eltarolasra a kovetkezo (Data set-et beolvaso)
														// receiveData() hivasig.
	private BufferedWriter diagOut;

	public ULManager(BufferedWriter diagOut)
	{
		isAssociationCreated = false;
		earlyDataSetPdvItems = new ArrayList<PDVItem>();
		timerType = 0;
		this.diagOut = diagOut;
	}

	/**
	 * DICOM association letrehozas az Upper Layer protocol szerint.
	 * Ha visszater az eljaras, akkor sikeresen letrejott az association.
	 * @param diSettings
	 * @param aSettings
	 * @throws DIException
	 */
	public void createAssociation(DicomInterfaceSettings diSettings, AssociationSettings aSettings) throws DIException
	{
		if(isIsAssociationCreated()) throw new DIInternalErrorException("ULManager.createAssociation(): Association is already created.");

		this.aSettings = aSettings;
		DIInputStreamReader in = null;

		// Socket letrehozas
		try
		{
			clientSocket = new Socket(diSettings.getPacsServerIpAddress(), diSettings.getPacsServerPort());
			out = new BufferedOutputStream(clientSocket.getOutputStream());
			bis = new BufferedInputStream(clientSocket.getInputStream());
			in = new DIInputStreamReader(AssociationSettings.EndianType, bis);
		}
		catch (UnknownHostException ex)
		{
			DIException.createAndThrow("ERR_1001");
		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1002");
		}

		try
		{
			// A-ASSOCIATE-RQ message kuldese
			AAssociateRqMessage aAssociateRqMessage = new AAssociateRqMessage(aSettings);
			out.write(aAssociateRqMessage.getData());
			out.flush();
			printDiag("AAssociateRqMessage sent.");

			// A-ASSOCIATE response fogadasa
			ArtimTimer timer = new ArtimTimer(this, TimerForAssociationLevel, diagOut);
			timer.startTimer(aSettings.getAssociationMessageResponseTimeout());
			int b = in.readByte();

			// response tipus meghatarozas
			if(b == AAssociateAcMessage.ItemType)
			{
				AAssociateAcMessage aAssociateAcMessage = new AAssociateAcMessage(in);
				timer.stopTimer();
				printDiag("AAssociateAcMessage received.");

				// Association settings modositas
				//     Presentation context update
				ArrayList<PresentationContextItem> pcItems = aAssociateAcMessage.getPresentationContextItems();
				for(int i = 0; i < pcItems.size(); i++)
				{
					aSettings.updatePresentationContext(pcItems.get(i).getAcPresentationContextID(),
							pcItems.get(i).getAcResult(), pcItems.get(i).getAcAcceptedTransferSyntax());
				}
				//     pdvListMaxSizeForSending beallitas
				aSettings.setPdvListMaxSizeForSending(
						(int) aAssociateAcMessage.getUserInformationItem().getMaximumLengthItem().getPdvListMaxSizeForSending());
				
			}
			else if(b == AAssociateRjMessage.ItemType)
			{
				AAssociateRjMessage aAssociateRjMessage = new AAssociateRjMessage(in);
				timer.stopTimer();
				printDiag("AAssociateRjMessage received.");
				
				// Socket lezaras
				socketClose();

				// error jelzese
				String errCode = String.format("ERR_2%d%d%d", aAssociateRjMessage.getResult(),
						aAssociateRjMessage.getSource(), aAssociateRjMessage.getReason());
				DIException.createAndThrow(errCode);
			}
			else if(b == AAbortMessage.ItemType)
			{
				handlingOfReceivedAbort(in, timer);
			}
			else		// nem vart PDU
			{
				handlingOfReceivedUnexpectedPDU(in, timer);
			}
		}
		catch (SocketException ex)
		{
			if("socket closed".equals(ex.getMessage()))		// ha a beallitott timeout letelt
			{
				handlingOfTimeout();
			}

			DIException.createAndThrow(ex, "ERR_1004");
		}
		catch (EOFException ex)
		{
			DIException.createAndThrow(ex, "ERR_1201");
		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
		}

		isAssociationCreated = true;
	}

	public void releaseAssociation() throws DIException
	{
		if(!isIsAssociationCreated()) throw new DIInternalErrorException("ULManager.releaseAssociation(): Association is not created.");

		DIInputStreamReader in = new DIInputStreamReader(AssociationSettings.EndianType, getBis());

		try
		{
			// A-RELEASE-RQ message kuldese
			AReleaseRqMessage aReleaseRqMessage = new AReleaseRqMessage();
			out.write(aReleaseRqMessage.getData());
			out.flush();
			printDiag("AReleaseRqMessage sent.");

			// A-RELEASE response fogadasa
			//----------------------------
			ArtimTimer timer = new ArtimTimer(this, TimerForAssociationLevel, diagOut);
			timer.startTimer(aSettings.getAssociationMessageResponseTimeout());
			int b = in.readByte();

			// response tipus meghatarozas
			if(b == AReleaseRpMessage.ItemType)
			{
				AReleaseRpMessage aReleaseRpMessage = new AReleaseRpMessage(in);
				timer.stopTimer();
				printDiag("AReleaseRpMessage received.");

				// Socket lezaras
				socketClose();
			}
			else if(b == AAbortMessage.ItemType)
			{
				handlingOfReceivedAbort(in, timer);
			}
			else		// nem vart PDU
			{
				handlingOfReceivedUnexpectedPDU(in, timer);
			}
		}
		catch (SocketException ex)
		{
			if("socket closed".equals(ex.getMessage()))		// ha a beallitott timeout letelt
			{
				handlingOfTimeout();
			}

			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
		}
		catch (IOException ex)
		{
			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
		}
	}

	/**
	 * P-DATA-TF-ek kuldese a command-nak es data set-nek megfeleloen.
	 * @param commandData
	 * @param dataSetData
	 * @param pcID Presentation context ID
	 * @throws DIException
	 */
	public void sendData(byte[] commandData, byte[] dataSetData, String abstractSyntax) throws DIException
	{
		if(!isIsAssociationCreated()) throw new DIInternalErrorException("ULManager.sendData(): Association is not created.");

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

			int pcID = aSettings.getPresentationContextByAbstractSyntax(abstractSyntax).getId();

			// Command PDV-k letrehozasa, kiveve az utolso PDV-t
			while(commandData.length - cOffs > cLen)
			{
				PDataTfMessage mes = new PDataTfMessage(commandData, cOffs, cLen, null, 0, 0, pcID);
				out.write(mes.getData());
				out.flush();
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
				dOffs += dLen;
			}
			else		// Utolso command PDV (dataSet PDV nelkul)
			{
				cLen = commandData.length - cOffs;
				PDataTfMessage mes = new PDataTfMessage(commandData, cOffs, cLen, null, 0, 0, pcID);
				out.write(mes.getData());
				out.flush();
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
					dOffs += dLen;
				}

				// Utolso dataSet PDV
				dLen = dataSetData.length - dOffs;
				PDataTfMessage mes = new PDataTfMessage(null, 0, 0, dataSetData, dOffs, dLen, pcID);
				out.write(mes.getData());
				out.flush();
			}
		}
		catch (IOException ex)
		{
			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
		}
	}

	public byte[] receiveData(boolean isCommand) throws DIException
	{
		if(!isIsAssociationCreated()) throw new DIInternalErrorException("ULManager.receiveData(): Association is not created.");

		DIInputStreamReader in = new DIInputStreamReader(AssociationSettings.EndianType, getBis());

		try
		{
			ArrayList<PDVItem> pdvItems = new ArrayList<PDVItem>();
			boolean lastFragment = false;
			ArtimTimer slTimer = new ArtimTimer(this, TimerForServiceLevel, diagOut);			// Service Level Timer
			ArtimTimer pdataTimer = null;				// Timer a P-DATA-TF-ek kozti idohoz

			if(isCommand)
			{
				earlyDataSetPdvItems.clear();
				slTimer.startTimer(aSettings.getServiceMessageResponseTimeout());		// service level timer inditasa
			}
			else	// Data set
			{
				pdvItems.addAll(earlyDataSetPdvItems);
				if(pdvItems.size() > 0 && pdvItems.get(pdvItems.size() - 1).isLastFragment()) lastFragment = true;
			}

			// P-DATA-TF message-ek beolvasasa
			while(!lastFragment)
			{
				if(!slTimer.isRunning())
				{
					pdataTimer = new ArtimTimer(this, TimerForPData, diagOut);
					pdataTimer.startTimer(aSettings.getPdataTimeout());
				}
				int b = in.readByte();
				printDiag("First byte is received.");
				
				if(b == PDataTfMessage.ItemType)
				{
					PDataTfMessage mes = new PDataTfMessage(in, null);
					printDiag("PDataTfMessage received.");
					int i = 0;
					for(; i < mes.getPdvItems().size(); i++)	// PDV-k beolvasasa
					{
						PDVItem item = mes.getPdvItems().get(i);
						if(isCommand && item.getInformationType() != PDVItem.Command)
						{
							sendAbortAndCloseConnection(2, 5);		// service provider, unexpected PDU parameter
							DIAssociationContentErrorException.createAndThrow(
									"P-DATA-FT: %d. PDV: Data set PDV is received instead of Command PDV.", i + 1);
						}
						else if(!isCommand && item.getInformationType() != PDVItem.DataSet)
						{
							sendAbortAndCloseConnection(2, 5);		// service provider, unexpected PDU parameter
							DIAssociationContentErrorException.createAndThrow(
									"P-DATA-FT: %d. PDV: Command PDV is received instead of Data set PDV.", i + 1);
						}
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
							{
								sendAbortAndCloseConnection(2, 5);		// service provider, unexpected PDU parameter
								DIAssociationContentErrorException.createAndThrow(
										"P-DATA-FT: %d. PDV: Command PDV is received instead of Data set PDV.", i + 1);
							}
							earlyDataSetPdvItems.add(item);
							if(item.isLastFragment()) break;
						}
					}

					slTimer.stopTimer();
					if(pdataTimer != null)
						pdataTimer.stopTimer();
				}
				else if(b == AAbortMessage.ItemType)
				{
					slTimer.stopTimer();
					handlingOfReceivedAbort(in, pdataTimer);
				}
				else		// nem vart PDU
				{
					slTimer.stopTimer();
					handlingOfReceivedUnexpectedPDU(in, pdataTimer);
				}
			}
			printDiag("All PDataTfMessages received.");

			// data tomb osszeallitasa
			int length = 0;
			for(int i = 0; i < pdvItems.size(); i++) length += pdvItems.get(i).getFragmentData().length;
			byte[] data = new byte[length];
			int offs = 0;
			for(int i = 0; i < pdvItems.size(); i++)	// fragment-ek bemasolasa a vegso data tombbe
			{
				byte[] fragment = pdvItems.get(i).getFragmentData();
				copyByteArray(fragment, data, offs);
				offs += fragment.length;
			}

			return data;
		}
		catch (SocketException ex)
		{
			if("socket closed".equals(ex.getMessage()))		// ha a beallitott timeout letelt
			{
				handlingOfTimeout();
			}

			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
		catch (IOException ex)
		{
			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
	}

	/**
	 * Ha letezo association soran programozott exception-t kell dobni, akkor elotte meg kell hivni ezt a metodust,
	 * hogy Abort message-et kuldjunk a masik felnek es lezarjuk a connection-t.
	 * @param source
	 * @param reason
	 */
	public void sendAbortAndCloseConnection(int source, int reason)
	{
		try
		{
			AAbortMessage mes = new AAbortMessage(source, reason);
			out.write(mes.getData());
			out.flush();
			socketClose();
		}
		catch (Exception ex)
		{
			isAssociationCreated = false;
		}
	}

	//----------------------------

	private void handlingOfReceivedAbort(DIInputStreamReader in, ArtimTimer timer) throws DIException
	{
		try
		{
			AAbortMessage aAbortMessage = new AAbortMessage(in);	// Abort message beolvasasa
			if(timer != null) timer.stopTimer();		// ha az ARTIM timer el lett inditva, akkor itt leallitjuk
			printDiag("AAbortMessage received.");

			// Socket lezaras
			socketClose();

			// error jelzese
			String errCode = null;
			if(aAbortMessage.getSource() == 0)		// ha DICOM UL service-user
				errCode = "ERR_3000";
			else
				errCode = String.format("ERR_30%d%d", aAbortMessage.getSource(), aAbortMessage.getReason());
			DIException.createAndThrow(errCode);
		}
		catch (IOException ex)
		{
			isAssociationCreated = false;
			DIException.createAndThrow(ex, "ERR_1004");
		}
	}

	private void handlingOfReceivedUnexpectedPDU(DIInputStreamReader in, ArtimTimer timer) throws DIException
	{
		if(timer != null) timer.stopTimer();		// ha az ARTIM timer el lett inditva, akkor itt leallitjuk
		printDiag("Unexpected PDU received.");

		// A-ABORT message kuldese + Socket lezaras
		sendAbortAndCloseConnection(2, 2);		// DICOM UL service provider, unexpected PDU

		// error jelzese
		DIException.createAndThrow("ERR_3101");
	}

	private void handlingOfTimeout() throws DIException
	{
		// Socket lezaras
		socketClose();

		// Timeout exception
		if(timerType == TimerForAssociationLevel)
			DITimeoutException.createAndThrow("ERR_1101");
		else if(timerType == TimerForServiceLevel)
			DITimeoutException.createAndThrow("ERR_1102");
		else if(timerType == TimerForPData)
			DITimeoutException.createAndThrow("ERR_1103");
		else
			throw new DIInternalErrorException("Illegal timerType: " + timerType);
	}

	private void socketClose() throws DIException
	{
		try
		{
			isAssociationCreated = false;
			if(bis != null) bis.close();
			if(out != null) out.close();
			clientSocket.close();
		}
		catch (IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1003");
		}
	}

	private void copyByteArray(byte[] fragment, byte[] array, int offs)
	{
		for(int i = 0, pos = offs; i < fragment.length; i++, pos++)
		{
			array[pos] = fragment[i];
		}
	}

	//---------------------------------------------------------------------------------------------------------

	public void printDiag(String text)
	{
		if(diagOut != null)
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				String s = String.format("%02d:%02d:%02d.%03d  ULManager: %s", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
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

	//---------------------------------------------------------------------------------------------------------

	/**
	 * @return the timerType
	 */ public int getTimerType() {
		return timerType;
	}

	/**
	 * @param timerType the timerType to set
	 */ public void setTimerType(int timerType) {
		this.timerType = timerType;
	}

	/**
	 * @return the bis
	 */ public BufferedInputStream getBis() {
		return bis;
	}

	/**
	 * @return the aSettings
	 */ public AssociationSettings getASettings() {
		return aSettings;
	}

	/**
	 * @return the isAssociationCreated
	 */ public boolean isIsAssociationCreated() {
		return isAssociationCreated;
	}
}
