/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSSession")
public class ITSSession extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "started")
    @Temporal(TemporalType.TIMESTAMP)
    private Date started;
    @Column(name = "ended")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ended;
    @Column(name = "endingType")
    private String endingType;
    @Column(name = "clientHostName")
    private String clientHostName;
    @Column(name = "clientHostIpAddress")
    private String clientHostIpAddress;
    @Column(name = "clientHostMacAddress")
    private String clientHostMacAddress;
    @Column(name = "itsApplication")
    private String itsApplication;
    @Column(name = "httpSessionId")
    private String httpSessionId;
    @JoinColumn(name = "itsUserId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSUser itsUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itsSession")
    private List<ITSSessionLogEntry> sessionLogEntryList;

    public ITSSession() {
        this(null, null);
    }

    public ITSSession(Long id) {
        this(id, null);
    }

    public ITSSession(Long id, Date started) {
        super(id);
        this.started = started;
    }

    @Override
    public ITSSession clone() throws CloneNotSupportedException {
        ITSSession result = (ITSSession) super.clone();
        return result;
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

    public String getEndingType() {
        return endingType;
    }

    public void setEndingType(String endingType) {
        this.endingType = endingType;
    }

    public String getClientHostName() {
        return clientHostName;
    }

    public void setClientHostName(String clientHostName) {
        this.clientHostName = clientHostName;
    }

    public String getClientHostIpAddress() {
        return clientHostIpAddress;
    }

    public void setClientHostIpAddress(String clientHostIpAddress) {
        this.clientHostIpAddress = clientHostIpAddress;
    }

    public String getClientHostMacAddress() {
        return clientHostMacAddress;
    }

    public void setClientHostMacAddress(String clientHostMacAddress) {
        this.clientHostMacAddress = clientHostMacAddress;
    }

    public String getItsApplication() {
        return itsApplication;
    }

    public void setItsApplication(String itsApplication) {
        this.itsApplication = itsApplication;
    }

    public List<ITSSessionLogEntry> getSessionLogEntryList() {
        return sessionLogEntryList;
    }

    public void setSessionLogEntryList(List<ITSSessionLogEntry> sessionLogEntryList) {
        this.sessionLogEntryList = sessionLogEntryList;
    }

    public ITSUser getItsUser() {
        return itsUser;
    }

    public void setItsUser(ITSUser itsUser) {
        this.itsUser = itsUser;
    }

    public String getHttpSessionId() {
        return httpSessionId;
    }

    public void setHttpSessionId(String httpSessionId) {
        this.httpSessionId = httpSessionId;
    }
}
