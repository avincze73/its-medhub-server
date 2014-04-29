package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class AAssociateRjMessage
{
	public static final int ItemType = 0x03;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasahoz
	private int result;
	private int source;
	private int reason;

	/**
	 * A-ASSOCIATE-RJ message letrehozasa kiirashoz a responder altal.
	 * @param result
	 * @param source
	 * @param reason
	 */
	public AAssociateRjMessage(int result, int source, int reason) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(4);			// item length
		out.writeByte(0);			// reserved
		out.writeByte(result);		// result
		out.writeByte(source);		// source
		out.writeByte(reason);		// reason

		data = out.getData();
	}

	/**
	 * A-ASSOCIATE-RJ message beolvasasa a requester altal.
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public AAssociateRjMessage(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
									// item type mar beolvasva a hivo altal
		in.readByte();				// reserved
		in.readLongU();				// item length
		in.readByte();				// reserved
		result = in.readByte();		// result
		if(result < 1 || result > 2)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "In AAssociateRjMessage result is illegal. (%d)", result);
		source = in.readByte();		// source
		if(source < 1 || source > 3)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "In AAssociateRjMessage source is illegal. (%d)", source);
		reason = in.readByte();		// reason
		if((source == 1 && (reason < 1 || reason > 3 && reason != 7))
				|| (source == 2 && (reason < 1 || reason > 2))
				|| (source == 3 && (reason < 1 || reason > 2)))
		{
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1,
					"In AAssociateRjMessage reason is illegal. (source = %d, reason = %d)", source, reason);
		}
	}

	//------------------------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the result
	 */ public int getResult() {
		return result;
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
