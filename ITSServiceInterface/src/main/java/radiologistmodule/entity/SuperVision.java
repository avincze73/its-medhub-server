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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "SuperVision")
@NamedQueries({
    @NamedQuery(name = "SuperVision.findAll", query = "SELECT s FROM SuperVision s"),
    @NamedQuery(name = "SuperVision.findById", query = "SELECT s FROM SuperVision s WHERE s.id = :id"),
    @NamedQuery(name = "SuperVision.findByStarted", query = "SELECT s FROM SuperVision s WHERE s.started = :started"),
    @NamedQuery(name = "SuperVision.findByEnded", query = "SELECT s FROM SuperVision s WHERE s.ended = :ended"),
    @NamedQuery(name = "SuperVision.findByAdded", query = "SELECT s FROM SuperVision s WHERE s.added = :added"),
    @NamedQuery(name = "SuperVision.findByActive", query = "SELECT s FROM SuperVision s WHERE s.active = :active")})
public class SuperVision implements Serializable {
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
    @Column(name = "ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "supervisorId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist supervisor;
    @JoinColumn(name = "supervisedId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist supervised;

    public SuperVision() {
    }

    public SuperVision(Long id) {
        this.id = id;
    }

    public SuperVision(Long id, Date started, Date added, boolean active) {
        this.id = id;
        this.started = started;
        this.added = added;
        this.active = active;
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

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ITSRadiologist getSupervised() {
        return supervised;
    }

    public void setSupervised(ITSRadiologist supervised) {
        this.supervised = supervised;
    }

    public ITSRadiologist getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(ITSRadiologist supervisor) {
        this.supervisor = supervisor;
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
        if (!(object instanceof SuperVision)) {
            return false;
        }
        SuperVision other = (SuperVision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SuperVision[id=" + id + "]";
    }

}
