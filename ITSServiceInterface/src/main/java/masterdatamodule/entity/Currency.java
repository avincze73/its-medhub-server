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
@Table(name = "Currency")
public class Currency extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min=1, message="!")
    @NotNull(message="!")
    private String name;

    public Currency() {
        this(null, null);
    }

    public Currency(Long id) {
        this(id, null);
    }

    public Currency(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Currency(String name) {
        this(null, name);
    }
    
    @Override
    public Currency clone() throws CloneNotSupportedException {
        Currency result = (Currency) super.clone();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
