/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

/**
 *
 * @author vincze.attila
 */
public class HospitalStaffRoleAssignmentDTO extends RoleAssignmentDTO {

    private HospitalStaffRoleDTO hospitalStaffRole;
    private HospitalStaffDTO hospitalStaff;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospitalStaffRoleAssignmentDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public HospitalStaffDTO getHospitalStaff() {
        return hospitalStaff;
    }

    public void setHospitalStaff(HospitalStaffDTO hospitalStaff) {
        HospitalStaffDTO oldValue = this.hospitalStaff;
        this.hospitalStaff = hospitalStaff;
        propertyChangeSupport.firePropertyChange("hospitalStaff", oldValue, this.hospitalStaff);
    }

    public HospitalStaffRoleDTO getHospitalStaffRole() {
        return hospitalStaffRole;
    }

    public void setHospitalStaffRole(HospitalStaffRoleDTO hospitalStaffRole) {
        HospitalStaffRoleDTO oldValue = this.hospitalStaffRole;
        this.hospitalStaffRole = hospitalStaffRole;
        propertyChangeSupport.firePropertyChange("hospitalStaffRole", oldValue, this.hospitalStaffRole);
    }
}
