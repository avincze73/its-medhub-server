/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "RegLicQual")
public class RegLicQual extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "reqLicQualTypeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message = "*")
    private RegLicQualType regLicQualType;
    @JoinColumn(name = "regionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message = "*")
    private Region region;

    public RegLicQual() {
        this(null, null, null, null);
    }

    public RegLicQual(Long id) {
        this(id, null, null, null);
    }

    public RegLicQual(Long id, String name) {
        this(id, name, null, null);
    }

    public RegLicQual(String name, Region region, RegLicQualType type) {
        this(null, name, region, type);
    }

    public RegLicQual(Long id, String name, Region region, RegLicQualType type) {
        super(id);
        this.name = name;
        this.region = region;
        this.regLicQualType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RegLicQualType getRegLicQualType() {
        return regLicQualType;
    }

    public void setRegLicQualType(RegLicQualType regLicQualType) {
        this.regLicQualType = regLicQualType;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public RegLicQual clone() throws CloneNotSupportedException {
        RegLicQual result = (RegLicQual) super.clone();
        result.setRegLicQualType(this.getRegLicQualType().clone());
        result.setRegion(this.region.clone());
        return result;
    }
}
