package tdsclients.radClient.imaging;

import casemodule.downloading.CaseDownloader;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import casemodule.dto.SeriesDTO;

//import datamodel.imagedatamodel.Series;

import tdsclients.radClient.CaseViewer;
import tdsclients.radClient.imaging.tools.ImagingTool;
import tdsclients.radClient.imaging.tools.MDistance;
import tdsclients.radClient.imaging.tools.MPointDens;
import tdsclients.radClient.imaging.tools.AnnotMeas;


@SuppressWarnings("serial")
public class DisplayImageFrame extends GenericImageFrame {


    private String seriesInstanceUid;

    public String getSeriesInstanceUid() {
        return seriesInstanceUid;
    }

    public void setSeriesInstanceUid(String seriesInstanceUid) {
        this.seriesInstanceUid = seriesInstanceUid;
        SeriesDTO seriesDTO = CaseDownloader.getInstance().getSeriesDTO(seriesInstanceUid);
        System.out.println(seriesDTO.getModality().getName());
    }

	
	public class FrameResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent arg0) 
		{
			System.out.println("FRAME RESIZED!!");
			layoutTextDisplay();
			layoutImageDisplay();
			validate();
			repaint();

/*			if(displayModel.getActImage()!=null)
				System.out.println(	  "->   ImageFrame size : ["+getSize().width+","+getSize().height+"]"+
									"\n      FrameInnerArea : ["+getInnerComponentArea().width+","+getInnerComponentArea().height+"]"+
									"\n      FrameImageArea : ["+getImageArea().width+","+getImageArea().height+"]"+
									"\n         DisplaySize : ["+imageDisplay.getWidth()+","+imageDisplay.getHeight()+"]"+
									"\n           ImageSize : ["+imageDisplay.getModel().getActImage().getWidth()+","+imageDisplay.getModel().getActImage().getHeight()+"]");*/
//			if(imageDisplay!=null)
//				imageDisplay.restoreImageFocus();
		}
	}		
	
	
	
	public class FullDisplayListener implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
		//int startX,startY,endX,endY;
		int actX,actY;
		//boolean windowing, moving;
//		boolean shiftDown;
		
		private DisplayImageFrame ownerDisplayFrame;
		
		public FullDisplayListener(DisplayImageFrame ownerIn){
			this.ownerDisplayFrame=ownerIn;
		}
		
		
		//SELECTION
		@Override
		public void mouseClicked(MouseEvent me) 			//left click -> single selection
		{
			if(SwingUtilities.isLeftMouseButton(me))
					{
//						System.out.println("\n*************************************************************************************");
//						System.out.println("*************************************************************************************");
//						System.out.println("LEFT MOUSE BUTTON");
//						System.out.println("TOOL: '"+ ownerViewer.getToolStateCommandString()+"'");
//						System.out.println("*************************************************************************************");
//						System.out.println("*************************************************************************************");
						
						if(!isSelected())
						
							{
								if( me.isControlDown())	
								{	
									ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
									ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);
								}
								else
								{
									ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
									ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,null);		// unselect ALL displays
									ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);	// select this
			//						select();																// select this
								}
							}
						else
							if( me.isControlDown())	
							{	
								ownerViewer.performOperationOnOne(CaseViewer.UNSELECTOP, null, ownerDisplayFrame);
							}

						
