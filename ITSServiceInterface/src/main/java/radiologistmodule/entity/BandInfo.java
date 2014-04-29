/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.entity;

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
@Table(name = "BandInfo")
@NamedQueries({
    @NamedQuery(name = "BandInfo.findAll", query = "SELECT b FROM BandInfo b"),
    @NamedQuery(name = "BandInfo.findById", query = "SELECT b FROM BandInfo b WHERE b.id = :id"),
    @NamedQuery(name = "BandInfo.findByBandNumber", query = "SELECT b FROM BandInfo b WHERE b.bandNumber = :bandNumber"),
    @NamedQuery(name = "BandInfo.findByWorkTimeMinute", query = "SELECT b FROM BandInfo b WHERE b.workTimeMinute = :workTimeMinute"),
    @NamedQuery(name = "BandInfo.findByPriceForNormalWork", query = "SELECT b FROM BandInfo b WHERE b.priceForNormalWork = :priceForNormalWork"),
    @NamedQuery(name = "BandInfo.findByPriceForPayAsYouGoWork", query = "SELECT b FROM BandInfo b WHERE b.priceForPayAsYouGoWork = :priceForPayAsYouGoWork"),
    @NamedQuery(name = "BandInfo.findByPriceForExtraWork", query = "SELECT b FROM BandInfo b WHERE b.priceForExtraWork = :priceForExtraWork")})
public class BandInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "bandNumber")
    private int bandNumber;
    @Basic(optional = false)
    @Column(name = "workTimeMinute")
    private double workTimeMinute;
    @Basic(optional = false)
    @Column(name = "priceForNormalWork")
    private double priceForNormalWork;
    @Basic(optional = false)
    @Column(name = "priceForPayAsYouGoWork")
    private double priceForPayAsYouGoWork;
    @Basic(optional = false)
    @Column(name = "priceForExtraWork")
    private double priceForExtraWork;
    @JoinColumn(name = "workBandTableId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private WorkBandTable workBandTable;

    public BandInfo() {
    }

    public BandInfo(Long id) {
        this.id = id;
    }

    public BandInfo(Long id, int bandNumber, double workTimeMinute, double priceForNormalWork, double priceForPayAsYouGoWork, double priceForExtraWork) {
        this.id = id;
        this.bandNumber = bandNumber;
        this.workTimeMinute = workTimeMinute;
        this.priceForNormalWork = priceForNormalWork;
        this.priceForPayAsYouGoWork = priceForPayAsYouGoWork;
        this.priceForExtraWork = priceForExtraWork;
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

    public double getWorkTimeMinute() {
        return workTimeMinute;
    }

    public void setWorkTimeMinute(double workTimeMinute) {
        this.workTimeMinute = workTimeMinute;
    }

    public double getPriceForNormalWork() {
        return priceForNormalWork;
    }

    public void setPriceForNormalWork(double priceForNormalWork) {
        this.priceForNormalWork = priceForNormalWork;
    }

    public double getPriceForPayAsYouGoWork() {
        return priceForPayAsYouGoWork;
    }

    public void setPriceForPayAsYouGoWork(double priceForPayAsYouGoWork) {
        this.priceForPayAsYouGoWork = priceForPayAsYouGoWork;
    }

    public double getPriceForExtraWork() {
        return priceForExtraWork;
    }

    public void setPriceForExtraWork(double priceForExtraWork) {
        this.priceForExtraWork = priceForExtraWork;
    }

    public WorkBandTable getWorkBandTable() {
        return workBandTable;
    }

    public void setWorkBandTable(WorkBandTable workBandTable) {
        this.workBandTable = workBandTable;
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
        if (!(object instanceof BandInfo)) {
            return false;
        }
        BandInfo other = (BandInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BandInfo[id=" + id + "]";
    }

}
