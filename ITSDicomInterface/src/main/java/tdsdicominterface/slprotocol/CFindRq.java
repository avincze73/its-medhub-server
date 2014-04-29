package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CFindRq
{
	public static final int CommandNum = 0x0020;

	// valtozok a kiirashoz
	private byte[] commandData;
	private byte[] dataSetData;

	// valtozok a beolvasashoz
	private String sopClassUID;
	private int messageID;

	public CFindRq(String sopClassUID, DicomItem query, TransferSyntax dataTransferSyntax) throws DIException
	{
		try
		{
			// Command set letrehozas
			//-----------------------
			TransferSyntax commTransferSyntax = TransferSyntax.getCommandTransferSyntax();
			DIOutputStreamWriter out = new DIOutputStreamWriter(commTransferSyntax.getEndianType());
			DIOutputStreamWriter out1 = new DIOutputStreamWriter(commTransferSyntax.getEndianType());
			DICoder coder = new DICoder(commTransferSyntax);

			// out1 feltoltese - a command group length-et koveto mezokkel
			coder.writeDataElement(new DataElement(0x0000, 0x0002, "UI", sopClassUID), out1);	// affected SOP class UID
			coder.writeDataElement(new DataElement(0x0000, 0x0100, "US", CommandNum), out1);	// command field
			coder.writeDataElement(new DataElement(0x0000, 0x0110, "US", 1), out1);				// message ID - Ez egyelore mindig 1, mert nem hasznalom fel sehol!
			coder.writeDataElement(new DataElement(0x0000, 0x0700, "US", 0x0000), out1);		// priority - Medium
			coder.writeDataElement(new DataElement(0x0000, 0x0800, "US", 0x0000), out1);		// data set type - van data set

			// out feltoltese
			coder.writeDataElement(new DataElement(0x0000, 0x0000, "UL", (long)out1.getData().length), out);		// command group length
			out.writeByteArray(out1.getData());

			commandData = out.getData();

			// Data set letrehozas
			//--------------------
			DIOutputStreamWriter dout = new DIOutputStreamWriter(dataTransferSyntax.getEndianType());
			coder = new DICoder(dataTransferSyntax);
			for(int i = 0; i < query.getDataElements().size(); i++)
			{
				coder.writeDataElement(query.getDataElements().get(i), dout);
			}

			dataSetData = dout.getData();
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_4003");
		}
	}

	public CFindRq(DicomItem commandSet) throws DIException
	{
		sopClassUID = commandSet.getString(0x0000, 0x0002);
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

	/**
	 * @return the sopClassUID
	 */ public String getSopClassUID() {
		return sopClassUID;
	}

	/**
	 * @return the dataSetData
	 */ public byte[] getDataSetData() {
		return dataSetData;
	}
}
