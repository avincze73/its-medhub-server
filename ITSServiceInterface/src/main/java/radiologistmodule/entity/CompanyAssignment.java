/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

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
import radiologistmodule.entity.ITSRadiologist;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "CompanyAssignment")
@NamedQueries({
    @NamedQuery(name = "CompanyAssignment.findAll", query = "SELECT c FROM CompanyAssignment c"),
    @NamedQuery(name = "CompanyAssignment.findById", query = "SELECT c FROM CompanyAssignment c WHERE c.id = :id"),
    @NamedQuery(name = "CompanyAssignment.findByStarted", query = "SELECT c FROM CompanyAssignment c WHERE c.started = :started"),
    @NamedQuery(name = "CompanyAssignment.findByEnded", query = "SELECT c FROM CompanyAssignment c WHERE c.ended = :ended"),
    @NamedQuery(name = "CompanyAssignment.findByActive", query = "SELECT c FROM CompanyAssignment c WHERE c.active = :active")})
public class CompanyAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "started")
    @Temporal(TemporalType.DATE)
    private Date started;
    @Column(name = "ended")
    @Temporal(TemporalType.DATE)
    private Date ended;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public CompanyAssignment() {
    }

    public CompanyAssignment(Long id) {
        this.id = id;
    }

    public CompanyAssignment(Long id, Date started, boolean active) {
        this.id = id;
        this.started = started;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        if (!(object instanceof CompanyAssignment)) {
            return false;
        }
        CompanyAssignment other = (CompanyAssignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CompanyAssignment[id=" + id + "]";
    }

}
