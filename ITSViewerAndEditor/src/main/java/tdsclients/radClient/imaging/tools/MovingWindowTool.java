package tdsclients.radClient.imaging.tools;

import java.awt.Dimension;

import javax.swing.JPanel;

import tdsclients.radClient.imaging.ImageDisplay.AnnotMeasLayer;



public abstract class MovingWindowTool extends ImagingTool{
	
	//protected Rectangle windowRectangle;	TODO need double Rectangle

	MovingWindowTool(AnnotMeasLayer toolLayer, String toolName) {
		super(toolLayer, toolName);
		// TODO Auto-generated constructor stub
	}

	public Dimension resizeWindow( double increment) {
		// TODO Auto-generated method stub
            return null;
		
	}
	
	@Override
	public double[] wholeMove( double xMove, double yMove) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
