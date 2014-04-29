package tdsdicomimage;

import icc.ICCProfileException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferUShort;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import jj2000.j2k.decoder.DecoderTDS;
import jj2000.j2k.encoder.EncoderTDS;
import jj2000.j2k.encoder.J2KTDSException;
import jj2000.j2k.image.output.OutputImgTDS;
import jj2000.j2k.util.OutDataTDS;
import tdsdicomimage.exception.DImgException;
import tdsdicomimage.exception.DImgInternalErrorException;
import tdsdicominterface.DValue;
import tdsdicominterface.DataElement;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomItem;
import tdsdicominterface.EncapsulatedData;
import tdsdicominterface.TransferSyntax;
import tdsdicominterface.VR;
import tdsdicominterface.exception.DIException;

/**
 * DICOM image-et reprezentalo osztaly.<br>
 * 1. Segitsegevel letre lehet hozni a megadott Dicom data set-bol kepernyon megjelenitheto BufferedImage-et. A loadImage()
 * metodus meghivasa utan barmennyiszer meg lehet hivni a getBufferedImage() metodust, amely a megadott window center, width
 * es VOI LUT parameterek alapjan letrehozza a megjelenitheto kepet BufferedImage formaban.<br>
 * 2. A repackImageToNewDataSet() metodus hasznalataval a megadott Dicom data set-bol uj data set hozhato letre a benne talalhato
 * image ujracsomagolasaval. Az uj image tarolasi modja lehet Dicom nativ, JPEG2000 lossless es JPEG2000 lossy beallithato
 * tomoritesi mertekkel. Az uj image merete (szelesseg, magassag) csokkentheto a megadott mertekben, vagy meretre.
 */
public class DicomImage
{
	public static final int Format_Native = 1;
	public static final int Format_JPEG2000 = 2;
	private static final int DefaultHistogramSize = 256;

	private DicomDataSet dataSet;
	private float[] buf;				// a pixel adatokat tartalmazo buffer
	private boolean isByteBuf;		// true: 8 bites adatok, false: 16 bites adatok
	private int histogramSize;
	private int[] histogram;
	private float minValue;
	private float maxValue;

	private int[] dispBuf;			// display buffer for VOI LUT and Presentation LUT processing
	private short[] imgBuf;			// buffer for ImageBuffer object

	private int rows;				// sorok szama  (DY)
	private int columns;			// sorok szama  (DX)
	private int samplesPerPixel;
	private int bitsAllocated;
	private int bitsStored;
	private int highBit;
	private int pixelRepresentation;
	private int realBitDepth;		// JPEG2000 tomoritesu adat eseten a dekodolaskor kiderulo igazi bitDepth

	private boolean isLoaded;


	/**
	 * DicomImage letrehozasa.
	 */
	public DicomImage()
	{
		dataSet = null;
		buf = null;
		isByteBuf = false;
		isLoaded = false;
		histogramSize = DefaultHistogramSize;
	}

	/**
	 * Image megjelenites elott az image betoltese.
	 * <p>Ezt kovetoen le lehet generaltatni a megjeleniteshez szukseges BufferedImage objektumot a megfelelo windowing
	 * es VOI LUT beallitasokkal (a getBufferedImage() metodussal), amelyet tetszoleges szamban meg lehet hivni ezek utan.
	 * Peldaul ha a felhasznalo a kliensben atallitja a windowing parametereket, akkor ujbol le lehet generalni a
	 * megfelelo BufferedImage-et.
	 * <p>A BufferedImage generalasa elott lehetoseg van lekerdezni a data set-ben tarolt default
	 * window center es window width adatokat (getDefaultWindowCentersAndWidths() metodussal), valamint a
	 * VOI LUT adatokat (getDefaultVOILUTNames() metodussal).
	 * @param dataSet
	 * @throws DImgException
	 */
	public void loadImage(DicomDataSet dataSet) throws DImgException
	{
		try
		{
			this.dataSet = dataSet;

			// Eredeti gepi image betoltese
			loadMachineImage();

			// Grayscale pipeline eleje

			// Modality LUT
			processModalityLUT();

			// Histogram
			createHistogram();

			// Elokeszites a VOI LUT-nak
			dispBuf = new int[buf.length];

			// Elokeszites az ImageBuffer letrehozashoz
			imgBuf = new short[buf.length];

			isLoaded = true;
		}
		catch (DIException ex)
		{
			throw new DImgInternalErrorException(ex);
		}
		catch (IOException ex)
		{
			throw new DImgException("", ex);
		}
	}

	public boolean isLoaded()
	{
		return isLoaded;
	}

	private void loadMachineImage() throws DIException, DImgException, IOException
	{
		samplesPerPixel = dataSet.getFirstInt(0x0028, 0x0002);					// greyscale-nel: 1
		rows = dataSet.getFirstInt(0x0028, 0x0010);								// sorok szama  (DY)
		columns = dataSet.getFirstInt(0x0028, 0x0011);							// oszlopok szama  (DX)
		bitsAllocated = dataSet.getFirstInt(0x0028, 0x0100);					// bit-ek szama egy sample tarolasahoz (8 tobbszorose)
		bitsStored = dataSet.getFirstInt(0x0028, 0x0101);						// bit-ek szama egy sample-ben
		highBit = dataSet.getFirstInt(0x0028, 0x0102);							// a sample-ben a Most Significant Bit
		pixelRepresentation = dataSet.getFirstInt(0x0028, 0x0103);				// 0: unsigned integer, 1: 2-es komplemens
		realBitDepth = bitsStored;

		if(samplesPerPixel != 1)		// Non-greyscale image is not supported.
			DImgException.createAndThrow("DIMGERR_1008");

		TransferSyntax ts = new TransferSyntax(dataSet.getTransferSyntax());
		DValue dvPixelData = dataSet.getValue(0x7fe0, 0x0010);

		buf = null;
		isByteBuf = false;

		// Image adatok beolvasasa a buf-ba
		if(ts.isEncapsulatedData())		// ha encapsulated
		{
			readEncapsulatedPixelData(dvPixelData, ts);
		}
		else		// ha nativ data
		{
			if(dvPixelData.getType() == DValue.BYTEARRAY)
			{
				readPixelDataOB(dvPixelData, bitsStored, bitsAllocated, highBit, pixelRepresentation);
				isByteBuf = true;
			}
			else if(dvPixelData.getType() == DValue.SHORTARRAY)
			{
				readPixelDataOW(dvPixelData, bitsStored, bitsAllocated, highBit, pixelRepresentation);
				isByteBuf = false;
			}
			else DImgException.createAndThrow("DIMGERR_0001");
		}
	}

