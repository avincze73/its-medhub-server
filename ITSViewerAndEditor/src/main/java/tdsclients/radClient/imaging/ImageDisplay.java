package tdsclients.radClient.imaging;


import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import tdsclients.radClient.CaseViewer;
import tdsclients.radClient.imaging.tools.Annotation;
import tdsclients.radClient.imaging.tools.Measurement;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



@SuppressWarnings("serial")
public class ImageDisplay extends JLayeredPane {
	
	
	

	public class AnnotMeasLayer extends JPanel{
		
		public AnnotMeasLayer(){
			super();
			this.setOpaque(false);
			this.setFocusable(false);			
			this.setDoubleBuffered(true);	

		//	this.setBackground(Color.blue);
			
		}
		
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
			System.out.println("ImageDisplay.AnnotationLayer.paintComponent()");
			System.out.println("model.getAnnotMeasLayerModel() = "+model.getAnnotMeasLayerModel());
			Graphics2D gc= (Graphics2D)g;			
			gc.setRenderingHints(toolRenderingHints);
			model.getAnnotMeasLayerModel().listToolsForAllImages();
			//TODO maybe draw these on an image and then output image			
			for(int i=0; i<model.getAnnotMeasLayerModel().getNumAnnotsforActImage(); i++)
				model.getAnnotMeasLayerModel().getAnnotationXforActImage(i).paint(gc,model);
			for(int i=0; i<model.getAnnotMeasLayerModel().getNumMeassforActImage(); i++)
				model.getAnnotMeasLayerModel().getMeasurementXforActImage(i).paint(gc,model);			
			
			//gc.setColor(Color.cyan);
			//gc.fillOval(140, 140, 30, 70);
		}
	    
	    	

		public AnnotMeasLayerModel getToolLayerModel(){ return model.getAnnotMeasLayerModel(); }

