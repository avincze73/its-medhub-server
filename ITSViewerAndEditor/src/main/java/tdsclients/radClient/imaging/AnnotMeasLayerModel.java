package tdsclients.radClient.imaging;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.LinkedList;

//import datamodel.imagedatamodel.DicomImage;

import tdsclients.radClient.imaging.tools.AnnotMeas;
import tdsclients.radClient.imaging.tools.Annotation;
import tdsclients.radClient.imaging.tools.MDistance;
import tdsclients.radClient.imaging.tools.MPointDens;
import tdsclients.radClient.imaging.tools.Measurement;

public class AnnotMeasLayerModel  {

	
	public class IndexedLinkedList <Type>{
		LinkedList<Type>    elementList ;
		LinkedList<Integer> indices;
		
		
		public IndexedLinkedList (){
			elementList = new LinkedList<Type>();
			indices = new LinkedList<Integer>();
		}
		
		public void add(Type element, int indexToSave){
			
			int pos = 0;
			if(indices.size()>0)
			{
				for(int i=0; i< indices.size(); i++)
					if(i<indices.size()-1)
					{
						if( indices.get(i+1) >  indexToSave)
						{
							pos = i+1;
							break;
						}
					}
					else pos=i+1;
			}
			
			elementList.add(pos, element);
			indices.add(pos, indexToSave);
		}
		
		
		public Type getByAssignedImageIndex(int savedIndex){
			int pos = indices.indexOf(savedIndex);
			if (pos ==-1)	return null;
			else return elementList.get(pos);
		}

		
		public Type getByListPos(int pos){
			if (pos >= elementList.size())
				return null;
			else 
				return elementList.get(pos);
		}

		
		public int getSavedIndexForListPos(int pos){
			return indices.get(pos);
		}


		public boolean remove(int savedIndex){
			int pos = indices.indexOf(savedIndex);
			if (pos ==-1)	
				return false;
			else 
				{
					elementList.remove(pos);
					indices.remove(pos);
					return true;
				}
		}

		public int size(){return elementList.size();}
		
		public boolean isIndexInList(int index){return indices.indexOf(index)>-1;}
	}
	
	
	
	public static final int TOOLTYPE_ANNOT = 0; 
	public static final int TOOLTYPE_MEAS  = 1; 
	public static final int TOOLTYPE_NOHIT = -2; 
	public static final int SENTINEL = -3; 
	
	public static final int MAXINNERLISTCAPACITY = 10;
//	public static final int STARTINGOUTERLISTSIZE = 3;
		
	private DisplayModel model; 	
	private ImageDisplay.AnnotMeasLayer annotMeasLayer;
//	private ArrayList<Annotation>[] annotationList;
//	private ArrayList<Measurement>[] measurementsList;
	
//	private ArrayList<ArrayList<Annotation>> annotationLists;
//	private ArrayList<ArrayList<Measurement>> measurementsLists;
//	private LinkedList<LinkedList<Annotation>> annotationLists;
//	private LinkedList<LinkedList<Measurement>> measurementsLists;
	
	private IndexedLinkedList<ArrayList<Annotation>> annotationLists;
	private IndexedLinkedList<ArrayList<Measurement>> measurementsLists;
	
//	private ArrayList<Integer> imageLookupA;
//	private ArrayList<Integer> imageLookupM;
//	private int[] imageLookupA;
//	private int[] imageLookupM;
	
//	FontRenderContext fontRenderContext; 
	
	
	
	
	//*************** CONSTRUCTOR *****************	
	public AnnotMeasLayerModel(DisplayModel model){
		this.model = model; 

		annotationLists =  new IndexedLinkedList<ArrayList<Annotation>>();
		measurementsLists =  new IndexedLinkedList<ArrayList<Measurement>>();
		
	}

	
	
	//*************** METHODS *****************
	
