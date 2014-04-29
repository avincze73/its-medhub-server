package tdsclients.radClient.imaging.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import tdsclients.radClient.imaging.DPoint;
import tdsclients.radClient.imaging.ImageDisplay;

public abstract class Measurement extends AnnotMeas{
	
	protected ToolLabel[] labels;
	
	
	//*************** CONSTRUCTOR *****************	
	Measurement(ImageDisplay.AnnotMeasLayer toolLayer, String toolName){super(toolLayer, toolName);}
	
	
	//*************** ABSTRACT METHODS *****************

	public abstract void moveLabelsWithPointMove(int pointNum, double xMove, double yMove);
	
	
	//*************** METHODS *****************
	
	//overriding methods of AnnotMeas

	@Override
	public double[] wholeMove( double xMove, double yMove){
		double[] correctWholeMoveMainPart = super.wholeMove( xMove,  yMove);
		for(int i=0; i<labels.length;i++)
			labels[i].wholeMove( correctWholeMoveMainPart[0], correctWholeMoveMainPart[1]);	
		validate();
		return correctWholeMoveMainPart;
	}
	
/*	public double[] pointMove(int pointNum, double xMove, double yMove) {
		double[] correctPointMove = correctPartOfPointMove(pointNum, xMove,  yMove);
		points[pointNum] = new DPoint(points[pointNum].x+correctPointMove[0], points[pointNum].y+correctPointMove[1]);
		moveLabelsWithPointMove(pointNum, xMove, yMove);
		return correctPointMove;
	}	*/		
	
	
	public void setSelected(){
		selected = true;
		for (int i=0; i<labels.length; i++)
			labels[i].setSelected();
	}


	public void setUnSelected(){
		selected = false;
		for (int i=0; i<labels.length; i++)
			labels[i].setUnSelected();
	}

	

	

	
	
}
