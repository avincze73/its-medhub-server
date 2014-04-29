package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;

public class ImplementationVersionNameItem
{
	public static final String ImplementationVersionName = "1.0";
	public static final int ItemType = 0x55;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private String implementationVersionName;
	private int inLength;

	/**
	 * Implementation Version Name Item kiirasa. Requester es responder eseten is a sajat Implementation Version Name-et kell megadni.
	 */
	public ImplementationVersionNameItem() throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeInt(ImplementationVersionName.length());	// item length
		out.writeString(ImplementationVersionName, false);	// ImplementationVersionName

		data = out.getData();
	}

	/**
	 * Implementation Version Name item beolvasasa. (Requestor es responder)
	 * @param in
	 * @throws IOException
	 */
	public ImplementationVersionNameItem(DIInputStreamReader in) throws IOException
	{
													// item type mar beolvasva a hivo altal
		in.readByte();								// reserved
		int itemLength = in.readIntU();				// item length
		implementationVersionName = in.readString(itemLength);		// implementationClassUid
		inLength = 4 + itemLength;
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
	 * @return the implementationVersionName
	 */ public String getImplementationVersionName() {
		return implementationVersionName;
	}
}
