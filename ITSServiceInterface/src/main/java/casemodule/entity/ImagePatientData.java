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
@Table(name = "ImagePatientData")
@NamedQueries({
    @NamedQuery(name = "ImagePatientData.findAll", query = "SELECT i FROM ImagePatientData i"),
    @NamedQuery(name = "ImagePatientData.findById", query = "SELECT i FROM ImagePatientData i WHERE i.id = :id")})
public class ImagePatientData extends ITSEntity {

    //@OneToMany(mappedBy = "imagePatientData")
    //private Collection<TDSCase> tDSCaseCollection;
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "imagePatientData")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "imagePatientDataId")
    private List<ScannedPatientDataImage> scannedPatientDataImageList;
    @Column(name = "dummy")
    private Integer dummy;

    public ImagePatientData() {
    }

    public ImagePatientData(Long id) {
        this.id = id;
    }

    
//    public Collection<TDSCase> getTDSCaseCollection() {
//        return tDSCaseCollection;
//    }
//
//    public void setTDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
//        this.tDSCaseCollection = tDSCaseCollection;
//    }


    public List<ScannedPatientDataImage> getScannedPatientDataImageList() {
        return scannedPatientDataImageList;
    }

    public void setScannedPatientDataImageList(List<ScannedPatientDataImage> scannedPatientDataImageList) {
        this.scannedPatientDataImageList = scannedPatientDataImageList;
    }


    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer dummy) {
        this.dummy = dummy;
    }
    
    

    
}
