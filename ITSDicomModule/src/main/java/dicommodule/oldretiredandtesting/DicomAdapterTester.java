package dicommodule.oldretiredandtesting;

import java.io.File;
import java.io.FileNotFoundException;

import dicommodule.tdsdicomadapter.DicomAdapter;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomManager;
import tdsdicominterface.exception.DIException;

public class DicomAdapterTester {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		DicomAdapterTester myDAT = new DicomAdapterTester();
/*		System.out.println(Double.parseDouble(""));
		System.out.println(Integer.parseInt(""));
		System.out.println(Short.parseShort(""));
		System.out.println(Byte.parseByte(""));
*/		
		myDAT.testAdapter();
//		int[] nullaInt = new int[0];
//		System.out.println("nullaInt: '"+nullaInt+"'");

/*		
		String orig = ",jkmhbkjhbv\\lkhj\\;lih;ih";
		System.out.println(orig);
//		String delimiter = new String(new char[]{'\\\\'});
		String delimiter = "\\\\";
		String[] split = orig.split(delimiter);
			for(int i=0; i< split.length; i++)
				System.out.println("\t"+split[i]);
				
				*/
	}
	

	
	private void testAdapter(){
		
		//File
		String filePath = "cttest.dcm"; 
		File file = new File(filePath);
		
		try {
			//Import
			DicomDataSet processedDicomFile = DicomManager.importFile(file);
			
			
			//Identifier
			DicomIdentifier myDI = new DicomIdentifier(processedDicomFile);
	
			//Identifier call - unique identifier
			System.out.println("\nIDENTIFIER CALLs:");
			System.out.println("\t"+"getUniqueId: "+myDI.getUniqueId());
			System.out.println();
			
			
			//Adapter 
			DicomAdapter myDA = new DicomAdapter(processedDicomFile);
			
			//Adapter calls
			System.out.println("\nADAPTER CALLs:");
			
			
				//TDS use
			System.out.println("\n ***\n TDS use\n ***\n");
			
			//public abstract String getUniqueId();
			System.out.println("\t"+"getUniqueId: \t"+myDA.getUniqueId());

			
			
			//Case/work/job
			System.out.println("\n ***\n Case/work/job\n ***\n");
			
			System.out.println("\t"+"getAccessionNumber: \t"+myDA.getAccessionNumber());
			System.out.println("\t"+"getAdmissionID: \t"+myDA.getAdmissionID());
//			System.out.println("\t"+"getIssuerOfAdmissionID: \t"+myDA.getIssuerOfAdmissionID());
			System.out.println("\t"+"getServiceEpisodeID: \t"+myDA.getServiceEpisodeID());
//			System.out.println("\t"+"getIssuerOfServiceEpisodeID: \t"+myDA.getIssuerOfServiceEpisodeID());
			System.out.println("\t"+"getServiceEpisodeDescription: \t"+myDA.getServiceEpisodeDescription());
			System.out.println("\t"+"getClinicalTrialTimePointID: \t"+myDA.getClinicalTrialTimePointID());
			System.out.println("\t"+"getClinicalTrialTimePointDescription: \t"+myDA.getClinicalTrialTimePointDescription());

			
			
			//DicomPatientData
			System.out.println("\n ***\n DicomPatientData\n ***\n");


			System.out.println("\t"+"getDICOMPatientName: \t"+myDA.getDICOMPatientName());
			System.out.println("\t"+"getDICOMPatientID: \t"+myDA.getDICOMPatientID());
			System.out.println("\t"+"getIssuerOfPatientID: \t"+myDA.getIssuerOfPatientID());
			System.out.println("\t"+"getTypeOfPatientID: \t"+myDA.getTypeOfPatientID());
			System.out.println("\t"+"getPatientDoBYYYYMMDD: \t"+myDA.getPatientDoBYYYYMMDD());
			System.out.println("\t"+"getPatientSex: \t"+myDA.getPatientSex());
			System.out.println("\t"+"getOtherPatientIDs: \t"+myDA.getOtherPatientIDs());
			System.out.print("\t"+"getOtherPatientIDRecords: \t");
				String[] stringAns = myDA.getOtherPatientIDRecords();
				stringArrayOutput(stringAns);
			System.out.println("\t"+"getOtherDICOMPatientNames: \t"+myDA.getOtherDICOMPatientNames());
			System.out.println("\t"+"getPatientComments: \t"+myDA.getPatientComments());

			System.out.println();
			System.out.println("\t"+"getPatientsMothersBirthName: \t"+myDA.getPatientsMothersBirthName());


			
			

			//Study
			System.out.println("\n ***\n Study\n ***\n");

		
			System.out.println("\t"+"getStudyInstanceUID: \t"+myDA.getStudyInstanceUID());
			System.out.println("\t"+"getStudyDateYYYYMMDD: \t"+myDA.getStudyDateYYYYMMDD());			
			System.out.println("\t"+"getStudyTimeHHMMSS: \t"+myDA.getStudyTimeHHMMSS());
			System.out.println("\t"+"getReferringPhysyciansDICOMName: \t"+myDA.getReferringPhysyciansDICOMName());			
//			System.out.println("\t"+"getReferringPhysycianIdentification: \t"+myDA.getReferringPhysycianIdentification());
			System.out.println("\t"+"getStudyID: \t"+myDA.getStudyID());
//			System.out.println("\t"+"getAccessionNumber: \t"+myDA.getAccessionNumber());			
			System.out.println("\t"+"getStudyDescription: \t"+myDA.getStudyDescription());
			System.out.println("\t"+"getPhysiciansOfRecordDICOMNames: \t"+myDA.getPhysiciansOfRecordDICOMNames());			
			System.out.println("\t"+"getPhysiciansReadingStudyDICOMNames: \t"+myDA.getPhysiciansReadingStudyDICOMNames());
			
			System.out.println();
			System.out.println("\t"+"getAdmittingDiagnoseDescription: \t"+myDA.getAdmittingDiagnoseDescription());			
//			System.out.println("\t"+"getAdmittingDiagnoseCodeSequence: \t"+myDA.getAdmittingDiagnoseCodeSequence());
			System.out.println("\t"+"getPatientSize: \t"+myDA.getPatientSize());			
			System.out.println("\t"+"getPatientsWeight: \t"+myDA.getPatientsWeight());
			System.out.println("\t"+"getPatientOccupation: \t"+myDA.getPatientOccupation());			
			System.out.println("\t"+"getAdditionalPatientHistory: \t"+myDA.getAdditionalPatientHistory());
//			System.out.println("\t"+"getAdmissionID: \t"+myDA.getAdmissionID());			
//			System.out.println("\t"+"getIssuerOfAdmissionID: \t"+myDA.getIssuerOfAdmissionID());
//			System.out.println("\t"+"getServiceEpisodeID: \t"+myDA.getServiceEpisodeID());			
//			System.out.println("\t"+"getIssuerOfServiceEpisodeID: \t"+myDA.getIssuerOfServiceEpisodeID());
//			System.out.println("\t"+"getServiceEpisodeDescription: \t"+myDA.getServiceEpisodeDescription());			

//			System.out.println();
//			System.out.println("\t"+"getClinicalTrialTimePointID: \t"+myDA.getClinicalTrialTimePointID());
//			System.out.println("\t"+"getClinicalTrialTimePointDescription: \t"+myDA.getClinicalTrialTimePointDescription());			

			System.out.println();
			System.out.println("\t"+"getPatientMedicalAlerts: \t"+myDA.getPatientMedicalAlerts());
			
			System.out.println();
			System.out.println("\t"+"getSmokingStatus: \t"+myDA.getSmokingStatus());			

		
			
			//Series
			System.out.println("\n ***\n Series\n ***\n");

			
			System.out.println("\t"+"getModality: \t"+myDA.getModality());
			System.out.println("\t"+"getSeriesInstanceUID: \t"+myDA.getSeriesInstanceUID());			
			System.out.println("\t"+"getSeriesNumber: \t"+myDA.getSeriesNumber());
			System.out.println("\t"+"getLaterality: \t"+myDA.getLaterality());			
			System.out.println("\t"+"getSeriesDateYYYYMMDD: \t"+myDA.getSeriesDateYYYYMMDD());
			System.out.println("\t"+"getSeriesTimeHHMMSS: \t"+myDA.getSeriesTimeHHMMSS());			
			System.out.println("\t"+"getPerformingPhysiciansDICOMName: \t"+myDA.getPerformingPhysiciansDICOMName());
			System.out.println("\t"+"getProtocolName: \t"+myDA.getProtocolName());			
			System.out.println("\t"+"getSeriesDescription: \t"+myDA.getSeriesDescription());
			System.out.println("\t"+"getOperatorsDICOMName: \t"+myDA.getOperatorsDICOMName());			
//			System.out.println("\t"+"getReferencedPerformedProcedureStepSequence: \t"+myDA.getReferencedPerformedProcedureStepSequence());
			System.out.println("\t"+"getBodyPartExamined: \t"+myDA.getBodyPartExamined());			
			System.out.println("\t"+"getPatientPosition: \t"+myDA.getPatientPosition());
			System.out.println("\t"+"getSmallestPixelValueInSeries: \t"+myDA.getSmallestPixelValueInSeries());			
			System.out.println("\t"+"getLargestPixelValueInSeries: \t"+myDA.getLargestPixelValueInSeries());
			
			System.out.println();
//			System.out.println("\t"+"getClinicalTrialCoordinatingCenterName: \t"+myDA.getClinicalTrialCoordinatingCenterName());			
			System.out.println("\t"+"getClinicalTrialSeriesID: \t"+myDA.getClinicalTrialSeriesID());			
			System.out.println("\t"+"getClinicalTrialSeriesDescription: \t"+myDA.getClinicalTrialSeriesDescription());			

			System.out.println();
			System.out.println("\t"+"getViewPosition: \t"+myDA.getViewPosition());			
//			System.out.println("\t"+"getFilterType: \t"+myDA.getFilterType());			
//			System.out.println("\t"+"getCollimatorOrGridNAme: \t"+myDA.getCollimatorOrGridNAme());			
//			System.out.println("\t"+"getFocalSpot: \t"+myDA.getFocalSpot());			
//			System.out.println("\t"+"getPlateType: \t"+myDA.getPlateType());			
//			System.out.println("\t"+"getPhosphorType: \t"+myDA.getPhosphorType());			

			System.out.println();
			System.out.println("\t"+"getFrameOfReferenceUID: \t"+myDA.getFrameOfReferenceUID());			
			System.out.println("\t"+"getPositionReferenceIndicator: \t"+myDA.getPositionReferenceIndicator());			

			System.out.println();
			System.out.println("\t"+"getEquipmentManufacturer: \t"+myDA.getEquipmentManufacturer());			
			System.out.println("\t"+"getEquipmentLocationInstitutionName: \t"+myDA.getEquipmentLocationInstitutionName());			
			System.out.println("\t"+"getEquipmentStationName: \t"+myDA.getEquipmentStationName());			
			System.out.println("\t"+"getEquipmentInstitutionalDepartmentName: \t"+myDA.getEquipmentInstitutionalDepartmentName());			
			System.out.println("\t"+"getEquipmentManufacturersModelName: \t"+myDA.getEquipmentManufacturersModelName());			
			System.out.println("\t"+"getEquipmentDeviceSerialNumber: \t"+myDA.getEquipmentDeviceSerialNumber());			
//			System.out.println("\t"+"getEquipmentSoftwareVersions: \t"+myDA.getEquipmentSoftwareVersions());			
//			System.out.println("\t"+"getEquipmentSpatialResolution: \t"+myDA.getEquipmentSpatialResolution());			
			System.out.println("\t"+"getEquipmentDateOfLastCalibrationYYYYMMDD: \t"+myDA.getEquipmentDateOfLastCalibrationYYYYMMDD());			
			System.out.println("\t"+"getEquipmentTimeOfLastCalibrationHHMMSS: \t"+myDA.getEquipmentTimeOfLastCalibrationHHMMSS());			

		
			
			
			
			//Image
			System.out.println("\n ***\n Image\n ***\n");
			
			System.out.println("\t"+"getImageInstanceNumber: \t"+myDA.getImageInstanceNumber());			
			System.out.println("\t"+"getPatientOrientation: \t"+myDA.getPatientOrientation());			
			System.out.println("\t"+"getContentDateYYYYMMDD: \t"+myDA.getContentDateYYYYMMDD());			
			System.out.println("\t"+"getContentTimeHHMMSS: \t"+myDA.getContentTimeHHMMSS());			
			System.out.println("\t"+"getImageType: \t"+myDA.getImageType());			
			System.out.println("\t"+"getAcquisitionNumber: \t"+myDA.getAcquisitionNumber());			
			System.out.println("\t"+"getAcquisitionDateYYYYMMDD: \t"+myDA.getAcquisitionDateYYYYMMDD());			
			System.out.println("\t"+"getAcquisitionTimeHHMMSS: \t"+myDA.getAcquisitionTimeHHMMSS());			
//			System.out.println("\t"+"getAcquisitionDateTime: \t"+myDA.getAcquisitionDateTime());			
			System.out.println("\t"+"getImagesInAcquisition: \t"+myDA.getImagesInAcquisition());			
			System.out.println("\t"+"getImageComments: \t"+myDA.getImageComments());			
			System.out.println("\t"+"getIsQualityControlImage: \t"+myDA.getIsQualityControlImage());			
			System.out.println("\t"+"getHasBurnedInAnnotation: \t"+myDA.getHasBurnedInAnnotation());			
			System.out.println("\t"+"getHadLossyImageCompression: \t"+myDA.getHadLossyImageCompression());			
			System.out.print("\t"+"getLossyImageCompressionRatio: \t");
				Double[] doubleArrayAns = myDA.getLossyImageCompressionRatio();
				doubleArrayOutput(doubleArrayAns);
			System.out.println("\t"+"getLossyImageCompressionMethod: \t"+myDA.getLossyImageCompressionMethod());			
//			System.out.println("\t"+"getIconImage: \t"+myDA.getIconImage());			
			System.out.println("\t"+"getPresentationLUT: \t"+myDA.getPresentationLUT());			
			System.out.println("\t"+"getPresentationLUTShape: \t"+myDA.getPresentationLUTShape());	
			
			System.out.println();
			System.out.println("\t"+"getSamplesPerPixel: \t"+myDA.getSamplesPerPixel());			
			System.out.println("\t"+"getPhotometricInterpretation: \t"+myDA.getPhotometricInterpretation());			
			System.out.println("\t"+"getRows: \t"+myDA.getRows());			
			System.out.println("\t"+"getColumns: \t"+myDA.getColumns());			
			System.out.println("\t"+"getBitsAllocated: \t"+myDA.getBitsAllocated());			
			System.out.println("\t"+"getBitsStored: \t"+myDA.getBitsStored());			
			System.out.println("\t"+"getHighBit: \t"+myDA.getHighBit());			
			System.out.println("\t"+"getPixelRepresentation: \t"+myDA.getPixelRepresentation());			
			System.out.println("\t"+"getPixelData: \t"+myDA.getPixelData());   //TODO			
			System.out.println("\t"+"getPlanarConfiguration: \t"+myDA.getPlanarConfiguration());			
			System.out.print("\t"+"getPixelAspectRatio: \t");	
				Integer[] intArrayAns = myDA.getPixelAspectRatio();
				intArrayOutput(intArrayAns);
			System.out.println("\t"+"getSmallestImagePixelValue: \t"+myDA.getSmallestImagePixelValue());			
			System.out.println("\t"+"getLargestImagePixelValue: \t"+myDA.getLargestImagePixelValue());			
			System.out.print("\t"+"getRedPaletteLookupTableDescriptor: \t");	
				intArrayAns = myDA.getPixelAspectRatio();
				intArrayOutput(intArrayAns);		
			System.out.print("\t"+"getGreenPaletteLookupTableDescriptor: \t");	
				intArrayAns = myDA.getGreenPaletteLookupTableDescriptor();
				intArrayOutput(intArrayAns);		
			System.out.print("\t"+"getBluePaletteLookupTableDescriptor: \t");	
				intArrayAns = myDA.getBluePaletteLookupTableDescriptor();
				intArrayOutput(intArrayAns);		
			System.out.print("\t"+"getRedPaletteLookupTableData: \t");	
				Short[] shortArrayAns = myDA.getRedPaletteLookupTableData();
				shortArrayOutput(shortArrayAns);		
			System.out.print("\t"+"getGreenPaletteLookupTableData: \t");	
				shortArrayAns = myDA.getGreenPaletteLookupTableData();
				shortArrayOutput(shortArrayAns);					
			System.out.print("\t"+"getBluePaletteLookupTableData: \t");	
				shortArrayAns = myDA.getBluePaletteLookupTableData();
				shortArrayOutput(shortArrayAns);								
			System.out.print("\t"+"getICCProfile: \t");	
				Byte[] byteArrayAns = myDA.getICCProfile();
				byteArrayOutput(byteArrayAns);							
			
		//	System.out.println();			
//			System.out.println("\t"+"getPixelDataProviderURL: \t"+myDA.getPixelDataProviderURL());			

		//	System.out.println();			
//			System.out.println("\t"+"getPixelPaddingRangeLimit: \t"+myDA.getPixelPaddingRangeLimit());
			
			System.out.println();			
			System.out.println("\t"+"getSpacingBetweenSlices: \t"+myDA.getSpacingBetweenSlices());			

	
			System.out.println();			
			System.out.println();			
			System.out.println();			

			
			

  
  			
			System.out.println("\t"+"getContrastOrBolusAgent: \t"+myDA.getContrastOrBolusAgent());			
			System.out.println("\t"+"getContrastOrBolusRoute: \t"+myDA.getContrastOrBolusRoute());			
			System.out.println("\t"+"getContrastOrBolusVolume: \t"+myDA.getContrastOrBolusVolume());			
			System.out.println("\t"+"getContrastOrBolusStartTime: \t"+myDA.getContrastOrBolusStartTime());			
			System.out.println("\t"+"getContrastOrBolusStopTime: \t"+myDA.getContrastOrBolusStopTime());			
			System.out.println("\t"+"getContrastOrBolusTotalDose: \t"+myDA.getContrastOrBolusTotalDose());			
//			System.out.println("\t"+"getContrastOrBolusFlowRate: \t"+myDA.getContrastOrBolusFlowRate());			
//			System.out.println("\t"+"getContrastOrBolusFlowDuration: \t"+myDA.getContrastOrBolusFlowDuration());			
			System.out.println("\t"+"getContrastOrBolusIngredient: \t"+myDA.getContrastOrBolusIngredient());			
			System.out.println("\t"+"getContrastOrBolusIngredientConcentration: \t"+myDA.getContrastOrBolusIngredientConcentration());

//			System.out.println();			
//			System.out.println("\t"+"getDevice: \t"+myDA.getDevice());			

			System.out.println();			
			System.out.print("\t"+"getPixelSpacing: \t");
				doubleArrayAns = myDA.getPixelSpacing();
				doubleArrayOutput(doubleArrayAns);		
			System.out.print("\t"+"getImageOrientationPatient: \t");
				doubleArrayAns = myDA.getImageOrientationPatient();
				doubleArrayOutput(doubleArrayAns);					
			System.out.print("\t"+"getImagePositionPatient: \t");
				doubleArrayAns = myDA.getImagePositionPatient();
				doubleArrayOutput(doubleArrayAns);				
			System.out.println("\t"+"getSliceThickness: \t"+myDA.getSliceThickness());			
			System.out.println("\t"+"getSliceLocation: \t"+myDA.getSliceLocation());		
			
			
			System.out.println();
//			System.out.println("\t"+"getAcquisitionContextSequence: \t"+myDA.getAcquisitionContextSequence());			
			System.out.println("\t"+"getAcquisitionContextDescription: \t"+myDA.getAcquisitionContextDescription());			
			
			System.out.println();
			System.out.println("\t"+"getOverlayRows: \t"+myDA.getOverlayRows());			
			System.out.println("\t"+"getOverlayColumns: \t"+myDA.getOverlayColumns());			
			System.out.println("\t"+"getOverlayType: \t"+myDA.getOverlayType());			
			System.out.print("\t"+"getOverlayOrigin: \t");	
				intArrayAns = myDA.getOverlayOrigin();
				intArrayOutput(intArrayAns);		
			System.out.println("\t\t (array of values - to check)");			
			System.out.println("\t"+"getOverlayBitsAllocated: \t"+myDA.getOverlayBitsAllocated());			
			System.out.println("\t"+"getOverlayBitPosition: \t"+myDA.getOverlayBitPosition());			
			System.out.println("\t"+"getOverlayData: \t"+myDA.getOverlayData());			
			System.out.println("\t"+"getOverlayDescription: \t"+myDA.getOverlayDescription());			
			System.out.println("\t"+"getOverlaySubtype: \t"+myDA.getOverlaySubtype());			
			System.out.println("\t"+"getOverlayLabel: \t"+myDA.getOverlayLabel());			
			System.out.println("\t"+"getROIArea: \t"+myDA.getROIArea());			
			System.out.println("\t"+"getROIMean: \t"+myDA.getROIMean());			
			System.out.println("\t"+"getROIStandardDeviation: \t"+myDA.getROIStandardDeviation());	
			
			System.out.println();
			System.out.println("\t"+"getVOILUT: \t"+myDA.getVOILUT());			
			System.out.print("\t"+"getWindowCenter: \t");
				doubleArrayAns = myDA.getWindowCenter();
				doubleArrayOutput(doubleArrayAns);							
			System.out.print("\t"+"getWindowWidth: \t");
				doubleArrayAns = myDA.getWindowWidth();
				doubleArrayOutput(doubleArrayAns);							
			System.out.println("\t"+"getWindowCenterAndWidthExplanationStrings: \t"+myDA.getWindowCenterAndWidthExplanationStrings());			
			System.out.println("\t"+"getVOILUTFunction: \t"+myDA.getVOILUTFunction());			

//			System.out.println();
//			System.out.println("\t"+"getKVP: \t"+myDA.getKVP());			
//			System.out.println("\t"+"getPlateID: \t"+myDA.getPlateID());			
//			System.out.println("\t"+"getExposureTime: \t"+myDA.getExposureTime());			
//			System.out.println("\t"+"getExposure: \t"+myDA.getExposure());			
//			System.out.println("\t"+"getExposureInMicroAs: \t"+myDA.getExposureInMicroAs());			

			System.out.println();
			System.out.println("\t"+"getModalityLUT: \t"+myDA.getModalityLUT());			
			System.out.println("\t"+"getModalityLUTRescaleIntercept: \t"+myDA.getModalityLUTRescaleIntercept());			
			System.out.println("\t"+"getModalityLUTRescaleSlope: \t"+myDA.getModalityLUTRescaleSlope());			
			System.out.println("\t"+"getModalityLUTRescaleType: \t"+myDA.getModalityLUTRescaleType());	
			
//			System.out.println();
//			System.out.println("\t"+"getDataCollectionDiameter: \t"+myDA.getDataCollectionDiameter());			

//			System.out.println();
//			System.out.println("\t"+"getScanningSequence: \t"+myDA.getScanningSequence());			
//			System.out.println("\t"+"getSequenceVariant: \t"+myDA.getSequenceVariant());			
//			System.out.println("\t"+"getScanOptions: \t"+myDA.getScanOptions());			
//			System.out.println("\t"+"getMRAcquisitionType: \t"+myDA.getMRAcquisitionType());			
//			System.out.println("\t"+"getRepetitionTime: \t"+myDA.getRepetitionTime());			
//			System.out.println("\t"+"getEchoTime: \t"+myDA.getEchoTime());			
//			System.out.println("\t"+"getEchoTrainLength: \t"+myDA.getEchoTrainLength());			
//			System.out.println("\t"+"getInversionTime: \t"+myDA.getInversionTime());			
//			System.out.println("\t"+"getTriggerTime: \t"+myDA.getTriggerTime());			
//			System.out.println("\t"+"getSpacingBetweenSlices: \t"+myDA.getSpacingBetweenSlices());		
			
			System.out.println();
			System.out.println("\t"+"getSOPClassUID: \t"+myDA.getSOPClassUID());			
			System.out.println("\t"+"getSOPInstanceUID: \t"+myDA.getSOPInstanceUID());			
//			System.out.println("\t"+"getSpecificCharacterSet: \t"+myDA.getSpecificCharacterSet());			
//			System.out.println("\t"+"getInstanceCreationDate: \t"+myDA.getInstanceCreationDate());			
//			System.out.println("\t"+"getInstanceCreationTime: \t"+myDA.getInstanceCreationTime());			
//			System.out.println("\t"+"getInstanceCreatorUID: \t"+myDA.getInstanceCreatorUID());			
			System.out.println("\t"+"getTimezoneOffsetFromUTC: \t"+myDA.getTimezoneOffsetFromUTC());			
			System.out.println("\t"+"getInstanceNumber: \t"+myDA.getInstanceNumber());			
			System.out.println("\t"+"getSOPInstanceStatus: \t"+myDA.getSOPInstanceStatus());			
			System.out.println("\t"+"getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ: \t"+myDA.getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ());			
			System.out.println("\t"+"getSOPAuthorizationComment: \t"+myDA.getSOPAuthorizationComment());			
//			System.out.println("\t"+"getEncryptedAttributes: \t"+myDA.getEncryptedAttributes());			
//			System.out.println("\t"+"getHL7StructuredDocument: \t"+myDA.getHL7StructuredDocument());			
			
		

			
			
			
		} 
		catch (FileNotFoundException e) 	{	e.printStackTrace();	}	
		catch (DIException e) 				{	e.printStackTrace();	}
		
		System.out.println();		
		
	}
	

	private void stringArrayOutput(String[] ans){
		if(ans == null)
			System.out.println(ans);
		else
		{
			System.out.println("String Array");
			for(int i=0; i< ans.length; i++)
				System.out.println("\t\t\t\t"+ans[i]);
		}	
	}

	private void intArrayOutput(Integer[] ansInt){
		if(ansInt == null)
			System.out.println(ansInt);
		else if(ansInt.length==0)
			System.out.println("Empty");
		else
		{
			System.out.println("Integer Array");				
			for(int i=0; i< ansInt.length; i++)
				System.out.println("\t\t\t\t"+ansInt[i]);
		}	
	}

	private void doubleArrayOutput(Double[] ansDouble){
		if(ansDouble == null)
			System.out.println(ansDouble);
		else if(ansDouble.length==0)
			System.out.println("Empty");
		else
		{
			System.out.println("Double Array");				
			for(int i=0; i< ansDouble.length; i++)
				System.out.println("\t\t\t\t"+ansDouble[i]);
		}	
	}

	
	private void shortArrayOutput(Short[] ansInt){
		if(ansInt == null)
			System.out.println(ansInt);
		else if(ansInt.length==0)
			System.out.println("Empty");
		else
		{
			System.out.println("Short Array");				
			for(int i=0; i< ansInt.length; i++)
				System.out.println("\t\t\t\t"+ansInt[i]);
		}	
	}

	
	private void byteArrayOutput(Byte[] ansInt){
		if(ansInt == null)
			System.out.println(ansInt);
		else if(ansInt.length==0)
			System.out.println("Empty");
		else
		{
			System.out.println("Byte Array");				
			for(int i=0; i< ansInt.length; i++)
				System.out.println("\t\t\t\t"+ansInt[i]);
		}	
	}
	
}
