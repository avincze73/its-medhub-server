/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import hospitalmodule.dto.HospitalDTO;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterdatamodule.dto.LanguageDTO;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class HospitalStaffDTO extends BaseDTO {

    private HospitalDTO hospital;
    private String positionInHosp;
    private String roleWithTDS;
    private UserDTO userInfo;
    private List<LanguageDTO> languageList;
    private List<HospitalStaffRoleAssignmentDTO> roleAssignmentList;
    transient private ObservableList<LanguageDTO> obsLanguageList;

    public static HospitalStaffDTO createNullObject() {
        HospitalStaffDTO ret = new HospitalStaffDTO();
        ret.getUserInfo().setTimeZoneGmt(null);
        return ret;
    }

    public HospitalStaffDTO() {
        this(0, null, null, null);
    }

    public HospitalStaffDTO(HospitalDTO hospital, String positionInHosp, String roleWithTDS) {
        this(0, hospital, positionInHosp, roleWithTDS);
    }

    public HospitalStaffDTO(long id, HospitalDTO hospital, String positionInHosp, String roleWithTDS) {
        super(id);
        this.hospital = hospital;
        this.positionInHosp = positionInHosp;
        this.roleWithTDS = roleWithTDS;
        this.languageList = new ArrayList<LanguageDTO>();
        userInfo = new UserDTO();
        obsLanguageList = ObservableCollections.observableList(languageList);
        roleAssignmentList = new ArrayList<HospitalStaffRoleAssignmentDTO>();
    }

    public HospitalStaffDTO(long id) {
        this(id, null, null, null);
    }

    @Override
    public HospitalStaffDTO clone() throws CloneNotSupportedException {
        HospitalStaffDTO result = (HospitalStaffDTO) super.clone();
        result.setUserInfo((UserDTO) userInfo.clone());
        return result;
    }

    @Override
    public List<SessionLogEntryDTO> getModifications(BaseDTO original) {
        List<SessionLogEntryDTO> ret = new ArrayList<SessionLogEntryDTO>();
        if (original != null && ((HospitalStaffDTO) original).getUserInfo() != null) {
            List<SessionLogEntryDTO> retUserInfo = userInfo.getModifications(((HospitalStaffDTO) original).getUserInfo());
            ret.addAll(retUserInfo);
        } else {
            List<SessionLogEntryDTO> retUserInfo = userInfo.getModifications(null);
            ret.addAll(retUserInfo);
        }
        return ret;
    }

    @Override
    public void validate() throws ConstraintException {
        super.validate();
        userInfo.validate();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospitalStaffDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        HospitalDTO oldValue = this.hospital;
        this.hospital = hospital;
        propertyChangeSupport.firePropertyChange("hospital", oldValue, this.hospital);
    }

    public List<LanguageDTO> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<LanguageDTO> languageList) {
        List<LanguageDTO> oldValue = this.languageList;
        this.languageList = languageList;
        this.obsLanguageList.clear();
        this.obsLanguageList.addAll(languageList);
        propertyChangeSupport.firePropertyChange("languageList", oldValue, this.languageList);
    }

    public String getPositionInHosp() {
        return positionInHosp;
    }

    public void setPositionInHosp(String positionInHosp) {
        String oldValue = this.positionInHosp;
        this.positionInHosp = positionInHosp;
        propertyChangeSupport.firePropertyChange("positionInHosp", oldValue, this.positionInHosp);
    }

    public String getRoleWithTDS() {
        return roleWithTDS;
    }

    public void setRoleWithTDS(String roleWithTDS) {
        String oldValue = this.roleWithTDS;
        this.roleWithTDS = roleWithTDS;
        propertyChangeSupport.firePropertyChange("roleWithTDS", oldValue, this.roleWithTDS);
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        UserDTO oldValue = this.userInfo;
        this.userInfo = userInfo;
        propertyChangeSupport.firePropertyChange("userInfo", oldValue, this.userInfo);
    }

    public ObservableList<LanguageDTO> getObsLanguageList() {
        return obsLanguageList;
    }

    public void setObsLanguageList(ObservableList<LanguageDTO> obsLanguageList) {
        ObservableList<LanguageDTO> oldValue = this.obsLanguageList;
        this.obsLanguageList = obsLanguageList;
        propertyChangeSupport.firePropertyChange("obsLanguageList", oldValue, this.obsLanguageList);
    }

    public List<HospitalStaffRoleAssignmentDTO> getRoleAssignmentList() {
        return roleAssignmentList;
    }

    public void setRoleAssignmentList(List<HospitalStaffRoleAssignmentDTO> roleAssignmentList) {
        List<HospitalStaffRoleAssignmentDTO> oldValue = this.roleAssignmentList;
        this.roleAssignmentList = roleAssignmentList;
        propertyChangeSupport.firePropertyChange("roleAssignmentList", oldValue, this.roleAssignmentList);
    }

    public void update(HospitalStaffDTO newItem) {
            languageList.clear();
            if (newItem.getUserInfo() == null) {
                setUserInfo(new UserDTO());
            } else {
                userInfo.setId(newItem.getUserInfo().getId());
                userInfo.setName(newItem.getUserInfo().getName());
                userInfo.setLoginName(newItem.getUserInfo().getLoginName());
                userInfo.setPassword(newItem.getUserInfo().getPassword());
                userInfo.setSex(newItem.getUserInfo().getSex());
                userInfo.setTdsEmail(newItem.getUserInfo().getTdsEmail());
                userInfo.setWorkEmail(newItem.getUserInfo().getWorkEmail());
                userInfo.setWorkTel(newItem.getUserInfo().getWorkTel());
                userInfo.setWorkFax(newItem.getUserInfo().getWorkFax());
                userInfo.setPlaceOfFax(newItem.getUserInfo().getPlaceOfFax());
                userInfo.setSkype(newItem.getUserInfo().getSkype());
                userInfo.setMsn(newItem.getUserInfo().getMsn());
                userInfo.setAddingDateTime(newItem.getUserInfo().getAddingDateTime());
                userInfo.setActive(newItem.getUserInfo().isActive());
                userInfo.setGetsSystemEmails(newItem.getUserInfo().isGetsSystemEmails());
            }
            setId(newItem.getId());
            setHospital(newItem.getHospital());
            getHospital().setOfficialName(getHospital().getOfficialName());
            setPositionInHosp(newItem.getPositionInHosp());
            setRoleWithTDS(newItem.getRoleWithTDS());
            setLanguageList(newItem.getLanguageList());
    }
}
