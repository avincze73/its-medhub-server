/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.entity;

import hospitalmodule.entity.HospitalContract;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "AvailabilityPerPeriod")
@NamedQueries({
    @NamedQuery(name = "AvailabilityPerPeriod.findAll", query = "SELECT a FROM AvailabilityPerPeriod a"),
    @NamedQuery(name = "AvailabilityPerPeriod.findById", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.id = :id"),
    @NamedQuery(name = "AvailabilityPerPeriod.findBySunday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.sunday = :sunday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByMonday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.monday = :monday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByTuesday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.tuesday = :tuesday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByWednesday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.wednesday = :wednesday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByThursday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.thursday = :thursday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByFriday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.friday = :friday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findBySaturday", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.saturday = :saturday"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByStarted", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.started = :started"),
    @NamedQuery(name = "AvailabilityPerPeriod.findByEnded", query = "SELECT a FROM AvailabilityPerPeriod a WHERE a.ended = :ended")})
public class AvailabilityPerPeriod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "sunday")
    private double sunday;
    @Basic(optional = false)
    @Column(name = "monday")
    private double monday;
    @Basic(optional = false)
    @Column(name = "tuesday")
    private double tuesday;
    @Basic(optional = false)
    @Column(name = "wednesday")
    private double wednesday;
    @Basic(optional = false)
    @Column(name = "thursday")
    private double thursday;
    @Basic(optional = false)
    @Column(name = "friday")
    private double friday;
    @Basic(optional = false)
    @Column(name = "saturday")
    private double saturday;
    @Basic(optional = false)
    @Column(name = "started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Basic(optional = false)
    @Column(name = "ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;

    public AvailabilityPerPeriod() {
    }

    public AvailabilityPerPeriod(Long id) {
        this.id = id;
    }

    public AvailabilityPerPeriod(Long id, double sunday, double monday, double tuesday, double wednesday, double thursday, double friday, double saturday, Date started, Date ended) {
        this.id = id;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.started = started;
        this.ended = ended;
    }

    public AvailabilityPerPeriod(double sunday, double monday, double tuesday, double wednesday, double thursday, double friday, double saturday, Date started, Date ended) {
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.started = started;
        this.ended = ended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSunday() {
        return sunday;
    }

    public void setSunday(double sunday) {
        this.sunday = sunday;
    }

    public double getMonday() {
        return monday;
    }

    public void setMonday(double monday) {
        this.monday = monday;
    }

    public double getTuesday() {
        return tuesday;
    }

    public void setTuesday(double tuesday) {
        this.tuesday = tuesday;
    }

    public double getWednesday() {
        return wednesday;
    }

    public void setWednesday(double wednesday) {
        this.wednesday = wednesday;
    }

    public double getThursday() {
        return thursday;
    }

    public void setThursday(double thursday) {
        this.thursday = thursday;
    }

    public double getFriday() {
        return friday;
    }

    public void setFriday(double friday) {
        this.friday = friday;
    }

    public double getSaturday() {
        return saturday;
    }

    public void setSaturday(double saturday) {
        this.saturday = saturday;
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
        if (!(object instanceof AvailabilityPerPeriod)) {
            return false;
        }
        AvailabilityPerPeriod other = (AvailabilityPerPeriod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AvailabilityPerPeriod[id=" + id + "]";
    }
}
