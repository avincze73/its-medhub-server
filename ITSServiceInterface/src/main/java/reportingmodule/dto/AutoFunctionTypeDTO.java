/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class AutoFunctionTypeDTO extends BaseDTO {

    private String name;
    private String typeClass;

    public AutoFunctionTypeDTO(long id) {
        this(id, null, null);
    }

    public AutoFunctionTypeDTO() {
        this(0, null, null);
    }

    public AutoFunctionTypeDTO(long id, String name, String typeClass) {
        super(id);
        this.name = name;
        this.typeClass = typeClass;
    }

    public AutoFunctionTypeDTO(String name, String typeClass) {
        super(0);
        this.name = name;
        this.typeClass = typeClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AutoFunctionTypeDTO)) {
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

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        String oldValue = this.typeClass;
        this.typeClass = typeClass;
        propertyChangeSupport.firePropertyChange("typeClass", oldValue, this.typeClass);
    }
}
