/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import base.ITSEntity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "TDSStudy")
@NamedQueries({
    @NamedQuery(name = "TDSStudy.findAll", query = "SELECT t FROM TDSStudy t"),
    @NamedQuery(name = "TDSStudy.findById", query = "SELECT t FROM TDSStudy t WHERE t.id = :id"),
    @NamedQuery(name = "TDSStudy.findByInstanceUid", query = "SELECT t FROM TDSStudy t WHERE t.instanceUid = :instanceUid"),
    @NamedQuery(name = "TDSStudy.findByStudyId", query = "SELECT t FROM TDSStudy t WHERE t.studyId = :studyId"),
    @NamedQuery(name = "TDSStudy.findByStudyDate", query = "SELECT t FROM TDSStudy t WHERE t.studyDate = :studyDate"),
    @NamedQuery(name = "TDSStudy.findByStudyTime", query = "SELECT t FROM TDSStudy t WHERE t.studyTime = :studyTime"),
    @NamedQuery(name = "TDSStudy.findByDescription", query = "SELECT t FROM TDSStudy t WHERE t.description = :description"),
    @NamedQuery(name = "TDSStudy.findByReferringPhysyciansDicomName", query = "SELECT t FROM TDSStudy t WHERE t.referringPhysyciansDicomName = :referringPhysyciansDicomName"),
    @NamedQuery(name = "TDSStudy.findByAdmittingDiagnoseDescription", query = "SELECT t FROM TDSStudy t WHERE t.admittingDiagnoseDescription = :admittingDiagnoseDescription"),
    @NamedQuery(name = "TDSStudy.findByAdditionalPatientHistory", query = "SELECT t FROM TDSStudy t WHERE t.additionalPatientHistory = :additionalPatientHistory"),
    @NamedQuery(name = "TDSStudy.findByPatientMedicalAlerts", query = "SELECT t FROM TDSStudy t WHERE t.patientMedicalAlerts = :patientMedicalAlerts"),
    @NamedQuery(name = "TDSStudy.findBySmokingStatus", query = "SELECT t FROM TDSStudy t WHERE t.smokingStatus = :smokingStatus")})
public class TDSStudy extends ITSEntity {
    @Column(name = "instanceUid")
    private String instanceUid;
    @Column(name = "studyId")
    private String studyId;
    @Column(name = "studyDate")
    @Temporal(TemporalType.DATE)
    private Date studyDate;
    @Column(name = "studyTime")
    @Temporal(TemporalType.TIME)
    private Date studyTime;
    @Column(name = "description")
    private String description;
    @Column(name = "referringPhysyciansDicomName")
    private String referringPhysyciansDicomName;
    @Column(name = "admittingDiagnoseDescription")
    private String admittingDiagnoseDescription;
    @Column(name = "additionalPatientHistory")
    private String additionalPatientHistory;
    @Column(name = "patientMedicalAlerts")
    private String patientMedicalAlerts;
    @Column(name = "smokingStatus")
    private String smokingStatus;
    @Column(name = "accessionNumber")
    private String accessionNumber;
    @Basic(optional = false)
    @Column(name = "numberInList")
    private Integer numberInList;
    //@JoinColumn(name = "caseId", referencedColumnName = "id")
    //@ManyToOne(optional = false)
    //private TDSCase tDSCase;
    @JoinColumn(name = "referralInfoId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ReferralInfo referralInfo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSStudy")
    private Collection<Series> seriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSStudy")
    private Collection<StudyLevelInvoiceElement> studyLevelInvoiceElementCollection;

    public TDSStudy() {
    }

    public TDSStudy(Long id) {
        this.id = id;
    }


    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        this.studyTime = studyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferringPhysyciansDicomName() {
        return referringPhysyciansDicomName;
    }

    public void setReferringPhysyciansDicomName(String referringPhysyciansDicomName) {
        this.referringPhysyciansDicomName = referringPhysyciansDicomName;
    }

    public String getAdmittingDiagnoseDescription() {
        return admittingDiagnoseDescription;
    }

    public void setAdmittingDiagnoseDescription(String admittingDiagnoseDescription) {
        this.admittingDiagnoseDescription = admittingDiagnoseDescription;
    }

    public String getAdditionalPatientHistory() {
        return additionalPatientHistory;
    }

    public void setAdditionalPatientHistory(String additionalPatientHistory) {
        this.additionalPatientHistory = additionalPatientHistory;
    }

    public String getPatientMedicalAlerts() {
        return patientMedicalAlerts;
    }

    public void setPatientMedicalAlerts(String patientMedicalAlerts) {
        this.patientMedicalAlerts = patientMedicalAlerts;
    }

    public String getSmokingStatus() {
        return smokingStatus;
    }

    public void setSmokingStatus(String smokingStatus) {
        this.smokingStatus = smokingStatus;
    }

    public ReferralInfo getReferralInfo() {
        return referralInfo;
    }

    public void setReferralInfo(ReferralInfo referralInfo) {
        this.referralInfo = referralInfo;
    }

    

    public Collection<Series> getSeriesCollection() {
        return seriesCollection;
    }

    public void setSeriesCollection(Collection<Series> seriesCollection) {
        this.seriesCollection = seriesCollection;
    }

    public Collection<StudyLevelInvoiceElement> getStudyLevelInvoiceElementCollection() {
        return studyLevelInvoiceElementCollection;
    }

    public void setStudyLevelInvoiceElementCollection(Collection<StudyLevelInvoiceElement> studyLevelInvoiceElementCollection) {
        this.studyLevelInvoiceElementCollection = studyLevelInvoiceElementCollection;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public Integer getNumberInList() {
        return numberInList;
    }

    public void setNumberInList(Integer numberInList) {
        this.numberInList = numberInList;
    }
//
//    public TDSCase gettDSCase() {
//        return tDSCase;
//    }
//
//    public void settDSCase(TDSCase tDSCase) {
//        this.tDSCase = tDSCase;
//    }


    

}
