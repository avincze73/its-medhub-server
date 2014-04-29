/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "RegLicQualType")
public class RegLicQualType extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;

    public RegLicQualType() {
        this(null, null);
    }

    public RegLicQualType(Long id) {
        this(id, null);
    }

    public RegLicQualType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public RegLicQualType(String name) {
        this(null, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   @Override
    public RegLicQualType clone() throws CloneNotSupportedException {
        RegLicQualType result = (RegLicQualType) super.clone();
        return result;
    }
}
