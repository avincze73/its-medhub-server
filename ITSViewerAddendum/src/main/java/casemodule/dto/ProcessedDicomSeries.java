package casemodule.dto;

import java.util.List;
import tdsdicominterface.DicomDataSet;


public class ProcessedDicomSeries {

	
	public ProcessedDicomSeries(List<DicomDataSet> dicomDataSetList){
		
	}
    
	
	
	
	
/*    
    public int getNumImages(){ 	return dicomImageList.size();    }

	
	
	private String sliceThickness;			// (0018,0050)
	private String sliceLocation;			// (0020,1014)

	public String getSliceThickness() {	return sliceThickness;	}
	public String getSliceLocation() {	return sliceLocation;	}

	
	private double minSeriesRawValue;
	private double maxSeriesRawValue;
	
	public double getSeriesMinRawValue()	{return minSeriesRawValue;}
	public double getSeriesMaxRawValue()	{return maxSeriesRawValue;}


	private String involvedBodyRegion;
	
	public String getInvolvedBodyRegion(){ 	return involvedBodyRegion; }
	
	
	
	//TODO on server ???
    
	
    private void processSeriesData(){
    	findBodyPartInvolved();
    	findSeriesMinMaxRawValues();
    }

    
    

	
	

	private void findBodyPartInvolved(){
		
		
		//TODO with consistency checking
		
		String bodyPart;
		if (fileContent.getElement("0018","0015")!=null)
			{
				System.out.println("\n\n >>>>>> (0018,0015) in NOT NULL!! <<<<<<<<< \n\n");
				bodyPart = (String)fileContent.getElement("0018","0015").getValue();
				involvedBodyRegion = bodyPart;
				setBodyPartExamined(bodyPart);
				return;
			}
		else if(fileContent.getElement("0008","2218")!=null)
			{
				System.out.println("\n\n >>>>>> (0008,2218) in NOT NULL!! <<<<<<<<< \n\n");
				bodyPart = (String)fileContent.getElement("0008","2218").getValue();
				setAnatomicRegionSequence(bodyPart);
				involvedBodyRegion = bodyPart;
				return;
			}	
		else
			{
				System.out.println("\n\n >>>>>> BOTH region infos NULL!! <<<<<<<<< \n\n");
				involvedBodyRegion = null;
			}
		
	}
    
	
	private void findSeriesMinMaxRawValues(){
		
		//TODO  properly, with consistency checking
		
		
		minSeriesRawValue  = Double.MAX_VALUE;
		maxSeriesRawValue  = -Double.MAX_VALUE;
//		meanSeriesRawValue = 0;
//		stdSeriesRawValue  = ;
		for(int i=0; i<getNumImages(); i++)
			{
				images[i] = new DicomImageDTO(fileContent[i]);
				//series minval, maxval, mean, std 
				if(images[i].getImageMinRawValue()<minSeriesRawValue)
					minSeriesRawValue = images[i].getImageMinRawValue();
				if(images[i].getImageMaxRawValue()>maxSeriesRawValue)
					maxSeriesRawValue = images[i].getImageMaxRawValue();
			}		
	}*/
	
	
}
