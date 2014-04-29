/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "AutoFunctionType")
public class AutoFunctionType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "typeClass")
    private String typeClass;
    

    public AutoFunctionType() {
        this(null, null, null);
    }

    public AutoFunctionType(Long id) {
        this(id, null, null);
    }

    public AutoFunctionType(Long id, String name, String typeClass) {
        this.id = id;
        this.name = name;
        this.typeClass = typeClass;
    }
    
    public AutoFunctionType(String name, String typeClass) {
        this(null, name, typeClass);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutoFunctionType)) {
            return false;
        }
        AutoFunctionType other = (AutoFunctionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AutoFunctionType[id=" + id + "]";
    }

}
