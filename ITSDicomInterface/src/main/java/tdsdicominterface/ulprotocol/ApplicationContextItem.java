package tdsdicominterface.ulprotocol;

import java.io.IOException;
import tdsdicominterface.DIInputStreamReader;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIAssociationContentErrorException;

public class ApplicationContextItem
{
	private static final String ApplicationContext = "1.2.840.10008.3.1.1.1";	// A jelenlegi DICOM standard-ben ez az egyeduli application context.
																				// Ha a requester egy sajat application context-et kuld,
																				// akkor is ezzel kell valaszolni.
	private static final int ItemType = 0x10;

	// valtozok a kiirashoz
	private byte[] data;

	// valtozok a beolvasashoz
	private String applicationContext;

	/**
	 * Application Context Item kiirasa. Requester es responder eseten is ugyanugy nez ki.
	 */
	public ApplicationContextItem() throws IOException
	{
		DIOutputStreamWriter out = new DIOutputStreamWriter(TransferSyntax.BigEndianType);

		out.writeByte(ItemType);	// item type
		out.writeByte(0);			// reserved
		out.writeInt(ApplicationContext.length());	// item length
		out.writeString(ApplicationContext, false);	// application context name
		data = out.getData();
	}

	/**
	 * Application context item beolvasasa. (Requestor es responder)
	 * @param in
	 */
	public ApplicationContextItem(DIInputStreamReader in) throws IOException, DIAssociationContentErrorException
	{
		int itemType = in.readByte();						// item type
		if(itemType != ItemType)
			DIAssociationContentErrorException.createAndThrow(in.getPosition() - 1, "Wrong item type in ApplicationContextItem (0x%X)", itemType);
		in.readByte();										// reserved
		int itemLength = in.readIntU();						// item length
		applicationContext = in.readString(itemLength);		// application context name
	}

	//-------------------------------------------------------------------------

	/**
	 * @return the data
	 */ public byte[] getData() {
		return data;
	}

	/**
	 * @return the applicationContext
	 */ public String getApplicationContext() {
		return applicationContext;
	}
}
