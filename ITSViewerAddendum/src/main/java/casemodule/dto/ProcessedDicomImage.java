package casemodule.dto;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import dicommodule.processing.DicomLUT;
import dicommodule.processing.DicomOptionsException;
import dicommodule.processing.NecessaryPictureAttributeMissingException;
import dicommodule.processing.SingleDoubleWithState;
import dicommodule.processing.SingleIntWithState;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceViewerSide;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;

public class ProcessedDicomImage {

	public static final int VOILUTIDENTITY = 0;
	public static final int SINGLEVOILUT = 1;
	public static final int MULTIPLEVOILUT = 2;

	//pixel data interpretation
		//private int compression;		//0 - no compression
		private String photometricInterpretation;
		private int width, height;
		private int sampleNum;
		private int Ba, Bs, Bh;
		
	//pixel data
		private short[] pixelData; //wordOrByteStream;	//TODO check type!!!
		private BufferedImage bufferedImage;

	//Modality LUT
		
	//VOI LUT	
		private byte caseVOILUT;	//can get values from {VOILUTIDENTITY, SINGLEVOILUT, MULTIPLEVOILUT} 
		private boolean isWindowPresentInDataset;			
		private DicomLUT[] sequenceVOILUT;	
		private Double[] windowCenter; 
		private Double[] windowWidth;			
		//private DicomLUT allVOILUTs;	

	//Presentation LUT
		
		
	//pixel stats
		private boolean isSmallestImagePixelValuePresentInDataset;			
		private double minImageRawValue;
		private boolean isLargestImagePixelValuePresentInDataset;			
		private double maxImageRawValue;
		private double meanImageRawValue;
		private double stdevImageRawValue;
		
	//orientation
		//private String patientOrientation;				// (0020,0020)
		private String displaySetPatientOrientation; 		//(0072,0700)
		private double[][] imageOrientationPatient; 		// (0020,0037)    
	
	//weird things 
		private boolean isSliceThicknessPresentInDataset;			
		private Double sliceThickness;						// (0018,0050)
		//spacing between slices???	private String SliceThickness;			// (0018,0088)
		private String sliceLocation;			// (0020,1014)
		private Double[] pixelSpacing; 					//(0028,0030)
		