	/**
	 * Beolvassa es nativ adatot csinal az encapsulated pixel data-bol. A beolvasott adatok a buf valtozoba kerulnek.
	 * Az isByteBuf erteket beallitja: true: 8 bitesek a nativ pixel adatok, false: 16 bitesek.
	 * @param dvPixelData
	 * @throws ICException
	 * @throws IOException
	 */
	private void readEncapsulatedPixelData(DValue dvPixelData, TransferSyntax ts) throws DImgException, IOException
	{
		if(dvPixelData.getType() != DValue.ENCAPSULATEDDATA)
				throw new DImgInternalErrorException("DValue is not encapsulated data!");
		EncapsulatedData encData = (EncapsulatedData) dvPixelData.getValue();
		if(encData.getFrames().size() != 1)
			DImgException.createAndThrow("DIMGERR_1002");
		if(encData.getFrames().get(0).getFragments().size() != 1)
			DImgException.createAndThrow("DIMGERR_1003");

		byte[] imageBytes = encData.getFrames().get(0).getFragments().get(0).getData();

		if(ts.getTransferSyntax().equals(TransferSyntax.JPEG2000)
				|| ts.getTransferSyntax().equals(TransferSyntax.JPEG2000_LosslessOnly))
		{
			// ha JPEG2000
			//--------------------

			// FOR TEST
//			FileOutputStream fos = new FileOutputStream(new File("c:\\Work\\TDS\\prog\\DicomMediaInterfaceClient\\Work\\imgj2k.dat"));
//			fos.write(imageBytes);
//			fos.close();

			DecoderTDS decoder = new DecoderTDS(imageBytes);
			try
			{
				decoder.start();
			}
			catch (J2KTDSException ex)
			{
				throw new DImgException("Error in JJ2000 decoder.", ex);
			}
			catch (ICCProfileException ex)
			{
				throw new DImgException("Error in JJ2000 decoder.", ex);
			}

			OutputImgTDS oimg = decoder.getOutputImg();
			byte[] odata = oimg.data;
			int endPtr = oimg.endPtr;
			realBitDepth = oimg.bitDepth;
			if(oimg.width != columns) throw new DImgException("Width in JPEG2000 images (" + oimg.width +
					") differs from width value in data set (" + columns + ")!");
			if(oimg.height != rows) throw new DImgException("Height in JPEG2000 images (" + oimg.height +
					") differs from height value in data set (" + rows + ")!");

			if(oimg.bitDepth <= 8)
			{
				isByteBuf = true;
				buf = new float[endPtr];
				for(int i = 0; i < endPtr; i++)
					buf[i] = (float) odata[i];
			}
			else
			{
				isByteBuf = false;
				buf = new float[endPtr / 2];
				int p = 0;
				for(int i = 0; i < endPtr; i += 2)
				{
					buf[p] = ((odata[i] << 8) | (odata[i + 1] & 0xff));		// BIG ENDIAN ordering
					p++;
				}
			}
		}
		else if(ts.getTransferSyntax().equals(TransferSyntax.JPEG2000_Multicomponent)
				|| ts.getTransferSyntax().equals(TransferSyntax.JPEG2000_Multicomponent_LosslessOnly))
		{
			DImgException.createAndThrow("DIMGERR_1009");
		}
		else	// ha NEM JPEG2000
		{
			// image uncompress
			BufferedImage image = null;
			try
			{
				image = ImageIO.read(new ByteArrayInputStream(imageBytes));
			}
			catch (IIOException ex)
			{
				String s = ex.getMessage().substring(0, "Unsupported JPEG process".length());
				if("Unsupported JPEG process".equals(s))
					DImgException.createAndThrow("DIMGERR_1004", ex.getMessage());
				else
					throw ex;
			}
			if(image == null)
				DImgException.createAndThrow("DIMGERR_1001");

			// image adat tomb kinyeres
			Raster raster = image.getData();
			int ttype = raster.getTransferType();
			int width = raster.getWidth();
			int height = raster.getHeight();
			buf = new float[width * height];
			if(ttype == DataBuffer.TYPE_BYTE)
			{
				if(raster.getNumBands() == 3)
				{
					int[] pixel = new int[3];
					int i = 0;
					for(int y = 0; y < height; y++)
					{
						for(int x = 0; x < width; x++, i++)
						{
							raster.getPixel(x, y, pixel);
							if(pixel[0] != pixel[1] || pixel[1] != pixel[2])
							{
								buf[i] = (pixel[0] + pixel[1] + pixel[2]) / 3;
							}
							else buf[i] = pixel[0];
						}
					}
				}
				else if(raster.getNumBands() == 1)
				{
					int[] pixel = new int[1];
					int i = 0;
					for(int y = 0; y < height; y++)
					{
						for(int x = 0; x < width; x++, i++)
						{
							raster.getPixel(x, y, pixel);
							buf[i] = pixel[0];
						}
					}
				}
				else DImgException.createAndThrow("DIMGERR_1004", "Unsupported number of pixel bands in case of BYTE transfer type ("
						+ raster.getNumBands() + ").");

				isByteBuf = true;
			}
			else if(ttype == DataBuffer.TYPE_USHORT)
			{
				if(raster.getNumBands() == 1)
				{
					int[] pixel = new int[1];
					int i = 0;
					for(int y = 0; y < height; y++)
					{
						for(int x = 0; x < width; x++, i++)
						{
							raster.getPixel(x, y, pixel);
							buf[i] = pixel[0];
						}
					}
				}
				else DImgException.createAndThrow("DIMGERR_1004", "Unsupported number of pixel bands in case of USHORT transfer type ("
						+ raster.getNumBands() + ").");

				isByteBuf = false;
			}
			else
				DImgException.createAndThrow("DIMGERR_1004", "Unknown image data transfer type (" + ttype + ").");
		}
	}

	private void readPixelDataOB(DValue dvPixelData, int bitsStored, int bitsAllocated, int highBit, int pixelRepresentation)
			throws DImgException
	{
		if(bitsAllocated != 8)
			DImgException.createAndThrow("DIMGERR_0003", bitsAllocated);
		byte[] pixelData = (byte[]) dvPixelData.getValue();
		buf = new float[pixelData.length];
		int nonUsedBits = bitsAllocated - bitsStored;
		byte mask = 0;
		for(int q = 0; q < bitsStored; q++) mask = (byte) ((mask << 1) + 1);

		for(int i = 0; i < pixelData.length; i++)
		{
			byte a = (byte) (pixelData[i] << (7 - highBit));		// eloszor felfele igazitjuk, hogy aztan a lefele tolasnal negativ ertek eseten
																	// huzza magaval az 1-eket
			a = (byte) (a >> nonUsedBits);
			if(pixelRepresentation == 0)		// ha unsigned integer
				a = (byte) (a & mask);
			buf[i] = a;
		}
	}

	private void readPixelDataOW(DValue dvPixelData, int bitsStored, int bitsAllocated, int highBit, int pixelRepresentation)
			throws DImgException
	{
		if(bitsAllocated != 16)
			DImgException.createAndThrow("DIMGERR_0002", bitsAllocated);
		short[] pixelData = (short[]) dvPixelData.getValue();
		buf = new float[pixelData.length];
		int nonUsedBits = bitsAllocated - bitsStored;
		short mask = 0;
		for(int q = 0; q < bitsStored; q++) mask = (short) ((mask << 1) + 1);

		for(int i = 0; i < pixelData.length; i++)
		{
			short a = (short) (pixelData[i] << (15 - highBit));		// eloszor felfele igazitjuk, hogy aztan a lefele tolasnal negativ ertek eseten
																	// huzza magaval az 1-eket
			a = (short) (a >> nonUsedBits);
			if(pixelRepresentation == 0)		// ha unsigned integer
				a = (short) (a & mask);
			buf[i] = a;
		}
	}

