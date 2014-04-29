/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import masterdatamodule.entity.BodyRegion;
import radiologistmodule.entity.WorkBandTable;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "BodyRegionWithBand")
@NamedQueries({
    @NamedQuery(name = "BodyRegionWithBand.findAll", query = "SELECT b FROM BodyRegionWithBand b"),
    @NamedQuery(name = "BodyRegionWithBand.findById", query = "SELECT b FROM BodyRegionWithBand b WHERE b.id = :id"),
    @NamedQuery(name = "BodyRegionWithBand.findByBandAssignment", query = "SELECT b FROM BodyRegionWithBand b WHERE b.bandAssignment = :bandAssignment")})
public class BodyRegionWithBand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "bandAssignment")
    private String bandAssignment;
    @JoinColumn(name = "workBandTableId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkBandTable workBandTable;
    @JoinColumn(name = "bodyRegionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BodyRegion bodyRegion;

    public BodyRegionWithBand() {
    }

    public BodyRegionWithBand(Long id) {
        this.id = id;
    }

    public BodyRegionWithBand(Long id, String bandAssignment) {
        this.id = id;
        this.bandAssignment = bandAssignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBandAssignment() {
        return bandAssignment;
    }

    public void setBandAssignment(String bandAssignment) {
        this.bandAssignment = bandAssignment;
    }

    public WorkBandTable getWorkBandTable() {
        return workBandTable;
    }

    public void setWorkBandTable(WorkBandTable workBandTable) {
        this.workBandTable = workBandTable;
    }

    public BodyRegion getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegion bodyRegion) {
        this.bodyRegion = bodyRegion;
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
        if (!(object instanceof BodyRegionWithBand)) {
            return false;
        }
        BodyRegionWithBand other = (BodyRegionWithBand) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BodyRegionWithBand[id=" + id + "]";
    }

}
