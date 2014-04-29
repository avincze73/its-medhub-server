/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import base.ITSEntity;
import radiologistmodule.entity.CaseLevelInvoiceElement;
import masterdatamodule.entity.CaseStatus;
import radiologistmodule.entity.Comment;
import radiologistmodule.entity.Consultation;
import hospitalmodule.entity.HospitalContract;
import masterdatamodule.entity.ModeOfAcquisition;
import reportingmodule.entity.Reporting;
import systemmodule.entity.SystemMessage;
import radiologistmodule.entity.WorkScrutiny;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import usermodule.entity.HospitalStaff;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "TDSCase")
@NamedQueries({
    @NamedQuery(name = "TDSCase.findAll", query = "SELECT t FROM TDSCase t"),
    @NamedQuery(name = "TDSCase.findById", query = "SELECT t FROM TDSCase t WHERE t.id = :id"),
    @NamedQuery(name = "TDSCase.findByTdsCaseUniqueId", query = "SELECT t FROM TDSCase t WHERE t.tdsCaseUniqueId = :tdsCaseUniqueId"),
    @NamedQuery(name = "TDSCase.findByTransferredToServer", query = "SELECT t FROM TDSCase t WHERE t.transferredToServer = :transferredToServer"),
    @NamedQuery(name = "TDSCase.findByTransferStarted", query = "SELECT t FROM TDSCase t WHERE t.transferStarted = :transferStarted"),
    @NamedQuery(name = "TDSCase.findByAcceptedAndAssigned", query = "SELECT t FROM TDSCase t WHERE t.acceptedAndAssigned = :acceptedAndAssigned"),
    @NamedQuery(name = "TDSCase.findByConfirmedDone", query = "SELECT t FROM TDSCase t WHERE t.confirmedDone = :confirmedDone"),
    @NamedQuery(name = "TDSCase.findByReadyDisplayedOnHC", query = "SELECT t FROM TDSCase t WHERE t.readyDisplayedOnHC = :readyDisplayedOnHC"),
    @NamedQuery(name = "TDSCase.findByHospTakes", query = "SELECT t FROM TDSCase t WHERE t.hospTakes = :hospTakes"),
    @NamedQuery(name = "TDSCase.findByRejectedByTDS", query = "SELECT t FROM TDSCase t WHERE t.rejectedByTDS = :rejectedByTDS"),
    @NamedQuery(name = "TDSCase.findByDeadLine", query = "SELECT t FROM TDSCase t WHERE t.deadLine = :deadLine"),
    @NamedQuery(name = "TDSCase.findByHospitalCaseIdDicomAttributeCode", query = "SELECT t FROM TDSCase t WHERE t.hospitalCaseIdDicomAttributeCode = :hospitalCaseIdDicomAttributeCode"),
    @NamedQuery(name = "TDSCase.findByHospitalCaseIdDicomAttributeValue", query = "SELECT t FROM TDSCase t WHERE t.hospitalCaseIdDicomAttributeValue = :hospitalCaseIdDicomAttributeValue"),
    @NamedQuery(name = "TDSCase.findByAgreementTest", query = "SELECT t FROM TDSCase t WHERE t.agreementTest = :agreementTest"),
    @NamedQuery(name = "TDSCase.findByAgreementTestDateTime", query = "SELECT t FROM TDSCase t WHERE t.agreementTestDateTime = :agreementTestDateTime"),
    @NamedQuery(name = "TDSCase.findByAgreementTestNotes", query = "SELECT t FROM TDSCase t WHERE t.agreementTestNotes = :agreementTestNotes")})
