package tdsdicominterface.ulprotocol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.cmoveserver.ServerULManager;
import tdsdicominterface.exception.DIInternalErrorException;

public class PDataTfMessage
{
	public static final int ItemType = 0x04;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private ArrayList<PDVItem> pdvItems;

	// P-DATA-TF message letrehozasa kiirashoz. (Requester vagy responder)
	public PDataTfMessage(byte[] commandData, int commandFragmentOffs, int commandFragmentLen,
							byte[] dataSetData, int dataSetFragmentOffs, int dataSetFragmentLen,
							int presentationContexID) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		int pduLength = 0;
		PDVItem pdvItem1 = null;
		PDVItem pdvItem2 = null;
		boolean isLastCommandFragment = false;
		if(commandData != null && commandFragmentOffs + commandFragmentLen == commandData.length) isLastCommandFragment = true;
		boolean isLastDataSetFragment = false;
		if(dataSetData != null && dataSetFragmentOffs + dataSetFragmentLen == dataSetData.length) isLastDataSetFragment = true;

		if(commandData != null && dataSetData == null)		// ha csak command data van
		{
			pdvItem1 = new PDVItem(commandData, commandFragmentOffs, commandFragmentLen,
					presentationContexID, PDVItem.Command, isLastCommandFragment);
			pduLength += pdvItem1.getData().length;
		}
		else if(commandData == null && dataSetData != null)		// ha csak data set data van
		{
			pdvItem1 = new PDVItem(dataSetData, dataSetFragmentOffs, dataSetFragmentLen,
					presentationContexID, PDVItem.DataSet, isLastDataSetFragment);
			pduLength += pdvItem1.getData().length;
		}
		else if(commandData != null && dataSetData != null)		// ha command data es data set data is van
		{
			pdvItem1 = new PDVItem(commandData, commandFragmentOffs, commandFragmentLen,
					presentationContexID, PDVItem.Command, isLastCommandFragment);
			pduLength += pdvItem1.getData().length;

			pdvItem2 = new PDVItem(dataSetData, dataSetFragmentOffs, dataSetFragmentLen,
					presentationContexID, PDVItem.DataSet, isLastDataSetFragment);
			pduLength += pdvItem2.getData().length;
		}
		else throw new DIInternalErrorException("PDataTfMessage: both commandDataFragment and dataSetDataFragment are null.");

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(pduLength);	// item length

		// PDV item list
		out.writeByteArray(pdvItem1.getData());
		if(pdvItem2 != null) out.writeByteArray(pdvItem2.getData());

		data = out.getData();
	}
	
	public PDataTfMessage(DIInputStreamReader in, BufferedWriter diagOut) throws IOException
	{
												// item type mar beolvasva a hivo altal
		in.readByte();							// reserved
		long itemLength = in.readLongU();		// item length
		printDiag(diagOut, "    PDataTfMessage item: itemLength = " + itemLength);

		// PDV-k beolvasasa
		pdvItems = new ArrayList<PDVItem>();
		long pos = 0;
		while(pos < itemLength)
		{
			PDVItem pdv = new PDVItem(in);
			if(diagOut != null)
					printDiag(diagOut, "    PDV item: pcID = " + pdv.getPresentationContextID() + ", mch = " + pdv.getMch() +
							", inLength = " + pdv.getInLength());
			pdvItems.add(pdv);
			pos += pdv.getInLength();
		}
		printDiag(diagOut, "All PDV items had been read.");
	}

	public static void printDiag(BufferedWriter diagOut, String text)
	{
		if(diagOut != null)
		{
			try
			{
				Calendar cal = Calendar.getInstance();
				String s = String.format("%02d:%02d:%02d.%03d  PDataTfMessage: %s", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
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

	//-----------------------------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the pdvItems
	 */ public ArrayList<PDVItem> getPdvItems() {
		return pdvItems;
	}
}
