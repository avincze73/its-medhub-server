package tdsclients.radClient.imaging.tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

import tdsclients.radClient.imaging.DDim;
import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.DRect2D;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ImageDisplay;



public class ToolLabel extends AnnotMeas{
	
	private static final String TOOLNAME = "IT_ToolLabel";
	
	private static final int BORDER = 3;

	public static final int KEEP_CENTER_WHEN_RESIZED = 0;
	public static final int KEEP_TL_WHEN_RESIZED = 1;
	private static final Font font = new Font("Arial",Font.PLAIN,12);
	private static final int NUMPOINTS  = 1;
	
	
	protected FontRenderContext fontRenderContext;
	
//	protected double width, height;
	protected String labelText;	
	protected TextLayout displayer;
	
	protected int verticalOffset;
	protected int horizontalOffset;
	
	
	//*************** CONSTRUCTOR *****************	
	
	ToolLabel(FontRenderContext frc, ImageDisplay.AnnotMeasLayer toolLayer, double toolCenterX, double toolCenterY, String text, int horizontalOffset, int verticalOffset ){
		super(toolLayer,TOOLNAME);
		fontRenderContext = frc;
		points = new DPoint[]{new DPoint(toolCenterX,toolCenterY)}; 			//referencePoint = centerpoint of tool
		labelText = text;	
		displayer = new TextLayout(labelText, font, fontRenderContext);
///		width = displayer.getAdvance();
//		height = displayer.getAscent();
		this.verticalOffset = verticalOffset;
		this.horizontalOffset = horizontalOffset;
		
		additionStage = WHOLEADDITIONDONE;
	}
	
	
	//*************** METHODS *****************
	
	
	public void reposition(double toolCenterX, double toolCenterY){
		points = new DPoint[]{new DPoint(toolCenterX,toolCenterY)}; 			//referencePoint = centerpoint of tool
	}
	

	//definitions of abstract methods from ImagingTools
	
	
	@Override
	public ToolLabel getCopy()
		{
			ToolLabel copy = new ToolLabel(this.fontRenderContext, this.toolLayer, this.points[0].x, this.points[0].y, this.labelText,   horizontalOffset, verticalOffset );
			return copy;	
		}

	
	@Override
	protected void validate() {}

	
	@Override
	public void paint(Graphics2D gc, DisplayModel displayModel)
	{ 
		
		//precalc
		Point refPointOnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getRefPoint());
		Rectangle2D rectHull =  displayer.getBounds();

		
		//for hit testing
		gc.setColor(new Color(0,100,0));
		gc.drawRect((int)(refPointOnDisplay.x-rectHull.getWidth()/2+horizontalOffset)-3,(int)(refPointOnDisplay.y -displayer.getAscent()+displayer.getDescent()-3 + verticalOffset),(int)rectHull.getWidth()+8,(int)rectHull.getHeight()+7);

		
		
		//painting
		if(selected)
		{
			gc.setColor(DEFAULT_MEASUREMENT_COLOR_SELECTED);
			displayer.draw(gc, (float)(refPointOnDisplay.x - rectHull.getWidth()/2 + horizontalOffset), (float)( refPointOnDisplay.y  + verticalOffset));
		}
		else
		{
			gc.setColor(DEFAULT_MEASUREMENT_COLOR_NORMAL);
			displayer.draw(gc, (float)(refPointOnDisplay.x - rectHull.getWidth()/2 + horizontalOffset), (float)( refPointOnDisplay.y  + verticalOffset));			
		}
//		displayer.draw(gc, (float)(getCenterPoint().x ), (float)( getCenterPoint().y - height/2 + verticalOffset));	

	

	
	}
	
