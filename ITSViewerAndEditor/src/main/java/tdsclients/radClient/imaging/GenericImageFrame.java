package tdsclients.radClient.imaging;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


import tdsclients.radClient.CaseViewer;
import tdsclients.radClient.GUIUtility;



public abstract class GenericImageFrame extends JLayeredPane{

	

	
	public class DisplayTextLayer extends JPanel {
				
		public static final int TEXTLAYERBORDERWIDTH = 12;
		public static final int LINEHEIGHT =  15;
		public static final int ACCURACY =  2;		//decimal places
		public static final String NOTGIVENSTRING = "(not given)";
		public static final String EXTRANOTGIVEN = " ";
		private final Font font = new Font("Arial",Font.PLAIN,12);
		
		public final double BADZOOMLOW  = 0.5;
		public final double BADZOOMHIGH = 2.5;

		String patientName ;
		String dob ;
		String studyTitle ;
		String modality ;
		String involvedBodyRegion;
		String extra ;

		
		double sliceThickness ;
		String sliceLocation ;

		
		double windowWidth  ;
		double windowCenter ;

		
		int imageNum ;
		int numImages ;
		double zoom ;	
		
		
		
		public DisplayTextLayer(){
			super();
			this.setOpaque(false);
			
			//setOpaque(true);
			//setBackground(Color.red);
			
			this.setFocusable(false);			
			this.setDoubleBuffered(true);	
		}
		
		
		public void paint(Graphics g){
			super.paint(g);
			System.out.println("DisplayTextLayer.paint()");
			if (displayModel!= null)
				if(displayModel.getActImage() != null)
				{	
//					System.out.println("### DisplayTextLayer.paint(Graphics g) ###");
					Graphics2D gc= (Graphics2D)g;		
					FontRenderContext frc = gc.getFontRenderContext();
					
					
//					System.out.println("### Size of TextLayer = ("+this.getSize().width+","+this.getSize().height+") ###");
//					System.out.println("### Is shown = "+this.isVisible()+" ###");
					
					
					gc.setColor( NORMALTEXTCOLOR );
		
					
					if (displayModel.getAssignedSeries()!= null)
						{
							 patientName = displayModel.getAssignedSeries().getStudy().getReferralInfo().getCaseItBelongsTo().getDicomPatientData().getPatientName();
							 Date dobDate = displayModel.getAssignedSeries().getStudy().getReferralInfo().getCaseItBelongsTo().getDicomPatientData().getDob();
							 if(dobDate!=null)
							 {
								 	dob = GUIUtility.formatSimpleDateString(dobDate);
							 }
							 else
								 dob = NOTGIVENSTRING;
							 studyTitle = displayModel.getAssignedSeries().getStudy().getDescription();
							 modality = displayModel.getAssignedSeries().getModality().getName();
							 involvedBodyRegion = displayModel.getAssignedSeries().getBodyPartExamined();   //TODO Be careful and make sure to handle all possibilities!!!
							 extra = null;
				
							
							 if ( displayModel.getActDICOMImage().getProcessedDicomImage().isIsSliceThicknessPresentInDataset()){
                                                             sliceThickness  = displayModel.getActDICOMImage().getProcessedDicomImage().getSliceThickness();
                                                         } else {
                                                             sliceThickness = 0;
                                                         }
                                                         //sliceThickness  = displayModel.getActDICOMImage().getProcessedDicomImage().getSliceThickness();
							 sliceLocation = displayModel.getActDICOMImage().getProcessedDicomImage().getSliceLocation();
				
							
							 windowWidth  = Math.round(displayModel.getActWindowWidth()*Math.pow(10, ACCURACY))/Math.pow(10, ACCURACY);
							 windowCenter = Math.round(displayModel.getActWindowCenter()*Math.pow(10, ACCURACY))/Math.pow(10, ACCURACY);
				
							
							 imageNum = displayModel.getActImageNum()+1;
							 numImages = displayModel.getAssignedSeries().getNumImages();
							 zoom = displayModel.getActZoom()*100;					
						}			
					
					String string;
					TextLayout displayer;
					
					//Top Left Corner
					
					//CASE OR REFERENCEPatientName
					string =  "CASE";				//TODO
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+LINEHEIGHT);

					//PatientName
					string =  patientName;
					if (string == null) string = "notGivenString";
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+2*LINEHEIGHT);
		
