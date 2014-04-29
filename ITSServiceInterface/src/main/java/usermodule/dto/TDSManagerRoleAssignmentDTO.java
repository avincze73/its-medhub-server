/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

/**
 *
 * @author vincze.attila
 */
public class TDSManagerRoleAssignmentDTO extends RoleAssignmentDTO {

    private TDSManagerRoleDTO tdsManagerRole;
    private TDSManagerDTO tdsManager;

    @Override
    public TDSManagerRoleAssignmentDTO clone() throws CloneNotSupportedException {
        TDSManagerRoleAssignmentDTO result = (TDSManagerRoleAssignmentDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSManagerRoleAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public TDSManagerDTO getTdsManager() {
        return tdsManager;
    }

    public void setTdsManager(TDSManagerDTO tdsManager) {
        TDSManagerDTO oldValue = this.tdsManager;
        this.tdsManager = tdsManager;
        propertyChangeSupport.firePropertyChange("tdsManager", oldValue, this.tdsManager);
    }

    public TDSManagerRoleDTO getTdsManagerRole() {
        return tdsManagerRole;
    }

    public void setTdsManagerRole(TDSManagerRoleDTO tdsManagerRole) {
        TDSManagerRoleDTO oldValue = this.tdsManagerRole;
        this.tdsManagerRole = tdsManagerRole;
        propertyChangeSupport.firePropertyChange("tdsManagerRole", oldValue, this.tdsManagerRole);
    }
}
