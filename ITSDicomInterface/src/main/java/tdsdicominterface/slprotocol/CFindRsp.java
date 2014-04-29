package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CFindRsp
{
	public static final int CommandNum = 0x8020;

	public static final int StatusSuccess = 0x0000;
	public static final int StatusPending = 0xff00;
	public static final int StatusPendingWithWarning = 0xff01;
	public static final int StatusWarning = 0x0001;
	public static final int StatusFailureRefused = 0xa700;
	public static final int StatusFailureIdentifierDoesNotMatchSOPClass = 0xa900;
	public static final int StatusFailureUnableToProcess = 0xc000;
	public static final int StatusCancel = 0xfe00;

	// valtozok a kiirashoz
	private byte[] commandData;
	private byte[] dataSetData;

	public CFindRsp(CFindRq rq, int status, DicomItem record, TransferSyntax dataTransferSyntax) throws DIException
	{
		try
		{
			// Command set letrehozasa
			//------------------------
			TransferSyntax transferSyntax = TransferSyntax.getCommandTransferSyntax();
			DIOutputStreamWriter out = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DIOutputStreamWriter out1 = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DICoder coder = new DICoder(transferSyntax);

			// out1 feltoltese - a command group length-et koveto mezokkel
			coder.writeDataElement(new DataElement(0x0000, 0x0002, "UI", rq.getSopClassUID()), out1);	// affected SOP class UID
			coder.writeDataElement(new DataElement(0x0000, 0x0100, "US", CommandNum), out1);			// command field
			coder.writeDataElement(new DataElement(0x0000, 0x0120, "US", rq.getMessageID()), out1);		// message ID
			int dataSetType = 0x0101;
			if(status == StatusPending) dataSetType = 0x0000;		// van data set
			coder.writeDataElement(new DataElement(0x0000, 0x0800, "US", dataSetType), out1);			// data set type
			coder.writeDataElement(new DataElement(0x0000, 0x0900, "US", status), out1);				// statusz

			// out feltoltese
			coder.writeDataElement(new DataElement(0x0000, 0x0000, "UL", (long)out1.getData().length), out);	// command group length
			out.writeByteArray(out1.getData());

			commandData = out.getData();

			// Data set letrehozasa
			//---------------------
			if(record != null)
			{
				DIOutputStreamWriter dout = new DIOutputStreamWriter(dataTransferSyntax.getEndianType());
				coder = new DICoder(dataTransferSyntax);
				for(int i = 0; i < record.getDataElements().size(); i++)
				{
					coder.writeDataElement(record.getDataElements().get(i), dout);
				}

				dataSetData = dout.getData();
			}
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_4003");
		}
	}

	public CFindRsp(DicomItem commandSet) throws DIException
	{
		// nincs teendo
	}

	//-----------------------------------------------------------------------------------

	/**
	 * @return the commandData
	 */ public byte[] getCommandData() {
		return commandData;
	}

	/**
	 * @return the dataSetData
	 */ public byte[] getDataSetData() {
		return dataSetData;
	}
}
