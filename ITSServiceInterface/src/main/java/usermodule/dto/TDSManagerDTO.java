/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class TDSManagerDTO extends BaseDTO {

    private UserDTO userInfo;
    private List<TDSManagerRoleAssignmentDTO> roleAssignmentList;

    public static TDSManagerDTO createNullObject() {
        TDSManagerDTO ret = new TDSManagerDTO();
        ret.getUserInfo().setTimeZoneGmt(null);
        return ret;
    }

    public TDSManagerDTO() {
        this(0);
    }

    public TDSManagerDTO(long id) {
        super(id);
        roleAssignmentList = new ArrayList<TDSManagerRoleAssignmentDTO>();
        userInfo = new UserDTO();
    }

    @Override
    public TDSManagerDTO clone() throws CloneNotSupportedException {
        TDSManagerDTO result = (TDSManagerDTO) super.clone();
        result.setUserInfo((UserDTO) userInfo.clone());
        return result;
    }

    @Override
    public List<SessionLogEntryDTO> getModifications(BaseDTO original) {
        List<SessionLogEntryDTO> ret = new ArrayList<SessionLogEntryDTO>();
        if (original != null && ((TDSManagerDTO) original).getUserInfo() != null) {
            List<SessionLogEntryDTO> retUserInfo = userInfo.getModifications(((TDSManagerDTO) original).getUserInfo());
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
        if (!(obj instanceof TDSManagerDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        UserDTO oldValue = this.userInfo;
        this.userInfo = userInfo;
        propertyChangeSupport.firePropertyChange("userInfo", oldValue, this.userInfo);
    }

    public List<TDSManagerRoleAssignmentDTO> getRoleAssignmentList() {
        return roleAssignmentList;
    }

    public void setRoleAssignmentList(List<TDSManagerRoleAssignmentDTO> roleAssignmentList) {
        List<TDSManagerRoleAssignmentDTO> oldValue = this.roleAssignmentList;
        this.roleAssignmentList = roleAssignmentList;
        propertyChangeSupport.firePropertyChange("roleAssignmentList", oldValue, this.roleAssignmentList);
    }

    
}
