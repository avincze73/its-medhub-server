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
public class SequenceCategoryDTO extends BaseDTO {

    private String categorisation;
    private String bounds;
    private String borders;
    private String multipliers;

    public SequenceCategoryDTO() {
        super();
    }

    public SequenceCategoryDTO(String categorisation, String bounds, String borders, String multipliers) {
        super();
        this.categorisation = categorisation;
        this.bounds = bounds;
        this.borders = borders;
        this.multipliers = multipliers;
    }

    public SequenceCategoryDTO(long id, String categorisation, String bounds, String borders, String multipliers) {
        super(id);
        this.categorisation = categorisation;
        this.bounds = bounds;
        this.borders = borders;
        this.multipliers = multipliers;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SequenceCategoryDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) throws PropertyVetoException {
        String oldValue = this.borders;
        vetoableChangeSupport.fireVetoableChange("borders", oldValue, borders);
        this.borders = borders;
        propertyChangeSupport.firePropertyChange("borders", oldValue, this.borders);
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) throws PropertyVetoException {
        String oldValue = this.bounds;
        vetoableChangeSupport.fireVetoableChange("bounds", oldValue, bounds);
        this.bounds = bounds;
        propertyChangeSupport.firePropertyChange("bounds", oldValue, this.bounds);
    }

    public String getCategorisation() {
        return categorisation;
    }

    public void setCategorisation(String categorisation) throws PropertyVetoException {
        String oldValue = this.categorisation;
        vetoableChangeSupport.fireVetoableChange("categorisation", oldValue, categorisation);
        this.categorisation = categorisation;
        propertyChangeSupport.firePropertyChange("categorisation", oldValue, this.categorisation);
    }

    public String getMultipliers() {
        return multipliers;
    }

    public void setMultipliers(String multipliers) throws PropertyVetoException {
        String oldValue = this.multipliers;
        vetoableChangeSupport.fireVetoableChange("multipliers", oldValue, multipliers);
        this.multipliers = multipliers;
        propertyChangeSupport.firePropertyChange("multipliers", oldValue, this.multipliers);
    }
}