public class TDSCase extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "tdsCaseUniqueId")
    private String tdsCaseUniqueId;
    @Basic(optional = false)
    @Column(name = "transferredToServer")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferredToServer;
    @Basic(optional = false)
    @Column(name = "transferStarted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transferStarted;
    @Basic(optional = false)
    @Column(name = "opened")
    @Temporal(TemporalType.TIMESTAMP)
    private Date opened;
    @Basic(optional = false)
    @Column(name = "inProgress")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inProgress;
    @Basic(optional = false)
    @Column(name = "done")
    @Temporal(TemporalType.TIMESTAMP)
    private Date done;
    @Basic(optional = false)
    @Column(name = "madeReady")
    @Temporal(TemporalType.TIMESTAMP)
    private Date madeReady;
    @Basic(optional = false)
    @Column(name = "reportFirstViewed")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportFirstViewed;
    @Basic(optional = false)
    @Column(name = "reportFirstDownloaded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportFirstDownloaded;
    @Basic(optional = false)
    @Column(name = "reportTransferFirstTried")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTransferFirstTried;
    @Basic(optional = false)
    @Column(name = "reportTransferSuccessful")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportTransferSuccessful;
    @Column(name = "acceptedAndAssigned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedAndAssigned;
    @Column(name = "confirmedDone")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmedDone;
    @Column(name = "readyDisplayedOnHC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date readyDisplayedOnHC;
    @Column(name = "hospTakes")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hospTakes;
    @Column(name = "rejectedByTDS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rejectedByTDS;
    @Column(name = "deadLine")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadLine;
    @Column(name = "hospitalCaseIdDicomAttributeCode")
    private String hospitalCaseIdDicomAttributeCode;
    @Column(name = "hospitalCaseIdDicomAttributeValue")
    private String hospitalCaseIdDicomAttributeValue;
    @Lob
    @Column(name = "finalizedReport")
    private byte[] finalizedReport;
    @Column(name = "nonDicomCaseIdInHospital1Name")
    private String nonDicomCaseIdInHospital1Name;
    @Column(name = "nonDicomCaseIdInHospital1ShortName")
    private String nonDicomCaseIdInHospital1ShortName;
    @Column(name = "nonDicomCaseIdInHospital1Value")
    private String nonDicomCaseIdInHospital1Value;
    @Column(name = "nonDicomCaseIdInHospital2Name")
    private String nonDicomCaseIdInHospital2Name;
    @Column(name = "nonDicomCaseIdInHospital2ShortName")
    private String nonDicomCaseIdInHospital2ShortName;
    @Column(name = "nonDicomCaseIdInHospital2Value")
    private String nonDicomCaseIdInHospital2Value;
    @Basic(optional = false)
    @Column(name = "agreementTest")
    private String agreementTest;
    @Basic(optional = false)
    @Column(name = "agreementTestDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date agreementTestDateTime;
    @Column(name = "agreementTestNotes")
    private String agreementTestNotes;
    @Column(name = "urgency")
    private String urgency;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<ScenarioInstance> scenarioInstanceCollection;
    @JoinColumn(name = "electronicPatientDataId", referencedColumnName = "id")
    @ManyToOne
    private ElectronicPatientData electronicPatientData;
    @JoinColumn(name = "caseStatusId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CaseStatus caseStatus;
    @JoinColumn(name = "senderHospitalStaffId", referencedColumnName = "id")
    @ManyToOne
    private HospitalStaff hospitalStaff;
    @JoinColumn(name = "modeOfAcquisitionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ModeOfAcquisition modeOfAcquisition;
    @JoinColumn(name = "imagePatientDataId", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ImagePatientData imagePatientData;
    @JoinColumn(name = "imageReferralId", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ImageReferral imageReferral;
    
    @JoinColumn(name = "dicomPatientDataId", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private DicomPatientData dicomPatientData;
    
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;
    @JoinColumn(name = "dataProcLogId", referencedColumnName = "id")
    @OneToOne(optional = false)
    private DataProcLog dataProcLog;
    @JoinColumn(name = "receiverHospitalStaffId", referencedColumnName = "id")
    @ManyToOne
    private HospitalStaff hospitalStaff1;
    @OneToMany(mappedBy = "tDSCase")
    private Collection<SystemMessage> systemMessageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<FlagAssignment> flagAssignmentCollection;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    //private List<TDSStudy> tdsStudyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<CaseLevelInvoiceElement> caseLevelInvoiceElementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<WorkScrutiny> workScrutinyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<Consultation> consultationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private Collection<Reporting> reportingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private List<ReferralInfo> referralInfoCollection;
    //History
//    @ManyToMany
//    @JoinTable(name = "ITSCaseHistories",
//    joinColumns = {
//        @JoinColumn(name = "caseId")},
//    inverseJoinColumns = {
//        @JoinColumn(name = "historyCaseId")})
//    private List<TDSCase> itsCaseHistoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSCase")
    private List<HistoryCase> hospitalCaseHistoryList;
//    @JoinColumn(name = "imageReportId", referencedColumnName = "id")
//    @ManyToOne
//    private ImageReport imageReport;

    public TDSCase() {
    }

    public TDSCase(Long id) {
        this.id = id;
    }

    public TDSCase(Long id, String tdsCaseUniqueId, Date transferredToServer, Date transferStarted, String agreementTest, Date agreementTestDateTime) {
        this.id = id;
        this.tdsCaseUniqueId = tdsCaseUniqueId;
        this.transferredToServer = transferredToServer;
        this.transferStarted = transferStarted;
        this.agreementTest = agreementTest;
        this.agreementTestDateTime = agreementTestDateTime;
    }
//
//    public List<TDSStudy> getTdsStudyList() {
//        return tdsStudyList;
//    }
//
//    public void setTdsStudyList(List<TDSStudy> tdsStudyList) {
//        this.tdsStudyList = tdsStudyList;
//    }

    
    public String getTdsCaseUniqueId() {
        return tdsCaseUniqueId;
    }

    public void setTdsCaseUniqueId(String tdsCaseUniqueId) {
        this.tdsCaseUniqueId = tdsCaseUniqueId;
    }

    public Date getTransferredToServer() {
        return transferredToServer;
    }

    public void setTransferredToServer(Date transferredToServer) {
        this.transferredToServer = transferredToServer;
    }

    public Date getTransferStarted() {
        return transferStarted;
    }

    public void setTransferStarted(Date transferStarted) {
        this.transferStarted = transferStarted;
    }

    public Date getAcceptedAndAssigned() {
        return acceptedAndAssigned;
    }

    public void setAcceptedAndAssigned(Date acceptedAndAssigned) {
        this.acceptedAndAssigned = acceptedAndAssigned;
    }

    public Date getConfirmedDone() {
        return confirmedDone;
    }

    public void setConfirmedDone(Date confirmedDone) {
        this.confirmedDone = confirmedDone;
    }

    public Date getReadyDisplayedOnHC() {
        return readyDisplayedOnHC;
    }

    public void setReadyDisplayedOnHC(Date readyDisplayedOnHC) {
        this.readyDisplayedOnHC = readyDisplayedOnHC;
    }

    public Date getHospTakes() {
        return hospTakes;
    }

    public void setHospTakes(Date hospTakes) {
        this.hospTakes = hospTakes;
    }

    public ImageReferral getImageReferral() {
        return imageReferral;
    }

    public void setImageReferral(ImageReferral imageReferral) {
        this.imageReferral = imageReferral;
    }

    public Date getRejectedByTDS() {
        return rejectedByTDS;
    }

    public void setRejectedByTDS(Date rejectedByTDS) {
        this.rejectedByTDS = rejectedByTDS;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getHospitalCaseIdDicomAttributeCode() {
        return hospitalCaseIdDicomAttributeCode;
    }

    public void setHospitalCaseIdDicomAttributeCode(String hospitalCaseIdDicomAttributeCode) {
        this.hospitalCaseIdDicomAttributeCode = hospitalCaseIdDicomAttributeCode;
    }

    public String getHospitalCaseIdDicomAttributeValue() {
        return hospitalCaseIdDicomAttributeValue;
    }

    public void setHospitalCaseIdDicomAttributeValue(String hospitalCaseIdDicomAttributeValue) {
        this.hospitalCaseIdDicomAttributeValue = hospitalCaseIdDicomAttributeValue;
    }

    public byte[] getFinalizedReport() {
        return finalizedReport;
    }

    public void setFinalizedReport(byte[] finalizedReport) {
        this.finalizedReport = finalizedReport;
    }

    public String getNonDicomCaseIdInHospital1Name() {
        return nonDicomCaseIdInHospital1Name;
    }

    public void setNonDicomCaseIdInHospital1Name(String nonDicomCaseIdInHospital1Name) {
        this.nonDicomCaseIdInHospital1Name = nonDicomCaseIdInHospital1Name;
    }

    public String getNonDicomCaseIdInHospital1ShortName() {
        return nonDicomCaseIdInHospital1ShortName;
    }

    public void setNonDicomCaseIdInHospital1ShortName(String nonDicomCaseIdInHospital1ShortName) {
        this.nonDicomCaseIdInHospital1ShortName = nonDicomCaseIdInHospital1ShortName;
    }

    public String getNonDicomCaseIdInHospital1Value() {
        return nonDicomCaseIdInHospital1Value;
    }

    public void setNonDicomCaseIdInHospital1Value(String nonDicomCaseIdInHospital1Value) {
        this.nonDicomCaseIdInHospital1Value = nonDicomCaseIdInHospital1Value;
    }

    public String getNonDicomCaseIdInHospital2Name() {
        return nonDicomCaseIdInHospital2Name;
    }

    public void setNonDicomCaseIdInHospital2Name(String nonDicomCaseIdInHospital2Name) {
        this.nonDicomCaseIdInHospital2Name = nonDicomCaseIdInHospital2Name;
    }

    public String getNonDicomCaseIdInHospital2ShortName() {
        return nonDicomCaseIdInHospital2ShortName;
    }

    public void setNonDicomCaseIdInHospital2ShortName(String nonDicomCaseIdInHospital2ShortName) {
        this.nonDicomCaseIdInHospital2ShortName = nonDicomCaseIdInHospital2ShortName;
    }

    public String getNonDicomCaseIdInHospital2Value() {
        return nonDicomCaseIdInHospital2Value;
    }

    public void setNonDicomCaseIdInHospital2Value(String nonDicomCaseIdInHospital2Value) {
        this.nonDicomCaseIdInHospital2Value = nonDicomCaseIdInHospital2Value;
    }

    public String getAgreementTest() {
        return agreementTest;
    }

    public void setAgreementTest(String agreementTest) {
        this.agreementTest = agreementTest;
    }

    public Date getAgreementTestDateTime() {
        return agreementTestDateTime;
    }

    public void setAgreementTestDateTime(Date agreementTestDateTime) {
        this.agreementTestDateTime = agreementTestDateTime;
    }

    public String getAgreementTestNotes() {
        return agreementTestNotes;
    }

    public void setAgreementTestNotes(String agreementTestNotes) {
        this.agreementTestNotes = agreementTestNotes;
    }

    public Collection<ScenarioInstance> getScenarioInstanceCollection() {
        return scenarioInstanceCollection;
    }

    public void setScenarioInstanceCollection(Collection<ScenarioInstance> scenarioInstanceCollection) {
        this.scenarioInstanceCollection = scenarioInstanceCollection;
    }

    public ElectronicPatientData getElectronicPatientData() {
        return electronicPatientData;
    }

    public void setElectronicPatientData(ElectronicPatientData electronicPatientData) {
        this.electronicPatientData = electronicPatientData;
    }

    public CaseStatus getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(CaseStatus caseStatus) {
        this.caseStatus = caseStatus;
    }

    public HospitalStaff getHospitalStaff() {
        return hospitalStaff;
    }

    public void setHospitalStaff(HospitalStaff hospitalStaff) {
        this.hospitalStaff = hospitalStaff;
    }

    public ModeOfAcquisition getModeOfAcquisition() {
        return modeOfAcquisition;
    }

    public void setModeOfAcquisition(ModeOfAcquisition modeOfAcquisition) {
        this.modeOfAcquisition = modeOfAcquisition;
    }

    public ImagePatientData getImagePatientData() {
        return imagePatientData;
    }

    public void setImagePatientData(ImagePatientData imagePatientData) {
        this.imagePatientData = imagePatientData;
    }

    public DicomPatientData getDicomPatientData() {
        return dicomPatientData;
    }

    public void setDicomPatientData(DicomPatientData dicomPatientData) {
        this.dicomPatientData = dicomPatientData;
    }

    public HospitalContract getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContract hospitalContract) {
        this.hospitalContract = hospitalContract;
    }

    public DataProcLog getDataProcLog() {
        return dataProcLog;
    }

    public void setDataProcLog(DataProcLog dataProcLog) {
        this.dataProcLog = dataProcLog;
    }

    public HospitalStaff getHospitalStaff1() {
        return hospitalStaff1;
    }

    public void setHospitalStaff1(HospitalStaff hospitalStaff1) {
        this.hospitalStaff1 = hospitalStaff1;
    }

    public Collection<SystemMessage> getSystemMessageCollection() {
        return systemMessageCollection;
    }

    public void setSystemMessageCollection(Collection<SystemMessage> systemMessageCollection) {
        this.systemMessageCollection = systemMessageCollection;
    }

    public Collection<FlagAssignment> getFlagAssignmentCollection() {
        return flagAssignmentCollection;
    }

    public void setFlagAssignmentCollection(Collection<FlagAssignment> flagAssignmentCollection) {
        this.flagAssignmentCollection = flagAssignmentCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<CaseLevelInvoiceElement> getCaseLevelInvoiceElementCollection() {
        return caseLevelInvoiceElementCollection;
    }

    public void setCaseLevelInvoiceElementCollection(Collection<CaseLevelInvoiceElement> caseLevelInvoiceElementCollection) {
        this.caseLevelInvoiceElementCollection = caseLevelInvoiceElementCollection;
    }

    public Collection<WorkScrutiny> getWorkScrutinyCollection() {
        return workScrutinyCollection;
    }

    public void setWorkScrutinyCollection(Collection<WorkScrutiny> workScrutinyCollection) {
        this.workScrutinyCollection = workScrutinyCollection;
    }

    public Collection<Consultation> getConsultationCollection() {
        return consultationCollection;
    }

    public void setConsultationCollection(Collection<Consultation> consultationCollection) {
        this.consultationCollection = consultationCollection;
    }

    public Collection<Reporting> getReportingCollection() {
        return reportingCollection;
    }

    public void setReportingCollection(Collection<Reporting> reportingCollection) {
        this.reportingCollection = reportingCollection;
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        this.done = done;
    }

    public Date getInProgress() {
        return inProgress;
    }

    public void setInProgress(Date inProgress) {
        this.inProgress = inProgress;
    }

    public Date getMadeReady() {
        return madeReady;
    }

    public void setMadeReady(Date madeReady) {
        this.madeReady = madeReady;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getReportFirstDownloaded() {
        return reportFirstDownloaded;
    }

    public void setReportFirstDownloaded(Date reportFirstDownloaded) {
        this.reportFirstDownloaded = reportFirstDownloaded;
    }

    public Date getReportFirstViewed() {
        return reportFirstViewed;
    }

    public void setReportFirstViewed(Date reportFirstViewed) {
        this.reportFirstViewed = reportFirstViewed;
    }

    public Date getReportTransferFirstTried() {
        return reportTransferFirstTried;
    }

    public void setReportTransferFirstTried(Date reportTransferFirstTried) {
        this.reportTransferFirstTried = reportTransferFirstTried;
    }

    public Date getReportTransferSuccessful() {
        return reportTransferSuccessful;
    }

    public void setReportTransferSuccessful(Date reportTransferSuccessful) {
        this.reportTransferSuccessful = reportTransferSuccessful;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public List<ReferralInfo> getReferralInfoCollection() {
        return referralInfoCollection;
    }

    public void setReferralInfoCollection(List<ReferralInfo> referralInfoCollection) {
        this.referralInfoCollection = referralInfoCollection;
    }

    public List<HistoryCase> getHospitalCaseHistoryList() {
        return hospitalCaseHistoryList;
    }

    public void setHospitalCaseHistoryList(List<HistoryCase> hospitalCaseHistoryList) {
        this.hospitalCaseHistoryList = hospitalCaseHistoryList;
    }

//    public List<TDSCase> getItsCaseHistoryList() {
//        return itsCaseHistoryList;
//    }
//
//    public void setItsCaseHistoryList(List<TDSCase> itsCaseHistoryList) {
//        this.itsCaseHistoryList = itsCaseHistoryList;
//    }
//    public ImageReport getImageReport() {
//        return imageReport;
//    }
//
//    public void setImageReport(ImageReport imageReport) {
//        this.imageReport = imageReport;
//    }
}
