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
public class RegLicQualTypeDTO extends BaseDTO {

    protected String name;
    protected boolean nameOptional;
    protected boolean nameError;

    public RegLicQualTypeDTO() {
        this(0, null);
    }

    public RegLicQualTypeDTO(String name) {
        this(0, name);
    }

    public RegLicQualTypeDTO(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public RegLicQualTypeDTO clone() throws CloneNotSupportedException {
        RegLicQualTypeDTO result = (RegLicQualTypeDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegLicQualTypeDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public boolean isNameError() {
        return nameError;
    }

    public void setNameError(boolean nameError) {
        boolean oldValue = this.nameError;
        this.nameError = nameError;
        propertyChangeSupport.firePropertyChange("nameError", oldValue, this.nameError);
    }

    public boolean isNameOptional() {
        return nameOptional;
    }

    public void setNameOptional(boolean nameOptional) {
        boolean oldValue = this.nameOptional;
        this.nameOptional = nameOptional;
        propertyChangeSupport.firePropertyChange("nameOptional", oldValue, this.nameOptional);
    }
}
