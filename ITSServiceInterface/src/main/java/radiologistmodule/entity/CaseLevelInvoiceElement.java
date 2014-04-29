/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.entity;

import casemodule.entity.TDSCase;
import casemodule.entity.StudyLevelInvoiceElement;
import hospitalmodule.entity.MonthlyInvoice;
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
@Table(name = "CaseLevelInvoiceElement")
@NamedQueries({
    @NamedQuery(name = "CaseLevelInvoiceElement.findAll", query = "SELECT c FROM CaseLevelInvoiceElement c"),
    @NamedQuery(name = "CaseLevelInvoiceElement.findById", query = "SELECT c FROM CaseLevelInvoiceElement c WHERE c.id = :id"),
    @NamedQuery(name = "CaseLevelInvoiceElement.findBySumValue", query = "SELECT c FROM CaseLevelInvoiceElement c WHERE c.sumValue = :sumValue"),
    @NamedQuery(name = "CaseLevelInvoiceElement.findByCaseReceived", query = "SELECT c FROM CaseLevelInvoiceElement c WHERE c.caseReceived = :caseReceived"),
    @NamedQuery(name = "CaseLevelInvoiceElement.findByConsulted", query = "SELECT c FROM CaseLevelInvoiceElement c WHERE c.consulted = :consulted")})
public class CaseLevelInvoiceElement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "sumValue")
    private double sumValue;
    @Basic(optional = false)
    @Column(name = "caseReceived")
    @Temporal(TemporalType.TIMESTAMP)
    private Date caseReceived;
    @Basic(optional = false)
    @Column(name = "reportSent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportSent;
    @Basic(optional = false)
    @Column(name = "reportBecameAvailable")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportBecameAvailable;
    @Basic(optional = false)
    @Column(name = "consulted")
    private int consulted;
    @Basic(optional = false)
    @Column(name = "workloadOnDayBeforeThis")
    private double workloadOnDayBeforeThis;
    @Basic(optional = false)
    @Column(name = "normalWorkload")
    private double normalWorkload;
    @Basic(optional = false)
    @Column(name = "paygWorkload")
    private double paygWorkload;
    @Basic(optional = false)
    @Column(name = "extraWorkload")
    private double extraWorkload;
    @Basic(optional = false)
    @Column(name = "estimatedWorkTime")
    private double estimatedWorkTime;
    @Basic(optional = false)
    @Column(name = "workTime")
    private double workTime;
    @Basic(optional = false)
    @Column(name = "workloadCategory")
    private String workloadCategory;
    @Basic(optional = false)
    @Column(name = "allowedReportingTime")
    private double allowedReportingTime;
    @Basic(optional = false)
    @Column(name = "reportingTime")
    private double reportingTime;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;
    @JoinColumn(name = "monthlyInvoiceId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MonthlyInvoice monthlyInvoice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseLevelInvoiceElement")
    private Collection<StudyLevelInvoiceElement> studyLevelInvoiceElementCollection;

    public CaseLevelInvoiceElement() {
    }

    public CaseLevelInvoiceElement(Long id) {
        this.id = id;
    }

    public CaseLevelInvoiceElement(Long id, double sumValue, Date caseReceived, Date reportSent, int consulted) {
        this.id = id;
        this.sumValue = sumValue;
        this.caseReceived = caseReceived;
        this.reportSent = reportSent;
        this.consulted = consulted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSumValue() {
        return sumValue;
    }

    public void setSumValue(double sumValue) {
        this.sumValue = sumValue;
    }

    public Date getCaseReceived() {
        return caseReceived;
    }

    public void setCaseReceived(Date caseReceived) {
        this.caseReceived = caseReceived;
    }

    public Date getReportSent() {
        return reportSent;
    }

    public void setReportSent(Date reportSent) {
        this.reportSent = reportSent;
    }

    public int getConsulted() {
        return consulted;
    }

    public void setConsulted(int consulted) {
        this.consulted = consulted;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
    }

    public MonthlyInvoice getMonthlyInvoice() {
        return monthlyInvoice;
    }

    public void setMonthlyInvoice(MonthlyInvoice monthlyInvoice) {
        this.monthlyInvoice = monthlyInvoice;
    }

    public Collection<StudyLevelInvoiceElement> getStudyLevelInvoiceElementCollection() {
        return studyLevelInvoiceElementCollection;
    }

    public void setStudyLevelInvoiceElementCollection(Collection<StudyLevelInvoiceElement> studyLevelInvoiceElementCollection) {
        this.studyLevelInvoiceElementCollection = studyLevelInvoiceElementCollection;
    }

    public double getAllowedReportingTime() {
        return allowedReportingTime;
    }

    public void setAllowedReportingTime(double allowedReportingTime) {
        this.allowedReportingTime = allowedReportingTime;
    }

    public double getEstimatedWorkTime() {
        return estimatedWorkTime;
    }

    public void setEstimatedWorkTime(double estimatedWorkTime) {
        this.estimatedWorkTime = estimatedWorkTime;
    }

    public double getExtraWorkload() {
        return extraWorkload;
    }

    public void setExtraWorkload(double extraWorkload) {
        this.extraWorkload = extraWorkload;
    }

    public double getNormalWorkload() {
        return normalWorkload;
    }

    public void setNormalWorkload(double normalWorkload) {
        this.normalWorkload = normalWorkload;
    }

    public double getPaygWorkload() {
        return paygWorkload;
    }

    public void setPaygWorkload(double paygWorkload) {
        this.paygWorkload = paygWorkload;
    }

    public Date getReportBecameAvailable() {
        return reportBecameAvailable;
    }

    public void setReportBecameAvailable(Date reportBecameAvailable) {
        this.reportBecameAvailable = reportBecameAvailable;
    }

    public double getReportingTime() {
        return reportingTime;
    }

    public void setReportingTime(double reportingTime) {
        this.reportingTime = reportingTime;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public String getWorkloadCategory() {
        return workloadCategory;
    }

    public void setWorkloadCategory(String workloadCategory) {
        this.workloadCategory = workloadCategory;
    }

    public double getWorkloadOnDayBeforeThis() {
        return workloadOnDayBeforeThis;
    }

    public void setWorkloadOnDayBeforeThis(double workloadOnDayBeforeThis) {
        this.workloadOnDayBeforeThis = workloadOnDayBeforeThis;
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
        if (!(object instanceof CaseLevelInvoiceElement)) {
            return false;
        }
        CaseLevelInvoiceElement other = (CaseLevelInvoiceElement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CaseLevelInvoiceElement[id=" + id + "]";
    }
}
