/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

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
@Table(name = "DataProcLog")
@NamedQueries({
    @NamedQuery(name = "DataProcLog.findAll", query = "SELECT d FROM DataProcLog d"),
    @NamedQuery(name = "DataProcLog.findById", query = "SELECT d FROM DataProcLog d WHERE d.id = :id"),
    @NamedQuery(name = "DataProcLog.findByStarted", query = "SELECT d FROM DataProcLog d WHERE d.started = :started")})
public class DataProcLog implements Serializable {
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
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dataProcLog")
    private TDSCase tDSCase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dataProcLog")
    private Collection<DataProcLogEntry> dataProcLogEntryCollection;

    public DataProcLog() {
    }

    public DataProcLog(Long id) {
        this.id = id;
    }

    public DataProcLog(Long id, Date started) {
        this.id = id;
        this.started = started;
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

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
    }

    public Collection<DataProcLogEntry> getDataProcLogEntryCollection() {
        return dataProcLogEntryCollection;
    }

    public void setDataProcLogEntryCollection(Collection<DataProcLogEntry> dataProcLogEntryCollection) {
        this.dataProcLogEntryCollection = dataProcLogEntryCollection;
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
        if (!(object instanceof DataProcLog)) {
            return false;
        }
        DataProcLog other = (DataProcLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DataProcLog[id=" + id + "]";
    }

}
