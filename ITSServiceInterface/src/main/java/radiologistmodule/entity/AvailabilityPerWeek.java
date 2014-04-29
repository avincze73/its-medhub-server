/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import hospitalmodule.entity.HospitalContract;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "AvailabilityPerWeek")
@NamedQueries({
    @NamedQuery(name = "AvailabilityPerWeek.findAll", query = "SELECT a FROM AvailabilityPerWeek a"),
    @NamedQuery(name = "AvailabilityPerWeek.findById", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.id = :id"),
    @NamedQuery(name = "AvailabilityPerWeek.findBySunday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.sunday = :sunday"),
    @NamedQuery(name = "AvailabilityPerWeek.findByMonday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.monday = :monday"),
    @NamedQuery(name = "AvailabilityPerWeek.findByTuesday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.tuesday = :tuesday"),
    @NamedQuery(name = "AvailabilityPerWeek.findByWednesday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.wednesday = :wednesday"),
    @NamedQuery(name = "AvailabilityPerWeek.findByThursday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.thursday = :thursday"),
    @NamedQuery(name = "AvailabilityPerWeek.findByFriday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.friday = :friday"),
    @NamedQuery(name = "AvailabilityPerWeek.findBySaturday", query = "SELECT a FROM AvailabilityPerWeek a WHERE a.saturday = :saturday")})
public class AvailabilityPerWeek implements Serializable {
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "availabilityPerWeek")
    private HospitalContract hospitalContract;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "availabilityPerWeek1")
    private HospitalContract hospitalContract1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "availabilityPerWeek")
    private RadAvailability radAvailability;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "availabilityPerWeek1")
    private RadAvailability radAvailability1;

    public AvailabilityPerWeek() {
    }

    public AvailabilityPerWeek(Long id) {
        this.id = id;
    }

    public AvailabilityPerWeek(Long id, double sunday, double monday, double tuesday, double wednesday, double thursday, double friday, double saturday) {
        this.id = id;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
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

    public HospitalContract getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContract hospitalContract) {
        this.hospitalContract = hospitalContract;
    }

    public HospitalContract getHospitalContract1() {
        return hospitalContract1;
    }

    public void setHospitalContract1(HospitalContract hospitalContract1) {
        this.hospitalContract1 = hospitalContract1;
    }

    public RadAvailability getRadAvailability() {
        return radAvailability;
    }

    public void setRadAvailability(RadAvailability radAvailability) {
        this.radAvailability = radAvailability;
    }

    public RadAvailability getRadAvailability1() {
        return radAvailability1;
    }

    public void setRadAvailability1(RadAvailability radAvailability1) {
        this.radAvailability1 = radAvailability1;
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
        if (!(object instanceof AvailabilityPerWeek)) {
            return false;
        }
        AvailabilityPerWeek other = (AvailabilityPerWeek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AvailabilityPerWeek[id=" + id + "]";
    }

}
