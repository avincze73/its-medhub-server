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
public class ModalityDTO extends BaseDTO {

    private String name;
    //error
    private boolean nameError;
    //optional
    private boolean nameOptional;

    public ModalityDTO() {
        this(0, null);
    }

    public ModalityDTO(String name) {
        this(0, name);
    }

    public ModalityDTO(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public ModalityDTO clone() throws CloneNotSupportedException {
        ModalityDTO result = (ModalityDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ModalityDTO)) {
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
