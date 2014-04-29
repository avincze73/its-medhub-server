package dicommodule.tdsdicomadapter;

import dicommodule.processing.SingleIntWithState;
import tdsdicominterface.exception.DIException;

public interface DicomInterfaceDBSide {

	
    //TDS use
    String getUniqueId();

    
    
    //Case/work/job
    public abstract String getAccessionNumber() throws DIException;

    public abstract String getAdmissionID() throws DIException;
//	public abstract String getIssuerOfAdmissionID() throws DIException;

    public abstract String getServiceEpisodeID() throws DIException;
//	public abstract String getIssuerOfServiceEpisodeID() throws DIException;

    public abstract String getServiceEpisodeDescription() throws DIException;

    public abstract String getClinicalTrialTimePointID() throws DIException;

    public abstract String getClinicalTrialTimePointDescription() throws DIException;

    
    //DicomPatientData
    public abstract String getDICOMPatientName() throws DIException;

    public abstract String getDICOMPatientID() throws DIException;

    public abstract String getIssuerOfPatientID() throws DIException;

    public abstract String getTypeOfPatientID() throws DIException;

    public abstract String getPatientDoBYYYYMMDD() throws DIException;

    public abstract String getPatientSex() throws DIException;

    public abstract String getOtherPatientIDs() throws DIException;

    public abstract String[] getOtherPatientIDRecords() throws DIException;

    public abstract String getOtherDICOMPatientNames() throws DIException;

    public abstract String getPatientComments() throws DIException;

    public abstract String getPatientsMothersBirthName() throws DIException;

    
    
    //Study
    public abstract String getStudyInstanceUID() throws DIException;

    public abstract String getStudyDateYYYYMMDD() throws DIException;

    public abstract String getStudyTimeHHMMSS() throws DIException;

    public abstract String getReferringPhysyciansDICOMName() throws DIException;
//	public abstract String getReferringPhysycianIdentification() throws DIException;

    public abstract String getStudyID() throws DIException;
//	public abstract String getAccessionNumber() throws DIException;  Case-be 

    public abstract String getStudyDescription() throws DIException;

    public abstract String getAdmittingDiagnoseDescription() throws DIException;
//	public abstract String getAdmittingDiagnoseCodeSequence() throws DIException;

    public abstract String getAdditionalPatientHistory() throws DIException;
//	public abstract String getAdmissionID() throws DIException;  Case-be
//	public abstract String getIssuerOfAdmissionID() throws DIException;  Case-be
//	public abstract String getServiceEpisodeID() throws DIException;  Case-be
//	public abstract String getIssuerOfServiceEpisodeID() throws DIException;  Case-be
//	public abstract String getServiceEpisodeDescription() throws DIException;  Case-be

//	public abstract String getClinicalTrialTimePointID() throws DIException;  Case-be
//	public abstract String getClinicalTrialTimePointDescription() throws DIException;  Case-be
    public abstract String getPatientMedicalAlerts() throws DIException;

    public abstract String getSmokingStatus() throws DIException;

    
    
    //Series
    public abstract String getModality() throws DIException;

    public abstract String getSeriesInstanceUID() throws DIException;

    public abstract SingleIntWithState getSeriesNumber() throws DIException;

    public abstract String getLaterality() throws DIException;

    public abstract String getSeriesDateYYYYMMDD() throws DIException;

    public abstract String getSeriesTimeHHMMSS() throws DIException;

    public abstract String getPerformingPhysiciansDICOMName() throws DIException;

    public abstract String getProtocolName() throws DIException;

    public abstract String getSeriesDescription() throws DIException;

    public abstract String getOperatorsDICOMName() throws DIException;

    public abstract String getBodyPartExamined() throws DIException;

    public abstract String getClinicalTrialSeriesID() throws DIException;

    public abstract String getClinicalTrialSeriesDescription() throws DIException;

    public abstract String getFrameOfReferenceUID() throws DIException;

    public abstract String getEquipmentManufacturer() throws DIException;

    public abstract String getEquipmentLocationInstitutionName() throws DIException;

    public abstract String getEquipmentStationName() throws DIException;

    public abstract String getEquipmentInstitutionalDepartmentName() throws DIException;

    public abstract String getEquipmentManufacturersModelName() throws DIException;

    public abstract String getEquipmentDeviceSerialNumber() throws DIException;

    public abstract String getEquipmentDateOfLastCalibrationYYYYMMDD() throws DIException;

    public abstract String getEquipmentTimeOfLastCalibrationHHMMSS() throws DIException;
    
	public abstract SingleIntWithState getSmallestPixelValueInSeries() throws DIException;
	
	public abstract SingleIntWithState getLargestPixelValueInSeries() throws DIException;

	
	
    //Image
    //SOP (???)
    public abstract String getSOPClassUID() throws DIException;

    public abstract String getSOPInstanceUID() throws DIException;

    public abstract String getTimezoneOffsetFromUTC() throws DIException;

    public abstract String getInstanceNumber() throws DIException;

    public abstract String getSOPInstanceStatus() throws DIException;

    public abstract String getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ() throws DIException;

    public abstract String getSOPAuthorizationComment() throws DIException;
    
//	public abstract String getEncryptedAttributes() throws DIException;
//	public abstract String getHL7StructuredDocument() throws DIException;
    
	public abstract SingleIntWithState getRows() throws DIException;

	public abstract SingleIntWithState getColumns() throws DIException;

	public abstract Object getPixelData() throws DIException;	//shall return either a Short[] or a Byte[]
	
/*	public abstract SingleIntWithState getSmallestImagePixelValue() throws DIException;
	
	public abstract SingleIntWithState getLargestImagePixelValue() throws DIException;*/

    
    
    
}
