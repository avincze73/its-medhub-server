package tdsdicominterface.ulprotocol;

import java.io.IOException;
import java.util.ArrayList;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;
import tdsdicominterface.ulprotocol.AssociationSettings.PresentationContext;

public class AAssociateAcMessage
{
	public static final int ItemType = 0x02;

	// valtozok a kiirashoz
	private byte[] data;

	// kozos valtozok
	private ApplicationContextItem applicationContextItem;
	private ArrayList<PresentationContextItem> presentationContextItems;
	private UserInformationItem userInformationItem;

	/**
	 * A-ASSOCIATE-AC message letrehozasa kiirashoz a responder altal.
	 * @param aSettings
	 * @param rqMessage
	 */
	public AAssociateAcMessage(AssociationSettings aSettings, AAssociateRqMessage rqMessage) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		int pduLength = 68;		// ennyi byte van a variable items elott

		// ApplicationContextItem letrehozas
		applicationContextItem = new ApplicationContextItem();
		pduLength += applicationContextItem.getData().length;

		// PresentationContextItem-ek letrehozasa
		presentationContextItems = new ArrayList<PresentationContextItem>();
		for(int i = 0; i < aSettings.getPresentationContextCount(); i++)
		{
			PresentationContext pc = aSettings.getPresentationContext(i);
			PresentationContextItem item = new PresentationContextItem(pc.getId(), pc.getRejectionType(), pc.getAccTransferSyntax());
			presentationContextItems.add(item);
			pduLength += item.getData().length;
		}

		// UserInformationItem letrehozas
		userInformationItem = new UserInformationItem(aSettings.getPdvListMaxSizeForReceiving());
		pduLength += userInformationItem.getData().length;

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(pduLength);	// item length
		out.writeInt(AssociationSettings.ProtocolVersion);		// protocol version
		out.writeInt(0);			// reserved (2 byte)
		out.writeString(rqMessage.getCalledAETitle(), 16);		// called AE title
		out.writeString(rqMessage.getCallingAETitle(), 16);	// calling AE title
		out.writeByteArray(rqMessage.getReserved32());	// reserved (32 byte)

		// ApplicationContextItem
		out.writeByteArray(applicationContextItem.getData());

		// PresentationContextItem-ek
		for(int i = 0; i < presentationContextItems.size(); i++)
			out.writeByteArray(presentationContextItems.get(i).getData());

		// UserInformationItem
		out.writeByteArray(userInformationItem.getData());

		data = out.getData();
	}

	/**
	 * A-ASSOCIATE-AC message beolvasasa a requester altal
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public AAssociateAcMessage(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
							// item type mar beolvasva a hivo altal
		in.readByte();		// reserved
		in.readLongU();		// PDU length
		in.readIntU();		// protocol version
		in.readIntU();		// reserved (2 byte)
		in.readString(16);	// called AE title
		in.readString(16);	// calling AE title
		in.readByteArray(32);	// reserved (32 byte)

		// application context item beolvasasa
		applicationContextItem = new ApplicationContextItem(in);
		
		// Presentation context item-ek beolvasasa
		presentationContextItems = new ArrayList<PresentationContextItem>();
		int b = in.readByte();
		while(b == PresentationContextItem.ItemTypeInResponse)
		{
			presentationContextItems.add(new PresentationContextItem(in, PresentationContextItem.ItemTypeInResponse));
			b = in.readByte();
		}

		// User information item beolvasasa
		if(b != UserInformationItem.ItemType)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in UserInformationItem (0x%X)", b);
		userInformationItem = new UserInformationItem(in);
	}

	//---------------------------------------------------------------------------------------------

	/**
	 * @return the applicationContextItem
	 */ public ApplicationContextItem getApplicationContextItem() {
		return applicationContextItem;
	}

	/**
	 * @return the presentationContextItems
	 */ public ArrayList<PresentationContextItem> getPresentationContextItems() {
		return presentationContextItems;
	}

	/**
	 * @return the userInformationItem
	 */ public UserInformationItem getUserInformationItem() {
		return userInformationItem;
	}

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}
}
