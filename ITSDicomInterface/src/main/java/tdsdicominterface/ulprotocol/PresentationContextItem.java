package tdsdicominterface.ulprotocol;

import java.io.IOException;
import java.util.ArrayList;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;
import tdsdicominterface.exception.DIInternalErrorException;

public class PresentationContextItem
{
	public static final int ItemTypeInRequest = 0x20;
	public static final int ItemTypeInResponse = 0x21;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok beolvasashoz az A-ASSOCIATE-RQ message-bol
	private int rqPresentationContextID;
	private AbstractSyntaxItem rqAbstractSyntaxItem;
	private ArrayList<TransferSyntaxItem> rqTransferSyntaxItems;

	// valtozok beolvasashoz az A-ASSOCIATE-AC message-bol
	private int acPresentationContextID;
	private int acResult;		// 0: acceptance, 1 - 4: rejection
	private String acAcceptedTransferSyntax;

	/**
	 * Presentation Context Item letrehozasa kiirashoz a Requester altal az A-ASSOCIATE-RQ message-be.
	 * @param presentationContext
	 */
	public PresentationContextItem(AssociationSettings.PresentationContext presentationContext) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		AbstractSyntaxItem abstractSyntaxItem = new AbstractSyntaxItem(presentationContext.getAbstractSyntax());

		int itemLength = 4 + abstractSyntaxItem.getData().length;

		ArrayList<TransferSyntaxItem> transferSyntaxItems = new ArrayList<TransferSyntaxItem>();
		for(int i = 0; i < presentationContext.getTransferSyntaxes().size(); i++)
		{
			TransferSyntaxItem item = new TransferSyntaxItem(presentationContext.getTransferSyntaxes().get(i));
			transferSyntaxItems.add(item);
			itemLength += item.getData().length;
		}

		out.writeByte(ItemTypeInRequest);		// item type
		out.writeByte(0);			// reserved
		out.writeInt(itemLength);	// item length
		out.writeByte(presentationContext.getId());	// presentation Context ID
		out.writeByte(0);			// reserved
		out.writeByte(0);			// reserved
		out.writeByte(0);			// reserved

		// Abstract syntax
		out.writeByteArray(abstractSyntaxItem.getData());

		// Transfer syntaxes
		for(int i = 0; i < transferSyntaxItems.size(); i++)
		{
			out.writeByteArray(transferSyntaxItems.get(i).getData());
		}

		data = out.getData();
	}

	/**
	 * Presentation Context Item letrehozasa kiirashoz a Responder altal az A-ASSOCIATE-AC message-be.
	 * @param presentationContextID
	 * @param result
	 * @param transferSyntaxName
	 */
	public PresentationContextItem(int presentationContextID, int result, String transferSyntaxName) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		int itemLength = 4;
		TransferSyntaxItem transferSyntaxItem = new TransferSyntaxItem(transferSyntaxName);
		itemLength += transferSyntaxItem.getData().length;

		out.writeByte(ItemTypeInResponse);		// item type
		out.writeByte(0);			// reserved
		out.writeInt(itemLength);	// item length
		out.writeByte(presentationContextID);	// presentation Context ID
		out.writeByte(0);			// reserved
		out.writeByte(result);		// result
		out.writeByte(0);			// reserved

		// Egy TransferSyntaxItem
		out.writeByteArray(transferSyntaxItem.getData());

		data = out.getData();
	}

	/**
	 * Presentation context item beolvasasa: a Responder altal az A-ASSOCIATE-RQ message-bol,
	 * vagy a Requester altal az A-ASSOCIATE-AC message-bol. Ennek megfeleloen az 'rq' vagy az 'ac'
	 * prefixu valtozok toltodnek fel.
	 * @param in
	 */
	public PresentationContextItem(DIInputStreamReader in, int itemType) throws IOException, DIAssociationContentErrorException
	{
													// item type mar beolvasva a hivo altal
		in.readByte();								// reserved
		int itemLength = in.readIntU();				// item length
		int ptr = 0;

		if(itemType == ItemTypeInRequest)
		{
			rqPresentationContextID = in.readByte();	// presentationContextID
			in.readByte();								// reserved
			in.readByte();								// reserved
			in.readByte();								// reserved
			ptr += 4;
			rqAbstractSyntaxItem = new AbstractSyntaxItem(in);
			ptr += rqAbstractSyntaxItem.getInLength();
			rqTransferSyntaxItems = new ArrayList<TransferSyntaxItem>();
			while(ptr < itemLength)
			{
				TransferSyntaxItem item = new TransferSyntaxItem(in);
				rqTransferSyntaxItems.add(item);
				ptr += item.getInLength();
			}
		}
		else if(itemType == ItemTypeInResponse)
		{
			acPresentationContextID = in.readByte();	// presentationContextID
			in.readByte();								// reserved
			acResult = in.readByte();					// result
			in.readByte();								// reserved
			TransferSyntaxItem item = new TransferSyntaxItem(in);	// transfer syntax item

			acAcceptedTransferSyntax = item.getTransferSyntax();
		}
		else throw new DIInternalErrorException(
				String.format("Wrong itemType as parameter in PresentationContextItem's constructor (0x%X)", itemType));
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the rqPresentationContextID
	 */ public int getRqPresentationContextID() {
		return rqPresentationContextID;
	}

	/**
	 * @return the rqAbstractSyntaxItem
	 */ public AbstractSyntaxItem getRqAbstractSyntaxItem() {
		return rqAbstractSyntaxItem;
	}

	/**
	 * @return the rqTransferSyntaxItems
	 */ public ArrayList<TransferSyntaxItem> getRqTransferSyntaxItems() {
		return rqTransferSyntaxItems;
	}

	/**
	 * @return the acPresentationContextID
	 */ public int getAcPresentationContextID() {
		return acPresentationContextID;
	}

	/**
	 * @return the acResult
	 */ public int getAcResult() {
		return acResult;
	}

	/**
	 * @return the acAcceptedTransferSyntax
	 */ public String getAcAcceptedTransferSyntax() {
		return acAcceptedTransferSyntax;
	}
}
