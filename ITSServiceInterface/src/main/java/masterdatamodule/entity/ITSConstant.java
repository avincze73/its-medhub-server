/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSConstant")
public class ITSConstant extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "?")
    @NotNull(message = "?")
    private String name;
    @Basic(optional = false)
    @Column(name = "constantValue")
    private int constantValue;
    @Column(name = "description")
    private String description;

    public ITSConstant() {
        this(null, null, 0, null);
    }

    public ITSConstant(Long id) {
        this(id, null, 0, null);
    }

    public ITSConstant(Long id, String name, int constantValue) {
        this(id, name, constantValue, null);
    }

    public ITSConstant(String name, int constantValue) {
        this(null, name, constantValue, null);
    }

    public ITSConstant(Long id, String name, int constantValue, String description) {
        super(id);
        this.name = name;
        this.constantValue = constantValue;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(int constantValue) {
        this.constantValue = constantValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ITSConstant clone() throws CloneNotSupportedException {
        ITSConstant result = (ITSConstant) super.clone();
        return result;
    }
}
