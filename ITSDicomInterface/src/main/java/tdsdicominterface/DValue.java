package tdsdicominterface;

import tdsdicominterface.exception.DIInternalErrorException;
import java.util.ArrayList;

/**
 *
 * @author Rob
 */
public class DValue
{
	public static final int STRING = 1;
	public static final int BYTEARRAY = 2;
	public static final int SHORTARRAY = 3;
	public static final int INTARRAY = 4;
	public static final int LONGARRAY = 5;
	public static final int FLOATARRAY = 6;
	public static final int DOUBLEARRAY = 7;
	public static final int LIST = 8;
	public static final int ENCAPSULATEDDATA = 9;


	private Object value;
	private int type;
	private String vrType;
	private int groupNum;
	private int elementNum;

	public DValue(Object value, String vrValueType, int groupNum, int elementNum)
	{
		this.value = value;
		if(vrValueType.equals(VR.STRING)) this.type = STRING;
		else if(vrValueType.equals(VR.INT)) this.type = INTARRAY;
		else if(vrValueType.equals(VR.UINT)) this.type = INTARRAY;
		else if(vrValueType.equals(VR.LONG)) this.type = LONGARRAY;
		else if(vrValueType.equals(VR.ULONG)) this.type = LONGARRAY;
		else if(vrValueType.equals(VR.FLOAT)) this.type = FLOATARRAY;
		else if(vrValueType.equals(VR.DOUBLE)) this.type = DOUBLEARRAY;
		else if(vrValueType.equals(VR.BYTEARRAY)) this.type = BYTEARRAY;
		else if(vrValueType.equals(VR.WORDARRAY)) this.type = SHORTARRAY;
		else if(vrValueType.equals(VR.DOUBLEWORDARRAY)) this.type = INTARRAY;
		else throw new DIInternalErrorException("Illegal vrValueType! (" + vrValueType + ")");
		this.vrType = vrValueType;
		this.groupNum = groupNum;
		this.elementNum = elementNum;
	}

	public DValue(ArrayList<DicomItem> dicomItems, int groupNum, int elementNum)
	{
		this.value = dicomItems;
		this.type = LIST;
		this.vrType = null;
		this.groupNum = groupNum;
		this.elementNum = elementNum;
	}

	public DValue(EncapsulatedData edata, int groupNum, int elementNum)
	{
		this.value = edata;
		this.type = ENCAPSULATEDDATA;
		this.vrType = null;
		this.groupNum = groupNum;
		this.elementNum = elementNum;
	}

	//----------------------------------------------------------------------------

	/**
	 * @return az attributum értéke. A típusát a type határozza meg.
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @return az attributum értékének (objektumának) típusa. A típus az ebben az osztályban definiált
	 * konstans értékekkel van meghatározva. Pl. a STRING String objektumot jelent, az INTARRAY int[] objektumot,
	 * a LIST pedig ArrayList-et jelent, amely további DValue objektumokat tartalmaz sequence-ben található
	 * attributumok esetén.
	 */
	public int getType() {
		return type;
	}

	/**
	 * A VR objektumban meghatarozott tipus
	 * @return the vrType
	 */ public String getVrType() {
		return vrType;
	}

	/**
	 * @return the groupNum
	 */ public int getGroupNum() {
		return groupNum;
	}

	/**
	 * @return the elementNum
	 */ public int getElementNum() {
		return elementNum;
	}

}
