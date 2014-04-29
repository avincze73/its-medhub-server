package tdsclients.radClient.imaging.tools;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import tdsclients.radClient.imaging.DDim;
import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ImageDisplay;

public abstract class AnnotMeas extends ImagingTool{

	//special mouse hit codes
	public static final int NO_PART_HIT  = -2;
	public static final int WHOLE_MOVE   = -1;
	public static final int DONTCARE     = Integer.MIN_VALUE;
	public static final int PARTNUM_MAIN = 0;
	
	public static final int WHOLEADDITIONDONE = -1;
	
//	public static final Color MEASUREMENT_COLOR = new Color(80,180,255);		//cyan	
//	public static final Color MEASUREMENT_COLOR_NORMAL = new Color(110,210,255);		//cyan	
//	public static final Color MEASUREMENT_COLOR_SELECTED = new Color(126,241,255);		//light cyan	
	public static final Color DEFAULT_MEASUREMENT_COLOR_NORMAL = new Color(100,189,255);		//cyan	
	public static final Color DEFAULT_MEASUREMENT_COLOR_SELECTED = new Color(126,241,255);		//light cyan	
	public static final Color DEFAULT_MEASUREMENT_COLOR_ADDING = new Color(126,241,255);		//light cyan	

	//proximity measurements for hit finding
	public static final double POINT_RADIUS = 9;
	public static final double LINE_DISTANCE_FOR_HIT = 7;
	
	public static final int NO_POINT = -2;
	
	protected DPoint[] points;		//these points are defined by coordinates in the Raw Image's space
	
	protected int additionStage;	//tells which point next to be defined (how many clicks have been made +1) when adding the tool - all done is a separate number
	//protected Color[] actColors; ezt hasznalni kell	//TODO RE use this
	
	
	protected boolean selected;
//	protected Rectangle2D.Double encapsulatingArea;

	
	
	//*************** CONSTRUCTOR *****************	
	AnnotMeas( ImageDisplay.AnnotMeasLayer toolLayer, String toolName){ 
		super(toolLayer, toolName);
		}
	
	
	
	//*************** ABSTRACT METHODS *****************	

	
	//self-copy
	public abstract AnnotMeas getCopy(); 

	

	
	
	public abstract double[] pointMove(int pointNum, double xMove, double yMove) ; //returns correct part of the movement
	
	public abstract void setSelected();
	public abstract void setUnSelected();

	public abstract int getActualNumPoints();
	public abstract int getTheoreticalNumPoints();
	
	
	//*************** METHODS *****************

	
	public boolean isSelected(){return selected;}

	
	//proximity methods for mouse hit
//	protected int getOrderedPointProximityResult(double pointPosX, double pointPosY, double zoom){
	protected int[] getOrderedPointProximityResults(int mousePosInDisplaySpaceX, int mousePosInDisplaySpaceY, DisplayModel displayModel){
		double scaledRadius = POINT_RADIUS;///zoom;		//the visual point proximity radius (measured in the displayed space) needs to be scaled into raw image space
		
		System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nPOINT PROXIMITY CHECK for curzor ["+mousePosInDisplaySpaceX+","+mousePosInDisplaySpaceY+"]:");

		int offsetX = Integer.MAX_VALUE;
		int offsetY = Integer.MAX_VALUE;
		
		Point actPointToCheck;
		
		for(int i=0; i<points.length; i++)
		{
			actPointToCheck = displayModel.pointTransformFromOriginalImageToShownImage(points[i]);
			if(actPointToCheck.distance(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY) <= scaledRadius) 
				{
					//offset is the vector from the cursor to the point
					offsetX = actPointToCheck.x-mousePosInDisplaySpaceX;
					offsetY = actPointToCheck.y-mousePosInDisplaySpaceY;
					System.out.println("\tPoint "+i+" ("+points[i].x+","+points[i].y+"), offset: ("+offsetX+","+offsetY+"), distance: "+points[i].distance(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY)+" - IN RANGE OF "+scaledRadius+", so stop");
					return new int[]{i,offsetX,offsetY};	
				}
			else
				{
					System.out.println("\tPoint "+i+" ["+points[i].x+","+points[i].y+"], offset: ("+offsetX+","+offsetY+"), distance: "+points[i].distance(mousePosInDisplaySpaceX, mousePosInDisplaySpaceY)+" - NOT IN RANGE OF "+scaledRadius+", so continuing checking");
				}
		}
		
		System.out.println("\tNo proximity found");
		return new int[]{NO_POINT,offsetX,offsetY}; 
	}
	
	
	protected int getOrderedLineProximityResult(int[][] lineSpec, double pointPosX, double pointPosY, double zoom){
		//lineSpec: the lines' specification needs to be an array of point number pairs (eg.: {{0,2},{1,2}} means two lines, one between points 0 and 2, and one between points 1 and 2)	
		double scaledLineDist = LINE_DISTANCE_FOR_HIT;///zoom;		//the visual line proximity distance (measured in the displayed space) needs to be scaled into raw image space
		int pointA, pointB;
		double distanceFromSegment;
		for(int i=0; i<points.length; i++)
			{
				pointA = lineSpec[i][0];
				pointB = lineSpec[i][1];
				distanceFromSegment = Line2D.ptSegDist(points[pointA].x, points[pointA].y, points[pointB].x, points[pointB].y, pointPosX, pointPosY);
				if(distanceFromSegment <= scaledLineDist) return i;
			}
		return NO_POINT; 
	}
		
	
	//correct part of movement methods for move	
	
/*	public double[] correctPartOfWholeMove(double xMove, double yMove){
		double[] soFarGloballyCorrectMovement = new double[]{xMove, yMove};
		for(int i=0; i<points.length;i++)
			soFarGloballyCorrectMovement = correctPartOfPointMove(i, soFarGloballyCorrectMovement[0], soFarGloballyCorrectMovement[1]);
		return soFarGloballyCorrectMovement;
	}

	public double[] correctPartOfPointMove(int pointNum, double xMove, double yMove){
		double actX = points[pointNum].x;
		double actY = points[pointNum].y;
		double intendedPosX = actX+xMove;
		double intendedPosY = actY+yMove;
		DDim rawImageSize = toolLayer.getRawImageSize();
		double correctPosX = Math.min(Math.max(0,intendedPosX),rawImageSize.w);
		double correctPosY = Math.min(Math.max(0,intendedPosY),rawImageSize.h);
		double correctMoveX = correctPosX-actX;
		double correctMoveY = correctPosY-actY;
		return new double[]{correctMoveX,correctMoveY};
	}*/
	
	
	//move methods
	
