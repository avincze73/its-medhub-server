package dicommodule.oldretiredandtesting;

import dicommodule.processing.DicomLUT;
import dicommodule.processing.SingleBooleanWithState;
import dicommodule.processing.SingleDoubleWithState;
import dicommodule.processing.SingleIntWithState;
import tdsdicominterface.exception.DIException;


public abstract class AbstractDicomAdapter {
	
    /**
     * The types of return values:
     * Integer[], Double[], Short[], Byte[] - null return value means complete abscence of data, 0-long array means empty field
     * String (including date and time strings) - null return value means complete abscence of data, empty String means empty field
     * SingleBooleanWithState, SingleDoubleWithState, SingleIntWithState - null return value means complete abscence of data, if 
     * 				Boolean/Double/Integer inside is null, that means empty field (there is a function in these return objects to test this)  
     * DicomLUT, ModalityLUT - null return value means complete absence of the all its data,  for the fields inside the DicomLUT objects, 
     * 				the interpretation rules for completely missing and empty values follow the String / array rules  
     * Object - Object generally returns Short[] or Integer[], hence should be interpreted as the Short[] or Integer[] arrays  
     */
    
    
	//TDS use
    
	public abstract String getUniqueId();

	
	
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
	public abstract String getPhysiciansOfRecordDICOMNames() throws DIException;
	public abstract String getPhysiciansReadingStudyDICOMNames() throws DIException; 


	public abstract String getAdmittingDiagnoseDescription() throws DIException;
//	public abstract String getAdmittingDiagnoseCodeSequence() throws DIException;
	public abstract SingleDoubleWithState getPatientSize() throws DIException;
	public abstract SingleDoubleWithState getPatientsWeight() throws DIException;
	public abstract String getPatientOccupation() throws DIException;
	public abstract String getAdditionalPatientHistory() throws DIException;
//	public abstract String getAdmissionID() throws DIException;  Case-be
////	public abstract String getIssuerOfAdmissionID() throws DIException;  Case-be
//	public abstract String getServiceEpisodeID() throws DIException;  Case-be
////	public abstract String getIssuerOfServiceEpisodeID() throws DIException;  Case-be
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
//	public abstract String getReferencedPerformedProcedureStepSequence() throws DIException;
	public abstract String getBodyPartExamined() throws DIException; 	
	public abstract String getPatientPosition() throws DIException;
	public abstract SingleIntWithState getSmallestPixelValueInSeries() throws DIException;
	public abstract SingleIntWithState getLargestPixelValueInSeries() throws DIException;
	
//	public abstract String getClinicalTrialCoordinatingCenterName() throws DIException;
	public abstract String getClinicalTrialSeriesID() throws DIException;
	public abstract String getClinicalTrialSeriesDescription() throws DIException;
	
	public abstract String getViewPosition() throws DIException;	//for CR series only
//	public abstract String getFilterType() throws DIException;	//for CR series only
//	public abstract String getCollimatorOrGridNAme() throws DIException;	//for CR series only
//	public abstract String getFocalSpot() throws DIException;	//for CR series only
//	public abstract String getPlateType() throws DIException;	//for CR series only
//	public abstract String getPhosphorType() throws DIException;	//for CR series only

	public abstract String getFrameOfReferenceUID() throws DIException;
	public abstract String getPositionReferenceIndicator() throws DIException;

	public abstract String getEquipmentManufacturer() throws DIException;
	public abstract String getEquipmentLocationInstitutionName() throws DIException;
	public abstract String getEquipmentStationName() throws DIException;
	public abstract String getEquipmentInstitutionalDepartmentName() throws DIException;
	public abstract String getEquipmentManufacturersModelName() throws DIException;
	public abstract String getEquipmentDeviceSerialNumber() throws DIException;
//	public abstract String getEquipmentSoftwareVersions() throws DIException;
//	public abstract String getEquipmentSpatialResolution() throws DIException;
	public abstract String getEquipmentDateOfLastCalibrationYYYYMMDD() throws DIException;
	public abstract String getEquipmentTimeOfLastCalibrationHHMMSS() throws DIException;
	
	
	