					//DoB
					string =  dob;
					if (string == null) string = NOTGIVENSTRING;
					string = "DOB:  "+dob.substring(0, 4)+"/"+dob.substring(4, 6)+"/"+dob.substring(6, 8);
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+3*LINEHEIGHT);
		
					//Study
					string =  studyTitle;
					if (string == null) string = NOTGIVENSTRING;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+4*LINEHEIGHT);
		
					//Modality
					string =  modality;
					if (string == null) string = NOTGIVENSTRING;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+5*LINEHEIGHT);
					
					//Region
					string =  involvedBodyRegion;
					if (string == null) string = NOTGIVENSTRING;
					string = "Region: " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+6*LINEHEIGHT);
					
		
					//T1/T2 or something
					string =  extra;
					if (string == null) string = EXTRANOTGIVEN;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+7*LINEHEIGHT);
		
					
					
					
					//Top Right Corner
					
					//SliceThickness
					string =  Double.toString(sliceThickness);
					if (string == null) string = NOTGIVENSTRING;
					string = "ST:  " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, this.getWidth()-displayer.getAdvance()-TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+LINEHEIGHT);
		
					//SlicePos
					string =  sliceLocation;
					if (string == null) string = NOTGIVENSTRING;
					string = "SL:  " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, this.getWidth()-displayer.getAdvance()-TEXTLAYERBORDERWIDTH, TEXTLAYERBORDERWIDTH+2*LINEHEIGHT);
		
					
					
					//Bottom Left Corner
					
					//window width
					
