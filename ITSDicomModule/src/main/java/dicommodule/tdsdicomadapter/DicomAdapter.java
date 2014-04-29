package dicommodule.tdsdicomadapter;

import java.util.ArrayList;

import dicommodule.processing.DicomDataProcessor;
import dicommodule.processing.DicomLUT;
import dicommodule.processing.ModalityLUT;
import dicommodule.processing.SingleBooleanWithState;
import dicommodule.processing.SingleDoubleWithState;
import dicommodule.processing.SingleIntWithState;
import tdsdicominterface.DValue;
import tdsdicominterface.DicomDataSet;
import tdsdicominterface.DicomItem;
import tdsdicominterface.EncapsulatedData;
import tdsdicominterface.exception.DIException;


public class DicomAdapter implements DicomInterfaceDBSide, DicomInterfaceViewerSide {//extends AbstractDicomAdapter {

    /**
     * return value classes:
     * 	-	asked (gggg,eeee) is not in the DicomDictionary -> throw DIException
     *  -	asked (gggg,eeee) is in the DicomDictionary, but not in the actual DicomDataSet -> return null
     *  -	asked (gggg,eeee) is in both the DicomDictionary, and the actual DicomDataSet, but the value was empty -> return "" for Strings, or return null for other cases
     *  -	asked (gggg,eeee) is in the DicomDictionary, and the actual DicomDataSet (with a proper value) -> return value
     **/
    protected DicomDataSet dicomDataSet;

    public DicomAdapter(DicomDataSet dicomDataSet) {
        this.dicomDataSet = dicomDataSet;
    }

    //----------------------------------------------------------------------------------
    /********************************************************************************************
     *	TDS useCase/work/job
     ********************************************************************************************/
    //@Override
    public String getUniqueId() {
        return dicomDataSet.getSopInstanceUID();
    }

    /********************************************************************************************
     *	Case/work/job
     ********************************************************************************************/
    @Override
    public String getAccessionNumber() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,0050) Accession Number SH 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???AccessionNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0050");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getAdmissionID() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0038,0010) Admission ID LO 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???AdmissionID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0038,0010");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getIssuerOfAdmissionID() throws DIException{	}
    @Override
    public String getServiceEpisodeID() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0038,0060) Service Episode ID LO 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???ServiceEpisodeID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0038,0060");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getIssuerOfServiceEpisodeID() throws DIException{	}
    @Override
    public String getServiceEpisodeDescription() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0038,0062) Service Episode Description LO 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???ServiceEpisodeDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0038,0062");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getClinicalTrialTimePointID() throws DIException {
        // source DICOM object:			-	Clinical Trial Study
        // DICOM name and code:			-	(0012,0050) Clinical Trial Time Point ID LO 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???ServiceEpisodeDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0012,0050");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getClinicalTrialTimePointDescription() throws DIException {
        // source DICOM object:			-	Clinical Trial Study
        // DICOM name and code:			-	(0012,0051) Clinical Trial Time Point Description ST 1
        // destination TDS object:		-	???Case/Study
        // destination TDS attribute:	-	???ServiceEpisodeDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0012,0051");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    /********************************************************************************************
     *	DicomPatientData
     ********************************************************************************************/
    @Override
    public String getDICOMPatientName() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0010) Patient�s Name PN 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	DICOMPatientName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0010");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getDICOMPatientID() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0020) Patient ID LO 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	DICOMPatientID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0020");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getIssuerOfPatientID() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0021) Issuer of Patient ID LO 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	IssuerOfPatientID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0021");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getTypeOfPatientID() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0022) Type of Patient ID CS 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	TypeOfPatientID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0022");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);	//TODO conversion facility
    }

    @Override
    public String getPatientDoBYYYYMMDD() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0030) Patient's Birth Date DA 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	DateOfBirth
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0030");
        if (dataElement == null) {
            return null;
        }
        return getDateAnswerAsYYYYMMDDString(dataElement);
    }

    @Override
    public String getPatientSex() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,0040) Patient's Sex CS 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	PatientSex
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,0040");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);	//TODO conversion facility
    }

    @Override
    public String getOtherPatientIDs() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,1000) Other Patient IDs LO 1-n
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	OtherPatientIDs ???
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1000");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement); //TODO multiplicity at Viewer
    }

    @Override
    public String[] getOtherPatientIDRecords() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,1002) Other Patient IDs Sequence SQ 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	???
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1002");
        if (dataElement == null) {
            return null;
        }
