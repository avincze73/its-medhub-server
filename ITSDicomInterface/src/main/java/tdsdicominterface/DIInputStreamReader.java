package tdsdicominterface;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import tdsdicominterface.exception.DIInternalErrorException;

public class DIInputStreamReader
{
	private int endianType;
	private InputStream in;
	private long position;
	private long markedPosition;

	/**
	 * A beolvasas a megadott BufferedInputStream-bol fog tortenni.
	 * @param endianType
	 * @param in
	 */
	public DIInputStreamReader(int endianType, BufferedInputStream in)
	{
		this.endianType = endianType;
		this.in = in;
		position = 0;
		markedPosition = -1;
	}

	/**
	 * A beolvasas a megadott byte tombbol fog tortenni.
	 * @param endianType
	 * @param data
	 */
	public DIInputStreamReader(int endianType, byte[] data)
	{
		this.endianType = endianType;
		this.in = new ByteArrayInputStream(data);
		position = 0;
		markedPosition = -1;
	}

	//--------------------------------------------------

	public int readByte() throws IOException
	{
		int b = in.read();
		if(b == -1) throw new EOFException();
		position += 1;
		return b;
	}

	public String readString(long length) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		for(long i = 0; i < length; i++)
		{
			int c = in.read();
			sb.append((char)c);
		}
		// A trailing blank space levagasa
		for(int i = (int)length - 1; i >= 0; i--)
		{
			if(sb.charAt(i) == 0 || sb.charAt(i) == ' ') sb.deleteCharAt(i);
			else break;
		}
		position += length;
		return sb.toString();
	}

	public int readInt() throws IOException
	{
		return (int)readInteger(2, true);
	}

	public int readIntU() throws IOException
	{
		return (int)readInteger(2, false);
	}

	public long readLong() throws IOException
	{
		return readInteger(4, true);
	}

	public long readLongU() throws IOException
	{
		return readInteger(4, false);
	}

	private long readInteger(int byteNum, boolean isSigned) throws IOException
	{
		long val = 0;
		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < byteNum; i++)
			{
				int b = in.read();
				if(b == -1) throw new EOFException();
				val += (long)b << (8 * i);
			}
		}
		else		// big endian type
		{
			for(int i = byteNum - 1; i >= 0; i--)
			{
				int b = in.read();
				if(b == -1) throw new EOFException();
				val += (long)b << (8 * i);
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

		position += byteNum;
		return val;
	}

	public double readDouble() throws IOException
	{
		ByteBuffer bbuf = ByteBuffer.allocate(8);
		byte[] buf = new byte[8];
		int ret = in.read(buf);
		if(ret == -1) throw new EOFException();
		bbuf.put(buf);
		if(endianType == TransferSyntax.LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		position += 8;
		return bbuf.getDouble(0);
	}

	public float readFloat() throws IOException
	{
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		byte[] buf = new byte[4];
		int ret = in.read(buf);
		if(ret == -1) throw new EOFException();
		bbuf.put(buf);
		if(endianType == TransferSyntax.LittleEndianType) bbuf.order(ByteOrder.LITTLE_ENDIAN);
		else bbuf.order(ByteOrder.BIG_ENDIAN);
		position += 4;
		return bbuf.getFloat(0);
	}

	public byte[] readByteArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readByteArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readByteArray: length = 0xffffffffL !");

		byte[] buf = new byte[(int)length];
		int len = (int) length;
		int off = 0;
		while(len > 0)
		{
			int rlen = in.read(buf, off, len);
			if(rlen == -1) throw new EOFException();
			off += rlen;
			len = (int) (length - off);
		}
		position += length;
		return buf;
		// Megjegyzes: Azt nem tudom eldonteni, hogy ha az utolso byte = 0, akkor az az eredeti byte tomb resze,
		// vagy a paros hossz miatt lett hozzabiggyesztve.
	}

	public short[] readWordArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readWordArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readWordArray: length = 0xffffffffL !");
		else if(length % 2 != 0) throw new DIInternalErrorException("readWordArray: length is odd = " + length + " !");
		short[] buf = new short[(int)length / 2];

		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < length / 2; i++)
			{
				int b1 = in.read();
				int b2 = in.read();
				if(b1 == -1 || b2 == -1) throw new EOFException();
				buf[i] = (short)((b2 << 8) + b1);
			}
		}
		else	// big endian
		{
			for(int i = 0; i < length / 2; i++)
			{
				int b1 = in.read();
				int b2 = in.read();
				if(b1 == -1 || b2 == -1) throw new EOFException();
				buf[i] = (short)((b1 << 8) + b2);
			}
		}

		position += length;
		return buf;
	}

	public int[] readDoubleWordArray(long length) throws IOException
	{
		if(length == 0) return null;
		else if(length < 0) throw new DIInternalErrorException("readDoubleWordArray: length = " + length + " !");
		else if(length == 0xffffffffL) throw new DIInternalErrorException("readDoubleWordArray: length = 0xffffffffL !");
		else if(length % 4 != 0) throw new DIInternalErrorException("readDoubleWordArray: length does not fit for double word array = " + length + " !");
		int[] buf = new int[(int)length / 4];

		if(endianType == TransferSyntax.LittleEndianType)
		{
			for(int i = 0; i < length / 4; i++)
			{
				int b1 = in.read();
				int b2 = in.read();
				int b3 = in.read();
				int b4 = in.read();
				if(b1 == -1 || b2 == -1 || b3 == -1 || b4 == -1) throw new EOFException();
				buf[i] = (b4 << 24) + (b3 << 16) + (b2 << 8) + b1;
			}
		}
		else	// big endian
		{
			for(int i = 0; i < length / 4; i++)
			{
				int b1 = in.read();
				int b2 = in.read();
				int b3 = in.read();
				int b4 = in.read();
				if(b1 == -1 || b2 == -1 || b3 == -1 || b4 == -1) throw new EOFException();
				buf[i] = (b1 << 24) + (b2 << 16) + (b3 << 8) + b4;
			}
		}

		position += length;
		return buf;
	}

	public void skipBytes(long byteNum) throws IOException
	{
		long len = byteNum;
		while(len > 0)
		{
			long skipped = in.skip(len);
			len -= skipped;
		}
		position += byteNum;
	}

	public void mark(int readlimit)
	{
		in.mark(readlimit);
		markedPosition = position;
	}

	public void reset() throws IOException
	{
		in.reset();
		position = markedPosition;
	}

	//----------------------------------------------------------------------------------------------------

	/**
	 * @return the position
	 */ public long getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */ public void setPosition(long position) {
		this.position = position;
	}
}