	//Image
	
		public abstract SingleIntWithState getImageInstanceNumber() throws DIException;
		public abstract String getPatientOrientation() throws DIException;
		public abstract String getContentDateYYYYMMDD() throws DIException;
		public abstract String getContentTimeHHMMSS() throws DIException;
		public abstract String getImageType() throws DIException;
		public abstract SingleIntWithState getAcquisitionNumber() throws DIException;
		public abstract String getAcquisitionDateYYYYMMDD() throws DIException;
		public abstract String getAcquisitionTimeHHMMSS() throws DIException;
//		public abstract String getAcquisitionDateTime() throws DIException; -covered by the above two
		public abstract SingleIntWithState getImagesInAcquisition() throws DIException;
		public abstract String getImageComments() throws DIException;
		public abstract SingleBooleanWithState getIsQualityControlImage() throws DIException;  //???
		public abstract SingleBooleanWithState getHasBurnedInAnnotation() throws DIException;
		public abstract SingleBooleanWithState getHadLossyImageCompression() throws DIException;
		public abstract Double[] getLossyImageCompressionRatio() throws DIException;
		public abstract String getLossyImageCompressionMethod() throws DIException;
//		public abstract String getIconImage() throws DIException;
		public abstract DicomLUT getPresentationLUT() throws DIException;
		public abstract String getPresentationLUTShape() throws DIException;

	
		public abstract SingleIntWithState getSamplesPerPixel() throws DIException;
		public abstract String getPhotometricInterpretation() throws DIException;
		public abstract SingleIntWithState getRows() throws DIException;
		public abstract SingleIntWithState getColumns() throws DIException;
		public abstract SingleIntWithState getBitsAllocated() throws DIException;
		public abstract SingleIntWithState getBitsStored() throws DIException;
		public abstract SingleIntWithState getHighBit() throws DIException;
		public abstract SingleIntWithState getPixelRepresentation() throws DIException;
		public abstract Object getPixelData() throws DIException;	//shall return either a Short[] or a Byte[]
		public abstract SingleIntWithState getPlanarConfiguration() throws DIException;
		public abstract Integer[] getPixelAspectRatio() throws DIException;
		public abstract SingleIntWithState getSmallestImagePixelValue() throws DIException;
		public abstract SingleIntWithState getLargestImagePixelValue() throws DIException;
		public abstract Integer[] getRedPaletteLookupTableDescriptor() throws DIException;
		public abstract Integer[] getGreenPaletteLookupTableDescriptor() throws DIException;
		public abstract Integer[] getBluePaletteLookupTableDescriptor() throws DIException;
		public abstract Short[] getRedPaletteLookupTableData() throws DIException;
		public abstract Short[] getGreenPaletteLookupTableData() throws DIException;
		public abstract Short[] getBluePaletteLookupTableData() throws DIException;
		public abstract Byte[] getICCProfile() throws DIException;
	
//		public abstract String getPixelDataProviderURL() throws DIException;
	
//		public abstract String getPixelPaddingRangeLimit() throws DIException;

		public abstract SingleDoubleWithState getSpacingBetweenSlices() throws DIException;

	 
		
		public abstract String getContrastOrBolusAgent() throws DIException;
		public abstract String getContrastOrBolusRoute() throws DIException;
		public abstract SingleDoubleWithState getContrastOrBolusVolume() throws DIException;
		public abstract String getContrastOrBolusStartTime() throws DIException;
		public abstract String getContrastOrBolusStopTime() throws DIException;
		public abstract SingleDoubleWithState getContrastOrBolusTotalDose() throws DIException;
	//	public abstract String getContrastOrBolusFlowRate() throws DIException;
	//	public abstract String getContrastOrBolusFlowDuration() throws DIException;
		public abstract String getContrastOrBolusIngredient() throws DIException;
		public abstract SingleDoubleWithState getContrastOrBolusIngredientConcentration() throws DIException;
		
