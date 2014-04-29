package tdsclients.radClient.imaging;

import java.awt.RenderingHints;
import java.awt.image.LookupOp;



public class Window {

		public static final int MINCOLORVAL = 0;
//		public static final int MAXCOLORVAL = 255;		//=2^8-1
		public static final int MAXCOLORVAL = 65535;		//=2^16-1
		private double windowWidth;
		private double windowCenter;

		private LookupOp windowOperation;
		
		public Window(double wCenter, double wWidth, RenderingHints renderingHints) {
			windowCenter = wCenter ;
			windowWidth = wWidth;
			
			windowOperation = new  LookupOp(produceLookupTable(),renderingHints);
		}
		
		public IntLookupTable produceLookupTable(){
			int[] lut = new int[MAXCOLORVAL+1];
			
			int  leftWindowBoundary = (int)Math.round(windowCenter-windowWidth/2);
			int rightWindowBoundary = (int)Math.round(windowCenter+windowWidth/2);
			
			if(leftWindowBoundary<0)	//window starts left of 0
			{
				if(rightWindowBoundary <0)	//whole window is before 0 -> everything MAXCOLORVAL
					for(int i = 0; i < lut.length ; i++)
						lut[i] = MAXCOLORVAL;
				else	//window starts left of 0, but finishes right of 0
				{
					for(int i = 0; i < Math.min(lut.length,rightWindowBoundary) ; i++) //lut for input range between leftWindowBoundary rightWindowBoundary is according to the window 
						lut[i] = (int)Math.round(getWindowWalue(i-leftWindowBoundary));
					for(int i = rightWindowBoundary; i < lut.length ; i++) 		//if rightWindowBoundary < lut.length then fill the lut from rightWindowBoundary with MAXCOLORVAL				
						lut[i] = MAXCOLORVAL;				
				}
			}
			else 		//window starts right of 0
			{
				//lut for input range left from the leftWindowBoundary stays 0 
				for(int i = leftWindowBoundary; i < Math.min(lut.length,rightWindowBoundary) ; i++) //lut for input range between leftWindowBoundary rightWindowBoundary is according to the window 
					lut[i] = (int)Math.round(getWindowWalue(i-leftWindowBoundary));
				for(int i = rightWindowBoundary; i < lut.length ; i++) 		//if rightWindowBoundary < lut.length then fill the lut from rightWindowBoundary with MAXCOLORVAL				
					lut[i] = MAXCOLORVAL;
			}
			
//			genericOffset=(int)(windowCenter-windowWidth/2);	//window min value given for offset
//			double scaler = (MAXCOLORVAL-MINCOLORVAL+1)/windowWidth;
//			int length = (int)windowWidth;
//			int[] windowedValues = new int[length];
//			for(int i = 0; i <windowedValues.length ; i++)
//				windowedValues[i] = (int) ( MINCOLORVAL + i*scaler );		
					
			return new  IntLookupTable(0, lut);
		}
		
		
		private double getWindowWalue(double val){	return (val/windowWidth)*MAXCOLORVAL;	}
		
		
		public final LookupOp getWindowOperation() {	return windowOperation;	}	
		public Window getCopy()	{	return new Window( windowCenter, windowWidth, this.windowOperation.getRenderingHints());		}	
		public double getWindowCenter() {	return windowCenter;	}
		public double getWindowWidth() {	return windowWidth;	}	

}
