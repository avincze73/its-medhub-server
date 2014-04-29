package tdsdicominterface;

import java.io.Serializable;
import java.util.ArrayList;
import tdsdicominterface.DicomDictionary.DDItem;
import tdsdicominterface.exception.DIInternalErrorException;


/**
 *
 * @author Rob
 */
public class DataElement implements Serializable, Cloneable
{
	private int groupNumber;
	private int elementNumber;
	private VR vr;
	private long valueLength;
	private Object value;		// String vagy valamilyen tomb vagy EncapsulatedData. Ha valueLength 0 volt, akkor null lesz!
	private ArrayList<DicomItem> dicomItems;
	private boolean sequence;
	private boolean encapsulatedPixelData;
	private long length;		// a data element hossza beolvasas utan

	public DataElement()
	{
		sequence = false;
		encapsulatedPixelData = false;
		length = 0;
		value = null;
		dicomItems = null;
	}

	/**
	 * DataElement letrehozasa a megadott adatokkal. A value lehet nativ (pl. Integer), lehet tomb (pl. int[]) vagy null.
	 * Ez a konstruktor sequence es encapsulated pixel data letrehozasara nem alkalmazhato!
	 * @param groupNumber
	 * @param elementNumber
	 * @param vr
	 * @param value
	 */
	public DataElement(int groupNumber, int elementNumber, String vrCode, Object value)
	{
		this(new DDItem(groupNumber, elementNumber, vrCode, null, null), value);
	}

	/**
	 * DataElement letrehozasa a megadott adatokkal. A value lehet nativ (pl. Integer), lehet tomb (pl. int[]) vagy null.
	 * Ez a konstruktor sequence es encapsulated pixel data letrehozasara nem alkalmazhato!
	 * @param groupNumber
	 * @param elementNumber
	 * @param value
	 */
	public DataElement(int groupNumber, int elementNumber, Object value)
	{
		this(new DDItem(groupNumber, elementNumber, DicomDictionary.getVR(groupNumber, elementNumber), null, null), value);
	}

	/**
	 * DataElement letrehozasa a megadott adatokkal. A value lehet nativ (pl. Integer), lehet tomb (pl. int[]) vagy null.
	 * Ez a konstruktor sequence es encapsulated pixel data letrehozasara nem alkalmazhato!
	 * @param attributeName
	 * @param value
	 */
	public DataElement(String attributeName, Object value)
	{
		this(DicomDictionary.getDDItem(attributeName), value);
	}

	private DataElement(DDItem ddItem, Object value)
	{
		if(ddItem == null) throw new DIInternalErrorException("DataElement(): ddItem is null!");
		this.groupNumber = ddItem.getGroup();
		this.elementNumber = ddItem.getElement();
		if(ddItem.getVr() == null) throw new DIInternalErrorException("DataElement(): vrCode is null!");
		this.vr = VR.getVR(ddItem.getVr());
		
		setNewValue(value);
	}

