/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemmodule.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import usermodule.entity.ITSRole;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "TDSService")
@NamedQueries({
    @NamedQuery(name = "TDSService.findAll", query = "SELECT t FROM TDSService t"),
    @NamedQuery(name = "TDSService.findById", query = "SELECT t FROM TDSService t WHERE t.id = :id"),
    @NamedQuery(name = "TDSService.findByName", query = "SELECT t FROM TDSService t WHERE t.name = :name"),
    @NamedQuery(name = "TDSService.findByDescription", query = "SELECT t FROM TDSService t WHERE t.description = :description"),
    @NamedQuery(name = "TDSService.findByTdsApplication", query = "SELECT t FROM TDSService t WHERE t.tdsApplication = :tdsApplication")})
public class TDSService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "tdsApplication")
    private String tdsApplication;
    @JoinTable(name = "TDSServiceRoles", joinColumns = {
        @JoinColumn(name = "tdsServiceId", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "roleId", referencedColumnName = "id")})
    @ManyToMany
    private Collection<ITSRole> tDSRoleCollection;

    public TDSService() {
    }

    public TDSService(Long id) {
        this.id = id;
    }

    public TDSService(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTdsApplication() {
        return tdsApplication;
    }

    public void setTdsApplication(String tdsApplication) {
        this.tdsApplication = tdsApplication;
    }

    public Collection<ITSRole> getTDSRoleCollection() {
        return tDSRoleCollection;
    }

    public void setTDSRoleCollection(Collection<ITSRole> tDSRoleCollection) {
        this.tDSRoleCollection = tDSRoleCollection;
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
        if (!(object instanceof TDSService)) {
            return false;
        }
        TDSService other = (TDSService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TDSService[id=" + id + "]";
    }

}
