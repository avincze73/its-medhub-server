package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;

public class AReleaseRpMessage
{
	public static final int ItemType = 0x06;

	// valtozok a kiirashoz
	private byte[] data;

	/**
	 * A-RELEASE-RP message letrehozasa kiirashoz a Responder altal.
	 */
	public AReleaseRpMessage() throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(4);			// item length
		out.writeLong(0);			// reserved (4 byte)

		data = out.getData();
	}

	/**
	 * A-RELEASE-RP message beolvasasa a Requester altal.
	 * @param in
	 * @throws IOException
	 */
	public AReleaseRpMessage(DIInputStreamReader in) throws IOException
	{
									// item type mar beolvasva a hivo altal
		in.readByte();				// reserved
		in.readLongU();				// item length
		in.readLongU();				// reserved (4 byte)
	}

	//-------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}
}
