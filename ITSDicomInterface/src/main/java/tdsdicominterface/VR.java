package tdsdicominterface;

import java.io.Serializable;
import java.util.HashMap;
import tdsdicominterface.exception.DIInternalErrorException;

/**
 *
 * @author Rob
 */
public class VR implements Serializable, Cloneable
{
	private static final String[][] VRs = {
		{"CS", "Code String", "16", "no", "string"},
		{"SH", "Short String", "16", "no", "string"},
		{"LO", "Long String", "64", "no", "string"},
		{"ST", "Short Text", "1024", "no", "string"},
		{"LT", "Long Text", "10240", "no", "string"},
		{"UT", "Unlimited Text", "4294967294", "yes", "string"},
		{"AE", "Application Entity", "16", "no", "string"},
		{"PN", "Person Name", "64", "no", "string"},
		{"UI", "Unique Identifier", "64", "no", "string"},
		{"DA", "Date", "8", "no", "string"},
		{"TM", "Time", "16", "no", "string"},
		{"DT", "Date Time", "26", "no", "string"},
		{"AS", "Age String", "4", "no", "string"},
		{"IS", "Integer String", "12", "no", "string"},
		{"DS", "Decimal String", "16", "no", "string"},
		{"SS", "Signed Short", "2", "no", "int"},
		{"US", "Unsigned short", "2", "no", "uint"},
		{"SL", "Signed Long", "4", "no", "long"},
		{"UL", "Unsigned Long", "4", "no", "ulong"},
		{"AT", "Attribute Tag", "4", "no", "ulong"},
		{"FL", "Floating Point Single", "4", "no", "float"},
		{"FD", "Floating Point Double", "8", "no", "double"},
		{"OB", "Other Byte String", "", "yes", "bytearray"},
		{"OW", "Other Word String", "", "yes", "wordarray"},
		{"OF", "Other Float String", "", "yes", "doublewordarray"},
		{"SQ", "Sequence of Items", "", "yes", ""},
		{"UN", "Unknown", "", "yes", ""}
	};

	public static final String STRING = "string";
	public static final String INT = "int";
	public static final String UINT = "uint";	// unsigned int
	public static final String LONG = "long";
	public static final String ULONG = "ulong";	// unsigned long
	public static final String FLOAT = "float";
	public static final String DOUBLE = "double";
	public static final String BYTEARRAY = "bytearray";
	public static final String WORDARRAY = "wordarray";
	public static final String DOUBLEWORDARRAY = "doublewordarray";

	private static HashMap<String, VR> vrMap = null;

	private String code;
	private String name;
	private Long length;
	private boolean longValueLength;
	private String valueType;

	private VR(String code, String name, String length, String longValueLength, String valueType)
	{
		this.code = code;
		this.name = name;
		if(length.length() == 0) this.length = null;
		else this.length = Long.parseLong(length);
		if("yes".equals(longValueLength)) this.longValueLength = true;
		else this.longValueLength = false;
		this.valueType = valueType;
	}

	public static VR getVR(String code)
	{
		if(vrMap == null) fillVrMap();
		if(code == null) return null;
		return vrMap.get(code);
	}

	private static void fillVrMap()
	{
		vrMap = new HashMap<String, VR>();
		for(int i = 0; i < VRs.length; i++)
		{
			VR vr = new VR(VRs[i][0], VRs[i][1], VRs[i][2], VRs[i][3], VRs[i][4]);
			vrMap.put(VRs[i][0], vr);
		}
	}

	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException ex)
		{
			throw new DIInternalErrorException("Clone problem.");
		}

//		String lengthS = "";
//		if(length != null) lengthS = length.toString();
//		String longValueLengthS = (longValueLength ? "yes" : "no");
//		VR vr = new VR(code, name, lengthS, longValueLengthS, valueType);
//		return vr;
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * @return the code
	 */ public String getCode() {
		return code;
	}

	/**
	 * @return the name
	 */ public String getName() {
		return name;
	}

	/**
	 * @return the length
	 */ public Long getLength() {
		return length;
	}

	/**
	 * @return the longValueLength
	 */ public boolean isLongValueLength() {
		return longValueLength;
	}

	/**
	 * @return the valueType
	 */ public String getValueType() {
		return valueType;
	}
}
