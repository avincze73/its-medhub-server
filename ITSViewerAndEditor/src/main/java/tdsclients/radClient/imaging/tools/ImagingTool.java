package tdsclients.radClient.imaging.tools;


import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.DRect2D;
import tdsclients.radClient.imaging.DisplayModel;
import tdsclients.radClient.imaging.ImageDisplay;

//import ij.*;
//import ij.plugin.*;
//import ij.process.*;
//import ij.gui.*;
//import ij.util.Tools;
import java.awt.*;
//import java.awt.geom.*;
//import java.awt.image.*;
//import java.util.*;



public abstract class ImagingTool {

	protected final Stroke DOTTEDLINESTROKE = new BasicStroke((float)1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2,2} , 0);

	
/*	protected final Stroke dottedLineStroke = new BasicStroke(1.0f,  // Width
            BasicStroke.CAP_SQUARE,    // End cap
            BasicStroke.JOIN_MITER,    // Join style
            10.0f,                     // Miter limit
            new float[] {16.0f,20.0f}, // Dash pattern
            0.0f);  */                   // Dash phase
	
		

		
		
	ImageDisplay.AnnotMeasLayer toolLayer;
	private String toolName; 

	//*************** CONSTRUCTOR *****************
	ImagingTool(ImageDisplay.AnnotMeasLayer toolLayer, String toolName){
			this.toolName = toolName;
			this.toolLayer = toolLayer;
			
	       
		}
	
	
	//*************** ABSTRACT METHODS *****************

	
	protected abstract void validate();		//model, labels, look, etc

	//painting
	public abstract void paint(Graphics2D gc, DisplayModel displayModel);
	//	public abstract void pointMove(int pointNum, double xMove, double yMove, Graphics2D g);
	//	public abstract void pointMoveTo(int pointNum, double xDestination, double yDestination, Graphics2D g);

	//getHitResult
//	public abstract int[] getMouseHitResult(double mouseRawImagePosX, double mouseRawImagePosY, double zoom);
	public abstract int[] getMouseHitResult(int mousePosInDisplaySpaceX, int mousePosInDisplaySpaceY, DisplayModel displayModel );
	//INPUT
		//the mouse coordinates need to be given in raw image space
	//RESULT
		//The first coordinate of the result stands for the part of the tool
		//The second first coordinate of the result stands for the point of the tool
		//possible results:
 			//	[-2,-]			no hit at all
 			//	[-1,-]  		move the whole (every part) 
 			//	[Part,-1]		only for (Part>=0)!  move the whole of Part
 			//	[Part,Point]	move Point of Part   
	
	//moving	
	public abstract double[] wholeMove(double xMove, double yMove);  //returns correct part of the movement
//	public abstract void wholeMove(double xMove, double yMove);  //returns correct part of the movement
	

//	public abstract DPoint getDefiningPointN(int n);
	public abstract double[] getApplicablePartOfToolMovement(int hitCode1, int hitCode2, int oldX, int oldY, int newX, int newY, DRect2D subImageRectangleOnRawImage, DisplayModel displayModel);
	
	
	//*************** METHODS *****************

	
	private void setToolName(String toolName){this.toolName = toolName;};
	public String getToolName(){return toolName;}

	
	
}
