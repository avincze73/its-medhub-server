/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.entity;

import radiologistmodule.entity.AvailabilityPerPeriod;
import radiologistmodule.entity.AvailabilityPerWeek;
import masterdatamodule.entity.Currency;
import radiologistmodule.entity.WorkBandTable;
import casemodule.entity.TDSCase;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "HospitalContract")
@NamedQueries({
    @NamedQuery(name = "HospitalContract.findAll", query = "SELECT h FROM HospitalContract h"),
    @NamedQuery(name = "HospitalContract.findById", query = "SELECT h FROM HospitalContract h WHERE h.id = :id"),
    @NamedQuery(name = "HospitalContract.findByContractCode", query = "SELECT h FROM HospitalContract h WHERE h.contractCode = :contractCode"),
    @NamedQuery(name = "HospitalContract.findBySignedDate", query = "SELECT h FROM HospitalContract h WHERE h.signedDate = :signedDate"),
    @NamedQuery(name = "HospitalContract.findByStartDateTime", query = "SELECT h FROM HospitalContract h WHERE h.startDateTime = :startDateTime"),
    @NamedQuery(name = "HospitalContract.findByLastCaseAcceptanceDateTime", query = "SELECT h FROM HospitalContract h WHERE h.lastCaseAcceptanceDateTime = :lastCaseAcceptanceDateTime"),
    @NamedQuery(name = "HospitalContract.findByIntendedEndDateTime", query = "SELECT h FROM HospitalContract h WHERE h.intendedEndDateTime = :intendedEndDateTime"),
    @NamedQuery(name = "HospitalContract.findByStopDateTime", query = "SELECT h FROM HospitalContract h WHERE h.stopDateTime = :stopDateTime"),
    @NamedQuery(name = "HospitalContract.findByActive", query = "SELECT h FROM HospitalContract h WHERE h.active = :active"),
    @NamedQuery(name = "HospitalContract.findBySignersName", query = "SELECT h FROM HospitalContract h WHERE h.signersName = :signersName"),
    @NamedQuery(name = "HospitalContract.findByNormalWorkTimeDays", query = "SELECT h FROM HospitalContract h WHERE h.normalWorkTimeDays = :normalWorkTimeDays"),
    @NamedQuery(name = "HospitalContract.findByWorkTimeDaysWithConsultation", query = "SELECT h FROM HospitalContract h WHERE h.workTimeDaysWithConsultation = :workTimeDaysWithConsultation"),
    @NamedQuery(name = "HospitalContract.findByCredit", query = "SELECT h FROM HospitalContract h WHERE h.credit = :credit"),
    @NamedQuery(name = "HospitalContract.findByInvoicePeriodDay", query = "SELECT h FROM HospitalContract h WHERE h.invoicePeriodDay = :invoicePeriodDay"),
    @NamedQuery(name = "HospitalContract.findByInvoiceProductionDay", query = "SELECT h FROM HospitalContract h WHERE h.invoiceProductionDay = :invoiceProductionDay"),
    @NamedQuery(name = "HospitalContract.findByAdminFeeForCheckingUselessCase", query = "SELECT h FROM HospitalContract h WHERE h.adminFeeForCheckingUselessCase = :adminFeeForCheckingUselessCase"),
    @NamedQuery(name = "HospitalContract.findByAdminFeeForManuallyEnteringData", query = "SELECT h FROM HospitalContract h WHERE h.adminFeeForManuallyEnteringData = :adminFeeForManuallyEnteringData"),
    @NamedQuery(name = "HospitalContract.findByLateReportingMultiplier", query = "SELECT h FROM HospitalContract h WHERE h.lateReportingMultiplier = :lateReportingMultiplier")})