//					Color windowColor = calcWindowColor();
//					gc.setColor( windowColor );
					
					string =  Double.toString(windowWidth);
					if (string == null) string = NOTGIVENSTRING;
					string = "W:  " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, this.getHeight()-TEXTLAYERBORDERWIDTH-LINEHEIGHT);
		
					//window center
					string =  Double.toString(windowCenter);
					if (string == null) string = NOTGIVENSTRING;
					string = "C:  " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, TEXTLAYERBORDERWIDTH, this.getHeight()-TEXTLAYERBORDERWIDTH);
		
					gc.setColor( NORMALTEXTCOLOR );
					
					
					
					//Bottom Right Corner
					
					//image number / num of images in the series
					string =  Integer.toString(imageNum)+"/"+numImages;
					if (string == null) string = NOTGIVENSTRING;
					string = "Image:  "+string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, this.getWidth()-displayer.getAdvance()-TEXTLAYERBORDERWIDTH, this.getHeight()-TEXTLAYERBORDERWIDTH-LINEHEIGHT);
		
					
					//actual zoom
					
					Color zoomColor = calcZoomColor(zoom/100);
					gc.setColor( zoomColor );
					
					string =  Integer.toString((int)Math.round(zoom))+"%";
					if (string == null) string = NOTGIVENSTRING;
					string = "Zoom:  " + string;
					displayer = new TextLayout(string, font, frc);
					displayer.draw(gc, this.getWidth()-displayer.getAdvance()-TEXTLAYERBORDERWIDTH, this.getHeight()-TEXTLAYERBORDERWIDTH);
		
					gc.setColor( NORMALTEXTCOLOR );
					
				}
			
			
		}
		
		
		
	private Color calcZoomColor(double zoom){
//			System.out.println("ZOOM IS: "+ zoom);
			
			if (zoom == 1 )
			{
//				System.out.println("ZOOM IS: 1");
				return NORMALCHANGINGTEXTCOLOR;
//				return NORMALTEXTCOLOR;
			}
			
			if( zoom <= BADZOOMLOW)
			{
//				System.out.println("ZOOM <= "+BADZOOMLOW);
				return BADTEXTCOLOR;
			}
			
			if( zoom >= BADZOOMHIGH)
			{
//				System.out.println("ZOOM >= "+BADZOOMHIGH);
				return BADTEXTCOLOR;
			}
			
			Color zoomColor = null;
			
			if(zoom > 1)
			{
//				System.out.println("1 < ZOOM < "+BADZOOMHIGH);

/*				int red   = Math.round(linInterPolant(1,BADZOOMHIGH,  zoom,  NORMALTEXTCOLOR.getRed(),  BADTEXTCOLOR.getRed()  ));
				int green = Math.round(linInterPolant(1,BADZOOMHIGH,  zoom,  NORMALTEXTCOLOR.getGreen(),BADTEXTCOLOR.getGreen()));
				int blue = Math.round(linInterPolant(1,BADZOOMHIGH,  zoom,  NORMALTEXTCOLOR.getBlue(),BADTEXTCOLOR.getBlue()));
*/				
/*				System.out.println("COLOR IS:"+
						   "\n\tRed: "+red+
						   "\n\tGreen"+green+
						   "\n\tBlue"+blue
						   );*/
//				zoomColor = new Color(red,green,blue);	
				int colorIndex = Math.round(linInterPolant(1,BADZOOMHIGH,  zoom,  0, COLORMAPSIZE-1));
				zoomColor = COLORMAP_NORMALTORED[colorIndex];
				return zoomColor;
			}
			else
			{
//				System.out.println("1 > ZOOM > "+BADZOOMLOW);
/*				int red   = Math.round(linInterPolant(BADZOOMLOW,1,   zoom,  BADTEXTCOLOR.getRed(),   NORMALTEXTCOLOR.getRed()  ));
				int green = Math.round(linInterPolant(BADZOOMLOW,1,   zoom,  BADTEXTCOLOR.getGreen(), NORMALTEXTCOLOR.getGreen()));
				int blue =  Math.round(linInterPolant(BADZOOMLOW,1,   zoom,  BADTEXTCOLOR.getBlue(), NORMALTEXTCOLOR.getBlue()));
*/				
/*				System.out.println("COLOR IS:"+
								   "\n\tRed: "+red+
								   "\n\tGreen"+green+
								   "\n\tBlue"+blue
								   );*/
//				zoomColor = new Color(red,green,blue);
				int colorIndex = Math.round(linInterPolant(BADZOOMLOW,1,  zoom,  COLORMAPSIZE-1,0));
				zoomColor = COLORMAP_NORMALTORED[colorIndex];
				return zoomColor;
				
			}
		}
		
	
	

		
		
	}
	

//	private static final Color SELECTIONCOLOR = new Color(250,0,0);
	protected static final Color SELECTIONCOLORTHUMBNAIL = new Color(250,30,30);
//	private static final Color SELECTIONCOLOR = new Color(255,0,0);
//	private static final Color SELECTIONCOLOR = new Color(200,55,0);
//	private static final Color SELECTIONCOLOR = Color.white;
//	protected static final Color SELECTIONCOLOR = new Color(200,200,200);
//	protected static final Color SELECTIONCOLOR = new Color(0,200,0);
	protected static final Color SELECTIONCOLOR = new Color(200,0,0);
//	private static final Color REFSELECTIONCOLOR = new Color(50,100,250);
//	private static final Color REFSELECTIONCOLOR = new Color(0,250,250);
//	private static final Color REFSELECTIONCOLOR = new Color(90,90,250);
	protected static final Color REFSELECTIONCOLOR = new Color(0,0,250);
//	private static final Color REFSELECTIONCOLOR2 = new Color(0,250,250);
	protected static final Color COMBINEDCOLOR = new Color(230,0,230);
	
//	private static final Color SELECTIONCOLOR2 = new Color(250,150,150);
//	private static final Color SELECTIONCOLOR2 = new Color(200,81,91);
	protected static final Color SELECTIONCOLOR2 = new Color(125,0,0);
