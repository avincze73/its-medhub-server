/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "HospitalStaffRoleAssignment")
public class HospitalStaffRoleAssignment extends ITSRoleAssignment {

    @JoinColumn(name = "hospitalStaffId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalStaff hospitalStaff;
    @JoinColumn(name = "hospitalStaffRoleId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalStaffRole hospitalStaffRole;

    public HospitalStaffRoleAssignment() {
        super();
    }

    public HospitalStaffRoleAssignment(Long id) {
        super(id);
    }

    public HospitalStaff getHospitalStaff() {
        return hospitalStaff;
    }

    public void setHospitalStaff(HospitalStaff hospitalStaff) {
        this.hospitalStaff = hospitalStaff;
    }

    public HospitalStaffRole getHospitalStaffRole() {
        return hospitalStaffRole;
    }

    public void setHospitalStaffRole(HospitalStaffRole hospitalStaffRole) {
        this.hospitalStaffRole = hospitalStaffRole;
    }
}
