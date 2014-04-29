package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;

public class MaximumLengthItem
{
	public static final int ItemType = 0x51;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private long pdvListMaxSizeForSending;			// kuldesnel maximum ekkorak lehetnek a PDV list-ek a P-DATA-TF message-ben
	private int inLength;

	/**
	 * Maximum Length Item kiirasa. Requester es responder eseten is az adat fogadashoz definialt erteket kell megadni.
	 * @param pdvListMaxSize
	 */
	public MaximumLengthItem(int pdvListMaxSizeForReceiving) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeInt(4);			// item length
		out.writeLong(pdvListMaxSizeForReceiving);	// PDV List MaxSize

		data = out.getData();
	}

	/**
	 * Maximum Length Item beolvasasa. (Requestor es responder)
	 * @param in
	 * @throws IOException
	 */
	public MaximumLengthItem(DIInputStreamReader in) throws IOException
	{
													// item type mar beolvasva a hivo altal
		in.readByte();								// reserved
		in.readIntU();								// item length (itt 4 az erteke)
		pdvListMaxSizeForSending = in.readLongU();	// pdvListMaxSizeForSending
		inLength = 8;
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the length
	 */ public int getInLength() {
		return inLength;
	}

	/**
	 * @return the pdvListMaxSizeForSending
	 */ public long getPdvListMaxSizeForSending() {
		return pdvListMaxSizeForSending;
	}
}
