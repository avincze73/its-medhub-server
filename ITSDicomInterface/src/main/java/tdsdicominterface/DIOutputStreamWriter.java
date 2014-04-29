package tdsdicominterface;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import tdsdicominterface.exception.DIInternalErrorException;

public class DIOutputStreamWriter
{
	private OutputStream out;
	private int endianType;

	public DIOutputStreamWriter(int endianType)
	{
		out = new ByteArrayOutputStream();
		this.endianType = endianType;
	}

	public DIOutputStreamWriter(int endianType, BufferedOutputStream bos)
	{
		out = bos;
		this.endianType = endianType;
	}

	/**
	 * Egy byte kiirasa
	 * @param b
	 * @return
	 */
	public int writeByte(int b) throws IOException
	{
		out.write(b);
		return 1;
	}

	/**
	 * Integer ertek kiirasa 2 byte-on.
	 * @param value
	 * @return
	 */
	public int writeInt(int value) throws IOException
	{
		writeInteger((long)value, 2);
		return 2;
	}

	/**
	 * Integer ertek kiirasa 4 byte-on.
	 * @param value
	 * @return
	 */
	public int writeLong(long value) throws IOException
	{
		writeInteger(value, 4);
		return 4;
	}

	/**
	 * Integer ertek kiirasa. A byteNum max. 4 lehet.
	 * @param value
	 * @param byteNum
	 */
	private void writeInteger(long value, int byteNum) throws IOException
	{
		long val = value;
		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < byteNum; i++)
			{
				out.write((int)(val & 0xffL));
				val = val >> 8;
			}
		}
		else		// big endian type
		{
			val = val << ((5 - byteNum) * 8);
			for(int i = 0; i < byteNum; i++)
			{
				out.write((int)((val >> 32) & 0xffL));
				val = val << 8;
			}
		}
	}

	public int writeFloat(float value)
	{
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		if(endianType == TransferSyntax.LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		bbuf.putFloat(value);
		byte[] buf = new byte[4];
		bbuf.position(0);
		bbuf.get(buf);
		return writeByteArray(buf);
	}

	public int writeDouble(double value)
	{
		ByteBuffer bbuf = ByteBuffer.allocate(8);
		if(endianType == TransferSyntax.LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		bbuf.putDouble(value);
		byte[] buf = new byte[8];
		bbuf.position(0);
		bbuf.get(buf);
		return writeByteArray(buf);
	}

	/**
	 * String kiirasa. Ha az evenBytes = true, akkor az eredmeny string hossza paros szam lesz, szukseg
	 * eseten pulsz egy szokozzel a vegen.
	 * @param value
	 * @param evenBytes
	 * @return
	 */
	public int writeString(String value, boolean evenBytes) throws IOException
	{
		byte[] buf = value.getBytes();
		int len = buf.length;
		try
		{
			out.write(buf);
		}
		catch (IOException ex)
		{
			throw new DIInternalErrorException(ex);
		}
		if(evenBytes && len % 2 == 1)
		{
			out.write(0);
			len++;
		}
		return len;
	}

	/**
	 * A string length hosszu lesz. Ha a tartalom roviebb, akkor szokozzel toltodik fel a maradek hely.
	 * @param value
	 * @param length
	 * @return
	 */
	public int writeString(String value, int length)
	{
		byte[] buf = value.getBytes();
		if(buf.length > length) throw new DIInternalErrorException("String is longer than the specified length. String = "
				+ value + ", spec. length = " + length);
		try
		{
			out.write(buf);
			for(int i = buf.length; i < length; i++) out.write(' ');
		}
		catch (IOException ex)
		{
			throw new DIInternalErrorException(ex);
		}
		return length;
	}

	public int writeByteArray(byte[] value)
	{
		try
		{
			out.write(value);
			return value.length;
		}
		catch (IOException ex)
		{
			throw new DIInternalErrorException("");
		}
	}

	public int writeByteArray(byte[] value, int offs, int len) throws IOException
	{
		out.write(value, offs, len);
		return len;
	}

	/**
	 * 2 byte-os word-oket tartalmazo tomb kiirasa.
	 * @param value
	 * @return
	 */
	public int writeWordArray(short[] value) throws IOException
	{
		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < value.length; i++)
			{
				writeByte(value[i] & 0x00ff);
				writeByte((value[i] >> 8) & 0x00ff);
			}
		}
		else	// big endian
		{
			for(int i = 0; i < value.length; i++)
			{
				writeByte((value[i] >> 8) & 0x00ff);
				writeByte(value[i] & 0x00ff);
			}
		}

		return value.length * 2;
	}

	/**
	 * 4 byte-os word-oket tartalmazo tomb kiirasa.
	 * @param value
	 * @return
	 */
	public int writeDoubleWordArray(int[] value) throws IOException
	{
		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < value.length; i++)
			{
				writeByte(value[i] & 0x00ff);
				writeByte((value[i] >> 8) & 0x00ff);
				writeByte((value[i] >> 16) & 0x00ff);
				writeByte((value[i] >> 24) & 0x00ff);
			}
		}
		else	// big endian
		{
			for(int i = 0; i < value.length; i++)
			{
				writeByte((value[i] >> 24) & 0x00ff);
				writeByte((value[i] >> 16) & 0x00ff);
				writeByte((value[i] >> 8) & 0x00ff);
				writeByte(value[i] & 0x00ff);
			}
		}

		return value.length * 4;
	}

	public void flushAndClose() throws IOException
	{
		out.flush();
		out.close();
	}

	public byte[] getData()
	{
		if(out instanceof ByteArrayOutputStream)
			return ((ByteArrayOutputStream)out).toByteArray();
		else
			throw new DIInternalErrorException("DIOutputStreamWriter.getData(): output stream is not ByteArrayOutputStream!");
	}
}
