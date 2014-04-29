/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import casemodule.entity.TDSCase;
import casemodule.entity.TDSStudy;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
@Table(name = "ReferralInfo")
@NamedQueries({
    @NamedQuery(name = "ReferralInfo.findAll", query = "SELECT r FROM ReferralInfo r"),
    @NamedQuery(name = "ReferralInfo.findById", query = "SELECT r FROM ReferralInfo r WHERE r.id = :id")})
public class ReferralInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "risReferralId", referencedColumnName = "id")
    @ManyToOne
    private RISReferral rISReferral;
    @JoinColumn(name = "hisReferralId", referencedColumnName = "id")
    @ManyToOne
    private HISReferral hISReferral;
    @JoinColumn(name = "imageReferralId", referencedColumnName = "id")
    @ManyToOne
    private ImageReferral imageReferral;
    @JoinColumn(name = "electronicReferralId", referencedColumnName = "id")
    @ManyToOne
    private ElectronicReferral electronicReferral;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne
    private TDSCase tDSCase;
    @JoinColumn(name = "historyCaseId", referencedColumnName = "id")
    @ManyToOne
    private HistoryCase historyCase;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referralInfo")
    private List<TDSStudy> tDSStudyCollection;

    public ReferralInfo() {
    }

    public ReferralInfo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RISReferral getRISReferral() {
        return rISReferral;
    }

    public void setRISReferral(RISReferral rISReferral) {
        this.rISReferral = rISReferral;
    }

    public HISReferral getHISReferral() {
        return hISReferral;
    }

    public void setHISReferral(HISReferral hISReferral) {
        this.hISReferral = hISReferral;
    }

    public ImageReferral getImageReferral() {
        return imageReferral;
    }

    public void setImageReferral(ImageReferral imageReferral) {
        this.imageReferral = imageReferral;
    }

    public ElectronicReferral getElectronicReferral() {
        return electronicReferral;
    }

    public void setElectronicReferral(ElectronicReferral electronicReferral) {
        this.electronicReferral = electronicReferral;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
    }

    public HISReferral gethISReferral() {
        return hISReferral;
    }

    public void sethISReferral(HISReferral hISReferral) {
        this.hISReferral = hISReferral;
    }

    public RISReferral getrISReferral() {
        return rISReferral;
    }

    public void setrISReferral(RISReferral rISReferral) {
        this.rISReferral = rISReferral;
    }

    public List<TDSStudy> gettDSStudyCollection() {
        return tDSStudyCollection;
    }

    public void settDSStudyCollection(List<TDSStudy> tDSStudyCollection) {
        this.tDSStudyCollection = tDSStudyCollection;
    }

    public HistoryCase getHistoryCase() {
        return historyCase;
    }

    public void setHistoryCase(HistoryCase historyCase) {
        this.historyCase = historyCase;
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
        if (!(object instanceof ReferralInfo)) {
            return false;
        }
        ReferralInfo other = (ReferralInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generatedentity.ReferralInfo[id=" + id + "]";
    }
}
