/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.entity;

import reportingmodule.entity.Reporting;
import systemmodule.entity.SystemMessage;
import usermodule.entity.ITSUser;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import usermodule.entity.ITSRadiologistRoleAssignment;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "TDSRadiologist")
public class ITSRadiologist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "reportSendingCode")
    private String reportSendingCode;
    @Basic(optional = false)
    @Column(name = "creditPoints")
    private int creditPoints;
    @Basic(optional = false)
    @Column(name = "creditValidity")
    @Temporal(TemporalType.DATE)
    private Date creditValidity;
    @Basic(optional = false)
    @Column(name = "lastCheck")
    @Temporal(TemporalType.DATE)
    private Date lastCheck;
    @Basic(optional = false)
    @Column(name = "defaultNormalAvailabilityHrsPerWeekDay")
    private int defaultNormalAvailabilityHrsPerWeekDay;
    @Basic(optional = false)
    @Column(name = "defaultMaxAvailabilityHrsPerWeekDay")
    private int defaultMaxAvailabilityHrsPerWeekDay;
    @Basic(optional = false)
    @Column(name = "defaultNormalAvailabilityHrsPerWeekendDay")
    private int defaultNormalAvailabilityHrsPerWeekendDay;
    @Basic(optional = false)
    @Column(name = "defaultMaxAvailabilityHrsPerWeekendDay")
    private int defaultMaxAvailabilityHrsPerWeekendDay;
    @Basic(optional = false)
    @Column(name = "workDemandPriority")
    private int workDemandPriority;
    @Basic(optional = false)
    @Column(name = "englishLanguageCompetence")
    private int englishLanguageCompetence;
    @Basic(optional = false)
    @Column(name = "hungarianLanguageCompetence")
    private int hungarianLanguageCompetence;
    @Basic(optional = false)
    @Column(name = "englishAllowed")
    private boolean englishAllowed;
    @Basic(optional = false)
    @Column(name = "canReport")
    private boolean canReport;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Basic(optional = false)
    @Column(name = "hungarianSalaryRate")
    private double hungarianSalaryRate;
    @Basic(optional = false)
    @Column(name = "englishSalaryRate")
    private double englishSalaryRate;
    @Basic(optional = false)
    @Column(name = "radInvoiceClosingDay")
    private int radInvoiceClosingDay;
    @Basic(optional = false)
    @Column(name = "radPaymentDay")
    private int radPaymentDay;
    @Basic(optional = false)
    @Column(name = "inProbation")
    private boolean inProbation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<WorkPlace> workPlaceCollection;
    @OneToMany(mappedBy = "tDSRadiologist")
    private Collection<SystemMessage> systemMessageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisor")
    private Collection<SuperVision> superVisionCollection;
    @JoinColumn(name = "itsUserId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ITSUser itsUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<WorkScrutiny> workScrutinyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<RegLicQualOwnership> regLicQualOwnershipCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<Consultation> consultationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<RadCompetence> radCompetenceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<Reporting> reportingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<RadAvailability> radAvailabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itsRadiologist")
    private List<ITSRadiologistRoleAssignment> itsRadiologistRoleAssignmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tDSRadiologist")
    private Collection<CompanyAssignment> companyAssignmentCollection;

    public ITSRadiologist() {
    }

    public ITSRadiologist(Long id) {
        this.id = id;
    }

    public ITSRadiologist(Long id, String reportSendingCode, int creditPoints, Date creditValidity, Date lastCheck, int defaultNormalAvailabilityHrsPerWeekDay, int defaultMaxAvailabilityHrsPerWeekDay, int defaultNormalAvailabilityHrsPerWeekendDay, int defaultMaxAvailabilityHrsPerWeekendDay, int workDemandPriority, int englishLanguageCompetence, int hungarianLanguageCompetence, boolean englishAllowed, double hungarianSalaryRate, double englishSalaryRate, int radInvoiceClosingDay, int radPaymentDay, boolean inProbation) {
        this.id = id;
        this.reportSendingCode = reportSendingCode;
        this.creditPoints = creditPoints;
        this.creditValidity = creditValidity;
        this.lastCheck = lastCheck;
        this.defaultNormalAvailabilityHrsPerWeekDay = defaultNormalAvailabilityHrsPerWeekDay;
        this.defaultMaxAvailabilityHrsPerWeekDay = defaultMaxAvailabilityHrsPerWeekDay;
        this.defaultNormalAvailabilityHrsPerWeekendDay = defaultNormalAvailabilityHrsPerWeekendDay;
        this.defaultMaxAvailabilityHrsPerWeekendDay = defaultMaxAvailabilityHrsPerWeekendDay;
        this.workDemandPriority = workDemandPriority;
        this.englishLanguageCompetence = englishLanguageCompetence;
        this.hungarianLanguageCompetence = hungarianLanguageCompetence;
        this.englishAllowed = englishAllowed;
        this.hungarianSalaryRate = hungarianSalaryRate;
        this.englishSalaryRate = englishSalaryRate;
        this.radInvoiceClosingDay = radInvoiceClosingDay;
        this.radPaymentDay = radPaymentDay;
        this.inProbation = inProbation;
    }

    @Override
    public ITSRadiologist clone() throws CloneNotSupportedException {
        ITSRadiologist result = (ITSRadiologist) super.clone();
        result.setItsUser(itsUser.clone());
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportSendingCode() {
        return reportSendingCode;
    }

    public void setReportSendingCode(String reportSendingCode) {
        this.reportSendingCode = reportSendingCode;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Date getCreditValidity() {
        return creditValidity;
    }

    public void setCreditValidity(Date creditValidity) {
        this.creditValidity = creditValidity;
    }

    public Date getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(Date lastCheck) {
        this.lastCheck = lastCheck;
    }

    public int getDefaultNormalAvailabilityHrsPerWeekDay() {
        return defaultNormalAvailabilityHrsPerWeekDay;
    }

    public void setDefaultNormalAvailabilityHrsPerWeekDay(int defaultNormalAvailabilityHrsPerWeekDay) {
        this.defaultNormalAvailabilityHrsPerWeekDay = defaultNormalAvailabilityHrsPerWeekDay;
    }

    public int getDefaultMaxAvailabilityHrsPerWeekDay() {
        return defaultMaxAvailabilityHrsPerWeekDay;
    }

    public void setDefaultMaxAvailabilityHrsPerWeekDay(int defaultMaxAvailabilityHrsPerWeekDay) {
        this.defaultMaxAvailabilityHrsPerWeekDay = defaultMaxAvailabilityHrsPerWeekDay;
    }

    public int getDefaultNormalAvailabilityHrsPerWeekendDay() {
        return defaultNormalAvailabilityHrsPerWeekendDay;
    }

    public void setDefaultNormalAvailabilityHrsPerWeekendDay(int defaultNormalAvailabilityHrsPerWeekendDay) {
        this.defaultNormalAvailabilityHrsPerWeekendDay = defaultNormalAvailabilityHrsPerWeekendDay;
    }

    public int getDefaultMaxAvailabilityHrsPerWeekendDay() {
        return defaultMaxAvailabilityHrsPerWeekendDay;
    }

    public void setDefaultMaxAvailabilityHrsPerWeekendDay(int defaultMaxAvailabilityHrsPerWeekendDay) {
        this.defaultMaxAvailabilityHrsPerWeekendDay = defaultMaxAvailabilityHrsPerWeekendDay;
    }

    public int getWorkDemandPriority() {
        return workDemandPriority;
    }

    public void setWorkDemandPriority(int workDemandPriority) {
        this.workDemandPriority = workDemandPriority;
    }

    public int getEnglishLanguageCompetence() {
        return englishLanguageCompetence;
    }

    public void setEnglishLanguageCompetence(int englishLanguageCompetence) {
        this.englishLanguageCompetence = englishLanguageCompetence;
    }

    public int getHungarianLanguageCompetence() {
        return hungarianLanguageCompetence;
    }

    public void setHungarianLanguageCompetence(int hungarianLanguageCompetence) {
        this.hungarianLanguageCompetence = hungarianLanguageCompetence;
    }

    public boolean getEnglishAllowed() {
        return englishAllowed;
    }

    public void setEnglishAllowed(boolean englishAllowed) {
        this.englishAllowed = englishAllowed;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getHungarianSalaryRate() {
        return hungarianSalaryRate;
    }

    public void setHungarianSalaryRate(double hungarianSalaryRate) {
        this.hungarianSalaryRate = hungarianSalaryRate;
    }

    public double getEnglishSalaryRate() {
        return englishSalaryRate;
    }

    public void setEnglishSalaryRate(double englishSalaryRate) {
        this.englishSalaryRate = englishSalaryRate;
    }

    public int getRadInvoiceClosingDay() {
        return radInvoiceClosingDay;
    }

    public void setRadInvoiceClosingDay(int radInvoiceClosingDay) {
        this.radInvoiceClosingDay = radInvoiceClosingDay;
    }

    public int getRadPaymentDay() {
        return radPaymentDay;
    }

    public void setRadPaymentDay(int radPaymentDay) {
        this.radPaymentDay = radPaymentDay;
    }

    public boolean getInProbation() {
        return inProbation;
    }

    public void setInProbation(boolean inProbation) {
        this.inProbation = inProbation;
    }

    public Collection<WorkPlace> getWorkPlaceCollection() {
        return workPlaceCollection;
    }

    public void setWorkPlaceCollection(Collection<WorkPlace> workPlaceCollection) {
        this.workPlaceCollection = workPlaceCollection;
    }

    public Collection<SystemMessage> getSystemMessageCollection() {
        return systemMessageCollection;
    }

    public void setSystemMessageCollection(Collection<SystemMessage> systemMessageCollection) {
        this.systemMessageCollection = systemMessageCollection;
    }

    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public Collection<SuperVision> getSuperVisionCollection() {
        return superVisionCollection;
    }

    public void setSuperVisionCollection(Collection<SuperVision> superVisionCollection) {
        this.superVisionCollection = superVisionCollection;
    }

    public ITSUser getItsUser() {
        return itsUser;
    }

    public void setItsUser(ITSUser itsUser) {
        this.itsUser = itsUser;
    }

    public Collection<WorkScrutiny> getWorkScrutinyCollection() {
        return workScrutinyCollection;
    }

    public void setWorkScrutinyCollection(Collection<WorkScrutiny> workScrutinyCollection) {
        this.workScrutinyCollection = workScrutinyCollection;
    }

    public Collection<RegLicQualOwnership> getRegLicQualOwnershipCollection() {
        return regLicQualOwnershipCollection;
    }

    public void setRegLicQualOwnershipCollection(Collection<RegLicQualOwnership> regLicQualOwnershipCollection) {
        this.regLicQualOwnershipCollection = regLicQualOwnershipCollection;
    }

    public Collection<Consultation> getConsultationCollection() {
        return consultationCollection;
    }

    public void setConsultationCollection(Collection<Consultation> consultationCollection) {
        this.consultationCollection = consultationCollection;
    }

    public Collection<RadCompetence> getRadCompetenceCollection() {
        return radCompetenceCollection;
    }

    public void setRadCompetenceCollection(Collection<RadCompetence> radCompetenceCollection) {
        this.radCompetenceCollection = radCompetenceCollection;
    }

    public Collection<Reporting> getReportingCollection() {
        return reportingCollection;
    }

    public void setReportingCollection(Collection<Reporting> reportingCollection) {
        this.reportingCollection = reportingCollection;
    }

    public Collection<RadAvailability> getRadAvailabilityCollection() {
        return radAvailabilityCollection;
    }

    public void setRadAvailabilityCollection(Collection<RadAvailability> radAvailabilityCollection) {
        this.radAvailabilityCollection = radAvailabilityCollection;
    }

    public List<ITSRadiologistRoleAssignment> getItsRadiologistRoleAssignmentList() {
        return itsRadiologistRoleAssignmentList;
    }

    public void setItsRadiologistRoleAssignmentList(List<ITSRadiologistRoleAssignment> itsRadiologistRoleAssignmentList) {
        this.itsRadiologistRoleAssignmentList = itsRadiologistRoleAssignmentList;
    }

    public Collection<CompanyAssignment> getCompanyAssignmentCollection() {
        return companyAssignmentCollection;
    }

    public void setCompanyAssignmentCollection(Collection<CompanyAssignment> companyAssignmentCollection) {
        this.companyAssignmentCollection = companyAssignmentCollection;
    }

    public boolean isCanReport() {
        return canReport;
    }

    public void setCanReport(boolean canReport) {
        this.canReport = canReport;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ITSRadiologist)) {
            return false;
        }
        ITSRadiologist other = (ITSRadiologist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TDSRadiologist[id=" + id + "]";
    }
}
