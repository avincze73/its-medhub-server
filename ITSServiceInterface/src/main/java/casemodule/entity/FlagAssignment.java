/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import masterdatamodule.entity.CaseFlag;
import casemodule.entity.TDSCase;
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
@Table(name = "FlagAssignment")
@NamedQueries({
    @NamedQuery(name = "FlagAssignment.findAll", query = "SELECT f FROM FlagAssignment f"),
    @NamedQuery(name = "FlagAssignment.findById", query = "SELECT f FROM FlagAssignment f WHERE f.id = :id"),
    @NamedQuery(name = "FlagAssignment.findByAdded", query = "SELECT f FROM FlagAssignment f WHERE f.added = :added"),
    @NamedQuery(name = "FlagAssignment.findByActive", query = "SELECT f FROM FlagAssignment f WHERE f.active = :active")})
public class FlagAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @JoinColumn(name = "caseFlagId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CaseFlag caseFlag;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;

    public FlagAssignment() {
    }

    public FlagAssignment(Long id) {
        this.id = id;
    }

    public FlagAssignment(Long id, Date added, boolean active) {
        this.id = id;
        this.added = added;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CaseFlag getCaseFlag() {
        return caseFlag;
    }

    public void setCaseFlag(CaseFlag caseFlag) {
        this.caseFlag = caseFlag;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
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
        if (!(object instanceof FlagAssignment)) {
            return false;
        }
        FlagAssignment other = (FlagAssignment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FlagAssignment[id=" + id + "]";
    }

}
