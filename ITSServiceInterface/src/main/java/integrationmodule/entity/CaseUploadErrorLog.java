/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author itsadmin
 */
@Entity
@Table(name = "CaseUploadErrorLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaseUploadErrorLog.findAll", query = "SELECT c FROM CaseUploadErrorLog c"),
    @NamedQuery(name = "CaseUploadErrorLog.findById", query = "SELECT c FROM CaseUploadErrorLog c WHERE c.id = :id"),
    @NamedQuery(name = "CaseUploadErrorLog.findByAdded", query = "SELECT c FROM CaseUploadErrorLog c WHERE c.added = :added"),
    @NamedQuery(name = "CaseUploadErrorLog.findByExceptionName", query = "SELECT c FROM CaseUploadErrorLog c WHERE c.exceptionName = :exceptionName"),
    @NamedQuery(name = "CaseUploadErrorLog.findByExceptionMessage", query = "SELECT c FROM CaseUploadErrorLog c WHERE c.exceptionMessage = :exceptionMessage"),
    @NamedQuery(name = "CaseUploadErrorLog.findByStacktrace", query = "SELECT c FROM CaseUploadErrorLog c WHERE c.stacktrace = :stacktrace")})
public class CaseUploadErrorLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Size(max = 255)
    @Column(name = "exceptionName")
    private String exceptionName;
    @Size(max = 255)
    @Column(name = "exceptionMessage")
    private String exceptionMessage;
    @Size(max = 255)
    @Column(name = "stacktrace")
    private String stacktrace;
    @JoinColumn(name = "caseUploadLogId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CaseUploadLog caseUploadLog;

    public CaseUploadErrorLog() {
    }

    public CaseUploadErrorLog(Long id) {
        this.id = id;
    }

    public CaseUploadErrorLog(Long id, Date added) {
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

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public CaseUploadLog getCaseUploadLog() {
        return caseUploadLog;
    }

    public void setCaseUploadLog(CaseUploadLog caseUploadLog) {
        this.caseUploadLog = caseUploadLog;
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
        if (!(object instanceof CaseUploadErrorLog)) {
            return false;
        }
        CaseUploadErrorLog other = (CaseUploadErrorLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "its.gs.entity.CaseUploadErrorLog[ id=" + id + " ]";
    }
    
}