/*						//TODO delete below
						Dimension maxInnerArea = imageDisplay.getMaxInnerPaintArea();
						Dimension innerArea = imageDisplay.getInnerPaintArea();						
						System.out.println("\n\n****\n\n"); 
						System.out.println(" maxInnerArea = "+maxInnerArea);
						System.out.println(" innerArea    = "+innerArea);
						System.out.println(" raw image size    = ("+displayModel.getActImage().getWidth()+","+ displayModel.getActImage().getHeight()+")");
						System.out.println(" act image size    = ("+displayModel.getRawImageWidth()+","+ displayModel.getActImage().getHeight()+")");
						System.out.println("\n\n****\n\n");*/ 
						
						
						Dimension borderOffset = calcBorderOffsetIntoImageArea();
						int[] hitResult = displayModel.pointBelongsToTool(actX - borderOffset.width, actY - borderOffset.height);
						boolean selectingATool = hitResult[0] != AnnotMeasLayerModel.TOOLTYPE_NOHIT;//.NO_HIT;// -1; 
						
						//if hitting a tool ...
						if(selectingATool)
							{
								int toolNumInList = hitResult[1];
/*								ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
								ownerViewer.performOperationOnOne(CaseViewer.SELECTTOOLOP,  new int[]{hitResult[0], toolNumInList, hitResult[2], hitResult[3]}, ownerDisplayFrame);*/
								ownerViewer.performOperationOnOne(CaseViewer.SELECTTOOLOP,  new int[]{hitResult[0], toolNumInList, hitResult[2], hitResult[3]}, ownerDisplayFrame);
							
							}
						else	//otherwise	- add new tool
						{
/*							System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
							System.out.println("\tMouse coords = ("+actX+","+actY+")");
							System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");*/
							
							if(isInImageArea(actX, actY))
							{
/*								System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
								System.out.println("Clicked on image");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");*/
								
								if(imagingToolBeingAdded!=null)	//defining a new point during addition or the last
								{
									//TODO RE
//									ezt meg kell irni
//									if( ... == numpoint) //last point of tool
//									else		//define new point
								}
								else	//adding point density measurement
								if( ownerViewer.getToolStateCommandString().equals(MPointDens.COMMANDSTRING)  ) 
								{//PointDensityTool selected							
									System.out.println("\n!\n!\n!\nMOUSE SENSED!\n!\n!");		
									if (displayModel.canAddMeasToActImage()) 
									{
										ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
										ownerViewer.performOperationOnOne(CaseViewer.ADD_M_POINTDENS, new int[]{actX - borderOffset.width, actY - borderOffset.height}, ownerDisplayFrame);
									}
								}		//adding distance measurement
								else if( ownerViewer.getToolStateCommandString().equals(MDistance.COMMANDSTRING) ) 
								{//PointDensityTool selected							
									System.out.println("\n!\n!\n!\nMOUSE SENSED!\n!\n!");		
									if (displayModel.canAddMeasToActImage()) 
									{
										ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
										ownerViewer.performOperationOnOne(CaseViewer.ADD_M_DIST, new int[]{actX - borderOffset.width, actY - borderOffset.height}, ownerDisplayFrame);
										
										//setting to first state
										//TODO RE imagingToolBeingAdded =  ezt meg kell irni
									}
								}
								else
								{
									ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
								}
							}
							else 
							{
/*								System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
								System.out.println("Not clicked on image");
								System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");*/
							}
						}
					}		
			
			if(SwingUtilities.isRightMouseButton(me))		//right click -> add or remove selection
				if(!isSelected())
					ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);	// select this
				else
					ownerViewer.performOperationOnOne(CaseViewer.UNSELECTOP, null, ownerDisplayFrame);	// unselect this
			
		}	
		
		
		
		
		//WINDOWING and MOVING
		
		@Override
		public void mouseEntered(MouseEvent e) {
			Point p = e.getPoint();
			actX = p.x;
			actY = p.y;
//			System.out.println("Mouse entered at: "+p);
		}


		@Override
		public void mousePressed(MouseEvent me) {
			System.out.println("Mouse pressed");
			if(SwingUtilities.isLeftMouseButton(me))
			{
				
				if(!isSelected())
					
				{
					if( me.isControlDown())	
					{	
						ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
						ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);
					}
					else
					{
						ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
						ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,null);		// unselect ALL displays
						ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);	// select this
//						select();																// select this
					}
				}				
				Dimension borderOffset = calcBorderOffsetIntoImageArea();
				int[] hitResult = displayModel.pointBelongsToTool(actX - borderOffset.width, actY - borderOffset.height);
				boolean selectingATool = hitResult[0] != AnnotMeasLayerModel.TOOLTYPE_NOHIT;; 
				
				if(selectingATool)
				{
					imagingToolPressed = hitResult;
					int toolNumInList = hitResult[1];
					ownerViewer.performOperationOnAll(CaseViewer.UNSELECTALLTOOLSOP, null);
					
					ownerViewer.performOperationOnOne(CaseViewer.SELECTTOOLOP,  new int[]{hitResult[0], toolNumInList, hitResult[2], hitResult[3]}, ownerDisplayFrame);
//					if(hitResult[0]==1)
	//					ownerViewer.performOperationOnOne(CaseViewer.SELECTMEASOP, new int[]{toolNumInList, hitResult[2], hitResult[3]}, ownerDisplayFrame);
				}
				else
					if( ! isSelected())
					{
/*						if( ! me.isControlDown())	
							{							
								ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,0,0);		// unselect ALL displays
								ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, 0, 0, ownerDisplayFrame);	// select this
							}*/
					}
			}
			
