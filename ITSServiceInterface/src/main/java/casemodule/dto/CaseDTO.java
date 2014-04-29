/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import hospitalmodule.dto.HospitalContractDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.dto.ModeOfAcquisitionDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import reportingmodule.dto.CommentDTO;
import reportingmodule.dto.ConsultationDTO;
import reportingmodule.dto.ReportingDTO;
import reportingmodule.dto.WorkScrutinyDTO;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseDTO extends BaseDTO {

    public static final String toDO = "toDO";
    public static final String ok = "ok";
    public static final String mismatch = "mismatch";
    public static final String uncertain = "uncertain";
    private String tdsCaseUniqueId;
    private CaseStatusDTO caseStatus;
    private ModeOfAcquisitionDTO modeOfAcquisition;
    private Date transferStarted;//írva
    private Date transferredToServer;//írva
    private Date acceptedAndAssigned; //az első kiosztás időpontja, írva
    private Date opened;//írva
    private Date inProgress;//írva
    private Date done; //írva
    private Date confirmedDone;//írva
    private Date madeReady; //ez új
    private Date readyDisplayedOnHC; //amikor először letölti a ready státuszt.
    private Date reportFirstViewed; //view report gomb a hc-n.
    private Date reportFirstDownloaded; //download report gomb a hc-n.
    private Date reportTransferFirstTried; //transfer report gomb a hc-n.
    private Date reportTransferSuccessful;
    private Date hospTakes;//ez már nem kell, ki lehet venni
    private Date rejectedByTDS;//írva
    private Date deadLine; //írva
    private HospitalContractDTO hospitalContract;
    private HospitalStaffDTO senderHospitalStaff;
    private HospitalStaffDTO receiverHospitalStaff;
    private byte[] finalizedReport;
    private DataProcLogDTO dataProcLog;
    private String hospitalCaseIdDicomAttributeCode;
    private String hospitalCaseIdDicomAttributeValue;
    private List<ReportingDTO> reportingList;
    private List<ConsultationDTO> consultationList;
    private List<CommentDTO> commentList;
    private List<WorkScrutinyDTO> workScrutinyList;
    private List<ScenarioInstanceDTO> scenarioInstanceList;
    private List<FlagAssignmentDTO> flagAssignmentList;
    private Long[] dicomImageIds;
    private String urgency; //enum {notdsrmal, urgent}, amikor átlépjük a deadline-normalclosenessto..
    //normal a default érték
    private String modalities;//nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private String bodyRegions;//nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private String studyDates;//nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private String scenarios;//nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private TDSRadiologistDTO assignedRadiologist; //nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private String unfinalizedReport;//nincs mentve az adatbázisban, csak a megjelenítéshez kell
    private int suggestedOrder;
    //ezek újak
    private List<ReferralInfoDTO> referralInfoList;
    private ElectronicPatientDataDTO electronicPatientData;
    private DicomPatientDataDTO dicomPatientData;
    private ImagePatientDataDTO imagePatientData;//ez valószínűleg nem kell majd
    private String nonDicomCaseIdInHospital1Name;
    private String nonDicomCaseIdInHospital2Name;
    private String nonDicomCaseIdInHospital1ShortName;
    private String nonDicomCaseIdInHospital2ShortName;
    private String nonDicomCaseIdInHospital1Value;
    private String nonDicomCaseIdInHospital2Value;
    private String agreementTest; //dicom-on belüli dolgok
    private Date agreementTestDateTime;//dicom-on belüli dolgok
    private String agreementTestNotes;//dicom-on belüli dolgok
    private String studyStructure;//nincs mentve az adatbáziban, csak a megjelenítéshez kell
    //csak a hc-n kell használni.
    private boolean selected;
    //History
    private List<CaseDTO> itsCaseHistoryList;
    private List<HistoryCaseDTO> hospitalCaseHistoryList;

    public CaseDTO() {
        this(0);
    }

    public CaseDTO(long id) {
        this(id, null);
    }

    public CaseDTO(long id, String tdsCaseUniqueId) {
        super(id);
        this.tdsCaseUniqueId = tdsCaseUniqueId;
        reportingList = new ArrayList<ReportingDTO>();
        consultationList = new ArrayList<ConsultationDTO>();
        commentList = new ArrayList<CommentDTO>();
        workScrutinyList = new ArrayList<WorkScrutinyDTO>();
        scenarioInstanceList = new ArrayList<ScenarioInstanceDTO>();
        flagAssignmentList = new ArrayList<FlagAssignmentDTO>();
        urgency = CaseUrgency.normal.name();
        referralInfoList = new ArrayList<ReferralInfoDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAcceptedAndAssigned() {
        return acceptedAndAssigned;
    }

    public void setAcceptedAndAssigned(Date acceptedAndAssigned) {
        Date oldValue = this.acceptedAndAssigned;
        this.acceptedAndAssigned = acceptedAndAssigned;
        propertyChangeSupport.firePropertyChange("acceptedAndAssigned", oldValue, this.acceptedAndAssigned);
    }

    public HospitalContractDTO getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContractDTO hospitalContract) {
        HospitalContractDTO oldValue = this.hospitalContract;
        this.hospitalContract = hospitalContract;
        propertyChangeSupport.firePropertyChange("hospitalContract", oldValue, this.hospitalContract);
    }

    public Date getConfirmedDone() {
        return confirmedDone;
    }

    public void setConfirmedDone(Date confirmedDone) {
        Date oldValue = this.confirmedDone;
        this.confirmedDone = confirmedDone;
        propertyChangeSupport.firePropertyChange("confirmedDone", oldValue, this.confirmedDone);
    }

    public Date getReadyDisplayedOnHC() {
        return readyDisplayedOnHC;
    }

    public void setReadyDisplayedOnHC(Date readyDisplayedOnHC) {
        Date oldValue = this.readyDisplayedOnHC;
        this.readyDisplayedOnHC = readyDisplayedOnHC;
        propertyChangeSupport.firePropertyChange("readyDisplayedOnHC", oldValue, this.readyDisplayedOnHC);
    }

    public byte[] getFinalizedReport() {
        return finalizedReport;
    }

    public void setFinalizedReport(byte[] finalizedReport) {
        byte[] oldValue = this.finalizedReport;
        this.finalizedReport = finalizedReport;
        propertyChangeSupport.firePropertyChange("finalizedReport", oldValue, this.finalizedReport);
    }

    public Date getHospTakes() {
        return hospTakes;
    }

    public void setHospTakes(Date hospTakes) {
        Date oldValue = this.hospTakes;
        this.hospTakes = hospTakes;
        propertyChangeSupport.firePropertyChange("hospTakes", oldValue, this.hospTakes);
    }

    public ModeOfAcquisitionDTO getModeOfAcquisition() {
        return modeOfAcquisition;
    }

    public void setModeOfAcquisition(ModeOfAcquisitionDTO modeOfAcquisition) {
        ModeOfAcquisitionDTO oldValue = this.modeOfAcquisition;
        this.modeOfAcquisition = modeOfAcquisition;
        propertyChangeSupport.firePropertyChange("modeOfAcquisition", oldValue, this.modeOfAcquisition);
    }

    public HospitalStaffDTO getReceiverHospitalStaff() {
        return receiverHospitalStaff;
    }

    public void setReceiverHospitalStaff(HospitalStaffDTO receiverHospitalStaff) {
        HospitalStaffDTO oldValue = this.receiverHospitalStaff;
        this.receiverHospitalStaff = receiverHospitalStaff;
        propertyChangeSupport.firePropertyChange("receiverHospitalStaff", oldValue, this.receiverHospitalStaff);
    }

    public Date getRejectedByTDS() {
        return rejectedByTDS;
    }

    public void setRejectedByTDS(Date rejectedByTDS) {
        Date oldValue = this.rejectedByTDS;
        this.rejectedByTDS = rejectedByTDS;
        propertyChangeSupport.firePropertyChange("rejectedByTDS", oldValue, this.rejectedByTDS);
    }

    public HospitalStaffDTO getSenderHospitalStaff() {
        return senderHospitalStaff;
    }

    public void setSenderHospitalStaff(HospitalStaffDTO senderHospitalStaff) {
        HospitalStaffDTO oldValue = this.senderHospitalStaff;
        this.senderHospitalStaff = senderHospitalStaff;
        propertyChangeSupport.firePropertyChange("senderHospitalStaff", oldValue, this.senderHospitalStaff);
    }

    public CaseStatusDTO getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatusDTO caseStatus) {
        CaseStatusDTO oldValue = this.caseStatus;
        this.caseStatus = caseStatus;
        propertyChangeSupport.firePropertyChange("caseStatus", oldValue, this.caseStatus);
    }

    public Date getTransferredToServer() {
        return transferredToServer;
    }

    public void setTransferredToServer(Date transferredToServer) {
        Date oldValue = this.transferredToServer;
        this.transferredToServer = transferredToServer;
        propertyChangeSupport.firePropertyChange("transferredToServer", oldValue, this.transferredToServer);
    }

    public Date getTransferStarted() {
        return transferStarted;
    }

    public void setTransferStarted(Date transferStarted) {
        Date oldValue = this.transferStarted;
        this.transferStarted = transferStarted;
        propertyChangeSupport.firePropertyChange("transferStarted", oldValue, this.transferStarted);
    }

