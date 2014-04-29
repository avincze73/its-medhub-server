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
public class RegLicQualDTO extends BaseDTO {

    private String name;
    private RegionDTO region;
    private RegLicQualTypeDTO type;
    //error
    private boolean nameError;
    private boolean regionError;
    private boolean typeError;
    //optional
    private boolean nameOptional;
    private boolean regionOptional;
    private boolean typeOptional;

    public RegLicQualDTO() {
        this(0, null, null, null);
    }

    public RegLicQualDTO(String name, RegionDTO region, RegLicQualTypeDTO type) {
        this(0, name, region, type);
    }

    public RegLicQualDTO(int id, String name, RegionDTO region, RegLicQualTypeDTO type) {
        super(id);
        this.name = name;
        this.region = region;
        this.type = type;
    }

    @Override
    public RegLicQualDTO clone() throws CloneNotSupportedException {
        RegLicQualDTO result = (RegLicQualDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegLicQualDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        RegionDTO oldValue = this.region;
        this.region = region;
        propertyChangeSupport.firePropertyChange("region", oldValue, this.region);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public RegLicQualTypeDTO getType() {
        return type;
    }

    public void setType(RegLicQualTypeDTO type) {
        RegLicQualTypeDTO oldValue = this.type;
        this.type = type;
        propertyChangeSupport.firePropertyChange("type", oldValue, this.type);
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

    public boolean isRegionError() {
        return regionError;
    }

    public void setRegionError(boolean regionError) {
        boolean oldValue = this.regionError;
        this.regionError = regionError;
        propertyChangeSupport.firePropertyChange("regionError", oldValue, this.regionError);
    }

    public boolean isRegionOptional() {
        return regionOptional;
    }

    public void setRegionOptional(boolean regionOptional) {
        boolean oldValue = this.regionOptional;
        this.regionOptional = regionOptional;
        propertyChangeSupport.firePropertyChange("regionOptional", oldValue, this.regionOptional);
    }

    public boolean isTypeError() {
        return typeError;
    }

    public void setTypeError(boolean typeError) {
        boolean oldValue = this.typeError;
        this.typeError = typeError;
        propertyChangeSupport.firePropertyChange("typeError", oldValue, this.typeError);
    }

    public boolean isTypeOptional() {
        return typeOptional;
    }

    public void setTypeOptional(boolean typeOptional) {
        boolean oldValue = this.typeOptional;
        this.typeOptional = typeOptional;
        propertyChangeSupport.firePropertyChange("typeOptional", oldValue, this.typeOptional);
    }
}
