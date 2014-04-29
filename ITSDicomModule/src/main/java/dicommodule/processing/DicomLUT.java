package dicommodule.processing;

public class DicomLUT {

	public static final String INTARRAYTYPELUTDATA   = "INTARRAYTYPE";
	public static final String SHORTARRAYTYPELUTDATA = "SHORTARRAYTYPE";
	public static final String MISSINGLUTDATA = "MISSINGLUTDATA";

//	private boolean empty;	//True means element was missing, false means element was empty

	private Integer[]  LUTDescriptor;
	private String LUTExplanation;
//	private String LUTFunction;

	private Integer[]  LUTDataIntArray;
	private Short[] LUTDataShortArray;
	
	
	private String LUTDataJavaType;
	
	
	
	//---Constructors----------
		
/*	private DicomLUT(){
		this.LUTDescriptor = null;
		this.LUTExplanation = null;
		this.LUTDataIntArray = null;
		this.LUTDataShortArray = null;
		this.LUTDataJavaType = null;
		this.empty = true;
	}*/
	
	public DicomLUT(Integer[] LUTDescriptor, String LUTExplanation, Integer[] LUTData){
		this.LUTDescriptor = LUTDescriptor;
		this.LUTExplanation = LUTExplanation;
		this.LUTDataIntArray = LUTData;
		this.LUTDataShortArray = null;
		this.LUTDataJavaType = INTARRAYTYPELUTDATA;
//		this.empty = false;
	}
	
	
	public DicomLUT(Integer[] LUTDescriptor, String LUTExplanation, Short[] LUTData){
		this.LUTDescriptor = LUTDescriptor;
		this.LUTExplanation = LUTExplanation;
		this.LUTDataIntArray = null;
		this.LUTDataShortArray = LUTData;
		this.LUTDataJavaType = SHORTARRAYTYPELUTDATA;		
//		this.empty = false;
	}

	
	public DicomLUT(Integer[] LUTDescriptor, String LUTExplanation){
		this.LUTDescriptor = LUTDescriptor;
		this.LUTExplanation = LUTExplanation;
		this.LUTDataIntArray = null;
		this.LUTDataShortArray = null;
		this.LUTDataJavaType = MISSINGLUTDATA;		
//		this.empty = false;
	}
	
	
/*	public static DicomLUT createEmpty(){
		return new DicomLUT();
	}*/

	
	//---get, is----------
	
	
	public Integer[] getLUTDescriptor()   { return LUTDescriptor; 	}
	public String getLUTExplanation() { return LUTExplanation; 	}
	
	public Object getLUTData() {
		
			if(LUTDataJavaType == INTARRAYTYPELUTDATA)
				return LUTDataIntArray; 	
			else 
				return LUTDataShortArray;
		}
	
	public String getLUTDataJavaType(){	return LUTDataJavaType; }
	
	
		
//	public boolean isEmpty() {return empty;}
	
	
}
