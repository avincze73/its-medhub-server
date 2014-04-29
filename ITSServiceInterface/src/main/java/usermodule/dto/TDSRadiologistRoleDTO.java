/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

/**
 *
 * @author vincze.attila
 */
public class TDSRadiologistRoleDTO extends RoleDTO {

    public TDSRadiologistRoleDTO(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias,
                abbreviation, description, roleBoundaries, canHave);
    }

    public TDSRadiologistRoleDTO() {
        super();
    }

    public TDSRadiologistRoleDTO(long id) {
        super(id);
    }




    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSRadiologistRoleDTO)) {
            return false;
        }
        return super.equals(obj);
    }

}