//		(0010,0020) Patient ID LO 1
//		(0010,0021) Issuer of Patient ID LO 1
//		(0010,0022) Type of Patient ID CS 1

        //TODO check
        ArrayList<DicomItem> elementSequenceArrayList = getSequence(dataElement);
        String[] patientRecords = new String[elementSequenceArrayList.size()];
        DValue ans1, ans2, ans3;
        for (int i = 0; i < patientRecords.length; i++) {
            ans1 = elementSequenceArrayList.get(i).getValueByGroupElementString("0010,0020");
            ans2 = elementSequenceArrayList.get(i).getValueByGroupElementString("0010,0021");
            ans3 = elementSequenceArrayList.get(i).getValueByGroupElementString("0010,0022");

            patientRecords[i] = getStringAnswer(ans1) + "|" + getStringAnswer(ans2) + "|" + getStringAnswer(ans3);
        }
        return patientRecords;
    }

    @Override
    public String getOtherDICOMPatientNames() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,1001) Other Patient Names PN 1-n
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	OtherDICOMPatientNames
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1001");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement); //TODO multiplicity at Viewer
    }

    @Override
    public String getPatientComments() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,4000) Patient Comments LT 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	Comments
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,4000");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);	//TODO conversion facility
    }

    @Override
    public String getPatientsMothersBirthName() throws DIException {
        // source DICOM object:			-	Patient
        // DICOM name and code:			-	(0010,1060) Patient's Mother's Birth Name PN 1
        // destination TDS object:		-	DicomPatientData
        // destination TDS attribute:	-	MothersBirthName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1060");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    /********************************************************************************************
     *	Study
     ********************************************************************************************/
    @Override
    public String getStudyInstanceUID() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0020,000D) Study Instance UID UI 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	StudyInstanceUID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,000D");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getStudyDateYYYYMMDD() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,0020) Study Date DA 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	StudyDate
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0020");
        if (dataElement == null) {
            return null;
        }
        return getDateAnswerAsYYYYMMDDString(dataElement);
    }

    @Override
    public String getStudyTimeHHMMSS() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,0030) Study Time TM 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	StudyTime
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0030");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    @Override
    public String getReferringPhysyciansDICOMName() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,0090) Referring Physician�s Name PN 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	ReferringPhysyciansName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0090");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getReferringPhysycianIdentification() throws DIException;
    @Override
    public String getStudyID() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0020,0010) Study ID SH 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	StudyID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0010");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getStudyDescription() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,1030) Study Description LO 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	StudyDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1030");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getPhysiciansOfRecordDICOMNames() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,1048) Physician(s) of Record PN 1-n
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	PhysiciansOfRecord
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1048");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getPhysiciansReadingStudyDICOMNames() throws DIException {
        // source DICOM object:			-	General Study
        // DICOM name and code:			-	(0008,1060) Name of Physician(s) Reading Study PN 1-n
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	PhysiciansReadingStudy
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1060");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getAdmittingDiagnoseDescription() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0008,1080) Admitting Diagnoses Description LO 1-n
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	AdmittingDiagnoseDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1080");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    /*	@Override
    public String getAdmittingDiagnoseCodeSequence() throws DIException{
    // source DICOM object:			-	Patient Study
    // DICOM name and code:			-	(0008,1084) Admitting Diagnoses Code Sequence SQ 1
    // destination TDS object:		-	Study
    // destination TDS attribute:	-	AdmittingDiagnoseCodeSequence
    DValue dataElement =  dicomDataSet.getValueByGroupElementString("0008,1084");
    if (dataElement == null) return null;
    return
    }*/
    @Override
    public SingleDoubleWithState getPatientSize() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0010,1020) Patient's Size DS 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	PatientSize
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1020");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public SingleDoubleWithState getPatientsWeight() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0010,1030) Patient's Weight DS 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	PatientsWeight
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,1030");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public String getPatientOccupation() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0010,2180) Occupation SH 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	PatientOccupation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,2180");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getAdditionalPatientHistory() throws DIException {
        // source DICOM object:			-	Patient Study
        // DICOM name and code:			-	(0010,21B0) Additional Patient History LT 1
        // destination TDS object:		-	Study
        // destination TDS attribute:	-	AdditionalPatientHistory
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,21B0");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getPatientMedicalAlerts() throws DIException {
        // source DICOM object:			-	...	 TODO check hierarchy level
        // DICOM name and code:			-	(0010,2000) Medical Alerts LO 1-n
        // destination TDS object:		- ??? TODO check
        // destination TDS attribute:	- ??? TODO check
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,2000");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getSmokingStatus() throws DIException {
        // source DICOM object:			-	Patient Medical Module  TODO check hierarchy level
        // DICOM name and code:			-	(0010,21A0) Smoking Status CS 1
        // destination TDS object:		- ??? TODO check
        // destination TDS attribute:	- ??? TODO check
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0010,21A0");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    /********************************************************************************************
     *	Series
     ********************************************************************************************/
    @Override
    public String getModality() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,0060) Modality CS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	Modality
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0060");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);		//TODO decoding facility in DB???
    }

    @Override
    public String getSeriesInstanceUID() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0020,000E) Series Instance UID UI 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SeriesInstanceUID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,000E");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getSeriesNumber() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0020,0011) Series Number IS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SeriesNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0011");
        if (dataElement == null) {
            return null;
        }
        return getIntegerAnswerFromDicomIS(dataElement);
    }

    @Override
    public String getLaterality() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0020,0060) Laterality CS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	Laterality
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0060");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);	//TODO decoding facility in Viewer???
    }

    @Override
    public String getSeriesDateYYYYMMDD() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,0021) Series Date DA 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SeriesDateYYYYMMDD
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0021");
        if (dataElement == null) {
            return null;
        }
        return getDateAnswerAsYYYYMMDDString(dataElement);
    }

    @Override
    public String getSeriesTimeHHMMSS() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,0031) Series Time TM 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SeriesTimeHHMMSS
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0031");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    @Override
    public String getPerformingPhysiciansDICOMName() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,1050) Performing Physician�s Name PN 1-n
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	PerformingPhysiciansName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1050");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getProtocolName() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0018,1030) Protocol Name LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	ProtocolName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1030");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getSeriesDescription() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,103E) Series Description LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SeriesDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,103E");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getOperatorsDICOMName() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0008,1070) Operators� Name PN 1-n
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	OperatorsName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1070");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getReferencedPerformedProcedureStepSequence() throws DIException;
    @Override
    public String getBodyPartExamined() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0018,0015) Body Part Examined CS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	BodyPartExamined
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,0015");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);		//TODO decoding facility in DB???
    }

    @Override
    public String getPatientPosition() throws DIException {  //TODO check Patient Orientation Code Sequnce
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0018,5100) Patient Position CS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	PatientPosition
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,5100");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);		//TODO decoding facility in Viewer?
    }

    @Override
    public SingleIntWithState getSmallestPixelValueInSeries() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0028,0108) Smallest Pixel Value in Series US or SS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	SmallestPixelValueInSeries
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0108");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement); //TODO match this with Pixel Data!!!
    }

    @Override
    public SingleIntWithState getLargestPixelValueInSeries() throws DIException {
        // source DICOM object:			-	General Series Module
        // DICOM name and code:			-	(0028,0109) Largest Pixel Value in Series US or SS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	LargestPixelValueInSeries
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0109");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement); //TODO match this with Pixel Data!!!
    }

    /*
    @Override
    public String getClinicalTrialCoordinatingCenterName() throws DIException{
    // source DICOM object:			-	Clinical Trial Series Module
    // DICOM name and code:			-	(0012,0060) Clinical Trial Coordinating Center Name LO 1
    // destination TDS object:		-	Series
    // destination TDS attribute:	-	ClinicalTrialCoordinatingCenterName
    DValue dataElement =  dicomDataSet.getValueByGroupElementString("0012,0060");
    if (dataElement == null) return null;
    return
    }*/
    @Override
    public String getClinicalTrialSeriesID() throws DIException {
        // source DICOM object:			-	Clinical Trial Series Module
        // DICOM name and code:			-	(0012,0071) Clinical Trial Series ID LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	ClinicalTrialSeriesID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0012,0071");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getClinicalTrialSeriesDescription() throws DIException {
        // source DICOM object:			-	Clinical Trial Series Module
        // DICOM name and code:			-	(0012,0072) Clinical Trial Series Description LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	ClinicalTrialSeriesDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0012,0072");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getViewPosition() throws DIException {	//for CR series only
        // source DICOM object:			-	CR Series Module
        // DICOM name and code:			-	(0018,5101) View Position CS 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	ViewPosition
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,5101");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);		//TODO decoding facility in Viewer?
    }

