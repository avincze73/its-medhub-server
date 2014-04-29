/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author vincze.attila
 */
@MappedSuperclass
public class ITSEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    protected Long id;

    public ITSEntity(Long id) {
        this.id = id;
    }

    public ITSEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        if (this.getId() != null && this.getId().equals(((ITSEntity) object).getId()) ) {
            result = true;
        }
        //boolean result = (this.getId() == null) ? false : this.getId() == ((ITSEntity) object).getId();
        return result;
    }

    @Override
    public ITSEntity clone() throws CloneNotSupportedException {
        ITSEntity result = (ITSEntity) super.clone();
        return result;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() + "[ id=" + id + " ]";
    }
}
