package tdsdicominterface.media;

import tdsdicominterface.exception.DIInternalErrorException;
import tdsdicominterface.exception.DIException;
import tdsdicominterface.exception.DIContentErrorException;
import tdsdicominterface.*;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author Rob
 */
public class DataImport
{
	public static final int LittleEndianType = 1;
	public static final int BigEndianType = 2;
	public static final int EncodingImplicit = 1;
	public static final int EncodingExplicit = 2;

	private BufferedInputStream bis;
	private int endianType;
	private int encoding;
	private boolean isEncapsulatedData;	// Ilyenkor a 7FE0,0010 OB elem SQ-kent viselkedik (ganyolas a standard-ben...)
	private int dataSetEncoding;
	private int dataSetEndianType;
	private File file;


	public DataImport()
	{		
	}

	public DicomDataSet importFile(File file) throws FileNotFoundException, DIException
	{
		try
		{
			DicomDataSet dicomDataSet = new DicomDataSet();
			this.file = file;
			if(!file.exists() || !file.isFile()) throw new FileNotFoundException();

			dicomDataSet.setFilePath(file.getAbsolutePath());
			long fileLength = file.length();
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			long filePos = 0;

			// Preamble
			skipBytes(128);		// 128 byte preamble
			filePos += 128;

			// DICM
			String s = readString(4);
			filePos += 4;
			if(!"DICM".equals(s)) throw new DIInternalErrorException("Nem DICOM file!");

			// Meta elements
			//--------------
			if(!bis.markSupported()) throw new DIInternalErrorException("'Mark' is not supported in BufferedInputStream!");

			// A Standard szerint a file meta adat resznek a kovetkezo kodolassal kell keszulnie:
			encoding = EncodingExplicit;
			endianType = LittleEndianType;

			// meta resz hossza
			DataElement deGroupLength = readDataElement(filePos);
			filePos += deGroupLength.getLength();
			long metaLength = ((long[])deGroupLength.getValue())[0];

			// meta resz beolvasasa
			long pos = 0;
			while(pos < metaLength)
			{
				DataElement de = readDataElement(filePos);
				pos += de.getLength();
				filePos += de.getLength();
				String metaElemName = DicomDictionary.getItemName(de.getGroupNumber(), de.getElementNumber());
				if("MediaStorageSOPInstanceUID".equals(metaElemName)) dicomDataSet.setSopInstanceUID((String)de.getValue());

				// Transfer syntax meghatarozasa
				if(de.getGroupNumber() == 0x0002 && de.getElementNumber() == 0x0010)
				{
					String transferSyntax = (String)de.getValue();
					dicomDataSet.setTransferSyntax(transferSyntax);
					if(transferSyntax == null || transferSyntax.length() == 0)
						DIContentErrorException.createAndThrow(filePos - de.getLength(), "No transfer syntax is defined!");
					settingsAccordingToTransferSyntax(transferSyntax, filePos - de.getLength());
				}
			}
			if(pos != metaLength)
				DIContentErrorException.createAndThrow(filePos,
					"Length of meta elements part does not equal to value in Group length attribute! "
					+ "length = " + pos + ", value (group length) = " + metaLength + " !");

			// Data elements beolvasasa
			//-------------------------

			// Encoding es endian beallitas
			encoding = dataSetEncoding;
			endianType = dataSetEndianType;

			int prevGroup = 0;
			int prevElement = 0;
			while(true)
			{
				if(filePos == fileLength) break;
				DataElement de = readDataElement(filePos);
				filePos += de.getLength();
				dicomDataSet.addDataElement(de);

				// Novekvo attributum tag ellenorzes
				if(de.getGroupNumber() < prevGroup
						|| de.getGroupNumber() == prevGroup && de.getElementNumber() <= prevElement)
				{
					DIContentErrorException.createAndThrow(filePos - de.getLength(), "Attribute tag is less than or equal the previuos tag!");
				}
				prevGroup = de.getGroupNumber();
				prevElement = de.getElementNumber();
			}

			bis.close();
			fis.close();
			return dicomDataSet;
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_5001");
			return null;
		}
	}