//	public String getFilterType() throws DIException;	//for CR series only
//	public String getCollimatorOrGridNAme() throws DIException;	//for CR series only
//	public String getFocalSpot() throws DIException;	//for CR series only
//	public String getPlateType() throws DIException;	//for CR series only
//	public String getPhosphorType() throws DIException;	//for CR series only
    @Override
    public String getFrameOfReferenceUID() throws DIException {
        // source DICOM object:			-	Frame Of Reference Module
        // DICOM name and code:			-	(0020,0052) Frame of Reference UID UI 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	FrameOfReferenceUID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0052");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getPositionReferenceIndicator() throws DIException {
        // source DICOM object:			-	Frame Of Reference Module
        // DICOM name and code:			-	(0020,1040) Position Reference Indicator LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	PositionReferenceIndicator
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,1040");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentManufacturer() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0008,0070) Manufacturer LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentManufacturer
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0070");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentLocationInstitutionName() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0008,0080) Institution Name LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentLocationInstitutionName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0080");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentStationName() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0008,1010) Station Name SH 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentStationName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1010");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentInstitutionalDepartmentName() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0008,1040) Institutional Department Name LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentInstitutionalDepartmentName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1040");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentManufacturersModelName() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0008,1090) Manufacturer�s Model Name LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentManufacturersModelName
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,1090");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getEquipmentDeviceSerialNumber() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0018,1000) Device Serial Number LO 1
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentDeviceSerialNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1000");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getEquipmentSoftwareVersions() throws DIException;
//	public String getEquipmentSpatialResolution() throws DIException;
    @Override
    public String getEquipmentDateOfLastCalibrationYYYYMMDD() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0018,1200) Date of Last Calibration DA 1-n
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentDateOfLastCalibration
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1200");
        if (dataElement == null) {
            return null;
        }
        return getDateAnswerAsYYYYMMDDString(dataElement);
    }

    @Override
    public String getEquipmentTimeOfLastCalibrationHHMMSS() throws DIException {
        // source DICOM object:			-	General Equipment Module
        // DICOM name and code:			-	(0018,1201) Time of Last Calibration TM 1-n
        // destination TDS object:		-	Series
        // destination TDS attribute:	-	EquipmentTimeOfLastCalibration
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1201");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    /********************************************************************************************
     *	Image
     ********************************************************************************************/
    @Override
    public SingleIntWithState getImageInstanceNumber() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0020,0013) Instance Number IS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImageInstanceNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0013");
        if (dataElement == null) {
            return null;
        }
        return getIntegerAnswerFromDicomIS(dataElement);
    }

    @Override
    public String getPatientOrientation() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0020,0020) Patient Orientation CS 2
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PatientOrientation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0020");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement); //TODO multiple values and decoding in Viewer???
    }

    @Override
    public String getContentDateYYYYMMDD() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0008,0023) Content Date DA 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContentDate
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0023");
        if (dataElement == null) {
            return null;
        }
        return getDateAnswerAsYYYYMMDDString(dataElement);
    }

    @Override
    public String getContentTimeHHMMSS() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0008,0033) Content Time TM 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContentTime
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0033");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    @Override
    public String getImageType() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0008,0008) Image Type CS 2-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImageType
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0008");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);	//TODO multiple values and decoding
    }

    @Override
    public SingleIntWithState getAcquisitionNumber() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0020,0012) Acquisition Number IS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	AcquisitionNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0012");
        if (dataElement == null) {
            return null;
        }
        return getIntegerAnswerFromDicomIS(dataElement);
    }

    @Override
    public String getAcquisitionDateYYYYMMDD() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0008,0022) Acquisition Date DA 1
        //								-	(0008,002A) Acquisition DateTime DT 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	AcquisitionDateYYYYMMDD
        DValue dataElementDate = dicomDataSet.getValueByGroupElementString("0008,0022");
        DValue dataElementDateTime = dicomDataSet.getValueByGroupElementString("0008,002A");
