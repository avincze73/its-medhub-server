/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author itsadmin
 */
@Entity
@Table(name = "CaseUploadLog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaseUploadLog.findAll", query = "SELECT c FROM CaseUploadLog c"),
    @NamedQuery(name = "CaseUploadLog.findById", query = "SELECT c FROM CaseUploadLog c WHERE c.id = :id"),
    @NamedQuery(name = "CaseUploadLog.findByUploadDate", query = "SELECT c FROM CaseUploadLog c WHERE c.uploadDate = :uploadDate"),
    @NamedQuery(name = "CaseUploadLog.findByUploadType", query = "SELECT c FROM CaseUploadLog c WHERE c.uploadType = :uploadType"),
    @NamedQuery(name = "CaseUploadLog.findBySucceeded", query = "SELECT c FROM CaseUploadLog c WHERE c.succeeded = :succeeded"),
    @NamedQuery(name = "CaseUploadLog.findByPatientId", query = "SELECT c FROM CaseUploadLog c WHERE c.patientId = :patientId"),
    @NamedQuery(name = "CaseUploadLog.findByPatientName", query = "SELECT c FROM CaseUploadLog c WHERE c.patientName = :patientName")})
public class CaseUploadLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uploadDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "uploadType")
    private String uploadType;
    @Column(name = "succeeded")
    private Boolean succeeded;
    @Size(max = 255)
    @Column(name = "patientId")
    private String patientId;
    @Size(max = 255)
    @Column(name = "patientName")
    private String patientName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseUploadLog")
    private List<CaseUploadErrorLog> caseUploadErrorLogList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caseUploadLog")
    private List<IntegrationHandledStudy> integrationHandledStudyList;

    public CaseUploadLog() {
    }

    public CaseUploadLog(Long id) {
        this.id = id;
    }

    public CaseUploadLog(Long id, Date uploadDate, String uploadType) {
        this.id = id;
        this.uploadDate = uploadDate;
        this.uploadType = uploadType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @XmlTransient
    public List<CaseUploadErrorLog> getCaseUploadErrorLogList() {
        return caseUploadErrorLogList;
    }

    public void setCaseUploadErrorLogList(List<CaseUploadErrorLog> caseUploadErrorLogList) {
        this.caseUploadErrorLogList = caseUploadErrorLogList;
    }

    @XmlTransient
    public List<IntegrationHandledStudy> getIntegrationHandledStudyList() {
        return integrationHandledStudyList;
    }

    public void setIntegrationHandledStudyList(List<IntegrationHandledStudy> integrationHandledStudyList) {
        this.integrationHandledStudyList = integrationHandledStudyList;
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
        if (!(object instanceof CaseUploadLog)) {
            return false;
        }
        CaseUploadLog other = (CaseUploadLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "its.gs.entity.CaseUploadLog[ id=" + id + " ]";
    }
    
}
