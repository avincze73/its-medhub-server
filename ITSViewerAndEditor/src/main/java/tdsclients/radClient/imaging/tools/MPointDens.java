package tdsclients.radClient.imaging.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;

import casemodule.dto.DicomImageDTO;

//import datamodel.imagedatamodel.DicomImage;
import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.DRect2D;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ImageDisplay;
import tdsclients.radClient.imaging.Utility;


public class MPointDens extends Measurement {

	public static final String TOOLNAME = "M_PointDens";
	public static final String COMMANDSTRING = "Point Density Measurement";

	public static final int NUMPOINTS  = 1;
	public static final int PARTNUM_LABEL = 1;
	public static final int LABELPRECISION = 2;

	private static final int DRAW_HEIGHT = (int)POINT_RADIUS*2;
	private static final int DRAW_WIDTH  = (int)POINT_RADIUS*2;
	private static final int DEFAULTLABELOFFSETX  = 0;
	private static final int DEFAULTLABELOFFSETY  = 20;	
	
//	private static final DPoint DEFAULT_RELATIVE_TEXT_POS = new DPoint(0,30);
	
	protected double density;
	protected DicomImageDTO dicomImage;
	
	//for testing
//	private double posX,posY;

	
	//*************** CONSTRUCTOR *****************	
	public MPointDens( FontRenderContext frc, ImageDisplay.AnnotMeasLayer toolLayer, DicomImageDTO image, DPoint pos){
		super( toolLayer, TOOLNAME);
		dicomImage = image;
		points = new DPoint[]{new DPoint(pos.getX(),pos.getY())};
//		labels = new ToolLabel[]{new ToolLabel(frc, toolLayer, getPos().x+DEFAULT_RELATIVE_TEXT_POS.x, getPos().y+DEFAULT_RELATIVE_TEXT_POS.y , Integer.toString(density), DEFAULTLABELOFFSET)};
//		labels = new ToolLabel[]{new ToolLabel(frc, toolLayer, getPos().x+DEFAULT_RELATIVE_TEXT_POS.x, getPos().y+DEFAULT_RELATIVE_TEXT_POS.y , "["+pos.x+","+pos.y+":"+Integer.toString(density)+"]", DEFAULTLABELOFFSET)};
		double posXToDisplay = Math.round(pos.x*100)/100;
		double posYToDisplay = Math.round(pos.y*100)/100;
//		labels = new ToolLabel[]{new ToolLabel(frc, toolLayer, getPos().x, getPos().y , "["+posXToDisplay+","+posYToDisplay+":"+density+"]", DEFAULTLABELOFFSETX,DEFAULTLABELOFFSETY)};
		labels = new ToolLabel[]{new ToolLabel(frc, toolLayer, getPos().x, getPos().y , Double.toString(density), DEFAULTLABELOFFSETX,DEFAULTLABELOFFSETY)};
//		this.posX = pos.getX();
//		this.posY = pos.getY();
		
		additionStage = WHOLEADDITIONDONE;
		
		setSelected();
		validate();		
	}
	

	
	//*************** METHODS *****************

	//definitions of abstract methods from ImagingTools

	@Override
	protected void validate() {
		updateDensityAtPos();
//		getLabel().changeText(Integer.toString(density), ToolLabel.KEEP_CENTER_WHEN_RESIZED); 	
//		double posXToDisplay = Math.round(points[0].x*100)/100;
//		double posYToDisplay = Math.round(points[0].y*100)/100;
//		getLabel().changeText("["+posXToDisplay+", "+posYToDisplay+"]: "+Integer.toString(density), ToolLabel.KEEP_CENTER_WHEN_RESIZED); 	
//		getLabel().changeText(Double.toString(density), ToolLabel.KEEP_CENTER_WHEN_RESIZED); 	
		getLabel().changeText(Utility.getNumStringWithPrecision(Double.toString(density),LABELPRECISION), ToolLabel.KEEP_CENTER_WHEN_RESIZED); 	

		}

	
	@Override
	public  void paint(Graphics2D gc, DisplayModel displayModel) {		

		//for precalc
		Point positionOnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getPos());
//		int centerX = (int)displayModel.pointTransformFromOriginalImageToShownImage(getPos().getX());
//		int centerY = (int)Math.round(getPos().getY());			

		int centerX = positionOnDisplay.x;
		int centerY = positionOnDisplay.y;
		
		//for hit testing
		gc.setColor(Color.RED);
		int d=((int)Math.round(POINT_RADIUS*2));
		gc.drawOval(centerX-d/2, centerY-d/2, d, d);
		
		
		
		//paint label
		getLabel().paint(gc,displayModel);
		
		//paint point
/*			//translate to display coordinate space
			DPoint forwardTranslatedCenter = toolLayer.getToolLayerModel().translatePointFromRawImageSpaceToDisplaySpace(getPos());
			int centerX = (int)Math.round(forwardTranslatedCenter.x);
			int centerY = (int)Math.round(forwardTranslatedCenter.y);			
*/

