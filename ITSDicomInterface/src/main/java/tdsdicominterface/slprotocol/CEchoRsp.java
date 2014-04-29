package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CEchoRsp
{
	public static final int CommandNum = 0x8030;

	// valtozok a kiirashoz
	private byte[] commandData;

	public CEchoRsp(CEchoRq rq) throws DIException
	{
		try
		{
			TransferSyntax transferSyntax = TransferSyntax.getCommandTransferSyntax();
			DIOutputStreamWriter out = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DIOutputStreamWriter out1 = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DICoder coder = new DICoder(transferSyntax);

			// out1 feltoltese - a command group length-et koveto mezokkel
			coder.writeDataElement(new DataElement(0x0000, 0x0002, "UI", SOP.Verification), out1);	// affected SOP class UID
			coder.writeDataElement(new DataElement(0x0000, 0x0100, "US", CommandNum), out1);	// command field
			coder.writeDataElement(new DataElement(0x0000, 0x0120, "US", rq.getMessageID()), out1);	// message ID
			coder.writeDataElement(new DataElement(0x0000, 0x0800, "US", 0x0101), out1);		// data set type - nincs data set
			coder.writeDataElement(new DataElement(0x0000, 0x0900, "US", 0), out1);				// statusz - Success

			// out feltoltese
			coder.writeDataElement(new DataElement(0x0000, 0x0000, "UL", (long)out1.getData().length), out);		// command group length
			out.writeByteArray(out1.getData());

			commandData = out.getData();
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_4003");
		}
	}

	public CEchoRsp(DicomItem commandSet) throws DIException
	{
		// nincs teendo
	}

	/**
	 * @return the commandData
	 */ public byte[] getCommandData() {
		return commandData;
	}
}
