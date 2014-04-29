package dicommodule.oldretiredandtesting;

import tdsdicominterface.DicomDataSet;


public class DicomIdentifier extends AbstractDicomIdentifier {

    public DicomIdentifier(DicomDataSet dicomDataSet) 
    {
    	super(dicomDataSet);	
    }

    
	//----------------------------------------------------------------------------------

	
	//TDS use
    
	@Override
	public String getUniqueId(){
		return dicomDataSet.getSopInstanceUID();
	}

}