/*			if(SwingUtilities.isRightMouseButton(me))	{
				if( ! isSelected())
				{
					if( ! me.isControlDown())	
						{							
							ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,0,0);		// unselect ALL displays
							ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, 0, 0, ownerDisplayFrame);	// select this
						}
				}				
			}*/
			
		  }
		
		
		@Override
		public void mouseReleased(MouseEvent me) {
			imagingToolPressed = null;
			
/*			System.out.println("Mouse released");
			if(SwingUtilities.isLeftMouseButton(me))
			{
				System.out.println("Moving stopped");
				moving = false;
			}
			if(SwingUtilities.isRightMouseButton(me))
			{
				System.out.println("Windowing stopped");
				windowing = false;
			}*/
		  }
		
		
		@Override			
		public void mouseMoved(MouseEvent me) {
			int newX = me.getPoint().x;
			int newY = me.getPoint().y;
//			System.out.println("Mouse moved from " + actX + "," + actY + " to " + newX + "," + newY);
//			int xTravelled = newX-actX;
//			int yTravelled = newY-actY;
			actX = newX;
			actY = newY;
			
			if(imagingToolBeingAdded==null)		;		
				// TODO RE hozzaadas eseten itt kell kezelni az eger mozgasanak koveteset a tool aktualis pontjaval
				//ownerViewer.performOperationOnOne(CaseViewer.MOVETOOLOP, new int[]{hitResult[0], hitResult[1], hitResult[2], hitResult[3], hitResult[4], hitResult[5], newX-innerAreaTLCornerWRTFrameTLCorner.x, newY-innerAreaTLCornerWRTFrameTLCorner.y }, ownerDisplayFrame);						
				
		  }
						
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub			
		}


		@Override
		public void mouseDragged(MouseEvent me) {
	
			int newX = me.getPoint().x;
			int newY = me.getPoint().y;
//			System.out.println("Mouse dragged from " + actX + "," + actY + " to " + newX + "," + newY);
			int xTravelled = newX-actX;
			int yTravelled = newY-actY;
			int oldMouseX = actX;
			int oldMouseY = actY;
			actX = newX;
			actY = newY;
			
		//	if(moving)
			if(SwingUtilities.isLeftMouseButton(me))
			{
				xTravelled = -xTravelled;
				yTravelled = -yTravelled;
				
				
/*				Dimension borderOffset = calcBorderOffsetIntoImageArea();
				int[] hitResult = displayModel.pointBelongsToTool(xStarted - borderOffset.width, yStarted - borderOffset.height);
				boolean draggingATool = hitResult[0] != AnnotMeasLayerModel.TOOLTYPE_NOHIT; //AnnotMeas.NO_HIT;; 
					*/
				
				Dimension innerImDispArea = imageDisplay.getInnerPaintArea();
				Dimension frameArea = getSize();
				Point innerAreaTLCornerWRTFrameTLCorner = new Point((frameArea.width-innerImDispArea.width)/2,(frameArea.height-innerImDispArea.height)/2);
				
				
				boolean draggingATool = imagingToolPressed != null;
				
				System.out.println("\n\n\t-----------------------------\n");
				System.out.println("\t\tOld Mouse Pos = ("+oldMouseX+","+oldMouseY+")");
				System.out.println("\t\tNew Mouse Pos = ("+newX+","+newY+")");
				System.out.println("\t\tTravelled     = ("+(-xTravelled)+","+(-yTravelled)+")");
				System.out.println("\n\n\t-----------------------------\n");
				
				
				if(draggingATool)
					{
						int[] hitResult = imagingToolPressed;
//						ownerViewer.performOperationOnOne(CaseViewer.MOVETOOLOP, new int[]{hitResult[0], hitResult[1], hitResult[2], hitResult[3], xTravelled, yTravelled }, ownerDisplayFrame);
						
//						ownerViewer.performOperationOnOne(CaseViewer.MOVETOOLOP, new int[]{hitResult[0], hitResult[1], hitResult[2], hitResult[3], hitResult[4], hitResult[5], newX-imageDisplay.getInnerPaintArea().width, newY-imageDisplay.getInnerPaintArea().height }, ownerDisplayFrame);						
						ownerViewer.performOperationOnOne(CaseViewer.MOVETOOLOP, new int[]{hitResult[0], hitResult[1], hitResult[2], hitResult[3], hitResult[4], hitResult[5], newX-innerAreaTLCornerWRTFrameTLCorner.x, newY-innerAreaTLCornerWRTFrameTLCorner.y }, ownerDisplayFrame);						
					}
				else
					{
		//				System.out.println("Moving");
						if(isSelected())
							{
								if( me.isControlDown())	
									{		
										ownerViewer.performOperationOnOne(CaseViewer.MOVEOP, new int[] {xTravelled, yTravelled}, ownerDisplayFrame);
									}
								else
									ownerViewer.performOperationOnSelected(CaseViewer.MOVEOP, new int[] {xTravelled, yTravelled});
							}
						else
							{
		/*						if( ! me.isControlDown())	
									{							
										ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,0,0);		// unselect ALL displays
										ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, 0, 0, ownerDisplayFrame);	// select this
									}*/
								
								ownerViewer.performOperationOnOne(CaseViewer.MOVEOP, new int[] {xTravelled, yTravelled}, ownerDisplayFrame);
								//moveImage(xTravelled,yTravelled);
							}
					}
			}	
			if(SwingUtilities.isRightMouseButton(me))
			{
//				System.out.println("Windowing");
				if(isSelected())
					{
						if( me.isControlDown())	
							{		
								ownerViewer.performOperationOnOne(CaseViewer.WINDOWOP, new int[]{xTravelled, yTravelled}, ownerDisplayFrame);
							}
						else
							ownerViewer.performOperationOnSelected(CaseViewer.WINDOWOP, new int[]{xTravelled, yTravelled});
					}
				else
					{
						if( ! me.isControlDown())						
						{							
							ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP, new int[]{0,0});		// unselect ALL displays
							ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, new int[]{0, 0}, ownerDisplayFrame);	// select this
						}
						
						ownerViewer.performOperationOnOne(CaseViewer.WINDOWOP, new int[]{xTravelled, yTravelled}, ownerDisplayFrame);
	//					windowImage(xTravelled,yTravelled);
					}
			}	
			//if(windowing)
		}



		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		    int notches = e.getWheelRotation();
		    
		    if(e.isShiftDown())
		    {
//				System.out.println("Shift Down: "+shiftDown);
		    	System.out.println("Mousewheel moved WITH SHIFT DOWN");
				if(isSelected())
					{
						if( e.isControlDown())	
							{		
								ownerViewer.performOperationOnOne(CaseViewer.ZOOMOP, new int[]{notches}, ownerDisplayFrame);
							}
						else
							ownerViewer.performOperationOnSelected(CaseViewer.ZOOMOP, new int[]{notches});
					}
				else
					{
						if( ! e.isControlDown())						
						{							
							ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP,null);		// unselect ALL displays
							ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);	// select this
						}
					

						ownerViewer.performOperationOnOne(CaseViewer.ZOOMOP,  new int[]{notches}, ownerDisplayFrame);
					}
		    }
		    else
		    {
//				System.out.println("Shift Down: "+shiftDown);
		    	System.out.println("Mousewheel moved WITH NO SHIFT");
				if(isSelected())
				{
					if( e.isControlDown())	
						{		
							ownerViewer.performOperationOnOne(CaseViewer.CHANGEIMAGEOP,  new int[]{notches}, ownerDisplayFrame);
						}
					else
						ownerViewer.performOperationOnSelected(CaseViewer.CHANGEIMAGEOP,  new int[]{notches});
				}
		    	else
		    		{
		    			if( ! e.isControlDown())	
						{							
							ownerViewer.performOperationOnSelected(CaseViewer.UNSELECTOP, null);		// unselect ALL displays
							ownerViewer.performOperationOnOne(CaseViewer.SELECTOP, null, ownerDisplayFrame);	// select this
						}
		    			ownerViewer.performOperationOnOne(CaseViewer.CHANGEIMAGEOP,  new int[]{notches}, ownerDisplayFrame);
		    		}
		    }		    
		}


		
		@Override
		public void keyPressed(KeyEvent arg0) {		}


		@Override
		public void keyReleased(KeyEvent arg0) {
/*			if(arg0.getKeyCode() == KeyEvent.VK_DELETE)
				imageDisplay.getModel().detachSeriesFromdisplay();
*/		}


		@Override
		public void keyTyped(KeyEvent arg0) {	}

		
	}
	
	
	
	//*** ATTRIBUTES ***
	
	public static final String HANDCOMMANDSTRING = "Hand";

	private int[] imagingToolPressed;