	@Override
	public double[] wholeMove( double xMove, double yMove){		//operates in image space
/*		double[] correctWholeMove = correctPartOfWholeMove( xMove,  yMove);
		for(int i=0; i<points.length;i++)
			points[i] = new DPoint(points[i].x+correctWholeMove[0], points[i].y+correctWholeMove[1]);	
		return correctWholeMove;*/
		for(int i=0; i<points.length;i++)
			points[i] = new DPoint(points[i].x+xMove, points[i].y+yMove);
		return new double[]{xMove,  yMove};
	}
	
/*	@Override
	public DPoint getDefiningPointN(int n){
		if(n==-1)
			return points[0];
		else 
			return points[n];
	}*/
	
	
	
	
	
/*	public abstract void pointMove(int pointNum, double xMove, double yMove) {
		double[] correctPointMove = correctPartOfPointMove(pointNum, xMove,  yMove);
		points[pointNum] = new DPoint(points[pointNum].x+correctPointMove[0], points[pointNum].y+correctPointMove[1]);
	}
	*/
	
//	public abstract int[] getComplexSpecificMouseHitResult(double mouseRawImagePosX, double mouseRawImagePosY, double zoom);
	
/*	public int[] getMouseHitResult(double mouseRawImagePosX, double mouseRawImagePosY, double zoom){
		return getComplexSpecificMouseHitResult(mouseRawImagePosX,mouseRawImagePosY,zoom);
	}
	*/
/*	public int[] getMouseHitResult(double mouseRawImagePosX, double mouseRawImagePosY, double zoom){
	//generic simple check
	if(!isMouseInProximityToEncapsulatingArea(mouseRawImagePosX,mouseRawImagePosY,zoom)) return new int[]{NO_HIT,DONTCARE};
	//specific complex check
	return getComplexSpecificMouseHitResult(mouseRawImagePosX,mouseRawImagePosY,zoom);
}

public boolean isMouseInProximityToEncapsulatingArea(double mouseRawImagePosX, double mouseRawImagePosY, double zoom){
	double scaledRadius = POINT_RADIUS/zoom;
	Rectangle2D.Double extendedToolEncapsulatingArea = new Rectangle2D.Double( , , , );
	
	return extendedToolEncapsulatingArea.contains(mouseRawImagePosX, mouseRawImagePosY);
}*/
	
//	public abstract void calcEncapsulatingArea();
//	public Rectangle2D.Double getEncapsulatingArea(){return encapsulatingArea;}
	
	
	
/*	public boolean isFullyInGivenArea(Rectangle2D.Double area){
		Rectangle2D.Double toolArea = getEncapsulatingArea();		
		return area.contains(toolArea);
	}*/


	
}


