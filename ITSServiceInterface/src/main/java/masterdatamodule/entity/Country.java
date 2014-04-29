/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.entity;

import base.ITSEntity;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Country")
public class Country extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "englishName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String englishName;
    @Basic(optional = false)
    @Column(name = "hungarianName")
    @Size(min=1, message="*")
    @NotNull(message="*")
    private String hungarianName;
    @JoinColumn(name = "currencyId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @NotNull(message="*")
    private Currency currency;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    //private List<Region> regionList;

    public Country() {
        this(null, null, null, null);
    }

    public Country(Long id) {
        this(id, null, null, null);
    }

    public Country(Long id, String englishName, String hungarianName) {
        this(id, englishName, hungarianName, null);
    }

    public Country(Long id, String englishName, String hungarianName, Currency currency) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.currency = currency;
    }

    public Country(String englishName, String hungarianName, Currency currency) {
        this(null, englishName, hungarianName, currency);
    }
    
    @Override
    public Country clone() throws CloneNotSupportedException {
        Country result = (Country) super.clone();
        result.setCurrency(currency.clone());
        return result;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        this.hungarianName = hungarianName;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

//    public List<Region> getRegionList() {
//        return regionList;
//    }
//
//    public void setRegionList(List<Region> regionList) {
//        this.regionList = regionList;
//    }
}
