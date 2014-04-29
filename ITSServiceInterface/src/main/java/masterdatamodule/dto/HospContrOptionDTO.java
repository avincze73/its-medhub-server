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
public class HospContrOptionDTO extends BaseDTO {

    private String name;
    private String types;
    private String explanation;
    private Long longParameter;
    private String stringParameter;
    private Double doubleParameter;
    //error
    private boolean nameError;
    private boolean typesError;
    private boolean explanationError;
    private boolean longParameterError;
    private boolean stringParameterError;
    private boolean doubleParameterError;
    //optional
    private boolean nameOptional;
    private boolean typesOptional;
    private boolean explanationOptional;
    private boolean longParameterOptional;
    private boolean stringParameterOptional;
    private boolean doubleParameterOptional;

    public HospContrOptionDTO() {
        this(0, null, null, null);
    }

    public HospContrOptionDTO(String name, String types, String explanation) {
        this(0, name, types, explanation);
    }

    public HospContrOptionDTO(long id, String name, String types, String explanation) {
        super(id);
        this.name = name;
        this.types = types;
        this.explanation = explanation;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospContrOptionDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public HospContrOptionDTO clone() throws CloneNotSupportedException {
        HospContrOptionDTO result = (HospContrOptionDTO) super.clone();
        return result;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        String oldValue = this.explanation;
        this.explanation = explanation;
        propertyChangeSupport.firePropertyChange("explanation", oldValue, this.explanation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        String oldValue = this.types;
        this.types = types;
        propertyChangeSupport.firePropertyChange("types", oldValue, this.types);
    }

    public Double getDoubleParameter() {
        return doubleParameter;
    }

    public void setDoubleParameter(Double doubleParameter) {
        Double oldValue = this.doubleParameter;
        this.doubleParameter = doubleParameter;
        propertyChangeSupport.firePropertyChange("doubleParameter", oldValue, this.doubleParameter);
    }

    public Long getLongParameter() {
        return longParameter;
    }

    public void setLongParameter(Long longParameter) {
        Long oldValue = this.longParameter;
        this.longParameter = longParameter;
        propertyChangeSupport.firePropertyChange("longParameter", oldValue, this.longParameter);
    }

    public String getStringParameter() {
        return stringParameter;
    }

    public void setStringParameter(String stringParameter) {
        String oldValue = this.stringParameter;
        this.stringParameter = stringParameter;
        propertyChangeSupport.firePropertyChange("stringParameter", oldValue, this.stringParameter);
    }

    public boolean isDoubleParameterError() {
        return doubleParameterError;
    }

    public void setDoubleParameterError(boolean doubleParameterError) {
        boolean oldValue = this.doubleParameterError;
        this.doubleParameterError = doubleParameterError;
        propertyChangeSupport.firePropertyChange("doubleParameterError", oldValue, this.doubleParameterError);
    }

    public boolean isDoubleParameterOptional() {
        return doubleParameterOptional;
    }

    public void setDoubleParameterOptional(boolean doubleParameterOptional) {
        boolean oldValue = this.doubleParameterOptional;
        this.doubleParameterOptional = doubleParameterOptional;
        propertyChangeSupport.firePropertyChange("doubleParameterOptional", oldValue, this.doubleParameterOptional);
    }

    public boolean isExplanationError() {
        return explanationError;
    }

    public void setExplanationError(boolean explanationError) {
        boolean oldValue = this.explanationError;
        this.explanationError = explanationError;
        propertyChangeSupport.firePropertyChange("explanationError", oldValue, this.explanationError);
    }

    public boolean isExplanationOptional() {
        return explanationOptional;
    }

    public void setExplanationOptional(boolean explanationOptional) {
        boolean oldValue = this.explanationOptional;
        this.explanationOptional = explanationOptional;
        propertyChangeSupport.firePropertyChange("explanationOptional", oldValue, this.explanationOptional);
    }

    public boolean isLongParameterError() {
        return longParameterError;
    }

    public void setLongParameterError(boolean longParameterError) {
        boolean oldValue = this.longParameterError;
        this.longParameterError = longParameterError;
        propertyChangeSupport.firePropertyChange("longParameterError", oldValue, this.longParameterError);
    }

    public boolean isLongParameterOptional() {
        return longParameterOptional;
    }

    public void setLongParameterOptional(boolean longParameterOptional) {
        boolean oldValue = this.longParameterOptional;
        this.longParameterOptional = longParameterOptional;
        propertyChangeSupport.firePropertyChange("longParameterOptional", oldValue, this.longParameterOptional);
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

    public boolean isStringParameterError() {
        return stringParameterError;
    }

    public void setStringParameterError(boolean stringParameterError) {
        boolean oldValue = this.stringParameterError;
        this.stringParameterError = stringParameterError;
        propertyChangeSupport.firePropertyChange("stringParameterError", oldValue, this.stringParameterError);
    }

    public boolean isStringParameterOptional() {
        return stringParameterOptional;
    }

    public void setStringParameterOptional(boolean stringParameterOptional) {
        boolean oldValue = this.stringParameterOptional;
        this.stringParameterOptional = stringParameterOptional;
        propertyChangeSupport.firePropertyChange("stringParameterOptional", oldValue, this.stringParameterOptional);
    }

    public boolean isTypesError() {
        return typesError;
    }

    public void setTypesError(boolean typesError) {
        boolean oldValue = this.typesError;
        this.typesError = typesError;
        propertyChangeSupport.firePropertyChange("typesError", oldValue, this.typesError);
    }

    public boolean isTypesOptional() {
        return typesOptional;
    }

    public void setTypesOptional(boolean typesOptional) {
        boolean oldValue = this.typesOptional;
        this.typesOptional = typesOptional;
        propertyChangeSupport.firePropertyChange("typesOptional", oldValue, this.typesOptional);
    }
}
