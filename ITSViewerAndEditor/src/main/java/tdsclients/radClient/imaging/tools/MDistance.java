package tdsclients.radClient.imaging.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;

import casemodule.dto.DicomImageDTO;

import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.DRect2D;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ImageDisplay;
import tdsclients.radClient.imaging.Utility;
import java.awt.*;

public class MDistance extends Measurement {

	
	private static final String TOOLNAME = "M_Distance";
	public static final String COMMANDSTRING = "Distance Measurement";
	private static final int ENDPOINT_TICKSIZE = 5; //should be odd number  //(int)POINT_RADIUS;
	private static final int DEFAULTLABELOFFSETX  = 0;
	private static final int DEFAULTLABELOFFSETY  = 15;
	private static final int NUMPOINTS  = 2;
	private static final int PARTNUM_LABEL = 1;
	private static final int LABELPRECISION = 1;

//	private static final DPoint DEFAULT_RELATIVE_TEXT_POS = new DPoint(0,30);
	
	protected double distance;
	protected DicomImageDTO dicomImage;
	
	
	
	//*************** CONSTRUCTOR *****************	
	public MDistance( FontRenderContext frc, ImageDisplay.AnnotMeasLayer toolLayer, DicomImageDTO image, DPoint point1, DPoint point2){
		super( toolLayer, TOOLNAME);
		dicomImage = image;
		points = new DPoint[]{new DPoint(point1.getX(),point1.getY()),new DPoint(point2.getX(),point2.getY())};
//		points = new DPoint[]{new DPoint(point1.getX()-35,point1.getY()+10),new DPoint(point1.getX()+35,point1.getY()-10)};
		labels = new ToolLabel[]{new ToolLabel(frc, toolLayer, getCenterPos().x, getCenterPos().y , getLabelString(), DEFAULTLABELOFFSETX,DEFAULTLABELOFFSETY)};
		
		additionStage = 2;
		
		setSelected();
		validate();		
	}


	
	//*************** METHODS *****************

	//definitions of abstract methods from ImagingTools
	
