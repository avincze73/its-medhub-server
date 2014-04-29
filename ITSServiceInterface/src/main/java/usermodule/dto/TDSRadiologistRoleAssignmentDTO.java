/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import radiologistmodule.dto.TDSRadiologistDTO;

/**
 *
 * @author vincze.attila
 */
public class TDSRadiologistRoleAssignmentDTO extends RoleAssignmentDTO {

    private TDSRadiologistRoleDTO tdsRadiologistRole;
    private TDSRadiologistDTO tdsRadiologist;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSRadiologistRoleAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }

    public TDSRadiologistRoleDTO getTdsRadiologistRole() {
        return tdsRadiologistRole;
    }

    public void setTdsRadiologistRole(TDSRadiologistRoleDTO tdsRadiologistRole) {
        TDSRadiologistRoleDTO oldValue = this.tdsRadiologistRole;
        this.tdsRadiologistRole = tdsRadiologistRole;
        propertyChangeSupport.firePropertyChange("tdsRadiologistRole", oldValue, this.tdsRadiologistRole);
    }
}
