/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tds
 */
@Entity
@Table(name = "ScannedReportImage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScannedReportImage.findAll", query = "SELECT s FROM ScannedReportImage s"),
    @NamedQuery(name = "ScannedReportImage.findById", query = "SELECT s FROM ScannedReportImage s WHERE s.id = :id")})
public class ScannedReportImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    private Long id;
    @Lob
    @Column(name = "scannedImage")
    private byte[] scannedImage;
    @JoinColumn(name = "imageReportId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ImageReport imageReport;

    public ScannedReportImage() {
    }

    public ScannedReportImage(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getScannedImage() {
        return scannedImage;
    }

    public void setScannedImage(byte[] scannedImage) {
        this.scannedImage = scannedImage;
    }

    public ImageReport getImageReport() {
        return imageReport;
    }

    public void setImageReport(ImageReport imageReport) {
        this.imageReport = imageReport;
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
        if (!(object instanceof ScannedReportImage)) {
            return false;
        }
        ScannedReportImage other = (ScannedReportImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScannedReportImage[ id=" + id + " ]";
    }
    
}
