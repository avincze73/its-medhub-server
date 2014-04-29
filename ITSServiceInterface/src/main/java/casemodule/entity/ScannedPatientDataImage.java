/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import base.ITSEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ScannedPatientDataImage")
@NamedQueries({
    @NamedQuery(name = "ScannedPatientDataImage.findAll", query = "SELECT s FROM ScannedPatientDataImage s"),
    @NamedQuery(name = "ScannedPatientDataImage.findById", query = "SELECT s FROM ScannedPatientDataImage s WHERE s.id = :id")})
public class ScannedPatientDataImage extends ITSEntity {
    @Lob
    @Column(name = "scannedImage")
    private byte[] scannedImage;
//    @JoinColumn(name = "imagePatientDataId", referencedColumnName = "id")
//    @ManyToOne(optional = false)
//    private ImagePatientData imagePatientData;

    public ScannedPatientDataImage() {
    }

    public ScannedPatientDataImage(Long id) {
        super(id);
    }


    public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        this.scannedImage = scannedImage;
    }

//    public ImagePatientData getImagePatientData() {
//        return imagePatientData;
//    }
//
//    public void setImagePatientData(ImagePatientData imagePatientData) {
//        this.imagePatientData = imagePatientData;
//    }

}
