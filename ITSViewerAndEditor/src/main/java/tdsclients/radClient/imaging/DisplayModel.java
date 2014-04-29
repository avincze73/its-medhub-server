package tdsclients.radClient.imaging;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;

import casemodule.downloading.LocalCaseDownloader;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.SeriesDTO;

import tdsclients.radClient.CaseViewer;
import tdsclients.radClient.imaging.ImageDisplay.AnnotMeasLayer;
/*import datamodel.ActSessionCaseData;
import datamodel.imagedatamodel.CaseImageDataTree;
import datamodel.imagedatamodel.DicomImage;
import datamodel.imagedatamodel.Series;*/




public class DisplayModel {


	
//	private static final int MAXIMAGEX = 0;
//	private static final int MAXIMAGEY = 0;
//	private static final double WINDOWINGWSCALER =12; //4;
//	private static final double WINDOWINGCSCALER =8; //4;
	private static final double WINDOWINGWSCALER =1; //4;
	private static final double WINDOWINGCSCALER =1; //4;
	private static final double ZOOMINGSCALER = 1.1; //1.05;
//	private static final double MINZOOM = 0.4;
	private static final double MINZOOM = 0.25;
//	private static final double MAXZOOM = 1.5;
	private static final double MAXZOOM = 4;
	private static final double MAXWINDOWWIDTH = Integer.MAX_VALUE-1;
	
	
	private ImageDisplay imageDisplay;
	private GenericImageFrame imageFrame;
	private AnnotMeasLayerModel annotMeasLayerModel;
	

	private boolean isThumbnail;
	private int minImageX;
	private int minImageY;
		
	
	//image model	
//	private ActSessionCaseData caseDownloader;
//	private CaseImageDataTree dataTree;
	private LocalCaseDownloader caseDownloader;
//	private CaseImageDataTree dataTree;
	private int studyNum;
	private int seriesNum;
	private SeriesDTO series;
	
	private int imageNum;
//	private BufferedImage[] rawImages;	// stores: 	non-windowed image values
//	private BufferedImage[] readyImages;	// stores: 	display-ready images of the actual series created
											//			with the actual settings  
//	private byte[] readyImageStates;		// keeps track of the state of the ready images 
	private BufferedImage readyImage;
//	private VolatileImage rawImage;
//	private VolatileImage readyImage;
	
	//settings and state model	
	private double zoom;			//size multiplier
	//private int posx,posy;		//the subimageWindow TL corner coords wrt the actimage TL corner
	private DPoint focusPoint;		//the focus point of the display on the raw image = the middle point of the subImage
	private double rotation;		//TODO need to put it in rotated source image...
//	private Window actWindow;
	private Window actWindow;
//	private double windowWidth;
//	private double windowCenter;
	private boolean showRefLine;
//	private double[] presumedFocus;
	
	//TODO
	//private DistMeasurement[] distMeasures;
	//private PointDensMeasurement[] pointDensMeasures;
	//private AreaDensMeasurement[] areaDensMeasures;
	//private Line[] refLines;
	
	

	//  ***  CONSTRUCTORS AND RELATED METHODS ***
 	public DisplayModel(/*ImageDisplay linkedDisplay,*/ boolean isThumbnailIn, GenericImageFrame linkedImageFrame) 
	{	
		//display = linkedDisplay;
		isThumbnail = isThumbnailIn;		
		imageFrame = linkedImageFrame;
		annotMeasLayerModel = new AnnotMeasLayerModel( this);
//		refSelected = false;		
		
		zoom = 1;
		
		rotation = 0;
		showRefLine = false;	
	}
 	
 	
	public void assignImageDisplay(ImageDisplay linkedDisplay){	imageDisplay = linkedDisplay;}
	public void assignAnnotMeasLayer(ImageDisplay.AnnotMeasLayer annotMeasLayer){	annotMeasLayerModel.assignAnnotMeasLayer(annotMeasLayer);}
	
	
	public DisplayModel getCopy()
	{
		DisplayModel copy = new DisplayModel(this.isThumbnail,this.imageFrame);		
		copy.imageDisplay = this.imageDisplay;
		
		
//		copy.selected = this.selected;
//		copy.refSelected = this.refSelected;		
		copy.minImageX = this.minImageX;
		copy.minImageY = this.minImageY;
		
		copy.caseDownloader = this.caseDownloader;
//		copy.dataTree = this.dataTree; 	//TODO  check if needed!!!
		copy.studyNum = this.studyNum;
		copy.seriesNum = this.seriesNum;
		copy.series = this.series;
		
		copy.imageNum = this.imageNum;
		
/*		if(this.rawImages == null)
			copy.rawImages =null;
		else
		{
			copy.rawImages = new BufferedImage[this.rawImages.length];
			for(int i=0; i< this.rawImages.length; i++)
				copy.rawImages[i] = copyBufferedImage(this.rawImages[i]);
		}*/

/*		if(this.readyImages == null)
			copy.readyImages =null;
		else
		{
			copy.readyImages = new BufferedImage[this.readyImages.length];
			for(int i=0; i< this.readyImages.length; i++)
				copy.readyImages[i] = Utility.copyBufferedImage(this.readyImages[i]);
		}
		
		if(this.readyImageStates == null)
			copy.readyImageStates =null;
		else
		{
			copy.readyImageStates = new byte[this.readyImageStates.length];
			for(int i=0; i< this.readyImageStates.length; i++)
				copy.readyImageStates[i] = this.readyImageStates[i];			
		}	*/										
		copy.readyImage = this.readyImage;
		
		
		if(this.actWindow != null)
			copy.actWindow = this.actWindow.getCopy();
		else 
			copy.actWindow = null;
		

		copy.zoom = this.zoom;
//		setPosX(displayModelToCopy.posx);	
//		setPosY(displayModelToCopy.posy);		
		if (this.focusPoint == null )copy.focusPoint= null;
		else copy.focusPoint = new DPoint(this.focusPoint.x,this.focusPoint.y);
//		copy.posx = this.posx;	
//		copy.posy = this.posy;		
		copy.rotation = this.rotation;
		//TODO setWindow to suggested
		copy.showRefLine = this.showRefLine;	
				
		
		copy.annotMeasLayerModel=this.annotMeasLayerModel.getCopy(copy);
		
		
		return copy;
		
		//return this;
	}
	
	
	
	
	
	
	
