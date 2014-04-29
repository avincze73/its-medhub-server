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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSUser")
public class ITSUser extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "loginName", unique=true)
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String loginName;
    @Basic(optional = false)
    @Column(name = "password")
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String password;
    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String name;
    @Column(name = "userType")
    private String userType;
    @Basic(optional = false)
    @Column(name = "sex")
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String sex;
    @Basic(optional = false)
    @Column(name = "itsEmail")
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String itsEmail;
    @Basic(optional = false)
    @Column(name = "workEmail")
    private String workEmail;
    @Basic(optional = false)
    @Column(name = "workTel")
    private String workTel;
    @Column(name = "workFax")
    private String workFax;
    @Column(name = "placeOfFax")
    private String placeOfFax;
    @Column(name = "skype")
    private String skype;
    @Column(name = "msn")
    private String msn;
    @Basic(optional = false)
    @Column(name = "addingDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addingDateTime;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "getsSystemEmails")
    private boolean getsSystemEmails;
    @Column(name = "timeZoneGmt")
    private Integer timeZoneGmt;
    //associations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itsUser")
    private List<ITSSession> sessionList;

    public ITSUser() {
        super();
    }

    public ITSUser(Long id) {
        super(id);
    }

    public ITSUser(Long id, String loginName, String password, String name, String sex, String itsEmail,
            String workEmail, String workTel, Date addingDateTime, boolean active, boolean getsSystemEmails) {
        super(id);
        this.loginName = loginName;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.itsEmail = itsEmail;
        this.workEmail = workEmail;
        this.workTel = workTel;
        this.addingDateTime = addingDateTime;
        this.active = active;
        this.getsSystemEmails = getsSystemEmails;
    }

    @Override
    public ITSUser clone() throws CloneNotSupportedException {
        ITSUser result = (ITSUser) super.clone();
        return result;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel = workTel;
    }

    public String getWorkFax() {
        return workFax;
    }

    public void setWorkFax(String workFax) {
        this.workFax = workFax;
    }

    public String getPlaceOfFax() {
        return placeOfFax;
    }

    public void setPlaceOfFax(String placeOfFax) {
        this.placeOfFax = placeOfFax;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public Date getAddingDateTime() {
        return addingDateTime;
    }

    public void setAddingDateTime(Date addingDateTime) {
        this.addingDateTime = addingDateTime;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getGetsSystemEmails() {
        return getsSystemEmails;
    }

    public void setGetsSystemEmails(boolean getsSystemEmails) {
        this.getsSystemEmails = getsSystemEmails;
    }

    public Integer getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(Integer timeZoneGmt) {
        this.timeZoneGmt = timeZoneGmt;
    }

    public String getItsEmail() {
        return itsEmail;
    }

    public void setItsEmail(String itsEmail) {
        this.itsEmail = itsEmail;
    }

    public List<ITSSession> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<ITSSession> sessionList) {
        this.sessionList = sessionList;
    }
}
