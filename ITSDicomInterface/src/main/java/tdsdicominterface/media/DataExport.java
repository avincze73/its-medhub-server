package tdsdicominterface.media;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import tdsdicominterface.DICoder;
import tdsdicominterface.DIOutputStreamWriter;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.exception.DIException;

public class DataExport
{
	public DataExport()
	{
	}

	public void exportToFile(DicomDataSet ds, String aeTitle) throws DIException
	{
		try
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(ds.getFilePath())));
			DIOutputStreamWriter out = new DIOutputStreamWriter(TransferSyntax.LittleEndianType, bos);

			// 128 byte preamble
			byte[] preamble = new byte[128];
			out.writeByteArray(preamble);

			// DICM
			out.writeString("DICM", false);

			// Meta elements
			//--------------
			// A Standard szerint a file meta adat resznek Explicit VR Little Endian kodolassal kell keszulnie.
			DIOutputStreamWriter meta = new DIOutputStreamWriter(TransferSyntax.LittleEndianType);		// Az elemeket eloszor egy ByteArray-be irjuk
			DICoder coder = new DICoder(new TransferSyntax(TransferSyntax.ExplicitVRLittleEndian));
			// File meta information version
			byte[] fmiv = {0x00, 0x01};
			DataElement de = new DataElement(0x0002, 0x0001, fmiv);
			coder.writeDataElement(de, meta);
			// Media storage SOP class UID
			de = new DataElement(0x0002, 0x0002, ds.getString(0x0008, 0x0016));
			coder.writeDataElement(de, meta);
			// Media storage SOP instance UID
			de = new DataElement(0x0002, 0x0003, ds.getString(0x0008, 0x0018));
			coder.writeDataElement(de, meta);
			// transfer syntax UID
			de = new DataElement(0x0002, 0x0010, ds.getTransferSyntax());
			coder.writeDataElement(de, meta);
			// Implementation class UID
			de = new DataElement(0x0002, 0x0012, "1.2.3.4");	// TODO
			coder.writeDataElement(de, meta);
			// Implementation version name
			de = new DataElement(0x0002, 0x0013, "1.1");		// TODO  (bar ez nem kotelezo elem)
			coder.writeDataElement(de, meta);
			// Source AET
			de = new DataElement(0x0002, 0x0016, aeTitle);
			coder.writeDataElement(de, meta);
			// Private information creator UID-t es Private information-t nem teszunk bele

			// group length
			long[] gl = new long[1];
			gl[0] = meta.getData().length;
			de = new DataElement(0x0002, 0x0000, gl);
			coder.writeDataElement(de, out);

			// tobbi meta elem
			out.writeByteArray(meta.getData());

			// Data elements
			//--------------
			coder = new DICoder(new TransferSyntax(ds.getTransferSyntax()));	// a data set-et mar a parameterkent megadott transfer syntax-szal
																				// kell kiirni!
			for(int i = 0; i < ds.getDataElements().size(); i++)
			{
				coder.writeDataElement(ds.getDataElements().get(i), out);
			}

			out.flushAndClose();
		}
		catch(FileNotFoundException ex)
		{
			DIException.createAndThrow(ex, "ERR_5003");
		}
		catch(IOException ex)
		{
			DIException.createAndThrow(ex, "ERR_5002");
		}
	}
}
