/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;
import java.beans.PropertyVetoException;

/**
 *
 * @author vincze.attila
 */
public class GoverningAuthorityDTO extends BaseDTO {

    private String name;

    public GoverningAuthorityDTO() {
        super();
    }

    public GoverningAuthorityDTO(String name) {
        super();
        this.name = name;
    }

    public GoverningAuthorityDTO(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GoverningAuthorityDTO)) {
            return false;
        }
        return super.equals(obj);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) throws PropertyVetoException {
        String oldValue = this.name;
        vetoableChangeSupport.fireVetoableChange("name", oldValue, name);
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }
}
