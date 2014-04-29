/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

/**
 *
 * @author vincze.attila
 */
public class TDSManagerRoleDTO extends RoleDTO {

    public TDSManagerRoleDTO(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias,
                abbreviation, description, roleBoundaries, canHave);
    }

    public TDSManagerRoleDTO() {
        super();
    }

    public TDSManagerRoleDTO(long id) {
        super(id);
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSManagerRoleDTO)) {
            return false;
        }
        return super.equals(obj);
    }
}