//		if (dataElementDate == null && dataElementDateTime == null) return null;
        if (dataElementDate != null) {
            return getDateAnswerAsYYYYMMDDString(dataElementDate);
        }
        if (dataElementDateTime != null) {
            return DicomDataProcessor.getDateAsYYYYMMDDfromYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ(getDateTimeAnswerAsYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString(dataElementDateTime));
        }
        return null;
    }

    @Override
    public String getAcquisitionTimeHHMMSS() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0008,0032) Acquisition Time TM 1
        //								-	(0008,002A) Acquisition DateTime DT 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	AcquisitionTimeHHMMSS
        DValue dataElementTime = dicomDataSet.getValueByGroupElementString("0008,0032");
        DValue dataElementDateTime = dicomDataSet.getValueByGroupElementString("0008,002A");
        if (dataElementTime == null && dataElementDateTime == null) {
            return null;
        }
        if (dataElementTime != null) {
            return getDateAnswerAsYYYYMMDDString(dataElementTime);
        }
        if (dataElementDateTime != null) {
            return DicomDataProcessor.getTimeAsHHMMSSFFFFFFfromYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ(getDateTimeAnswerAsYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString(dataElementDateTime));
        }
        return null;
    }

    @Override
    public SingleIntWithState getImagesInAcquisition() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0020,1002) Images in Acquisition IS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImagesInAcquisition
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,1002");
        if (dataElement == null) {
            return null;
        }
        return getIntegerAnswerFromDicomIS(dataElement);
    }

    @Override
    public String getImageComments() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0020,4000) Image Comments LT 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImageComments
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,4000");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleBooleanWithState getIsQualityControlImage() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0028,0300) Quality Control Image CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	IsQualityControlImage
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0300");
        if (dataElement == null) {
            return null;
        }
        String code = getStringAnswer(dataElement);
        if (code.isEmpty()) {
            return SingleBooleanWithState.createEmpty();
        }
        if (code == "YES") {
            return new SingleBooleanWithState(true);
        }
        if (code == "NO") {
            return new SingleBooleanWithState(false);
        }
        return null;
    }

    @Override
    public SingleBooleanWithState getHasBurnedInAnnotation() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0028,0301) Burned In Annotation CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	HasBurnedInAnnotation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0301");
        if (dataElement == null) {
            return null;
        }
        String code = getStringAnswer(dataElement);
        if (code.isEmpty()) {
            return SingleBooleanWithState.createEmpty();
        }
        if (code == "YES") {
            return new SingleBooleanWithState(true);
        }
        if (code == "NO") {
            return new SingleBooleanWithState(false);
        }
        return null;
    }

    @Override
    public SingleBooleanWithState getHadLossyImageCompression() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0028,2110) Lossy Image Compression CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	HadLossyImageCompression
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,2110");
        if (dataElement == null) {
            return null;
        }
        String code = getStringAnswer(dataElement);
        if (code.isEmpty()) {
            return SingleBooleanWithState.createEmpty();
        }
        if (code == "01") {
            return new SingleBooleanWithState(true);
        }
        if (code == "00") {
            return new SingleBooleanWithState(false);
        }
        return null;
    }

    @Override
    public Double[] getLossyImageCompressionRatio() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0028,2112) Lossy Image Compression Ratio DS 1-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	LossyImageCompressionRatio
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,2112");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public String getLossyImageCompressionMethod() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(0028,2114) Lossy Image Compression Method CS 1-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	LossyImageCompressionMethod
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,2114");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement); //  TODO multiplicity //TODO check values(enum) getStringAnswer(dataElement)
    }

