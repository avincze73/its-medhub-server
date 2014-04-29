package dicommodule.oldretiredandtesting;

import tdsdicominterface.DicomDataSet;


public abstract class AbstractDicomIdentifier {
	
	protected DicomDataSet dicomDataSet;
	
    public AbstractDicomIdentifier(DicomDataSet dicomDataSet) 
    {
    	this.dicomDataSet = dicomDataSet; 
    }
    
	//----------------------------------------------------------------------------------


	public abstract String getUniqueId();

}
