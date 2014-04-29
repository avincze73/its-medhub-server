/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import hospitalmodule.entity.Hospital;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "WorkPlace")
@NamedQueries({
    @NamedQuery(name = "WorkPlace.findAll", query = "SELECT w FROM WorkPlace w"),
    @NamedQuery(name = "WorkPlace.findById", query = "SELECT w FROM WorkPlace w WHERE w.id = :id"),
    @NamedQuery(name = "WorkPlace.findByFromDate", query = "SELECT w FROM WorkPlace w WHERE w.fromDate = :fromDate"),
    @NamedQuery(name = "WorkPlace.findByToDate", query = "SELECT w FROM WorkPlace w WHERE w.toDate = :toDate"),
    @NamedQuery(name = "WorkPlace.findByAdded", query = "SELECT w FROM WorkPlace w WHERE w.added = :added")})
public class WorkPlace implements Serializable {
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
    @Column(name = "toDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hospital hospital;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public WorkPlace() {
    }

    public WorkPlace(Long id) {
        this.id = id;
    }

    public WorkPlace(Long id, Date fromDate, Date added) {
        this.id = id;
        this.fromDate = fromDate;
        this.added = added;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
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
        if (!(object instanceof WorkPlace)) {
            return false;
        }
        WorkPlace other = (WorkPlace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkPlace[id=" + id + "]";
    }

}
