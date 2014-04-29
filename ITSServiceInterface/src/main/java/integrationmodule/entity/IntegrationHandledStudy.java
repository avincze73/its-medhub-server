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
@Table(name = "IntegrationHandledStudy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IntegrationHandledStudy.findAll", query = "SELECT i FROM IntegrationHandledStudy i"),
    @NamedQuery(name = "IntegrationHandledStudy.findById", query = "SELECT i FROM IntegrationHandledStudy i WHERE i.id = :id"),
    @NamedQuery(name = "IntegrationHandledStudy.findByInstanceUid", query = "SELECT i FROM IntegrationHandledStudy i WHERE i.instanceUid = :instanceUid"),
    @NamedQuery(name = "IntegrationHandledStudy.findByStudyDateTime", query = "SELECT i FROM IntegrationHandledStudy i WHERE i.studyDateTime = :studyDateTime"),
    @NamedQuery(name = "IntegrationHandledStudy.findBySucceeded", query = "SELECT i FROM IntegrationHandledStudy i WHERE i.succeeded = :succeeded"),
    @NamedQuery(name = "IntegrationHandledStudy.findByDataSetCount", query = "SELECT i FROM IntegrationHandledStudy i WHERE i.dataSetCount = :dataSetCount")})
public class IntegrationHandledStudy implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "instanceUid")
    private String instanceUid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "studyDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date studyDateTime;
    @Column(name = "succeeded")
    private Boolean succeeded;
    @Column(name = "dataSetCount")
    private Integer dataSetCount;
    @JoinColumn(name = "caseUploadLogId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CaseUploadLog caseUploadLog;

    public IntegrationHandledStudy() {
    }

    public IntegrationHandledStudy(Long id) {
        this.id = id;
    }

    public IntegrationHandledStudy(Long id, String instanceUid, Date studyDateTime) {
        this.id = id;
        this.instanceUid = instanceUid;
        this.studyDateTime = studyDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public Date getStudyDateTime() {
        return studyDateTime;
    }

    public void setStudyDateTime(Date studyDateTime) {
        this.studyDateTime = studyDateTime;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Integer getDataSetCount() {
        return dataSetCount;
    }

    public void setDataSetCount(Integer dataSetCount) {
        this.dataSetCount = dataSetCount;
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
        if (!(object instanceof IntegrationHandledStudy)) {
            return false;
        }
        IntegrationHandledStudy other = (IntegrationHandledStudy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "its.gs.entity.IntegrationHandledStudy[ id=" + id + " ]";
    }
    
}
