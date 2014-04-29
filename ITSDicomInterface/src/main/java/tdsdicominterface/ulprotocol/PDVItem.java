package tdsdicominterface.ulprotocol;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;

public class PDVItem
{
	public static final int Command = 1;
	public static final int DataSet = 2;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private int presentationContextID;
	private int informationType;
	private int mch;
	private boolean lastFragment;
	private byte[] fragmentData;
	private int inLength;

	/**
	 * Presentation Data Value Item letrehozasa kiirashoz.
	 * @param fragment a command vagy data set adott fragment-je. Paros szamu byte-ot kell tartalmazzon!
	 * @param presentationContextID
	 * @param informationType command vagy data set-rol van-e szo. PresentationDataValueItem konstanssal kell megadni.
	 * @param isLastFragment ez az utolso fragment-e.
	 */
	public PDVItem(byte[] totalData, int fragmentOffs, int fragmentLen,
			int presentationContextID, int informationType, boolean isLastFragment) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		int itemLength = 2 + fragmentLen;

		out.writeLong(itemLength);					// item length
		out.writeByte(presentationContextID);		// presentationContextID

		// Message Control Header
		mch = 0;
		if(informationType == Command) mch = mch | 0x01;
		if(isLastFragment) mch = mch | 0x02;
		out.writeByte(mch);

		// Presentation data value
		out.writeByteArray(totalData, fragmentOffs, fragmentLen);

		data = out.getData();
	}

	/**
	 * Presentation Data Value Item beolvasasa.
	 * @param in
	 * @throws IOException
	 */
	public PDVItem(DIInputStreamReader in) throws IOException
	{
		long itemLength = in.readLongU();			// item length
		presentationContextID = in.readByte();		// presentationContextID

		// Message Control Header
		mch = in.readByte();
		if((mch & 0x01) > 0) informationType = Command;
		else informationType = DataSet;
		if((mch & 0x02) > 0) lastFragment = true;
		else lastFragment = false;

		// Presentation data value
		fragmentData = in.readByteArray(itemLength - 2);

		inLength = (int) (4 + itemLength);
	}

	//--------------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the presentationContextID
	 */ public int getPresentationContextID() {
		return presentationContextID;
	}

	/**
	 * @return the informationType
	 */ public int getInformationType() {
		return informationType;
	}

	/**
	 * @return the lastFragment
	 */ public boolean isLastFragment() {
		return lastFragment;
	}

	/**
	 * @return the fragmentData
	 */ public byte[] getFragmentData() {
		return fragmentData;
	}

	/**
	 * @return the inLength
	 */ public int getInLength() {
		return inLength;
	}

	/**
	 * @return the mch
	 */ public int getMch() {
		return mch;
	}
}
