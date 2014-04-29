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
@Table(name = "WayOfClosing")
public class WayOfClosing extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    @Size(min = 1, message = "*")
    @NotNull(message = "*")
    private String name;

    public WayOfClosing() {
        this(null, null);
    }

    public WayOfClosing(Long id) {
        this(id, null);
    }

    public WayOfClosing(Long id, String name) {
        super(id);
        this.name = name;
    }

    public WayOfClosing(String name) {
        this(null, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public WayOfClosing clone() throws CloneNotSupportedException {
        WayOfClosing result = (WayOfClosing) super.clone();
        return result;
    }
}
