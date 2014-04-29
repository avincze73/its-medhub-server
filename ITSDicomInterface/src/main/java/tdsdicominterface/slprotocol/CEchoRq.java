package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CEchoRq
{
	public static final int CommandNum = 0x0030;

	// valtozok a kiirashoz
	private byte[] commandData;

	// valtozok a beolvasashoz
	private int messageID;

	public CEchoRq() throws DIException
	{
		try
		{
			TransferSyntax transferSyntax = TransferSyntax.getCommandTransferSyntax();
			DIOutputStreamWriter out = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DIOutputStreamWriter out1 = new DIOutputStreamWriter(transferSyntax.getEndianType());
			DICoder coder = new DICoder(transferSyntax);

			// out1 feltoltese - a command group length-et koveto mezokkel
			coder.writeDataElement(new DataElement(0x0000, 0x0002, "UI", SOP.Verification), out1);		// affected SOP class UID
			coder.writeDataElement(new DataElement(0x0000, 0x0100, "US", CommandNum), out1);	// command field
			coder.writeDataElement(new DataElement(0x0000, 0x0110, "US", 1), out1);				// message ID - Ez egyelore mindig 1, mert nem hasznalom fel sehol!
			coder.writeDataElement(new DataElement(0x0000, 0x0800, "US", 0x0101), out1);		// data set type - nincs data set

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

	public CEchoRq(DicomItem commandSet) throws DIException
	{
		messageID = commandSet.getFirstInt(0x0000, 0x0110);
	}

	//--------------------------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getCommandData() {
		return commandData;
	}

	/**
	 * @return the messageID
	 */ public int getMessageID() {
		return messageID;
	}
}
