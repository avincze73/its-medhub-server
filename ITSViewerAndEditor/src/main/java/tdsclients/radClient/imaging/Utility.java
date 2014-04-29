package tdsclients.radClient.imaging;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Utility {

	public static BufferedImage copyBufferedImage(BufferedImage source){
		if(source != null)
		{
			Raster origRaster = source.getRaster();		
			BufferedImage copy = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_USHORT_GRAY);
			WritableRaster newRaster = copy.getRaster();	
			newRaster.setRect(origRaster);
			return copy;
		}
		else return null;		
	}
	
	
	public static int roundTowardsZero(double num){		
		if (num<0)	return (int)Math.ceil(num);
		else		return (int)Math.floor(num);			
	}
	
	public static boolean compareDoublesToPrecesion(double double1,double double2, int precision){
		double precisionMultiplier = Math.pow(10,precision);		
		return  Math.round(double1*precisionMultiplier) == Math.round(double2*precisionMultiplier);		
	}
	
	
	public static Dimension doubleVectToDimension(double[] inVal){
		return new Dimension((int)Math.round(inVal[0]),(int)Math.round(inVal[1]));
	}
	

	
	
	public static DPoint findClosestPointNotOutsideRect(DPoint point, DRect2D rect){
//		if(rect.contains(point.x, point.y)) return point;
		double x;
		double y;
		
		//resolving x coordinate
		if(point.x<rect.getMinX())
			x = rect.getMinX();
		else
			if(point.x>rect.getMaxX())
				x = rect.getMaxX();
			else
				x = point.x;
		
			
		//resolving y coordinate
		if(point.y<rect.getMinY())
			y = rect.getMinY();
		else
			if(point.y>rect.getMaxY())
				y = rect.getMaxY();
			else
				y = point.y;
		
		return new DPoint(x,y);
	}
	
	
	public static Point roundPointIntoImageGrid(DPoint dPoint){ //TODO check
		return new Point((int)Utility.roundTowardsZero(dPoint.x-0.5),(int)Utility.roundTowardsZero(dPoint.y-0.5));
	}
	
	
	
	public static String getNumStringWithPrecision(String numString, int precision){
		
		int dotPos = numString.indexOf(".");
		
		if(precision<0)
			return null; //TODO throw exception
		//	throw new Exception();

		if(precision==0)
		{	
			System.out.println("no precision");
			return numString.substring(0, dotPos);
		}
		else	
			{
			
				//TODO should delete nulls from backward
				int decimals = Math.min(precision,numString.length()-dotPos-1);
				String zeroDecimalsComparisonString = repString("0",decimals);
				
				String decimalPart = numString.substring(dotPos+1,Math.min(dotPos+decimals+1,numString.length()));
/*				System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");
				System.out.println("original whole numString		="+numString);
				System.out.println("precision						="+precision);
				System.out.println("dotPos							="+dotPos);
				System.out.println("decimals						="+decimals);
				System.out.println("zeroDecimalsComparisonString	="+zeroDecimalsComparisonString);
				System.out.println("decimalPart						="+decimalPart);
				System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");*/
				if(zeroDecimalsComparisonString.equalsIgnoreCase(numString.substring(dotPos+1,Math.min(dotPos+decimals+1,numString.length()))))
				{
/*					System.out.println("simplified double");
					System.out.println("result string					="+numString.substring(0, dotPos));
					System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");*/
					return numString.substring(0, dotPos); 		//the significant digits are 0s
				}
				else
				{
/*					System.out.println("normal double");
					System.out.println("result string					="+numString.substring(0, dotPos+decimals+1));
					System.out.println("\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n");*/
					return numString.substring(0, dotPos+decimals+1);	//the significant digits are important
				}
			
			}
	}
	
	
	public static String repString(String str, int repNum){
		String ans = "";
		for(int i=1; i<=repNum; i++)
			ans = ans + str;
		return ans;
	}
	
	
	
}
