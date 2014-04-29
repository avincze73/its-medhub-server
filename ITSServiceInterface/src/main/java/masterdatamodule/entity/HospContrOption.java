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
@Table(name = "HospContrOption")
public class HospContrOption extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;
    @Basic(optional = false)
    @Column(name = "types")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String types;
    @Basic(optional = false)
    @Column(name = "explanation")
    private String explanation;
    @Column(name = "longParameter")
    private Long longParameter;
    @Column(name = "stringParameter")
    private String stringParameter;
    @Column(name = "doubleParameter")
    private Double doubleParameter;

    public HospContrOption() {
        this(null, null, null, null);
    }

    public HospContrOption(Long id) {
        this(id, null, null, null);
    }

    public HospContrOption(Long id, String name, String types, String explanation) {
        super(id);
        this.name = name;
        this.types = types;
        this.explanation = explanation;
    }

    public HospContrOption(String name, String types, String explanation) {
        this(null, name, types, explanation);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Long getLongParameter() {
        return longParameter;
    }

    public void setLongParameter(Long longParameter) {
        this.longParameter = longParameter;
    }

    public String getStringParameter() {
        return stringParameter;
    }

    public void setStringParameter(String stringParameter) {
        this.stringParameter = stringParameter;
    }

    public Double getDoubleParameter() {
        return doubleParameter;
    }

    public void setDoubleParameter(Double doubleParameter) {
        this.doubleParameter = doubleParameter;
    }

    @Override
    public HospContrOption clone() throws CloneNotSupportedException {
        HospContrOption result = (HospContrOption) super.clone();
        return result;
    }
}
