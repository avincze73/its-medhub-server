/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import base.ITSEntity;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ImageReferral")
@NamedQueries({
    @NamedQuery(name = "ImageReferral.findAll", query = "SELECT i FROM ImageReferral i"),
    @NamedQuery(name = "ImageReferral.findById", query = "SELECT i FROM ImageReferral i WHERE i.id = :id")})
public class ImageReferral extends ITSEntity {
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "imageReferralId")
    private List<ScannedReferralImage> scannedReferralImageList;

    @Column(name = "dummy")
    private Integer dummy;
    
    public ImageReferral() {
    }

    public ImageReferral(Long id) {
        this.id = id;
    }

    public List<ScannedReferralImage> getScannedReferralImageList() {
        return scannedReferralImageList;
    }

    public void setScannedReferralImageList(List<ScannedReferralImage> scannedReferralImageList) {
        this.scannedReferralImageList = scannedReferralImageList;
    }


    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer dummy) {
        this.dummy = dummy;
    }
    
    

   

}