	public void assignAnnotMeasLayer(ImageDisplay.AnnotMeasLayer annotMeasLayer){	this.annotMeasLayer = annotMeasLayer;}


	

	
	private boolean addAnnotationToImageX(Annotation annot, int actImageNum){
		if(getNumAnnotsforActImage()<MAXINNERLISTCAPACITY)
		{
			System.out.println("annotationLists.size = "+annotationLists.size());
			System.out.println("actImageNum = "+actImageNum);
			if(annotationLists.getByAssignedImageIndex(actImageNum)==null)
			{
				System.out.println("annotationLists.get(actImageNum).size = NULL before setting it up");
				annotationLists.add(new ArrayList<Annotation>(MAXINNERLISTCAPACITY), actImageNum);
				System.out.println("annotationLists.get(actImageNum).size = "+annotationLists.getByAssignedImageIndex(model.getActImageNum()).size());
			}
			else
				System.out.println("annotationLists.get(actImageNum).size = "+annotationLists.getByAssignedImageIndex(model.getActImageNum()).size());
			
			annotationLists.getByAssignedImageIndex(model.getActImageNum()).add(annot);
			return true;
		}
		else
			return false;
	}
	
	
	private boolean addAnnotationToActImage(Annotation annot){
		int actImageNum = model.getActImageNum();
		return addAnnotationToImageX( annot, actImageNum);
	}
	
	
	
	private boolean addMeasurementToImageX(Measurement meas, int imageNum){
		if(getNumAnnotsforActImage()<MAXINNERLISTCAPACITY)
		{
			System.out.println("measurementsLists.size = "+measurementsLists.size());
			System.out.println("actImageNum = "+imageNum);
			if(measurementsLists.getByAssignedImageIndex(imageNum)==null)
			{
				System.out.println("Before setting up for new image");
				System.out.println("measurementsLists                       = "+ measurementsLists);
				System.out.println("measurementsLists.get(actImageNum)      = "+ measurementsLists.getByAssignedImageIndex(imageNum));
//				System.out.println("measurementsLists.get(actImageNum).size = NULL before setting it up");
				measurementsLists.add(new ArrayList<Measurement>(MAXINNERLISTCAPACITY), imageNum);
				System.out.println("After setting up for new image");
				System.out.println("measurementsLists.get(imageNum).size = " + measurementsLists.getByAssignedImageIndex(imageNum).size());
			}
			else
				System.out.println("measurementsLists.get(imageNum).size = "+measurementsLists.getByAssignedImageIndex(imageNum).size());
			
			measurementsLists.getByAssignedImageIndex(imageNum).add(meas);
			return true;
		}
		else
			return false;
	}
	
	
	private boolean addMeasurementToActImage(Measurement meas){
		int actImageNum = model.getActImageNum();
		return addMeasurementToImageX( meas, actImageNum);
	}
	
	
	public boolean addMPointDensToActImage(DPoint pointOnOrigImage){
		return addMeasurementToActImage(new MPointDens(  ((Graphics2D)model.getImageDisplay().getGraphics()).getFontRenderContext(), annotMeasLayer, model.getActDICOMImage(), pointOnOrigImage));
	}
	
	public boolean addMDistanceToActImage(DPoint pointOnOrigImage)
	{
		return addMeasurementToActImage(new MDistance(  ((Graphics2D)model.getImageDisplay().getGraphics()).getFontRenderContext(), annotMeasLayer, model.getActDICOMImage(), pointOnOrigImage,pointOnOrigImage));
	}
	
	public void deleteSelectedToolOfActImage( int toolType, int toolNum){ 
		if(toolType==0)
			annotationLists.getByAssignedImageIndex(model.getActImageNum()).remove(toolNum);
		if(toolType==1)
			measurementsLists.getByAssignedImageIndex(model.getActImageNum()).remove(toolNum);
	}
	
	
	public boolean clearAnnotations(int numImage){ 	//returns false if this action didn't result in any change (list was empty)
/*		int numList = imageLookupA[numImage];
		if(numList == -1) return false; 
		if(annotationLists.get(numList).isEmpty())	return false;
		annotationLists.get(numList).clear();
		return true;*/
		if(annotationLists.getByAssignedImageIndex(numImage)==null)
			return false;
		else 
			return annotationLists.remove(numImage);		
	}

	public boolean clearMeasurements(int numImage){	//returns false if this action didn't result in any change (list was empty)
/*		int numList = imageLookupM[numImage];
		if(numList == -1) return false; 
		if(measurementsLists.get(numList).isEmpty())	return false;
		measurementsLists.get(numList).clear();
		return true;*/
		if(measurementsLists.getByAssignedImageIndex(numImage)==null)
			return false;
		else 
			return measurementsLists.remove(numImage);	
	}
	
