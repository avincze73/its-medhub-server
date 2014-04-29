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

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "DicomImage")
@NamedQueries({
    @NamedQuery(name = "DicomImage.findAll", query = "SELECT d FROM DicomImage d"),
    @NamedQuery(name = "DicomImage.findById", query = "SELECT d FROM DicomImage d WHERE d.id = :id"),
    @NamedQuery(name = "DicomImage.findByDicomUniqueId", query = "SELECT d FROM DicomImage d WHERE d.dicomUniqueId = :dicomUniqueId"),
    @NamedQuery(name = "DicomImage.findByInstanceUid", query = "SELECT d FROM DicomImage d WHERE d.instanceUid = :instanceUid"),
    @NamedQuery(name = "DicomImage.findBySopClassUid", query = "SELECT d FROM DicomImage d WHERE d.sopClassUid = :sopClassUid"),
    @NamedQuery(name = "DicomImage.findByTimeZoneOffsetFromUtc", query = "SELECT d FROM DicomImage d WHERE d.timeZoneOffsetFromUtc = :timeZoneOffsetFromUtc"),
    @NamedQuery(name = "DicomImage.findByInstanceNumber", query = "SELECT d FROM DicomImage d WHERE d.instanceNumber = :instanceNumber"),
    @NamedQuery(name = "DicomImage.findBySopInstanceStatus", query = "SELECT d FROM DicomImage d WHERE d.sopInstanceStatus = :sopInstanceStatus"),
    @NamedQuery(name = "DicomImage.findBySopAuthorizationDateTime", query = "SELECT d FROM DicomImage d WHERE d.sopAuthorizationDateTime = :sopAuthorizationDateTime"),
    @NamedQuery(name = "DicomImage.findBySopAuthorizationComment", query = "SELECT d FROM DicomImage d WHERE d.sopAuthorizationComment = :sopAuthorizationComment")})
public class DicomImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "dicomUniqueId")
    private String dicomUniqueId;
    @Column(name = "instanceUid")
    private String instanceUid;
    @Column(name = "sopClassUid")
    private String sopClassUid;
    @Column(name = "timeZoneOffsetFromUtc")
    private String timeZoneOffsetFromUtc;
    @Column(name = "instanceNumber")
    private String instanceNumber;
    @Column(name = "sopInstanceStatus")
    private String sopInstanceStatus;
    @Column(name = "sopAuthorizationDateTime")
    private String sopAuthorizationDateTime;
    @Column(name = "sopAuthorizationComment")
    private String sopAuthorizationComment;
    @Lob
    @Column(name = "dicomDataSet")
    private byte[] dicomDataSet;
    @Lob
    @Column(name = "dicomDataSetIcon")
    private byte[] dicomDataSetIcon;
    @JoinColumn(name = "seriesId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Series series;

    public DicomImage() {
    }

    public DicomImage(Long id) {
        this.id = id;
    }

    public DicomImage(Long id, String dicomUniqueId) {
        this.id = id;
        this.dicomUniqueId = dicomUniqueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDicomUniqueId() {
        return dicomUniqueId;
    }

    public void setDicomUniqueId(String dicomUniqueId) {
        this.dicomUniqueId = dicomUniqueId;
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public String getSopClassUid() {
        return sopClassUid;
    }

    public void setSopClassUid(String sopClassUid) {
        this.sopClassUid = sopClassUid;
    }

    public String getTimeZoneOffsetFromUtc() {
        return timeZoneOffsetFromUtc;
    }

    public void setTimeZoneOffsetFromUtc(String timeZoneOffsetFromUtc) {
        this.timeZoneOffsetFromUtc = timeZoneOffsetFromUtc;
    }

    public String getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(String instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public String getSopInstanceStatus() {
        return sopInstanceStatus;
    }

    public void setSopInstanceStatus(String sopInstanceStatus) {
        this.sopInstanceStatus = sopInstanceStatus;
    }

    public String getSopAuthorizationDateTime() {
        return sopAuthorizationDateTime;
    }

    public void setSopAuthorizationDateTime(String sopAuthorizationDateTime) {
        this.sopAuthorizationDateTime = sopAuthorizationDateTime;
    }

    public String getSopAuthorizationComment() {
        return sopAuthorizationComment;
    }

    public void setSopAuthorizationComment(String sopAuthorizationComment) {
        this.sopAuthorizationComment = sopAuthorizationComment;
    }

    public byte[] getDicomDataSet() {
        return dicomDataSet;
    }

    public void setDicomDataSet(byte[] dicomDataSet) {
        this.dicomDataSet = dicomDataSet;
    }

    public byte[] getDicomDataSetIcon() {
        return dicomDataSetIcon;
    }

    public void setDicomDataSetIcon(byte[] dicomDataSetIcon) {
        this.dicomDataSetIcon = dicomDataSetIcon;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
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
        if (!(object instanceof DicomImage)) {
            return false;
        }
        DicomImage other = (DicomImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DicomImage[id=" + id + "]";
    }

}