//     public PatientAndReferralInfoDTO getPatientAndReferralInfo() {
//        return patientAndReferralInfo;
//    }
//
//    public void setPatientAndReferralInfo(PatientAndReferralInfoDTO patientAndReferralInfo) {
//        PatientAndReferralInfoDTO oldValue = this.patientAndReferralInfo;
//        this.patientAndReferralInfo = patientAndReferralInfo;
//        propertyChangeSupport.firePropertyChange("patientAndReferralInfo", oldValue, this.patientAndReferralInfo);
//    }
    public DataProcLogDTO getDataProcLog() {
        return dataProcLog;
    }

    public void setDataProcLog(DataProcLogDTO dataProcLog) {
        DataProcLogDTO oldValue = this.dataProcLog;
        this.dataProcLog = dataProcLog;
        propertyChangeSupport.firePropertyChange("dataProcLog", oldValue, this.dataProcLog);
    }

    public String getHospitalCaseIdDicomAttributeCode() {
        return hospitalCaseIdDicomAttributeCode;
    }

    public void setHospitalCaseIdDicomAttributeCode(String hospitalCaseIdDicomAttributeCode) {
        String oldValue = this.hospitalCaseIdDicomAttributeCode;
        this.hospitalCaseIdDicomAttributeCode = hospitalCaseIdDicomAttributeCode;
        propertyChangeSupport.firePropertyChange("hospitalCaseIdDicomAttributeCode", oldValue, this.hospitalCaseIdDicomAttributeCode);
    }

    public String getHospitalCaseIdDicomAttributeValue() {
        return hospitalCaseIdDicomAttributeValue;
    }

    public void setHospitalCaseIdDicomAttributeValue(String hospitalCaseIdDicomAttributeValue) {
        String oldValue = this.hospitalCaseIdDicomAttributeValue;
        this.hospitalCaseIdDicomAttributeValue = hospitalCaseIdDicomAttributeValue;
        propertyChangeSupport.firePropertyChange("hospitalCaseIdDicomAttributeValue", oldValue, this.hospitalCaseIdDicomAttributeValue);
    }

    public List<ReportingDTO> getReportingList() {
        return reportingList;
    }

    public void setReportingList(List<ReportingDTO> reportingList) {
        List<ReportingDTO> oldValue = this.reportingList;
        this.reportingList = reportingList;
        propertyChangeSupport.firePropertyChange("reportingList", oldValue, this.reportingList);
    }

    public List<CommentDTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDTO> commentList) {
        List<CommentDTO> oldValue = this.commentList;
        this.commentList = commentList;
        propertyChangeSupport.firePropertyChange("commentList", oldValue, this.commentList);
    }

    public List<ConsultationDTO> getConsultationList() {
        return consultationList;
    }

    public void setConsultationList(List<ConsultationDTO> consultationList) {
        List<ConsultationDTO> oldValue = this.consultationList;
        this.consultationList = consultationList;
        propertyChangeSupport.firePropertyChange("consultationList", oldValue, this.consultationList);
    }

    public List<WorkScrutinyDTO> getWorkScrutinyList() {
        return workScrutinyList;
    }

    public void setWorkScrutinyList(List<WorkScrutinyDTO> workScrutinyList) {
        List<WorkScrutinyDTO> oldValue = this.workScrutinyList;
        this.workScrutinyList = workScrutinyList;
        propertyChangeSupport.firePropertyChange("workScrutinyList", oldValue, this.workScrutinyList);
    }

    public Long[] getDicomImageIds() {
        return dicomImageIds;
    }

    public void setDicomImageIds(Long[] dicomImageIds) {
        this.dicomImageIds = dicomImageIds;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        Date oldValue = this.deadLine;
        this.deadLine = deadLine;
        propertyChangeSupport.firePropertyChange("deadLine", oldValue, this.deadLine);
    }

    public String getTdsCaseUniqueId() {
        return tdsCaseUniqueId;
    }

    public void setTdsCaseUniqueId(String tdsCaseUniqueId) {
        String oldValue = this.tdsCaseUniqueId;
        this.tdsCaseUniqueId = tdsCaseUniqueId;
        propertyChangeSupport.firePropertyChange("tdsCaseUniqueId", oldValue, this.tdsCaseUniqueId);
    }

    public List<ScenarioInstanceDTO> getScenarioInstanceList() {
        return scenarioInstanceList;
    }

    public void setScenarioInstanceList(List<ScenarioInstanceDTO> scenarioInstanceList) {
        List<ScenarioInstanceDTO> oldValue = this.scenarioInstanceList;
        this.scenarioInstanceList = scenarioInstanceList;
        propertyChangeSupport.firePropertyChange("scenarioInstanceList", oldValue, this.scenarioInstanceList);
    }

    public List<FlagAssignmentDTO> getFlagAssignmentList() {
        return flagAssignmentList;
    }

    public void setFlagAssignmentList(List<FlagAssignmentDTO> flagAssignmentList) {
        List<FlagAssignmentDTO> oldValue = this.flagAssignmentList;
        this.flagAssignmentList = flagAssignmentList;
        propertyChangeSupport.firePropertyChange("flagAssignmentList", oldValue, this.flagAssignmentList);
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        Date oldValue = this.done;
        this.done = done;
        propertyChangeSupport.firePropertyChange("done", oldValue, this.done);
    }

    public Date getInProgress() {
        return inProgress;
    }

    public void setInProgress(Date inProgress) {
        Date oldValue = this.inProgress;
        this.inProgress = inProgress;
        propertyChangeSupport.firePropertyChange("inProgress", oldValue, this.inProgress);
    }

    public Date getMadeReady() {
        return madeReady;
    }

    public void setMadeReady(Date madeReady) {
        Date oldValue = this.madeReady;
        this.madeReady = madeReady;
        propertyChangeSupport.firePropertyChange("madeReady", oldValue, this.madeReady);
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        Date oldValue = this.opened;
        this.opened = opened;
        propertyChangeSupport.firePropertyChange("opened", oldValue, this.opened);
    }

    public Date getReportFirstDownloaded() {
        return reportFirstDownloaded;
    }

    public void setReportFirstDownloaded(Date reportFirstDownloaded) {
        Date oldValue = this.reportFirstDownloaded;
        this.reportFirstDownloaded = reportFirstDownloaded;
        propertyChangeSupport.firePropertyChange("reportFirstDownloaded", oldValue, this.reportFirstDownloaded);
    }

    public Date getReportFirstViewed() {
        return reportFirstViewed;
    }

    public void setReportFirstViewed(Date reportFirstViewed) {
        Date oldValue = this.reportFirstViewed;
        this.reportFirstViewed = reportFirstViewed;
        propertyChangeSupport.firePropertyChange("reportFirstViewed", oldValue, this.reportFirstViewed);
    }

    public Date getReportTransferFirstTried() {
        return reportTransferFirstTried;
    }

    public void setReportTransferFirstTried(Date reportTransferFirstTried) {
        Date oldValue = this.reportTransferFirstTried;
        this.reportTransferFirstTried = reportTransferFirstTried;
        propertyChangeSupport.firePropertyChange("reportTransferFirstTried", oldValue, this.reportTransferFirstTried);
    }

    public Date getReportTransferSuccessful() {
        return reportTransferSuccessful;
    }

    public void setReportTransferSuccessful(Date reportTransferSuccessful) {
        Date oldValue = this.reportTransferSuccessful;
        this.reportTransferSuccessful = reportTransferSuccessful;
        propertyChangeSupport.firePropertyChange("reportTransferSuccessful", oldValue, this.reportTransferSuccessful);
    }

    public String getBodyRegions() {
        return bodyRegions;
    }

    public String getModalities() {
        return modalities;
    }

    public String getStudyDates() {
        return studyDates;
    }

    public void setBodyRegions(String bodyRegions) {
        this.bodyRegions = bodyRegions;
    }

    public void setModalities(String modalities) {
        this.modalities = modalities;
    }

    public void setStudyDates(String studyDates) {
        this.studyDates = studyDates;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        String oldValue = this.urgency;
        this.urgency = urgency;
        propertyChangeSupport.firePropertyChange("urgency", oldValue, this.urgency);
    }

    public int getSuggestedOrder() {
        return suggestedOrder;
    }

    public void setSuggestedOrder(int suggestedOrder) {
        int oldValue = this.suggestedOrder;
        this.suggestedOrder = suggestedOrder;
        propertyChangeSupport.firePropertyChange("suggestedOrder", oldValue, this.suggestedOrder);
    }

    public TDSRadiologistDTO getAssignedRadiologist() {
        return assignedRadiologist;
    }

    public void setAssignedRadiologist(TDSRadiologistDTO assignedRadiologist) {
        TDSRadiologistDTO oldValue = this.assignedRadiologist;
        this.assignedRadiologist = assignedRadiologist;
        propertyChangeSupport.firePropertyChange("assignedRadiologist", oldValue, this.assignedRadiologist);
    }

    public String getScenarios() {
        return scenarios;
    }

    public void setScenarios(String scenarios) {
        String oldValue = this.scenarios;
        this.scenarios = scenarios;
        propertyChangeSupport.firePropertyChange("scenarios", oldValue, this.scenarios);
    }

    public String getUnfinalizedReport() {
        return unfinalizedReport;
    }

    public void setUnfinalizedReport(String unfinalizedReport) {
        String oldValue = this.unfinalizedReport;
        this.unfinalizedReport = unfinalizedReport;
        propertyChangeSupport.firePropertyChange("unfinalizedReport", oldValue, this.unfinalizedReport);
    }

    public List<ReferralInfoDTO> getReferralInfoList() {
        return referralInfoList;
    }

    public void setReferralInfoList(List<ReferralInfoDTO> referralInfoList) {
        List<ReferralInfoDTO> oldValue = this.referralInfoList;
        this.referralInfoList = referralInfoList;
        propertyChangeSupport.firePropertyChange("referralInfoList", oldValue, this.referralInfoList);
    }

    public String getAgreementTest() {
        return agreementTest;
    }

    public void setAgreementTest(String agreementTest) {
        String oldValue = this.agreementTest;
        this.agreementTest = agreementTest;
        propertyChangeSupport.firePropertyChange("agreementTest", oldValue, this.agreementTest);
    }

    public Date getAgreementTestDateTime() {
        return agreementTestDateTime;
    }

    public void setAgreementTestDateTime(Date agreementTestDateTime) {
        Date oldValue = this.agreementTestDateTime;
        this.agreementTestDateTime = agreementTestDateTime;
        propertyChangeSupport.firePropertyChange("agreementTestDateTime", oldValue, this.agreementTestDateTime);
    }

    public String getAgreementTestNotes() {
        return agreementTestNotes;
    }

    public void setAgreementTestNotes(String agreementTestNotes) {
        String oldValue = this.agreementTestNotes;
        this.agreementTestNotes = agreementTestNotes;
        propertyChangeSupport.firePropertyChange("agreementTestNotes", oldValue, this.agreementTestNotes);
    }

    public DicomPatientDataDTO getDicomPatientData() {
        return dicomPatientData;
    }

    public void setDicomPatientData(DicomPatientDataDTO dicomPatientData) {
        DicomPatientDataDTO oldValue = this.dicomPatientData;
        this.dicomPatientData = dicomPatientData;
        propertyChangeSupport.firePropertyChange("dicomPatientData", oldValue, this.dicomPatientData);
    }

    public ElectronicPatientDataDTO getElectronicPatientData() {
        return electronicPatientData;
    }

    public void setElectronicPatientData(ElectronicPatientDataDTO electronicPatientData) {
        ElectronicPatientDataDTO oldValue = this.electronicPatientData;
        this.electronicPatientData = electronicPatientData;
        propertyChangeSupport.firePropertyChange("electronicPatientData", oldValue, this.electronicPatientData);
    }

    public ImagePatientDataDTO getImagePatientData() {
        return imagePatientData;
    }

    public void setImagePatientData(ImagePatientDataDTO imagePatientData) {
        ImagePatientDataDTO oldValue = this.imagePatientData;
        this.imagePatientData = imagePatientData;
        propertyChangeSupport.firePropertyChange("imagePatientData", oldValue, this.imagePatientData);
    }

    public String getNonDicomCaseIdInHospital1Name() {
        return nonDicomCaseIdInHospital1Name;
    }

    public void setNonDicomCaseIdInHospital1Name(String nonDicomCaseIdInHospital1Name) {
        String oldValue = this.nonDicomCaseIdInHospital1Name;
        this.nonDicomCaseIdInHospital1Name = nonDicomCaseIdInHospital1Name;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital1Name", oldValue, this.nonDicomCaseIdInHospital1Name);
    }

    public String getNonDicomCaseIdInHospital1ShortName() {
        return nonDicomCaseIdInHospital1ShortName;
    }

    public void setNonDicomCaseIdInHospital1ShortName(String nonDicomCaseIdInHospital1ShortName) {
        String oldValue = this.nonDicomCaseIdInHospital1ShortName;
        this.nonDicomCaseIdInHospital1ShortName = nonDicomCaseIdInHospital1ShortName;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital1ShortName", oldValue, this.nonDicomCaseIdInHospital1ShortName);
    }

    public String getNonDicomCaseIdInHospital1Value() {
        return nonDicomCaseIdInHospital1Value;
    }

    public void setNonDicomCaseIdInHospital1Value(String nonDicomCaseIdInHospital1Value) {
        String oldValue = this.nonDicomCaseIdInHospital1Value;
        this.nonDicomCaseIdInHospital1Value = nonDicomCaseIdInHospital1Value;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital1Value", oldValue, this.nonDicomCaseIdInHospital1Value);
    }

    public String getNonDicomCaseIdInHospital2Name() {
        return nonDicomCaseIdInHospital2Name;
    }

    public void setNonDicomCaseIdInHospital2Name(String nonDicomCaseIdInHospital2Name) {
        String oldValue = this.nonDicomCaseIdInHospital2Name;
        this.nonDicomCaseIdInHospital2Name = nonDicomCaseIdInHospital2Name;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital2Name", oldValue, this.nonDicomCaseIdInHospital2Name);
    }

    public String getNonDicomCaseIdInHospital2ShortName() {
        return nonDicomCaseIdInHospital2ShortName;
    }

    public void setNonDicomCaseIdInHospital2ShortName(String nonDicomCaseIdInHospital2ShortName) {
        String oldValue = this.nonDicomCaseIdInHospital2ShortName;
        this.nonDicomCaseIdInHospital2ShortName = nonDicomCaseIdInHospital2ShortName;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital2ShortName", oldValue, this.nonDicomCaseIdInHospital2ShortName);
    }

    public String getNonDicomCaseIdInHospital2Value() {
        return nonDicomCaseIdInHospital2Value;
    }

    public void setNonDicomCaseIdInHospital2Value(String nonDicomCaseIdInHospital2Value) {
        String oldValue = this.nonDicomCaseIdInHospital2Value;
        this.nonDicomCaseIdInHospital2Value = nonDicomCaseIdInHospital2Value;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital2Value", oldValue, this.nonDicomCaseIdInHospital2Value);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        boolean oldValue = this.selected;
        this.selected = selected;
        propertyChangeSupport.firePropertyChange("selected", oldValue, this.selected);
    }

    public String getStudyStructure() {
        return studyStructure;
    }

    public void setStudyStructure(String studyStructure) {
        String oldValue = this.studyStructure;
        this.studyStructure = studyStructure;
        propertyChangeSupport.firePropertyChange("studyStructure", oldValue, this.studyStructure);
    }

    public List<HistoryCaseDTO> getHospitalCaseHistoryList() {
        return hospitalCaseHistoryList;
    }

    public void setHospitalCaseHistoryList(List<HistoryCaseDTO> hospitalCaseHistoryList) {
        List<HistoryCaseDTO> oldValue = this.hospitalCaseHistoryList;
        this.hospitalCaseHistoryList = hospitalCaseHistoryList;
        propertyChangeSupport.firePropertyChange("hospitalCaseHistoryList", oldValue, this.hospitalCaseHistoryList);
    }

    public List<CaseDTO> getItsCaseHistoryList() {
        return itsCaseHistoryList;
    }

    public void setItsCaseHistoryList(List<CaseDTO> itsCaseHistoryList) {
        List<CaseDTO> oldValue = this.itsCaseHistoryList;
        this.itsCaseHistoryList = itsCaseHistoryList;
        propertyChangeSupport.firePropertyChange("itsCaseHistoryList", oldValue, this.itsCaseHistoryList);
    }

}
