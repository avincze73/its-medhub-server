package tdsclients.radClient.imaging;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import tdsclients.radClient.CaseViewer;

public class ThumbnailImageFrame extends GenericImageFrame {

	public ThumbnailImageFrame(CaseViewer ownerViewer,int topMarginIn, int leftMarginIn, int bottomMarginIn, int rightMarginIn, Dimension size, Color borderColor, int a){		
		super(ownerViewer,topMarginIn, leftMarginIn, bottomMarginIn, rightMarginIn);
		displayModel = new DisplayModel(false,this);
//		imageDisplay = new ImageDisplay(this, displayModel);		TODO need own version of image
		displayModel.assignImageDisplay( imageDisplay);

//		displayModel = new DisplayModel(imageDisplay, true);
//		imageDisplay = new ImageDisplay(this,  size,  displayModel,  borderColor);
		super.setup();
		setSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);	
	}	
	
	public void layoutImageAndTextDisplays()
	{
		//TODO
	}

	protected void setBorder(boolean init){		
		
		boolean drawSelectionBorder;
		
		if( init )	drawSelectionBorder = false;
		else drawSelectionBorder = isSelected();

				
	  	if(drawSelectionBorder)							
	  		setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(DISTANCE+2,DISTANCE,DISTANCE+3,DISTANCE,CaseViewer.DISPLAYBACKGROUNDCOLOR),
					BorderFactory.createCompoundBorder(
							BorderFactory.createCompoundBorder(
									BorderFactory.createLineBorder(SELECTIONCOLOR,0),
									BorderFactory.createLineBorder(SELECTIONCOLOR,BORDERWIDTH)),
									BorderFactory.createMatteBorder(BORDERGAP,BORDERGAP,BORDERGAP,BORDERGAP,CaseViewer.DISPLAYBACKGROUNDCOLOR))));				
	  	else
			setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createCompoundBorder(
								BorderFactory.createMatteBorder(DISTANCE+2,DISTANCE,DISTANCE+3,DISTANCE,CaseViewer.DISPLAYBACKGROUNDCOLOR),
			//					BorderFactory.createEmptyBorder(0,0,0,0)));				
								BorderFactory.createMatteBorder(BORDERWIDTH,BORDERWIDTH,BORDERWIDTH,BORDERWIDTH,FRAMEEDGECOLOR)),
							BorderFactory.createMatteBorder(BORDERGAP,BORDERGAP,BORDERGAP,BORDERGAP,CaseViewer.DISPLAYBACKGROUNDCOLOR)
						));		

	}
	
	
}