//	public String getIconImage() throws DIException;
    @Override
    public DicomLUT getPresentationLUT() throws DIException {
        // source DICOM object:			-	Presentation LUT Module
        // DICOM name and code:			-	(2050,0010) Presentation LUT Sequence SQ 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PresentationLUT
        DValue dataElement = dicomDataSet.getValueByGroupElementString("2050,0010");
        if (dataElement == null) {
            return null;
        }

        ArrayList<DicomItem> elementSequenceArrayList = getSequence(dataElement);
        return getLUTAnswerFromSequenceElement(elementSequenceArrayList.get(0));
    }

    @Override
    public String getPresentationLUTShape() throws DIException {
        // source DICOM object:			-	General Image Module
        // DICOM name and code:			-	(2050,0020) Presentation LUT Shape CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PresentationLUTShape
        DValue dataElement = dicomDataSet.getValueByGroupElementString("2050,0020");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);  //{"IDENTITY","INVERSE"}
    }

    @Override
    public SingleIntWithState getSamplesPerPixel() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0002) Samples per Pixel US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SamplesPerPixel
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0002");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);

        // TODO check: (0028,0003) Samples per Pixel Used US 1
    }

    @Override
    public String getPhotometricInterpretation() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0004) Photometric Interpretation CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PhotometricInterpretation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0004");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement); 	// {"MONOCHROME1","MONOCHROME2","PALETTE COLOR","RGB","YBR_FULL",
        //  "YBR_FULL_422","YBR_PARTIAL_422","YBR_PARTIAL_420","YBR_ICT","YBR_RCT"}
    }

    @Override
    public SingleIntWithState getRows() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0010) Rows US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	Rows
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0010");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getColumns() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0011) Columns US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	Columns
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0011");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getBitsAllocated() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0100) Bits Allocated US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	BitsAllocated
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0100");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getBitsStored() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0101) Bits Stored US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	BitsStored
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0101");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getHighBit() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0102) High Bit US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	HighBit
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0102");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getPixelRepresentation() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0103) Pixel Representation US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PixelRepresentation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0103");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);		//can be 0000H = unsigned integer.
        //	or	 0001H = 2's complement
    }

    @Override
    public Object getPixelData() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(7FE0,0010) Pixel Data OW or OB 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PixelData
        DValue dataElement = dicomDataSet.getValueByGroupElementString("7FE0,0010");
        if (dataElement == null) {
            return null;
        }
        if (dataElement.getType() == DValue.SHORTARRAY) {
        	System.out.println("class = "+dataElement.getValue().getClass().getCanonicalName());
            return (short[]) dataElement.getValue();
        } else if (dataElement.getType() == DValue.BYTEARRAY) {
            return (byte[]) dataElement.getValue();
        } else if (dataElement.getType() == DValue.ENCAPSULATEDDATA) {
            return (EncapsulatedData) dataElement.getValue(); //TODO pixels
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.SHORTARRAY + " or " + DValue.BYTEARRAY);
        }

    }

    @Override
    public SingleIntWithState getPlanarConfiguration() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0006) Planar Configuration US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PlanarConfiguration
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0006");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
        /*
        Indicates whether the pixel data are sent
        color-by-plane or color-by-pixel. Required if
        Samples per Pixel (0028,0002) has a value
        greater than 1. See C.7.6.3.1.3 for further
        explanation.
         */
    }

    @Override
    public Integer[] getPixelAspectRatio() throws DIException {		//TODO  take car of this in Viewer!!!!
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0034) Pixel Aspect Ratio IS 2
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PixelAspectRatio
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0034");
        if (dataElement == null) {
            return null;
        }
        String[] integerStringArray = getMultipleStringsAnswer(dataElement);
        if (integerStringArray.length != 2) {
            throw new DIException("PixelAspectRatio length is not 2! ");
        }
        Integer[] ans = new Integer[integerStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Integer.parseInt(integerStringArray[i]);
        }
        return ans;
    }

    @Override
    public SingleIntWithState getSmallestImagePixelValue() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0106) Smallest Image Pixel Value US or SS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SmallestImagePixelValue
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0106");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getLargestImagePixelValue() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,0107) Largest Image Pixel Value US or SS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	LargestImagePixelValue
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,0107");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public Integer[] getRedPaletteLookupTableDescriptor() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1101) Red Palette Color Lookup Table Descriptor US or SS 3
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	RedPaletteLookupTableDescriptor
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1101");
        if (dataElement == null) {
            return null;
        }
        Integer[] ans = getMultipleIntegerAnswer(dataElement);
        if (ans.length != 3) {
            throw new DIException("Length of int array is " + ans.length + " instead of 3");
        }
        return ans;
    }

    @Override
    public Integer[] getGreenPaletteLookupTableDescriptor() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1102) Green Palette Color Lookup Table Descriptor US or SS 3
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	GreenPaletteLookupTableDescriptor
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1102");
        if (dataElement == null) {
            return null;
        }
        Integer[] ans = getMultipleIntegerAnswer(dataElement);
        if (ans.length != 3) {
            throw new DIException("Length of int array is " + ans.length + " instead of 3");
        }
        return ans;
    }

    @Override
    public Integer[] getBluePaletteLookupTableDescriptor() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1103) Blue Palette Color Lookup Table Descriptor US or SS 3
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	BluePaletteLookupTableDescriptor
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1103");
        if (dataElement == null) {
            return null;
        }
        Integer[] ans = getMultipleIntegerAnswer(dataElement);
        if (ans.length != 3) {
            throw new DIException("Length of int array is " + ans.length + " instead of 3");
        }
        return ans;
    }

    @Override
    public Short[] getRedPaletteLookupTableData() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1201) Red Palette Color Lookup Table Data OW 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	RedPaletteLookupTableData
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1201");
        if (dataElement == null) {
            return null;
        }
        return getDicomOWAnswer(dataElement);
    }

    @Override
    public Short[] getGreenPaletteLookupTableData() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1202) Green Palette Color Lookup Table Data OW 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	GreenPaletteLookupTableData
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1202");
        if (dataElement == null) {
            return null;
        }
        return getDicomOWAnswer(dataElement);
    }

    @Override
    public Short[] getBluePaletteLookupTableData() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,1203) Blue Palette Color Lookup Table Data OW 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	BluePaletteLookupTableData
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1203");
        if (dataElement == null) {
            return null;
        }
        return getDicomOWAnswer(dataElement);
    }

    @Override
    public Byte[] getICCProfile() throws DIException {
        // source DICOM object:			-	Image Pixel Module - Image Pixel Macro
        // DICOM name and code:			-	(0028,2000) ICC Profile OB 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ICCProfile
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,2000");
        if (dataElement == null) {
            return null;
        }
        return getDicomOBAnswer(dataElement);
    }

//	public String getPixelDataProviderURL() throws DIException;
//	public String getPixelPaddingRangeLimit() throws DIException;
    @Override
    public SingleDoubleWithState getSpacingBetweenSlices() throws DIException {
        // source DICOM object:			-	MR Image Module
        // DICOM name and code:			-	(0018,0088) Spacing Between Slices DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SpacingBetweenSlices
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,0088");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public String getContrastOrBolusAgent() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,0010) Contrast/Bolus Agent LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusAgent
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,0010");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getContrastOrBolusRoute() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1040) Contrast/Bolus Route LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusRoute
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1040");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleDoubleWithState getContrastOrBolusVolume() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1041) Contrast/Bolus Volume DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusVolume
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1041");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public String getContrastOrBolusStartTime() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1042) Contrast/Bolus Start Time TM 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusStartTime
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1042");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    @Override
    public String getContrastOrBolusStopTime() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1043) Contrast/Bolus Stop Time TM 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusStopTime
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1043");
        if (dataElement == null) {
            return null;
        }
        return getTimeAnswerAsHHMMSSFFFFFFString(dataElement);
    }

    @Override
    public SingleDoubleWithState getContrastOrBolusTotalDose() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1044) Contrast/Bolus Total Dose DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusTotalDose
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1044");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public String getContrastOrBolusIngredient() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1048) Contrast/Bolus Ingredient CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusIngredient
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1048");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleDoubleWithState getContrastOrBolusIngredientConcentration() throws DIException {
        // source DICOM object:			-	Contrast/Bolus Module
        // DICOM name and code:			-	(0018,1049) Contrast/Bolus Ingredient Concentration DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ContrastOrBolusIngredientConcentration
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1049");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

