package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CStoreRsp
{
	public static final int CommandNum = 0x8001;

	public static final int StatusSuccess = 0x0000;
	public static final int StatusFailure = 0xc000;

	// valtozok a kiirashoz
	private byte[] commandData;

	public CStoreRsp(CStoreRq rq, int status, String sopInstanceUID) throws DIException
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
			coder.writeDataElement(new DataElement(0x0000, 0x0800, "US", 0x0101), out1);				// data set type (null)
			coder.writeDataElement(new DataElement(0x0000, 0x0900, "US", status), out1);				// statusz
			coder.writeDataElement(new DataElement(0x0000, 0x1000, "UI", sopInstanceUID), out1);		// Affected SOP instance UID

			// out feltoltese
			coder.writeDataElement(new DataElement(0x0000, 0x0000, "UL", (long)out1.getData().length), out);	// command group length
			out.writeByteArray(out1.getData());

			commandData = out.getData();
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_4003");
		}
	}

	//-----------------------------------------------------------------------------------

	/**
	 * @return the commandData
	 */ public byte[] getCommandData() {
		return commandData;
	}
}