	/**
	 * Pixel data konvertalasa a Modality LUT-nak megfeleloen.
	 * Ha a data set-ben nincs Modality LUT informacio, akkor nincs konvertalas.
	 * @param dataSet
	 * @throws DIException
	 * @throws ICException
	 */
	private void processModalityLUT()
			throws DIException, DImgException
	{
		String s = dataSet.getString(0x0028, 0x1052);		// rescaleIntercept
		if(s == null)		// rescaleIntercept nincs
		{
			DValue dv = dataSet.getValue(0x0028, 0x3000);		// Modality LUT sequence
			if(dv != null)
			{
				// Modality LUT sequence in action:
				// "In the case where the Modality LUT Sequence is used, the output range is from 0 to 2n-1 where n is the third value
				// of LUT Descriptor. This range is always unsigned."
				DicomItem item = ((ArrayList<DicomItem>) dv.getValue()).get(0);
				DValue dvLutDescriptor = item.getValue(0x0028, 0x3002);
				int[] lutDescriptor = (int[]) dvLutDescriptor.getValue();

				// LUT size
				int lutSize = lutDescriptor[0];
				if(lutSize < 0)		// ha a LUT descriptor SS tipusu es a LUT size eredetileg 32767-nel nagyobb
				{
					lutSize = 65536 + lutSize;
				}
				else if(lutSize == 0) lutSize = 65536;

				// also ertek a mapping-nel
				int lowValue = lutDescriptor[1];		// US vagy SS

				// Entry size in bits
				int entrySize = lutDescriptor[2];		// 8 vagy 16 lehet
				if(entrySize != 8 && entrySize != 16) DImgException.createAndThrow("DIMGERR_0006", entrySize);

				// LUT data    (mindig unsigned!)
				DValue dvLutData = dataSet.getValue(0x0028, 0x3006);
				short[] lutDataShort = (short[]) dvLutData.getValue();			// ez lehet US vagy OW
				int[] lutData = new int[lutSize];
				if(entrySize == 16 && lutDataShort.length != lutSize)
					DImgException.createAndThrow("DIMGERR_0007", lutDataShort.length, lutSize);
				if(entrySize == 8 && lutDataShort.length != lutSize)
				{
					// ha 8 bites az entry meret es folyamatosan van eltarolva (nem csak word-onkent 1 byte), akkor atalakitas...
					if(lutDataShort.length != lutSize / 2)
						DImgException.createAndThrow("DIMGERR_0008", lutDataShort.length, lutSize);
					for(int i = 0, j = 0; i < lutDataShort.length; i++, j += 2)
					{
						lutData[j] = (lutDataShort[i] & 0x00ff);
						lutData[j + 1] = ((lutDataShort[i] >> 8) & 0x00ff);
					}
				}
				else
				{
					for(int i = 0; i < lutSize; i++)
					{
						lutData[i] = (lutDataShort[i] & 0xffff);
					}
				}

				// konverzio
				int highValue = lowValue + lutSize - 1;
				int lowLutValue = lutData[0];		// LUT elso eleme
				int highLutValue = lutData[lutData.length - 1];		// LUT utolso eleme
				for(int i = 0; i < buf.length; i++)
				{
					if(buf[i] <= lowValue) buf[i] = lowLutValue;
					else if(buf[i] >= highValue) buf[i] = highLutValue;
					else buf[i] = lutData[Math.round(buf[i]) - lowValue];
				}
			}
		}
		else			// rescaleIntercept van
		{
			double rescaleIntercept = Double.parseDouble(s);
			s = dataSet.getString(0x0028, 0x1053);		// rescaleSlope
			double rescaleSlope = Double.parseDouble(s);
//			String rescaleType = dataSet.getString(0x0028, 0x1054);

			if(isByteBuf)
			{
				for(int i = 0; i < buf.length; i++)
				{
					// Output units = m*SV + b
					buf[i] = (float) (rescaleSlope * (double) buf[i] + rescaleIntercept);
				}
			}
			else
			{
				for(int i = 0; i < buf.length; i++)
				{
					// Output units = m*SV + b
					buf[i] = (float) (rescaleSlope * (double) buf[i] + rescaleIntercept);
				}
			}
		}
	}

	/**
	 * A dataSet-ben szereplo VOI LUT sequence(ek) nevenek (neveinek) meghatarozasa. A sequence-ben talalhato LUT explanation mezo erteke
	 * sorszammal kiegeszitve. Elotte a loadImage() metodust meg kell hivni.
	 * @return
	 * @throws DIException
	 */
	public ArrayList<String> getDefaultVOILUTNames() throws DIException
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getDefaultVOILUTNames(): Image is not loaded!");

		ArrayList<String> voilutNames = new ArrayList<String>();

