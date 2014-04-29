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
public class RegionDTO extends BaseDTO {

    private String name;
    private CountryDTO country;
    //error
    private boolean nameError;
    private boolean countryError;
    //optional
    private boolean nameOptional;
    private boolean countryOptional;

    public RegionDTO(String name, CountryDTO country) {
        this(0, name, country);
    }

    public RegionDTO(long id, String name, CountryDTO country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    public RegionDTO() {
        this(0, null, null);
    }

    @Override
    public RegionDTO clone() throws CloneNotSupportedException {
        RegionDTO result = (RegionDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegionDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        CountryDTO oldValue = this.country;
        this.country = country;
        propertyChangeSupport.firePropertyChange("country", oldValue, this.country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public boolean isCountryError() {
        return countryError;
    }

    public void setCountryError(boolean countryError) {
        boolean oldValue = this.countryError;
        this.countryError = countryError;
        propertyChangeSupport.firePropertyChange("countryError", oldValue, this.countryError);
    }

    public boolean isCountryOptional() {
        return countryOptional;
    }

    public void setCountryOptional(boolean countryOptional) {
        boolean oldValue = this.countryOptional;
        this.countryOptional = countryOptional;
        propertyChangeSupport.firePropertyChange("countryOptional", oldValue, this.countryOptional);
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
