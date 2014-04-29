/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author itsadmin
 */
@Entity
@Table(name = "CaseUploadAttribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaseUploadAttribute.findAll", query = "SELECT c FROM CaseUploadAttribute c"),
    @NamedQuery(name = "CaseUploadAttribute.findById", query = "SELECT c FROM CaseUploadAttribute c WHERE c.id = :id"),
    @NamedQuery(name = "CaseUploadAttribute.findByIpAddress", query = "SELECT c FROM CaseUploadAttribute c WHERE c.ipAddress = :ipAddress"),
    @NamedQuery(name = "CaseUploadAttribute.findByPort", query = "SELECT c FROM CaseUploadAttribute c WHERE c.port = :port"),
    @NamedQuery(name = "CaseUploadAttribute.findByAeTitle", query = "SELECT c FROM CaseUploadAttribute c WHERE c.aeTitle = :aeTitle"),
    @NamedQuery(name = "CaseUploadAttribute.findByPacsServerIpAddress", query = "SELECT c FROM CaseUploadAttribute c WHERE c.pacsServerIpAddress = :pacsServerIpAddress"),
    @NamedQuery(name = "CaseUploadAttribute.findByPacsServerPort", query = "SELECT c FROM CaseUploadAttribute c WHERE c.pacsServerPort = :pacsServerPort"),
    @NamedQuery(name = "CaseUploadAttribute.findByPacsServerAETitle", query = "SELECT c FROM CaseUploadAttribute c WHERE c.pacsServerAETitle = :pacsServerAETitle"),
    @NamedQuery(name = "CaseUploadAttribute.findByAssociationMessageResponseTimeout", query = "SELECT c FROM CaseUploadAttribute c WHERE c.associationMessageResponseTimeout = :associationMessageResponseTimeout"),
    @NamedQuery(name = "CaseUploadAttribute.findByServiceMessageResponseTimeout", query = "SELECT c FROM CaseUploadAttribute c WHERE c.serviceMessageResponseTimeout = :serviceMessageResponseTimeout"),
    @NamedQuery(name = "CaseUploadAttribute.findByPdataTimeout", query = "SELECT c FROM CaseUploadAttribute c WHERE c.pdataTimeout = :pdataTimeout"),
    @NamedQuery(name = "CaseUploadAttribute.findByAutoCaseUpdateType", query = "SELECT c FROM CaseUploadAttribute c WHERE c.autoCaseUpdateType = :autoCaseUpdateType"),
    @NamedQuery(name = "CaseUploadAttribute.findByRunPeriod", query = "SELECT c FROM CaseUploadAttribute c WHERE c.runPeriod = :runPeriod"),
    @NamedQuery(name = "CaseUploadAttribute.findByMinOldStudy", query = "SELECT c FROM CaseUploadAttribute c WHERE c.minOldStudy = :minOldStudy"),
    @NamedQuery(name = "CaseUploadAttribute.findByFixRunHour", query = "SELECT c FROM CaseUploadAttribute c WHERE c.fixRunHour = :fixRunHour"),
    @NamedQuery(name = "CaseUploadAttribute.findByFixRunMinute", query = "SELECT c FROM CaseUploadAttribute c WHERE c.fixRunMinute = :fixRunMinute")})
public class CaseUploadAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "ipAddress")
    private String ipAddress;
    @Column(name = "port")
    private Integer port;
    @Size(max = 255)
    @Column(name = "aeTitle")
    private String aeTitle;
    @Size(max = 255)
    @Column(name = "pacsServerIpAddress")
    private String pacsServerIpAddress;
    @Column(name = "pacsServerPort")
    private Integer pacsServerPort;
    @Size(max = 255)
    @Column(name = "pacsServerAETitle")
    private String pacsServerAETitle;
    @Column(name = "associationMessageResponseTimeout")
    private BigInteger associationMessageResponseTimeout;
    @Column(name = "serviceMessageResponseTimeout")
    private BigInteger serviceMessageResponseTimeout;
    @Column(name = "pdataTimeout")
    private BigInteger pdataTimeout;
    @Column(name = "autoCaseUpdateType")
    private Integer autoCaseUpdateType;
    @Column(name = "runPeriod")
    private Integer runPeriod;
    @Column(name = "minOldStudy")
    private Integer minOldStudy;
    @Column(name = "fixRunHour")
    private Integer fixRunHour;
    @Column(name = "fixRunMinute")
    private Integer fixRunMinute;

    public CaseUploadAttribute() {
    }

    public CaseUploadAttribute(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAeTitle() {
        return aeTitle;
    }

    public void setAeTitle(String aeTitle) {
        this.aeTitle = aeTitle;
    }

    public String getPacsServerIpAddress() {
        return pacsServerIpAddress;
    }

    public void setPacsServerIpAddress(String pacsServerIpAddress) {
        this.pacsServerIpAddress = pacsServerIpAddress;
    }

    public Integer getPacsServerPort() {
        return pacsServerPort;
    }

    public void setPacsServerPort(Integer pacsServerPort) {
        this.pacsServerPort = pacsServerPort;
    }

    public String getPacsServerAETitle() {
        return pacsServerAETitle;
    }

    public void setPacsServerAETitle(String pacsServerAETitle) {
        this.pacsServerAETitle = pacsServerAETitle;
    }

    public BigInteger getAssociationMessageResponseTimeout() {
        return associationMessageResponseTimeout;
    }

    public void setAssociationMessageResponseTimeout(BigInteger associationMessageResponseTimeout) {
        this.associationMessageResponseTimeout = associationMessageResponseTimeout;
    }

    public BigInteger getServiceMessageResponseTimeout() {
        return serviceMessageResponseTimeout;
    }

    public void setServiceMessageResponseTimeout(BigInteger serviceMessageResponseTimeout) {
        this.serviceMessageResponseTimeout = serviceMessageResponseTimeout;
    }

    public BigInteger getPdataTimeout() {
        return pdataTimeout;
    }

    public void setPdataTimeout(BigInteger pdataTimeout) {
        this.pdataTimeout = pdataTimeout;
    }

    public Integer getAutoCaseUpdateType() {
        return autoCaseUpdateType;
    }

    public void setAutoCaseUpdateType(Integer autoCaseUpdateType) {
        this.autoCaseUpdateType = autoCaseUpdateType;
    }

    public Integer getRunPeriod() {
        return runPeriod;
    }

    public void setRunPeriod(Integer runPeriod) {
        this.runPeriod = runPeriod;
    }

    public Integer getMinOldStudy() {
        return minOldStudy;
    }

    public void setMinOldStudy(Integer minOldStudy) {
        this.minOldStudy = minOldStudy;
    }

    public Integer getFixRunHour() {
        return fixRunHour;
    }

    public void setFixRunHour(Integer fixRunHour) {
        this.fixRunHour = fixRunHour;
    }

    public Integer getFixRunMinute() {
        return fixRunMinute;
    }

    public void setFixRunMinute(Integer fixRunMinute) {
        this.fixRunMinute = fixRunMinute;
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
        if (!(object instanceof CaseUploadAttribute)) {
            return false;
        }
        CaseUploadAttribute other = (CaseUploadAttribute) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "its.gs.entity.CaseUploadAttribute[ id=" + id + " ]";
    }
    
}
