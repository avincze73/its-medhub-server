package tdsdicominterface;

import java.io.IOException;
import tdsdicominterface.EncapsulatedData.DataFragment;
import tdsdicominterface.EncapsulatedData.DataFrame;
import tdsdicominterface.exception.DIContentErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIInternalErrorException;

public class DICoder
{
	private int encoding;
	private boolean isEncapsulatedData;	// Ilyenkor a 7FE0,0010 OB elem SQ-kent viselkedik (ganyolas a standard-ben...)

	public DICoder(TransferSyntax transferSyntax) throws DIException
	{
		if(!transferSyntax.isSupported()) DIException.createAndThrow("ERR_4102", transferSyntax.getTransferSyntax());
		this.encoding = transferSyntax.getEncoding();
		this.isEncapsulatedData = transferSyntax.isEncapsulatedData();
	}

	public DataElement readDataElement(DIInputStreamReader in) throws IOException, DIContentErrorException
	{
		DataElement de = new DataElement();
		long streamPosSt = in.getPosition();

		// Tag
		de.setGroupNumber(in.readIntU());
		de.setElementNumber(in.readIntU());

		// VR es value length
		if(encoding == TransferSyntax.EncodingExplicit)
		{
			String vrStr = in.readString(2);
			VR vr = VR.getVR(vrStr);
			if(vr == null) DIContentErrorException.createAndThrow(in.getPosition() - 2, "Invalid VR!");
			de.setVr(vr);
			if(vr.isLongValueLength())
			{
				int d = in.readIntU();		// 0x0000 reserved bytes
				if(d != 0) DIContentErrorException.createAndThrow(in.getPosition() - 2, "Invalid Reserved bytes after VR!");
				de.setValueLength(in.readLongU());		// value length
			}
			else
			{
				de.setValueLength((long)in.readIntU());		// value length
			}
		}
		else		// encoding is implicit
		{
			String vrStr = DicomDictionary.getVR(de.getGroupNumber(), de.getElementNumber());
			VR vr = VR.getVR(vrStr);
			de.setVr(vr);		// ervenytelen VR eseten null lesz
			de.setValueLength(in.readLongU());		// value length
		}

		// Value
		//-------------------------------

		// Encapsulated pixel data-rol van-e szo?
		if(isEncapsulatedData &&														// ha encapsulated transfer syntax
				de.getGroupNumber() == 0x7fe0 && de.getElementNumber() == 0x0010 &&		// ha pixel data
				/*"OB".equals(de.getVr().getCode()) &&*/								// VR = "OB" -nek kellene lennie, de van aki athagja ezt a szabalyt...
				de.getValueLength() == 0xffffffffL)										// ha undefined length
		{
			EncapsulatedData edata = readEncapsulatedData(in);
			de.setValue(edata);
			de.setEncapsulatedPixelData(true);
		}
		else	// nativ adat
		{
			// A value-val csak akkor foglalkozunk, ha van es ervenyes a VR
			VR vr = de.getVr();
			if(vr != null)
			{
				if(vr.getCode().equals("UN"))		// Unknown value type - nem kerul beolvasasra es eltarolasra
				{
					if(de.getValueLength() == 0xffffffffL)
						DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: unknown value type and unknown length!");
					in.skipBytes(de.getValueLength());
				}
				else if(vr.getCode().equals("SQ"))	// Sequence
				{
					// Item-ek beolvasasa
					boolean isUndefinedLength = (de.getValueLength() == 0xffffffffL ? true : false);
					long pos = 0;		// adott item beolvasasa utani pozicio a value reszben
					while(true)
					{
						// Vege van-e az item listanak
						if(isUndefinedLength)
						{
							in.mark(10);
							int tagGr = in.readIntU();
							int tagEl = in.readIntU();
							if(tagGr == 0xfffe && tagEl == 0xe0dd)	// ha itt a sequence vege
							{
								long len = in.readLongU();
								if(len != 0)
									DIContentErrorException.createAndThrow(in.getPosition() - 4, "Sequence delimitation item's length is not 00000000!");
								break;
							}
							in.reset();
						}
						else
						{
							if(pos == de.getValueLength()) break;
							else if(pos > de.getValueLength())
								DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: items length bigger than SQ value length!");
						}

						// DItem beolvasasa
						DicomItem di = readDicomItem(in);
						pos += di.getLength();
						de.getDicomItems().add(di);
					}

					de.setSequence(true);
				}
				else if(de.getValueLength() > 0)		// Normal value type es value length nem 0
				{
					if(de.getValueLength() == 0xffffffffL)
						DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: normal value type and unknown length!");
					String valueType = vr.getValueType();
					if(valueType.equals(VR.STRING))
					{
						String val = in.readString(de.getValueLength());
						if(val.length() == 0) val = null;
						de.setValue(val);
					}
					else if(valueType.equals(VR.INT))
					{
//						if(de.getValueLength() > 0)
//						{
							if(de.getValueLength() % 2 != 0)
								DIContentErrorException.createAndThrow(streamPosSt,
										"Illegal data element: Value length is not multiple of 2: value length = "
										+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
							int count = (int)de.getValueLength() / 2;
							int[] val = new int[count];
							for(int i = 0; i < count; i++) val[i] = in.readInt();
							de.setValue(val);
//						}
//						else de.setValue(null);
					}
					else if(valueType.equals(VR.UINT))
					{
						if(de.getValueLength() % 2 != 0)
							DIContentErrorException.createAndThrow(streamPosSt,
									"Illegal data element: Value length is not multiple of 2: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 2;
						int[] val = new int[count];
						for(int i = 0; i < count; i++) val[i] = in.readIntU();
						de.setValue(val);
					}
					else if(valueType.equals(VR.LONG))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(streamPosSt,
									"Illegal data element: Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						long[] val = new long[count];
						for(int i = 0; i < count; i++) val[i] = in.readLong();
						de.setValue(val);
					}
					else if(valueType.equals(VR.ULONG))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(streamPosSt,
									"Illegal data element: Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						long[] val = new long[count];
						for(int i = 0; i < count; i++) val[i] = in.readLongU();
						de.setValue(val);
					}
					else if(valueType.equals(VR.FLOAT))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(streamPosSt,
									"Illegal data element: Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						float[] val = new float[count];
						for(int i = 0; i < count; i++) val[i] = in.readFloat();
						de.setValue(val);
					}
					else if(valueType.equals(VR.DOUBLE))
					{
						if(de.getValueLength() % 8 != 0)
							DIContentErrorException.createAndThrow(streamPosSt,
									"Illegal data element: Value length is not multiple of 8: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 8;
						double[] val = new double[count];
						for(int i = 0; i < count; i++) val[i] = in.readDouble();
						de.setValue(val);
					}
					else if(valueType.equals(VR.BYTEARRAY))
					{
						byte[] val = in.readByteArray(de.getValueLength());
						de.setValue(val);
					}
					else if(valueType.equals(VR.WORDARRAY))
					{
						if(de.getValueLength() % 2 != 0)
							DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: Value length is not multiple of 2!");
						short[] val = in.readWordArray(de.getValueLength());
						de.setValue(val);
					}
					else if(valueType.equals(VR.DOUBLEWORDARRAY))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: Value length is not multiple of 4!");
						int[] val = in.readDoubleWordArray(de.getValueLength());
						de.setValue(val);
					}
					else throw new DIInternalErrorException("Illegal value type in VR: code = %s, value type = %s !",
							vr.getCode(), vr.getValueType());
				}
				else		// value length = 0  -  value = null lesz!
				{
					// nothing to do
				}
			}
			else
			{
				if(de.getValueLength() == 0xffffffffL)
					DIContentErrorException.createAndThrow(streamPosSt, "Illegal data element: unknown VR and unknown length!");
				in.skipBytes(de.getValueLength());
			}
		}

		de.setLength(in.getPosition() - streamPosSt);		// data element length
		return de;
	}

	public void writeDataElement(DataElement de, DIOutputStreamWriter out) throws IOException
	{
		// Tag
		out.writeInt(de.getGroupNumber());
		out.writeInt(de.getElementNumber());

		// VR es length
		VR vr = de.getVr();
		if(vr == null) throw new DIInternalErrorException("writeDataElement(): VR in data element is null!");

		if(encoding == TransferSyntax.EncodingExplicit)
		{
			out.writeString(vr.getCode(), 2);		// VR
			if(vr.isLongValueLength())		// ha 2 byte reserved + 4 byte length
			{
				out.writeInt(0);					// reserved (2 byte)
				if(vr.getCode().equals("SQ"))
					out.writeLong(0xffffffff);		// undefined length
				else
					out.writeLong(de.getValueLength());		// length
			}
			else		// ha 2 byte a length
			{
				out.writeInt((int) de.getValueLength());	// length
			}
		}
		else		// implicit encoding
		{
			if(vr.getCode().equals("SQ"))
				out.writeLong(0xffffffff);		// undefined length
			else
				out.writeLong(de.getValueLength());		// length
		}

		// Value
		if(de.getValueLength() != 0)	// ha nem ures a value
		{
			if(de.isEncapsulatedPixelData())
			{
				writeEncapsulatedData((EncapsulatedData) de.getValue(), out);
			}
			else		// nativ adat
			{
				if(vr.getCode().equals("UN"))			// Unknown value type
				{
					byte[] val = new byte[(int)de.getValueLength()];
					out.writeByteArray(val);
//					throw new DIInternalErrorException("Writting unknown value type is not supported at this time!");
				}
				else if(vr.getCode().equals("SQ"))		// sequence
				{
					// Dicom item-ek kiirasa
					for(int i = 0; i < de.getDicomItems().size(); i++)
					{
						writeDicomItem(de.getDicomItems().get(i), out);
					}
					
					// sequence delimitation
					out.writeInt(0xfffe);
					out.writeInt(0xe0dd);
					out.writeLong(0);
				}
				else if(vr.getValueType().equals(VR.STRING))
				{
					out.writeString((String) de.getValue(), true);
				}
				else if(vr.getValueType().equals(VR.INT))
				{
					int[] val = (int[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeInt(val[i]);
				}
				else if(vr.getValueType().equals(VR.UINT))
				{
					int[] val = (int[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeInt(val[i]);
				}
				else if(vr.getValueType().equals(VR.LONG))
				{
					long[] val = (long[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeLong(val[i]);
				}
				else if(vr.getValueType().equals(VR.ULONG))
				{
					long[] val = (long[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeLong(val[i]);
				}
				else if(vr.getValueType().equals(VR.FLOAT))
				{
					float[] val = (float[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeFloat(val[i]);
				}
				else if(vr.getValueType().equals(VR.DOUBLE))
				{
					double[] val = (double[]) de.getValue();
					for(int i = 0; i < val.length; i++) out.writeDouble(val[i]);
				}
				else if(vr.getValueType().equals(VR.BYTEARRAY))
				{
					byte[] val = (byte[]) de.getValue();
					out.writeByteArray(val);
				}
				else if(vr.getValueType().equals(VR.WORDARRAY))
				{
					short[] val = (short[]) de.getValue();
					out.writeWordArray(val);
				}
				else if(vr.getValueType().equals(VR.DOUBLEWORDARRAY))
				{
					int[] val = (int[]) de.getValue();
					out.writeDoubleWordArray(val);
				}
				else throw new DIInternalErrorException("Illegal value type in VR: code = %s, value type = %s !",
							vr.getCode(), vr.getValueType());
			}
		}
	}

	private DicomItem readDicomItem(DIInputStreamReader in) throws IOException, DIContentErrorException
	{
		DicomItem di = new DicomItem();
		long streamPosSt = in.getPosition();

		// Tag
		int tagGr = in.readIntU();
		int tagEl = in.readIntU();

		if(tagGr != 0xfffe || tagEl != 0xe000)
			DIContentErrorException.createAndThrow(streamPosSt, "Invalid Item tag: 0x%1$04X,0x%2$04X !", tagGr, tagEl);
		long length = in.readLongU();

		// Data element-ek beolvasasa
		boolean isUndefinedLength = (length == 0xffffffffL ? true : false);
		long pos = 0;		// adott data element beolvasasa utani pozicio a value reszben
		int prevGroup = 0;
		int prevElement = 0;
		while(true)
		{
			// Vege van-e a data element listanak
			if(isUndefinedLength)
			{
				in.mark(10);
				tagGr = in.readIntU();
				tagEl = in.readIntU();
				if(tagGr == 0xfffe && tagEl == 0xe00d)	// ha itt az item vege
				{
					long len = in.readLongU();
					if(len != 0) DIContentErrorException.createAndThrow(in.getPosition() - 4, "Item delimitation length is not 00000000!");
					break;
				}
				in.reset();
			}
			else
			{
				if(pos == length) break;
				else if(pos > length)
					DIContentErrorException.createAndThrow(streamPosSt, "Data elements length (in item) is bigger than Item value length!");
			}

			// Data element beolvasasa
			//------------------------
			DataElement de = readDataElement(in);
			pos += de.getLength();
			di.addDataElement(de);

			// Novekvo attributum tag ellenorzes
			if(de.getGroupNumber() < prevGroup
					|| de.getGroupNumber() == prevGroup && de.getElementNumber() <= prevElement)
			{
				DIContentErrorException.createAndThrow(in.getPosition() - de.getLength(), "Attribute tag is less than or equal the previuos tag!");
			}
			prevGroup = de.getGroupNumber();
			prevElement = de.getElementNumber();
		}

		di.setLength(in.getPosition() - streamPosSt);

		return di;
	}

	public void writeDicomItem(DicomItem ditem, DIOutputStreamWriter out) throws IOException
	{
		// Tag
		out.writeInt(0xfffe);
		out.writeInt(0xe000);

		// length (undefined)
		out.writeLong(0xffffffff);

		// DICOM object
		for(int i = 0; i < ditem.getDataElements().size(); i++)
		{
			writeDataElement(ditem.getDataElements().get(i), out);
		}

		// delimitation
		out.writeInt(0xfffe);
		out.writeInt(0xe00d);
		out.writeLong(0);
	}

	private EncapsulatedData readEncapsulatedData(DIInputStreamReader in) throws IOException, DIContentErrorException
	{
		EncapsulatedData edata = new EncapsulatedData();
		long streamPosSt = in.getPosition();

		// Basic Offset Table item
		//------------------------
		// Tag
		int tagGr = in.readIntU();
		int tagEl = in.readIntU();
		if(tagGr != 0xfffe || tagEl != 0xe000)
			DIContentErrorException.createAndThrow(in.getPosition() - 4, "Invalid Item tag: 0x%1$04X,0x%2$04X !", tagGr, tagEl);

		// Item length
		long len = in.readLongU();
		if(len == 0xffffffffL)
			DIContentErrorException.createAndThrow(in.getPosition() - 4, "Item length cannot be undefined!");

		// Item values
		if(len > 0) edata.setBasicOffsetTable(new long[(int)len / 4]);
		for(int i = 0; i < len / 4; i++)
		{
			edata.getBasicOffsetTable()[i] = in.readLongU();
		}

		// Fragment Item-ek
		//-----------------
		EncapsulatedData.DataFrame frame = new EncapsulatedData.DataFrame();
		edata.getFrames().add(frame);		// ez az elso frame
		long pos = 0;
		int nextFrameNum = 1;
		long nextFrameStart = -1;
		if(edata.getBasicOffsetTable() != null)
		{
			if(edata.getBasicOffsetTable().length > nextFrameNum)
				nextFrameStart = edata.getBasicOffsetTable()[nextFrameNum];
			else nextFrameStart = -1;
		}
		while(true)
		{
			// Frame kezdet ellenorzes
			if(pos == nextFrameStart)
			{
				frame = new EncapsulatedData.DataFrame();
				edata.getFrames().add(frame);
				nextFrameNum = edata.getFrames().size();
				if(edata.getBasicOffsetTable().length > nextFrameNum)
					nextFrameStart = edata.getBasicOffsetTable()[nextFrameNum];
				else nextFrameStart = -1;
			}

			// Tag
			tagGr = in.readIntU();
			tagEl = in.readIntU();
			pos += 4;

			// Sequence delimiter-e
			if(tagGr == 0xfffe && tagEl == 0xe0dd)	// ha vege az Item listanak
			{
				len = in.readLongU();
				if(len != 0)
					DIContentErrorException.createAndThrow(in.getPosition() - 4, "Item delimitation length is not 00000000!");
				break;
			}
			if(tagGr != 0xfffe || tagEl != 0xe000)
				DIContentErrorException.createAndThrow(in.getPosition() - 4, "Invalid Item tag: 0x%1$04X,0x%2$04X !", tagGr, tagEl);

			// Item length
			len = in.readLongU();
			if(len == 0xffffffffL)
				DIContentErrorException.createAndThrow(in.getPosition() - 4, "Item length cannot be undefined!");
			if(len == 0)
				DIContentErrorException.createAndThrow(in.getPosition() - 4, "Item length cannot be 0!");
			pos += 4;

			// Item value (compressed)
			EncapsulatedData.DataFragment fragment = new EncapsulatedData.DataFragment();
			fragment.setData(in.readByteArray(len));
			pos += len;
			frame.getFragments().add(fragment);
		}

		edata.setLength(in.getPosition() - streamPosSt);
		return edata;
	}

	private void writeEncapsulatedData(EncapsulatedData edata, DIOutputStreamWriter out) throws IOException
	{
		// item tag
		out.writeInt(0xfffe);
		out.writeInt(0xe000);
		
		// basic offset table
		if(edata.getBasicOffsetTable() != null)
		{
			out.writeLong(edata.getBasicOffsetTable().length * 4);		// item length
			for(int i = 0; i < edata.getBasicOffsetTable().length; i++)
			{
				out.writeLong(edata.getBasicOffsetTable()[i]);
			}
		}
		else
		{
			out.writeLong(0);
		}

		// frames
		for(int f = 0; f < edata.getFrames().size(); f++)
		{
			DataFrame frame = edata.getFrames().get(f);

			// fragments
			for(int fg = 0; fg < frame.getFragments().size(); fg++)
			{
				DataFragment fragment = frame.getFragments().get(fg);
				out.writeInt(0xfffe);		// item tag
				out.writeInt(0xe000);
				out.writeLong(fragment.getData().length);	// item length
				out.writeByteArray(fragment.getData());		// item value
			}
		}

		// sequence delimiter item
		out.writeInt(0xfffe);
		out.writeInt(0xe0dd);
		out.writeLong(0);
	}
}