		DValue dvVoilutSequence = dataSet.getValue(0x0028, 0x3010);		// VOI LUT sequence
		if(dvVoilutSequence != null)
		{
			ArrayList<DicomItem> dicomItems = ((ArrayList<DicomItem>) dvVoilutSequence.getValue());
			for(int i = 0; i < dicomItems.size(); i++)
			{
				String lutExplanation = dicomItems.get(i).getString(0x0028, 0x3002);
				voilutNames.add(Integer.toString(i + 1) + " " + lutExplanation);
			}
		}
		return voilutNames;
	}

	/**
	 * A dataSet-ben szereplo window center es window width ertekpar(ok) es azok neveinek (explanation) meghatarozasa.
	 * Elotte a loadImage() metodust meg kell hivni.
	 * @return
	 * @throws DIException
	 */
	public ArrayList<ImageWindow> getDefaultWindowCentersAndWidths() throws DIException
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getDefaultWindowCentersAndWidths(): Image is not loaded!");

		ArrayList<ImageWindow> windows = new ArrayList<ImageWindow>();

		String wcsStr = dataSet.getString(0x0028, 0x1050);		// window centers
		if(wcsStr == null) wcsStr = "";
		String wwsStr = dataSet.getString(0x0028, 0x1051);		// window widths
		if(wwsStr == null) wwsStr = "";
		ArrayList<Double> wcs = convertStringToDoubleList(wcsStr);
		ArrayList<Double> wws = convertStringToDoubleList(wwsStr);
		String s = dataSet.getString(0x0028, 0x1055);	// Window Center & Width Explanation
		if(s == null) s = "";
		String[] explanations = s.split("\\\\");
		for(int i = 0; i < wcs.size(); i++)
		{
			if(explanations.length > i)		// nem biztos hogy mindegyik Window Center & Width paroshoz van explanation
				windows.add(new ImageWindow(Integer.toString(i + 1) + " " + explanations[i], wcs.get(i), wws.get(i)));
			else
				windows.add(new ImageWindow(Integer.toString(i + 1), wcs.get(i), wws.get(i)));
		}

		return windows;
	}

	/**
	 * A megadott string-et konvertalja double listava. Null eseten ures lista az eredmeny.
	 * @param s backslash-sel elvalasztott decimal string-eket tartalmazo string.
	 * @return Double lista.
	 */
	private ArrayList<Double> convertStringToDoubleList(String s)
	{
		ArrayList<Double> list = new ArrayList<Double>();

		if(s != null && !s.isEmpty())
		{
			String[] vals = s.split("\\\\");
			for(int i = 0; i < vals.length; i++)
			{
				list.add(new Double(vals[i]));
			}
		}
		return list;
	}

	/**
	 * Pixel ertek meghatarozasa az x, y altal definialt pontban.
	 * A pixel erteke a modality LUT konverzio utani ertek.
	 * @param x
	 * @param y
	 * @return a pixel erteke (lehet negativ is).
	 * @throws DImgException
	 */
	public float getPixelValue(int x, int y) throws DImgException
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getPixelValue(): Image is not loaded!");
		if(x >= columns || y >= rows)
			DImgException.createAndThrow("DIMGERR_2003", x, y);
		
		return buf[y * columns + x];
	}

	/**
	 * BufferedImage letrehozasa a megadott parameterek alapjan. (Elotte a loadImage() metodust meg kell hivni.)<p>
	 * Az elso negy parameter hasznalata:<p>
	 * 1. Csak a windowCenter es windowWidth van megadva, a tobbi parameter null: a megadott window ertekekkel jon letre a kep.<p>
	 * 2. Csak a defaultWindowNum van megadva, a tobbi parameter null: a dataSet-ben talalhato default window ertekek kozul a megadott
	 * sorszamu kivalasztasa es a kep ezalapjan torteno letrehozasa.<p>
	 * 3. Csak a voilutNum van megadva, a tobbi parameter null: a dataSet-ben talalhato default VOI LUT-ok kozul a megadott
	 * sorszamu kivalasztasa es a kep ezalapjan torteno letrehozasa.
	 * @param windowCenter
	 * @param windowWidth
	 * @param defaultWindowNum tobberteku window center es window width eseten a valasztott par sorszama
	 * @param voilutNum tobberteku VOI LUT sequence eseten a valasztott VOI LUT sorszama
	 * @param dispRect az eredeti image megjeleniteni kivant resze. Ha null, akkor a teljes image kerul megjelenitesre.
	 * @param finalSize a vegleges megjelenitendo meret. Ha null, akkor a vegleges meret a rows es columns attributumoknak megfelelo lesz
	 * @param inverted ha true, akkor a kep negativja lesz az eredmeny
	 * @return a megjelenitheto BufferedImage
	 * @throws DIException
	 * @throws DImgException
	 */
	public BufferedImage getBufferedImage(Double windowCenter, Double windowWidth, Integer defaultWindowNum, Integer voilutNum,
			Rectangle dispRect, Dimension finalSize, boolean inverted)
			throws DIException, DImgException
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getBufferedImage(): Image is not loaded!");

		int outputBitCount = 0;		// a konverziok utani ertekek hany bitesek

		// tenyleges megjelenitesi parameterek meghatarozosa
		//--------------------------------------------------
		double wndCenter = 0;
		double wndWidth = 0;
		Integer voilut = null;
		boolean isDisplayedByWindow = false;

		if(windowCenter != null && windowWidth != null)
		{
			wndCenter = windowCenter;
			wndWidth = windowWidth;
			isDisplayedByWindow = true;
		}
		else if(defaultWindowNum != null)		// ha az egyik default window center & width szerint kell megjeleniteni
		{
			String wcStr = dataSet.getString(0x0028, 0x1050);		// window center
			ArrayList<Double> wcs = convertStringToDoubleList(wcStr);
			String wwStr = dataSet.getString(0x0028, 0x1051);		// window width
			ArrayList<Double> wws = convertStringToDoubleList(wwStr);

			if(wcs.size() != wws.size())
				DImgException.createAndThrow("DIMGERR_0012", wcs.size(), wws.size());
			if(defaultWindowNum.intValue() >= wcs.size() || defaultWindowNum.intValue() < 0)
				throw new DImgInternalErrorException("DicomImage.getBufferedImage(): defaultWindowNum is out of range (" + defaultWindowNum + ")!");

			wndCenter = wcs.get(defaultWindowNum);
			wndWidth = wws.get(defaultWindowNum);
			isDisplayedByWindow = true;
		}
		else if(voilutNum != null)
		{
			voilut = voilutNum;
			isDisplayedByWindow = false;
		}
		else		// akkor a default megjelenites tortenik
		{
			String wcStr = dataSet.getString(0x0028, 0x1050);		// window center
			String wwStr = dataSet.getString(0x0028, 0x1051);		// window width
			DValue dvVoilutSequence = dataSet.getValue(0x0028, 0x3010);		// VOI LUT sequence

			if(wcStr != null)	// ha a window center es width meg van adva
			{
				ArrayList<Double> wcs = convertStringToDoubleList(wcStr);
				ArrayList<Double> wws = convertStringToDoubleList(wwStr);
				if(wcs.size() != wws.size())
					DImgException.createAndThrow("DIMGERR_0012", wcs.size(), wws.size());
				wndCenter = wcs.get(0);
				wndWidth = wws.get(0);
				isDisplayedByWindow = true;
			}
			else if(dvVoilutSequence != null)
			{
				voilut = 0;
				isDisplayedByWindow = false;
			}
			else	// ha se window center-width, se VOILUT sequence nincs
			{
				wndWidth = maxValue - minValue;
				wndCenter = (minValue + maxValue) / 2;
				isDisplayedByWindow = true;
			}
		}

		// VOILUT
		//==============================

		// ellenorzes, hogy nem log-e ki a keret az eredeti kepbol
		if(dispRect != null && (dispRect.x + dispRect.width > columns || dispRect.y + dispRect.height > rows))
			DImgException.createAndThrow("DIMGERR_2001", columns, rows, dispRect.x, dispRect.y, dispRect.width, dispRect.height);

		Rectangle rect = dispRect;
		if(rect == null)
		{
			rect = new Rectangle(0, 0, columns, rows);
		}
		outputBitCount = processVOILUT(isDisplayedByWindow, wndCenter, wndWidth, voilut, rect);

		// Presentation LUT
		//==============================

		processPresentationLUT(rect, inverted);

		// buffered image letrehozasa
		//==============================

		return createBufferedImageForDisplay(dispBuf, imgBuf, outputBitCount, rect, finalSize);
	}

	public BufferedImage getBufferedImage(Dimension size) throws DIException, DImgException
	{
		return getBufferedImage(null, null, null, null, null, size, false);
	}

	/**
	 * BufferedImage letrehozasa az srcBuf buffer-bol megjelenitesi celra.
	 * @param srcBuf az image adat
	 * @param internalBuf egy buffer a metodusnak belso hasznalatra. Azert kell megadni, hogy erre allando tarteruletet
	 * hasznalhasson a metodus.
	 * @param outputBitCount az image adathoz allokalando bitek szama: 8 vagy 16
	 * @param dispRect az eredeti image megjeleniteni kivant resze
	 * @param finalSize a vegleges megjelenitendo meret. Ha null, akkor a vegleges meret a rows es columns attributumoknak megfelelo lesz
	 * @return a megjelenitheto BufferedImage
	 */
	private BufferedImage createBufferedImageForDisplay(int[] srcBuf, short[] internalBuf, int outputBitCount, Rectangle dispRect, Dimension finalSize)
			throws DImgException
	{
		double zoom = 1;

		if(finalSize != null && (finalSize.width != dispRect.width || finalSize.height != dispRect.height))
		{
			// mertaranyok ellenorzese
			double xr = (double)finalSize.width / (double)dispRect.width;
			double yr = (double)finalSize.height / (double)dispRect.height;
			zoom = (xr + yr) / 2;		// az eredeti nagyitasnak a ketto atlagat tekintjuk

			// ha az X es Y iranyu nagyitas nem egyenlo, akkor hiba van
			if(Math.round((double)dispRect.width * zoom) != finalSize.width
					|| Math.round((double)dispRect.height * zoom) != finalSize.height)
				DImgException.createAndThrow("DIMGERR_2002", dispRect.width, dispRect.height, finalSize.width, finalSize.height);
		}

		BufferedImage image = null;

		if(zoom == 1)		// meret megtartasa eseten
		{
			image = createBufferedImage(srcBuf, internalBuf, outputBitCount, new Dimension(dispRect.width, dispRect.height));
		}
		else if(zoom > 1)		// nagyitas eseten
		{
			image = createBufferedImage(srcBuf, internalBuf, outputBitCount, new Dimension(dispRect.width, dispRect.height));

			BufferedImage bi = new BufferedImage(finalSize.width, finalSize.height, BufferedImage.TYPE_USHORT_GRAY);
			Graphics2D g2 = (Graphics2D) bi.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g2.drawImage(image, 0, 0, finalSize.width, finalSize.height, null);
			image = bi;
			g2.dispose();
		}
		else		// kicsinyites eseten
		{
			int[] shrinkedImg = ImageResampler.imageShrink(srcBuf, dispRect.width, dispRect.height, finalSize.width, finalSize.height);
			image = createBufferedImage(shrinkedImg, internalBuf, outputBitCount, new Dimension(finalSize.width, finalSize.height));
		}

		return image;
	}

	private BufferedImage createBufferedImage(int[] srcBuf, short[] internalBuf, int outputBitCount, Dimension size)
	{
		BufferedImage image = null;
		int[] bandOffsets = new int[1];
		bandOffsets[0] = 0;
		Raster raster = null;

		int dataLength = size.width * size.height;
		if(outputBitCount == 8)
		{
			for(int i = 0; i < dataLength; i++) internalBuf[i] = (short) ((srcBuf[i] << 8) & 0xffff);
		}
		else if(outputBitCount == 16)
		{
			for(int i = 0; i < dataLength; i++) internalBuf[i] = (short) (srcBuf[i] & 0xffff);
		}
		else throw new DImgInternalErrorException("DicomImage.createBufferedImageForDisplay(): outputBitCount is not supported (" + outputBitCount + ")!");

		DataBuffer dataBuf = new DataBufferUShort(internalBuf, dataLength);
		raster = Raster.createInterleavedRaster(dataBuf, size.width, size.height, size.width, 1, bandOffsets, null);
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_USHORT_GRAY);
		image.setData(raster);

		return image;
	}

	private BufferedImage createBufferedImage(float[] srcBuf, int outputBitCount)
	{
		byte[] bufByte = null;
		short[] bufShort = null;

		BufferedImage image = null;
		int[] bandOffsets = new int[1];
		bandOffsets[0] = 0;
		Raster raster = null;

		DataBuffer dataBuf = null;
		if(outputBitCount == 8)
		{
			bufByte = new byte[srcBuf.length];
			for(int i = 0; i < srcBuf.length; i++) bufByte[i] = (byte) (Math.round(srcBuf[i]) & 0xff);
			dataBuf = new DataBufferByte(bufByte, bufByte.length);
			image = new BufferedImage(columns, rows, BufferedImage.TYPE_BYTE_GRAY);
		}
		else if(outputBitCount == 16)
		{
			bufShort = new short[srcBuf.length];
			for(int i = 0; i < srcBuf.length; i++) bufShort[i] = (short) (Math.round(srcBuf[i]) & 0xffff);
			dataBuf = new DataBufferUShort(bufShort, bufShort.length);
			image = new BufferedImage(columns, rows, BufferedImage.TYPE_USHORT_GRAY);
		}
		else throw new DImgInternalErrorException("DicomImage.createBufferedImage(): outputBitCount is not supported (" + outputBitCount + ")!");

		raster = Raster.createInterleavedRaster(dataBuf, columns, rows, columns, 1, bandOffsets, null);
		image.setData(raster);

		return image;
	}

	private int processVOILUT(boolean isDisplayedByWindow, double wndCenter, double wndWidth, Integer voilut,
			Rectangle dispRect) throws DIException, DImgException
	{
		// "If any VOI LUT Table is included by an Image, a Window Width and Window Center or the VOI LUT Table, but not both,
		// may be applied to the Image for display. Inclusion of both indicates that multiple alternative views may be presented."
		//
		// Window eseten:
		// "If multiple values are present, both Attributes shall have the same number of values and shall be considered as pairs.
		// Multiple values indicate that multiple alternative views may be presented."
		//
		// Softcopy VOI LUT nincs hasznalva a feldolgozando IOD-k eseten

		int outputBitCount = 0;

		if(isDisplayedByWindow)	// ha a window center es width alapjan tortenik a megjelenites
		{
			// A transzformacio kimeneti tartomanya: 0 - 65535
			//
			// "Whether the minimum output value is rendered as black or white may depend on the
			// value of Photometric Interpretation (0028,0004) or the presence of a Presentation LUT Module."

			String photometricInterpretation = dataSet.getString(0x0028, 0x0004);		// MONOCHROME1 vagy MONOCHROME2
			if(!"MONOCHROME1".equals(photometricInterpretation) && !"MONOCHROME2".equals(photometricInterpretation))
				DImgException.createAndThrow("DIMGERR_1006", photometricInterpretation);

			String voilutFunction = dataSet.getString(0x0028, 0x1056);		// VOI LUT function
			if(voilutFunction == null || "LINEAR".equals(voilutFunction))
			{
				// ha a VOI LUT function linearis
				int ymin = 0;
				int ymax = 65535;
				double xmin = wndCenter - 0.5 - (wndWidth - 1) / 2;
				double xmax = wndCenter - 0.5 + (wndWidth - 1) / 2;
				double a = wndCenter - 0.5;
				double b = wndWidth - 1;
				double c = (ymax - ymin) + ymin;

				int s = 0;
				int d = 0;
				for(int y = 0; y < dispRect.height; y++)
				{
					s = (dispRect.y + y) * columns + dispRect.x;
					for(int x = 0; x < dispRect.width; x++, s++, d++)
					{
						if(buf[s] <= xmin) dispBuf[d] = ymin;
						else if(buf[s] > xmax) dispBuf[d] = ymax;
						else dispBuf[d] = (int) (((buf[s] - a) / b + 0.5) * c);		// else	y = ((x - (c - 0.5)) / (w-1) + 0.5) * (ymax - ymin)+ ymin
					}
				}
			}
			else if("SIGMOID".equals(voilutFunction))
			{
				// ha a VOI LUT function SIGMOID
				//
				// A transzformacio kimeneti tartomanya: 0 - 65535

				int s = 0;
				int d = 0;
				for(int y = 0; y < dispRect.height; y++)
				{
					s = (dispRect.y + y) * columns + dispRect.x;
					for(int x = 0; x < dispRect.width; x++, s++, d++)
					{
						dispBuf[d] = (int) (65535 / (1 + Math.exp((-4) * (buf[s] - wndCenter) / wndWidth)));
					}
				}
			}
			else DImgException.createAndThrow("DIMGERR_1005", voilutFunction);

			outputBitCount = 16;
		}
		else if(voilut != null)		// VOI LUT sequence hasznalata
		{
			// "The output range is from 0 to 2n-1 where n is the third value of LUT Descriptor. This range is always unsigned."
			//
			// "If multiple items are present in VOI LUT Sequence (0028,3010), only one may be applied to the Image for display.
			// Multiple items indicate that multiple alternative views may be presented."

			DValue dvVoilutSequence = dataSet.getValue(0x0028, 0x3010);		// VOI LUT sequence
			ArrayList<DicomItem> items = (ArrayList<DicomItem>) dvVoilutSequence.getValue();
			if(voilut.intValue() >= items.size() || voilut.intValue() < 0)
				throw new DImgInternalErrorException("DicomImage.getBufferedImage(): voilutNum is out of range (" + voilut + ")!");

			DicomItem item = items.get(voilut);
			DValue dvLutDescriptor = item.getValue(0x0028, 0x3002);
			int[] lutDescriptor = (int[]) dvLutDescriptor.getValue();

			// LUT size
			int lutSize = lutDescriptor[0];
			if(lutSize < 0)		// ha a LUT descriptor SS tipusu es a LUT size eredetileg 32767-nel nagyobb
			{
				lutSize = 65536 + lutSize;
			}
			else if(lutSize == 0) lutSize = 65536;

			// also ertek a mapping-nel
			int lowValue = lutDescriptor[1];		// US vagy SS

			// Entry size in bits
			int entrySize = lutDescriptor[2];		// 8 vagy 16 lehet
			outputBitCount = entrySize;
			if(entrySize != 8 && entrySize != 16) DImgException.createAndThrow("DIMGERR_0009", entrySize);

			// LUT data    (mindig unsigned!)
			DValue dvLutData = item.getValue(0x0028, 0x3006);
			short[] lutDataShort = (short[]) dvLutData.getValue();			// ez lehet US vagy OW
			int[] lutData = new int[lutSize];
			if(entrySize == 16 && lutDataShort.length != lutSize)
				DImgException.createAndThrow("DIMGERR_0010", lutDataShort.length, lutSize);
			if(entrySize == 8 && lutDataShort.length != lutSize)
			{
				// ha 8 bites az entry meret es folyamatosan van eltarolva (nem csak word-onkent 1 byte), akkor atalakitas...
				if(lutDataShort.length != lutSize / 2)
					DImgException.createAndThrow("DIMGERR_0011", lutDataShort.length, lutSize);
				for(int i = 0, j = 0; i < lutDataShort.length; i++, j += 2)
				{
					lutData[j] = (lutDataShort[i] & 0x00ff);
					lutData[j + 1] = ((lutDataShort[i] >> 8) & 0x00ff);
				}
			}
			else
			{
				for(int i = 0; i < lutSize; i++)
				{
					lutData[i] = (lutDataShort[i] & 0xffff);
				}
			}

			// konverzio
			int highValue = lowValue + lutSize - 1;
			int lowLutValue = lutData[0];		// LUT elso eleme
			int highLutValue = lutData[lutData.length - 1];		// LUT utolso eleme

			int s = 0;
			int d = 0;
			for(int y = 0; y < dispRect.height; y++)
			{
				s = (dispRect.y + y) * columns + dispRect.x;
				for(int x = 0; x < dispRect.width; x++, s++, d++)
				{
					if(buf[s] <= lowValue) dispBuf[d] = lowLutValue;
					else if(buf[s] >= highValue) dispBuf[d] = highLutValue;
					else dispBuf[d] = lutData[Math.round(buf[s]) - lowValue];
				}
			}
		}
		else
		{
			throw new DImgInternalErrorException("");
//			// ha nincs VOI LUT sequence sem.
//			// ilyen eset elvileg nem lehetseges...
//			int s = 0;
//			int d = 0;
//			for(int y = 0; y < dispRect.height; y++)
//			{
//				s = (dispRect.y + y) * columns + dispRect.x;
//				for(int x = 0; x < dispRect.width; x++, s++, d++)
//				{
//					dispBuf[d] = Math.round(buf[s]);
//				}
//			}
//			outputBitCount = 16;
		}

		return outputBitCount;
	}

	private void processPresentationLUT(Rectangle dispRect, boolean inverted) throws DIException, DImgException
	{
		// Presentation LUT Sequence-t nem tartalmaz egy IOD sem (???). Viszont Presentation LUT Shape-et igen.
		//
		// Presentation LUT
		// "The second value is the first input value mapped, and shall always be 0." Tehat a VOI LUT kimenetenek 0 vagy nagyobb erteknek kell lennie!
		// "The third value specifies the number of bits for each entry in the LUT Data. It shall be between 10 and 16 inclusive."
		// "LUT Data (0028,3006) contains the LUT entry values, which are P-Values."
		//
		// Softcopy Presentation LUT nincs hasznalva a feldolgozando IOD-k eseten

		String shape = dataSet.getString(0x2050, 0x0020);		// Presentation LUT Shape
		if(shape == null) shape = "IDENTITY";
		
		if(shape.equals("IDENTITY") && !inverted
				|| shape.equals("INVERSE") && inverted)
		{
			// nincs konverzio, a bemeneti adatok P-value-k.
		}
		else if(shape.equals("IDENTITY") && inverted
				|| shape.equals("INVERSE") && !inverted)
		{
			int len = dispRect.width * dispRect.height;
			for(int i = 0; i < len; i++)
			{
				dispBuf[i] = 65535 - dispBuf[i];
			}
		}
		else DImgException.createAndThrow("DIMGERR_1007", shape);
	}

	/**
	 * A Modality LUT utani allapotot tartalmazo BufferedImage letrehozasa.
	 * Atmenetileg, tesztelesi celra fenntartott eljaras!!!
	 * @return
	 */
	public BufferedImage getBufferedImageAfterModalityLUT()
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getBufferedImagePetikenek(): Image is not loaded!");
		return createBufferedImage(buf, bitsAllocated);
	}

	//=================================================================================================================================

	/**
	 * Image ujracsomagolasa egy uj data set-be. A csomagolas modja kivalaszthato az imageFormat parameterrel, amelynek
	 * egy DicomImage konstanst kell megadni.
	 *
	 * <p>Az eredeti image merete csokkentheto. Az uj meretet a magnification, destWidth es destHeight parameterekkel
	 * lehet megadni a kovetkezokeppen:<br>
	 *     - ha csak a magnification parameter van megadva: akkor az eredeti meret * magnification lesz az uj meret.<br>
	 *     - ha csak a destWidth es destHeight van megadva: akkor az uj image-nek pontosan ezek lesznek a meretei, feteve
	 * hogy egyik sem nagyobb az image eredeti mereteinel.<br>
	 *     - ha a magnification, destWidth es destHeight parameterek egyuttesen vannak megadva: akkor
	 * az eredeti meret * magnification lesz az uj meret, felteve hogy a kapott szelesseg nagyobb vagy egyenlo a destWidth-nel, illetve
	 * a magassag nagyobb vagy egyenlo a destHeight-nal. Ha valamelyik meret kisebb, akkor a magnification erteke ugy novekszik, hogy
	 * megfeleljen az elobbi feltetelnek. (A magnification erteke igy sem lehet nagyobb mint 1.)
	 * <p>JPEG2000 tarolasi formatum eseten lossy tomorites is valaszthato. A compressRatio parameter segitsegevel lehet megadni
	 * a tomorites merteket.
	 * @param dataSet az eredeti image-et tartalmazo eredeti data set
	 * @param imageFormat az ujracsomagolas formatuma (DicomImage konstans)
	 * @param compressRatio tomorites merteke (Csak az imageFormat = Format_JPEG2000 eseten ertelmezett).
	 * Ha null, akkor lossless. Ha 0 - 1 kozott van, akkor azt hatarozza meg, hogy az eredeti
	 * meret hanyszorosara tomoritse. Pl. 0.1 az 10 szeres tomoritest jelent.
	 * @param magnification meret szorzo: erteke 0 - 1 kozti lehet, vagy null
	 * @param destWidth az uj image szelesseg
	 * @param destHeight az uj image magassag
	 * @param newSOPInstanceUID az uj data set SOP instance UID-je. Abban az esetben ha az imageFormat = Format_JPEG2000, valamint
	 * a compressRatio meg van adva, tehat lossy compression tortenik, akkor az ujonnan lerehozott data set-nek adni kell egy uj, globalisan
	 * egyedi SOP instance UID-t. (Ha null, akkor az eredeti data set SOP instance UID-jat fogja kapni.)
	 * @return az ujonnan letrehozott data set
	 * @throws DImgException
	 */
	public DicomDataSet repackImageToNewDataSet(DicomDataSet dataSet, int imageFormat, Float compressRatio, Double magnification,
			Integer destWidth, Integer destHeight, String newSOPInstanceUID) throws DImgException
	{
		if(magnification != null && (magnification <= 0 || magnification > 1))
			throw new DImgInternalErrorException("repackImageToNewDataSet(): magnification should be between 0 - 1! (%f)", magnification);
		if(compressRatio != null && (compressRatio <= 0 || compressRatio > 1))
			throw new DImgInternalErrorException("repackImageToNewDataSet(): compressRatio should be between 0 - 1! (%f)", compressRatio);

		try
		{
			this.dataSet = dataSet;

			// uj data set letrehozasa
			DicomDataSet dsNew = (DicomDataSet) dataSet.clone();

			// Eredeti gepi image betoltese
			loadMachineImage();

			// uj bits allocated, bit-depth es high-bit beallitas
			if(realBitDepth <= 8) bitsAllocated = 8;
			else bitsAllocated = 16;
			dsNew.changeDataElementValue(0x0028, 0x0100, new Integer(bitsAllocated));		// bits allocated
			dsNew.changeDataElementValue(0x0028, 0x0101, new Integer(realBitDepth));		// bitDepth
			highBit = realBitDepth - 1;
			dsNew.changeDataElementValue(0x0028, 0x0102, new Integer(highBit));		// highBit

			int[] processedImg = null;
			if(magnification != null || destWidth != null && destHeight != null)
			{
				// eredeti image lekicsinyitese ikon meretre
				double width = columns;
				double height = rows;
				if(magnification != null && destWidth == null && destHeight == null)
				{
					width = width * magnification;
					height = height * magnification;
				}
				else if(magnification == null && destWidth != null && destHeight != null)
				{
					if(destWidth < width) width = destWidth;
					if(destHeight < height) height = destHeight;
				}
				else if(magnification != null && destWidth != null && destHeight != null)
				{
					double m = magnification;
					if(Math.round(width * m) < destWidth)
						m = (double)destWidth / width;
					if(Math.round(height * m) < destHeight)
						m = (double)destHeight / height;
					if(m > 1) m = 1;

					width = Math.round(width * m);
					height = Math.round(height * m);
				}
				
				processedImg = ImageResampler.imageShrink(buf, columns, rows, (int) Math.round(width), (int) Math.round(height));
				columns = (int) Math.round(width);
				rows = (int) Math.round(height);
				dsNew.changeDataElementValue(0x0028, 0x0010, new Integer(rows));		// rows
				dsNew.changeDataElementValue(0x0028, 0x0011, new Integer(columns));		// column
			}
			else
			{
				// eredeti image nem valtozik
				processedImg = new int[buf.length];
				for(int i = 0; i < buf.length; i++)
					processedImg[i] = Math.round(buf[i]);
			}

			if(imageFormat == Format_JPEG2000)
			{
				byte[] encodedImg = null;
				String vrCode = "OW";
				if(isByteBuf) vrCode = "OB";
				boolean isSigned = (pixelRepresentation == 1 ? true : false);

				if(compressRatio != null)	// LOSSY
				{
					EncoderTDS encoder = new EncoderTDS(processedImg, realBitDepth, columns, rows, isSigned, compressRatio);
					try
					{
						encoder.start();
					}
					catch (J2KTDSException ex)
					{
						throw new DImgException("Error in JJ2000 encoder.", ex);
					}

					OutDataTDS outData = encoder.getOutData();
					encodedImg = new byte[outData.endPtr];
					System.arraycopy(outData.data, 0, encodedImg, 0, outData.endPtr);

					// Data elemet-ek modositasa a data set-ben
					//-----------------------------------------

					// Image type
					String imageType = dsNew.getString(0x0008, 0x0008);
					if(imageType != null)
						dsNew.changeDataElementValue(0x0008, 0x0008, "DERIVED" + imageType.substring(imageType.indexOf("\\")));
					else
						dsNew.createOrChangeDataElementValue(0x0008, 0x0008, "DERIVED\\PRIMARY");

					// LossyImageCompression: 01 = lossy
					dsNew.createOrChangeDataElementValue(0x0028, 0x2110, "01");

					// elert compression ratio
					float achievedCompressRatio = (float)(processedImg.length * realBitDepth) / (float)(encodedImg.length * 8);
					String licr = dsNew.getString(0x0028, 0x2112);		// LossyImageCompressionRatio
					if(licr != null)
						dsNew.changeDataElementValue(0x0028, 0x2112,
								licr + "\\" + Float.toString(achievedCompressRatio));
					else
						dsNew.createOrChangeDataElementValue(0x0028, 0x2112, Float.toString(achievedCompressRatio));

					// Lossy Image Compression Method
					String licm = dsNew.getString(0x0028, 0x2114);
					if(licm != null)
						dsNew.changeDataElementValue(0x0028, 0x2114, licm + "\\ISO_15444_1");	// JPEG 2000 Irreversible Compression
					else
						dsNew.createOrChangeDataElementValue(0x0028, 0x2114, "ISO_15444_1");

					// transfer syntax
					dsNew.setTransferSyntax(TransferSyntax.JPEG2000);
				}
				else	// LOSSLESS
				{
					EncoderTDS encoder = new EncoderTDS(processedImg, realBitDepth, columns, rows, isSigned, null);
					try
					{
						encoder.start();
					}
					catch (J2KTDSException ex)
					{
						throw new DImgException("Error in JJ2000 encoder.", ex);
					}

					OutDataTDS outData = encoder.getOutData();
					encodedImg = new byte[outData.endPtr];
					System.arraycopy(outData.data, 0, encodedImg, 0, outData.endPtr);

					// Data elemet-ek modositasa a data set-ben
					//-----------------------------------------
					
					// PS 3.3 / C.7.6.1.1.5
					// "If an image is compressed with a lossy algorithm, the attribute Lossy Image Compression (0028,2110) is set to “01”.
					// Subsequently, if the image is decompressed and transferred in uncompressed format, this attribute value remains “01”."
					String lic = dsNew.getString(0x0028, 0x2110);	// LossyImageCompression
					if(lic == null)
						dsNew.createOrChangeDataElementValue(0x0028, 0x2110, "00");		// LossyImageCompression: 00 = lossless

					// transfer syntax
					dsNew.setTransferSyntax(TransferSyntax.JPEG2000_LosslessOnly);
				}

				// encapsulated data keszitese
				EncapsulatedData edata = new EncapsulatedData();
				EncapsulatedData.DataFragment fragment = new EncapsulatedData.DataFragment();
				fragment.setData(encodedImg);
				EncapsulatedData.DataFrame frame = new EncapsulatedData.DataFrame();
				frame.getFragments().add(fragment);
				edata.getFrames().add(frame);

				// Data element keszitese
				DataElement de = new DataElement();
				de.setGroupNumber(0x7fe0);
				de.setElementNumber(0x0010);
				de.setVr(VR.getVR(vrCode));
				de.setValueLength(0);
				de.setValue(edata);
				de.setEncapsulatedPixelData(true);

				// pixel value data element csere
				dsNew.replaceDataElement(de);

				return dsNew;
			}
			else if(imageFormat == Format_Native)
			{
				// Data element keszitese
				DataElement de = new DataElement();
				de.setGroupNumber(0x7fe0);
				de.setElementNumber(0x0010);
				de.setValueLength(0);

				if(isByteBuf)
				{
					de.setVr(VR.getVR("OB"));
					byte[] byteBuf = new byte[processedImg.length];
					for(int i = 0; i < processedImg.length; i++) byteBuf[i] = (byte) (processedImg[i] & 0xff);
					de.setValue(byteBuf);
				}
				else
				{
					de.setVr(VR.getVR("OW"));
					short[] shortBuf = new short[processedImg.length];
					for(int i = 0; i < processedImg.length; i++) shortBuf[i] = (short) (processedImg[i] & 0xffff);
					de.setValue(shortBuf);
				}

				// pixel value data element csere
				dsNew.replaceDataElement(de);

				// transfer syntax
				dsNew.setTransferSyntax(TransferSyntax.ExplicitVRLittleEndian);

				return dsNew;
			}
			else throw new DImgInternalErrorException("repackImageToNewDataSet(): Unsupported image format (%d)", imageFormat);
		}
		catch (DIException ex)
		{
			throw new DImgInternalErrorException(ex);
		}
		catch (IOException ex)
		{
			throw new DImgException("", ex);
		}
	}

	/**
	 * A data set-ben tarolt image meretei (szelesseg, magassag) lekerdezese.
	 * @param dataSet
	 * @return
	 * @throws DIException
	 */
	public Dimension getImageDimensions(DicomDataSet dataSet) throws DIException
	{
		int dy = dataSet.getFirstInt(0x0028, 0x0010);							// sorok szama  (DY)
		int dx = dataSet.getFirstInt(0x0028, 0x0011);							// oszlopok szama  (DX)
		return new Dimension(dx, dy);
	}

	private void createHistogram()
	{
		histogram = new int[histogramSize];
		for(int i = 0; i < histogramSize; i++) histogram[i] = 0;
		float elemC = histogramSize;

		// minimum es maximum ertekek meghatarozasa
		int len = columns * rows;
		minValue = buf[0];
		maxValue = buf[0];
		for(int i = 1; i < len; i++)
		{
			if(buf[i] < minValue) minValue = buf[i];
			if(buf[i] > maxValue) maxValue = buf[i];
		}

		float din = maxValue - minValue;
		for(int i = 0; i < len; i++)
		{
			int index = (int) Math.floor((buf[i] - minValue) * elemC / din);
			if(index == histogramSize) index--;		// a maximum ertek az utolso hisztogram elemhez tartozik (256 elem eseten a 255-os indexuhoz)
			histogram[index]++;
		}
	}

	/**
	 * Modality LUT utan az image hisztogramja
	 * @return
	 */
	public int[] getHistogram()
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getBufferedImagePetikenek(): Image is not loaded!");
		return histogram;
	}

	/**
	 * Modality LUT utan az image legkisebb pixel erteke
	 * @return
	 */
	public float getMinValue()
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getBufferedImagePetikenek(): Image is not loaded!");
		return minValue;
	}

	/**
	 * Modality LUT utan az image legnagyobb pixel erteke
	 * @return
	 */
	public float getMaxValue()
	{
		if(!isLoaded) throw new DImgInternalErrorException("DicomImage.getBufferedImagePetikenek(): Image is not loaded!");
		return maxValue;
	}

	//--------------------------------------------------------------------------------------------------------------------------

	/**
	 * @return the histogramSize
	 */ public int getHistogramSize() {
		return histogramSize;
	}

	/**
	 * @param histogramSize the histogramSize to set
	 */ public void setHistogramSize(int histogramSize) {
		this.histogramSize = histogramSize;
	}
}
