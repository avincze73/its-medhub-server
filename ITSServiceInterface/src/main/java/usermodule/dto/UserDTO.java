/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vincze.attila
 */
public class UserDTO extends BaseDTO {

    //Attributes
    private String loginName;
    private String password;
    private String name;
    private String sex;
    private String tdsEmail;
    private String workEmail;
    private String workTel;
    private String workFax;
    private String placeOfFax;
    private String skype;
    private String msn;
    private Integer timeZoneGmt;
    private Date addingDateTime;
    private boolean active;
    private boolean getsSystemEmails;
    private String userType;
    private List<SessionDTO> sessionList;
    //Optionals
    private boolean loginNameOptional;
    private boolean passwordOptional;
    private boolean nameOptional;
    private boolean sexOptional;
    private boolean tdsEmailOptional;
    private boolean workEmailOptional;
    private boolean workTelOptional;
    private boolean workFaxOptional;
    private boolean placeOfFaxOptional;
    private boolean skypeOptional;
    private boolean msnOptional;
    private boolean timeZoneGmtOptional;
    //Errors
    private boolean loginNameError;
    private boolean passwordError;
    private boolean nameError;
    private boolean sexError;
    private boolean tdsEmailError;
    private boolean workEmailError;
    private boolean workTelError;
    private boolean workFaxError;
    private boolean placeOfFaxError;
    private boolean skypeError;
    private boolean msnError;
    private boolean timeZoneGmtError;

    public UserDTO(long id) {
        super(id);
        sessionList = new ArrayList<SessionDTO>();
        workFaxOptional = true;
        placeOfFaxOptional = true;
        msnOptional = true;
        skypeOptional = true;
        timeZoneGmt = 0;
    }

    public UserDTO() {
        this(0);
        sessionList = new ArrayList<SessionDTO>();
        workFaxOptional = true;
        placeOfFaxOptional = true;
        msnOptional = true;
        skypeOptional = true;
    }

    @Override
    public UserDTO clone() throws CloneNotSupportedException {
        UserDTO result = (UserDTO) super.clone();
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public Date getAddingDateTime() {
        return addingDateTime;
    }

    public void setAddingDateTime(Date addingDateTime) {
        Date oldValue = this.addingDateTime;
        this.addingDateTime = addingDateTime;
        propertyChangeSupport.firePropertyChange("addingDateTime", oldValue, this.addingDateTime);
    }

    public boolean isGetsSystemEmails() {
        return getsSystemEmails;
    }

    public void setGetsSystemEmails(boolean getsSystemEmails) {
        boolean oldValue = this.getsSystemEmails;
        this.getsSystemEmails = getsSystemEmails;
        propertyChangeSupport.firePropertyChange("getsSystemEmails", oldValue, this.getsSystemEmails);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        String oldValue = this.loginName;
        this.loginName = loginName;
        propertyChangeSupport.firePropertyChange("loginName", oldValue, this.loginName);
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        String oldValue = this.msn;
        this.msn = msn;
        propertyChangeSupport.firePropertyChange("msn", oldValue, this.msn);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        String oldValue = this.password;
        this.password = password;
        propertyChangeSupport.firePropertyChange("password", oldValue, this.password);
    }

    public String getPlaceOfFax() {
        return placeOfFax;
    }

    public void setPlaceOfFax(String placeOfFax) {
        String oldValue = this.placeOfFax;
        this.placeOfFax = placeOfFax;
        propertyChangeSupport.firePropertyChange("placeOfFax", oldValue, this.placeOfFax);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        String oldValue = this.sex;
        this.sex = sex;
        propertyChangeSupport.firePropertyChange("sex", oldValue, this.sex);
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        String oldValue = this.skype;
        this.skype = skype;
        propertyChangeSupport.firePropertyChange("skype", oldValue, this.skype);
    }

    public String getTdsEmail() {
        return tdsEmail;
    }

    public void setTdsEmail(String tdsEmail) {
        String oldValue = this.tdsEmail;
        this.tdsEmail = tdsEmail;
        propertyChangeSupport.firePropertyChange("tdsEmail", oldValue, this.tdsEmail);
    }

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        String oldValue = this.workEmail;
        this.workEmail = workEmail;
        propertyChangeSupport.firePropertyChange("workEmail", oldValue, this.workEmail);
    }

    public String getWorkFax() {
        return workFax;
    }

    public void setWorkFax(String workFax) {
        String oldValue = this.workFax;
        this.workFax = workFax;
        propertyChangeSupport.firePropertyChange("workFax", oldValue, this.workFax);
    }

