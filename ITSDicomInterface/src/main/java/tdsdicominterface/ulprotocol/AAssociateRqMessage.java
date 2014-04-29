package tdsdicominterface.ulprotocol;

import java.io.IOException;
import java.util.ArrayList;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class AAssociateRqMessage
{
	public static final int ItemType = 0x01;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private int protocolVersion;
	private String calledAETitle;
	private String callingAETitle;
	private byte[] reserved32;

	// kozos valtozok
	private ApplicationContextItem applicationContextItem;
	private ArrayList<PresentationContextItem> presentationContextItems;
	private UserInformationItem userInformationItem;

	/**
	 * A-ASSOCIATE-RQ message letrehozasa requester altal
	 * @param aSettings
	 */
	public AAssociateRqMessage(AssociationSettings aSettings) throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(AssociationSettings.EndianType);

		int pduLength = 68;		// ennyi byte van a pduLength utan a variable items elott

		// ApplicationContextItem
		applicationContextItem = new ApplicationContextItem();
		pduLength += applicationContextItem.getData().length;

		// PresentationContextItem-ek
		presentationContextItems = new ArrayList<PresentationContextItem>();
		for(int i = 0; i < aSettings.getPresentationContextCount(); i++)
		{
			PresentationContextItem item = new PresentationContextItem(aSettings.getPresentationContext(i));
			presentationContextItems.add(item);
			pduLength += item.getData().length;
		}

		// UserInformationItem
		userInformationItem = new UserInformationItem(aSettings.getPdvListMaxSizeForReceiving());
		pduLength += userInformationItem.getData().length;

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeLong(pduLength);	// item length
		out.writeInt(AssociationSettings.ProtocolVersion);	// protocol version
		out.writeInt(0);			// reserved (2 byte)
		out.writeString(aSettings.getCalledAETitle(), 16);		// called AE title
		out.writeString(aSettings.getCallingAETitle(), 16);	// calling AE title
		byte[] space = new byte[32];
		for(int i = 0; i < 32; i++) space[i] = 0;
		out.writeByteArray(space);	// reserved (32 byte)

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
	 * A-ASSOCIATE-RQ message beolvasasa a responder altal
	 * @param in
	 * @throws IOException
	 * @throws DIAssociationContentErrorException
	 */
	public AAssociateRqMessage(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
												// az item type mar be van olvasva a hivo altal
		in.readByte();							// reserved
		long pduLength = in.readLongU();		// PDU length
		protocolVersion = in.readIntU();		// protocol version
		if(protocolVersion != AssociationSettings.ProtocolVersion)		// ismeretlen protocol eseten beolvassuk a maradek reszt es visszaterunk
		{
			in.readByteArray(pduLength - 2);
			return;
		}
		in.readIntU();							// reserved (2 byte)
		calledAETitle = in.readString(16);		// called AE title
		callingAETitle = in.readString(16);		// calling AE title
		reserved32 = in.readByteArray(32);		// reserved (32 byte)

		// application context item beolvasasa
		applicationContextItem = new ApplicationContextItem(in);

		// Presentation context item-ek beolvasasa
		presentationContextItems = new ArrayList<PresentationContextItem>();
		int b = in.readByte();
		while(b == PresentationContextItem.ItemTypeInRequest)
		{
			presentationContextItems.add(new PresentationContextItem(in, PresentationContextItem.ItemTypeInRequest));
			b = in.readByte();
		}

		// User information item beolvasasa
		if(b != UserInformationItem.ItemType)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in UserInformationItem (0x%X)", b);
		userInformationItem = new UserInformationItem(in);
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the protocolVersion
	 */ public int getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * @return the calledAETitle
	 */ public String getCalledAETitle() {
		return calledAETitle;
	}

	/**
	 * @return the callingAETitle
	 */ public String getCallingAETitle() {
		return callingAETitle;
	}

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
	 * @return the reserved32
	 */ public byte[] getReserved32() {
		return reserved32;
	}

}
