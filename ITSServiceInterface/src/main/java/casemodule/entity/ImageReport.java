/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tds
 */
@Entity
@Table(name = "ImageReport")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImageReport.findAll", query = "SELECT i FROM ImageReport i"),
    @NamedQuery(name = "ImageReport.findById", query = "SELECT i FROM ImageReport i WHERE i.id = :id")})
public class ImageReport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id")
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "imageReport")
    private List<ScannedReportImage> scannedReportImageList;

    public ImageReport() {
    }

    public ImageReport(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public List<ScannedReportImage> getScannedReportImageList() {
        return scannedReportImageList;
    }

    public void setScannedReportImageList(List<ScannedReportImage> scannedReportImageList) {
        this.scannedReportImageList = scannedReportImageList;
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
        if (!(object instanceof ImageReport)) {
            return false;
        }
        ImageReport other = (ImageReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ImageReport[ id=" + id + " ]";
    }
    
}
