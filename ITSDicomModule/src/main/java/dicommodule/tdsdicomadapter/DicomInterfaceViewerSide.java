package dicommodule.tdsdicomadapter;

import dicommodule.processing.DicomLUT;
import dicommodule.processing.SingleBooleanWithState;
import dicommodule.processing.SingleDoubleWithState;
import dicommodule.processing.SingleIntWithState;
import tdsdicominterface.exception.DIException;

public interface DicomInterfaceViewerSide {

	

		public abstract String getPhysiciansOfRecordDICOMNames() throws DIException;
		public abstract String getPhysiciansReadingStudyDICOMNames() throws DIException; 


		public abstract SingleDoubleWithState getPatientSize() throws DIException;
		public abstract SingleDoubleWithState getPatientsWeight() throws DIException;
		public abstract String getPatientOccupation() throws DIException;

	
	
		
	
	//Series
	
//		public abstract String getReferencedPerformedProcedureStepSequence() throws DIException;
		public abstract String getPatientPosition() throws DIException;
		public abstract SingleIntWithState getSmallestPixelValueInSeries() throws DIException;
		public abstract SingleIntWithState getLargestPixelValueInSeries() throws DIException;
	
//		public abstract String getClinicalTrialCoordinatingCenterName() throws DIException;
//		public abstract String getFilterType() throws DIException;	//for CR series only
//		public abstract String getCollimatorOrGridNAme() throws DIException;	//for CR series only
//		public abstract String getFocalSpot() throws DIException;	//for CR series only
//		public abstract String getPlateType() throws DIException;	//for CR series only
//		public abstract String getPhosphorType() throws DIException;	//for CR series only

		public abstract String getPositionReferenceIndicator() throws DIException;

		//	public abstract String getEquipmentSoftwareVersions() throws DIException;
		//	public abstract String getEquipmentSpatialResolution() throws DIException;

	
	
	

	
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
		
		public abstract DicomLUT[] getVOILUT() throws DIException;
		public abstract Double[] getWindowCenter() throws DIException;
		public abstract Double[] getWindowWidth() throws DIException;
		public abstract String getWindowCenterAndWidthExplanationStrings() throws DIException;
		public abstract String getVOILUTFunction() throws DIException;
		
		public abstract String getViewPosition() throws DIException;	//for CR series only
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
		
	//	public abstract String getSpecificCharacterSet() throws DIException;
	//	public abstract String getInstanceCreationDate() throws DIException;
	//	public abstract String getInstanceCreationTime() throws DIException;
	//	public abstract String getInstanceCreatorUID() throws DIException;

		
	
		
	
	
	
	
}