//	private ImagingTool imagingToolBeingAdded;	// = link to the tool
	private int[] imagingToolBeingAdded;		// = [tool type, tool number in list]    (note: stage is recorded at the tool ) 
	
	private int frameNumber;
	
	
	
	
	public DisplayImageFrame(CaseViewer ownerViewer, int frameNumberIn, int topMarginIn, int leftMarginIn, int bottomMarginIn, int rightMarginIn, Color borderColor){
		super(ownerViewer,topMarginIn, leftMarginIn, bottomMarginIn, rightMarginIn/*, borderColor*/);		
		frameNumber = frameNumberIn;
		displayModel = new DisplayModel(false,this);
		imageDisplay = new ImageDisplay(this, displayModel);
		displayModel.assignImageDisplay( imageDisplay);
		super.setup();

//		this.setOpaque(true);
		this.setOpaque(true);
//		this.setBackground(Color.red);
//		this.setDoubleBuffered(true);
		
		this.addComponentListener(new FrameResizeListener());
		
		FullDisplayListener fullListener = new FullDisplayListener(this);
		this.addMouseListener(fullListener);
		this.addMouseMotionListener(fullListener);
		this.addMouseWheelListener(fullListener);
		this.addKeyListener(fullListener);
	}	
		

	public DisplayModel getDisplayModelCopy(){
		return displayModel.getCopy();
	}

	
	public void loadDisplayModel(DisplayModel newModel){
		System.out.println("DisplayImageFrame.loadExDisplayStates()");
		displayModel = newModel;
		
		imageDisplay.assignDisplayModel(newModel);
		
	//	displayModel.assignImageDisplay(imageDisplay);
	//	displayModel.assignAnnotMeasLayer(imageDisplay.getAnnotMeasLayer());
		
		System.out.println("	before repaint");
		
		layoutTextDisplay();
		layoutImageDisplay();		//needed!!!
		validate();
		repaint();
		
	}
	
	
	public void layoutTextDisplay()
	{	//find and set correct TextDisplay size
		Dimension correctTextDisplaySize = getMaxInnerComponentSize();		
		//find and set location of TextDisplay in the DisplayImageFrame
		textDisplay.setSize(correctTextDisplaySize);
		textDisplay.setMinimumSize(correctTextDisplaySize);
		textDisplay.setMaximumSize(correctTextDisplaySize);
		textDisplay.setPreferredSize(correctTextDisplaySize);
		textDisplay.setLocation(this.getInsets().left,this.getInsets().top);
		//refresh
//		validate();
//		repaint();
	}
	
	
	
	
	public Dimension calcBorderOffsetIntoImageArea(){
		
		Dimension innerImageArea = imageDisplay.getInnerPaintArea();
		//Dimension frameArea = getMaxInnerComponentSize();
		Dimension frameAreaWithoutBorders = new Dimension(	this.getSize().width  - this.getInsets().left - this.getInsets().right,
															this.getSize().height - this.getInsets().top  - this.getInsets().bottom);
		
//		System.out.println("\n\n\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println("DisplayImageFrame        size =    "+getSize());
//		System.out.println("DisplayImageFrame        insets = ["+getInsets().top+","+getInsets().left+","+getInsets().bottom+","+getInsets().right+"]");
//		System.out.println("frameAreaWithoutBorders  size =    "+frameAreaWithoutBorders);

//		System.out.println("imageDisplay	         size =    "+imageDisplay.getSize());
//		System.out.println("innerImageArea           size =    "+innerImageArea);
//		System.out.println("DisplayImageFrame        size =    "+getSize());
//		System.out.println("DisplayImageFrame        size =    "+getSize());
//		System.out.println("DisplayImageFrame        size =    "+getSize());

		int offsetX = (this.getSize().width-innerImageArea.width)/2;
		int offsetY = (this.getSize().height-innerImageArea.height)/2;
//		int offsetX = (frameAreaWithoutBorders.width-innerImageArea.width)/2;
//		int offsetY = (frameAreaWithoutBorders.height-innerImageArea.height)/2;
//		int offsetX = (frameAreaWithoutBorders.width-imageDisplay.getSize().width)/2;
//		int offsetY = (frameAreaWithoutBorders.height-imageDisplay.getSize().height)/2;
//		int offsetX = 0;
//		int offsetY = 0;
		
//		System.out.println("BorderOffsetIntoImageArea     =    "+new Dimension(offsetX,offsetY));
//		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
		
		return new Dimension(offsetX, offsetY);
	}
	
	
	
	public boolean isInImageArea(int x, int y){
		
		Dimension borders = calcBorderOffsetIntoImageArea();
		boolean topBoundary    = y > borders.height;
		boolean leftBoundary   = x > borders.width;
		boolean bottomBoundary = y < this.getSize().height - borders.height;
		boolean rightBoundary  = x < this.getSize().width - borders.width;

//		System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
//		System.out.println("\tleftBoundary, topBoundary, rightBoundary, bottomBoundary = ("+leftBoundary+","+topBoundary +"," + rightBoundary+","+bottomBoundary+")");
//		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n");
		
		return topBoundary && leftBoundary && bottomBoundary && rightBoundary;
	}
	
	
	
	
	public void layoutImageDisplay()
	{
		Dimension maxInnerComponentSize = getMaxInnerComponentSize();
		//find and set correct ImageDisplay size
		setCorrectImageDisplaySize();		
		//find and set location of ImageDisplay in the DisplayImageFrame
			//if the ImageDisplay exactly fits the frame's inner area
			int locationX = this.getInsets().left;	
			int locationY = this.getInsets().top;
			//if the frame is wider
			if(maxInnerComponentSize.width > imageDisplay.getWidth())
				locationX += (maxInnerComponentSize.width - imageDisplay.getWidth())/2;
			//if the frame is higher
			if(maxInnerComponentSize.height > imageDisplay.getHeight())
				locationY += (maxInnerComponentSize.height - imageDisplay.getHeight())/2;		
		imageDisplay.setLocation(locationX,locationY );		
		//refresh
//		validate();
//		repaint();
	}
	
	
	private void setCorrectImageDisplaySize(){
		
		Dimension correctSize;
		if(displayModel.getActImage() == null)	correctSize = new Dimension(0,0);
		else
		{
			Dimension maxImDispSize = imageDisplay.getMaxAreaOccupiableWithActImage();
			Dimension maxInnerComponentSize = getMaxInnerComponentSize();
			correctSize = new Dimension(	Math.min(	maxImDispSize.width,	maxInnerComponentSize.width	 ),
											Math.min(	maxImDispSize.height,	maxInnerComponentSize.height )		);
		}

	//	System.out.println("\nCorrect size: "+correctSize);
		imageDisplay.setSize(correctSize);
		imageDisplay.setMinimumSize(correctSize);
		imageDisplay.setMaximumSize(correctSize);
		imageDisplay.setPreferredSize(correctSize);
		
//		displayModel.restoreFocus();	//TODO check

	}



		


	public int[] findApplicablePartOfChange(int operation, int[] params){		
		return displayModel.findCorrectFinalStateFromChange(operation, params);		
	}
	
	public boolean wouldTheOperationMakeAChange(int operation, int[] params){		
		return displayModel.wouldTheOperationMakeAChangeOnThisDisplay(operation, params);		
	}

		
	public void moveImageWithNoCheck(int moveX, int moveY)
	{
		//System.out.println("DisplayImageFrame.moveImage("+moveX+","+moveY+")");
		displayModel.performUnderlyingMovement(moveX,moveY);
		repaint();
	}
	
	public void windowImageWithNoCheck(int xDiff, int yDiff)
	{
		//System.out.println("DisplayImageFrame.windowImageWithNoCheck("+xDiff+","+yDiff+")");
		displayModel.performUnderlyingWindowing(xDiff,yDiff);
		repaint();
	}
		
	public void zoomImageWithNoCheck(int wheelChange)
	{//	System.out.println("!! wheelChange: "+wheelChange);
		displayModel.performUnderlyingZooming(wheelChange);
		layoutImageDisplay();
		validate();
		repaint();

	}	
	
	public void changeImageWithNoCheck(int wheelChange)
	{//	System.out.println("!! wheelChange: "+wheelChange);
		displayModel.performUnderlyingImageChange(wheelChange); 
		repaint();
		ownerViewer.printToolLists();
	}	
	
	public void centerImage()
	{
		System.out.println("DisplayImageFrame.centerImage()");
		displayModel.centerImage();
		repaint();
	}		
	
	public void fitImage2Display()
	{
		System.out.println("DisplayImageFrame.fitImage2Display()");
		displayModel.fitImage2Display(/*this.getInnerImageArea()*/);
		layoutImageDisplay();
		validate();
		repaint();

	}		
	
	public void zoom100()
	{
		System.out.println("DisplayImageFrame.zoom100()");
		displayModel.zoom100();
		layoutImageDisplay();
		validate();
		repaint();
	}	
	
	public void resetWindow()
	{
		displayModel.resetWindow();
		repaint();
	}	
	
	public void autoWindow()
	{
		System.out.println("DisplayImageFrame.autoWindow()");
		displayModel.autoWindow();
		repaint();
	}	
	
	public void presetWindow(double w, double c)
	{
		System.out.println("DisplayImageFrame.setWindow()");
		displayModel.presetWindow(w,c);
		repaint();
	}	
	
	
	
	
	//unselect all tools
	public void unselectAllToolsForAllImages(){
		System.out.println("DisplayImageFrame.unselectAllToolsForActImage()");
		displayModel.unselectAllToolsForAllImages();
		repaint();		
	}
	
	//select a tool
	public void selectToolXForActImage(int toolType, int numTool){
		displayModel.selectToolXForActImage(toolType, numTool);
	}