	public boolean clearAnnotationsAndMeasurements(int numImage){	//returns false if this action didn't result in any change (lists were empty)
		return clearAnnotations(numImage) || clearMeasurements(numImage);
	}
	
	
	//get tool selection info
	public int[] checkToolSelection(){ //int[]{toolType,toolNum}
		if(annotationLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int a = 0; a < annotationLists.getByAssignedImageIndex(model.getActImageNum()).size(); a++)
				if(annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(a).isSelected())
					return new int[]{0,a};

		if(measurementsLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int m = 0; m < measurementsLists.getByAssignedImageIndex(model.getActImageNum()).size(); m++)
				if(measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(m).isSelected())
					return new int[]{1,m};
		
//		return new int[]{-1,-1};
		return new int[]{TOOLTYPE_NOHIT,SENTINEL};
	}
	

	
	public double[] findClosestCorrectToolPointCoords(int toolType, int toolNumInList, int hitCode1, int hitCode2, int xTravelled, int yTravelled){
		
		
		return null;
	}
	
	
	public double[] findApplicablePartOfToolMovement(int toolType, int toolNumInList, int hitCode1, int hitCode2, int cursorOffsetX, int cursorOffsetY, int newX, int newY, DRect2D subImageRectangleOnRawImage) {
		
//		double[] closestCorrectToolPointCoords = findClosestCorrectToolPointCoords(toolType, toolNumInList, hitCode1, hitCode2, xTravelled, yTravelled);
		
		//double[] startingToolPointCoords;
		//double xTravelled,yTravelled;
		double[] applicablePartOfToolMovement = new double[]{0,0};
		
		if(toolType == 0)
			applicablePartOfToolMovement = 
				annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).
					getApplicablePartOfToolMovement(hitCode1, hitCode2, cursorOffsetX, cursorOffsetY, newX, newY, subImageRectangleOnRawImage, model); //DefiningPointN(n)
		if(toolType == 1)
			applicablePartOfToolMovement = 
				measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).
					getApplicablePartOfToolMovement(hitCode1, hitCode2, cursorOffsetX, cursorOffsetY, newX, newY, subImageRectangleOnRawImage, model); //DefiningPointN(n)
			
		//double[] toolPointCoordsWRTdraggingPoint = ;	// tool point coords - old cursor coords 
		//double[] destPointInOriginalImageSpace = ;				
		//double[] visibleAreaOfOriginalImageInOrigImageSpace = ;  
		//double[] closestToolPointCoordsInVisualizedSpace = ;
		
		
		//double xTravelled = closestToolPointCoordsInVisualizedSpace[0];
		//double yTravelled = closestToolPointCoordsInVisualizedSpace[0];
		
