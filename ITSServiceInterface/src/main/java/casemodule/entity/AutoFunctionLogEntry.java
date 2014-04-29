/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

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
@Table(name = "AutoFunctionLogEntry")
@NamedQueries({
    @NamedQuery(name = "AutoFunctionLogEntry.findAll", query = "SELECT a FROM AutoFunctionLogEntry a"),
    @NamedQuery(name = "AutoFunctionLogEntry.findById", query = "SELECT a FROM AutoFunctionLogEntry a WHERE a.id = :id"),
    @NamedQuery(name = "AutoFunctionLogEntry.findByAdded", query = "SELECT a FROM AutoFunctionLogEntry a WHERE a.added = :added")})
public class AutoFunctionLogEntry implements Serializable {
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
    @JoinColumn(name = "autoFunctionTypeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AutoFunctionType autoFunctionType;

    public AutoFunctionLogEntry() {
    }

    public AutoFunctionLogEntry(Long id) {
        this.id = id;
    }

    public AutoFunctionLogEntry(Long id, Date added) {
        this.id = id;
        this.added = added;
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

    public AutoFunctionType getAutoFunctionType() {
        return autoFunctionType;
    }

    public void setAutoFunctionType(AutoFunctionType autoFunctionType) {
        this.autoFunctionType = autoFunctionType;
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
        if (!(object instanceof AutoFunctionLogEntry)) {
            return false;
        }
        AutoFunctionLogEntry other = (AutoFunctionLogEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AutoFunctionLogEntry[id=" + id + "]";
    }

}
