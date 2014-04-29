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

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "PAXConnection")
@NamedQueries({
    @NamedQuery(name = "PAXConnection.findAll", query = "SELECT p FROM PAXConnection p"),
    @NamedQuery(name = "PAXConnection.findById", query = "SELECT p FROM PAXConnection p WHERE p.id = :id"),
    @NamedQuery(name = "PAXConnection.findByIpAddress", query = "SELECT p FROM PAXConnection p WHERE p.ipAddress = :ipAddress"),
    @NamedQuery(name = "PAXConnection.findByPort", query = "SELECT p FROM PAXConnection p WHERE p.port = :port"),
    @NamedQuery(name = "PAXConnection.findByAeTitle", query = "SELECT p FROM PAXConnection p WHERE p.aeTitle = :aeTitle"),
    @NamedQuery(name = "PAXConnection.findByPacsServerIpAddress", query = "SELECT p FROM PAXConnection p WHERE p.pacsServerIpAddress = :pacsServerIpAddress"),
    @NamedQuery(name = "PAXConnection.findByPacsServerPort", query = "SELECT p FROM PAXConnection p WHERE p.pacsServerPort = :pacsServerPort"),
    @NamedQuery(name = "PAXConnection.findByPacsServerAETitle", query = "SELECT p FROM PAXConnection p WHERE p.pacsServerAETitle = :pacsServerAETitle"),
    @NamedQuery(name = "PAXConnection.findByAssociationMessageResponseTimeout", query = "SELECT p FROM PAXConnection p WHERE p.associationMessageResponseTimeout = :associationMessageResponseTimeout"),
    @NamedQuery(name = "PAXConnection.findByServiceMessageResponseTimeout", query = "SELECT p FROM PAXConnection p WHERE p.serviceMessageResponseTimeout = :serviceMessageResponseTimeout"),
    @NamedQuery(name = "PAXConnection.findByPdataTimeout", query = "SELECT p FROM PAXConnection p WHERE p.pdataTimeout = :pdataTimeout"),
    @NamedQuery(name = "PAXConnection.findByAutoCaseUpdateType", query = "SELECT p FROM PAXConnection p WHERE p.autoCaseUpdateType = :autoCaseUpdateType"),
    @NamedQuery(name = "PAXConnection.findByRunPeriod", query = "SELECT p FROM PAXConnection p WHERE p.runPeriod = :runPeriod"),
    @NamedQuery(name = "PAXConnection.findByMinOldStudy", query = "SELECT p FROM PAXConnection p WHERE p.minOldStudy = :minOldStudy"),
    @NamedQuery(name = "PAXConnection.findByFixRunHour", query = "SELECT p FROM PAXConnection p WHERE p.fixRunHour = :fixRunHour"),
    @NamedQuery(name = "PAXConnection.findByFixRunMinute", query = "SELECT p FROM PAXConnection p WHERE p.fixRunMinute = :fixRunMinute")})
public class PAXConnection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "ipAddress")
    private String ipAddress;
    @Column(name = "port")
    private Integer port;
    @Column(name = "aeTitle")
    private String aeTitle;
    @Column(name = "pacsServerIpAddress")
    private String pacsServerIpAddress;
    @Column(name = "pacsServerPort")
    private Integer pacsServerPort;
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

    public PAXConnection() {
    }

    public PAXConnection(Long id) {
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
        if (!(object instanceof PAXConnection)) {
            return false;
        }
        PAXConnection other = (PAXConnection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PAXConnection[id=" + id + "]";
    }

}