//	private static final Color EDGECOLOR1 = new Color(110,110,110);
	protected static final Color EDGECOLOR1 = new Color(0,0,0);
//	private static final Color EDGECOLOR2 = new Color(170,170,170);
	protected static final int DISTANCE = 3;
//	protected static final int BORDERGAP = 30;
	protected static final int BORDERGAP = 1;
	protected static final Color FRAMEEDGECOLOR = new Color(25,25,25);
	protected static final Color FRAMECOLOR = Color.BLACK;
//	private static final Color FRAMECOLOR = new Color(40,40,40);
	protected static final int BORDERWIDTH = 2;
//	private static final int FRAMEWIDTH = BORDERWIDTH+DISTANCE;

//	public static final Color NORMALTEXTCOLOR = new Color(0,200,200); //Color.cyan;
	public static final Color NORMALTEXTCOLOR = new Color(80,180,255); //Color.cyan;
//	public static final Color NORMALCHANGINGTEXTCOLOR = new Color(0,200,0); //Color.green;
	public static final Color NORMALCHANGINGTEXTCOLOR = new Color(0,185,0); //Color.green;
	public static final Color BADTEXTCOLOR = Color.red;
	public static final int COLORMAPSIZE = 100;
	protected static Color[] COLORMAP_NORMALTORED;// = new Color[COLORMAPSIZE];
	
	protected static final int IMAGELAYER = 0;
	protected static final int ANNOTATIONLAYER = 10;
	protected static final int MOVINGWINDOWLAYER = 20;
	protected static final int TEXTLAYER = 200;
	
	//core model
	protected CaseViewer ownerViewer;
	protected ImageDisplay imageDisplay;
	protected DisplayModel displayModel;
	protected DisplayTextLayer textDisplay;
	
/*	protected int topMargin;
	protected int leftMargin;
	protected int bottomMargin;
	protected int rightMargin;*/
	
	
	//state model
//	private boolean shown;
	private boolean refSelected; 
	private boolean selected; 
	
	
	
	//view model
//	private Color edgeColor;
//	protected boolean toCenterImageOfDisplay;
//	private boolean isThumbnail;
	
	
	//CONSTRUCTORS
	
	
	protected GenericImageFrame(CaseViewer ownerViewer,int topMarginIn, int leftMarginIn, int bottomMarginIn, int rightMarginIn/*, boolean isThumbnail, Color borderColor*/){
		this.ownerViewer =ownerViewer;
		textDisplay = new DisplayTextLayer();	
//		edgeColor = borderColor;		
/*		topMargin =topMarginIn;
		leftMargin =leftMarginIn;
		bottomMargin =bottomMarginIn;
		rightMargin =rightMarginIn;*/
//		this.isThumbnail = isThumbnail;
		setBackground(FRAMECOLOR);
		//setBackground(Color.GREEN);
		selected = false;
		refSelected = false;
		
		setBorder(true);		
		
		if (COLORMAP_NORMALTORED ==null)
			produceColorMap();	
		
		
	
	}
	
	
	
	protected void setup()
	{
		setLayout(null);
		/*		setLayout(new GridBagLayout());
				GridBagConstraints gbc=new GridBagConstraints();
//				gbc.fill = GridBagConstraints.BOTH;
				gbc.anchor = GridBagConstraints.CENTER;
				gbc.gridx=0;
				gbc.gridy=0;
				gbc.weighty=1;
				gbc.weightx=1;
//				gbc.insets = new Insets(topMargin, leftMargin, bottomMargin, rightMargin);
				gbc.insets = new Insets(0, 0, 0, 0);
				add(imageDisplay, gbc);*/
				this.add(imageDisplay, new  Integer( IMAGELAYER), 0);		
				this.add(textDisplay, new  Integer( TEXTLAYER), 0);
				
				//layoutImageAndTextDisplays();
				

				
		/*		imageDisplay.addComponentListener(new ComponentAdapter(){
					public void componentResized(ComponentEvent arg0) {setImageDisplayLocation(); validate();	repaint();}			
				});		*/
				
				this.setFocusable(true);	
				
				this.setDoubleBuffered(true);	
				
		//		toCenterImageOfDisplay = true;
				
				//this.setLayout(new CardLayout());

/*				textLayer.setLocation(0, 0);
				Dimension size = new Dimension(480,100);
				textLayer.setSize(size);
				textLayer.setPreferredSize(size);
				textLayer.setMinimumSize(size);
				textLayer.setMaximumSize(size);*/
		
	}
		
	
	
