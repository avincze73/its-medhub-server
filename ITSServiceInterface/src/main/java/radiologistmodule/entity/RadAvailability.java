/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import radiologistmodule.entity.ITSRadiologist;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "RadAvailability")
@NamedQueries({
    @NamedQuery(name = "RadAvailability.findAll", query = "SELECT r FROM RadAvailability r"),
    @NamedQuery(name = "RadAvailability.findById", query = "SELECT r FROM RadAvailability r WHERE r.id = :id"),
    @NamedQuery(name = "RadAvailability.findByFromDate", query = "SELECT r FROM RadAvailability r WHERE r.fromDate = :fromDate"),
    @NamedQuery(name = "RadAvailability.findByToDate", query = "SELECT r FROM RadAvailability r WHERE r.toDate = :toDate"),
    @NamedQuery(name = "RadAvailability.findByAdded", query = "SELECT r FROM RadAvailability r WHERE r.added = :added"),
    @NamedQuery(name = "RadAvailability.findByValid", query = "SELECT r FROM RadAvailability r WHERE r.valid = :valid"),
    @NamedQuery(name = "RadAvailability.findByConfirmedByPM", query = "SELECT r FROM RadAvailability r WHERE r.confirmedByPM = :confirmedByPM"),
    @NamedQuery(name = "RadAvailability.findByConfirmedByRad", query = "SELECT r FROM RadAvailability r WHERE r.confirmedByRad = :confirmedByRad")})
public class RadAvailability implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "fromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @Column(name = "toDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Basic(optional = false)
    @Column(name = "valid")
    private boolean valid;
    @Basic(optional = false)
    @Column(name = "confirmedByPM")
    private boolean confirmedByPM;
    @Basic(optional = false)
    @Column(name = "confirmedByRad")
    private boolean confirmedByRad;
    @JoinColumn(name = "maxAvailabilityPerWeekkId", referencedColumnName = "id")
    @OneToOne(optional = false)
    private AvailabilityPerWeek availabilityPerWeek;
    @JoinColumn(name = "normalAvailabilityPerWeekkId", referencedColumnName = "id")
    @OneToOne(optional = false)
    private AvailabilityPerWeek availabilityPerWeek1;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public RadAvailability() {
    }

    public RadAvailability(Long id) {
        this.id = id;
    }

    public RadAvailability(Long id, Date fromDate, Date toDate, Date added, boolean valid, boolean confirmedByPM, boolean confirmedByRad) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.added = added;
        this.valid = valid;
        this.confirmedByPM = confirmedByPM;
        this.confirmedByRad = confirmedByRad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean getConfirmedByPM() {
        return confirmedByPM;
    }

    public void setConfirmedByPM(boolean confirmedByPM) {
        this.confirmedByPM = confirmedByPM;
    }

    public boolean getConfirmedByRad() {
        return confirmedByRad;
    }

    public void setConfirmedByRad(boolean confirmedByRad) {
        this.confirmedByRad = confirmedByRad;
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

    public ITSRadiologist getTDSRadiologist() {
        return tDSRadiologist;
    }

    public void setTDSRadiologist(ITSRadiologist tDSRadiologist) {
        this.tDSRadiologist = tDSRadiologist;
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
        if (!(object instanceof RadAvailability)) {
            return false;
        }
        RadAvailability other = (RadAvailability) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RadAvailability[id=" + id + "]";
    }

}
