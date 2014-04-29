/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pm;

import base.BaseDTO;
import usermodule.dto.RoleDTO;

/**
 *
 * @author vincze.attila
 */
public class RolePM extends BaseDTO {

    private RoleDTO tdsRole;
    private boolean checked;

    public RolePM(RoleDTO tdsRole, boolean checked) {
        super();
        this.tdsRole = tdsRole;
        this.checked = checked;
    }

    public RolePM(long id, RoleDTO tdsRole, boolean checked) {
        super(id);
        this.tdsRole = tdsRole;
        this.checked = checked;
    }


    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        boolean oldValue = this.checked;
        this.checked = checked;
        propertyChangeSupport.firePropertyChange("checked", oldValue, this.checked);
    }

    public RoleDTO getTdsRole() {
        return tdsRole;
    }

    public void setTdsRole(RoleDTO tdsRole) {
        RoleDTO oldValue = this.tdsRole;
        this.tdsRole = tdsRole;
        propertyChangeSupport.firePropertyChange("tdsRole", oldValue, this.tdsRole);
    }
}