			//paint tool
			if(selected)
				{
					gc.setColor(DEFAULT_MEASUREMENT_COLOR_SELECTED);
					gc.drawLine(centerX-DRAW_WIDTH/2, centerY, centerX-2, centerY);
					gc.drawLine(centerX+2, centerY, centerX+DRAW_WIDTH/2, centerY);
					gc.drawLine(centerX, centerY-DRAW_HEIGHT/2, centerX, centerY-2);
					gc.drawLine(centerX, centerY+2, centerX, centerY+DRAW_HEIGHT/2);
			
				}
			else
				{
					gc.setColor(DEFAULT_MEASUREMENT_COLOR_NORMAL);
		//			gc.drawLine(centerX-DRAW_WIDTH/2, centerY-DRAW_HEIGHT/2, centerX+DRAW_WIDTH/2, centerY+DRAW_HEIGHT/2);
		//			gc.drawLine(centerX+DRAW_WIDTH/2, centerY-DRAW_HEIGHT/2, centerX-DRAW_WIDTH/2, centerY+DRAW_HEIGHT/2);
		
		//			gc.drawLine(centerX-DRAW_WIDTH/2, centerY, centerX+DRAW_WIDTH/2, centerY);
		//			gc.drawLine(centerX, centerY-DRAW_HEIGHT/2, centerX, centerY+DRAW_HEIGHT/2);
					
/*					gc.drawLine(centerX-DRAW_WIDTH/2, centerY, centerX, centerY);
					gc.drawLine(centerX+2, centerY, centerX+DRAW_WIDTH/2, centerY);
					gc.drawLine(centerX, centerY-DRAW_HEIGHT/2, centerX, centerY);
					gc.drawLine(centerX, centerY+2, centerX, centerY+DRAW_HEIGHT/2);	*/
					
					gc.drawLine(centerX-DRAW_WIDTH/2, centerY, centerX-2, centerY);
					gc.drawLine(centerX+2, centerY, centerX+DRAW_WIDTH/2, centerY);
					gc.drawLine(centerX, centerY-DRAW_HEIGHT/2, centerX, centerY-2);
					gc.drawLine(centerX, centerY+2, centerX, centerY+DRAW_HEIGHT/2);
				
		//			gc.setColor(MEASUREMENT_COLOR);
		//			gc.fillRect(centerX-DRAW_WIDTH/2, centerY-DRAW_HEIGHT/2, DRAW_WIDTH, DRAW_HEIGHT);
				}
			

/*			System.out.println("\n\n--------Point (point density tool)----------");
			System.out.println("Floating point coords      = "+getPos());
			System.out.println("Corresponding image point  = "+positionOnDisplay);
			System.out.println("------------------\n");*/
	}

	
	
	@Override
	public double[] getApplicablePartOfToolMovement(int hitCode1, int hitCode2, int offsetX, int offsetY,
			int newX, int newY, DRect2D subImageRectangleOnRawImage, DisplayModel displayModel) {

		//there are two parts (point and label) but the main part only has one point 
		int partToMove = hitCode1;
		
		if(partToMove == NO_PART_HIT)
			return null;
			
		if(partToMove == PARTNUM_LABEL) //moving label
		{
			//TODO 
			//return ...;
		}		
		if(partToMove == WHOLE_MOVE)
		{
			
			System.out.println("\n\n\n ************************************************");
			System.out.println(" ************************************************\n\n\n");
	
/*			System.out.println("\tcurrent focus                    = "+displayModel.getFocusOnActRawImage());
			System.out.println("\tsub image size on act raw image  = "+displayModel.getSubImageSizeOnActRawImage());
			System.out.println("\tdisplayed part of sub image TLC  = "+displayModel.getSubImageTLCOnRawImage());
			System.out.println("\tdisplayed part of sub image BRC  = "+displayModel.getSubImageBRCOnRawImage());
			
			System.out.println("\tsubImageRectangleOnRawImage = ("
					+subImageRectangleOnRawImage.getX()+","+
					subImageRectangleOnRawImage.getY()+";"+
					(subImageRectangleOnRawImage.getX()+subImageRectangleOnRawImage.getWidth())+","+
					(subImageRectangleOnRawImage.getY()+subImageRectangleOnRawImage.getHeight())+")");
			
			
			System.out.println("\tcurrentPos in image space= "+this.points[0]);
*/			
			//	calc intended destination  = newCursorPoint + grabbingOffset
	//		int destinationX = newX+offsetX;
	//		int destinationY = newY+offsetY;
			Point intendedDestinationDisp = new Point(newX+offsetX,newY+offsetY);
//			System.out.println("\tintendedDestination in Disp space = "+intendedDestinationDisp);
			
			//transform destination on display to image space
			
			DPoint intendedDestinationImageSpace = displayModel.pointTransformFromShownImageToOriginalImage(intendedDestinationDisp);
//			System.out.println("\tintendedDestination in Image space = "+intendedDestinationImageSpace);
			
					
			//	calculate closest point to intended destination inside displayed area (image space)
			DPoint correctedDestination =  Utility.findClosestPointNotOutsideRect(intendedDestinationImageSpace,subImageRectangleOnRawImage);
//			System.out.println("\tcorrectedDestination in image space= "+correctedDestination);
			
			//double check that everything is sound in original image space (eg other points effected by moving this point )
			DPoint correctedDestination2 = correctedDestination;
//			System.out.println("\tcorrectedDestination2 in image space = "+correctedDestination2);
	
			double correctedMovementX = correctedDestination2.getX()-points[0].getX();
			double correctedMovementY = correctedDestination2.getY()-points[0].getY();
	
//			System.out.println("\tcorrectedMovement in image space= ("+correctedMovementX+","+correctedMovementY+")");
//			System.out.println("\t            from "+this.points[0]+" to "+correctedDestination2);
	
			
/*			System.out.println("\tNew floating point coords        = "+getPos());
			System.out.println("\tNew corresponding display point  = "+displayModel.pointTransformFromOriginalImageToShownImage(getPos()));
			System.out.println("\tNew corresponding image point    = "+getPos().roundIntoImageGrid());
	
			System.out.println("\n\n\n ************************************************");
			System.out.println(" ************************************************\n\n\n");*/
					
			return new double[]{correctedMovementX,correctedMovementY};
		}
		else 	
			return null; //all else is an error TODO handle		
	}	
	
	
	//definitions of abstract methods from AnnotMeas
	
	@Override
	public MPointDens getCopy()
		{
			MPointDens copy = new MPointDens(this.getLabel().fontRenderContext, this.toolLayer, this.dicomImage, new DPoint(this.points[0].x, this.points[0].y) );
			copy.selected = this.selected;
			copy.labels[0].selected = this.labels[0].selected;
			return copy;	
		}
	
	
	@Override
	public int[] getMouseHitResult(int mousePosInDisplaySpaceX, int mousePosInDisplaySpaceY, DisplayModel displayModel ) {
		
		int[] ownPointResult = getOrderedPointProximityResults(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY, displayModel);
/*		System.out.println("\n\n\n*************************************************************************************");
		System.out.println("\n\n***\nPoint proximity check result = " + ownPointResult + "\n***\n\n");
		System.out.println("\n*************************************************************************************\n\n");
*/		int xOffset = ownPointResult[1];
		int yOffset = ownPointResult[2];
		if (ownPointResult[0]==NO_POINT)	//own point not hit
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
				return new int[]{NO_PART_HIT, DONTCARE, xOffset,yOffset };
			}
		else