	//	public abstract String getDevice() throws DIException;
		
		public abstract Double[] getPixelSpacing() throws DIException;
		public abstract Double[] getImageOrientationPatient() throws DIException;
		public abstract Double[] getImagePositionPatient() throws DIException;
		public abstract SingleDoubleWithState getSliceThickness() throws DIException;
		public abstract SingleDoubleWithState getSliceLocation() throws DIException;
		
	//	public abstract String getAcquisitionContextSequence() throws DIException;
		public abstract String getAcquisitionContextDescription () throws DIException;
		
		public abstract SingleIntWithState getOverlayRows() throws DIException;
		public abstract SingleIntWithState getOverlayColumns() throws DIException;
		public abstract String getOverlayType() throws DIException;
		public abstract Integer[] getOverlayOrigin() throws DIException;
		public abstract SingleIntWithState getOverlayBitsAllocated() throws DIException;
		public abstract SingleIntWithState getOverlayBitPosition() throws DIException;
		public abstract Object getOverlayData() throws DIException;	//shall return either a Short[] or a Byte[]
		public abstract String getOverlayDescription() throws DIException;
		public abstract String getOverlaySubtype() throws DIException;
		public abstract String getOverlayLabel() throws DIException;
		public abstract SingleIntWithState getROIArea() throws DIException;
		public abstract SingleDoubleWithState getROIMean() throws DIException;
		public abstract SingleDoubleWithState getROIStandardDeviation() throws DIException;
		
		public abstract DicomLUT getVOILUT() throws DIException;
		public abstract Double[] getWindowCenter() throws DIException;
		public abstract Double[] getWindowWidth() throws DIException;
		public abstract String getWindowCenterAndWidthExplanationStrings() throws DIException;
		public abstract String getVOILUTFunction() throws DIException;
		
	//	public abstract String getKVP() throws DIException;	//CR image only	
	//	public abstract String getPlateID() throws DIException;	//CR image only
	//	public abstract String getExposureTime() throws DIException;	//CR image only
	//	public abstract String getExposure() throws DIException;	//CR image only
	//	public abstract String getExposureInMicroAs() throws DIException;	//CR image only
	
		public abstract DicomLUT getModalityLUT() throws DIException;
		public abstract SingleDoubleWithState getModalityLUTRescaleIntercept() throws DIException;
		public abstract SingleDoubleWithState getModalityLUTRescaleSlope() throws DIException;
		public abstract String getModalityLUTRescaleType() throws DIException;
	
	//	public abstract String getDataCollectionDiameter() throws DIException;
		
	//	public abstract String getScanningSequence() throws DIException;
	//	public abstract String getSequenceVariant() throws DIException;
	//	public abstract String getScanOptions() throws DIException;
	//	public abstract String getMRAcquisitionType() throws DIException;
	//	public abstract String getRepetitionTime() throws DIException;
	//	public abstract String getEchoTime() throws DIException;
	//	public abstract String getEchoTrainLength() throws DIException;
	//	public abstract String getInversionTime() throws DIException;
	//	public abstract String getTriggerTime() throws DIException;
	//	public abstract String getSpacingBetweenSlices() throws DIException;
	
		
		
	//SOP (???)
		
	public abstract String getSOPClassUID() throws DIException;
	public abstract String getSOPInstanceUID() throws DIException;
	//	public abstract String getSpecificCharacterSet() throws DIException;
	//	public abstract String getInstanceCreationDate() throws DIException;
	//	public abstract String getInstanceCreationTime() throws DIException;
	//	public abstract String getInstanceCreatorUID() throws DIException;
	public abstract String getTimezoneOffsetFromUTC() throws DIException;
	public abstract String getInstanceNumber() throws DIException;
	public abstract String getSOPInstanceStatus() throws DIException;
	public abstract String getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ() throws DIException;
	public abstract String getSOPAuthorizationComment() throws DIException;
	public abstract String getEncryptedAttributes() throws DIException;
	public abstract String getHL7StructuredDocument() throws DIException;
		
	
	
	
}
