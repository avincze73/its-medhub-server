/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import base.ITSEntity;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ScannedReferralImage")
@NamedQueries({
    @NamedQuery(name = "ScannedReferralImage.findAll", query = "SELECT s FROM ScannedReferralImage s"),
    @NamedQuery(name = "ScannedReferralImage.findById", query = "SELECT s FROM ScannedReferralImage s WHERE s.id = :id")})
public class ScannedReferralImage extends ITSEntity {
    @Basic(optional = false)
    @Lob
    @Column(name = "scannedImage")
    private byte[] scannedImage;
//    @JoinColumn(name = "imageReferralId", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private ImageReferral imageReferral;

    public ScannedReferralImage() {
    }

    public ScannedReferralImage(Long id) {
        super(id);
    }


     public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        this.scannedImage = scannedImage;
    }


//    public ImageReferral getImageReferral() {
//        return imageReferral;
//    }
//
//    public void setImageReferral(ImageReferral imageReferral) {
//        this.imageReferral = imageReferral;
//    }

    
}