    public String getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        String oldValue = this.workTel;
        this.workTel = workTel;
        propertyChangeSupport.firePropertyChange("workTel", oldValue, this.workTel);
    }

    public Integer getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(Integer timeZoneGmt) {
        Integer oldValue = this.timeZoneGmt;
        this.timeZoneGmt = timeZoneGmt;
        propertyChangeSupport.firePropertyChange("timeZoneGmt", oldValue, this.timeZoneGmt);
    }

    public List<SessionDTO> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<SessionDTO> sessionList) {
        List<SessionDTO> oldValue = this.sessionList;
        this.sessionList = sessionList;
        propertyChangeSupport.firePropertyChange("sessionList", oldValue, this.sessionList);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        String oldValue = this.userType;
        this.userType = userType;
        propertyChangeSupport.firePropertyChange("userType", oldValue, this.userType);
    }

    public boolean isLoginNameError() {
        return loginNameError;
    }

    public void setLoginNameError(boolean loginNameError) {
        boolean oldValue = this.loginNameError;
        this.loginNameError = loginNameError;
        propertyChangeSupport.firePropertyChange("loginNameError", oldValue, this.loginNameError);
    }

    public boolean isLoginNameOptional() {
        return loginNameOptional;
    }

    public void setLoginNameOptional(boolean loginNameOptional) {
        boolean oldValue = this.loginNameOptional;
        this.loginNameOptional = loginNameOptional;
        propertyChangeSupport.firePropertyChange("loginNameOptional", oldValue, this.loginNameOptional);
    }

    public boolean isMsnError() {
        return msnError;
    }

    public void setMsnError(boolean msnError) {
        boolean oldValue = this.msnError;
        this.msnError = msnError;
        propertyChangeSupport.firePropertyChange("msnError", oldValue, this.msnError);
    }

    public boolean isMsnOptional() {
        return msnOptional;
    }

    public void setMsnOptional(boolean msnOptional) {
        boolean oldValue = this.msnOptional;
        this.msnOptional = msnOptional;
        propertyChangeSupport.firePropertyChange("msnOptional", oldValue, this.msnOptional);
    }

    public boolean isNameError() {
        return nameError;
    }

    public void setNameError(boolean nameError) {
        boolean oldValue = this.nameError;
        this.nameError = nameError;
        propertyChangeSupport.firePropertyChange("nameError", oldValue, this.nameError);
    }

    public boolean isNameOptional() {
        return nameOptional;
    }

    public void setNameOptional(boolean nameOptional) {
        boolean oldValue = this.nameOptional;
        this.nameOptional = nameOptional;
        propertyChangeSupport.firePropertyChange("nameOptional", oldValue, this.nameOptional);
    }

    public boolean isPasswordError() {
        return passwordError;
    }

    public void setPasswordError(boolean passwordError) {
        boolean oldValue = this.passwordError;
        this.passwordError = passwordError;
        propertyChangeSupport.firePropertyChange("passwordError", oldValue, this.passwordError);
    }

    public boolean isPasswordOptional() {
        return passwordOptional;
    }

    public void setPasswordOptional(boolean passwordOptional) {
        boolean oldValue = this.passwordOptional;
        this.passwordOptional = passwordOptional;
        propertyChangeSupport.firePropertyChange("passwordOptional", oldValue, this.passwordOptional);
    }

    public boolean isPlaceOfFaxError() {
        return placeOfFaxError;
    }

    public void setPlaceOfFaxError(boolean placeOfFaxError) {
        boolean oldValue = this.placeOfFaxError;
        this.placeOfFaxError = placeOfFaxError;
        propertyChangeSupport.firePropertyChange("placeOfFaxError", oldValue, this.placeOfFaxError);
    }

    public boolean isPlaceOfFaxOptional() {
        return placeOfFaxOptional;
    }

    public void setPlaceOfFaxOptional(boolean placeOfFaxOptional) {
        boolean oldValue = this.placeOfFaxOptional;
        this.placeOfFaxOptional = placeOfFaxOptional;
        propertyChangeSupport.firePropertyChange("placeOfFaxOptional", oldValue, this.placeOfFaxOptional);
    }

    public boolean isSexError() {
        return sexError;
    }

    public void setSexError(boolean sexError) {
        boolean oldValue = this.sexError;
        this.sexError = sexError;
        propertyChangeSupport.firePropertyChange("sexError", oldValue, this.sexError);
    }

    public boolean isSexOptional() {
        return sexOptional;
    }

    public void setSexOptional(boolean sexOptional) {
        boolean oldValue = this.sexOptional;
        this.sexOptional = sexOptional;
        propertyChangeSupport.firePropertyChange("sexOptional", oldValue, this.sexOptional);
    }

    public boolean isSkypeError() {
        return skypeError;
    }

    public void setSkypeError(boolean skypeError) {
        boolean oldValue = this.skypeError;
        this.skypeError = skypeError;
        propertyChangeSupport.firePropertyChange("skypeError", oldValue, this.skypeError);
    }

    public boolean isSkypeOptional() {
        return skypeOptional;
    }

    public void setSkypeOptional(boolean skypeOptional) {
        boolean oldValue = this.skypeOptional;
        this.skypeOptional = skypeOptional;
        propertyChangeSupport.firePropertyChange("skypeOptional", oldValue, this.skypeOptional);
    }

    public boolean isTdsEmailError() {
        return tdsEmailError;
    }

    public void setTdsEmailError(boolean tdsEmailError) {
        boolean oldValue = this.tdsEmailError;
        this.tdsEmailError = tdsEmailError;
        propertyChangeSupport.firePropertyChange("tdsEmailError", oldValue, this.tdsEmailError);
    }

    public boolean isTdsEmailOptional() {
        return tdsEmailOptional;
    }

    public void setTdsEmailOptional(boolean tdsEmailOptional) {
        boolean oldValue = this.tdsEmailOptional;
        this.tdsEmailOptional = tdsEmailOptional;
        propertyChangeSupport.firePropertyChange("tdsEmailOptional", oldValue, this.tdsEmailOptional);
    }

    public boolean isTimeZoneGmtError() {
        return timeZoneGmtError;
    }

    public void setTimeZoneGmtError(boolean timeZoneGmtError) {
        boolean oldValue = this.timeZoneGmtError;
        this.timeZoneGmtError = timeZoneGmtError;
        propertyChangeSupport.firePropertyChange("timeZoneGmtError", oldValue, this.timeZoneGmtError);
    }

    public boolean isTimeZoneGmtOptional() {
        return timeZoneGmtOptional;
    }

    public void setTimeZoneGmtOptional(boolean timeZoneGmtOptional) {
        boolean oldValue = this.timeZoneGmtOptional;
        this.timeZoneGmtOptional = timeZoneGmtOptional;
        propertyChangeSupport.firePropertyChange("timeZoneGmtOptional", oldValue, this.timeZoneGmtOptional);
    }

    public boolean isWorkEmailError() {
        return workEmailError;
    }

    public void setWorkEmailError(boolean workEmailError) {
        boolean oldValue = this.workEmailError;
        this.workEmailError = workEmailError;
        propertyChangeSupport.firePropertyChange("workEmailError", oldValue, this.workEmailError);
    }

    public boolean isWorkEmailOptional() {
        return workEmailOptional;
    }

    public void setWorkEmailOptional(boolean workEmailOptional) {
        boolean oldValue = this.workEmailOptional;
        this.workEmailOptional = workEmailOptional;
        propertyChangeSupport.firePropertyChange("workEmailOptional", oldValue, this.workEmailOptional);
    }

    public boolean isWorkFaxError() {
        return workFaxError;
    }

    public void setWorkFaxError(boolean workFaxError) {
        boolean oldValue = this.workFaxError;
        this.workFaxError = workFaxError;
        propertyChangeSupport.firePropertyChange("workFaxError", oldValue, this.workFaxError);
    }

    public boolean isWorkFaxOptional() {
        return workFaxOptional;
    }

    public void setWorkFaxOptional(boolean workFaxOptional) {
        boolean oldValue = this.workFaxOptional;
        this.workFaxOptional = workFaxOptional;
        propertyChangeSupport.firePropertyChange("workFaxOptional", oldValue, this.workFaxOptional);
    }

    public boolean isWorkTelError() {
        return workTelError;
    }

    public void setWorkTelError(boolean workTelError) {
        boolean oldValue = this.workTelError;
        this.workTelError = workTelError;
        propertyChangeSupport.firePropertyChange("workTelError", oldValue, this.workTelError);
    }

    public boolean isWorkTelOptional() {
        return workTelOptional;
    }

    public void setWorkTelOptional(boolean workTelOptional) {
        boolean oldValue = this.workTelOptional;
        this.workTelOptional = workTelOptional;
        propertyChangeSupport.firePropertyChange("workTelOptional", oldValue, this.workTelOptional);
    }

    @Override
    public void Validate()  throws ConstraintException{
        super.Validate();
        boolean hasConstraintError = false;
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (field.getName().contains("Optional") && !field.getBoolean(this)) {
                    String attr = field.getName().replace("Optional", "");
                    String err = field.getName().replace("Optional", "Error");
                    String errSetter = "set" + err.substring(0, 1).toUpperCase() + err.substring(1);
                    if (this.getClass().getDeclaredField(attr).get(this) == null || "".equals(this.getClass().getDeclaredField(attr).get(this).toString())) {
                        this.getClass().getDeclaredMethod(errSetter, boolean.class).invoke(this, true);
                        hasConstraintError = true;
                    }
                }
            } catch (InvocationTargetException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchFieldException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (hasConstraintError) throw new ConstraintException("Hiba");
    }
}
