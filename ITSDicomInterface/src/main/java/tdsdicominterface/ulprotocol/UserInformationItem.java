package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class UserInformationItem
{
	public static final int ItemType = 0x50;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz

	// kozos valtozok
	private MaximumLengthItem maximumLengthItem;
	private ImplementationClassUidItem implementationClassUidItem;
	private ImplementationVersionNameItem implementationVersionNameItem;

	/**
	 * User Information Item letrehozasa kiirashoz. (A-ASSOCIATE-RQ es A-ASSOCIATE-AC message-be egyarant.)
	 * @param settings
	 */
	public UserInformationItem(int pdvListMaxSizeForReceiving) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		maximumLengthItem = new MaximumLengthItem(pdvListMaxSizeForReceiving);
		int itemLength = maximumLengthItem.getData().length;
		implementationClassUidItem = new ImplementationClassUidItem();
		itemLength += implementationClassUidItem.getData().length;
		implementationVersionNameItem = new ImplementationVersionNameItem();
		itemLength += implementationVersionNameItem.getData().length;

		out.writeByte(ItemType);		// item type
		out.writeByte(0);				// reserved
		out.writeInt(itemLength);		// item length
		out.writeByteArray(maximumLengthItem.getData());				// maximumLengthItem
		out.writeByteArray(implementationClassUidItem.getData());		// implementationClassUidItem
		out.writeByteArray(implementationVersionNameItem.getData());	// implementationVersionNameItem

		data = out.getData();
	}

	/**
	 * User Information Item beolvasasa. (A-ASSOCIATE-RQ es A-ASSOCIATE-AC message-bol egyarant.)
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public UserInformationItem(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
													// item type mar beolvasva a hivo altal
		in.readByte();								// reserved
		int itemLength = in.readIntU();				// item length
		int ptr = 0;
		while(ptr < itemLength)
		{
			int b = in.readByte();		// item type
			if(b == MaximumLengthItem.ItemType)
			{
				maximumLengthItem = new MaximumLengthItem(in);
				ptr += maximumLengthItem.getInLength();
			}
			else if(b == ImplementationClassUidItem.ItemType)
			{
				implementationClassUidItem = new ImplementationClassUidItem(in);
				ptr += implementationClassUidItem.getInLength();
			}
			else if(b == ImplementationVersionNameItem.ItemType)
			{
				implementationVersionNameItem = new ImplementationVersionNameItem(in);
				ptr += implementationVersionNameItem.getInLength();
			}
			else if(b >= 0x51 && b <= 0xff)		// egyeb User data sub-item-ek
			{
				in.readByte();								// reserved
				int subItemLength = in.readIntU();			// item length
				in.readByteArray(subItemLength);			// a sub-item tovabbi tartalma
				ptr += (4 + subItemLength);
			}
			else		// helytelen item type
			{
				DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in User data sub-item (0x%X)", b);
			}
		}
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the maximumLengthItem
	 */ public MaximumLengthItem getMaximumLengthItem() {
		return maximumLengthItem;
	}

	/**
	 * @return the implementationClassUidItem
	 */ public ImplementationClassUidItem getImplementationClassUidItem() {
		return implementationClassUidItem;
	}

	/**
	 * @return the implementationVersionNameItem
	 */ public ImplementationVersionNameItem getImplementationVersionNameItem() {
		return implementationVersionNameItem;
	}
}
