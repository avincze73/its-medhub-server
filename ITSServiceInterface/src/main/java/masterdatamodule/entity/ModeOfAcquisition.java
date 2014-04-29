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
@Table(name = "ModeOfAcquisition")
public class ModeOfAcquisition extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;
    @Basic(optional = false)
    @Column(name = "code")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String code;

    public ModeOfAcquisition() {
        this(null, null, null);
    }

    public ModeOfAcquisition(Long id) {
        this(id, null, null);
    }

    public ModeOfAcquisition(Long id, String name, String code) {
        super(id);
        this.name = name;
        this.code = code;
    }

    public ModeOfAcquisition(String name, String code) {
        this(null, name, code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public ModeOfAcquisition clone() throws CloneNotSupportedException {
        ModeOfAcquisition result = (ModeOfAcquisition) super.clone();
        return result;
    }
}
