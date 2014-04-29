package tdsdicominterface.cmoveserver;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIContentErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.slprotocol.CStoreRq;
import tdsdicominterface.slprotocol.CStoreRsp;
import tdsdicominterface.ulprotocol.AssociationSettings.PresentationContext;

public class ServerSLManager
{
	private ServerULManager ulManager;

	public ServerSLManager(ServerULManager ulManager)
	{
		this.ulManager = ulManager;
	}

	public void processServiceRequest() throws DIException
	{
		byte[] commandData = ulManager.receiveData(true);

		DicomItem commandSet = readCommandSet(commandData);

		ulManager.printDiag("Command set received:");
		for(int i = 0; i < commandSet.getDataElements().size(); i++)
			ulManager.printDiag("    " + commandSet.getDataElements().get(i).toString());

		int command = commandSet.getFirstInt(0x0000, 0x0100);		// command field

		if(command == CStoreRq.CommandNum) processCStoreRq(commandSet);
		else
			DIContentErrorException.createAndThrow(0, "Unexpected command received (0x%1$04X)!", command);
	}

	private void processCStoreRq(DicomItem commandSet) throws DIException
	{
		ulManager.printDiag("CStoreRq received.");
		CStoreRq request = new CStoreRq(commandSet);			// command adatok kinyerese		

		// Teljes dataSet kinyerese
		byte[] dataSetData = ulManager.receiveData(false);
		PresentationContext pc = ulManager.getASettings().getPresentationContextByAbstractSyntax(request.getSopClassUID());
		TransferSyntax ts = new TransferSyntax(pc.getAccTransferSyntax());
		DicomDataSet dataSet = readDataSet(dataSetData, ts);

		int status = CStoreRsp.StatusSuccess;
		String sopInstanceUID = dataSet.getString(0x0008, 0x0018);
		if(sopInstanceUID == null)
		{
			ulManager.printDiag("Error: sopInstanceUID is not found in dataSet");
			status = CStoreRsp.StatusFailure;
		}

		// ellenorzes
		if(!request.getMoveOriginatorAET().equals(ulManager.getMoveOriginatorAET())
				|| request.getMoveOriginatorMessageID() != ulManager.getcMoveRqMessageID())
		{
			ulManager.printDiag("Error:\n    MoveOriginatorAET in request: " + request.getMoveOriginatorAET() + ", should be: " +
					ulManager.getMoveOriginatorAET() + "\n    MoveOriginatorMessageID in request: " + request.getMoveOriginatorMessageID() +
					", should be: " + ulManager.getcMoveRqMessageID() + ".");
			status = CStoreRsp.StatusFailure;
		}

		if(status == CStoreRsp.StatusSuccess && !ulManager.isFailure())
		{
			ulManager.getDataSetList().add(dataSet);
			ulManager.printDiag("Data set is received successfully.");
		}
		else
		{
			ulManager.setFailure(true);
			ulManager.printDiag("Some failure occured during the reception of Data set.");
		}

		// response kuldese
		CStoreRsp response = new CStoreRsp(request, status, sopInstanceUID);
		ulManager.sendData(response.getCommandData(), null, pc.getId());	// C-STORE-RSP kuldese
		ulManager.printDiag("C-STORE-RSP sent.");
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

	private DicomDataSet readDataSet(byte[] dataSetData, TransferSyntax transferSyntax) throws DIContentErrorException, DIException
	{
		DIInputStreamReader in = new DIInputStreamReader(transferSyntax.getEndianType(), dataSetData);
		DICoder coder = new DICoder(transferSyntax);

		try
		{
			// Data group beolvasas
			DicomDataSet dataSet = new DicomDataSet();
			while(in.getPosition() < dataSetData.length)
			{
				dataSet.addDataElement(coder.readDataElement(in));
			}
			dataSet.setTransferSyntax(transferSyntax.getTransferSyntax());
			dataSet.setSopInstanceUID(dataSet.getString(0x0008, 0x0018));
			return dataSet;
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_1004");
			return null;
		}
	}
}