	private DataElement readDataElement(long filePos) throws IOException, DIContentErrorException
	{
		DataElement de = new DataElement();
		long deLength = 0;

		// Tag
		de.setGroupNumber(readIntU());
		de.setElementNumber(readIntU());
		deLength += 4;

		// VR es value length
		if(encoding == EncodingExplicit)
		{
			String vrStr = readString(2);
			VR vr = VR.getVR(vrStr);
			if(vr == null) DIContentErrorException.createAndThrow(filePos + deLength, "Invalid VR!");
			de.setVr(vr);
			deLength += 2;
			if(vr.isLongValueLength())
			{
				int d = readIntU();		// 0x0000 reserved bytes
				if(d != 0) DIContentErrorException.createAndThrow(filePos + deLength, "Invalid Reserved bytes after VR!");
				deLength += 2;
				de.setValueLength(readLongU());		// value length
				deLength += 4;
			}
			else
			{
				de.setValueLength((long)readIntU());		// value length
				deLength += 2;
			}			
		}
		else		// encoding is implicit
		{
			String vrStr = DicomDictionary.getVR(de.getGroupNumber(), de.getElementNumber());
			VR vr = VR.getVR(vrStr);
			de.setVr(vr);		// ervenytelen VR eseten null lesz
			de.setValueLength(readLongU());		// value length
			deLength += 4;
		}

		// Value
		//-------------------------------

		// Encapsulated pixel data-rol van-e szo?
		if(isEncapsulatedData &&														// ha encapsulated transfer syntax
				de.getGroupNumber() == 0x7fe0 && de.getElementNumber() == 0x0010 &&		// ha pixel data
				/*"OB".equals(de.getVr().getCode()) &&*/								// VR = "OB" -nek kellene lennie, de van aki athagja ezt a szabalyt...
				de.getValueLength() == 0xffffffffL)										// ha undefined length
		{
			EncapsulatedData edata = readEncapsulatedData(filePos + deLength, de.getVr().getCode());
			de.setValue(edata);
			de.setEncapsulatedPixelData(true);
			deLength += edata.getLength();
		}
		else	// nativ adat
		{
			// A value-val csak akkor foglalkozunk, ha van es ervenyes a VR
			VR vr = de.getVr();
			if(vr != null)
			{
				if(vr.getCode().equals("UN"))		// Unknown value type
				{
					if(de.getValueLength() == 0xffffffffL)
						DIContentErrorException.createAndThrow(filePos, "Unknown value type and unknown length!");
					skipBytes(de.getValueLength());
					deLength += de.getValueLength();
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
							bis.mark(10);
							int tagGr = readIntU();
							int tagEl = readIntU();
							deLength += 4;
							if(tagGr == 0xfffe && tagEl == 0xe0dd)	// ha itt a sequence vege
							{
								long len = readLongU();
								if(len != 0)
									DIContentErrorException.createAndThrow(filePos + deLength, "Sequence delimitation item's length is not 00000000!");
								deLength += 4;
								break;
							}
							bis.reset();
							deLength -= 4;
						}
						else
						{
							if(pos == de.getValueLength()) break;
							else if(pos > de.getValueLength())
								DIContentErrorException.createAndThrow(filePos, "Items length bigger than SQ value length!");
						}

						// DItem beolvasasa
						DicomItem di = readDicomItem(filePos + deLength);
						deLength += di.getLength();
						pos += di.getLength();
						de.getDicomItems().add(di);
					}

					de.setSequence(true);
				}
				else if(de.getValueLength() > 0)		// Normal value type es value length nem 0
				{
					if(de.getValueLength() == 0xffffffffL)
						DIContentErrorException.createAndThrow(filePos, "Normal value type and unknown length!");
					String valueType = vr.getValueType();
					if(valueType.equals(VR.STRING))
					{
						String val = readString(de.getValueLength());
						if(val.length() == 0) val = null;
						de.setValue(val);
					}
					else if(valueType.equals(VR.INT))
					{
//						if(de.getValueLength() > 0)
//						{
							if(de.getValueLength() % 2 != 0)
								DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 2: value length = "
										+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
							int count = (int)de.getValueLength() / 2;
							int[] val = new int[count];
							for(int i = 0; i < count; i++) val[i] = readInt();
							de.setValue(val);
//						}
//						else de.setValue(null);
					}
					else if(valueType.equals(VR.UINT))
					{
						if(de.getValueLength() % 2 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 2: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 2;
						int[] val = new int[count];
						for(int i = 0; i < count; i++) val[i] = readIntU();
						de.setValue(val);
					}
					else if(valueType.equals(VR.LONG))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						long[] val = new long[count];
						for(int i = 0; i < count; i++) val[i] = readLong();
						de.setValue(val);
					}
					else if(valueType.equals(VR.ULONG))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						long[] val = new long[count];
						for(int i = 0; i < count; i++) val[i] = readLongU();
						de.setValue(val);
					}
					else if(valueType.equals(VR.FLOAT))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 4: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 4;
						float[] val = new float[count];
						for(int i = 0; i < count; i++) val[i] = readFloat();
						de.setValue(val);
					}
					else if(valueType.equals(VR.DOUBLE))
					{
						if(de.getValueLength() % 8 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 8: value length = "
									+ de.getValueLength() + ", VR = " + vr.getCode() + " !");
						int count = (int)de.getValueLength() / 8;
						double[] val = new double[count];
						for(int i = 0; i < count; i++) val[i] = readDouble();
						de.setValue(val);
					}
					else if(valueType.equals(VR.BYTEARRAY))
					{
						byte[] val = readByteArray(de.getValueLength());
						de.setValue(val);
					}
					else if(valueType.equals(VR.WORDARRAY))
					{
						if(de.getValueLength() % 2 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 2!");
						short[] val = readWordArray(de.getValueLength());
						de.setValue(val);
					}
					else if(valueType.equals(VR.DOUBLEWORDARRAY))
					{
						if(de.getValueLength() % 4 != 0)
							DIContentErrorException.createAndThrow(filePos, "Value length is not multiple of 4!");
						int[] val = readDoubleWordArray(de.getValueLength());
						de.setValue(val);
					}
					else throw new DIInternalErrorException("Illegal value type in VR: code = " + vr.getCode() + ", value type = "
							+ vr.getValueType() + " !");

					deLength += de.getValueLength();
				}
				else		// value length = 0
				{
					// nothing to do
				}
			}
			else
			{
				if(de.getValueLength() == 0xffffffffL)
					DIContentErrorException.createAndThrow(filePos, "Unknown VR and unknown length!");
				skipBytes(de.getValueLength());
				deLength += de.getValueLength();
			}
		}

		de.setLength(deLength);		// data element length
		return de;
	}

	private DicomItem readDicomItem(long filePos) throws IOException, DIContentErrorException
	{
		DicomItem di = new DicomItem();
		long diLength = 0;

		// Tag
		int tagGr = readIntU();
		int tagEl = readIntU();

		diLength += 4;
		if(tagGr != 0xfffe || tagEl != 0xe000)
			DIContentErrorException.createAndThrow(filePos, "Invalid Item tag: " + String.format("0x%1$04X,0x%2$04X", tagGr, tagEl) + " !");
		long length = readLongU();
		diLength += 4;

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
				bis.mark(10);
				tagGr = readIntU();
				tagEl = readIntU();
				diLength += 4;
				if(tagGr == 0xfffe && tagEl == 0xe00d)	// ha itt az item vege
				{
					long len = readLongU();
					if(len != 0) DIContentErrorException.createAndThrow(filePos + diLength, "Item delimitation length is not 00000000!");
					diLength += 4;
					break;
				}
				bis.reset();
				diLength -= 4;
			}
			else
			{
				if(pos == length) break;
				else if(pos > length)
					DIContentErrorException.createAndThrow(filePos, "Data elements length (in item) is bigger than Item value length!");
			}

			// Data element beolvasasa
			//------------------------
			DataElement de = readDataElement(filePos + diLength);
			diLength += de.getLength();
			pos += de.getLength();
			di.addDataElement(de);

			// Novekvo attributum tag ellenorzes
			if(de.getGroupNumber() < prevGroup
					|| de.getGroupNumber() == prevGroup && de.getElementNumber() <= prevElement)
			{
				DIContentErrorException.createAndThrow(filePos + diLength - de.getLength(), "Attribute tag is less than or equal the previuos tag!");
			}
			prevGroup = de.getGroupNumber();
			prevElement = de.getElementNumber();
		}

		di.setLength(diLength);

		return di;
	}

	private EncapsulatedData readEncapsulatedData(long filePos, String vrType) throws IOException, DIContentErrorException
	{
		EncapsulatedData edata = new EncapsulatedData();
		long edLength = 0;

		// Basic Offset Table item
		//------------------------
		// Tag
		int tagGr = readIntU();
		int tagEl = readIntU();
		if(tagGr != 0xfffe || tagEl != 0xe000)
			DIContentErrorException.createAndThrow(filePos + edLength, "Invalid Item tag: " + String.format("0x%1$04X,0x%2$04X", tagGr, tagEl) + " !");
		edLength += 4;

		// Item length
		long len = readLongU();
		if(len == 0xffffffffL)
			DIContentErrorException.createAndThrow(filePos + edLength, "Item length cannot be undefined!");
		edLength += 4;

		// Item values
		if(len > 0) edata.setBasicOffsetTable(new long[(int)len / 4]);
		for(int i = 0; i < len / 4; i++)
		{
			edata.getBasicOffsetTable()[i] = readLongU();
			edLength += 4;
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
			tagGr = readIntU();
			tagEl = readIntU();
			edLength += 4;
			pos += 4;

			// Sequence delimiter-e
			if(tagGr == 0xfffe && tagEl == 0xe0dd)	// ha vege az Item listanak
			{
				len = readLongU();
				if(len != 0)
					DIContentErrorException.createAndThrow(filePos + edLength, "Item delimitation length is not 00000000!");
				edLength += 4;
				break;
			}
			if(tagGr != 0xfffe || tagEl != 0xe000)
				DIContentErrorException.createAndThrow(filePos + edLength - 4, "Invalid Item tag: "
						+ String.format("0x%1$04X,0x%2$04X", tagGr, tagEl) + " !");

			// Item length
			len = readLongU();
			if(len == 0xffffffffL)
				DIContentErrorException.createAndThrow(filePos + edLength, "Item length cannot be undefined!");
			if(len == 0)
				DIContentErrorException.createAndThrow(filePos + edLength, "Item length cannot be 0!");
			edLength += 4;
			pos += 4;

			// Item value (compressed)
			EncapsulatedData.DataFragment fragment = new EncapsulatedData.DataFragment();
			if("OB".equals(vrType))
			{
				fragment.setData(readByteArray(len));
			}
			else if("OW".equals(vrType))
			{
				fragment.setData(readByteArray(len));		// Ugy tunik nincs jelentosege, hogy OB vagy OW itt a beolvasasnal

//				byte[] bbuf = readByteArray(len);
//				for(int i = 0; i < bbuf.length; i += 2)
//				{
//					byte b = bbuf[i];
//					bbuf[i] = bbuf[i + 1];
//					bbuf[i + 1] = b;
//				}
//				fragment.setData(bbuf);
			}
			edLength += len;
			pos += len;
			frame.getFragments().add(fragment);
		}

		edata.setLength(edLength);
		return edata;
	}

	private String readString(long length) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		for(long i = 0; i < length; i++)
		{
			int c = bis.read();
			sb.append((char)c);
		}
		String res = sb.toString();		// A trailing blank space levagasa
		if(res.length() > 0 && res.charAt(res.length() - 1) == 0) return res.substring(0, res.length() - 1);
		if(res.length() > 0 && res.charAt(res.length() - 1) == ' ') return res.substring(0, res.length() - 1);
		else return res;
	}

	private int readInt() throws IOException
	{
		return (int)readInteger(2, true);
	}

	private int readIntU() throws IOException
	{
		return (int)readInteger(2, false);
	}

	private long readLong() throws IOException
	{
		return readInteger(4, true);
	}

	private long readLongU() throws IOException
	{
		return readInteger(4, false);
	}

	private long readInteger(int byteNum, boolean isSigned) throws IOException
	{
		long val = 0;
		if(endianType == LittleEndianType)
		{
			long k = 1;
			for(int i = 0; i < byteNum; i++)
			{
				int b = bis.read();
				if(b == -1) throw new EOFException();
				val += (long)b * k;
				k = k << 8;
			}
		}
		else		// big endian type
		{
			long k = 0x1 << ((byteNum - 1) * 8);
			for(int i = 0; i < byteNum; i++)
			{
				int b = bis.read();
				if(b == -1) throw new EOFException();
				val += (long)b * k;
				k = k >> 8;
			}
		}

		if(isSigned)
		{
			long sign = (val >> (byteNum * 8 - 1)) & 0x1;
			if(sign == 1)		// ha negativ
			{
				long mask = 0xFF00000000000000L >> ((7 - byteNum) * 8);
				val = val | mask;
			}
		}

		return val;
	}

	private double readDouble() throws IOException
	{
		ByteBuffer bbuf = ByteBuffer.allocate(8);
		byte[] buf = new byte[8];
		int ret = bis.read(buf);
		if(ret == -1) throw new EOFException();
		bbuf.put(buf);
		if(endianType == LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		return bbuf.getDouble(0);
	}

	private float readFloat() throws IOException
	{
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		byte[] buf = new byte[4];
		int ret = bis.read(buf);
		if(ret == -1) throw new EOFException();
		bbuf.put(buf);
		if(endianType == LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		return bbuf.getFloat(0);
	}

	private byte[] readByteArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readByteArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readByteArray: length = 0xffffffffL !");
		byte[] buf = new byte[(int)length];
		int rlen = bis.read(buf);
		if(rlen != (int)length) throw new EOFException();
		return buf;
		// Megjegyzes: Azt nem tudom eldonteni, hogy ha az utolso byte = 0, akkor az az eredeti byte tomb resze,
		// vagy a paros hossz miatt lett hozzabiggyesztve.
	}

	private short[] readWordArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readWordArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readWordArray: length = 0xffffffffL !");
		else if(length % 2 != 0) throw new DIInternalErrorException("readWordArray: length is odd = " + length + " !");
		short[] buf = new short[(int)length / 2];

		if(endianType == LittleEndianType)
		{
			for(int i = 0; i < length / 2; i++)
			{
				int b1 = bis.read();
				int b2 = bis.read();
				buf[i] = (short)((b2 << 8) + b1);
			}
		}
		else	// big endian
		{
			for(int i = 0; i < length / 2; i++)
			{
				int b1 = bis.read();
				int b2 = bis.read();
				buf[i] = (short)((b1 << 8) + b2);
			}
		}

		return buf;
	}

	private int[] readDoubleWordArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readDoubleWordArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readDoubleWordArray: length = 0xffffffffL !");
		else if(length % 4 != 0) throw new DIInternalErrorException("readDoubleWordArray: length does not fit for double word array = " + length + " !");
		int[] buf = new int[(int)length / 4];

		if(endianType == LittleEndianType)
		{
			for(int i = 0; i < length / 4; i++)
			{
				int b1 = bis.read();
				int b2 = bis.read();
				int b3 = bis.read();
				int b4 = bis.read();
				buf[i] = (b4 << 24) + (b3 << 16) + (b2 << 8) + b1;
			}
		}
		else	// big endian
		{
			for(int i = 0; i < length / 2; i++)
			{
				int b1 = bis.read();
				int b2 = bis.read();
				int b3 = bis.read();
				int b4 = bis.read();
				buf[i] = (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
			}
		}

		return buf;
	}

	private void settingsAccordingToTransferSyntax(String transferSyntax, long filePos) throws DIContentErrorException
	{
		isEncapsulatedData = false;

		if("1.2.840.10008.1.2".equals(transferSyntax))
		{
			dataSetEncoding = EncodingImplicit;
			dataSetEndianType = LittleEndianType;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.1"))
		{
			// Non compressed data vagy deflated compressed data encapsulation
			dataSetEncoding = EncodingExplicit;
			dataSetEndianType = LittleEndianType;
		}
		else if(transferSyntax.equals("1.2.840.10008.1.2.2"))
		{
			dataSetEncoding = EncodingExplicit;
			dataSetEndianType = BigEndianType;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.4"))
		{
			// JPEG, JPEG-LS, JPEG 2000, MPEG2, JPIP, JPIP deflated image encapsulation
			dataSetEncoding = EncodingExplicit;
			dataSetEndianType = LittleEndianType;
			isEncapsulatedData = true;
		}
		else if(transferSyntax.contains("1.2.840.10008.1.2.5"))
		{
			// RLE compressed data encapsulation
			dataSetEncoding = EncodingExplicit;
			dataSetEndianType = LittleEndianType;
			isEncapsulatedData = true;
		}
		else DIContentErrorException.createAndThrow(filePos, "Transfer syntax is not supported!");
	}

	private void skipBytes(long byteNum) throws IOException
	{
		long len = byteNum;
		while(len > 0)
		{
			long skipped = bis.skip(len);
			len -= skipped;
		}
	}

}