public class HospitalContract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "contractCode")
    private String contractCode;
    @Basic(optional = false)
    @Column(name = "signedDate")
    @Temporal(TemporalType.DATE)
    private Date signedDate;
    @Basic(optional = false)
    @Column(name = "startDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    @Basic(optional = false)
    @Column(name = "lastCaseAcceptanceDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastCaseAcceptanceDateTime;
    @Basic(optional = false)
    @Column(name = "intendedEndDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date intendedEndDateTime;
    @Column(name = "stopDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stopDateTime;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "signersName")
    private String signersName;
    @Basic(optional = false)
    @Column(name = "normalWorkTimeDays")
    private double normalWorkTimeDays;
    @Basic(optional = false)
    @Column(name = "workTimeDaysWithConsultation")
    private double workTimeDaysWithConsultation;
    @Basic(optional = false)
    @Column(name = "normalClosenessToDeadlineDaysHosp")
    private double normalClosenessToDeadlineDaysHosp;
    @Basic(optional = false)
    @Column(name = "closenessToDeadlineDaysWithConsHosp")
    private double closenessToDeadlineDaysWithConsHosp;
    @Basic(optional = false)
    @Column(name = "credit")
    private int credit;
    @Basic(optional = false)
    @Column(name = "invoicePeriodDay")
    private int invoicePeriodDay;
    @Basic(optional = false)
    @Column(name = "invoiceProductionDay")
    private int invoiceProductionDay;
    @Basic(optional = false)
    @Column(name = "adminFeeForCheckingUselessCase")
    private double adminFeeForCheckingUselessCase;
    @Basic(optional = false)
    @Column(name = "adminFeeForManuallyEnteringData")
    private double adminFeeForManuallyEnteringData;
    @Column(name = "lateReportingMultiplier")
    private Double lateReportingMultiplier;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<TDSCase> tDSCaseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<OptionAssignment> optionAssignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<MonthlyInvoice> monthlyInvoiceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<WorkBandTable> workBandTableCollection;
    @JoinColumn(name = "currencyId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Currency currency;
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hospital hospital;
    @JoinColumn(name = "maxRequiredAvailabilityPerWeekId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private AvailabilityPerWeek availabilityPerWeek;
    @JoinColumn(name = "minRequiredAvailabilityPerWeekId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private AvailabilityPerWeek availabilityPerWeek1;
    @JoinColumn(name = "contractTypeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ContractType contractType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<ContactPersonAssignment> contactPersonAssignmentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalContract")
    private Collection<AvailabilityPerPeriod> availabilityPerPeriodCollection;

    public HospitalContract() {
    }

    public HospitalContract(Long id) {
        this.id = id;
    }

    public HospitalContract(Long id, Date signedDate, Date startDateTime, Date lastCaseAcceptanceDateTime, Date intendedEndDateTime, boolean active, String signersName, double normalWorkTimeDays, double workTimeDaysWithConsultation, double normalClosenessToDeadlineDaysHosp, double closenessToDeadlineDaysWithConsHosp, int credit, int invoicePeriodDay, int invoiceProductionDay, double adminFeeForCheckingUselessCase, double adminFeeForManuallyEnteringData) {
        this.id = id;
        this.signedDate = signedDate;
        this.startDateTime = startDateTime;
        this.lastCaseAcceptanceDateTime = lastCaseAcceptanceDateTime;
        this.intendedEndDateTime = intendedEndDateTime;
        this.active = active;
        this.signersName = signersName;
        this.normalWorkTimeDays = normalWorkTimeDays;
        this.workTimeDaysWithConsultation = workTimeDaysWithConsultation;
        this.normalClosenessToDeadlineDaysHosp = normalClosenessToDeadlineDaysHosp;
        this.closenessToDeadlineDaysWithConsHosp = closenessToDeadlineDaysWithConsHosp;
        this.credit = credit;
        this.invoicePeriodDay = invoicePeriodDay;
        this.invoiceProductionDay = invoiceProductionDay;
        this.adminFeeForCheckingUselessCase = adminFeeForCheckingUselessCase;
        this.adminFeeForManuallyEnteringData = adminFeeForManuallyEnteringData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Date getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(Date signedDate) {
        this.signedDate = signedDate;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getLastCaseAcceptanceDateTime() {
        return lastCaseAcceptanceDateTime;
    }

    public void setLastCaseAcceptanceDateTime(Date lastCaseAcceptanceDateTime) {
        this.lastCaseAcceptanceDateTime = lastCaseAcceptanceDateTime;
    }

    public Date getIntendedEndDateTime() {
        return intendedEndDateTime;
    }

    public void setIntendedEndDateTime(Date intendedEndDateTime) {
        this.intendedEndDateTime = intendedEndDateTime;
    }

    public Date getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(Date stopDateTime) {
        this.stopDateTime = stopDateTime;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getSignersName() {
        return signersName;
    }

    public void setSignersName(String signersName) {
        this.signersName = signersName;
    }

    public double getNormalWorkTimeDays() {
        return normalWorkTimeDays;
    }

    public void setNormalWorkTimeDays(double normalWorkTimeDays) {
        this.normalWorkTimeDays = normalWorkTimeDays;
    }

    public double getWorkTimeDaysWithConsultation() {
        return workTimeDaysWithConsultation;
    }

    public void setWorkTimeDaysWithConsultation(double workTimeDaysWithConsultation) {
        this.workTimeDaysWithConsultation = workTimeDaysWithConsultation;
    }

    public double getNormalClosenessToDeadlineDaysHosp() {
        return normalClosenessToDeadlineDaysHosp;
    }

    public void setNormalClosenessToDeadlineDaysHosp(double normalClosenessToDeadlineDaysHosp) {
        this.normalClosenessToDeadlineDaysHosp = normalClosenessToDeadlineDaysHosp;
    }

    public double getClosenessToDeadlineDaysWithConsHosp() {
        return closenessToDeadlineDaysWithConsHosp;
    }

    public void setClosenessToDeadlineDaysWithConsHosp(double closenessToDeadlineDaysWithConsHosp) {
        this.closenessToDeadlineDaysWithConsHosp = closenessToDeadlineDaysWithConsHosp;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getInvoicePeriodDay() {
        return invoicePeriodDay;
    }

    public void setInvoicePeriodDay(int invoicePeriodDay) {
        this.invoicePeriodDay = invoicePeriodDay;
    }

    public int getInvoiceProductionDay() {
        return invoiceProductionDay;
    }

    public void setInvoiceProductionDay(int invoiceProductionDay) {
        this.invoiceProductionDay = invoiceProductionDay;
    }

    public double getAdminFeeForCheckingUselessCase() {
        return adminFeeForCheckingUselessCase;
    }

    public void setAdminFeeForCheckingUselessCase(double adminFeeForCheckingUselessCase) {
        this.adminFeeForCheckingUselessCase = adminFeeForCheckingUselessCase;
    }

    public double getAdminFeeForManuallyEnteringData() {
        return adminFeeForManuallyEnteringData;
    }

    public void setAdminFeeForManuallyEnteringData(double adminFeeForManuallyEnteringData) {
        this.adminFeeForManuallyEnteringData = adminFeeForManuallyEnteringData;
    }

    public Double getLateReportingMultiplier() {
        return lateReportingMultiplier;
    }

    public void setLateReportingMultiplier(Double lateReportingMultiplier) {
        this.lateReportingMultiplier = lateReportingMultiplier;
    }

    public Collection<TDSCase> getTDSCaseCollection() {
        return tDSCaseCollection;
    }

    public void setTDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
        this.tDSCaseCollection = tDSCaseCollection;
    }

    public Collection<OptionAssignment> getOptionAssignmentCollection() {
        return optionAssignmentCollection;
    }

    public void setOptionAssignmentCollection(Collection<OptionAssignment> optionAssignmentCollection) {
        this.optionAssignmentCollection = optionAssignmentCollection;
    }

    public Collection<MonthlyInvoice> getMonthlyInvoiceCollection() {
        return monthlyInvoiceCollection;
    }

    public void setMonthlyInvoiceCollection(Collection<MonthlyInvoice> monthlyInvoiceCollection) {
        this.monthlyInvoiceCollection = monthlyInvoiceCollection;
    }

    public Collection<WorkBandTable> getWorkBandTableCollection() {
        return workBandTableCollection;
    }

    public void setWorkBandTableCollection(Collection<WorkBandTable> workBandTableCollection) {
        this.workBandTableCollection = workBandTableCollection;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public AvailabilityPerWeek getAvailabilityPerWeek() {
        return availabilityPerWeek;
    }

    public void setAvailabilityPerWeek(AvailabilityPerWeek availabilityPerWeek) {
        this.availabilityPerWeek = availabilityPerWeek;
    }

    public AvailabilityPerWeek getAvailabilityPerWeek1() {
        return availabilityPerWeek1;
    }

    public void setAvailabilityPerWeek1(AvailabilityPerWeek availabilityPerWeek1) {
        this.availabilityPerWeek1 = availabilityPerWeek1;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Collection<ContactPersonAssignment> getContactPersonAssignmentCollection() {
        return contactPersonAssignmentCollection;
    }

    public void setContactPersonAssignmentCollection(Collection<ContactPersonAssignment> contactPersonAssignmentCollection) {
        this.contactPersonAssignmentCollection = contactPersonAssignmentCollection;
    }

    public Collection<AvailabilityPerPeriod> getAvailabilityPerPeriodCollection() {
        return availabilityPerPeriodCollection;
    }

    public void setAvailabilityPerPeriodCollection(Collection<AvailabilityPerPeriod> availabilityPerPeriodCollection) {
        this.availabilityPerPeriodCollection = availabilityPerPeriodCollection;
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
        if (!(object instanceof HospitalContract)) {
            return false;
        }
        HospitalContract other = (HospitalContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HospitalContract[id=" + id + "]";
    }
}
