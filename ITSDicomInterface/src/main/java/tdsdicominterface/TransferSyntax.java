package tdsdicominterface;

public class TransferSyntax
{
	public static final int EncodingImplicit = 1;
	public static final int EncodingExplicit = 2;
	public static final int LittleEndianType = 1;
	public static final int BigEndianType = 2;

	public static final String ImplicitVRLittleEndian = "1.2.840.10008.1.2";
	public static final String ExplicitVRLittleEndian = "1.2.840.10008.1.2.1";
	public static final String ExplicitVRBigEndian = "1.2.840.10008.1.2.2";
	public static final String JPEG_Lossless = "1.2.840.10008.1.2.4.57";
	public static final String JPEG_LosslessFirstOrder = "1.2.840.10008.1.2.4.70";
	public static final String JPEG_LS_Lossless = "1.2.840.10008.1.2.4.80";
	public static final String JPEG_LS_Lossy = "1.2.840.10008.1.2.4.81";
	public static final String JPEG2000_LosslessOnly = "1.2.840.10008.1.2.4.90";
	public static final String JPEG2000 = "1.2.840.10008.1.2.4.91";
	public static final String JPEG2000_Multicomponent_LosslessOnly = "1.2.840.10008.1.2.4.92";
	public static final String JPEG2000_Multicomponent = "1.2.840.10008.1.2.4.93";

	private String transferSyntax;
	private int encoding;
	private int endianType;
	private boolean encapsulatedData;
	private boolean supported;

	public TransferSyntax(String transferSyntax)
	{
		this.transferSyntax = transferSyntax;
		encoding = 0;
		endianType = 0;
		encapsulatedData = false;
		supported = true;

		if("1.2.840.10008.1.2".equals(transferSyntax))
		{
			encoding = EncodingImplicit;
			endianType = LittleEndianType;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.1"))
		{
			// Non compressed data vagy deflated compressed data encapsulation
			encoding = EncodingExplicit;
			endianType = LittleEndianType;
		}
		else if(transferSyntax.equals("1.2.840.10008.1.2.2"))
		{
			encoding = EncodingExplicit;
			endianType = BigEndianType;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.4"))
		{
			// JPEG, JPEG-LS, JPEG 2000, MPEG2, JPIP, JPIP deflated image encapsulation
			encoding = EncodingExplicit;
			endianType = LittleEndianType;
			encapsulatedData = true;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.5"))
		{
			// RLE compressed data encapsulation
			encoding = EncodingExplicit;
			endianType = LittleEndianType;
			encapsulatedData = true;
		}
		else
			supported = false;
	}

	/**
	 * Service level message-ek eseten a command set-ekhez hasznalando transfer syntax-ot adja vissza
	 * @return
	 */
	public static TransferSyntax getCommandTransferSyntax()
	{
		return new TransferSyntax(ImplicitVRLittleEndian);		// PS3.7 6.3.1 alapjan
	}

	//-----------------------------------------------------------------------------------------------------

	/**
	 * @return the encoding
	 */ public int getEncoding() {
		return encoding;
	}

	/**
	 * @return the endianType
	 */ public int getEndianType() {
		return endianType;
	}

	/**
	 * @return the encapsulatedData
	 */ public boolean isEncapsulatedData() {
		return encapsulatedData;
	}

	/**
	 * @return the supported
	 */ public boolean isSupported() {
		return supported;
	}

	/**
	 * @return the transferSyntax
	 */ public String getTransferSyntax() {
		return transferSyntax;
	}
}