//	public abstract void layoutImageAndTextDisplays();
	
	
	public Dimension getMaxInnerComponentSize(){
		//	System.out.println("Frame Insets = "+this.getInsets());
			return new Dimension(	this.getSize().width-this.getInsets().left-this.getInsets().right,
									this.getSize().height-this.getInsets().top-this.getInsets().bottom);
		}	
	
	
	public void select(){
//		imageDisplay.getModel().select();
		//displayModel.select();
		selected = true;
		setBorder(false);
//		this.paintBorder(this.getGraphics()); TODO for performance
		repaint();		
	}
	
	
	public void unselect(){
//		imageDisplay.getModel().unselect();	
		//displayModel.unselect();
		selected = false;
		setBorder(false);	
//		System.out.println("Graphics == null = "+this.getGraphics()==null);
//		this.paintBorder(this.getGraphics()); TODO for performance
		repaint();		
	}
	
//	public void setShown(boolean makeShown){	shown = makeShown; 	}
	
	
	
	public void refSelect(){
//		imageDisplay.getModel().refSelect();	
		//displayModel.refSelect();
		refSelected = true;
		//TODO add ref lines to all other displays and then refresh them		
		setBorder(false);
		repaint();		
	}
	
	
	public void refUnselect(){
//		imageDisplay.getModel().refUnselect();
		//displayModel.refUnselect();
		refSelected = false;
		//TODO delete the corresponding ref lines on all other displays and then refresh them
		setBorder(false);
		repaint();		
	}
	
	
	public boolean isSelected(){ return selected;/*displayModel.isSelected();*/	}
	
		
	protected abstract void setBorder(boolean init);
	
	
	
	public void paint(Graphics g){
		System.out.println("GenericImageFrame.paint() ");
		super.paint(g);

	}
	
	public void update(Graphics g){
		System.out.println("GenericImageFrame.update() ");
		super.update(g);

	}
	
	public void repaint(){
		System.out.println("GenericImageFrame.repaint() ");
		super.repaint();

	}
	

	private static void produceColorMap(){
		COLORMAP_NORMALTORED = new Color[COLORMAPSIZE];  // i = 0 : ok,    i = COLORMAPSIZE-1 : bad
		int changepoint = COLORMAPSIZE*1/2;
		//double blueAtChangePoint = linInterPolant(0, COLORMAPSIZE, changepoint, 0, 1); 
		for(int i=0; i<COLORMAPSIZE ; i++)
		{
			int red, green, blue;

/*			if(i<changepoint)
			{
				red = Math.round(linInterPolant(0, 		  changepoint,  i, 0, 255));
				green = Math.round(linInterPolant(0, 		  changepoint,  i, NORMALTEXTCOLOR.getGreen(), 0));
				blue = Math.round(linInterPolant(0, 		  changepoint,  i, NORMALTEXTCOLOR.getBlue(), blueAtChangePoint));
			}
			else
			{
				red = 255; 	//linInterPolant(changepoint, COLORMAPSIZE, i, double scaleLowY, double scaleHighY);;
				green = 0; 	//linInterPolant(changepoint, COLORMAPSIZE, i, double scaleLowY, double scaleHighY);;
				blue = Math.round(linInterPolant(changepoint, COLORMAPSIZE, i, blueAtChangePoint, 0));
			}*/
			
			
			if(i<changepoint)
			{
				red = Math.round(linInterPolant(0, 		  changepoint,  i, 0, 255));
				green = NORMALCHANGINGTEXTCOLOR.getGreen(); // Math.round(linInterPolant(0, 	  changepoint,  i, NORMALCHANGINGTEXTCOLOR.getGreen(), 0));
				blue = 0;	//NORMALCHANGINGTEXTCOLOR.getBlue();	// Math.round(linInterPolant(0, 	  changepoint,  i, NORMALCHANGINGTEXTCOLOR.getBlue(), blueAtChangePoint));
			}
			else
			{
				red = 255; 	//linInterPolant(changepoint, COLORMAPSIZE, i, double scaleLowY, double scaleHighY);;
				green = Math.round(linInterPolant(changepoint, COLORMAPSIZE, i, NORMALCHANGINGTEXTCOLOR.getGreen(), 0));
				blue = 0;	//Math.round(linInterPolant(changepoint, COLORMAPSIZE, i, blueAtChangePoint, 0));
			}

			
/*			System.out.println("COLOR "+i+" IS:" + "\tRed: "+red + "\tGreen" + green + "\tBlue" + blue );*/
			
			COLORMAP_NORMALTORED[i] = new Color(red, green, blue);
		}
		
	}
	
	
	private static float linInterPolant(double scaleLowX, double scaleHighX, double valuePosX, double scaleLowY, double scaleHighY){
		//			A				C					B				Ya					Yb	
		double lambda = (valuePosX-scaleLowX)/(scaleHighX-scaleLowX);
		//    AB					AC
		
		float linInterp =  (float)((1-lambda)*scaleLowY + lambda*scaleHighY);
		//weighted (by lambda) sum of the values;
		return linInterp;
	}
	
	
	public ImageDisplay getImageDisplay(){
		return imageDisplay; 
	}
	
	
	
	
	/*	{
			imageDisplay.setCorrectDisplaySize();
			//this.get
			
			int locationX = this.getInsets().left;// + Math.max(0,);
			int locationY = this.getInsets().top;//  + Math.max(0,);
//			if(getInnerComponentArea().width > imageDisplay.getWidth())
//				locationX += (getInnerComponentArea().width - imageDisplay.getWidth())/2;
//			if(getInnerComponentArea().height > imageDisplay.getHeight())
//				locationY += (getInnerComponentArea().height - imageDisplay.getHeight())/2;
			
			if(getInnerComponentArea().width > imageDisplay.getWidth())
				locationX += (getInnerComponentArea().width - imageDisplay.getInnerPaintArea().width)/2;
			if(getInnerComponentArea().height > imageDisplay.getHeight())
				locationY += (getInnerComponentArea().height - imageDisplay.getInnerPaintArea().height)/2;

			imageDisplay.setLocation(locationX,locationY );


			Dimension correctSize = this.getInnerComponentArea();
			
			textDisplay.setSize(correctSize);
			textDisplay.setMinimumSize(correctSize);
			textDisplay.setMaximumSize(correctSize);
			textDisplay.setPreferredSize(correctSize);
			textDisplay.setLocation(this.getInsets().left,this.getInsets().top);
			
			validate();
			repaint();
		}*/
		
		
	/*	public Dimension getInnerComponentArea(){
		//	System.out.println("Frame Insets = "+this.getInsets());
			return new Dimension(	this.getSize().width-this.getInsets().left-this.getInsets().right,
									this.getSize().height-this.getInsets().top-this.getInsets().bottom);
		}*/
		
		

		
	/*	public Dimension getOuterImageArea(){		
			return 
				new Dimension(
						//this.getInnerComponentArea().width  ,
						//this.getInnerComponentArea().height );
						this.getInnerComponentArea().width-leftMargin-rightMargin  ,
						this.getInnerComponentArea().height-topMargin-bottomMargin );	
		}*/
		
	/*	public Dimension getInnerImageArea(){		

//			System.out.println("\n\nGenericImageFrame.getInnerImageArea()");
			
		//	Dimension innerArea = new Dimension(
		//			this.getOuterImageArea().width-leftMargin-rightMargin - imageDisplay.getInsets().left - imageDisplay.getInsets().right,
		//			this.getOuterImageArea().height-topMargin-bottomMargin- imageDisplay.getInsets().top - imageDisplay.getInsets().bottom );
			
			//Dimension innerArea = imageDisplay.getInnerPaintArea();
			
			System.out.println("\n\tFrame size: "+getSize());
			System.out.println("\tFrame Insets = "+this.getInsets());
			System.out.println("\tMargins = ["+topMargin+","+rightMargin+","+bottomMargin+","+leftMargin+"]");
			System.out.println("\n\tFrame inner component area: "+getInnerComponentArea());
			System.out.println("\tOuter Area: "+getOuterImageArea());
			System.out.println("\tImageDisplay Insets = "+imageDisplay.getInsets());
			System.out.println("\n\tInner Area: "+innerArea);
			
			System.out.println("\n\n");
			
			return 
				new Dimension(
						this.getOuterImageArea().width - imageDisplay.getInsets().left - imageDisplay.getInsets().right,
						this.getOuterImageArea().height- imageDisplay.getInsets().top - imageDisplay.getInsets().bottom );
//			this.getInnerComponentArea().width-leftMargin-rightMargin - 2*ImageDisplay.EDGEWIDTH ,
//			this.getInnerComponentArea().height-topMargin-bottomMargin- 2*ImageDisplay.EDGEWIDTH );
		}*/
		
	
	