//	public String getDevice() throws DIException;
    @Override
    public Double[] getPixelSpacing() throws DIException {
        // source DICOM object:			-	Image Plane Module
        // DICOM name and code:			-	(0018,1164) Imager Pixel Spacing DS 2
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	PixelSpacing
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,1164");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        if (decimalStringArray.length != 2) {
            throw new DIException("PixelSpacing length is not 2! ");
        }
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public Double[] getImageOrientationPatient() throws DIException {
        // source DICOM object:			-	Image Plane Module
        // DICOM name and code:			-	(0020,0037) Image Orientation (Patient) DS 6
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImageOrientationPatient
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0037");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        if (decimalStringArray.length != 6) {
            throw new DIException("ImageOrientationPatient length is not 6! ");
        }
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public Double[] getImagePositionPatient() throws DIException {
        // source DICOM object:			-	Image Plane Module
        // DICOM name and code:			-	(0020,0032) Image Position (Patient) DS 3
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ImagePositionPatient
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0032");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        if (decimalStringArray.length != 3) {
            throw new DIException("ImagePositionPatient length is not 3! ");
        }
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public SingleDoubleWithState getSliceThickness() throws DIException {
        // source DICOM object:			-	Image Plane Module
        // DICOM name and code:			-	(0018,0050) Slice Thickness DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SliceThickness
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0018,0050");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public SingleDoubleWithState getSliceLocation() throws DIException {		//TODO check what this is
        // source DICOM object:			-	Image Plane Module
        // DICOM name and code:			-	(0020,1041) Slice Location DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SliceLocation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,1041");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
        /*
        Relative position of the image plane expressed in mm.
         */
    }

