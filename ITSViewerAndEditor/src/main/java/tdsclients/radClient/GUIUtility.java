package tdsclients.radClient;



import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GUIUtility {
	private static SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy MM dd");

	
/*	public static BufferedImage loadIcon(String iconFileName)
	{
		try
        {
			BufferedImage icon = null; // = new BufferedImage();
//			icon=(BufferedImage)ImageIO.read(new File(iconFile));
//			System.out.println("iconfilename = " + iconFileName);
//			System.out.println("A icon==null: "+(icon==null) + " - "+ icon );
			File iconFile = new File(iconFileName);
//			System.out.println("A iconfile==null: "+(iconFile==null) + " - "+ iconFile );
			icon = ImageIO.read(iconFile);
//			System.out.println("B icon==null: "+(icon==null)+ " - "+ icon );
			int h = icon.getHeight();
			int w = icon.getWidth();
//			System.out.println("Icon loading");
			int[] pixel = new int[4];//, pixel2;
			WritableRaster raster = icon.getRaster();
			for (int i=0; i<h; i++)
			{
				for (int j=0; j<w; j++)
				{
//					pixel = raster.getPixel(i, j, pixel);
//					pixel2 = new int[]{
//										raster.getSample(i, j, 0),
//										raster.getSample(i, j, 1),
//										raster.getSample(i, j, 2)
//										};
////					System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+","+pixel[3]+"]");
//					System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+"]");

//					if(pixel[0]==255 && pixel[1]==255 && pixel[2]==255 )
//						raster.setPixel(i,j,new int[]{1,0,0,0});
				}
			}
			return icon;
        }
        catch (IOException ex) 
        {
 //       	System.out.println("Catch "+ ex.getMessage()+" "+ex.getLocalizedMessage()+" "+ex.toString());
        	return null;
        }	
		
	}*/
	
	public static ImageIcon loadIcon(String iconFileName, Color transparentColor){

            System.out.println(iconFileName);
		File iconFile = new File(iconFileName);
		BufferedImage iconimage;
				
		try {
			iconimage = ImageIO.read(iconFile);			


			int h = iconimage.getHeight();
			int w = iconimage.getWidth();
//			System.out.println("Icon loading");
			int[] pixel = new int[4];//, pixel2;
			WritableRaster raster = iconimage.getRaster();
//			WritableRaster alphaRaster  = iconimage.getAlphaRaster() ;
			for (int i=0; i<h; i++)
			{
				for (int j=0; j<w; j++)
				{
					pixel = raster.getPixel(j, i, pixel);
//					pixel2 = new int[]{
//										raster.getSample(i, j, 0),
//										raster.getSample(i, j, 1),
//										raster.getSample(i, j, 2)
//										};
////					System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+","+pixel[3]+"]");
//					System.out.println("Pixel = ["+pixel[0]+","+pixel[1]+","+pixel[2]+"]");

					if( pixel[0]==transparentColor.getRed() && 
						pixel[1]==transparentColor.getGreen() && 
						pixel[2]==transparentColor.getBlue() )
//							raster.setPixel(i, j, new float[]{1,0,0,0});
							//raster.setPixel(i,j,new int[]{255,255,255});
		//					alphaRaster.setPixel(i,j,new int[]{255});
							//raster.setSample(i, j, 2, 0);
							;
				}
			}
			
			
			
			ImageIcon icon = new ImageIcon(iconimage);		
			return icon;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}
	
	
	
	
	
	public static String formatSimpleDateString(Date date){
		StringBuffer ans = new StringBuffer();
//		ans = SIMPLEDATEFORMAT.format(date,ans,null);

//		ans = SIMPLEDATEFORMAT.format(date);
			
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd");
		
		return format.format(date);
	}
	
	
	
}