	@Override
	protected void validate() {
		updateDistance();
		getLabel().changeText(getLabelString(), ToolLabel.KEEP_CENTER_WHEN_RESIZED); 
	}
	
	
	@Override
	public void paint(Graphics2D gc, DisplayModel displayModel) {

		//precalc
//		Point positionOnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getPos());
//		Point centerOnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getCenterPos());
		Point point1OnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getPoint(0));
		Point point2OnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getPoint(1));

			
		//for hit testing
		gc.setColor(Color.RED);
		int d=((int)Math.round(POINT_RADIUS*2));
		gc.drawOval(point1OnDisplay.x-d/2, point1OnDisplay.y-d/2, d, d);			
		gc.drawOval(point2OnDisplay.x-d/2, point2OnDisplay.y-d/2, d, d);			
			
		
		//paint label
		getLabel().paint(gc,displayModel);

		
		//paint tool
		if(additionStage ==2)
			{
				gc.setColor(DEFAULT_MEASUREMENT_COLOR_SELECTED);
				gc.setStroke(DOTTEDLINESTROKE);
				gc.drawLine(point1OnDisplay.x, point1OnDisplay.y, point2OnDisplay.x, point2OnDisplay.y);
			}
		else
			if(selected)
				{
					gc.setColor(DEFAULT_MEASUREMENT_COLOR_SELECTED);
//					gc.setStroke(new BasicStroke());
					gc.drawLine(point1OnDisplay.x, point1OnDisplay.y, point2OnDisplay.x, point2OnDisplay.y);
				}
				else
				{
					gc.setColor(DEFAULT_MEASUREMENT_COLOR_NORMAL);			
//					gc.setStroke(new BasicStroke());
					gc.drawLine(point1OnDisplay.x, point1OnDisplay.y, point2OnDisplay.x, point2OnDisplay.y);
				}
	

		System.out.println("\n\n--------Line (distance tool)----------");
		System.out.println("Floating point coords - point 1      = "+getPoints());
		System.out.println("Floating point coords - point 2      = "+getPoints());
		System.out.println("Corresponding image point  - point 1 = "+point1OnDisplay);
		System.out.println("Corresponding image point  - point 2 = "+point2OnDisplay);
		System.out.println("------------------\n");
		
		
	}

	@Override
	public double[] getApplicablePartOfToolMovement(int hitCode1, int hitCode2, int offsetX, int offsetY,
			int newX, int newY, DRect2D subImageRectangleOnRawImage, DisplayModel displayModel) {
		
/*		System.out.println("\n\n\n ************************************************");
		System.out.println(" ************************************************\n\n\n");

		System.out.println("\tcurrent focus                    = "+displayModel.getFocusOnActRawImage());
		System.out.println("\tsub image size on act raw image  = "+displayModel.getSubImageSizeOnActRawImage());
		System.out.println("\tdisplayed part of sub image TLC  = "+displayModel.getSubImageTLCOnRawImage());
		System.out.println("\tdisplayed part of sub image BRC  = "+displayModel.getSubImageBRCOnRawImage());
		
		System.out.println("\tsubImageRectangleOnRawImage = ("
				+subImageRectangleOnRawImage.getX()+","+
				subImageRectangleOnRawImage.getY()+";"+
				(subImageRectangleOnRawImage.getX()+subImageRectangleOnRawImage.getWidth())+","+
				(subImageRectangleOnRawImage.getY()+subImageRectangleOnRawImage.getHeight())+")");
*/		
		//there are two parts (point and label) and the main part has two points 
		int partToMove = hitCode1;
		int pointToMove = hitCode2;
		
		if(partToMove == NO_PART_HIT)
			return null;
			
		if(partToMove == PARTNUM_LABEL)   //moving label
		{
			//TODO moving label
			//return ...;
		}
		
		if(partToMove == WHOLE_MOVE)
		{
			return null; //TODO 
		}
		else
		{
			if(pointToMove == DONTCARE)
				return null; //error - TODO handle 
				
//			System.out.println("\tcurrentPos of point "+pointToMove+" in image space= "+this.points[pointToMove]);			
			
			//	calc intended destination  = newCursorPoint + grabbingOffset
	//		int destinationX = newX+offsetX;
	//		int destinationY = newY+offsetY;
			Point intendedDestinationDisp = new Point(newX+offsetX,newY+offsetY);
//			System.out.println("\tintendedDestination of point "+pointToMove+" in Disp space = "+intendedDestinationDisp);
			
			//transform destination on display to image space
			
			DPoint intendedDestinationImageSpace = displayModel.pointTransformFromShownImageToOriginalImage(intendedDestinationDisp);
//			System.out.println("\tintendedDestination of point "+pointToMove+" in Image space = "+intendedDestinationImageSpace);
			
					
			//	calculate closest point to intended destination inside displayed area (image space)
			DPoint correctedDestination =  Utility.findClosestPointNotOutsideRect(intendedDestinationImageSpace,subImageRectangleOnRawImage);
//			System.out.println("\tcorrectedDestination of point "+pointToMove+" in image space= "+correctedDestination);
			
			//double check that everything is sound in original image space (eg other points effected by moving this point )
			DPoint correctedDestination2 = correctedDestination;
//			System.out.println("\tcorrectedDestination2 of point "+pointToMove+" in image space = "+correctedDestination2);
	
			double correctedMovementX = correctedDestination2.getX()-points[pointToMove].getX();
			double correctedMovementY = correctedDestination2.getY()-points[pointToMove].getY();
	
/*			System.out.println("\tcorrectedMovement of point "+pointToMove+" in image space= ("+correctedMovementX+","+correctedMovementY+")");
			System.out.println("\t            from "+this.points[pointToMove]+" to "+correctedDestination2);
	
			
			System.out.println("\tNew floating point coords        = "+getPoint(pointToMove));
			System.out.println("\tNew corresponding display point  = "+displayModel.pointTransformFromOriginalImageToShownImage(getPoint(pointToMove)));
			System.out.println("\tNew corresponding image point    = "+getPoint(pointToMove).roundIntoImageGrid());
	
			System.out.println("\n\n\n ************************************************");
			System.out.println(" ************************************************\n\n\n");*/
					
			return new double[]{correctedMovementX,correctedMovementY};
		}

	}

	
	
	
	//definitions of abstract methods from AnnotMeas

	@Override
	public MDistance getCopy() {
		MDistance copy = new MDistance(this.getLabel().fontRenderContext, this.toolLayer, this.dicomImage, new DPoint(this.points[0].x, this.points[0].y),new DPoint(this.points[1].x, this.points[1].y) );
		copy.selected = this.selected;
		copy.labels[0].selected = this.labels[0].selected;
		return copy;
	}
	
	
	@Override
	public int[] getMouseHitResult(int mousePosInDisplaySpaceX,
			int mousePosInDisplaySpaceY, DisplayModel displayModel) {
		
		int[] ownPointResult = getOrderedPointProximityResults(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY, displayModel);
/*		System.out.println("\n\n\n*************************************************************************************");
		System.out.println("\n\n***\nPoint proximity check result = " + ownPointResult + "\n***\n\n");
		System.out.println("\n*************************************************************************************\n\n");
*/		int xOffset = ownPointResult[1];
		int yOffset = ownPointResult[2];
		
		System.out.println("\n\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n\n");
		System.out.println("ownPointResult   ="+ownPointResult[0]+","+ownPointResult[1]+","+ownPointResult[2]);
		
		if (ownPointResult[0]==NO_POINT)	//own point not hit
			{
/*				//TODO line hits
				if()
				{
					return new int[]{WHOLE_MOVE, DONTCARE, xOffset,yOffset}; //own point hit
					
				}
				else  //no line hit, check label*/
				{
					/*			    int[] labelHitResult = getLabel().getMouseHitResult(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY, displayModel); 
					System.out.println("\n\n***\nLabelPoint proximity check result = [" + labelHitResult[0] + "," + labelHitResult[1] + "]\n***\n\n");
					if(labelHitResult[0] == AnnotMeas.NO_HIT)
					{
						//label not hit
						System.out.println("Label not hit");
						return new int[]{NO_HIT, DONTCARE};
					}
					else 
					{
						//label hit
						System.out.println("Label hit");
						return new int[]{PARTNUM_LABEL, WHOLE_MOVE};			//part 1 is label
					}*/
				}
				System.out.println("Label checked");
				System.out.println("returning   = "+NO_PART_HIT +","+ DONTCARE +","+ xOffset +","+ yOffset );
				System.out.println("\n\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n\n");
				return new int[]{NO_PART_HIT, DONTCARE, xOffset,yOffset };
			}
		else
		{
//			return new int[]{ownPointResult[0], DONTCARE, xOffset,yOffset}; //own point hit

			System.out.println("Main part checked");
			System.out.println("returning   = "+PARTNUM_MAIN  +","+  ownPointResult[0] +","+  xOffset +","+ yOffset);
			System.out.println("\n\n\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n\n");
			return new int[]{PARTNUM_MAIN, ownPointResult[0], xOffset,yOffset}; //own point hit
		}

	}


	@Override
	public int getActualNumPoints() {	return points.length;}

	@Override
	public int getTheoreticalNumPoints() {		return NUMPOINTS;}

	
	
	
	//definitions of abstract methods from Measurement	
	
	@Override
	public void moveLabelsWithPointMove(int pointNum, double xMove, double yMove) {	}


	@Override
	public double[] pointMove(int pointNum, double xMove, double yMove) {
		points[pointNum] = new DPoint(points[pointNum].x+xMove, points[pointNum].y+yMove);

		//TODO check move label
		labels[0].reposition(getCenterPos().x, getCenterPos().y);
		validate();
		return new double[]{xMove,  yMove};
	}



	//own methods
	
	private void updateDistance(){
/*		System.out.println("\n\n\n ************************************************");
		System.out.println("\tupdateDensityAtPos: pointOnRawImage                 = "+getCenterPos());
		System.out.println("\tupdateDensityAtPos: pointOnRawImage-rounded to grid = "+pointOnRawImage);
		System.out.println(" ************************************************\n\n\n");*/
		double distXInImageSpace = Math.abs(points[0].x-points[1].x);
		double distYInImageSpace = Math.abs(points[0].y-points[1].y);

		//PS 3.3(IODs), page 92 of the DICOM standard 2009
		//TODO  dicomImage.getProcessedDicomImage(). ???  ;	
		  
		if( dicomImage.getProcessedDicomImage().isPixelSpacingPresent() )  //Pixel Spacing (0028,0030) present
  			{
				Double[] pixelSpacingScalers =  dicomImage.getProcessedDicomImage().getPixelSpacing();
  				double scaledXDist = distXInImageSpace*pixelSpacingScalers[0];
  				double scaledYDist = distYInImageSpace*pixelSpacingScalers[1];
  				distance = Math.sqrt(scaledXDist*scaledXDist+scaledYDist*scaledYDist);
  			} 
		else  //not present	
			distance = Double.NaN;

		//TODO beware of DICOM pixel Aspect Ratio (0028,0034), Imager Pixel Spacing, Nominal Scanned Pixel Spacing, Image Plane Pixel Spacing, Presentation Pixel Spacing!!!!	
	}

	protected DPoint getCenterPos(){ return new DPoint((points[0].x+points[1].x)/2,(points[0].y+points[1].y)/2); }
	protected DPoint[] getPoints(){ return points; }
	protected DPoint getPoint(int point){ return points[point]; }
	protected ToolLabel getLabel(){ return labels[0]; }

	private String getLabelString(){
		String distanceString;
		if (Double.isNaN(distance))
			distanceString = "(n/a)";
		else			
			distanceString = Utility.getNumStringWithPrecision(Double.toString(distance),LABELPRECISION)+" mm";
		
		System.out.println("\n\n\n ************************************************");
		System.out.println("\tdistance (double) = "+distance);
		System.out.println("\tdistance toString = "+Double.toString(distance));
		System.out.println("\tdistanceString    = "+distanceString);
		System.out.println(" ************************************************\n\n\n");
		
		
		
		return distanceString;
	}


}