		public DDim getRawImageSize(){			return new DDim(model.getSubImageBRCOnRawImage().x,model.getSubImageBRCOnRawImage().y);		}
		
		


//		public DPoint[] translatePoints(){		
//		}

		
	}
	
	
	
	
	public class CornerBorder extends AbstractBorder {
		private int cornerSize;
		private int width;
		private Color color;
		
		public CornerBorder(int cornerSize, int width, Color color)
		{
			this.cornerSize = cornerSize;
			this.width = width;
			this.color = color;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{			
			w=w-1;
			h=h-1;
//			for(int j=0; j<width; j++)
			for(int i=0; i<width; i++)
			{
//				int i=-j;
				g.setColor(CaseViewer.DISPLAYBACKGROUNDCOLOR);
				g.drawRect(x+i, y+i, w-2*i, h-2*i);
				
				g.setColor(color);
				//topleft
				g.drawLine(x+i, y+i, x+cornerSize, y+i);	//vert
				g.drawLine(x+i, y+i, x+i, y+cornerSize);	//horiz
				
				//bottomleft
				g.drawLine(x+i, y+h-i, x+cornerSize, y+h-i);	//vert
				g.drawLine(x+i, y+h-i, x+i, y+h-cornerSize);	//horiz
				
				//bottomright
				g.drawLine(x+w-i, y+h-i, x+w-cornerSize, y+h-i);	//vert
				g.drawLine(x+w-i, y+h-i, x+w-i, y+h-cornerSize);	//horiz
				
				//topright
				g.drawLine(x+w-i, y+i, x+w-cornerSize, y+i);	//vert
//				g.drawLine(x+w-i, y+i, x+w-i, y+cornerSize);	//horiz
				g.drawLine(x+w-i, y+i, x+w-i, y+cornerSize);	//horiz

				
/*				//middleLines
				int sideLength = cornerSize*2;
				g.drawLine(x+w/2-sideLength/2, y+i, x+w/2+sideLength/2, y+i);	//horiz
				g.drawLine(x+i, y+h/2-sideLength/2, x+i, y+h/2+sideLength/2);	//vert
				g.drawLine(x+w/2-sideLength/2, y+h-i, x+w/2+sideLength/2, y+h-i);	//horiz
				g.drawLine(x+w-i, y+h/2-sideLength/2, x+w-i, y+h/2+sideLength/2);	//vert
*/				
				
				
				
//				System.out.println("\tTL Corner coords of CornerBorder :("+(x+i)+","+(y+i)+")");
//				System.out.println("\tBR Corner coords of CornerBorder :("+(x+w-i)+","+(y+h-i)+")");
			}
//			System.out.println("\tTL Corner coords inside CornerBorder :("+(x+width)+","+(y+width)+")");
//			System.out.println("\tBR Corner coords of CornerBorder :("+(x+w-width)+","+(y+h-width)+")");
//			System.out.println("\t\tInner Size Calced From This : ("+((x+w-width)-(x+width))+","+((y+h-width)-(y+width))+")");

			
		}
		
		public Insets getBorderInsets(Component c){	return new Insets(width,width,width,width);	}

		public Insets getBorderInsets(Component c, Insets i){
			i.left =width;
			i.right =width;
			i.top =width;
			i.bottom =width;			
			return i;
		}

		public boolean isBorderOpaque() {return true;}		
		
	}
	
	

	public class SideLabelBorder extends AbstractBorder {
		private int width;
		private Color color;
		
		public SideLabelBorder(int width, Color color)
		{
			this.width = width;
			this.color = color;
		}
		
		public void paintBorder(Component c, Graphics g, int x, int y, int w, int h)
		{			
			Graphics2D gc= (Graphics2D)g;		
			w=w-1;
			h=h-1;
			FontRenderContext frc = gc.getFontRenderContext();
			Font font = new Font("Arial",Font.PLAIN,12);
			
//			System.out.println("\n\n***************************Painting label border????******************************\n");
			gc.setColor(Color.red);	

/*			//outer edge
			gc.drawLine(x, y, x+w, y);
			gc.drawLine(x, y, x, y+h);
			gc.drawLine(x, y+h, x+w, y+h);
			gc.drawLine(x+w, y, x+w, y+h);

			//inner edge
			gc.drawLine(x, y+width, x+w, y+width);
			gc.drawLine(x+width, y, x+width, y+h);
			gc.drawLine(x, y+h-width, x+w, y+h-width);
			gc.drawLine(x+w-width, y, x+w-width, y+h);

			//cross
			gc.drawLine(x, y+h/2, x+w, y+h/2);
			gc.drawLine(x+w/2, y, x+w/2, y+h);*/

			
			gc.setColor(color);	
//			gc.setBackground(Color.red);
			if (model.getAssignedSeries()!= null && model.getActImage() != null)
			{	
//				System.out.println("\n***************************Painting label border******************************\n\n\n");
//				System.out.println("\tBR Corner coords of CornerBorder :("+(x+w-width)+","+(y+h-width)+")");
//				System.out.println("\t\tInner Size Calced From This : ("+((x+w-width)-(x+width))+","+((y+h-width)-(y+width))+")");

				String[] orientationLabels = model.getOrientationLabels();	
				String string;
				TextLayout displayer;
			
				//TOP
				string = orientationLabels[0];		
				displayer = new TextLayout(string, font, frc);
				//int baseLineLowering=
//				displayer.draw(	gc, 
//								x+w/2 - displayer.getAdvance()/2, 
//								y + width/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
				displayer.draw(	gc, 
								x+w/2 - displayer.getAdvance()/2, 
								y + width /*/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);

				//RIGHT
				string = orientationLabels[1];		
				displayer = new TextLayout(string, font, frc);
//				displayer.draw(	gc, 
//						x+w - width/2 - displayer.getAdvance()/2, 
//						y+h/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
				displayer.draw(	gc, 
						x+w - width, 
						y+h/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
				//BOTTOM
				string = orientationLabels[2];		
				displayer = new TextLayout(string, font, frc);
//				displayer.draw(	gc, 
//								x+w/2 - displayer.getAdvance()/2, 
//								y + h - width/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
				displayer.draw(	gc, 
								x+w/2 - displayer.getAdvance()/2, 
								y + h - width + displayer.getAscent() /*- displayer.getDescent()/2*/);

				//LEFT
				string = orientationLabels[3];		
				displayer = new TextLayout(string, font, frc);
//				displayer.draw(	gc,
//						x + width/2 - displayer.getAdvance()/2,
//						y+h/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
				displayer.draw(	gc,
						x + width - displayer.getAdvance(),
						y+h/2 + displayer.getAscent()/2 /*- displayer.getDescent()/2*/);
			}
		}
		
		public Insets getBorderInsets(Component c){	return new Insets(width,width,width,width);	}

		public Insets getBorderInsets(Component c, Insets i){
			i.left =width;
			i.right =width;
			i.top =width;
			i.bottom =width;			
			return i;
		}

		public boolean isBorderOpaque() {return true;}		
		
	}

	
	
	//view
//	public static final int EDGEWIDTH =50;
	public static final int LABELFRAMEWIDTH =30;
	private static final Color SIDELABELCOLOR = new Color(150,150,150);
//	private static final Color SELECTIONCOLOR = new Color(250,0,0);
//	private static final Color SELECTIONCOLORTHUMBNAIL = new Color(250,30,30);
//	private static final Color SELECTIONCOLOR = new Color(250,70,70);
//	private static final Color REFSELECTIONCOLOR = new Color(50,100,250);
//	private static final Color REFSELECTIONCOLOR = new Color(0,250,250);
//	private static final Color REFSELECTIONCOLOR = new Color(90,90,250);
//	private static final Color REFSELECTIONCOLOR = new Color(0,0,250);
//	private static final Color REFSELECTIONCOLOR2 = new Color(0,250,250);
//	private static final Color COMBINEDCOLOR = new Color(230,0,230);
	
//	private static final Color SELECTIONCOLOR2 = new Color(250,150,150);
//	private static final Color SELECTIONCOLOR2 = new Color(200,81,91);
//	private static final Color SELECTIONCOLOR2 = new Color(125,0,0);
//	private static final Color EDGECOLOR1 = new Color(110,110,110);
//	private static final Color EDGECOLOR1 = new Color(0,0,0);
//	private static final Color EDGECOLOR2 = new Color(170,170,170);
//	private static final int DISTANCE = -2;
//	private static final Color IMAGEDISPLAYEDGECOLOR = new Color(35,35,35);
//	private static final Color IMAGEDISPLAYEDGECOLOR = new Color(50,50,50);
	
//	protected static final int IMAGELAYER = 0;
//	protected static final int ANNOTATIONLAYER = 20;
	
	
	
	// model
	private DisplayImageFrame frame;
	//private Color edgeColor;
	private DisplayModel model;
	private AnnotMeasLayer annotMeasLayer;
	
	private RenderingHints imageRenderingHints;
	private RenderingHints toolRenderingHints;
	private BufferedImage image;
	private boolean drawn;
//	private Dimension presumedSize;		//to enable keeping the focus
//	private double lastZoom;			//to enable keeping the focus
//	private int	lastFocusX, lastFocusY;			//to enable keeping the focus


	public RenderingHints getRenderingHints(){ return imageRenderingHints;} 
	
	
	public ImageDisplay(DisplayImageFrame parentFrame, DisplayModel modelIn){
		super();
		frame = parentFrame;
//		edgeColor = null;
		model = modelIn; //new DisplayModel(this, isThumbnailIn);
		//isThumbnail = isThumbnailIn;
		
//		setBorder();
//		setBackground(Color.BLACK);
//		setBackground(Color.RED);
//		setBackground(new Color(180,0,0));
//		setOpaque(true);
//		setOpaque(false);
		
		this.setDoubleBuffered(true);
		
		imageRenderingHints = new RenderingHints (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		imageRenderingHints.put(RenderingHints.KEY_DITHERING,RenderingHints.VALUE_DITHER_DISABLE);
		imageRenderingHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);
//		imageRenderingHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
//		imageRenderingHints.put(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		imageRenderingHints.put(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//		imageRenderingHints.put(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		
		toolRenderingHints = new RenderingHints (RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_OFF);
		toolRenderingHints.put(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		imageRenderingHints.put(RenderingHints.KEY_DITHERING,RenderingHints.VALUE_DITHER_DISABLE);
		toolRenderingHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_SPEED);

		
/*		this.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(EDGEWIDTH, EDGEWIDTH, EDGEWIDTH, EDGEWIDTH, this.getBackground()),
//				BorderFactory.createEmptyBorder(EDGEWIDTH, EDGEWIDTH, EDGEWIDTH, EDGEWIDTH),
				new CornerBorder(12,2,IMAGEDISPLAYEDGECOLOR)));*/

//		this.setBorder(BorderFactory.createCompoundBorder(
//				new SideLabelBorder(LABELFRAMEWIDTH, SIDELABELCOLOR),
//				new CornerBorder(12,2,IMAGEDISPLAYEDGECOLOR)));
		this.setBorder(new SideLabelBorder(LABELFRAMEWIDTH, SIDELABELCOLOR));
		annotMeasLayer = new AnnotMeasLayer();
		
		setLayout(new BorderLayout());
		
//		this.add(annotMeasLayer, new  Integer( GenericImageFrame.ANNOTATIONLAYER), BorderLayout.CENTER);
		this.add(annotMeasLayer, BorderLayout.CENTER);
		this.setLayer(annotMeasLayer, GenericImageFrame.ANNOTATIONLAYER);
		
		
		this.setOpaque(true);
		//this.setBackground(new Color(0,50,0));
		this.setBackground(new Color(10,10,10));
	}
	
	

	
	public void assignDisplayModel(DisplayModel displayModel) { //TODO check
		model = displayModel; 	
		}
	
	public AnnotMeasLayer getAnnotMeasLayer() {return annotMeasLayer; }
	

	
	
	public Dimension getOuterDisplayArea() {return this.getSize();}	
	public Dimension getInnerPaintArea(){ 
			int outerBoundaryW = this.getSize().width;
			int outerBoundaryH = this.getSize().height;
			
			int innerBoundaryW = outerBoundaryW-this.getInsets().left-this.getInsets().right;
			int innerBoundaryH = outerBoundaryH-this.getInsets().top-this.getInsets().bottom;
			Dimension innerBoundary = new Dimension(innerBoundaryW,innerBoundaryH);
			
			return innerBoundary;
		}
	public Dimension getMaxInnerPaintArea(){ 
			int maxOuterBoundaryW = frame.getMaxInnerComponentSize().width;
			int maxOuterBoundaryH = frame.getMaxInnerComponentSize().height;		
			int maxInnerBoundaryW = maxOuterBoundaryW-this.getInsets().left-this.getInsets().right;
			int maxInnerBoundaryH = maxOuterBoundaryH-this.getInsets().top-this.getInsets().bottom;
			Dimension maxInnerBoundary = new Dimension(maxInnerBoundaryW,maxInnerBoundaryH);		
			return maxInnerBoundary;
		}
	
	public Dimension getMaxAreaOccupiableWithActImage(){   //this is used to check whether it can fit in the image area or not
			Dimension actProductImageSize = model.getTheoreticalSizeOfImageProductWithActZoom();		
			return new Dimension(	actProductImageSize.width  + this.getInsets().left + this.getInsets().right,
									actProductImageSize.height + this.getInsets().top  + this.getInsets().bottom);
		}
	
	

	public void setUnDrawn(){drawn=false;}
	
	
	public void paintImage(Graphics2D gc){ 
		if(model.getActImage() != null)
		{
//			gc.drawImage(model.getActImage(), model.getActPosX(), model.getActPosY(), null);		//	add picture at location (implementing the move function)
			if(!drawn)
			{
//				model.fitImage2Display(frame.getImageArea());
				frame.fitImage2Display();
				drawn = true;
//				presumedSize = this.getSize();
//				presumedSize = this.getInnerPaintArea();
				
//				model.saveFocus();
			}
			
			//else
			{
//				System.out.println("\n\n\n-----------------------------------------\n");
				System.out.println("\tImageDisplay.paintImage()");
//				System.out.println("\nInsets: "+this.getInsets());
				
/*				System.out.println(	  "->   ImageFrame size : ["+getSize().width+","+getSize().height+"]"+
						"\n      FrameInnerArea : ["+frame.getInnerComponentArea().width+","+frame.getInnerComponentArea().height+"]"+
						"\n      FrameImageArea : ["+frame.getInnerImageArea().width+","+frame.getInnerImageArea().height+"]"+
						"\n         DisplaySize : ["+getWidth()+","+getHeight()+"]"+
						"\n           ImageSize : ["+model.getActImage().getWidth()+","+model.getActImage().getHeight()+"]");*/
				
/*				Dimension maxOuterAreaInFrame = frame.getMaxInnerComponentSize();
				Dimension maxInnerAreaInFrame = getMaxInnerPaintArea();
				System.out.println("\nFrame size: "+frame.getSize());
				System.out.println("Frame inner component area: "+maxOuterAreaInFrame);
				System.out.println("\nImageDisplay outer Area: "+getOuterDisplayArea());
				System.out.println("ImageDisplay inner Area: "+getInnerPaintArea());				
				System.out.println("\nActImage size 				= "+model.getActImageSize());
				System.out.println("\nModel image pos 			= "+model.getActPos());
				System.out.println("\nSubImage Top Left Corner coords     	= "+model.getSubImageTLCornerCoordsInReadyImage());
				System.out.println(  "SubImage size 				= "+model.getSubImageSize());
				System.out.println(  "SubImage Bottom Right Corner coords 	= "+model.getSubImageBRCornerCoordsInReadyImage());
*/
				int leftborderWidth = this.getInsets().left;
				int topborderWidth = this.getInsets().top;
				
				DPoint subImageTLCoords = model.getSubImageTLCOnRawImage();
				DPoint subImageBRCoords = model.getSubImageBRCOnRawImage();
				int xTL=(int)Math.round(subImageTLCoords.x);
				int yTL=(int)Math.round(subImageTLCoords.y);
				int xBR=(int)Math.round(subImageBRCoords.x);
				int yBR=(int)Math.round(subImageBRCoords.y);
				Dimension innerPaintArea = getInnerPaintArea();

				System.out.println("\nPAINT:");
				
				
				
				System.out.println("\n>>>>>>focusPoint:   (x: "+model.getFocusOnActRawImage().x+",y:"+model.getFocusOnActRawImage().y+")\n");

/*		
//				Dimension maxOuterAreaInFrame = frame.getMaxInnerComponentSize();
//				Dimension maxInnerAreaInFrame = getMaxInnerPaintArea();
				System.out.println("\nFrame size: "+frame.getSize());
				System.out.println(  "Frame maxOuterAreaInFrame:              "	+maxOuterAreaInFrame);
				System.out.println(  "Frame maxInnerAreaInFrame:              "	+maxInnerAreaInFrame);				
				System.out.println("\nImageDisplay outer Area:                "	+getOuterDisplayArea());
				System.out.println(  "ImageDisplay inner Area:                "	+getInnerPaintArea());				
				System.out.println("\nRaw Image size =                        " +"("+model.getRawImageWidth()+","+model.getRawImageHeight()+")");
				System.out.println("\nZoom           =                        "	+ model.getActZoom());		
				System.out.println("\nSubImage size  =                        "	+"("+model.getSubImageSizeOnActRawImage().w+","+model.getSubImageSizeOnActRawImage().h+")");		
				System.out.println(  "SubImage Top Left Corner coords     	= "	+"("+subImageTLCoords.x+","+subImageTLCoords.y+")");
				System.out.println(  "SubImage Bottom Right Corner coords 	= "	+"("+subImageBRCoords.x+","+subImageBRCoords.y+")");
*/
				
				System.out.println("\n");
				
				//	paint subpicture only
/*				boolean stillChanging = gc.drawImage(
						model.getActImage().getSubimage( subimageTLPosX, subimageTLPosY, subimageWidth, subimageHeight),
						borderWidth, 
						borderWidth, 
						null  );*/
				
				//TODO
/*				BufferedImage actImage2Display = model.getActImage().getSubimage( subimageTLPosX, subimageTLPosY, subimageWidth, subimageHeight);				
				boolean stillChanging = gc.drawImage(
						actImage2Display,
						leftborderWidth, 
						topborderWidth, 
						this  );*/
				

				
				BufferedImage actImage2Display = model.getWindowOp().filter(model.getActImage(),image);
				boolean stillChanging = gc.drawImage(
									actImage2Display, 
									leftborderWidth, topborderWidth, leftborderWidth+innerPaintArea.width, topborderWidth+innerPaintArea.height,
									xTL, yTL, xBR, yBR,
									this);
				
				System.out.println("\n\n > > The image is still changing: "+stillChanging+"\n\n");
				
				this.getBorder().paintBorder(null, gc, 0,0,this.getSize().width, this.getSize().height );
				//this.getBorder().p
			}
			
		}
		else
		{
//			System.out.println("\n!!!!!ImageDisplay.paintImage(): THE ACT IMAGE IS NULL!!!\n");
			this.setVisible(false);
//			this.setBackground(Color.BLACK);
		}
		

	}
	
	
	
	
/*	public void repaint(){
		Graphics g = this.getGraphics();
		paint(g);
	}*/
		
	
		
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		//super.paint(g);	
		System.out.println("ImageDisplay.paintComponent()");
		Graphics2D gc= (Graphics2D)g;		
		gc.setRenderingHints(imageRenderingHints);
		paintImage(gc);	

//		settings.put(RenderingHints.KEY_DITHERING,RenderingHints.VALUE_DITHER_ENABLE);
//		gc.setRenderingHints(renderingHints);	
		
		//	drawing picture:
//		paintImage();
		
		
		//TODO	draw glass pane:
		//		draw reference lines
		//		add measurements
		//		add notes/		
	}
	
	
	public void update(Graphics g) {  
		System.out.println("ImageDisplay.update()");	
//		super.update(g);
//		paint(g);	 		
		paintComponent(g);	 		
	}
	 
	public void repaint(){
		System.out.println("ImageDisplay.repaint() ");
		super.repaint();
	}
	

		
}