//	public void setToCenter(){ /*toCenterImageOfDisplay =true;*/}

	
/*	public void centerImage()	{		
		System.out.println("Frame centerImage()");
		imageDisplay.centerImage();			
//		
//   		if(imageDisplay.getModel().getActImage()!=null)
//			{
//				System.out.println("Size after");
//				System.out.println(	  "-> FrameInnerArea    : ["+getInnerComponentArea().width+","+getInnerComponentArea().height+"]"+
//						"\n      FrameImageArea : ["+getImageArea().width+","+getImageArea().height+"]"+
//						"\n         DisplaySize : ["+imageDisplay.getWidth()+","+imageDisplay.getHeight()+"]"+
//						"\n           ImageSize : ["+imageDisplay.getModel().getActImage().getWidth()+","+imageDisplay.getModel().getActImage().getHeight()+"]");
//			}			
//				
	}*/
	
	
	
//	public ImageDisplay getDisplay(){return imageDisplay;}
	
	/*	public void select(){	selected = true;	}	
	public void unselect(){	selected = false;		}	
//	public void setShown(boolean makeShown){	shown = makeShown; 	}	
	public void refSelect(){	refSelected = true;		}	
	public void refUnselect(){	refSelected = false;	}
	public boolean isSelected(){ return selected; }	*/
	
	
	
