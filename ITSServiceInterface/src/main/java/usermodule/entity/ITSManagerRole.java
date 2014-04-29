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
@DiscriminatorValue("ITSManager")
public class ITSManagerRole extends ITSRole {

    public ITSManagerRole(Long id) {
        super(id);
    }

    public ITSManagerRole() {
        super();
    }

    public ITSManagerRole(String name, String hungarianPublicAlias,
            String englishPublicAlias, String abbreviation, String canHave,
            String description, String roleBoundaries) {
        super(name, hungarianPublicAlias, englishPublicAlias, abbreviation, canHave, description, roleBoundaries);
    }
}