//	public String getModalityLUT() throws DIException;
//	public String getRescaleIntercept() throws DIException;
//	public String getRescaleSlope() throws DIException;
//	public String getRescaleType() throws DIException;
    /*	@Override
    public String getAcquisitionContextSequence() throws DIException{
    // source DICOM object:			-	Acquisition Context Module
    // DICOM name and code:			-	(0040,0555) Acquisition Context Sequence SQ 1
    // destination TDS object:		-	Image
    // destination TDS attribute:	-	AcquisitionContextSequence
    DValue dataElement =  dicomDataSet.getValueByGroupElementString("0040,0555");
    if (dataElement == null) return null;
    return
    }*/
    @Override
    public String getAcquisitionContextDescription() throws DIException {
        // source DICOM object:			-	Acquisition Context Module
        // DICOM name and code:			-	(0040,0556) Acquisition Context Description ST 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	AcquisitionContextDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0040,0556");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getOverlayRows() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0010) Overlay Rows US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayRows
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0010");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getOverlayColumns() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0011) Overlay Columns US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayColumns
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0011");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public String getOverlayType() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0040) Overlay Type CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayType
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0040");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public Integer[] getOverlayOrigin() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0050) Overlay Origin SS 2
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayOrigin
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0050");
        if (dataElement == null) {
            return null;
        }
        Integer[] ans = getMultipleIntegerAnswer(dataElement);
        if (ans.length != 2) {
            throw new DIException("Length of int array is " + ans.length + " instead of 3");
        }
        return ans;
    }

    @Override
    public SingleIntWithState getOverlayBitsAllocated() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0100) Overlay Bits Allocated US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayBitsAllocated
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0100");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getOverlayBitPosition() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0102) Overlay Bit Position US 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayBitPosition
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0102");
        if (dataElement == null) {
            return null;
        }
        return getSingleIntegerAnswer(dataElement);
    }

    @Override
    public Object getOverlayData() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,3000) Overlay Data OB or OW 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayData
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,3000");
        if (dataElement == null) {
            return null;
        }

        if (dataElement.getType() == DValue.SHORTARRAY) {
            return (Short[]) dataElement.getValue();
        } else if (dataElement.getType() == DValue.BYTEARRAY) {
            return (Byte[]) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.SHORTARRAY + " or " + DValue.BYTEARRAY);
        }

    }

    @Override
    public String getOverlayDescription() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0022) Overlay Description LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayDescription
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0022");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getOverlaySubtype() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,0045) Overlay Subtype LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlaySubtype
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,0045");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getOverlayLabel() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,1500) Overlay Label LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	OverlayLabel
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,1500");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public SingleIntWithState getROIArea() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,1301) ROI Area IS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ROIArea
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,1301");
        if (dataElement == null) {
            return null;
        }
        return getIntegerAnswerFromDicomIS(dataElement);
    }

    @Override
    public SingleDoubleWithState getROIMean() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,1302) ROI Mean DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ROIMean
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,1302");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public SingleDoubleWithState getROIStandardDeviation() throws DIException {
        // source DICOM object:			-	Overlay plane module
        // DICOM name and code:			-	(60xx,1303) ROI Standard Deviation DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ROITandardDeviation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("6099,1303");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public DicomLUT[] getVOILUT() throws DIException {
        // source DICOM object:			-	VOI LUT Module - VOI LUT Macro
        // DICOM name and code:			-	(0028,3010) VOI LUT Sequence SQ 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	VOILUT
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,3010");
        if (dataElement == null) {
            return null;
        }

        ArrayList<DicomItem> elementSequenceArrayList = getSequence(dataElement);
        int numLUTs = elementSequenceArrayList.size();
        DicomLUT[] ans = new DicomLUT[numLUTs]; 
        for(int i=0; i<numLUTs; i++)
        	ans[i] = getLUTAnswerFromSequenceElement(elementSequenceArrayList.get(i)); 
        return ans;
    }

    @Override
    public Double[] getWindowCenter() throws DIException {
        // source DICOM object:			-	VOI LUT Module - VOI LUT Macro
        // DICOM name and code:			-	(0028,1050) Window Center DS 1-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	WindowCenter
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1050");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public Double[] getWindowWidth() throws DIException {
        // source DICOM object:			-	VOI LUT Module - VOI LUT Macro
        // DICOM name and code:			-	(0028,1051) Window Width DS 1-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	WindowWidth
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1051");
        if (dataElement == null) {
            return null;
        }

        String[] decimalStringArray = getMultipleStringsAnswer(dataElement);
        Double[] ans = new Double[decimalStringArray.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(decimalStringArray[i]);
        }
        return ans;
    }

    @Override
    public String getWindowCenterAndWidthExplanationStrings() throws DIException {
        // source DICOM object:			-	VOI LUT Module - VOI LUT Macro
        // DICOM name and code:			-	(0028,1055) Window Center & Width Explanation LO 1-n
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	WindowCenterAndWidthExplanation
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1055");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    @Override
    public String getVOILUTFunction() throws DIException {
        // source DICOM object:			-	VOI LUT Module - VOI LUT Macro
        // DICOM name and code:			-	(0028,1056) VOI LUT Function CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	VOILUTFunction
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1056");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getKVP() throws DIException;	//CR image only	
//	public String getPlateID() throws DIException;	//CR image only
//	public String getExposureTime() throws DIException;	//CR image only
//	public String getExposure() throws DIException;	//CR image only
//	public String getExposureInMicroAs() throws DIException;	//CR image only
    @Override
    public ModalityLUT getModalityLUT() throws DIException {
        // source DICOM object:			-	Modality LUT Module
        // DICOM name and code:			-	(0028,3000) Modality LUT Sequence SQ 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ModalityLUT
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,3000");
        if (dataElement == null) {
            return null;
        }

//		(0028,3004) Modality LUT Type LO 1

        ArrayList<DicomItem> elementSequenceArrayList = getSequence(dataElement);
        DicomLUT basicLUTData = getLUTAnswerFromSequenceElement(elementSequenceArrayList.get(0));

        DValue modalityLUTTypeElement = elementSequenceArrayList.get(0).getValueByGroupElementString("0028,3004");
        String modalityLUTType;

        if (modalityLUTTypeElement == null) {
            modalityLUTType = null;
        } else {
            modalityLUTType = getStringAnswer(modalityLUTTypeElement);
        }

        if (basicLUTData.getLUTDataJavaType() == DicomLUT.INTARRAYTYPELUTDATA) {
            return new ModalityLUT(basicLUTData.getLUTDescriptor(), basicLUTData.getLUTExplanation(), modalityLUTType, (Integer[]) basicLUTData.getLUTData());
        } else {
            return new ModalityLUT(basicLUTData.getLUTDescriptor(), basicLUTData.getLUTExplanation(), modalityLUTType, (Short[]) basicLUTData.getLUTData());
        }
    }

    @Override
    public SingleDoubleWithState getModalityLUTRescaleIntercept() throws DIException {
        // source DICOM object:			-	Modality LUT Module
        // DICOM name and code:			-	(0028,1052) Rescale Intercept DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ModalityLUTRescaleIntercept
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1052");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public SingleDoubleWithState getModalityLUTRescaleSlope() throws DIException {
        // source DICOM object:			-	Modality LUT Module
        // DICOM name and code:			-	(0028,1053) Rescale Slope DS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ModalityLUTRescaleSlope
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1053");
        if (dataElement == null) {
            return null;
        }
        return getDoubleAnswerFromDicomDS(dataElement);
    }

    @Override
    public String getModalityLUTRescaleType() throws DIException {
        // source DICOM object:			-	Modality LUT Module
        // DICOM name and code:			-	(0028,1054) Rescale Type LO 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	ModalityLUTRescaleType
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0028,1054");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

//	public String getDataCollectionDiameter() throws DIException;
//	public String getScanningSequence() throws DIException;
//	public String getSequenceVariant() throws DIException;
//	public String getScanOptions() throws DIException;
//	public String getMRAcquisitionType() throws DIException;
//	public String getRepetitionTime() throws DIException;
//	public String getEchoTime() throws DIException;
//	public String getEchoTrainLength() throws DIException;
//	public String getInversionTime() throws DIException;
//	public String getTriggerTime() throws DIException;
//	public String getSpacingBetweenSlices() throws DIException;
    //SOP (???)
    public String getSOPClassUID() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0008,0016) SOP Class UID UI 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SOPClassUID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0016");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    public String getSOPInstanceUID() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0008,0018) SOP Instance UID UI 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SOPInstanceUID
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0018");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    //	public abstract String getSpecificCharacterSet() throws DIException;
    //	public abstract String getInstanceCreationDate() throws DIException;
    //	public abstract String getInstanceCreationTime() throws DIException;
    //	public abstract String getInstanceCreatorUID() throws DIException;
    public String getTimezoneOffsetFromUTC() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0008,0201) Timezone Offset From UTC SH 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	TimezoneOffsetFromUTC
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0008,0201");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    public String getInstanceNumber() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0020,0013) Instance Number IS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	InstanceNumber
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0020,0013");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    public String getSOPInstanceStatus() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0100,0410) SOP Instance Status CS 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SOPInstanceStatus
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0100,0410");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    public String getSOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0100,0420) SOP Authorization Date and Time DT 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SOPAuthorizationDateTimeYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZ
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0100,0420");
        if (dataElement == null) {
            return null;
        }
        return getDateTimeAnswerAsYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString(dataElement);
    }

    public String getSOPAuthorizationComment() throws DIException {
        // source DICOM object:			-	SOP Common Module
        // DICOM name and code:			-	(0100,0424) SOP Authorization Comment LT 1
        // destination TDS object:		-	Image
        // destination TDS attribute:	-	SOPAuthorizationComment
        DValue dataElement = dicomDataSet.getValueByGroupElementString("0100,0424");
        if (dataElement == null) {
            return null;
        }
        return getStringAnswer(dataElement);
    }

    /*
    public  getEncryptedAttributes() throws DIException{
    // source DICOM object:			-	SOP Common Module
    // DICOM name and code:			-	(0400,0500) Encrypted Attributes Sequence SQ 1
    // destination TDS object:		-	Image
    // destination TDS attribute:	-	EncryptedAttributes

    // (0400,0510) Encrypted Content Transfer Syntax UID UI 1
    // (0400,0520) Encrypted Content OB 1

    DValue dataElement =  dicomDataSet.getValueByGroupElementString("0400,0500");
    if (dataElement == null) return null;
    return getStringAnswer(dataElement);
    }
     */
    /*	public String[] getHL7StructuredDocument() throws DIException{
    // source DICOM object:			-	SOP Common Module
    // DICOM name and code:			-	(0040,A390) HL7 Structured Document Reference Sequence SQ 1
    // destination TDS object:		-	Image
    // destination TDS attribute:	-	HL7StructuredDocument:
    //	ReferencedSOPClassUID
    //	ReferencedSOPInstanceUID
    //	HL7InstanceIdentifier
    //	RetrieveURI
    // (0008,1150) Referenced SOP Class UID UI 1
    // (0008,1155) Referenced SOP Instance UID UI 1
    // (0040,E001) HL7 Instance Identifier ST 1
    // (0040,E010) Retrieve URI UT 1

    DValue dataElement =  dicomDataSet.getValueByGroupElementString("0040,A390");
    if (dataElement == null) return null;

    return ;
    }*/
    //----Auxiliary methods----------------------------------------------------
    public static String getStringAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            return (String) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static String[] getMultipleStringsAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            String concatenatedStrings = (String) dataElement.getValue();
