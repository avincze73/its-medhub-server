package util;

import java.util.List;

//import casemodule.dto.DicomImageDTO;
import dicommodule.tdsdicomadapter.DicomAdapter;
import dicommodule.tdsdicomadapter.DicomInterfaceDBSide;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.exception.DIException;

public class SeriesProcessingUtility {
	

	
	public class SeriesStats{
		public int seriesMin, seriesMax;
		public double seriesMean, seriesStdev;
		public SeriesStats(int seriesMin, int seriesMax, double seriesMean, double seriesStdev ){
			this.seriesMin = seriesMin;
			this.seriesMax = seriesMax;
			this.seriesMean = seriesMean;
			this.seriesStdev = seriesStdev;			
		}
	}
		
	public static SeriesProcessingUtility create(){
		return new SeriesProcessingUtility();
	}
	
    public SeriesStats findSeriesMinMaxRawVals(List<DicomDataSet> dataSets) throws DIException{
    	
    	int numImages = dataSets.size();
    	int minSeriesRawValue = Integer.MAX_VALUE; 
    	int maxSeriesRawValue = Integer.MIN_VALUE;
		double meanSeriesRawValue 	= Double.NaN;
		double stdevSeriesRawValue 	= Double.NaN;
    	
    	for(int i=0; i<numImages; i++)	//find minmax through images
    	{
    		int minImageRawValue 	= Integer.MAX_VALUE;
    		int maxImageRawValue 	= Integer.MIN_VALUE;
    		double meanImageRawValue 	= Double.NaN;
    		double stdevImageRawValue 	= Double.NaN;
    		
    		//find min and max for  images
    		
    		DicomInterfaceDBSide dicomInterface = new DicomAdapter(dataSets.get(i));
    		int height = dicomInterface.getRows().value;
    		int width = dicomInterface.getColumns().value;
    		int[] pixelData = (int[])(dicomInterface.getPixelData());	//TODO check type!!!
    		
			for(int r=0; r<height; r++)
				for(int c=0; c<width; c++)
					{
						int actPixel = pixelData[r*width+c];
						if(actPixel < minImageRawValue)
							minImageRawValue = actPixel;
						if(actPixel > maxImageRawValue)
							maxImageRawValue = actPixel;
					}
			
			//updating series extreme values
			if(minImageRawValue < minSeriesRawValue)
				minSeriesRawValue = minImageRawValue;
			
			if(maxImageRawValue > maxSeriesRawValue)
				maxSeriesRawValue = maxImageRawValue;
    	}
    	return new SeriesStats(minSeriesRawValue, maxSeriesRawValue, meanSeriesRawValue, stdevSeriesRawValue);    	
    }
}
