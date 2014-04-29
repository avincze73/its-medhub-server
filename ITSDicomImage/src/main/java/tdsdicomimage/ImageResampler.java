package tdsdicomimage;

import java.util.ArrayList;
import tdsdicomimage.exception.DImgInternalErrorException;

public class ImageResampler
{
	public static int[] imageShrink(Object srcImage, int srcWidth, int srcHeight, int destWidth, int destHeight)
	{
		// Az uj pixel merete nagyobb, mint a regi. Az uj pixel erteke az alatta talalhato regi pixelek ertekenek sulyozott atlaga lesz.
		// A sulyozas attol fugg, hogy a regi pixel hanyad reszet fedi az uj pixel.
		// Jelolesek:
		//   HR: horizontalis felbontas regi = srcWidth
		//   HU: horizontalis felbontas uj = destWidth
		//   VR: vertikalis felbontas regi = srcHeight
		//   VU: vertikalis felbontas uj = destHeight
		//   m: regi pixel szelesseg = 1/HR
		//   M: uj pixel szelesseg = 1/HU
		//   n: regi pixel magassag = 1/VR
		//   N: uj pixel magassag = 1/VU

		double srcW = srcWidth;
		double srcH = srcHeight;
		double destW = destWidth;
		double destH = destHeight;

		if((srcImage instanceof int[]) && ((int[])srcImage).length < srcW * srcH
				|| (srcImage instanceof float[]) && ((float[])srcImage).length < srcW * srcH)
			throw new DImgInternalErrorException("imageShrink(): less number of pixel in srcImage compare to width and height!");
		if(destW > srcW || destH > srcH)
			throw new DImgInternalErrorException("imageShrink(): destination image is bigger than the source image!");

		int[] image = new int[(int)(destW * destH)];

		// Kepletek:
		//   elso fedett regi pixel horizontalisan: xs = floor((i * M) / m) = floor(i * HR / HU)
		//   utolso fedett regi pixel horizontalisan: xe = floor((i + 1) * HR / HU)  ha ez pont a pixel kezdetenel van, akkor nem vesszuk bele
		//   elso fedett regi pixel vertikalisan: ys = floor((j * N) / n) = floor(j * VR / VU)
		//   utolso fedett regi pixel vertikalisan: ye = floor((j + 1) * VR / VU)  ha ez pont a pixel kezdetenel van, akkor nem vesszuk bele
		//   uj pixel kozeppontja es a regi pixel kozeppontjanak maximalis tavolsaga horizontalisan: M/2 + m/2
		//   uj pixel kozeppontja es a regi pixel kozeppontjanak maximalis tavolsaga vertikalisan: N/2 + n/2
		double horD = srcW / destW;		// HR / HU
		double verD = srcH / destH;	// VR / VU
		double halfm = 1 / (2 * srcW);		// m/2
		double halfM = 1 / (2 * destW);		// M/2
		double mmdH = halfM + halfm;		// Maximum Middlepoints Distance Horizontal: (M/2 + m/2)
		double halfn = 1 / (2 * srcH);		// n/2
		double halfN = 1 / (2 * destH);	// N/2
		double mmdV = halfN + halfn;		// Maximum Middlepoints Distance Vertical: (N/2 + n/2)

		ArrayList<SrcPixelRate> xRates = new ArrayList<SrcPixelRate>();
		ArrayList<SrcPixelRate> yRates = new ArrayList<SrcPixelRate>();

		for(double j = 0; j < destH; j++)
		{
			for(double i = 0; i < destW; i++)
			{
				// egy uj pixel kiszamitasa
				double xs = Math.floor(i * horD);			// X start: elso X koordinata amit fed az uj pixel
				double xe = Math.floor((i + 1) * horD);		// X end: utolso X koordinata amit fed az uj pixel
				if(xe == srcW) xe--;
				double ys = Math.floor(j * verD);			// Y start: elso Y koordinata amit fed az uj pixel
				double ye = Math.floor((j + 1) * verD);		// Y end: utolso Y koordinata amit fed az uj pixel
				if(ye == srcH) ye--;

				// Keplet:
				//   regi pixel horizontalis lefedettsege:
				//      rate = (kozeppontok max. tavolsaga - kozeppontok tenyleges tavolsaga) / m
				//      ha rate > 1, akkor rate = 1
				//      tehat:
				//      rate = ((M/2 + m/2) - abs((i/HU + M/2) - (x * m + m/2))) / m
				//      ha rate > 1, akkor rate = 1
				//
				//   ugyanez a vertikalis esetre:
				//      rate = ((N/2 + n/2) - abs((j/VU + N/2) - (y * n + n/2))) / n
				//      ha rate > 1, akkor rate = 1
				double aH = i / destW + halfM;		// (i/HU + M/2)   Horizontal
				double aV = j / destH + halfN;		// (j/VU + N/2)   Vertical

				// az adott X koordinataju pont hanyad reszben vesz reszt az atlagolasnal
				xRates.clear();
				for(double x = xs; x <= xe; x++)
				{
					double rate = (mmdH - Math.abs(aH - (x / srcW + halfm))) * srcW;
					if(rate > 1) rate = 1;
					xRates.add(new SrcPixelRate(x, rate));
				}
				// az adott Y koordinataju pont hanyad reszben vesz reszt az atlagolasnal
				yRates.clear();
				for(double y = ys; y <= ye; y++)
				{
					double rate = (mmdV - Math.abs(aV - (y / srcH + halfn))) * srcH;
					if(rate > 1) rate = 1;
					yRates.add(new SrcPixelRate(y, rate));
				}

				// Sulyozott atlag szamitasa
				double a = 0;		// atlag = a/b
				double b = 0;
				if((srcImage instanceof int[]))
				{
					for(int v = 0; v < yRates.size(); v++)
					{
						for(int h = 0; h < xRates.size(); h++)
						{
							double val = ((int[])srcImage)[(int)(yRates.get(v).num * srcW + xRates.get(h).num)];	// adott regi pixel erteke
							double rate = yRates.get(v).rate * xRates.get(h).rate;	// adott regi pixel uj pixelbeli aranya
							a += val * rate;
							b += rate;
						}
					}
				}
				else if((srcImage instanceof float[]))
				{
					for(int v = 0; v < yRates.size(); v++)
					{
						for(int h = 0; h < xRates.size(); h++)
						{
							double val = ((float[])srcImage)[(int)(yRates.get(v).num * srcW + xRates.get(h).num)];	// adott regi pixel erteke
							double rate = yRates.get(v).rate * xRates.get(h).rate;	// adott regi pixel uj pixelbeli aranya
							a += val * rate;
							b += rate;
						}
					}
				}
				else throw new DImgInternalErrorException("");

				double newVal = a / b;

				// Uj pixel ertek tarolasa
				image[(int)(j * destW + i)] = (int) Math.round(newVal);
			}
		}

		return image;
	}

	private static class SrcPixelRate
	{
		public double num;
		public double rate;

		public SrcPixelRate(double num, double rate)
		{
			this.num = num;
			this.rate = rate;
		}
	}
}