//		return new double[]{xTravelled, yTravelled};
		return applicablePartOfToolMovement;
	}

	
	public void moveSelectedToolOfActImage(int toolType, int toolNumInList, int hitCode1, int hitCode2, double toolMovementApplicableChangeX, double toolMovementApplicableChangeY) {
		
		System.out.println("\t-----------------------------\n\tPERFORMING MOVEMENT: params:[ toolType: "+toolType +", toolNumInList: "+ toolNumInList +", hitCode1: "+ hitCode1 +", hitCode2: "+ hitCode2 +", x: "+ toolMovementApplicableChangeX +", y: "+ toolMovementApplicableChangeY+"]\n-----------------------------" );
		if(hitCode1 == AnnotMeas.NO_PART_HIT)	//no hit
			return;
		
			
		if(hitCode1 == AnnotMeas.WHOLE_MOVE)	//move the whole and every part as one
			{
			if(toolType == TOOLTYPE_ANNOT) // 0)
				{
					System.out.println("\t-----------------------------\n\tPERFORMING WHOLE MOVEMENT on ANNOTATION: ["+(toolMovementApplicableChangeX)+","+(toolMovementApplicableChangeY)+"]\n\t------------------------------" );
					annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).wholeMove(toolMovementApplicableChangeX, toolMovementApplicableChangeY);
				}
			if(toolType == TOOLTYPE_MEAS)  // 1)
				{					
					System.out.println("\t-----------------------------\n\tPERFORMING WHOLE MOVEMENT on MEASUREMENT: ["+(toolMovementApplicableChangeX)+","+(toolMovementApplicableChangeY)+"]\n\t------------------------------" );
					measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).wholeMove(toolMovementApplicableChangeX, toolMovementApplicableChangeY);
				}	
			}
		else
			if(hitCode1>=0)
			{
				int part = hitCode1;
				if(hitCode2 == AnnotMeas.WHOLE_MOVE)	// 	move the whole of one part
				{
					System.out.println("\t-----------------------------\n\tSHOULD BE PERFORMING WHOLE MOVEMENT OF A PART");	
				}
				else
					if(hitCode2>=0) // 	move a point
					{
						int pointNum = hitCode2;
						System.out.println("\t-----------------------------\n\tPERFORMING POINT MOVEMENT OF A PART");
						if(toolType == TOOLTYPE_ANNOT) // 0)
							{
								System.out.println("\t-----------------------------\n\tPERFORMING WHOLE MOVEMENT on ANNOTATION: ["+(toolMovementApplicableChangeX)+","+(toolMovementApplicableChangeY)+"]\n\t------------------------------" );
								annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).pointMove(pointNum,toolMovementApplicableChangeX, toolMovementApplicableChangeY);
							}
						if(toolType == TOOLTYPE_MEAS)  // 1)
							{					
								System.out.println("\t-----------------------------\n\tPERFORMING WHOLE MOVEMENT on MEASUREMENT: ["+(toolMovementApplicableChangeX)+","+(toolMovementApplicableChangeY)+"]\n\t------------------------------" );
								measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(toolNumInList).pointMove(pointNum,toolMovementApplicableChangeX, toolMovementApplicableChangeY);
							}	
					
					}
					else 
						System.out.println("\t-----------------------------\n\tMOVEMENT ERROR 1 in Annot Meas Layer Model");
			
			}
			else 
				System.out.println("\t-----------------------------\n\tMOVEMENT ERROR 2 in Annot Meas Layer Model");
		
		

	}
	
	
	
	
	
	
	public DPoint translatePointFromRawImageSpaceToDisplaySpace(DPoint pR){
		//TODO check!!!
		double x = (pR.x-model.getSubImageTLCOnRawImage().x)*model.getActZoom();
		double y = (pR.y-model.getSubImageTLCOnRawImage().y)*model.getActZoom();
		return new DPoint(x,y);
	}
	
	
	public Annotation getAnnotationXforImageY(int numImage, int numAnnot)
	{
/*		int numList = imageLookupA[numImage];
		if(numList == -1) return null;
		return annotationLists.get(numList).get(numAnnot);		*/
		if(getNumAnnots(numImage) == -1) 
			return null;
		else 
			return annotationLists.getByAssignedImageIndex(numImage).get(numAnnot);
	}
	
	public Measurement getMeasurementXforImageY(int numImage, int numMeas)
	{	
/*		int numList = imageLookupM[numImage];
		if(numList == -1) return null;
		return measurementsLists.get(numList).get(numMeas);	*/
		if(getNumMeass(numImage) == -1) 
			return null;
		else 
			return measurementsLists.getByAssignedImageIndex(numImage).get(numMeas);
	}

	
	public int getNumAnnots(int numImage)
	{ 
/*		int numList = imageLookupA[numImage];
		if(numList == -1) return 0;
		return annotationLists.get(numImage).size(); */
		if(annotationLists.getByAssignedImageIndex(numImage) == null)
			return 0;
		else
			return annotationLists.getByAssignedImageIndex(numImage).size();
	}
	
	public int getNumMeass(int numImage)
	{  
/*		int numList = imageLookupM[numImage];
		if(numList == -1) return 0;
		return measurementsLists.get(numImage).size(); */
		if(measurementsLists.getByAssignedImageIndex(numImage) == null)
			return 0;
		else
			return measurementsLists.getByAssignedImageIndex(numImage).size();
	}
	
	public boolean canAddAnnotToActImage(){
		return annotationLists.getByAssignedImageIndex(model.getActImageNum()).size() < MAXINNERLISTCAPACITY;
	}

	public boolean canAddMeasToActImage(){
		if(measurementsLists.getByAssignedImageIndex(model.getActImageNum()) == null) 
			return true;
		else
			return measurementsLists.getByAssignedImageIndex(model.getActImageNum()).size() < MAXINNERLISTCAPACITY;
	}
	
	
	public Annotation getAnnotationXforActImage( int numAnnot){ return getAnnotationXforImageY(model.getActImageNum(),numAnnot);}

	public Measurement getMeasurementXforActImage( int numMeas){ return getMeasurementXforImageY(model.getActImageNum(),numMeas);}

	public int getNumAnnotsforActImage(){ return getNumAnnots(model.getActImageNum());}

	public int getNumMeassforActImage(){ return getNumMeass(model.getActImageNum());}

	

	
	public void unselectAllToolsForAllImages(){
		for(int i=0; i<annotationLists.size(); i++)
			for(int a = 0; a < annotationLists.getByListPos(i).size(); a++)
				annotationLists.getByListPos(i).get(a).setUnSelected();

		for(int i=0; i<measurementsLists.size(); i++)
			for(int m = 0; m < measurementsLists.getByListPos(i).size(); m++)
				measurementsLists.getByListPos(i).get(m).setUnSelected();
	}
	

	public void unselectAllToolsForActImage(){
		if(annotationLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int a = 0; a < annotationLists.getByAssignedImageIndex(model.getActImageNum()).size(); a++)
				annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(a).setUnSelected();

		if(measurementsLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int m = 0; m < measurementsLists.getByAssignedImageIndex(model.getActImageNum()).size(); m++)
				measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(m).setUnSelected();
	}


	public void selectToolXForActImage(int toolType, int numTool){
		if(toolType==0)
			annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(numTool).setSelected();
		else if(toolType==1)
			measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(numTool).setSelected();
	}