//			return new int[]{-1, DONTCARE}; //own point hit
			return new int[]{WHOLE_MOVE, DONTCARE, xOffset,yOffset}; //own point hit
		//TODO other hits
	}
	

	@Override
	public int getActualNumPoints() {	return points.length;}

	@Override
	public int getTheoreticalNumPoints() {		return NUMPOINTS;}
	

	
	
	
	//definitions of abstract methods from Measurement	
	@Override
	public void moveLabelsWithPointMove(int pointNum, double xMove, double yMove) {	}

	
	//overriding definition in Measurement of pointMove() from AnnotMeas 
	@Override	
	public double[] pointMove(int pointNum, double xMove, double yMove) { return wholeMove(xMove, yMove);	}
		
	
	//own methods
		
	private void updateDensityAtPos(){
		Point pointOnRawImage = getPos().roundIntoImageGrid();
/*		System.out.println("\n\n\n ************************************************");
		System.out.println("\tupdateDensityAtPos: pointOnRawImage                 = "+getPos());
		System.out.println("\tupdateDensityAtPos: pointOnRawImage-rounded to grid = "+pointOnRawImage);
		System.out.println(" ************************************************\n\n\n");
*/		density = dicomImage.getProcessedDicomImage().getPixelValueAt(pointOnRawImage.x, pointOnRawImage.y);	
//		density = dicomImage.getProcessedDicomImage().getPixelValueAt(0, 0);
		
	}

	protected DPoint getPos(){ return points[0]; }
	protected ToolLabel getLabel(){ return labels[0]; }






	
	
/*	@Override
	public double[] getApplicablePartOfToolMovement(int pointMoved, int oldX, int oldY, int newX, int newY, DRect2D subImageRectangleOnRawImage) {
		//calc grabbingOffset vector from cursor old point to the grabbed tool point = currentToolPointCoords - oldCursorCoords 
		//calc intended destination  = newCursorPoint + grabbingOffset
		//calculate closest point to intended destination 
		
		return null;
	}*/


	




/*	@Override
	public void calcEncapsulatingArea() {
		double posX = getPos().x;
		double posY = getPos().y;
		double xTL = getLabel().encapsulatingArea.x;
		double yTL = getLabel().encapsulatingArea.y;
		double xBR = xTL+getLabel().encapsulatingArea.width;
		double yBR = yTL+getLabel().encapsulatingArea.height;
		xTL = Math.min(xTL, posX);
		yTL = Math.min(yTL, posY);
		xBR = Math.max(xBR, posX);
		yBR = Math.max(yBR, posY);
		encapsulatingArea = new Rectangle2D.Double(xTL,yTL,xBR-xTL,yBR-yTL);
	}*/



}
