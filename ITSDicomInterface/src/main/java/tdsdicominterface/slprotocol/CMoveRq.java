package tdsdicominterface.slprotocol;

import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomItem;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class CMoveRq
{
	public static final int CommandNum = 0x0021;

	public static int prevMessageID;

	// valtozok a kiirashoz
	private byte[] commandData;
	private byte[] dataSetData;
	private int outMessageID;

	// valtozok a beolvasashoz
	private String sopClassUID;
	private int messageID;

	public CMoveRq(String sopClassUID, DicomItem query, TransferSyntax dataTransferSyntax, String destAETitle) throws DIException
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
			if(CMoveRq.prevMessageID == 0) CMoveRq.prevMessageID = -1;
			CMoveRq.prevMessageID += 2;
			if(CMoveRq.prevMessageID > Short.MAX_VALUE) CMoveRq.prevMessageID = 1;
			outMessageID = CMoveRq.prevMessageID;
			coder.writeDataElement(new DataElement(0x0000, 0x0110, "US", outMessageID), out1);	// message ID
			coder.writeDataElement(new DataElement(0x0000, 0x0600, "AE", destAETitle), out1);	// Move destination
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

	public CMoveRq(DicomItem commandSet) throws DIException
	{
		sopClassUID = commandSet.getString(0x0000, 0x0002);
		messageID = commandSet.getFirstInt(0x0000, 0x0110);
	}

	//--------------------------------------------------------------------------------------------------------

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

	/**
	 * @return the sopClassUID
	 */ public String getSopClassUID() {
		return sopClassUID;
	}

	/**
	 * @return the messageID
	 */ public int getMessageID() {
		return messageID;
	}

	/**
	 * @return the outMessageID
	 */ public int getOutMessageID() {
		return outMessageID;
	}
}