	// *** TOOL METHODS
	
	
	

	//hit check
	public int[] pointBelongsToTool(int x, int y){		
		return annotMeasLayerModel.pointBelongsToTool(x, y);  
	}

	//addition check
	public boolean canAddAnnotToActImage(){ return annotMeasLayerModel.canAddAnnotToActImage(); }
	public boolean canAddMeasToActImage(){ 	return annotMeasLayerModel.canAddMeasToActImage();	}
	
	
	//unselect all
	public void unselectAllToolsForAllImages(){
		annotMeasLayerModel.unselectAllToolsForAllImages();
	}
	
	//select one
	public void selectToolXForActImage(int toolType, int numTool){
		annotMeasLayerModel.selectToolXForActImage(toolType, numTool);
	}
//	public void selectMeasXForActImage(int numAnnotation){
//		annotMeasLayerModel.selectMeasXForActImage(numAnnotation);
//	}

	
	//get tool selection info
	public int[] checkToolSelection(){		
		return annotMeasLayerModel.checkToolSelection();
	}
	
	
	//del one
	public void deleteSelectedToolOfActImage( int toolType, int toolNum){
		annotMeasLayerModel.deleteSelectedToolOfActImage( toolType, toolNum);
	}

	
	//clear
	public void clearAnnotMeasLayerForActImage(){ annotMeasLayerModel.clearAnnotMeasLayerForActImage(); }
	
	
	//check proposed movement
	public double[] findApplicablePartOfToolMovement (int toolType, int toolNumInList, int hitCode1, int hitCode2, int cursorOffsetX, int cursorOffsetY, int newX, int newY) {
		return annotMeasLayerModel.findApplicablePartOfToolMovement(toolType, toolNumInList, hitCode1, hitCode2, cursorOffsetX, cursorOffsetY, newX, newY, this.getSubImageRectangleInOriginalImageSpace());	
	}

	//move
	public void moveSelectedToolOfActImage(int toolType, int toolNumInList, int hitCode1, int hitCode2, double toolMovementApplicableChangeX, double toolMovementApplicableChangeY) {
		annotMeasLayerModel.moveSelectedToolOfActImage(toolType, toolNumInList, hitCode1, hitCode2, toolMovementApplicableChangeX, toolMovementApplicableChangeY);	
	}
	
	
	//add
	public void addMPointDensToActImage(int xPosOnDisplayedImage, int yPosOnDisplayedImage){
		DPoint pointOnOrigImage = this.pointTransformFromShownImageToOriginalImage(new Point(xPosOnDisplayedImage,yPosOnDisplayedImage)); 
		annotMeasLayerModel.addMPointDensToActImage(pointOnOrigImage);		
	}
	
