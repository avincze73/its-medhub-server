/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.Modality;
import radiologistmodule.entity.ITSRadiologist;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "RadCompetence")
@NamedQueries({
    @NamedQuery(name = "RadCompetence.findAll", query = "SELECT r FROM RadCompetence r"),
    @NamedQuery(name = "RadCompetence.findById", query = "SELECT r FROM RadCompetence r WHERE r.id = :id"),
    @NamedQuery(name = "RadCompetence.findByAddingDateTime", query = "SELECT r FROM RadCompetence r WHERE r.addingDateTime = :addingDateTime"),
    @NamedQuery(name = "RadCompetence.findByCompetenceLevel", query = "SELECT r FROM RadCompetence r WHERE r.competenceLevel = :competenceLevel"),
    @NamedQuery(name = "RadCompetence.findByValid", query = "SELECT r FROM RadCompetence r WHERE r.valid = :valid")})
public class RadCompetence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "addingDateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addingDateTime;
    @Basic(optional = false)
    @Column(name = "competenceLevel")
    private int competenceLevel;
    @Basic(optional = false)
    @Column(name = "valid")
    private boolean valid;
    @JoinColumn(name = "bodyRegionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BodyRegion bodyRegion;
    @JoinColumn(name = "modalityId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modality modality;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist tDSRadiologist;

    public RadCompetence() {
    }

    public RadCompetence(Long id) {
        this.id = id;
    }

    public RadCompetence(Long id, Date addingDateTime, int competenceLevel, boolean valid) {
        this.id = id;
        this.addingDateTime = addingDateTime;
        this.competenceLevel = competenceLevel;
        this.valid = valid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddingDateTime() {
        return addingDateTime;
    }

    public void setAddingDateTime(Date addingDateTime) {
        this.addingDateTime = addingDateTime;
    }

    public int getCompetenceLevel() {
        return competenceLevel;
    }

    public void setCompetenceLevel(int competenceLevel) {
        this.competenceLevel = competenceLevel;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public BodyRegion getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegion bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public ITSRadiologist getTDSRadiologist() {
        return tDSRadiologist;
    }

    public void setTDSRadiologist(ITSRadiologist tDSRadiologist) {
        this.tDSRadiologist = tDSRadiologist;
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
        if (!(object instanceof RadCompetence)) {
            return false;
        }
        RadCompetence other = (RadCompetence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RadCompetence[id=" + id + "]";
    }

}
