package tdsdicominterface;

import tdsdicominterface.exception.DIException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import tdsdicominterface.exception.DIInternalErrorException;

/**
 *
 * @author Rob
 */
public class DicomItem implements Serializable, Cloneable
{
	private ArrayList<DataElement> dataElements;
	private HashMap<Integer, DataElement> dataElementsHash;
	private long length;

	public DicomItem()
	{
		dataElements = new ArrayList<DataElement>();
		dataElementsHash = new HashMap<Integer, DataElement>();
		length = 0;
	}

	/**
	 * Attributum ertekenek lekerdezese attributum nev alapjan. Az eljaras egy DValue objektummal ter vissza, amely
	 * 'value'-ja tartalmazza az erteket es 'type'-ja meghatarozza annak tipusat. A 'type' DValue konstanssal van megadva.
	 * Ha az attributum egy sequence, akkor egy ArrayList objektum a visszateresi ertek, amely DicomItem objektumokat
	 * tartalmaz. A 'type' pedig a DValue.LIST konstans lesz. Egy ilyen DicomItem objektumnak a megfelelo getValue()
	 * metodusaval le lehet kerdezni a kivant attributumot.
	 * @param attributeName attributum neve. Szavakat egybe kell irni, kis és nagy betu nem szamit.
	 * @return egy DValue objektum. Ha a megadott attributum nem talalhato, akkor null.
	 * @throws tdsdicominterface.DIException
	 */
	public DValue getValue(String attributeName) throws DIException
	{
		Integer key = DicomDictionary.getItemTag(attributeName.toUpperCase());
		if(key == null) throw new DIException("DicomFile.getValue(): Attribute name is invalid (" + attributeName.toUpperCase() + ")");
		return getValue(key);
	}

