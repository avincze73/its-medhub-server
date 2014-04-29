package dicommodule.processing;


public class ModalityLUT extends DicomLUT {

	private String ModalityLUTType;
	
	
	public ModalityLUT(Integer[] LUTDescriptor, String LUTExplanation, String ModalityLUTType, Short[] LUTData){
		super(LUTDescriptor, LUTExplanation, LUTData);
		this.ModalityLUTType = ModalityLUTType;
		
	}

	
	public ModalityLUT(Integer[] LUTDescriptor, String LUTExplanation, String ModalityLUTType, Integer[] LUTData){
		super(LUTDescriptor, LUTExplanation, LUTData);
		this.ModalityLUTType = ModalityLUTType;
		
	}

	
}