	//preprocessing values
		public static final double PLANECLASSIFICATIONTHRESHOLD = 0.5;		//cosine of 60 degrees
		private String imagePlane;
		private String[] imageSideOrientationLabels;	//N,E,S,W - upper, right, lower, left

	
	//history
		private boolean displayed;
		
		
	
	
	public ProcessedDicomImage(DicomDataSet dicomDataSet) throws DIException, NecessaryPictureAttributeMissingException, DicomOptionsException{
    	
		//Basic loading
			DicomInterfaceViewerSide dicomInterface = new DicomAdapter(dicomDataSet);
		
			//photometricInterpretation
				photometricInterpretation = dicomInterface.getPhotometricInterpretation();
				if(photometricInterpretation.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image photometricInterpretation missing");
			//height
				SingleIntWithState tempSIWS1 = dicomInterface.getRows();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image height missing");
				else	height =  tempSIWS1.value;
			//width
				tempSIWS1 = dicomInterface.getColumns();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image width missing");
				else	width =  tempSIWS1.value;
			//sampleNum
				tempSIWS1 = dicomInterface.getSamplesPerPixel();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image sampleNum missing");
				else	sampleNum =  tempSIWS1.value;
			//Ba
				tempSIWS1 = dicomInterface.getBitsAllocated();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image Ba missing");
				else	Ba =  tempSIWS1.value;
			//Bs
				tempSIWS1 = dicomInterface.getBitsStored();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image Bs missing");
				else	Bs =  tempSIWS1.value;
			//Bh
				tempSIWS1 = dicomInterface.getHighBit();
				if(tempSIWS1.isEmpty())	
					throw new NecessaryPictureAttributeMissingException("Image Bh missing");
				else	Bh =  tempSIWS1.value;

		//Image loading
				loadPixelData(dicomInterface.getPixelData());
				
				
		//VOI LUT 
			//sequenceVOILUT
				sequenceVOILUT = dicomInterface.getVOILUT();	// TODO convert normal center and width info into LUT
			//windowCenter
				windowCenter = dicomInterface.getWindowCenter();
			//windowWidth
				windowWidth = dicomInterface.getWindowWidth();
				
			//checking multiple options
				if(windowWidth==null && windowCenter==null && sequenceVOILUT==null)
					{
						this.isWindowPresentInDataset = false;
						caseVOILUT = VOILUTIDENTITY;
					}
				else
					{
						this.isWindowPresentInDataset = true;
						
						if ( (windowWidth==null && windowCenter==null) && (sequenceVOILUT!=null) )
							{
								if(sequenceVOILUT.length==0)
									caseVOILUT = VOILUTIDENTITY;
								else
									if(sequenceVOILUT.length==1)
										caseVOILUT = SINGLEVOILUT;
									else
										caseVOILUT = MULTIPLEVOILUT;									
							}
						else
							if( (windowWidth!=null && windowCenter!=null) && (sequenceVOILUT==null) )	
								{
								if(windowWidth.length==0 && windowCenter.length==0)
									caseVOILUT = VOILUTIDENTITY;
								else
									if(windowWidth.length==1 && windowCenter.length==1)
										caseVOILUT = SINGLEVOILUT;
									else
										caseVOILUT = MULTIPLEVOILUT;									
								}
							else
								throw new DicomOptionsException();
						
					}
			// TODO convert normal centre and width info into LUT
				//allVOILUTs = ;
				
		
		//extras
//			findStatistics(dicomInterface);		
			findSliceLocationAndThickness(dicomInterface);    	
			findPatientOrientationAndSideLabels(dicomInterface);		
			
			pixelSpacing = dicomInterface.getPixelSpacing();
			
			System.out.println("\n\n\n XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
			System.out.println("\n\nPIXEL SPACING:\n");
			System.out.println("\t\t"+pixelSpacing+"\n\n");
			System.out.println(" XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n\n\n");

    }

	
	public boolean isPixelSpacingPresent()
		{ 
			if (pixelSpacing == null)return false;
			if (pixelSpacing.length==0)return false;
			return true;
		}
	
	
	public Double[] getPixelSpacing(){return pixelSpacing;}
	
	
	private void findPatientOrientationAndSideLabels(DicomInterfaceViewerSide dicomInterface){
/*		if(fileContent.getElement("0072","0700")==null)		//TODO check, take care!
			setDisplaySetPatientOrientation(null);
		else
			setDisplaySetPatientOrientation((String)(fileContent.getElement("0072","0700").getValue()));*/
				
//		setImageOrientationPatient(decodeOrientation((String)(fileContent.getElement("0020","0037").getValue()))); 
		imageSideOrientationLabels = new String[]{"TEST","TEST","TEST","TEST"};	//N,E,S,W - upper, right, lower, left

	}

    public boolean isIsSliceThicknessPresentInDataset() {
        return isSliceThicknessPresentInDataset;
    }
	
	
	
	private void findSliceLocationAndThickness(DicomInterfaceViewerSide dicomInterface) throws DIException{

		//TODO with consistency checking

		//slice thickness
		SingleDoubleWithState tempSDWS = dicomInterface.getSliceThickness();
                if (tempSDWS == null){
                    isSliceThicknessPresentInDataset = false;
                } else {
                    isSliceThicknessPresentInDataset = !tempSDWS.isEmpty();
                    if(isSliceThicknessPresentInDataset)
			sliceThickness = tempSDWS.value;
		else
			sliceThickness = Double.NaN;
                }
		
		//slice thickness

		//TODO
	}
	
	
	private void findStatistics(DicomInterfaceViewerSide dicomInterface) throws DIException{
		
		minImageRawValue 	= Double.MAX_VALUE;
		maxImageRawValue 	= -Double.MIN_VALUE;
		meanImageRawValue 	= Double.NaN;
		stdevImageRawValue 	= Double.NaN;				
		
		SingleIntWithState tempSIWS1 = dicomInterface.getSmallestImagePixelValue();
		isSmallestImagePixelValuePresentInDataset = tempSIWS1!=null;	//TODO take care of empty value 
//		!tempSIWS1.isEmpty();
		SingleIntWithState tempSIWS2 = dicomInterface.getLargestImagePixelValue();
		isLargestImagePixelValuePresentInDataset = tempSIWS2!=null;	//TODO take care of empty value 
//			!tempSIWS2.isEmpty();
		
		if(isSmallestImagePixelValuePresentInDataset && isLargestImagePixelValuePresentInDataset)
			{
				minImageRawValue = tempSIWS1.value;
				maxImageRawValue = tempSIWS2.value;
			}
		else	//calculating statistics
			{
				for(int r=0; r<height; r++)
					for(int c=0; c<width; c++)
						{
							int actPixel = pixelData[r*width+c];
							if(actPixel < minImageRawValue)
								minImageRawValue = actPixel;
							if(actPixel > maxImageRawValue)
								maxImageRawValue = actPixel;
						}
			}

		//TODO calc mean and stdev
		int sumPixelValues = 0;

		meanImageRawValue = StatsUtility.mean(pixelData);
		stdevImageRawValue = StatsUtility.stdev(pixelData,meanImageRawValue);
		
	}
	
	
    
	
	
	

/*	
	
	public double[][] decodeOrientation(String imageOrientationPatientString){
		
//		System.out.println("\n\n\n>>>\n\n\nIMAGE ORIENTATION String: '"+imageOrientationPatientString+"' \n>>>\n\n\n");
		String[] stringElements = new String[6];
		double[] doubleElements = new double[6];
		
		
		int[] boundaries = new int[7];
		
		boundaries[0]=0;
		boundaries[6]=imageOrientationPatientString.length()-1;
		
		for(int i=0;i<=4;i++)
		{
			boundaries[i+1] = imageOrientationPatientString.indexOf('\\', boundaries[i]+1);
//			System.out.println("\tLocation of the "+(i+1)+"th '\\' is: "+boundaries[i+1]);
		}
//		boundaries[5] = imageOrientationPatientString.indexOf('\\', boundaries[6]);
//		System.out.println("\tLocation of the "+6+"th '\\' is: "+boundaries[5]);
		System.out.println();
		
		stringElements[0] = imageOrientationPatientString.substring(0, boundaries[1]);
		for(int i=1;i<6;i++)
		{			
			stringElements[i] = imageOrientationPatientString.substring(boundaries[i]+1, boundaries[i+1]);
		}	
		
		
//		System.out.println("\tString delimiter is '"+DICOM_DataElement.STRINGDELIMITER+"'"); 
		//stringElements = imageOrientationPatientString.split("\\" ); // DICOM_DataElement.STRINGDELIMITER);
//		stringElements = imageOrientationPatientString.split("." ); // DICOM_DataElement.STRINGDELIMITER);
		
//		System.out.println("\tNum of strings is:"+stringElements.length+"\n");
		for(int i=0;i<6;i++)
		{
//			System.out.println("\tString value "+i+" is: "+stringElements[i] );
			doubleElements[i]=Double.parseDouble(stringElements[i]);
//			System.out.println("\t\tDouble value "+i+" is: "+doubleElements[i] );
		}		
		
//		System.out.println("\n>>>\n\n\n");
		
		double[] rowCosines = new double[]{doubleElements[0],doubleElements[1],doubleElements[2]};
		double[] colCosines = new double[]{doubleElements[3],doubleElements[4],doubleElements[5]};;
	
		return new double[][]{	rowCosines, colCosines};
		
//		return new double[][]{	{0,1,0},
//								{1,0,0}
//							 };
	}
	
	
	private void setSideLabels(){	

		//TODO take care, may need to flip signs!! according to Display Set Patient orientation (0072,0700)
		
		System.out.println("\n\n\n>>>\n\n\n");
		
		System.out.println("The First ROW cosines vector is ("+imageOrientationPatient[0][0]+","+imageOrientationPatient[0][1]+","+imageOrientationPatient[0][2]+")");
		System.out.println("The First COL cosines vector is ("+imageOrientationPatient[1][0]+","+imageOrientationPatient[1][1]+","+imageOrientationPatient[1][2]+")");
		System.out.println("The display set patient orientation: "+displaySetPatientOrientation);
		int axisR = -1,axisC =-1;
		//find plane
				if (abs(x) > threshold)
					axis = �RL�
					else if (abs(y) > threshold)
					axis = �AP�
					else if (abs(z) > threshold)
					axis = �HF�
					else
					is OBLIQUE
			//find major axis for ROW
				if 		(Math.abs(imageOrientationPatient[0][0]) > PLANECLASSIFICATIONTHRESHOLD)	axisR = 0; //�RL�
				else if (Math.abs(imageOrientationPatient[0][1]) > PLANECLASSIFICATIONTHRESHOLD)	axisR = 1; //�AP�
				else if (Math.abs(imageOrientationPatient[0][2]) > PLANECLASSIFICATIONTHRESHOLD)	axisR = 2; //�HF�
				else 																				axisR = 3;  //OBLIQUE
			//find major axis for COLOUMN
				if 		(Math.abs(imageOrientationPatient[1][0]) > PLANECLASSIFICATIONTHRESHOLD)	axisC = 0; //�RL�
				else if (Math.abs(imageOrientationPatient[1][1]) > PLANECLASSIFICATIONTHRESHOLD)	axisC = 1; //�AP�
				else if (Math.abs(imageOrientationPatient[1][2]) > PLANECLASSIFICATIONTHRESHOLD)	axisC = 2; //�HF�
				else 																				axisC = 3;  //OBLIQUE
		
				
			String[] planeDefs = new String[]{"Transverse","Coronal","Sagittal","Oblique","ERROR"};	
			int planeDefCode;
			
			//plane definition
			if (axisR == 3 || axisC == 3)
			{
				planeDefCode = 3;
				imagePlane = planeDefs[planeDefCode];
			}
			else
			{
				int[][] planeDefTable = 
				{	{4,0,1},
					{0,4,2},
					{1,2,4}	};
				planeDefCode = planeDefTable[axisC][axisR];
				imagePlane = planeDefs[planeDefCode];
			}
			
			System.out.println("The Major axis of the first row is "+axisR);
			System.out.println("The Major axis of the first column is "+axisC);
			System.out.println("THE IMAGE PLANE is: '"+imagePlane+"' \n>>>\n\n\n");
			
			
			//N,E,S,W - upper, right, lower, left
			imageSideOrientationLabels = new String[4];
			switch(planeDefCode){ 
						//TODO check for orientation changes 
						//				- alternative Patient orientation ???
						//				- rotation
				case 0:	//Transversal 
					{
						imageSideOrientationLabels[0] = "A";
						imageSideOrientationLabels[1] = "R";
						imageSideOrientationLabels[2] = "P";
						imageSideOrientationLabels[3] = "L";
						break;
					}
				case 1:	//Coronal 
					{
						imageSideOrientationLabels[0] = "H";
						imageSideOrientationLabels[1] = "L";
						imageSideOrientationLabels[2] = "F";
						imageSideOrientationLabels[3] = "R";
						break;
					}
				case 2:	//Sagittal 
					{
						imageSideOrientationLabels[0] = "H";
						imageSideOrientationLabels[1] = "A";
						imageSideOrientationLabels[2] = "F";
						imageSideOrientationLabels[3] = "P";
						break;
					}
				case 3:	//Oblique 
					{
						imageSideOrientationLabels[0] = "OB";
						imageSideOrientationLabels[1] = "OB";
						imageSideOrientationLabels[2] = "OB";
						imageSideOrientationLabels[3] = "OB";
						break;
					}
				case 4:	//ERROR 
					{
						imageSideOrientationLabels[0] = "Err";
						imageSideOrientationLabels[1] = "Err";
						imageSideOrientationLabels[2] = "Err";
						imageSideOrientationLabels[3] = "Err";
						break;
					}
			}
	}*/
	
	
	
	//public int getCompression() {	return compression;	}
	public int getWidth() {	return width;	}	
	public int getHeight() {	return height; }
	public String getPhotometricInterpretation() {	return photometricInterpretation; }
	public int getSampleNum() {	return sampleNum; }
	public int getBa() {	return Ba; }
	public int getBs() {	return Bs; }
	public int getBh() {	return Bh; }
	public boolean isNoWindowGiven(){ return !isWindowPresentInDataset;}
	public Double getFirstGivenWindowCenter() {	//TODO need generic method	
		return windowCenter[0];  //50 
		}
	public Double getFirstGivenWindowWidth() {	//TODO need genereic method 
		return windowWidth[0];  //50
		}
	
	public Double[] getFirstGivenWindow() {	//TODO be careful of multiple possibilities!!!
		return null;  //50
		}
		
	public short[] getPixelData() {	return pixelData; }
	public BufferedImage getBufferedImage(){ return bufferedImage;	}

	public double getImageMinRawValue(){ return minImageRawValue; }	
	public double getImageMaxRawValue(){ return maxImageRawValue; }
	public int getPixelValueAt(int x, int y){ return pixelData[y*width+x];  } 

	public String[] getImageSideOrientationLabels(){return imageSideOrientationLabels;}
    
   	public double getSliceThickness() {	return sliceThickness;	}
	public String getSliceLocation() {	return sliceLocation;	}
	

	
	
/*	private void loadPixelData(byte[] rawPixelData)	//interpret two signed bytes as one unsigned int
	{
		int[] pixData = new int[rawPixelData.length/2];
		for(int i=0; i<pixData.length; i++)
		{						
			byte byte0 = rawPixelData[i*2];
			byte byte1 = rawPixelData[i*2+1];
			int byte0m,byte1m;
			if(byte0<0) byte0m = 256+byte0;
			else byte0m=byte0;
			byte1m = (byte1 & 0xf);
			int val = 256*byte1m+byte0m;
			pixData[i] = val;	
		}	
*/
	private void loadPixelData(Object rawPixelData)	//interpret two signed bytes as one unsigned int
	{
		
/*		int row=256;
		System.out.println("\n(Display)Raw image pixels of row "+row+" :");
		for(int c=0; c<512; c+=16)
		{
			System.out.print(pixelData[512*row+c]+" ");
		}
		System.out.println();*/
		pixelData = (short[])rawPixelData;
		createBufferedImage();
		
		//return pixelData;
	}
	
	

	
	
	private void createBufferedImage()
	{		
//		int row=256;
		
		short[] origPixelArray = pixelData;
//		System.out.println("\n(Display)Original image pixels of row "+row+" :");
//		for(int c=0; c<512; c+=16)
//			System.out.print(origPixelArray[512*row+c]+"   \t");
		System.out.println();
		
		
/*		double[] picString = new double[origPicString.length];

		double minval = 0;
		double maxval=Math.pow(2,Bs)-1;
		int scaling = (int)Math.pow(2,Ba-Bs);
		double windowedValue;
		for(int i=0;i<origPicString.length;i++)
			{
				windowedValue = DICOM_WindowFilter(origPicString[i],windowCenter,windowWidth,minval,maxval);
				picString[i]=( windowedValue)*scaling;
			}	*/
		
		int[] pixelarray = new int[origPixelArray.length];
		for(int i=0; i<origPixelArray.length; i++)
			pixelarray[i]=origPixelArray[i];
//		System.out.println("\n(Display)Windowed(1) image pixels of row "+row+" :");
//		for(int c=0; c<512; c+=16)
//		{
//			String numString = Double.toString((pixelarray[512*row+c]));
//			int dotPos = numString.lastIndexOf(".");
//			System.out.print(numString.substring(0, Math.min(dotPos+2,numString.length()))+"\t");
//		}
//		System.out.println();
		
		
		//creating the base picture		
		BufferedImage image2Display = new BufferedImage(width,height,BufferedImage.TYPE_USHORT_GRAY);
		image2Display.setAccelerationPriority(1);
		WritableRaster raster = image2Display.getRaster();
		raster.setPixels(0, 0, width, height, pixelarray);
		bufferedImage = image2Display;
		
//		System.out.println("\n(Display)Original(2) image pixels of row "+row+" :");
//		for(int c=0; c<512; c+=16)
//		{
//			//String numString = Double.toString((picString[512*row+c]));
//			//int dotPos = numString.lastIndexOf(".");
//			//System.out.print(numString.substring(0, Math.min(dotPos+2,numString.length()))+"\t");
//			System.out.print(raster.getPixel(c, row, (int[])null)[0]+"\t");
//		}
//		System.out.println();
//		System.out.println();
		
	}


	
	
	
}