//	public void selectMeasXForActImage(int numMeasurement){
//		measurementsLists.get(model.getActImageNum()).get(numMeasurement).setSelected();
//	}
	
	
	public void clearAnnotMeasLayerForActImage(){
		if(measurementsLists.getByAssignedImageIndex(model.getActImageNum()) != null) 
			measurementsLists.getByAssignedImageIndex(model.getActImageNum()).removeAll(measurementsLists.getByAssignedImageIndex(model.getActImageNum()));
		if(annotationLists.getByAssignedImageIndex(model.getActImageNum()) != null) 
			annotationLists.getByAssignedImageIndex(model.getActImageNum()).removeAll(annotationLists.getByAssignedImageIndex(model.getActImageNum()));
	}
	

	
	public int[] pointBelongsToTool(int x, int y){
		
		System.out.println("\n\n\n*************************************************************************************");
		System.out.println("\n\n***\nAnnotMeasLayer.pointBelongsToTool()\n***\n\n");
		System.out.println("\n*************************************************************************************\n\n");
		int toolType = TOOLTYPE_NOHIT;  
		int numTool  = SENTINEL;//-1;
		int hitCode1 = AnnotMeas.NO_PART_HIT;
		int hitCode2 = SENTINEL;//-1;
		int[] grabbingOffset = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE}; //vector of cursor from the reference point that is grabbed 

		int[] hitCodeTemp;
		
			
		//check annotations backwards
		if(annotationLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int a = annotationLists.getByAssignedImageIndex(model.getActImageNum()).size()-1; a >= 0  ; a--)
			{
				hitCodeTemp = annotationLists.getByAssignedImageIndex(model.getActImageNum()).get(a).getMouseHitResult(x, y, model);
				hitCode1 = hitCodeTemp[0];
				hitCode2 = hitCodeTemp[1];
				grabbingOffset = new int[]{hitCodeTemp[2],hitCodeTemp[3]}; 
				if( hitCode1 != AnnotMeas.NO_PART_HIT)//-2 )
				{
					toolType = TOOLTYPE_ANNOT;//0;
					numTool = a;
					System.out.println("\n\n\t***\n\tANNOTATION TOOL HIT!!!\n\t***\n\n");
					break;
				}
			}	
		//check measurements backwards
		if(toolType == TOOLTYPE_NOHIT && measurementsLists.getByAssignedImageIndex(model.getActImageNum()) != null)
			for(int m = measurementsLists.getByAssignedImageIndex(model.getActImageNum()).size()-1; m >= 0  ; m--)
			{
				hitCodeTemp = measurementsLists.getByAssignedImageIndex(model.getActImageNum()).get(m).getMouseHitResult(x, y, model);
				hitCode1 = hitCodeTemp[0];
				hitCode2 = hitCodeTemp[1]; 
				grabbingOffset = new int[]{hitCodeTemp[2],hitCodeTemp[3]};
				if( hitCode1 != AnnotMeas.NO_PART_HIT)//-2 )
				{
					toolType = TOOLTYPE_MEAS; //1;
					numTool = m;
					System.out.println("\n\n\t***\n\tMEASUREMENT TOOL HIT!!!\n\t***\n\n");
					break;
				}
			}
		
				
		if(toolType == TOOLTYPE_NOHIT)
		{
			System.out.println("\n\n\n\t*************************************************************************************");
			System.out.println("\n\n\t***\n\tNO TOOL HIT!\n\t***\n\n");
			System.out.println("\n\t*************************************************************************************\n\n");
			return new int[]{toolType, numTool, hitCode1, hitCode2, grabbingOffset[0], grabbingOffset[1]};
		}
		else
		{
			System.out.println("\n\n\n\t*************************************************************************************");
			System.out.println("\n\n\tTOOL HIT!  (toolType: "+toolType+", numTool: "+numTool+", hitCode1: "+hitCode1+", hitCode2: "+hitCode2+", grabbingOffset = ("+grabbingOffset[0]+","+grabbingOffset[1]+") ) \n\n");
			System.out.println("\n\t*************************************************************************************\n\n");
			return new int[]{toolType, numTool, hitCode1, hitCode2, grabbingOffset[0], grabbingOffset[1]};		
		}
	}
	
	
	
	
	public AnnotMeasLayerModel getCopy(DisplayModel newDisplayModelCopy){
		AnnotMeasLayerModel copy = new AnnotMeasLayerModel(newDisplayModelCopy);
		copy.assignAnnotMeasLayer(this.annotMeasLayer);

		copy.annotationLists =  new IndexedLinkedList<ArrayList<Annotation>>();
		copy.measurementsLists =  new IndexedLinkedList<ArrayList<Measurement>>();

		//TODO check if addition is with act image (=WRONG) index or the required image index
		for(int numImage = 0; numImage<annotationLists.size(); numImage++)
		{
//			if(annotationLists.getByAssignedImageIndex(numImage) != null)
//			{				
				//fill annotationList with element copies
				for(int a =0; a<this.annotationLists.getByListPos(numImage).size(); a++)
					copy.addAnnotationToImageX((Annotation)annotationLists.getByListPos(numImage).get(a).getCopy(), annotationLists.getSavedIndexForListPos(numImage));
//			}
		}
		
		//TODO check if addition is with act image (=WRONG) index or the required image index
		for(int numImage = 0; numImage<measurementsLists.size(); numImage++)
		{			
//			if(measurementsLists.getByAssignedImageIndex(numImage) != null)
//			{				
				//fill measurementsList with element copies
				for(int m =0; m<this.measurementsLists.getByListPos(numImage).size(); m++)
					copy.addMeasurementToImageX((Measurement)measurementsLists.getByListPos(numImage).get(m).getCopy(), measurementsLists.getSavedIndexForListPos(numImage));
//			}
		}
		return copy;
	}
	
	
	
	public void listToolsForAllImages(){  //TODO delete this function
		System.out.println("\t\t\tAnnotations:  (upper list size  = "+annotationLists.size()+")");
		for(int numImage = 0; numImage<annotationLists.size(); numImage++)
		{
/*
//			if(annotationLists.getByListPos(numImage) != null)
//			{				
//				System.out.println("\t\t\t\tImage ["+numImage+"]th = image "+annotationLists.getSavedIndexForListNum(numImage));
				System.out.println("\t\t\t\tImage "+annotationLists.getSavedIndexForListPos(numImage)+" which is " +numImage+"th in the list (sublist size is = "+this.annotationLists.getByListPos(numImage).size()+" )");
				for(int a =0; a<this.annotationLists.getByListPos(numImage).size(); a++)
					System.out.println("\t\t\t\t\t"+(Annotation)annotationLists.getByListPos(numImage).get(a));
//			}
		}
		
		System.out.println("\t\t\tMeasurements: (upper list size  = "+measurementsLists.size()+")");
		for(int numImage = 0; numImage<measurementsLists.size(); numImage++)
		{
//			System.out.println("\t\t\t\tImage "+measurementsLists.getSavedIndexForListPos(numImage)+" which is " +numImage+"th in the list");
			System.out.println("\t\t\t\tImage "+measurementsLists.getSavedIndexForListPos(numImage)+" which is " +numImage+"th in the list   (sublist size is = "+this.measurementsLists.getByListPos(numImage).size()+" )");
			System.out.println("\t\t\t\t\t (this.measurementsLists.getByListPos(numImage) = "+this.measurementsLists.getByListPos(numImage)+")");
//			if(measurementsLists.getByListPos(numImage) != null)
//			{				
//				System.out.println("\t\t\t\tImage "+measurementsLists.getByListPos(numImage)+" which is " +numImage+"th in the list   (sublist size is = "+this.measurementsLists.getByListPos(numImage).size()+" )");
				for(int m =0; m<this.measurementsLists.getByListPos(numImage).size(); m++)
					System.out.println("\t\t\t\t\t"+(Measurement)measurementsLists.getByListPos(numImage).get(m));
//			}
*/
		}
		
	}


	

	
}