	public void addMDistanceToActImage(int xPosOnDisplayedImage, int yPosOnDisplayedImage)
	{
		DPoint pointOnOrigImage = this.pointTransformFromShownImageToOriginalImage(new Point(xPosOnDisplayedImage,yPosOnDisplayedImage)); 
		annotMeasLayerModel.addMDistanceToActImage(pointOnOrigImage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// *** IMAGE OPERATION METHODS
	
	
	//individual operation checks
	
	
/*	public boolean isSizingOfImagesCorrect() {
		Dimension actImageSize = getActImageSize();
		Dimension subImageSize = getSubImageSize();
		return subImageSize.width <= actImageSize.width && subImageSize.height <= actImageSize.height;
	}*/
	
	public boolean isPositioningCorrect(double x, double y) {
		Dimension rawImageSize = getRawImageSize();
		DDim subImageSize = this.getSubImageSizeOnActRawImage();
		
//		DPoint subImageTLCornerCoords = this.getSubImageTLCOnActFinalImage();
//		DPoint subImageBRCornerCoords = this.getSubImageBRCOnActFinalImage();
		DPoint subImageTLCornerCoords = new DPoint(x-subImageSize.w,y-subImageSize.h);
		DPoint subImageBRCornerCoords = new DPoint(x+subImageSize.w,y+subImageSize.h);
		
		boolean topLeftCornerIsFine = subImageTLCornerCoords.x >= 0 && subImageTLCornerCoords.y >= 0;
		boolean bottomRightCornerIsFine = subImageBRCornerCoords.x <= rawImageSize.width && subImageBRCornerCoords.y <= rawImageSize.height;
		return topLeftCornerIsFine && bottomRightCornerIsFine;
	}
	
	
	private DPoint findClosestCorrectFocusPosition(double x, double y){
		Dimension rawImageSize = this.getRawImageSize();
		DDim subImageSize = this.getSubImageSizeOnActRawImage();
		
//		DPoint subImageTLCornerCoords = this.getSubImageTLCOnActFinalImage();
//		DPoint subImageBRCornerCoords = this.getSubImageBRCOnActFinalImage();
		DPoint subImageTLCornerCoords = new DPoint(x-subImageSize.w/2.0,y-subImageSize.h/2.0);
		DPoint subImageBRCornerCoords = new DPoint(x+subImageSize.w/2.0,y+subImageSize.h/2.0);
		
		System.out.println("\tfindClosestCorrectFocusPosition():");
		System.out.println("\t\tSubimage size:          (w:"+subImageSize.w+", h:"+subImageSize.h+")");
		System.out.println("\t\tSubimage size /2:       (w:"+subImageSize.w/2.0+", h:"+subImageSize.h/2.0+")");
		System.out.println("\t\tAct pos:                (x:"+getFocusOnActRawImage().x+", y:"+getFocusOnActRawImage().y+")");
		System.out.println("\t\tTL coords:              (x:"+subImageTLCornerCoords.x+", y:"+subImageTLCornerCoords.y+")");
		System.out.println("\t\tBR coords:              (x:"+subImageBRCornerCoords.x+", y:"+subImageBRCornerCoords.y+")");
		
		double correctFocusPosX = x;
		double correctFocusPosY = y;
		if(subImageTLCornerCoords.x <= 0)							//left boundary problem
			{
			System.out.println("\t\t> left boundary problem");
			correctFocusPosX = subImageSize.w/2.0;
			}
		else 
			if(subImageBRCornerCoords.x >= rawImageSize.width)		//right boundary problem
				{
				System.out.println("\t\t> right boundary problem");
				correctFocusPosX = rawImageSize.width-subImageSize.w/2.0;				
				}
		
		if(subImageTLCornerCoords.y <= 0)							//top boundary problem
			{
			System.out.println("\t\t> top boundary problem");
			correctFocusPosY = subImageSize.h/2.0;
			}
		else 
			if(subImageBRCornerCoords.y >= rawImageSize.height)	//bottom boundary problem
				{
				System.out.println("\t\t> bottom boundary problem");
				correctFocusPosY = rawImageSize.height-subImageSize.h/2.0;	
				}
		return new DPoint(correctFocusPosX,correctFocusPosY);
	}
	
	
	
	private void correctActPosition(){
		if(isPositioningCorrect(focusPoint.x,focusPoint.y)) return;
		//calc needed Repositioning
		DPoint closestCorrectPos = findClosestCorrectFocusPosition(this.getFocusOnActRawImage().x, this.getFocusOnActRawImage().y);
		//perform repositioning
		performUnderlyingPositioning(closestCorrectPos.x, closestCorrectPos.y);
	}
		
	
	private int[] findCorrectPartOfIntendedMovement(int mouseMoveX, int mouseMoveY){	//correct, if subimageBoundaries would go over actImage boundaries
//		if (!isSizingOfImagesCorrect())		return null;	//fatal error
		double scaledIntendedMovementX = mouseMoveX/zoom;
		double scaledIntendedMovementY = mouseMoveY/zoom;
		
		
		//gather info
		DPoint prevPos 					= this.getFocusOnActRawImage();
		DPoint intendedPos 	= new DPoint(prevPos.x+scaledIntendedMovementX,prevPos.y+scaledIntendedMovementY);
		
		
		System.out.println("\nfindCorrectPartOfIntendedMovement()");
		System.out.println("MouseMove:                 (x:"+mouseMoveX+", y:"+mouseMoveY+")");
		System.out.println("Zoom:                         "+zoom);
		System.out.println("Intended movement:         (x:"+scaledIntendedMovementX+", y:"+scaledIntendedMovementY+")");
		System.out.println("Act focus:                 (x:"+prevPos.x+", y:"+prevPos.y+")");
		System.out.println("Intended pos:              (x:"+intendedPos.x+", y:"+intendedPos.y+")");
		
		DPoint closestCorrectPos = findClosestCorrectFocusPosition(intendedPos.x, intendedPos.y);
		
		double correctMovementX = closestCorrectPos.x-prevPos.x;
		double correctMovementY = closestCorrectPos.y-prevPos.y;
		double correctMovementXBackScaled = correctMovementX*zoom;
		double correctMovementYBackScaled = correctMovementY*zoom;
		int correctMovementIntX = Utility.roundTowardsZero(correctMovementXBackScaled);
		int correctMovementIntY = Utility.roundTowardsZero(correctMovementYBackScaled);
		

		System.out.println("Correct pos:               (x:"+closestCorrectPos.x+", y:"+closestCorrectPos.y+")");
		System.out.println("Correct movement (double): (x:"+correctMovementX+", y:"+correctMovementY+")");
		System.out.println("Corr. mov. scaled(double): (x:"+correctMovementXBackScaled+", y:"+correctMovementYBackScaled+")");
		System.out.println("Correct movement (int)   : (x:"+correctMovementIntX+", y:"+correctMovementIntY+")");
		
		return new int[]{correctMovementIntX,correctMovementIntY};
		
	}
	
	
	
		

 	
	
	private int findPartOfScrollDiffForCorrectZoom(int mouseScrollDiff){

		double intendedZoom = calcIntendedZoomFromScrollDiff(mouseScrollDiff);		
			
		double correctZoom 	= Math.min(MAXZOOM, Math.max(MINZOOM,intendedZoom));
		
		//reverse-calculate scrollDiff From correct final zoom value
		double correctChangeValue;
		int scrollDiffForCorrectZoom; 		
		if (Utility.compareDoublesToPrecesion(zoom, correctZoom, 2)) 
			{
				//correctChangeValue = 0;
				scrollDiffForCorrectZoom = 0; //-Utility.roundTowardsZero(correctChangeValue/ZOOMINGSCALER);
			}
		else if ( correctZoom > zoom  ) 	//zoom value increased
			{
				correctChangeValue = correctZoom/zoom;
				scrollDiffForCorrectZoom = Utility.roundTowardsZero(-correctChangeValue/ZOOMINGSCALER);
			}
		else //if ( correctZoom < zoom  ) 	//zoom value decreased
		{
			correctChangeValue = -zoom/correctZoom;
			scrollDiffForCorrectZoom = Utility.roundTowardsZero(-correctChangeValue/ZOOMINGSCALER);
		}
		
		return scrollDiffForCorrectZoom;
	}

	private int findPartOfScrollDiffForChangeToCorrectImageNum(int mouseScrollDiff){
		
		int minImageNum = 0;
		int maxImageNum = series.getNumImages()-1;
		int intendedImageNum = getActImageNum()+mouseScrollDiff;
		int correctImageNum  = Math.min(maxImageNum, Math.max(minImageNum,intendedImageNum));		
		int correctChange = correctImageNum-getActImageNum();		
		return correctChange;	
		
/*		int minImageNum = 0;
		int maxImageNum = series.getNumImages()-1;
		int intendedImageNum = getActImageNum()+mouseScrollDiff;
		int correctImageNum  = Math.min(maxImageNum, Math.max(minImageNum,intendedImageNum));		
		return correctImageNum;	*/	
	}
	
	private int[] findPartOfMouseMovementForChangeToCorrectWindow(int mouseXMove, int mouseYMove){
	
		double[] proposedWindow = calcIntendedWindowFromMouseMove( mouseXMove,  mouseYMove);
//	  	double wWidthChange  = mouseXMove*WINDOWINGWSCALER;
		double wCenterChange = mouseYMove*WINDOWINGCSCALER;
		double proposedWWidth  = proposedWindow[0];
		double proposedWCenter = proposedWindow[1];

		double actWWidth  = getActWindowWidth() ;
		double actWCenter = getActWindowCenter();
		double seriesMin  = series.getSeriesMinRawValue() ;
		double seriesMax  = series.getSeriesMaxRawValue();
		
		double correctWWidth  = Math.min(MAXWINDOWWIDTH, Math.max(1,proposedWWidth)); 
		double correctWCenter = proposedWCenter;		//if there are no problems  
		
		if( seriesMin > actWCenter + actWWidth/2 ) 		//whole image is white
			{
		//		newWWidth  = actWWidth;					//can't change window width when the window is off the scale of the image
		//		System.out.println("window high");
				if(wCenterChange <= 0)
					correctWCenter = actWCenter;		//can only change window center upwards			
			}
		else
			if( seriesMax < actWCenter - actWWidth/2 ) 	//whole image is black
				{
			//		newWWidth  = actWWidth;				//can't change window width when the window is off the scale of the image
			//		System.out.println("window low");
					if(wCenterChange >= 0)
						correctWCenter = actWCenter;	//can only change window center upwards
				}
			else
				System.out.println("Normal windowing");	//center changes normally

		
		//now reverse-calculate the mouseMovements that would have lead to this window
		double winWchangeToCorrect =  correctWWidth-actWWidth;
		double winCchangeToCorrect =  correctWCenter-actWCenter;
		int mouseXMoveForCorrectResult =  Utility.roundTowardsZero(winWchangeToCorrect/WINDOWINGWSCALER);
		int mouseYMoveForCorrectResult =  Utility.roundTowardsZero(winCchangeToCorrect/WINDOWINGCSCALER);
		
		return new int[]{mouseXMoveForCorrectResult,mouseYMoveForCorrectResult};
	}

	
	
	//common operation checks
	
	public int[] findCorrectFinalStateFromChange(int operation, int[] params){
		switch(operation)
		{		
			case CaseViewer.MOVEOP: 		return findCorrectPartOfIntendedMovement(params[0],params[1]);
			case CaseViewer.WINDOWOP:		return findPartOfMouseMovementForChangeToCorrectWindow(params[0],params[1]);
			case CaseViewer.ZOOMOP:			return new int[]{findPartOfScrollDiffForCorrectZoom(params[0]),0};
			case CaseViewer.CHANGEIMAGEOP:	return new int[]{findPartOfScrollDiffForChangeToCorrectImageNum(params[0]),0};				
		}
		System.out.println("\n\n\n!!!! OPERATION CODE NOT FOUND!!!\n\n\n\n");			
		return null;	
	}
	
	public boolean wouldTheOperationMakeAChangeOnThisDisplay(int operation, int[] params){
		switch(operation)
		{
			case CaseViewer.RESETOP:
					if(this.getActImage() != null)	return true;
					else return false;	//always takes it as a change ... //TODO implement better: if the actual state is the same as the original state, then recognize it!
			case CaseViewer.DELETEOP:
					if(this.getActImage()!=null)return true;		//no deletion if there is no image
					else return false;								//delete otherwise
			case CaseViewer.CLEAROP:
				if(this.getActImage()!=null)return true;		//no deletion if there is no image
				else
				{				
					//no need to clear if there are no annotations or measurements
					return (annotMeasLayerModel.getNumAnnotsforActImage() >0 || annotMeasLayerModel.getNumMeassforActImage()>0); 
				}					
			case CaseViewer.CENTEROP:
				{		
					if(this.getActImage()==null)return false;	//if image null, exit;	
					DPoint posForCentering = findFocusPosOnRawImageForCentering();
					if((posForCentering.x != getFocusOnActRawImage().x || posForCentering.y != getFocusOnActRawImage().y) )	return true;
					else return false;
				}
			case CaseViewer.FITDISPOP:
				{
					if(this.getActImage()==null)return false;	//if image null, exit;
					if (Utility.compareDoublesToPrecesion(findDisplayFitZoom(), zoom, 2)) return false;	//if image size is fitted (and hence position is correct)
					else return true; //new int[]{Math.round((float)newZoom*100)};						//else fit2disp
				}
			case CaseViewer.ZOOM100OP:
					if (this.getActImage() == null || zoom==1 ) 	return false;
					else return true;
			case CaseViewer.RESETWINDOWOP:
				{
					if(this.getActImage() == null || series ==null) return false;
					double resetWindowW  = series.getDicomImageList().get(imageNum).getProcessedDicomImage().getFirstGivenWindowWidth(); 
					double newWCenter = series.getDicomImageList().get(imageNum).getProcessedDicomImage().getFirstGivenWindowCenter();
					if(resetWindowW == actWindow.getWindowWidth() && newWCenter == actWindow.getWindowCenter())	return false;
					else return true;
				}					
			case CaseViewer.AUTOWINDOWOP:
					if(findAutoWindow()==null) return false;
					else return true;					
			case CaseViewer.SETWINDOWOP:
				{
					if((this.getActImage() == null)) return false;
					if( !series.getModality().equals("CT") || (params[0] == Math.round(actWindow.getWindowWidth()) && params[1] == Math.round(actWindow.getWindowCenter()))  )	return false;
					else return true;
				}
		}			
		System.out.println("\n\n\n!!!! OPERATION CODE NOT FOUND!!!\n\n\n\n");			
		return false;		
	}

	
	
	//checked core operations
		
 	public void checkMoveAndPerform(int mouseXMove, int mouseYMove){	
		int[] correctMouseMove = findCorrectPartOfIntendedMovement(mouseXMove,mouseYMove);
		//int[] correctEndPosition = new int[]{getActPosX()+correctMove[0],getActPosY()+correctMove[1]};
		performUnderlyingMovement( correctMouseMove[0], correctMouseMove[1]);		
	}	
	
	public void checkImageChangeAndPerform(int mouseScrollDiff){
		int correctImageNumChange = findPartOfScrollDiffForChangeToCorrectImageNum(mouseScrollDiff);
		performUnderlyingImageChange( correctImageNumChange );		
	}	

	public void checkZoomAndPerform(int mouseScrollDiff){
		int scrollDiffForCorrectZooming  = findPartOfScrollDiffForCorrectZoom(mouseScrollDiff);
		double newZoom = calcIntendedZoomFromScrollDiff(scrollDiffForCorrectZooming);
		performUnderlyingZoomSetting(newZoom);
	}	

	public void checkWindowingAndPerform(int mouseXMove, int mouseYMove){
		int[] mouseMovementForCorrectWindowing  = findPartOfMouseMovementForChangeToCorrectWindow(mouseXMove,mouseYMove);
		double[] newWindow = calcIntendedWindowFromMouseMove(mouseMovementForCorrectWindowing[0], mouseMovementForCorrectWindowing[1]);
		performUnderlyingWindowSetting(newWindow[0],newWindow[1]);		
	}	


	
	//unchecked underlying operations
	
	private void performUnderlyingPositioning(double xPos, double yPos){		this.setPositionByFocus(xPos, yPos);	}
	
	void performUnderlyingMovement(double xMove, double yMove){
		//	the signs are equal to those of the actPos change
		
		DPoint actPos = this.getFocusOnActRawImage();
		System.out.println("\nperformUnderlyingMovement()");
		System.out.println("MouseMove: (x:"+xMove+", y:"+yMove+")");
		System.out.println("Zoom:                         "+zoom);
		System.out.println("Intended movement: (x:"+xMove/zoom+", y:"+yMove/zoom+")");
		System.out.println("Act focus: (x:"+actPos.x+", y:"+actPos.y+")");
		System.out.println("Intended pos: (x:"+(actPos.x + xMove/zoom)+", y:"+(actPos.y + yMove/zoom)+")");
		performUnderlyingPositioning( actPos.x + xMove/zoom, actPos.y + yMove/zoom);
		//TODO save focus???
		//this.saveFocus();
	}	
	
	void performUnderlyingImageChange(int change){		performUnderlyingLoadOfImageWithActParams(imageNum + change);	}

	private void performUnderlyingLoadOfImageWithActParams(int newImageNumber){		
		imageNum = newImageNumber;
		readyImage = this.getRawImage();
//		readyImage = applyWindow(this.getRawImage());
		//readyImages[imageNum].setAccelerationPriority(1);
	}

	
	void performUnderlyingZooming(int  mouseScrollDiff){	
		double zoom= calcIntendedZoomFromScrollDiff(mouseScrollDiff);
		performUnderlyingZoomSetting(zoom);
	}
	
	void performUnderlyingZoomSetting(double newZoom){	
		zoom= newZoom;
		performUnderlyingLoadOfImageWithActParams(getActImageNum());
		correctActPosition();
		//TODO maybe need position correction, because actImageSize changed		
	}
	
	
	
	void performUnderlyingWindowing(int xMove, int yMove){
		double[] newWindow = calcIntendedWindowFromMouseMove(xMove, yMove);
		performUnderlyingWindowSetting(newWindow[0],newWindow[1]);			
	}
	
	void performUnderlyingWindowSetting(double newWindowWidth, double newWindowCenter){
		setWindow(newWindowCenter,newWindowWidth);		
		performUnderlyingLoadOfImageWithActParams(getActImageNum());		
	}
		
/*	private BufferedImage applyWindow(VolatileImage BufferedImage raw){
		return   actWindow.convert(raw);		
	}*/
	
	

	
	
	//extra operations
	
	
	public void centerImage()
	{
//		System.out.println("Model centerImage()");
		DPoint posForCentering = findFocusPosOnRawImageForCentering();
		performUnderlyingPositioning(posForCentering.x,posForCentering.y);
	}
	
	
	public void fitImage2Display(/*Dimension displayImageArea*/)
	{
//		System.out.println("DisplayModel.fitImage2Display()");
		double fitZoom = findDisplayFitZoom();
		performUnderlyingZoomSetting(fitZoom);
	}
	
	
	public void zoom100()	
	{//	System.out.println("DisplayModel.zoom100()");
		performUnderlyingZoomSetting(1);			 			//zoom to 100%
	}
	
	
	public void resetWindow()
	{		
		if(series.getDicomImageList().get(0).getProcessedDicomImage().isNoWindowGiven())
			{	//no window given, set autowindow
				double[] autoWindow = findAutoWindow();				
				performUnderlyingWindowSetting(autoWindow[0],autoWindow[1]);
			}				
		else //some window properly given 
			{	
				double newWWidth  = series.getDicomImageList().get(imageNum).getProcessedDicomImage().getFirstGivenWindowWidth(); 
				double newWCenter = series.getDicomImageList().get(imageNum).getProcessedDicomImage().getFirstGivenWindowCenter();
				performUnderlyingWindowSetting(newWWidth,newWCenter);
			}
	}
	
	
	public void autoWindow()
	{		
		double[] autoWindow = findAutoWindow();
		performUnderlyingWindowSetting(autoWindow[0],autoWindow[1]);
	}
		
	
	public void presetWindow(double w, double c){	performUnderlyingWindowSetting(w, c);	}
	
	
	
	//auxiliary calculations
	
 	private double calcIntendedZoomFromScrollDiff(int mouseScrollDiff){
		double changeValue  = -mouseScrollDiff*ZOOMINGSCALER;
		//		System.out.println("!! changeValue: "+changeValue);
		double newZoom;		
		if(changeValue < 0)			newZoom = zoom/(-changeValue);		//shrinking actImage, zoom value decreases, zooming out
		else if (changeValue == 0)	newZoom = zoom;	
		else /*(changeValue > 0)*/	newZoom = zoom*changeValue;	//growing actImage,   zoom value increases, zooming in
		return newZoom;
	}

	private double[] calcIntendedWindowFromMouseMove(int mouseXMove, int mouseYMove){
	  	double wWidthChange  = mouseXMove*WINDOWINGWSCALER;
		double wCenterChange = mouseYMove*WINDOWINGCSCALER;
		double actWWidth  = getActWindowWidth() ;
		double actWCenter = getActWindowCenter();
		double newWWidth  = actWWidth + wWidthChange;
		double newWCenter = actWCenter+ wCenterChange;
		return new double[]{newWWidth,newWCenter};
	}
	
	private DPoint findFocusPosOnRawImageForCentering(){
		if(this.getActImage()==null)return null;	//if image null, exit;				
		return new DPoint(this.getRawImageWidth()/2.0,this.getRawImageHeight()/2.0);
	}
	
	private double findDisplayFitZoom(){
		double fitZoom;			

		int maxDisplayableImageWidth  = imageDisplay.getMaxInnerPaintArea().width;
		int maxDisplayableImageHeight = imageDisplay.getMaxInnerPaintArea().height;
		
		int imageWidth  = getRawImageWidth();
		int imageHeight = getRawImageHeight();
		double displayYperXRatio = (double)maxDisplayableImageHeight/(double)maxDisplayableImageWidth;
		double imageYperXRatio   = (double)imageHeight/(double)imageWidth;	
		
		if(imageYperXRatio<displayYperXRatio)	// fitted image will fit in width but will be smaller in height than the display				
			fitZoom = (double)maxDisplayableImageWidth /(double)imageWidth;				
		else									// fitted image will fit in height but will be narrower than the display
			fitZoom = (double)maxDisplayableImageHeight /(double)imageHeight;						

		return fitZoom;
	}
	
	private double[] findAutoWindow()
	{
		if(getActImage() == null) return null;
		if(series == null) return null;
		
		double minRawValue = series.getSeriesMinRawValue();
		double maxRawValue = series.getSeriesMaxRawValue();
		
		double proposedWWidth  = (maxRawValue-minRawValue)/2; 
		double proposedWCenter = (minRawValue+maxRawValue)/2;

		//TODO need to transform
		
		double newWWidth  = Math.min(MAXWINDOWWIDTH, Math.max(1,proposedWWidth)); 
		double newWCenter = proposedWCenter;
		
		System.out.println("AutoWindow newWWidth = "+newWWidth+", newWCenter = "+newWCenter);
		System.out.println("AutoWindow getActWindowWidth() = "+getActWindowWidth()+", getActWindowCenter() = "+getActWindowCenter());
		if(newWWidth == getActWindowWidth() && newWCenter == getActWindowCenter()) return null;
		
		return new double[]{newWWidth,newWCenter};
	}

	
	
	private double[] findAutoWindowForInit(SeriesDTO series)
	{
		System.out.println("\n\nfindAutoWindowForInit\n\n");
		double minRawValue = series.getSeriesMinRawValue();
		double maxRawValue = series.getSeriesMaxRawValue();

		//TODO need to transform
		
		double proposedWWidth  = (maxRawValue-minRawValue)/2; 
		double proposedWCenter = (minRawValue+maxRawValue)/2;

		double newWWidth  = Math.min(MAXWINDOWWIDTH, Math.max(1,proposedWWidth)); 
		double newWCenter = proposedWCenter;
		
//		System.out.println("AutoWindow newWWidth = "+newWWidth+", newWCenter = "+newWCenter);
//		System.out.println("AutoWindow getActWindowWidth() = "+getActWindowWidth()+", getActWindowCenter() = "+getActWindowCenter());
		
		return new double[]{newWWidth,newWCenter};
	}
	
	
	
	
	//ADMINISTRATION METHODS
	
	public void assignSeries(SeriesDTO seriesIn, boolean freshLoad)	//test version
		{				
			studyNum=0;
			seriesNum = 0;		
			series = seriesIn;		
	//		rawImages = new BufferedImage[series.getNumImages()];			//TODO check this for changing back...
//			readyImages = new BufferedImage[series.getNumImages()];
//			readyImage = new BufferedImage[series.getNumImages()];
			imageNum = 0;
			
			//init display parameters
			DicomImageDTO firstImage = series.getDicomImageList().get(0);		
			
			setPositionByFocus(firstImage.getProcessedDicomImage().getWidth()/2.0,firstImage.getProcessedDicomImage().getHeight()/2.0);
//			setPositionByFocus(firstImage.getProcessedDicomImage().getWidth()/2.0-0.5,firstImage.getProcessedDicomImage().getHeight()/2.0-0.5);
			zoom = 1;

			if(firstImage.getProcessedDicomImage().isNoWindowGiven())
				{	//no window given, set autowindow
					double[] autoWindow = findAutoWindowForInit(series);				
					setWindow(autoWindow[1],autoWindow[0]);
				}				
			else //some window properly given 
				setWindow(firstImage.getProcessedDicomImage().getFirstGivenWindowCenter(),firstImage.getProcessedDicomImage().getFirstGivenWindowWidth());
			
			
			rotation = 0;
			showRefLine = false;
			
			System.out.println("\n>>>>>>focusPoint:   (x: "+focusPoint.x+","+focusPoint.y+")\n");
			System.out.println("SeriesLength: "+series.getNumImages());
	//		System.out.println("RawImagesLength: "+rawImages.length);
	//		System.out.println("ReadyImagesLength: "+readyImages.length);
			
			performUnderlyingLoadOfImageWithActParams(0);
			imageDisplay.setUnDrawn();
			
	//		annotMeasLayerModel.newSeriesAssigned();
	//		if(freshLoad)
	//			centerImage();		
		}	
	public void detachSeriesFromdisplay()
		{
			series = null;
			readyImage = null;
	//		rawImages = null;			//TODO check this for change back... 
		}
	public SeriesDTO getAssignedSeries(){return series;}
	
	
	
	
	//AUXILIARY METHODS

	//rawImage
	public BufferedImage getRawImage() {	return series.getDicomImageList().get(imageNum).getProcessedDicomImage().getBufferedImage();	}
	
	public Dimension getRawImageSize() 
	{	
		if(getActImage() == null)	return null;			
		return new Dimension(this.getRawImageWidth(),this.getRawImageHeight());	
	}
	
	//ActImage
	public BufferedImage getActImage() {  return readyImage;	}
	
	public int getActImageNum(){return imageNum;}
/*	public Dimension getActImageSize() 
		{	
			if(getActImage() == null)	return null;			
			return new Dimension(getActImage().getWidth(),getActImage().getHeight());	
		}*/
	public int getRawImageWidth() {		return getRawImage().getWidth();	}
	
	public int getRawImageHeight() {	return getRawImage().getHeight();	}
	
	//DICOMImage
	public DicomImageDTO getActDICOMImage(){ 
		return getAssignedSeries().getDicomImageList().get(getActImageNum());	
		}
	
	//SubImage is the part of the actImage, which is/will be displayed
	public DPoint getFocusOnActRawImage(){	return this.focusPoint;	}
	
	public DDim getSubImageSizeOnActRawImage() {  
		if(this.getActImage()==null)return null;
		Dimension maxPaintArea = this.imageDisplay.getMaxInnerPaintArea();
		
//		double w = Math.min(this.getRawImageWidth()/zoom,this.getRawImageWidth());
//		double h = Math.min(this.getRawImageHeight()/zoom,this.getRawImageHeight());	
		double w = Math.min((double)(maxPaintArea.width)/zoom,this.getRawImageWidth());
		double h = Math.min((double)(maxPaintArea.height)/zoom,this.getRawImageHeight());
		
		return new DDim(w,h);
		}
	
	public Dimension getTheoreticalSizeOfImageProductWithActZoom(){ //this is used to check whether it can fit in the image area or not
		if(this.getActImage()==null)return null;
//		DDim subImSize = getSubImageSizeOnActRawImage();
		Dimension rawImSize = this.getRawImageSize();
		Dimension maxPaintArea = this.imageDisplay.getMaxInnerPaintArea();
		int w = (int)Math.round(Math.min(rawImSize.width*zoom, (double)maxPaintArea.width));
		int h = (int)Math.round(Math.min(rawImSize.height*zoom, (double)maxPaintArea.height));
		return new Dimension(w,h); 
		}	

	
	public DPoint getSubImageTLCOnRawImage(){
		if(this.getActImage()==null)return null;	
		return new DPoint(focusPoint.x-getSubImageSizeOnActRawImage().w/2, focusPoint.y-getSubImageSizeOnActRawImage().h/2); }
	
	public DPoint getSubImageBRCOnRawImage(){
		if(this.getActImage()==null)return null;
		return new DPoint(focusPoint.x+getSubImageSizeOnActRawImage().w/2, focusPoint.y+getSubImageSizeOnActRawImage().h/2); }
	
	public DRect2D getSubImageRectangleInOriginalImageSpace(){
		return new DRect2D(focusPoint.x-getSubImageSizeOnActRawImage().w/2, focusPoint.y-getSubImageSizeOnActRawImage().h/2,getSubImageSizeOnActRawImage().w,getSubImageSizeOnActRawImage().h);
		}
	
	
	//point transformation
		// both coordinate spaces are defined by the images TL corner
	public DPoint pointTransformFromShownImageToOriginalImage(Point intPoint){
			double ansX, ansY; 
			DRect2D subImageRect  = getSubImageRectangleInOriginalImageSpace();
			Dimension displaySize = imageDisplay.getInnerPaintArea();

			//TL corner of subImage in original image space
			ansX = subImageRect.getX(); 
			ansY = subImageRect.getY(); 
						
//			ansX += (intPoint.getX()+0.5) * (double)subImageRect.getWidth()  / (double)displaySize.getWidth();
//			ansY += (intPoint.getY()+0.5) * (double)subImageRect.getHeight() / (double)displaySize.getHeight();
			ansX += (intPoint.getX()) * (double)subImageRect.getWidth()  / (double)displaySize.getWidth();
			ansY += (intPoint.getY()) * (double)subImageRect.getHeight() / (double)displaySize.getHeight();
			
			return new DPoint(ansX, ansY);
		}
	
	public Point pointTransformFromOriginalImageToShownImage(DPoint dPoint){
			double ansX, ansY; 
			DRect2D subImageRect  = getSubImageRectangleInOriginalImageSpace();
			Dimension displaySize = imageDisplay.getInnerPaintArea(); 		
			
			//point wrt TL corner of orig image
			ansX = dPoint.getX()-subImageRect.getX();  
			ansY = dPoint.getY()-subImageRect.getY(); 
//			ansX = dPoint.getX()-subImageRect.getX();  
//			ansY = dPoint.getY()-subImageRect.getY(); 

			//rescale to display area
			ansX = ansX * (double)displaySize.getWidth() /subImageRect.getWidth();
			ansY = ansY * (double)displaySize.getHeight() / subImageRect.getHeight();
			
//			return new Point(Math.max(0,(int)Math.floor(ansX-0.5)),Math.max(0,(int)Math.floor(ansY-0.5)));
//			return new Point((int)Utility.roundTowardsZero(ansX-0.5),(int)Utility.roundTowardsZero(ansY-0.5));
			return Utility.roundPointIntoImageGrid(new DPoint(ansX,ansY));
//			return new Point((int)Math.round(ansX),(int)Math.round(ansY));
		}

	public Point pointTransformFromOriginalImageSpaceToOriginalImageGrid(DPoint dPoint){
		return dPoint.roundIntoImageGrid();
	}
	
	public DPoint pointTransformFromOriginalImageGridToOriginalImageSpace(Point point){
		return new DPoint(point.x+0.5,point.y+0.5);
	}
	
	// TODO check problem with right and bottom boundaries: if image size is 512, floating point space is [0,512] but image pixels are [0,...,511]
	
	
	//Position  - refers to where the image was moved - agrees with mouse movement direction, opposes subimage positioning  (it is always <= 0)
	private void setPositionByFocus(double x, double y){ focusPoint = new DPoint(x,y); };
	
	//Window
	public double getActWindowCenter() {	return actWindow.getWindowCenter();	}
	
	public double getActWindowWidth() {	return actWindow.getWindowWidth();	}
	
	public void setWindow(double newWCenter, double newWWidth )
		{
//			actWindow = new Window(/*0,65535*/newWCenter,newWWidth);
			actWindow = new Window(newWCenter,newWWidth,this.imageDisplay.getRenderingHints());
			System.out.println("Act window:   center = "+actWindow.getWindowCenter()+", width = "+actWindow.getWindowWidth()+
				" - interval: ["+(actWindow.getWindowCenter()-actWindow.getWindowWidth()/2)+","+
				+(actWindow.getWindowCenter()+actWindow.getWindowWidth()/2)+"]");
		}
	
	public LookupOp getWindowOp(){
		return actWindow.getWindowOperation();
	}
	
	
	//Zoom
	public double getActZoom() {	return zoom;	}

	

	//orientation labels
	public String[] getOrientationLabels(){		//TODO do it taking care of actRotation!!!
		return series.getDicomImageList().get(imageNum).getProcessedDicomImage().getImageSideOrientationLabels();
		}
	
	
	//AnnotMeasLayer
	public AnnotMeasLayerModel getAnnotMeasLayerModel() {	return annotMeasLayerModel;	}

	//ImageDisplay
	public ImageDisplay getImageDisplay() {	return imageDisplay;	}



	
	
	

	
	
}
