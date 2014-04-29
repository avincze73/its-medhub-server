/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author vincze.attila
 */
@Entity
@DiscriminatorValue("HospitalStaff")
public class HospitalStaffRole extends ITSRole {

    public HospitalStaffRole() {
    }

    public HospitalStaffRole(Long id) {
        super(id);
    }

    public HospitalStaffRole(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias, abbreviation, canHave, description, roleBoundaries);
    }
}
