/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

/**
 *
 * @author vincze.attila
 */
public class HospitalStaffRoleDTO extends RoleDTO {

    public HospitalStaffRoleDTO(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias,
                abbreviation, description, roleBoundaries, canHave);
    }

    public HospitalStaffRoleDTO() {
        super();
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospitalStaffRoleDTO)) {
            return false;
        }
        return super.equals(obj);
    }
}
