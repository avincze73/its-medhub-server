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
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Region")
public class Region extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;
    @JoinColumn(name = "countryId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message = "*")
    private Country country;

    public Region() {
        this(null, null, null);
    }

    public Region(Long id) {
        this(id, null, null);
    }

    public Region(Long id, String name) {
        this(id, name, null);
    }

    public Region(String name, Country country) {
        this(null, name, country);
    }

    public Region(Long id, String name, Country country) {
        super(id);
        this.name = name;
        this.country = country;
    }

    @Override
    public Region clone() throws CloneNotSupportedException {
        Region result = (Region) super.clone();
        result.setCountry(this.country.clone());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
