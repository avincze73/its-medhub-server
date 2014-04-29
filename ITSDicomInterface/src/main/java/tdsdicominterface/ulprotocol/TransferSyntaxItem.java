package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class TransferSyntaxItem
{
	public static final int ItemType = 0x40;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private String transferSyntax;
	private int inLength;

	/**
	 * Transfer Syntax Item letrehozasa kiirashoz. (A-ASSOCIATE-RQ es A-ASSOCIATE-AC message-be egyarant.)
	 * @param transferSyntaxName
	 */
	public TransferSyntaxItem(String transferSyntaxName) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		out.writeByte(0x40);						// item type
		out.writeByte(0);							// reserved
		out.writeInt(transferSyntaxName.length());	// item length
		out.writeString(transferSyntaxName, false);	// transfer syntax name

		data = out.getData();
	}

	/**
	 * Transfer Syntax Item beolvasasa. (A-ASSOCIATE-RQ es A-ASSOCIATE-AC message-bol egyarant.)
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public TransferSyntaxItem(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
		int itemType = in.readByte();					// item type
		if(itemType != ItemType)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in TransferSyntaxItem (0x%X)", itemType);
		in.readByte();									// reserved
		int itemLength = in.readIntU();					// item length
		transferSyntax = in.readString(itemLength);		// transferSyntax name
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
	 * @return the transferSyntax
	 */ public String getTransferSyntax() {
		return transferSyntax;
	}
}
