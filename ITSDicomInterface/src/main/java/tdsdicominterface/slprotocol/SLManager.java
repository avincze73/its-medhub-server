// Service Level Manager
//----------------------

package tdsdicominterface.slprotocol;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomInterfaceSettings;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.cmoveserver.CMoveServer;
import tdsdicominterface.exception.DIContentErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.exception.DITimeoutException;
import tdsdicominterface.ulprotocol.AssociationSettings;
import tdsdicominterface.ulprotocol.AssociationSettings.PresentationContext;
import tdsdicominterface.ulprotocol.ULManager;

public class SLManager
{
	private DicomInterfaceSettings diSettings;
	private ULManager ulManager;

	private BufferedWriter diagOut;

	/**
	 * SLManager letrehozasa. Letrejon az association is. A kommunikacio vegen le kell zarni az association-t
	 * a closeAssociation() metodussal!
	 * @param diSettings
	 * @throws DIException
	 */
	public SLManager(DicomInterfaceSettings diSettings, BufferedWriter diagOut) throws DIException
	{
		this.diSettings = diSettings;
		this.diagOut = diagOut;
		ulManager = new ULManager(diagOut);
		createAssociation();
	}

	public void closeAssociation() throws DIException
	{
		ulManager.releaseAssociation();
	}

	public boolean isAssociationCreated()
	{
		return ulManager.isIsAssociationCreated();
	}

	private void createAssociation() throws DIException
	{
		AssociationSettings aSettings = new AssociationSettings(diSettings, false);

		// Presentation context: C-ECHO
		ArrayList<String> transferSyntaxes = new ArrayList<String>();		// transfer syntax-ok letrehozasa
		transferSyntaxes.add(TransferSyntax.ImplicitVRLittleEndian);
		aSettings.addPresentationContext(SOP.Verification, transferSyntaxes);	// Presentation Context letrehozasa

		// Presentation context: C-FIND STUDY root
		transferSyntaxes = new ArrayList<String>();		// transfer syntax-ok letrehozasa
		transferSyntaxes.add(TransferSyntax.ImplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRBigEndian);
		aSettings.addPresentationContext(SOP.StudyRootQueryRetrieveFIND, transferSyntaxes);	// Presentation Context letrehozasa

		// Presentation context: C-FIND PATIENT root
		transferSyntaxes = new ArrayList<String>();		// transfer syntax-ok letrehozasa
		transferSyntaxes.add(TransferSyntax.ImplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRBigEndian);
		aSettings.addPresentationContext(SOP.PatientRootQueryRetrieveFIND, transferSyntaxes);	// Presentation Context letrehozasa

		// Presentation context: C-MOVE PATIENT root
		transferSyntaxes = new ArrayList<String>();		// transfer syntax-ok letrehozasa
		transferSyntaxes.add(TransferSyntax.ImplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRBigEndian);
		aSettings.addPresentationContext(SOP.PatientRootQueryRetrieveMOVE, transferSyntaxes);	// Presentation Context letrehozasa

		// Presentation context: C-MOVE STUDY root
		transferSyntaxes = new ArrayList<String>();		// transfer syntax-ok letrehozasa
		transferSyntaxes.add(TransferSyntax.ImplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRLittleEndian);
		transferSyntaxes.add(TransferSyntax.ExplicitVRBigEndian);
		aSettings.addPresentationContext(SOP.StudyRootQueryRetrieveMOVE, transferSyntaxes);	// Presentation Context letrehozasa

		// Association negotiation
		ulManager.createAssociation(diSettings, aSettings);

		// Association valasz ellenorzes
		for(int i = 0; i < aSettings.getPresentationContextCount(); i++)
		{
			PresentationContext pc = aSettings.getPresentationContext(i);
			checkPresentationContext(pc, ulManager);
			TransferSyntax dataTransferSyntax = new TransferSyntax(pc.getAccTransferSyntax());
			if(!dataTransferSyntax.isSupported())
			{
				ulManager.sendAbortAndCloseConnection(0, 0);		// service user initiated abort
				throw new DIInternalErrorException("Accepted transfer syntax is not supported! (%s)", pc.getAccTransferSyntax());
			}
		}
	}

