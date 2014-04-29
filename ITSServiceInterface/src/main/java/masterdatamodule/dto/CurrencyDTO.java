/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class CurrencyDTO extends BaseDTO {

    protected String name;
    protected boolean nameOptional;
    protected boolean nameError;

    public CurrencyDTO() {
        this(0, null);
    }

    public CurrencyDTO(String name) {
        this(0, name);
    }

    public CurrencyDTO(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public CurrencyDTO clone() throws CloneNotSupportedException {
        CurrencyDTO result = (CurrencyDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CurrencyDTO)) {
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
