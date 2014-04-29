/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.entity;

import hospitalmodule.entity.HospitalContract;
import masterdatamodule.entity.Modality;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "WorkBandTable")
@NamedQueries({
    @NamedQuery(name = "WorkBandTable.findAll", query = "SELECT w FROM WorkBandTable w"),
    @NamedQuery(name = "WorkBandTable.findById", query = "SELECT w FROM WorkBandTable w WHERE w.id = :id"),
    @NamedQuery(name = "WorkBandTable.findByBandNumber", query = "SELECT w FROM WorkBandTable w WHERE w.bandNumber = :bandNumber")})
public class WorkBandTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "bandNumber")
    private int bandNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workBandTable", orphanRemoval = true)
    private Collection<BandInfo> bandInfoCollection;
    @JoinColumn(name = "hospitalContractId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private HospitalContract hospitalContract;
    @JoinColumn(name = "modalityId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modality modality;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workBandTable", orphanRemoval = true)
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "workBandTable")
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//        org.hibernate.annotations.CascadeType.DELETE,
//        org.hibernate.annotations.CascadeType.MERGE,
//        org.hibernate.annotations.CascadeType.PERSIST})
    private Collection<BodyRegionWithBand> bodyRegionWithBandCollection;

    public WorkBandTable() {
    }

    public WorkBandTable(Long id) {
        this.id = id;
    }

    public WorkBandTable(Long id, int bandNumber) {
        this.id = id;
        this.bandNumber = bandNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBandNumber() {
        return bandNumber;
    }

    public void setBandNumber(int bandNumber) {
        this.bandNumber = bandNumber;
    }

    public Collection<BandInfo> getBandInfoCollection() {
        return bandInfoCollection;
    }

    public void setBandInfoCollection(Collection<BandInfo> bandInfoCollection) {
        this.bandInfoCollection = bandInfoCollection;
    }

    public HospitalContract getHospitalContract() {
        return hospitalContract;
    }

    public void setHospitalContract(HospitalContract hospitalContract) {
        this.hospitalContract = hospitalContract;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public Collection<BodyRegionWithBand> getBodyRegionWithBandCollection() {
        return bodyRegionWithBandCollection;
    }

    public void setBodyRegionWithBandCollection(Collection<BodyRegionWithBand> bodyRegionWithBandCollection) {
        this.bodyRegionWithBandCollection = bodyRegionWithBandCollection;
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
        if (!(object instanceof WorkBandTable)) {
            return false;
        }
        WorkBandTable other = (WorkBandTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.WorkBandTable[id=" + id + "]";
    }
}
