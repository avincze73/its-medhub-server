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
public class TDSConstantDTO extends BaseDTO {

    private String name;
    private String description;
    private int constantValue;
    //error
    private boolean nameError;
    private boolean descriptionError;
    private boolean constantValueError;
    //error
    private boolean nameOptional;
    private boolean descriptionOptional;
    private boolean constantValueOptional;

    public TDSConstantDTO() {
        this(0, null, 0, null);
    }

    public TDSConstantDTO(String name, int constantValue) {
        this(0, name, constantValue, null);
    }

    public TDSConstantDTO(long id, String name, int constantValue, String description) {
        super(id);
        this.name = name;
        this.constantValue = constantValue;
        this.description = description;
    }

    @Override
    public TDSConstantDTO clone() throws CloneNotSupportedException {
        TDSConstantDTO result = (TDSConstantDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TDSConstantDTO)) {
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

    public int getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(int constantValue) {
        int oldValue = this.constantValue;
        this.constantValue = constantValue;
        propertyChangeSupport.firePropertyChange("constantValue", oldValue, this.constantValue);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }

    public boolean isDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(boolean descriptionError) {
        boolean oldValue = this.descriptionError;
        this.descriptionError = descriptionError;
        propertyChangeSupport.firePropertyChange("descriptionError", oldValue, this.descriptionError);
    }

    public boolean isDescriptionOptional() {
        return descriptionOptional;
    }

    public void setDescriptionOptional(boolean descriptionOptional) {
        boolean oldValue = this.descriptionOptional;
        this.descriptionOptional = descriptionOptional;
        propertyChangeSupport.firePropertyChange("descriptionOptional", oldValue, this.descriptionOptional);
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

    public boolean isConstantValueError() {
        return constantValueError;
    }

    public void setConstantValueError(boolean constantValueError) {
        boolean oldValue = this.constantValueError;
        this.constantValueError = constantValueError;
        propertyChangeSupport.firePropertyChange("constantValueError", oldValue, this.constantValueError);
    }

    public boolean isConstantValueOptional() {
        return constantValueOptional;
    }

    public void setConstantValueOptional(boolean constantValueOptional) {
        boolean oldValue = this.constantValueOptional;
        this.constantValueOptional = constantValueOptional;
        propertyChangeSupport.firePropertyChange("constantValueOptional", oldValue, this.constantValueOptional);
    }
}