/*	@Override
	public double[] wholeMove( double xMove, double yMove){
		double[] correctWholeMove = correctPartOfWholeMove( xMove,  yMove);
		for(int i=0; i<points.length;i++)
			points[i] = new DPoint(points[i].x+correctWholeMove[0], points[i].y+correctWholeMove[1]);	
		
	}*/
	
	
	//definitions of abstract methods from AnnotMeas
	
	@Override
	public int[] getMouseHitResult(int mousePosInDisplaySpaceX, int mousePosInDisplaySpaceY, DisplayModel displayModel ) { 
/*		double border = BORDER ;///zoom;
		Rectangle2D rectHull =  displayer.getBounds();
		Point refPointOnDisplay = displayModel.pointTransformFromOriginalImageToShownImage(getRefPoint());
		
		double x1 = getRefPoint().x+rectHull.getX()-rectHull.getWidth()/2;
		double y1 = getRefPoint().y+rectHull.getY()-rectHull.getHeight()/2-1;
		double x2 = getRefPoint().x+rectHull.getX()+rectHull.getWidth()/2;
		double y2 = getRefPoint().y+rectHull.getY()+rectHull.getHeight()/2;

		double x1 = refPointOnDisplay.x-rectHull.getWidth()/2+horizontalOffset;
		double y1 = (refPointOnDisplay.y -displayer.getAscent()+displayer.getDescent()-1 + verticalOffset);
		double x2 = refPointOnDisplay.x+rectHull.getWidth()/2+horizontalOffset;
		double y2 = (refPointOnDisplay.y -displayer.getAscent()+displayer.getDescent()-1 + verticalOffset)+rectHull.getHeight()+1;

		gc.drawRect((int)(refPointOnDisplay.x-rectHull.getWidth()/2+horizontalOffset),(int)(refPointOnDisplay.y -displayer.getAscent()+displayer.getDescent()-1 + verticalOffset),(int)rectHull.getWidth()+3,(int)rectHull.getHeight()+2);

		if(	mousePosInDisplaySpaceX < x1  ||
			mousePosInDisplaySpaceX > x2  ||
			mousePosInDisplaySpaceY < y1  ||
			mousePosInDisplaySpaceY > y2	) 
			//no hit
			return new int[]{-2,DONTCARE,Integer.MAX_VALUE,Integer.MAX_VALUE};
		else 
			{//hit
				return new int[]{-1,DONTCARE,,}; //TODO check offset from Middle?
			}*/
		return null;
//		if(	mouseRawImagePosX > getCenterPoint().x-width/2-border  || mouseRawImagePosX < getCenterPoint().x+width/2+border ||
//				mouseRawImagePosY > getCenterPoint().y-height/2-border || mouseRawImagePosY < getCenterPoint().y+height/2+border	) return new int[]{0,-1};
//		else return new int[]{0,-2};			
	

		
//		return new int[]{0,-1};
		}
	
	
	
	public void setSelected(){		selected = true;	}


	public void setUnSelected(){	selected = false;	}
	
	//overriding methods of AnnotMeas
	
/*	@Override
	public double[] correctPartOfWholeMove(double xMove, double yMove){
		double actCenterX = getCenterPoint().x;
		double actCenterY = getCenterPoint().y;
		double intendedPosX = actCenterX+xMove;
		double intendedPosY = actCenterY+yMove;
		DDim rawImageSize = toolLayer.getRawImageSize();
		double correctPosX = Math.min(Math.max(width/2, intendedPosX),rawImageSize.w-width/2);
		double correctPosY = Math.min(Math.max(height/2,intendedPosY),rawImageSize.h-height/2);
		double correctMoveX = correctPosX-actCenterX;
		double correctMoveY = correctPosY-actCenterY;
		return new double[]{correctMoveX,correctMoveY};
	}	
	@Override
	public double[] correctPartOfPointMove(int pointNum, double xMove, double yMove){	return null;	}
	*/
	
	@Override
	public double[] pointMove(int pointNum, double xMove, double yMove) { return null;	}
	
	@Override
	public int getActualNumPoints() {	return points.length;}

	@Override
	public int getTheoreticalNumPoints() {		return NUMPOINTS;}

	
	
	// own methods	
	
	public void changeText(String newText, int mode){
		if(mode  == KEEP_CENTER_WHEN_RESIZED){
			labelText = newText;
			displayer = new TextLayout(labelText, font, fontRenderContext);
//			width = displayer.getAdvance();
//			height = displayer.getAscent();
		}
		if(mode == KEEP_TL_WHEN_RESIZED){
//			double prevWidth  = width;
//			double prevHeight = height;
			labelText = newText;
			displayer = new TextLayout(labelText, font, fontRenderContext);
//			width = displayer.getAdvance();
//			height = displayer.getAscent();
/*			double centerDiffernceX = (width-prevWidth)/2; 
			double centerDiffernceY = (height-prevHeight)/2; 
			pointMove(0, centerDiffernceX, centerDiffernceY);*/
		}
	}
	
	
	
	public DPoint getRefPoint(){ return points[0]; }
	
	
	public double[] getApplicablePartOfToolMovement(int hitCode1, int hitCode2, int oldX, int oldY, int newX, int newY, DRect2D subImageRectangleOnRawImage, DisplayModel displayModel){
		return null; //TODO change if lebels are to be moved separately
	}


	
	
	
	
//	public void calcEncapsulatingArea(){ encapsulatingArea = new Rectangle2D.Double(getCenterPoint().x - width/2,getCenterPoint().y - height/2,width,height); }	
/*	@Override
	protected void validate() {
		calcEncapsulatingArea();
		}*/
	
}