	public void verification() throws DIException
	{
		CEchoRq request = new CEchoRq();								// C-ECHO-RQ letrehozasa

		if(diagOut != null)
		{
			printDiag("CEchoRq:");
			printDiag("  command set to be sent:");
			DicomItem commSet = readCommandSet(request.getCommandData());
			for(int i = 0; i < commSet.getDataElements().size(); i++)
				printDiag("        " + commSet.getDataElements().get(i).toString());
		}

		ulManager.sendData(request.getCommandData(), null, SOP.Verification);	// C-ECHO-RQ kuldese
		printDiag("CEchoRq sent.");

		byte[] cData = ulManager.receiveData(true);						// C-ECHO-RSP Command fogadasa (Data Set nincs hozza)
		DicomItem commandSet = readCommandSet(cData);			// Command set beolvasasa DicomItem-be

		if(diagOut != null)
		{
			printDiag("Command set received:");
			for(int i = 0; i < commandSet.getDataElements().size(); i++)
				printDiag("    " + commandSet.getDataElements().get(i).toString());
		}

		int command = commandSet.getFirstInt(0x0000, 0x0100);				// command field ellenorzes
		if(command != CEchoRsp.CommandNum)
		{
			ulManager.sendAbortAndCloseConnection(0, 0);		// service user initiated abort
			DIContentErrorException.createAndThrow(0, "Unexpected command received (0x%1$04X)!", command);
		}
		printDiag("\nCEchoRsp received.");

		// FOR TEST
		testFilebaKiiras(cData, null);
	}

	public ArrayList<DicomItem> query(String sopClassUID, DicomItem query) throws DIException
	{
		PresentationContext pc = ulManager.getASettings().getPresentationContextByAbstractSyntax(sopClassUID);
		TransferSyntax dataTransferSyntax = new TransferSyntax(pc.getAccTransferSyntax());
		CFindRq request = new CFindRq(sopClassUID, query, dataTransferSyntax);					// C-FIND-RQ letrehozasa

		if(diagOut != null)
		{
			printDiag("CFindRq:");
			printDiag("  command set to be sent:");
			DicomItem commSet = readCommandSet(request.getCommandData());
			for(int i = 0; i < commSet.getDataElements().size(); i++)
				printDiag("        " + commSet.getDataElements().get(i).toString());
			printDiag("  data set (identifier) to be sent:");
			DicomItem dataSet = readDataSet(request.getDataSetData(), dataTransferSyntax);
			for(int i = 0; i < dataSet.getDataElements().size(); i++)
				printDiag("        " + dataSet.getDataElements().get(i).toString());
		}

		ulManager.sendData(request.getCommandData(), request.getDataSetData(), sopClassUID);		// C-FIND-RQ kuldese
		printDiag("CFindRq sent.");

		// Valasz rekordok fogadasa
		ArrayList<DicomItem> queryResult = new ArrayList<DicomItem>();
		while(true)
		{
			byte[] cData = ulManager.receiveData(true);				// Command fogadasa: C-FIND-RSP
			DicomItem commandSet = readCommandSet(cData);			// Command set beolvasasa DicomItem-be

			if(diagOut != null)
			{
				printDiag("Command set received:");
				for(int i = 0; i < commandSet.getDataElements().size(); i++)
					printDiag("    " + commandSet.getDataElements().get(i).toString());
			}

			int command = commandSet.getFirstInt(0x0000, 0x0100);				// command field lekerdezes
			if(command != CFindRsp.CommandNum)
			{
				ulManager.sendAbortAndCloseConnection(0, 0);		// service user initiated abort
				DIContentErrorException.createAndThrow(0, "Unexpected command received (0x%1$04X)!", command);
			}
			printDiag("CFindRsp received.");

			int status = commandSet.getFirstInt(0x0000, 0x0900);				// status field lekerdezes
			if(status == CFindRsp.StatusSuccess)
			{
				printDiag("C-FIND-RSP status: Success.");
				break;
			}
			else if(status == CFindRsp.StatusPending || status == CFindRsp.StatusPendingWithWarning)
			{
				printDiag("C-FIND-RSP status: Pending.");

				byte[] dsData = ulManager.receiveData(false);				// Data set fogadasa
				DicomItem record = readDataSet(dsData, dataTransferSyntax);

				if(diagOut != null)
				{
					printDiag("  C-FIND-RSP: Received record:");
					for(int i = 0; i < record.getDataElements().size(); i++)
						printDiag("    " + record.getDataElements().get(i).toString());
				}

				queryResult.add(record);
			}
			else if(status == CFindRsp.StatusFailureIdentifierDoesNotMatchSOPClass)
			{
				printDiag("C-FIND-RSP status: FailureIdentifierDoesNotMatchSOPClass.");
				DIException.createAndThrow("ERR_4002");
			}
			else
			{
				printDiag("C-FIND-RSP status: Failure: Refused or unable to process.");
				DIException.createAndThrow("ERR_4004");
			}
		}

		return queryResult;
	}

