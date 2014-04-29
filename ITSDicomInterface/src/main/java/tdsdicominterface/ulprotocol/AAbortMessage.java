package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class AAbortMessage
{
	public static final int ItemType = 0x07;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasahoz
	private int source;
	private int reason;

	/**
	 * A-ABORT message letrehozasa kiirashoz. (Requester vagy responder)
	 * @param source
	 * @param reason
	 */
	public AAbortMessage(int source, int reason) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(4);			// item length
		out.writeByte(0);			// reserved
		out.writeByte(0);			// reserved
		out.writeByte(source);		// source
		out.writeByte(reason);		// reason

		data = out.getData();
	}

	/**
	 * A-ABORT message beolvasasa. (Requester vagy responder)
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public AAbortMessage(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
									// item type mar beolvasva a hivo altal
		in.readByte();				// reserved
		in.readLongU();				// item length
		in.readByte();				// reserved
		in.readByte();				// reserved
		source = in.readByte();		// source
		if(source != 0 && source != 2)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "In AAbortMessage source is illegal. (%d)", source);
		reason = in.readByte();		// reason
		if(source == 2 && (reason < 0 || reason > 6 || reason == 3))
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1,
					"In AAbortMessage reason is illegal. (source = %d, reason = %d)", source, reason);
	}

	//------------------------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the source
	 */ public int getSource() {
		return source;
	}

	/**
	 * @return the reason
	 */ public int getReason() {
		return reason;
	}
}
