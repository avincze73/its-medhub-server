package jj2000.j2k.util;

import jj2000.j2k.encoder.J2KTDSException;

public class RandomAccessByteArrayTDS
{
	private byte[] data;
	private long endPtr;	// a tenyleges adatok vege a data-n belul
	private long pos;

	/**
	 * RandomAccessByteArrayTDS instance letrehozasa.
	 * @param outData
	 * @param bufInitSize outData = null eseten ekkora meretu byte tombot hoz letre
	 */
	public RandomAccessByteArrayTDS(byte[] outData, int bufInitSize)
	{
		if(outData != null)
		{
			this.data = outData;
			endPtr = data.length;
		}
		else
		{
			this.data = new byte[bufInitSize];
			endPtr = 0;
		}
		pos = 0;
	}

	public long length()
	{
		return endPtr;
	}

	public void seek(long offs) throws J2KTDSException
	{
		if(offs < 0 || offs > endPtr) throw new J2KTDSException("RandomAccessByteArrayTDS.seek(): invalid offs (offs = " + offs + ", length = " + endPtr + ")");
		this.pos = offs;
	}

	public int read(byte[] b, int off, int len) throws J2KTDSException
	{
		if(b == null) throw new J2KTDSException("RandomAccessByteArrayTDS.read(): b is null");
		if(off < 0 || off >= b.length)
			throw new J2KTDSException("RandomAccessByteArrayTDS.read(): invalid 'off' (" + off + ", b.length = " + b.length + ")");
		if(len < 0 || len > b.length - off)
			throw new J2KTDSException("RandomAccessByteArrayTDS.read(): invalid 'len' (" + len + ", off = " + off + ", b.length = " + b.length + ")");
		if(len == 0) return 0;

		int i = 0;
		for(; i < len; i++)
		{
			if(pos == endPtr) break;
			b[off + i] = data[(int)pos];
			pos++;
		}

		if(i == 0) return -1;
		else return i;
	}

	public void write(byte[] b, int off, int len) throws J2KTDSException
	{
		if(b == null) throw new J2KTDSException("RandomAccessByteArrayTDS.write(): b is null");
		if(off < 0 || off >= b.length)
			throw new J2KTDSException("RandomAccessByteArrayTDS.write(): invalid 'off' (" + off + ", b.length = " + b.length + ")");
		if(len < 0 || len > b.length - off)
			throw new J2KTDSException("RandomAccessByteArrayTDS.write(): invalid 'len' (" + len + ", off = " + off + ", b.length = " + b.length + ")");
		if(len == 0) return;

		int i = 0;
		for(; i < len; i++)
		{
			if(pos == endPtr)	// ha a file vege pozicion allunk
			{
				endPtr++;	// a file veget kitoljuk eggyel
				if(endPtr > data.length)	// ha mar nincs tobb hely a data tombben
				{
					// data tomb kiterjesztese
					increaseDataSize();
				}
			}
			data[(int)pos] = (byte) (b[off + i] & 0xff);
			pos++;
		}
	}

	private void increaseDataSize()
	{
		// az uj data size az eredeti 1.5 szorose lesz
		int newSize = (int) ((float) data.length * 1.5f);
		byte[] buf = new byte[newSize];
		System.arraycopy(data, 0, buf, 0, data.length);
		data = buf;
	}

	public void close()
	{
		// nem csinal semmit
	}

	public OutDataTDS getOutData()
	{
		OutDataTDS od = new OutDataTDS();
		od.data = data;
		od.endPtr = (int) endPtr;
		return od;

//		byte[] buf = new byte[(int)endPtr];
//		System.arraycopy(data, 0, buf, 0, (int)endPtr);
//		return buf;
	}
}