	/**
	 * Attributum ertekenek lekerdezese group, element number alapjan. Az eljaras egy DValue objektummal ter vissza, amely
	 * 'value'-ja tartalmazza az erteket es 'type'-ja meghatarozza annak tipusat. A 'type' DValue konstanssal van megadva.
	 * Ha az attributum egy sequence, akkor egy ArrayList objektum a visszateresi ertek, amely DicomItem objektumokat
	 * tartalmaz. A 'type' pedig a DValue.LIST konstans lesz. Egy ilyen DicomItem objektumnak a megfelelo getValue()
	 * metodusaval le lehet kerdezni a kivant attributumot.
	 * @param groupNumber
	 * @param elementNumber
	 * @return egy DValue objektum. Ha a megadott attributum nem talalhato, akkor null.
	 * @throws tdsdicominterface.DIException
	 */
	public DValue getValue(int groupNumber, int elementNumber) throws DIException
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		return getValue(key);
	}

	/**
	 * Attributum ertekenek lekerdezese group, element number alapjan. A groupElement string-et
	 * gggg,eeee formatumban kell megadni, ahol az egyes ertekek hexadecimalisan ertendok.
	 * @param groupElement
	 * @return
	 * @throws tdsdicominterface.DIException
	 */
	public DValue getValueByGroupElementString(String groupElement) throws DIException
	{
		if(groupElement.length() != 9) throw new DIException("Invalid parameter (" + groupElement + ")!");
		if(groupElement.charAt(4) != ',') throw new DIException("Invalid parameter (" + groupElement + ")!");
		int groupNumber = Integer.parseInt(groupElement.substring(0,4), 16);
		int elementNumber = Integer.parseInt(groupElement.substring(5,9), 16);
		return getValue(groupNumber, elementNumber);
	}

	/**
	 * Attributum ertekenek lekerdezese group, element number alapjan. A group number-t string formaban kell megadni
	 * es lehet X-eket is alkamazni benne: pl. a (60xx,0050) attributum eseten. Ha X-ek vannak a group number-ben,
	 * akkor a 0. es 1. helyierteken KELL lennie az X-eknek. Mashol nem lehetnek es 2 X-nek kell lennie.
	 * <p>Az eljaras egy DValue listaval ter vissza, mivel tobb data element is megfelelhet a kriteriumoknak. A lista elemei
	 * DValue objektumok, amelyek 'value'-ja tartalmazza az erteket, 'type'-ja meghatarozza annak tipusat, valamint
	 * groupNum, elementNum meghatarozzak a tenyleges group es element number-t.<br>
	 * A 'type' DValue konstanssal van megadva.
	 * Ha az attributum egy sequence, akkor egy ArrayList objektum a visszateresi ertek, amely DicomItem objektumokat
	 * tartalmaz. A 'type' pedig a DValue.LIST konstans lesz. Egy ilyen DicomItem objektumnak a megfelelo getValue()
	 * metodusaval le lehet kerdezni a kivant attributumot.
	 * @param groupNumber string-kent kell megadni. Tartalmazhat X-et is, viszont akkor CSAK az nnXX formatum alkalmazhato, ahol
	 * n hexadecimalis szamjegy.
	 * @param elementNumber
	 * @return DValue lista. Ha a megadott attributum nem talalhato, akkor null.
	 * @throws tdsdicominterface.DIException
	 */
	public ArrayList<DValue> getValue(String groupNumber, int elementNumber) throws DIException
	{
		String gnum = groupNumber.toUpperCase();
		ArrayList<DValue> list = new ArrayList<DValue>();
		if(gnum.contains("X"))	// van X a group number-ben
		{
			if(gnum.length() != 4 || gnum.indexOf("X") != 2 || gnum.charAt(3) != 'X')
				throw new DIInternalErrorException("GroupNumber string parameter has incorrect form (%s)", groupNumber);
			int gnumFloor = Integer.parseInt(gnum.replace("XX", "00"), 16);
			for(int i = 0; i < dataElements.size(); i++)
			{
				if((dataElements.get(i).getGroupNumber() & 0xff00) == gnumFloor
						&& dataElements.get(i).getElementNumber() == elementNumber)		// ha a group es element number stimmel
				{
					Integer key = ((dataElements.get(i).getGroupNumber() & 0xffff) << 16) + (elementNumber & 0xffff);
					list.add(getValue(key));
				}
			}
		}
		else	// nincs X a group number-ben - normal way
		{
			int groupNum = Integer.parseInt(groupNumber, 16);
			Integer key = ((groupNum & 0xffff) << 16) + (elementNumber & 0xffff);
			list.add(getValue(key));
		}
		if(list.size() > 0) return list;
		else return null;
	}

	private DValue getValue(Integer key)
	{
		DValue val = null;
		DataElement de = dataElementsHash.get(key);
		if(de == null) return null;

		if (de.isSequence())
		{
			val = new DValue(de.getDicomItems(), de.getGroupNumber(), de.getElementNumber());
		}
		else if(de.isEncapsulatedPixelData())
		{
			val = new DValue((EncapsulatedData)de.getValue(), de.getGroupNumber(), de.getElementNumber());
		}
		else
		{
			VR vr = de.getVr();
			if (vr == null) return null;
			val = new DValue(de.getValue(), de.getVr().getValueType(), de.getGroupNumber(), de.getElementNumber());
		}

		return val;
	}

	public String getString(int groupNumber, int elementNumber) throws DIException
	{
		DValue dv = getValue(groupNumber, elementNumber);
		if(dv == null) return null;
		if(dv.getType() != DValue.STRING)
			throw new DIInternalErrorException("DicomItem.getString(): the requested element's value is not a String!");
		return (String) dv.getValue();
	}

	public String getString(String attributeName) throws DIException
	{
		DValue dv = getValue(attributeName);
		if(dv == null) return null;
		if(dv.getType() != DValue.STRING)
			throw new DIInternalErrorException("DicomItem.getString(): the requested element's value is not a String!");
		return (String) dv.getValue();
	}

	public Integer getFirstInt(int groupNumber, int elementNumber) throws DIException
	{
		DValue dv = getValue(groupNumber, elementNumber);
		if(dv == null) return null;
		if(dv.getType() != DValue.INTARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstInt(): the requested element's value is not an Integer!");
		if(((int[])dv.getValue()).length == 0) return null;
		return ((int[])dv.getValue())[0];
	}

	public Integer getFirstInt(String attributeName) throws DIException
	{
		DValue dv = getValue(attributeName);
		if(dv == null) return null;
		if(dv.getType() != DValue.INTARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstInt(): the requested element's value is not an Integer!");
		if(((int[])dv.getValue()).length == 0) return null;
		return ((int[])dv.getValue())[0];
	}

	public Long getFirstLong(int groupNumber, int elementNumber) throws DIException
	{
		DValue dv = getValue(groupNumber, elementNumber);
		if(dv == null) return null;
		if(dv.getType() != DValue.LONGARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstLong(): the requested element's value is not a Long!");
		if(((long[])dv.getValue()).length == 0) return null;
		return ((long[])dv.getValue())[0];
	}

	public Long getFirstLong(String attributeName) throws DIException
	{
		DValue dv = getValue(attributeName);
		if(dv == null) return null;
		if(dv.getType() != DValue.LONGARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstLong(): the requested element's value is not a Long!");
		if(((long[])dv.getValue()).length == 0) return null;
		return ((long[])dv.getValue())[0];
	}

	public Double getFirstDouble(int groupNumber, int elementNumber) throws DIException
	{
		DValue dv = getValue(groupNumber, elementNumber);
		if(dv == null) return null;
		if(dv.getType() != DValue.DOUBLEARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstDouble(): the requested element's value is not a Double!");
		if(((double[])dv.getValue()).length == 0) return null;
		return ((double[])dv.getValue())[0];
	}

	public Double getFirstDouble(String attributeName) throws DIException
	{
		DValue dv = getValue(attributeName);
		if(dv == null) return null;
		if(dv.getType() != DValue.DOUBLEARRAY)
			throw new DIInternalErrorException("DicomItem.getFirstDouble(): the requested element's value is not a Double!");
		if(((double[])dv.getValue()).length == 0) return null;
		return ((double[])dv.getValue())[0];
	}

	public byte[] getByteArray(int groupNumber, int elementNumber) throws DIException
	{
		DValue dv = getValue(groupNumber, elementNumber);
		if(dv == null) return null;
		if(dv.getType() != DValue.BYTEARRAY)
			throw new DIInternalErrorException("DicomItem.getByteArray(): the requested element's value is not a byte array!");
		return (byte[]) dv.getValue();
	}

	public byte[] getByteArray(String attributeName) throws DIException
	{
		DValue dv = getValue(attributeName);
		if(dv == null) return null;
		if(dv.getType() != DValue.BYTEARRAY)
			throw new DIInternalErrorException("DicomItem.getByteArray(): the requested element's value is not a byte array!");
		return (byte[]) dv.getValue();
	}

	public void addDataElement(DataElement dataElement)
	{
		dataElements.add(dataElement);
		Integer key = (dataElement.getGroupNumber() << 16) + dataElement.getElementNumber();
		getDataElementsHash().put(key, dataElement);
	}

	/**
	 * Meglevo data element-et cserel egy ujra. Az uj data element group number-enek es element number-enek egyeznie kell
	 * a lecserelendo data element-evel!
	 * @param dataElement
	 */
	public void replaceDataElement(DataElement dataElement)
	{
		int i = 0;
		for(; i < dataElements.size(); i++)
		{
			if(dataElements.get(i).getGroupNumber() == dataElement.getGroupNumber() &&
					dataElements.get(i).getElementNumber() == dataElement.getElementNumber()) break;
		}
		if(i == dataElements.size())
			throw new DIInternalErrorException("DicomItem.replaceDataElement(): matching data element cannot be found!");

		dataElements.remove(i);
		dataElements.add(i, dataElement);
		
		Integer key = (dataElement.getGroupNumber() << 16) + dataElement.getElementNumber();
		dataElementsHash.remove(key);
		dataElementsHash.put(key, dataElement);
	}

	public void changeDataElementValue(int groupNumber, int elementNumber, Object newValue)
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		DataElement de = dataElementsHash.get(key);
		if(de == null) throw new DIInternalErrorException("DicomItem.changeDataElementValue(): Data element does not exist (%04X,%04X)!",
				groupNumber, elementNumber);
		de.setNewValue(newValue);
	}

	/**
	 * DataElement letrehozasa vagy modositasa ha mar letezik. A value lehet nativ (pl. Integer), lehet tomb (pl. int[]) vagy null.
	 * Sequence es encapsulated pixel data letrehozasara, modositasara nem alkalmazhato!
	 * @param groupNumber
	 * @param elementNumber
	 * @param newValue
	 */
	public void createOrChangeDataElementValue(int groupNumber, int elementNumber, Object newValue)
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		DataElement de = dataElementsHash.get(key);
		if(de != null)		// ha change
		{
			de.setNewValue(newValue);
		}
		else	// ha create
		{
			// uj data element letrehozasa + beillesztes a listaba a group- es element number szerinti helyre
			de = new DataElement(groupNumber, elementNumber, newValue);
			int i = 0;
			for(; i < dataElements.size(); i++)
			{
				DataElement elem = dataElements.get(i);
				if(elem.getGroupNumber() > groupNumber ||
						(elem.getGroupNumber() == groupNumber && elem.getElementNumber() > elementNumber)) break;
			}
			dataElements.add(i, de);
			dataElementsHash.put(key, de);
		}
	}

	public void deleteDataElement(int groupNumber, int elementNumber)
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		DataElement de = dataElementsHash.remove(key);
		if(de != null)
		{
			dataElements.remove(de);
		}
	}

	public void createOrReplaceDataElement(int groupNumber, int elementNumber, DataElement de)
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		DataElement prevDe = dataElementsHash.get(key);
		if(prevDe != null)		// toroljuk ha volt
		{
			dataElementsHash.remove(key);
			dataElements.remove(prevDe);
		}

		// uj beszurasa
		int i = 0;
		for(; i < dataElements.size(); i++)
		{
			DataElement elem = dataElements.get(i);
			if(elem.getGroupNumber() > groupNumber ||
					(elem.getGroupNumber() == groupNumber && elem.getElementNumber() > elementNumber)) break;
		}
		dataElements.add(i, de);
		dataElementsHash.put(key, de);
	}

	public String getVrCode(int groupNumber, int elementNumber)
	{
		Integer key = ((groupNumber & 0xffff) << 16) + (elementNumber & 0xffff);
		DataElement de = dataElementsHash.get(key);
		if(de == null)
			throw new DIInternalErrorException("DicomItem.getVrCode(): Data element does not exist (%04X,%04X)!",
				groupNumber, elementNumber);
		return de.getVr().getCode();
	}

	@Override
	public Object clone()
	{
		try
		{
			DicomItem di = (DicomItem) super.clone();
			di.createEmptyLists();
			for(int i = 0; i < dataElements.size(); i++)
			{
				di.addDataElement((DataElement) dataElements.get(i).clone());
			}
			return di;
		}
		catch (CloneNotSupportedException ex)
		{
			throw new DIInternalErrorException("Clone problem.");
		}
	}

	public void createEmptyLists()
	{
		dataElements = new ArrayList<DataElement>();
		dataElementsHash = new HashMap<Integer, DataElement>();
	}

	//-------------------------------------------------------------------------------------------

	/**
	 * @return the dataElements
	 */ public ArrayList<DataElement> getDataElements() {
		return dataElements;
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
	 * @return the dataElementsHash
	 */
	public HashMap<Integer, DataElement> getDataElementsHash() {
		return dataElementsHash;
	}
}
