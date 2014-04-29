/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.entity;

import hospitalmodule.entity.HospitalContract;
import radiologistmodule.entity.CaseLevelInvoiceElement;
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
@Table(name = "MonthlyInvoice")
@NamedQueries({
    @NamedQuery(name = "MonthlyInvoice.findAll", query = "SELECT m FROM MonthlyInvoice m"),
    @NamedQuery(name = "MonthlyInvoice.findById", query = "SELECT m FROM MonthlyInvoice m WHERE m.id = :id"),
    @NamedQuery(name = "MonthlyInvoice.findByStarted", query = "SELECT m FROM MonthlyInvoice m WHERE m.started = :started"),
    @NamedQuery(name = "MonthlyInvoice.findByEnded", query = "SELECT m FROM MonthlyInvoice m WHERE m.ended = :ended"),
    @NamedQuery(name = "MonthlyInvoice.findByCalculation", query = "SELECT m FROM MonthlyInvoice m WHERE m.calculation = :calculation"),
    @NamedQuery(name = "MonthlyInvoice.findBySentToHospital", query = "SELECT m FROM MonthlyInvoice m WHERE m.sentToHospital = :sentToHospital"),
    @NamedQuery(name = "MonthlyInvoice.findByPaid", query = "SELECT m FROM MonthlyInvoice m WHERE m.paid = :paid"),
    @NamedQuery(name = "MonthlyInvoice.findByInvoiceClosingSum", query = "SELECT m FROM MonthlyInvoice m WHERE m.invoiceClosingSum = :invoiceClosingSum"),
    @NamedQuery(name = "MonthlyInvoice.findByStatement", query = "SELECT m FROM MonthlyInvoice m WHERE m.statement = :statement")})
public class MonthlyInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Basic(optional = false)
    @Column(name = "ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @Column(name = "calculation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calculation;
    @Column(name = "sentToHospital")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentToHospital;
    @Column(name = "paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paid;
    @Column(name = "invoiceClosingSum")
    private Double invoiceClosingSum;
    @Column(name = "statement")
    private String statement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monthlyInvoice")
    private Collection<CaseLevelInvoiceElement> caseLevelInvoiceElementCollection;
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;

    public MonthlyInvoice() {
    }

    public MonthlyInvoice(Long id) {
        this.id = id;
    }

    public MonthlyInvoice(Long id, Date started, Date ended) {
        this.id = id;
        this.started = started;
        this.ended = ended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public Date getCalculation() {
        return calculation;
    }

    public void setCalculation(Date calculation) {
        this.calculation = calculation;
    }

    public Date getSentToHospital() {
        return sentToHospital;
    }

    public void setSentToHospital(Date sentToHospital) {
        this.sentToHospital = sentToHospital;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public Double getInvoiceClosingSum() {
        return invoiceClosingSum;
    }

    public void setInvoiceClosingSum(Double invoiceClosingSum) {
        this.invoiceClosingSum = invoiceClosingSum;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Collection<CaseLevelInvoiceElement> getCaseLevelInvoiceElementCollection() {
        return caseLevelInvoiceElementCollection;
    }

    public void setCaseLevelInvoiceElementCollection(Collection<CaseLevelInvoiceElement> caseLevelInvoiceElementCollection) {
        this.caseLevelInvoiceElementCollection = caseLevelInvoiceElementCollection;
    }

    public HospitalContract getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContract hospitalContract) {
        this.hospitalContract = hospitalContract;
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
        if (!(object instanceof MonthlyInvoice)) {
            return false;
        }
        MonthlyInvoice other = (MonthlyInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MonthlyInvoice[id=" + id + "]";
    }

}
