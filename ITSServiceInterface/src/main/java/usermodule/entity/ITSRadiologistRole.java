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
@DiscriminatorValue("ITSRadiologist")
public class ITSRadiologistRole extends ITSRole {

    public ITSRadiologistRole() {
    }

    public ITSRadiologistRole(Long id) {
        super(id);
    }

    public ITSRadiologistRole(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias, abbreviation, canHave, description, roleBoundaries);
    }
}
