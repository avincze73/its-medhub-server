/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class StudyDTO extends BaseDTO {

    private ReferralInfoDTO referralInfo;
    //private CaseDTO caseItBelongsTo;
    private Date studyDate;
    private Date studyTime;
    private String instanceUid;
    private String studyId;
    private String description;
    private String referringPhysyciansDicomName;
    private String admittingDiagnoseDescription;
    private String additionalPatientHistory;
    private String patientMedicalAlerts;
    private String smokingStatus;
    private String accessionNumber;
    private int numberInList;
    private List<SeriesDTO> seriesList;

    public StudyDTO() {
        this(0);
    }

    public StudyDTO(long id) {
        super(id);
        seriesList = new ArrayList<SeriesDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StudyDTO)) {
            return false;
        }
        return super.equals(obj);
    }

//    public CaseDTO getCaseItBelongsTo() {
//        return caseItBelongsTo;
//    }
//
//    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
//        CaseDTO oldValue = this.caseItBelongsTo;
//        this.caseItBelongsTo = caseItBelongsTo;
//        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
//    }
    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        Date oldValue = this.studyDate;
        this.studyDate = studyDate;
        propertyChangeSupport.firePropertyChange("studyDate", oldValue, this.studyDate);
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        Date oldValue = this.studyTime;
        this.studyTime = studyTime;
        propertyChangeSupport.firePropertyChange("studyTime", oldValue, this.studyTime);
    }

    public List<SeriesDTO> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SeriesDTO> seriesList) {
        List<SeriesDTO> oldValue = this.seriesList;
        this.seriesList = seriesList;
        propertyChangeSupport.firePropertyChange("seriesList", oldValue, this.seriesList);
    }

    public void addSeries(SeriesDTO series) {
        series.setStudy(this);
        seriesList.add(series);
    }

    public String getAdditionalPatientHistory() {
        return additionalPatientHistory;
    }

    public void setAdditionalPatientHistory(String additionalPatientHistory) {
        String oldValue = this.additionalPatientHistory;
        this.additionalPatientHistory = additionalPatientHistory;
        propertyChangeSupport.firePropertyChange("additionalPatientHistory", oldValue, this.additionalPatientHistory);
    }

    public String getAdmittingDiagnoseDescription() {
        return admittingDiagnoseDescription;
    }

    public void setAdmittingDiagnoseDescription(String admittingDiagnoseDescription) {
        String oldValue = this.admittingDiagnoseDescription;
        this.admittingDiagnoseDescription = admittingDiagnoseDescription;
        propertyChangeSupport.firePropertyChange("admittingDiagnoseDescription", oldValue, this.admittingDiagnoseDescription);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);

    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        String oldValue = this.instanceUid;
        this.instanceUid = instanceUid;
        propertyChangeSupport.firePropertyChange("instanceUid", oldValue, this.instanceUid);
    }

    public String getPatientMedicalAlerts() {
        return patientMedicalAlerts;
    }

    public void setPatientMedicalAlerts(String patientMedicalAlerts) {
        String oldValue = this.patientMedicalAlerts;
        this.patientMedicalAlerts = patientMedicalAlerts;
        propertyChangeSupport.firePropertyChange("patientMedicalAlerts", oldValue, this.patientMedicalAlerts);
    }

    public String getReferringPhysyciansDicomName() {
        return referringPhysyciansDicomName;
    }

    public void setReferringPhysyciansDicomName(String referringPhysyciansDicomName) {
        String oldValue = this.referringPhysyciansDicomName;
        this.referringPhysyciansDicomName = referringPhysyciansDicomName;
        propertyChangeSupport.firePropertyChange("referringPhysyciansDicomName", oldValue, this.referringPhysyciansDicomName);
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) {
        String oldValue = this.smokingStatus;
        this.smokingStatus = smokingStatus;
        propertyChangeSupport.firePropertyChange("smokingStatus", oldValue, this.smokingStatus);
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        String oldValue = this.studyId;
        this.studyId = studyId;
        propertyChangeSupport.firePropertyChange("studyId", oldValue, this.studyId);
    }

    public ReferralInfoDTO getReferralInfo() {
        return referralInfo;
    }

    public void setReferralInfo(ReferralInfoDTO referralInfo) {
        ReferralInfoDTO oldValue = this.referralInfo;
        this.referralInfo = referralInfo;
        propertyChangeSupport.firePropertyChange("referralInfo", oldValue, this.referralInfo);
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        String oldValue = this.accessionNumber;
        this.accessionNumber = accessionNumber;
        propertyChangeSupport.firePropertyChange("accessionNumber", oldValue, this.accessionNumber);
    }

    public int getNumberInList() {
        return numberInList;
    }

    public void setNumberInList(int numberInList) {
        int oldValue = this.numberInList;
        this.numberInList = numberInList;
        propertyChangeSupport.firePropertyChange("numberInList", oldValue, this.numberInList);
    }

    public String getStudyNumber() {
        return "Study " + numberInList;
    }
}
