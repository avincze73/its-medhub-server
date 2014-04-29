package dicommodule.processing;


public class DicomDataProcessor {

	
	public class OtherPatientID{	
		public String ID, type, issuer;
	}
	
	
/*	public static Date dicomDAtoJavaDate(String dicomDAstring){

		if(dicomDAstring.contains("."))	//. notation   - "yyyy.mm.dd"
			{
				int year 	= Integer.parseInt(dicomDAstring.substring(0, 4)) - 1900;
				int month 	= Integer.parseInt(dicomDAstring.substring(5, 7));
				int day 	= Integer.parseInt(dicomDAstring.substring(8, 10));
				return new Date(year, month, day); 
			}			
		else	//no delimiter - "yyyymmdd"
			{
				int year 	= Integer.parseInt(dicomDAstring.substring(0, 4)) - 1900;
				int month 	= Integer.parseInt(dicomDAstring.substring(4, 6));
				int day 	= Integer.parseInt(dicomDAstring.substring(6, 8));
				return new Date(year, month, day); 
			}
	}*/
	
	
	public static String dicomDAtoYYYYMMDDString(String dicomDAstring){

		String transformedString = dicomDAstring;
		
		//get rid of possible . delimiters
		transformedString.replaceAll(".","");

		return transformedString;
		
		
/*		if(dicomDAstring.contains("."))	//. notation   - "yyyy.mm.dd"
			{
				String year 	= dicomDAstring.substring(0, 4) ;
				String month 	= dicomDAstring.substring(5, 7);
				String day 		= dicomDAstring.substring(8, 10);
				return year + month + day; 
			}			
		else	//no delimiter - "yyyymmdd"
			{
				String year 	= dicomDAstring.substring(0, 4);
				String month 	= dicomDAstring.substring(4, 6);
				String day 		= dicomDAstring.substring(6, 8);
				return year + month + day; 
			}*/		
	}
	
	
	public static String dicomTMtoHHMMSSFFFFFFString(String dicomTMstring){

		String transformedString = dicomTMstring;	

		//first get rid of possible : and . delimiters 
		transformedString.replaceAll(":","");
		transformedString.replaceAll(".","");
		
		//cutting off the trailing spaces
		int firstSpaceIndex = transformedString.indexOf(" "); 
		if(firstSpaceIndex != -1)
			transformedString = transformedString.substring(firstSpaceIndex);
		
		//ensuring that the string is exactly 12 characters long
		for(int i=transformedString.length(); i<12; i++)
			transformedString = transformedString + "0";
		

		return transformedString;
		
/*		if(dicomDAstring.contains(":"))	//. notation   - "hh:mm:ss.ffffff"
			{
				String hour 	= dicomDAstring.substring(0, 2) ;
				String mins 	= dicomDAstring.substring(3, 5);
				String secs		= dicomDAstring.substring(6, 8);
				return hour + mins + secs; 
			}			
		else	//no delimiter - "hhmmss.ffffff"
			{
				String hour 	= dicomDAstring.substring(0, 2) ;
				String mins 	= dicomDAstring.substring(2, 4);
				String secs		= dicomDAstring.substring(4, 6);
				return hour + mins + secs; 
			}*/			
				
	}	

	
	
	public static String dicomDTtoYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString(String dicomDTstring){
		
		String transformedMainString, timeZoneSuffix;
		
		int plusSuffixStarIndex =  dicomDTstring.indexOf("+");
		int minusSuffixStarIndex =  dicomDTstring.indexOf("-");
		int suffixStartIndex = Math.max(plusSuffixStarIndex, minusSuffixStarIndex);
		
		if(dicomDTstring.contains("+") || dicomDTstring.contains("-"))
			{			
				transformedMainString = dicomDTstring.substring(0,suffixStartIndex);
				timeZoneSuffix = dicomDTstring.substring(suffixStartIndex);
			}
		else
			{
				transformedMainString = dicomDTstring;
				timeZoneSuffix = "";
			}
		
		
		//first get rid of possible . delimiter 
		transformedMainString.replaceAll(".","");
		
		//cutting off the trailing spaces
		int firstSpaceIndex = transformedMainString.indexOf(" "); 
		if(firstSpaceIndex != -1)
			transformedMainString = transformedMainString.substring(firstSpaceIndex);
		
		//ensuring that the string is exactly 12 characters long
		for(int i=transformedMainString.length(); i<20; i++)
			transformedMainString = transformedMainString + "0";
		

		//ensuring that the timeZoneSuffix is either "" or is exactly 5 characters long
		if(timeZoneSuffix.length()>0)
			for(int i=timeZoneSuffix.length(); i<5; i++)
				timeZoneSuffix = timeZoneSuffix + "0";

		
		return transformedMainString + timeZoneSuffix;		

	}

	
	
	
	
	public static String[] dicomPNtoStringArray(String dicomPNstring){
		String [] ans = dicomPNstring.split("^"); //TODO check!!!
		return ans;
	}
	
	
	public static String stringArrayToWholeNameString(String[] strArray){	
		if (strArray.length ==5)
			return strArray[3] + strArray[1] + strArray[2] + strArray[0] + strArray[4];
		else
			return strArray[0] + strArray[1] + strArray[2] + strArray[3] + strArray[4] + "(Name Parse Error)";
	}
	
	
	public static String dicomPNtoWholeNameString(String dicomPNstring){
		return stringArrayToWholeNameString(dicomPNtoStringArray(dicomPNstring));
	}
	

	
	
	public static String[] splitDICOMConcatenatedStrings(String concatenatedStrings){
		return concatenatedStrings.split("\\");				
	}
	
	public static String getDateAsYYYYMMDDfromYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ(String stringDT){
		return stringDT.substring(0, 8);
	}
	
	public static String getTimeAsHHMMSSFFFFFFfromYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ(String stringDT){
		return stringDT.substring(8, 19);
	}

	
	
}