/*	public void detachSeries()
	{
		imageDisplay.getModel().detachSeriesFromdisplay();
		imageDisplay.setCorrectDisplaySize();
		revalidate();
//		validate();
		repaint();
	}*/


	
/*	public void windowImage(int xTravelled, int yTravelled)
	{		imageDisplay.windowImage(xTravelled,yTravelled);	}
		
	public void moveImage(int xTravelled, int yTravelled)
	{		imageDisplay.moveImage(xTravelled,yTravelled);	}
		
	public void zoomImage(int notches)
	{		imageDisplay.zoomImage(notches);	}
	
	public void changeImage(int notches) 
	{ 		imageDisplay.changeImage(notches);	}*/
	

	
	
/*	public void repaint(){
		super.repaint();
//		if(imageDisplay!=null)
//			imageDisplay.repaint();
//		if(textLayer!=null)
//			textLayer.repaint();
	}*/
	


	
	
/*	private void setBorder(){		
		
		if (isThumbnail())
		  	if(isSelected())					
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
		else
					
			  	if(isSelected())					
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
					
		
		
	}*/
	
//	private boolean isThumbnail(){return isThumbnail;}
	
/*	 public void update(Graphics g) {
		  paint(g);
		 }*/
	
	
	
	
	
	
	
}

