//				String delimiter = new String(new char[]{'\\'});
            return concatenatedStrings.split("\\\\");
//				return concatenatedStrings.split(delimiter);				
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static SingleIntWithState getSingleIntegerAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.INTARRAY) {
            int[] intArray = (int[]) dataElement.getValue();
            if (intArray.length == 0) {
                return SingleIntWithState.createEmpty();
            } else if (intArray.length != 1) {
                throw new DIException("Integer array length is not 1, while single integer is asked for! ");
            }
            return new SingleIntWithState(((int[]) dataElement.getValue())[0]);
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.INTARRAY);
        }
    }

    public static Integer[] getMultipleIntegerAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.INTARRAY) {
            return (Integer[]) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.INTARRAY);
        }
    }

    public static String getDateAnswerAsYYYYMMDDString(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            if (((String) dataElement.getValue()).isEmpty()) {
                return "";
            } else {
                return DicomDataProcessor.dicomDAtoYYYYMMDDString((String) dataElement.getValue());
            }
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static String getTimeAnswerAsHHMMSSFFFFFFString(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            if (((String) dataElement.getValue()).isEmpty()) {
                return "";
            } else {
                return DicomDataProcessor.dicomTMtoHHMMSSFFFFFFString((String) dataElement.getValue());
            }
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static String getDateTimeAnswerAsYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            if (((String) dataElement.getValue()).isEmpty()) {
                return "";
            } else {
                return DicomDataProcessor.dicomDTtoYYYYMMDDHHMMSSFFFFFFplusOptionalSZZZZString((String) dataElement.getValue());
            }
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static Short[] getDicomOWAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.SHORTARRAY) {
            return (Short[]) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.SHORTARRAY);
        }
    }

    public static Byte[] getDicomOBAnswer(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.BYTEARRAY) {
            return (Byte[]) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.BYTEARRAY);
        }
    }

    public static SingleDoubleWithState getDoubleAnswerFromDicomDS(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            String doubleString = getStringAnswer(dataElement);
            if (doubleString.isEmpty()) {
                return SingleDoubleWithState.createEmpty();
            } else {
                return new SingleDoubleWithState(Double.parseDouble(doubleString));
            }
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static SingleIntWithState getIntegerAnswerFromDicomIS(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.STRING) {
            String intString = getStringAnswer(dataElement);
            if (intString.isEmpty()) {
                return SingleIntWithState.createEmpty();
            } else {
                return new SingleIntWithState(Integer.parseInt(intString));
            }
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    public static ArrayList<DicomItem> getSequence(DValue dataElement) throws DIException {
        if (dataElement.getType() == DValue.LIST) {
            return (ArrayList<DicomItem>) dataElement.getValue();
        } else //			return null;
        {
            throw new DIException("Wrong DValue type: " + dataElement.getType() + " asked: " + DValue.STRING);
        }
    }

    private DicomLUT getLUTAnswerFromSequenceElement(DicomItem dicomItem) throws DIException {
        DValue ans1, ans2, ans3;
        //		(0028,3002) LUT Descriptor US or SS 3
        //		(0028,3003) LUT Explanation LO 1
        //		(0028,3006) LUT Data US or SS	1-n
        //								or OW	1

        ans1 = dicomItem.getValueByGroupElementString("0028,3002");
        ans2 = dicomItem.getValueByGroupElementString("0028,3003");
        ans3 = dicomItem.getValueByGroupElementString("0028,3006");

        Integer[] LUTDescriptor;
        if (ans1 == null) {
            LUTDescriptor = null;
        } else {
            LUTDescriptor = getMultipleIntegerAnswer(ans1);
        }
        String LUTExplanation;
        if (ans1 == null) {
            LUTExplanation = null;
        } else {
            LUTExplanation = getStringAnswer(ans2);
        }

        if (ans3 == null) {
            return new DicomLUT(LUTDescriptor, LUTExplanation);
        } else if (ans3.getType() == DValue.INTARRAY) {
            Short[] LUTDataShortArray = getDicomOWAnswer(ans3);
            return new DicomLUT(LUTDescriptor, LUTExplanation, LUTDataShortArray);
        } else if (ans3.getType() == DValue.SHORTARRAY) {
            Integer[] LUTDataIntTArray = getMultipleIntegerAnswer(ans3);
            return new DicomLUT(LUTDescriptor, LUTExplanation, LUTDataIntTArray);
        } else {
            throw new DIException("Wrong DValue type for PresentationLUT: " + ans3.getType() + " could be: " + DValue.INTARRAY + " or " + DValue.SHORTARRAY);
        }

    }
    //-----------Late added------------------------------
}



/*
{
		// source DICOM object:			-	
		// DICOM name and code:			-	
			// destination TDS object:		-	
			// destination TDS attribute:	-	
		DValue dataElement =  dicomDataSet.getValueByGroupElementString(");
		if (dataElement == null) return null;
		return 		
	}


*/
