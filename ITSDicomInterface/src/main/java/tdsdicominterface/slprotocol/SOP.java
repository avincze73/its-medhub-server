package tdsdicominterface.slprotocol;

public class SOP
{
	// SOP class UID-k
	public static final String Verification = "1.2.840.10008.1.1";

	public static final String PatientRootQueryRetrieveFIND = "1.2.840.10008.5.1.4.1.2.1.1";
	public static final String PatientRootQueryRetrieveMOVE = "1.2.840.10008.5.1.4.1.2.1.2";
	public static final String PatientRootQueryRetrieveGET = "1.2.840.10008.5.1.4.1.2.1.3";
	public static final String StudyRootQueryRetrieveFIND = "1.2.840.10008.5.1.4.1.2.2.1";
	public static final String StudyRootQueryRetrieveMOVE = "1.2.840.10008.5.1.4.1.2.2.2";
	public static final String StudyRootQueryRetrieveGET = "1.2.840.10008.5.1.4.1.2.2.3";

	public static final String CT_ImageStorage = "1.2.840.10008.5.1.4.1.1.2";
	public static final String EnhancedCT_ImageStorage = "1.2.840.10008.5.1.4.1.1.2.1";
	public static final String MR_ImageStorage = "1.2.840.10008.5.1.4.1.1.4";
	public static final String EnhancedMR_ImageStorage = "1.2.840.10008.5.1.4.1.1.4.1";
	public static final String CR_ImageStorage = "1.2.840.10008.5.1.4.1.1.1";		// foszforlemezes röntgen
	public static final String MG_ImageStorage = "1.2.840.10008.5.1.4.1.1.1.2";		// mammografia
	public static final String DX_ImageStorage = "1.2.840.10008.5.1.4.1.1.1.1";		// Digital X-RAY
}