//	public void selectMeasXForActImage(int numAnnotation){
//		displayModel.selectMeasXForActImage(numAnnotation);
//	}

	//get tool selection info
	public int[] checkToolSelection(){		
		return displayModel.checkToolSelection();
	}
	
	
	//del a tool
	public void deleteSelectedToolOfActImage( int toolType, int toolNum){
		displayModel.deleteSelectedToolOfActImage( toolType, toolNum);
		repaint();
	}
	
	//clear tools
	public void clearAnnotMeasLayerForActImage(){
		System.out.println("DisplayImageFrame.clearAnnotMeasLayerForActImage()");
		displayModel.clearAnnotMeasLayerForActImage();
		repaint();
	}
	
	
	public double[] findApplicablePartOfToolMovement (int toolType, int toolNumInList, int hitCode1, int hitCode2, int cursorOffsetX, int cursorOffsetY, int newX, int newY) {

		return displayModel.findApplicablePartOfToolMovement(toolType, toolNumInList, hitCode1, hitCode2, cursorOffsetX, cursorOffsetY, newX, newY);		
	}

	
	public void moveSelectedToolOfActImage   (int toolType, int toolNumInList, int hitCode1, int hitCode2, double toolMovementApplicableChangeX, double toolMovementApplicableChangeY) {
		displayModel.moveSelectedToolOfActImage(toolType, toolNumInList, hitCode1, hitCode2, toolMovementApplicableChangeX, toolMovementApplicableChangeY);
		repaint();

	}

	
	public void addMPointDensToActImage(int xPosOnDisplayedImage, int yPosOnDisplayedImage)
	{
		System.out.println("DisplayImageFrame.addMPointDensToActImage(int,int)");
		displayModel.addMPointDensToActImage(xPosOnDisplayedImage, yPosOnDisplayedImage);
		repaint();
	}
	
	
	
	
	public void addMDistanceToActImage(int xPosOnDisplayedImage, int yPosOnDisplayedImage)
	{
		System.out.println("DisplayImageFrame.addMDistanceToActImage(int,int)");
		displayModel.addMDistanceToActImage(xPosOnDisplayedImage, yPosOnDisplayedImage);
		repaint();
	}

	
	
	
	
	
	public void assignSeriesToDisplay(SeriesDTO seriesIn, boolean freshLoad)	//test version //TODO
		{
			displayModel.assignSeries( seriesIn,  freshLoad);	
			repaint();
		}
	public void disposeSeriesFromdisplay()		//test version //TODO
		{	displayModel.detachSeriesFromdisplay();
			repaint();
		}
	
	
	protected void setBorder(boolean init){		
		
		boolean drawSelectionBorder;
		
		if( init )	drawSelectionBorder = false;
		else drawSelectionBorder = isSelected();
				
	  	if(drawSelectionBorder)					
	  		setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(DISTANCE,DISTANCE,DISTANCE,DISTANCE,CaseViewer.DISPLAYBACKGROUNDCOLOR),
					BorderFactory.createCompoundBorder(
							BorderFactory.createCompoundBorder(
									BorderFactory.createLineBorder(SELECTIONCOLOR,0),
									BorderFactory.createLineBorder(SELECTIONCOLOR,BORDERWIDTH)),
									BorderFactory.createMatteBorder(BORDERGAP,BORDERGAP,BORDERGAP,BORDERGAP,CaseViewer.DISPLAYBACKGROUNDCOLOR))));				
	  	else
			setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createCompoundBorder(
								BorderFactory.createMatteBorder(DISTANCE,DISTANCE,DISTANCE,DISTANCE,CaseViewer.DISPLAYBACKGROUNDCOLOR),
			//					BorderFactory.createEmptyBorder(0,0,0,0)));				
								BorderFactory.createMatteBorder(BORDERWIDTH,BORDERWIDTH,BORDERWIDTH,BORDERWIDTH,FRAMEEDGECOLOR)),
							BorderFactory.createMatteBorder(BORDERGAP,BORDERGAP,BORDERGAP,BORDERGAP,CaseViewer.DISPLAYBACKGROUNDCOLOR)
						));				
	}
	
		
	
