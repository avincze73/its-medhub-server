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
@Table(name = "InvalidLogin")
public class InvalidLogin extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "incidentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date incidentDate;
    @Column(name = "clientHostName")
    private String clientHostName;
    @Column(name = "clientHostIpAddress")
    private String clientHostIpAddress;
    @Column(name = "clientHostMacAddress")
    private String clientHostMacAddress;
    @Column(name = "itsApplication")
    private String itsApplication;
    @Column(name = "loginName")
    private String loginName;

    public InvalidLogin() {
        this(null, null);
    }

    public InvalidLogin(Long id) {
        this(id, null);
    }

    public InvalidLogin(Long id, Date incidentDate) {
        super(id);
        this.incidentDate = incidentDate;
    }

    @Override
    public InvalidLogin clone() throws CloneNotSupportedException {
        InvalidLogin result = (InvalidLogin) super.clone();
        return result;
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

    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