	public ArrayList<DicomDataSet> retrieveByCMove(String patientID,
										String studyInstanceUID,
										String seriesInstaceUID) throws DIException
	{
		// lekerdezesi adatok osszeallitasa
		DicomItem query = new DicomItem();
		query.addDataElement(new DataElement(0x0008, 0x0052, "SERIES"));			// QueryRetrieveLevel
		query.addDataElement(new DataElement("PatientID", patientID));
		query.addDataElement(new DataElement("StudyInstanceUID", studyInstanceUID));
		query.addDataElement(new DataElement("SeriesInstanceUID", seriesInstaceUID));

		PresentationContext pc = ulManager.getASettings().getPresentationContextByAbstractSyntax(SOP.PatientRootQueryRetrieveMOVE);
		TransferSyntax dataTransferSyntax = new TransferSyntax(pc.getAccTransferSyntax());
		CMoveRq request = new CMoveRq(SOP.PatientRootQueryRetrieveMOVE, query, dataTransferSyntax, diSettings.getAeTitle());	// C-MOVE-RQ letrehozasa

		if(diagOut != null)
		{
			printDiag("CMoveRq:");
			printDiag("  command set to be sent:");
			DicomItem commSet = readCommandSet(request.getCommandData());
			for(int i = 0; i < commSet.getDataElements().size(); i++)
				printDiag("    " + commSet.getDataElements().get(i).toString());
			printDiag("  data set (identifier) to be sent:");
			DicomItem dataSet = readDataSet(request.getDataSetData(), dataTransferSyntax);
			for(int i = 0; i < dataSet.getDataElements().size(); i++)
				printDiag("    " + dataSet.getDataElements().get(i).toString());
		}

		// Server inditasa az adatok fogadasahoz
		printDiag("Starting CMoveServer.");
		CMoveServer cMoveServer = new CMoveServer(diSettings, request.getOutMessageID(), diagOut);
		cMoveServer.start();	// server inditas kulon thread-en

		// lekerdezes elkuldese
		ulManager.sendData(request.getCommandData(), request.getDataSetData(), SOP.StudyRootQueryRetrieveMOVE);		// C-MOVE-RQ kuldese
		printDiag("CMoveRq sent.");

		// Valasz fogadasa
		boolean isCancelled = false;
		while(true)
		{
			byte[] cData = null;
			try
			{
				cData = ulManager.receiveData(true);				// Command fogadasa: C-MOVE-RSP
			}
			catch(DITimeoutException ex)
			{
				printDiag("Time out...");
				if(cMoveServer.getError() != null)
					throw new DIException("An Exception occured in CMoveServer: ", cMoveServer.getError());
				throw ex;
			}
			
			DicomItem commandSet = readCommandSet(cData);			// Command set beolvasasa DicomItem-be

			if(diagOut != null)
			{
				printDiag("  command set received:");
				for(int i = 0; i < commandSet.getDataElements().size(); i++)
					printDiag("    " + commandSet.getDataElements().get(i).toString());
			}

			int command = commandSet.getFirstInt(0x0000, 0x0100);		// command field lekerdezes
			if(command != CMoveRsp.CommandNum)
			{
				printDiag("Error: NOT CMoveRsp received.");
				ulManager.sendAbortAndCloseConnection(0, 0);		// service user initiated abort
				DIContentErrorException.createAndThrow(0, "Unexpected command received (0x%1$04X)!", command);
			}
			printDiag("CMoveRsp received.");

			// Adat resz lekerdezes, ha van
			if(commandSet.getFirstInt(0x0000, 0x0800) != 0x0101)
			{
				byte[] dsData = ulManager.receiveData(false);				// Data set fogadasa
				// ezzel nem csinalunk semmit, csak beolvassuk
				// TODO: megnezni, hogy van-e, es hogy mi van benne
				printDiag("Jött valmi adat a CMoveRsp-ban. Hossza: " + dsData.length);
			}

			int status = commandSet.getFirstInt(0x0000, 0x0900);				// status field lekerdezes
			if(status == CMoveRsp.StatusSuccess)
			{
				printDiag("CMoveRsp status: Success.");
				break;
			}
			else if(status == CMoveRsp.StatusPending)
			{
				printDiag("CMoveRsp status: Pending.");
				// nincs teendo
			}
			else if(status == CMoveRsp.StatusWarning)
			{
				printDiag("CMoveRsp status: Warning.");
				DIException.createAndThrow("ERR_4005");
			}
			else if(status == CMoveRsp.StatusCancel)
			{
				printDiag("CMoveRsp status: Cancel.");
				isCancelled = true;
				break;
			}
			else if(status == CMoveRsp.StatusFailureRefused1)
			{
				printDiag("CMoveRsp status: FailureRefused1.");
				DIException.createAndThrow("ERR_4006");
			}
			else if(status == CMoveRsp.StatusFailureRefused2)
			{
				printDiag("CMoveRsp status: FailureRefused2.");
				DIException.createAndThrow("ERR_4007");
			}
			else if(status == CMoveRsp.StatusFailureUnknownDest)
			{
				printDiag("CMoveRsp status: FailureUnknownDest.");
				DIException.createAndThrow("ERR_4008");
			}
			else if(status == CMoveRsp.StatusFailureIdentifierDoesNotMatchSOPClass)
			{
				printDiag("CMoveRsp status: FailureIdentifierDoesNotMatchSOPClass.");
				DIException.createAndThrow("ERR_4009");
			}
			else
			{
				printDiag("CMoveRsp status: Failure Unable to process.");
				DIException.createAndThrow("ERR_4010");
			}
		}

		if(isCancelled) return null;

		if(cMoveServer.getError() != null)
		{
			throw new DIException("An Exception occured in CMoveServer: ", cMoveServer.getError());
		}

		return cMoveServer.getUlManager().getDataSetList();
	}