//	for testing:
	
//	public int getImagePosX(){return displayModel.getActPosX();}
//	public int getImagePosY(){return displayModel.getActPosY();}
	
	public int getFrameNumber(){ return frameNumber;}
	
	public DisplayModel getModel(){ return displayModel;}   //TODO delete this function!!!
	
	
	public void paint(Graphics g){
		System.out.println("DisplayImageFrame.paint() for frame "+frameNumber);
		super.paint(g);
	}
	
	public void update(Graphics g){
		System.out.println("DisplayImageFrame.update() for frame "+frameNumber);
		super.update(g);
	}
	
	public void repaint(){
		System.out.println("DisplayImageFrame.repaint() for frame "+frameNumber);
		super.repaint();
	}






	
	
	/*
	public int[] preCheckOperationResult(int operation, int param1, int param2){		
		return displayModel.preCheckOperationResult(operation, param1, param2);		
	}
	*/
	
	
	/*	public void centerImage()
		{
			System.out.println("Display.centerImage()");

			if ( displayModel.centerImage() )
			{			
//				setCorrectSize(frame.getImageArea());
				//paintImage();
				
				imageDisplay.repaint();
				
//				   if(getModel().getActImage()!=null)
//					{
//						System.out.println("Size after");
//						System.out.println(	  "-> FrameInnerArea    : ["+((ImageFrame)(getParent())).getInnerComponentArea().width+","+((ImageFrame)(getParent())).getInnerComponentArea().height+"]"+
//								"\n      FrameImageArea : ["+((ImageFrame)(getParent())).getImageArea().width+","+((ImageFrame)(getParent())).getImageArea().height+"]"+
//								"\n         DisplaySize : ["+getWidth()+","+getHeight()+"]"+
//								"\n           ImageSize : ["+getModel().getActImage().getWidth()+","+getModel().getActImage().getHeight()+"]");
//					}
			}
		}*/
		
	
	
}
