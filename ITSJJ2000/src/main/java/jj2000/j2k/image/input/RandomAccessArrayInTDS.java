package jj2000.j2k.image.input;

import jj2000.j2k.encoder.J2KTDSException;

public class RandomAccessArrayInTDS
{
	private int[] data;
	private long pos;
	private long length;
	private int bytesPerPixel;

	/**
	 *
	 * Maximum 16 bites adatok kezelesere alkalmas
	 * @param data
	 * @param bitDepth
	 */
	public RandomAccessArrayInTDS(int[] data, int bitDepth) throws J2KTDSException
	{
		this.data = data;
		if(bitDepth < 1 || bitDepth > 16) throw new J2KTDSException("RandomAccessArray(): invalid bitDepth (" + bitDepth + ")");
		pos = 0;
		if(bitDepth <= 8)
		{
			bytesPerPixel = 1;
			length = data.length;
		}
		else
		{
			bytesPerPixel = 2;
			length = data.length * 2;
		}
	}

	public void seek(long pos) throws J2KTDSException
	{
		if(pos < 0 || pos > length) throw new J2KTDSException("RandomAccessArray.seek(): invalid pos (pos = " + pos + ", length = " + length + ")");
		this.pos = pos;
	}

	public int read(byte[] b, int off, int len) throws J2KTDSException
	{
		if(b == null) throw new J2KTDSException("RandomAccessArray.read(): b is null");
		if(off < 0 || off >= b.length) throw new J2KTDSException("RandomAccessArray.read(): invalid 'off' (" + off + ", b.length = " + b.length + ")");
		if(len < 0 || len > b.length - off)
			throw new J2KTDSException("RandomAccessArray.read(): invalid 'len' (" + len + ", off = " + off + ", b.length = " + b.length + ")");
		if(len == 0) return 0;

		int i = 0;
		if(bytesPerPixel == 1)
		{
			for(; i < len; i++)
			{
				if(pos == length) break;
				b[off + i] = (byte) (data[(int)pos] & 0xff);
				pos++;
			}
		}
		else	// bytesPerPixel == 2
		{
			for(; i < len; i++)
			{
				if(pos == length) break;
				if(pos % 2 == 0) b[off + i] = (byte)(data[(int)pos / 2] & 0xff);	// paros pos-nal az also byte-ot vesszuk
				else b[off + i] = (byte)((data[(int)pos / 2] >> 8) & 0xff);			// paratlan pos-nal a felso byte-ot (<- LITTLE ENDIAN)
				pos++;
			}
		}

		if(i == 0) return -1;
		else return i;
	}

	public void close()
	{
		// nem csinal semmit
	}
}
