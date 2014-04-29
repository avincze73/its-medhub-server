/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemmodule.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "TDSSystem")
@NamedQueries({
    @NamedQuery(name = "TDSSystem.findAll", query = "SELECT t FROM TDSSystem t"),
    @NamedQuery(name = "TDSSystem.findById", query = "SELECT t FROM TDSSystem t WHERE t.id = :id"),
    @NamedQuery(name = "TDSSystem.findByTimeZoneGmt", query = "SELECT t FROM TDSSystem t WHERE t.timeZoneGmt = :timeZoneGmt"),
    @NamedQuery(name = "TDSSystem.findByRadiologistAssignmentMode", query = "SELECT t FROM TDSSystem t WHERE t.radiologistAssignmentMode = :radiologistAssignmentMode")})
public class TDSSystem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "timeZoneGmt")
    private Integer timeZoneGmt;
    @Column(name = "radiologistAssignmentMode")
    private String radiologistAssignmentMode;

    public TDSSystem() {
    }

    public TDSSystem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(Integer timeZoneGmt) {
        this.timeZoneGmt = timeZoneGmt;
    }

    public String getRadiologistAssignmentMode() {
        return radiologistAssignmentMode;
    }

    public void setRadiologistAssignmentMode(String radiologistAssignmentMode) {
        this.radiologistAssignmentMode = radiologistAssignmentMode;
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
        if (!(object instanceof TDSSystem)) {
            return false;
        }
        TDSSystem other = (TDSSystem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TDSSystem[id=" + id + "]";
    }

}
