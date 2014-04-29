package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class AbstractSyntaxItem
{
	public static final int ItemType = 0x30;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private String abstactSyntax;
	private int inLength;

	/**
	 * Abstract Syntax Item kiirasa - Csak a requestor irja ki az A-ASSOCIATE-RQ message-ben.
	 * @param abstractSyntaxName
	 */
	public AbstractSyntaxItem(String abstractSyntaxName) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeInt(abstractSyntaxName.length());		// item length
		out.writeString(abstractSyntaxName, false);		// abstract syntax name
		data = out.getData();
	}

	/**
	 * Abstract Syntax Item beolvasasa - Csak a responder olvassa be az A-ASSOCIATE-RQ message-bol.
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public AbstractSyntaxItem(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
		int itemType = in.readByte();						// item type
		if(itemType != ItemType)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in AbstractSyntaxItem (0x%X)", itemType);
		in.readByte();										// reserved
		int itemLength = in.readIntU();						// item length
		abstactSyntax = in.readString(itemLength);			// abstactSyntax
		inLength = 4 + itemLength;
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the abstactSyntax
	 */ public String getAbstactSyntax() {
		return abstactSyntax;
	}

	/**
	 * @return the length
	 */ public int getInLength() {
		return inLength;
	}
}
