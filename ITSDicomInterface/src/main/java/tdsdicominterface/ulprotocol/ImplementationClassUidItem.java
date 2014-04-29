package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;

public class ImplementationClassUidItem
{
	// TODO: Szerezni valahonnan egy ilyen UID-t!
	public static final String ImplementationClassUid = "xxxxxxxxxxxxxx";
	public static final int ItemType = 0x52;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private String implementationClassUid;
	private int inLength;

	/**
	 * Implementation Class UID Item kiirasa. Requester es responder eseten is a sajat Implementation Class UID-t kell megadni.
	 */
	public ImplementationClassUidItem() throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);		// item type
		out.writeByte(0);			// reserved
		out.writeInt(ImplementationClassUid.length());	// item length
		out.writeString(ImplementationClassUid, false);	// ImplementationClassUid

		data = out.getData();
	}

	/**
	 * Implementation Class UID item beolvasasa. (Requestor es responder)
	 * @param in
	 * @throws IOException
	 */
	public ImplementationClassUidItem(DIInputStreamReader in) throws IOException
	{
													// item type mar beolvasva a hivo altal
		in.readByte();								// reserved
		int itemLength = in.readIntU();				// item length
		implementationClassUid = in.readString(itemLength);		// implementationClassUid
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
	 * @return the implementationClassUid
	 */ public String getImplementationClassUid() {
		return implementationClassUid;
	}
}
