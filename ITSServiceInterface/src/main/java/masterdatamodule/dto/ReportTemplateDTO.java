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
public class ReportTemplateDTO extends BaseDTO {

    private String name;
    private String description;
    //error
    private boolean nameError;
    private boolean descriptionError;
    //optional
    private boolean nameOptional;
    private boolean descriptionOptional;

    public ReportTemplateDTO() {
        this(0, null, null);
    }

    public ReportTemplateDTO(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportTemplateDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public ReportTemplateDTO clone() throws CloneNotSupportedException {
        ReportTemplateDTO result = (ReportTemplateDTO) super.clone();
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
}