	//---------------------------------------------------------------

	private void checkPresentationContext(PresentationContext pc, ULManager ulManager) throws DIException
	{
		if(!pc.isAccepted())		// ha nem lett elfogadva
		{
			ulManager.sendAbortAndCloseConnection(0, 0);		// service user initiated abort
			String errCode = String.format("ERR_150%d", pc.getRejectionType());
			DIException.createAndThrow(errCode);
		}
	}

	private DicomItem readCommandSet(byte[] commandData) throws DIContentErrorException, DIException
	{
		TransferSyntax transferSyntax = TransferSyntax.getCommandTransferSyntax();
		DIInputStreamReader in = new DIInputStreamReader(transferSyntax.getEndianType(), commandData);
		DICoder coder = new DICoder(transferSyntax);

		try
		{
			// Command group beolvasas
			DicomItem dItem = new DicomItem();
			while(in.getPosition() < commandData.length)
			{
				dItem.addDataElement(coder.readDataElement(in));
			}
			return dItem;
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
	}

	private DicomItem readDataSet(byte[] dataSetData, TransferSyntax transferSyntax) throws DIContentErrorException, DIException
	{
		DIInputStreamReader in = new DIInputStreamReader(transferSyntax.getEndianType(), dataSetData);
		DICoder coder = new DICoder(transferSyntax);

		try
		{
			// Data group beolvasas
			DicomItem dItem = new DicomItem();
			while(in.getPosition() < dataSetData.length)
			{
				dItem.addDataElement(coder.readDataElement(in));
			}
			return dItem;
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
	}

	//-------------------------------------------------------

	public void printDiag(String text)
	{
		if(diagOut != null)
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				String s = String.format("%02d:%02d:%02d.%03d  SLManager: %s", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
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

	private void testFilebaKiiras(byte[] cDataBack, byte[] dsDataBack)
	{
		try
		{
			FileOutputStream fos1 = new FileOutputStream("c:\\Program Files\\TDSHospitalClient\\test\\cData.txt");
			FileOutputStream fos2 = new FileOutputStream("c:\\Program Files\\TDSHospitalClient\\test\\dsData.txt");

			if(cDataBack != null) fos1.write(cDataBack);
			if(dsDataBack != null) fos2.write(dsDataBack);

			fos1.close();
			fos2.close();
		}
		catch (Exception ex)
		{
			Logger.getLogger(SLManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
