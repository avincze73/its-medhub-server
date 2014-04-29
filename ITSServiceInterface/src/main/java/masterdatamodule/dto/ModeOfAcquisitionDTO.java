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
public class ModeOfAcquisitionDTO extends BaseDTO {

    private String name;
    private String code;
    //error
    private boolean nameError;
    private boolean codeError;
    //optional
    private boolean nameOptional;
    private boolean codeOptional;

    public ModeOfAcquisitionDTO() {
        this(0, null, null);
    }

    public ModeOfAcquisitionDTO(String name, String code) {
        this(0, name, code);
    }

    public ModeOfAcquisitionDTO(long id, String name, String code) {
        super(id);
        this.name = name;
        this.code = code;
    }

    public ModeOfAcquisitionDTO(long id) {
        this(id, null, null);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ModeOfAcquisitionDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public ModeOfAcquisitionDTO clone() throws CloneNotSupportedException {
        ModeOfAcquisitionDTO result = (ModeOfAcquisitionDTO) super.clone();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        String oldValue = this.code;
        this.code = code;
        propertyChangeSupport.firePropertyChange("code", oldValue, this.code);
    }

    public boolean isCodeError() {
        return codeError;
    }

    public void setCodeError(boolean codeError) {
        boolean oldValue = this.codeError;
        this.codeError = codeError;
        propertyChangeSupport.firePropertyChange("codeError", oldValue, this.codeError);
    }

    public boolean isCodeOptional() {
        return codeOptional;
    }

    public void setCodeOptional(boolean codeOptional) {
        boolean oldValue = this.codeOptional;
        this.codeOptional = codeOptional;
        propertyChangeSupport.firePropertyChange("codeOptional", oldValue, this.codeOptional);
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