	public final void setNewValue(Object value)
	{
		if(value != null)
		{
			if(vr.getValueType().equals(VR.STRING))
			{
				if(!(value instanceof String)) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
				this.value = value;
				valueLength = ((String)value).length();
				if(valueLength % 2 == 1) valueLength++;		// a hossz paros szamu kell legyen
			}
			else if(vr.getValueType().equals(VR.INT) || vr.getValueType().equals(VR.UINT))
			{
				if(value instanceof Integer)
				{
					this.value = new int[1];
					((int[])this.value)[0] = (Integer)value;
					valueLength = 2;
				}
				else
				{
					if(!(value instanceof int[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
					this.value = value;
					valueLength = ((int[])value).length * 2;
				}
			}
			else if(vr.getValueType().equals(VR.LONG) || vr.getValueType().equals(VR.ULONG))
			{
				if(value instanceof Long)
				{
					this.value = new long[1];
					((long[])this.value)[0] = (Long)value;
					valueLength = 4;
				}
				else
				{
					if(!(value instanceof long[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
					this.value = value;
					valueLength = ((long[])value).length * 4;
				}
			}
			else if(vr.getValueType().equals(VR.FLOAT))
			{
				if(value instanceof Float)
				{
					this.value = new float[1];
					((float[])this.value)[0] = (Float)value;
					valueLength = 4;
				}
				else
				{
					if(!(value instanceof float[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
					this.value = value;
					valueLength = ((float[])value).length * 4;
				}
			}
			else if(vr.getValueType().equals(VR.DOUBLE))
			{
				if(value instanceof Double)
				{
					this.value = new double[1];
					((double[])this.value)[0] = (Double)value;
					valueLength = 8;
				}
				else
				{
					if(!(value instanceof double[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
					this.value = value;
					valueLength = ((double[])value).length * 8;
				}
			}
			else if(vr.getValueType().equals(VR.BYTEARRAY))
			{
				if(!(value instanceof byte[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
				this.value = value;
				valueLength = ((byte[])value).length;
				if(valueLength % 2 != 0)
					throw new DIInternalErrorException("DataElement(): length of the given byte array is odd!");
			}
			else if(vr.getValueType().equals(VR.WORDARRAY))
			{
				if(!(value instanceof short[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
				this.value = value;
				valueLength = ((short[])value).length * 2;
			}
			else if(vr.getValueType().equals(VR.DOUBLEWORDARRAY))
			{
				if(!(value instanceof int[])) throw new DIInternalErrorException("DataElement.setNewValue(): wrong value type!");
				this.value = value;
				valueLength = ((int[])value).length * 4;
			}
			else throw new DIInternalErrorException("DataElement.setNewValue(): Illegal value type in VR: code = %s, value type = %s !",
						vr.getCode(), vr.getValueType());
		}
		else
		{
			this.value = null;
			valueLength = 0;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder val = new StringBuilder();
		if(getValue() != null)
		{
			if(vr.getValueType().equals(VR.STRING))
			{
				val.append(getValue().toString());
			}
			else if(vr.getValueType().equals(VR.INT) || vr.getValueType().equals(VR.UINT))
			{
				for(int i = 0; i < ((int[])getValue()).length; i++)
				{
					if(i > 0) val.append(";");
					int ival = ((int[])getValue())[i];
					val.append(String.format("%d (%XH)", ival, ival));
				}
			}
			else if(vr.getValueType().equals(VR.LONG) || vr.getValueType().equals(VR.ULONG))
			{
				for(int i = 0; i < ((long[])getValue()).length; i++)
				{
					if(i > 0) val.append(";");
					val.append(Long.toString(((long[])getValue())[i]));
				}
			}
			else if(vr.getValueType().equals(VR.FLOAT))
			{
				for(int i = 0; i < ((float[])getValue()).length; i++)
				{
					if(i > 0) val.append(";");
					val.append(Float.toString(((float[])getValue())[i]));
				}
			}
			else if(vr.getValueType().equals(VR.DOUBLE))
			{
				for(int i = 0; i < ((double[])getValue()).length; i++)
				{
					if(i > 0) val.append(";");
					val.append(Double.toString(((double[])getValue())[i]));
				}
			}
			else val.append("...");
		}

		String attName = DicomDictionary.getItemName(groupNumber, elementNumber);
		if(attName == null) attName = "<private attribute>";
		String vrCode = (vr != null ? vr.getCode() : "--");

		if(sequence)
			return String.format("(%1$04X,%2$04X) %3$s - %4$s", groupNumber, elementNumber, vrCode, attName);
		else if(encapsulatedPixelData)
		{
			return String.format("(%1$04X,%2$04X) %3$s - %4$s ENCAPSULATED PIXEL DATA",
					groupNumber, elementNumber, vrCode, attName);
		}
		else
			return String.format("(%1$04X,%2$04X) %3$s [%4$s] - %5$s", groupNumber, elementNumber,
					vrCode, val.toString(), attName);
	}

	public ArrayList<DicomItem> getDicomItems()
	{
		if(dicomItems == null) dicomItems = new ArrayList<DicomItem>();
		return dicomItems;
	}

	@Override
	public Object clone()
	{
		try
		{
			DataElement de = (DataElement) super.clone();
			de.setVr((VR) vr.clone());

			// value
			if(value == null) de.setValue(null);
			else if(value instanceof String) de.setValue(value);
			else if(value instanceof int[]) de.setValue(((int[])value).clone());
			else if(value instanceof long[]) de.setValue(((long[])value).clone());
			else if(value instanceof float[]) de.setValue(((float[])value).clone());
			else if(value instanceof double[]) de.setValue(((double[])value).clone());
			else if(value instanceof byte[]) de.setValue(((byte[])value).clone());
			else if(value instanceof short[]) de.setValue(((short[])value).clone());
			else if(value instanceof EncapsulatedData) de.setValue(((EncapsulatedData)value).clone());
			else throw new DIInternalErrorException("clone(): unrecognised object type of value (%s).", value.getClass().getName());

			
			ArrayList<DicomItem> dItems = new ArrayList<DicomItem>();
			de.setDicomItems(dItems);
			for(int i = 0; i < getDicomItems().size(); i++)
			{
				dItems.add((DicomItem) getDicomItems().get(i).clone());
			}

			return de;
		}
		catch (CloneNotSupportedException ex)
		{
			throw new DIInternalErrorException("Clone problem.");
		}

//		DataElement de = new DataElement();
//		de.setGroupNumber(groupNumber);
//		de.setElementNumber(elementNumber);
//		de.setVr(vr.clone());
//		de.setValueLength(valueLength);
//
//		// value
//		if(value == null) de.setValue(null);
//		else if(value instanceof String) de.setValue(value);
//		else if(value instanceof int[]) de.setValue(((int[])value).clone());
//		else if(value instanceof long[]) de.setValue(((long[])value).clone());
//		else if(value instanceof float[]) de.setValue(((float[])value).clone());
//		else if(value instanceof double[]) de.setValue(((double[])value).clone());
//		else if(value instanceof byte[]) de.setValue(((byte[])value).clone());
//		else if(value instanceof short[]) de.setValue(((short[])value).clone());
//		else if(value instanceof EncapsulatedData) de.setValue(((EncapsulatedData)value).clone());
//		else throw new DIInternalErrorException("clone(): unrecognised object type of value (%s).", value.getClass().getName());
//
//		ArrayList<DicomItem> dItems = de.getDicomItems();
//		for(int i = 0; i < getDicomItems().size(); i++)
//		{
//			dItems.add(getDicomItems().get(i).clone());
//		}
//
//		de.setSequence(sequence);
//		de.setEncapsulatedPixelData(encapsulatedPixelData);
//		de.setLength(length);
//
//		return de;
	}

	//----------------------------------------------------------------------------------------------------

	/**
	 * @return the groupNumber
	 */ public int getGroupNumber() {
		return groupNumber;
	}

	/**
	 * @param groupNumber the groupNumber to set
	 */ public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	/**
	 * @return the elementNumber
	 */ public int getElementNumber() {
		return elementNumber;
	}

	/**
	 * @param elementNumber the elementNumber to set
	 */ public void setElementNumber(int elementNumber) {
		this.elementNumber = elementNumber;
	}

	/**
	 * @return the vr
	 */ public VR getVr() {
		return vr;
	}

	/**
	 * @param vr the vr to set
	 */ public void setVr(VR vr) {
		this.vr = vr;
	}

	/**
	 * @return the valueLength
	 */ public long getValueLength() {
		return valueLength;
	}

	/**
	 * @param valueLength the valueLength to set
	 */ public void setValueLength(long valueLength) {
		this.valueLength = valueLength;
	}

	/**
	 * @return the sequence
	 */ public boolean isSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */ public void setSequence(boolean sequence) {
		this.sequence = sequence;
	}

	/**
	 * @param value the value to set
	 */ public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the length
	 */ public long getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */ public void setLength(long length) {
		this.length = length;
	}

	/**
	 * @return the encapsulatedPixelData
	 */ public boolean isEncapsulatedPixelData() {
		return encapsulatedPixelData;
	}

	/**
	 * @param encapsulatedPixelData the encapsulatedPixelData to set
	 */ public void setEncapsulatedPixelData(boolean encapsulatedPixelData) {
		this.encapsulatedPixelData = encapsulatedPixelData;
	}

	/**
	 * @return the value
	 */ public Object getValue() {
		return value;
	}

	/**
	 * @param dicomItems the dicomItems to set
	 */ public void setDicomItems(ArrayList<DicomItem> dicomItems) {
		this.dicomItems = dicomItems;
	}
	
}
