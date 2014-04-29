/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import casemodule.entity.TDSCase;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "DicomPatientData")
@NamedQueries({
    @NamedQuery(name = "DicomPatientData.findAll", query = "SELECT d FROM DicomPatientData d"),
    @NamedQuery(name = "DicomPatientData.findById", query = "SELECT d FROM DicomPatientData d WHERE d.id = :id"),
    @NamedQuery(name = "DicomPatientData.findByPatientName", query = "SELECT d FROM DicomPatientData d WHERE d.patientName = :patientName"),
    @NamedQuery(name = "DicomPatientData.findByPatientId", query = "SELECT d FROM DicomPatientData d WHERE d.patientId = :patientId"),
    @NamedQuery(name = "DicomPatientData.findByIssuerOfPatientId", query = "SELECT d FROM DicomPatientData d WHERE d.issuerOfPatientId = :issuerOfPatientId"),
    @NamedQuery(name = "DicomPatientData.findByTypeOfPatientId", query = "SELECT d FROM DicomPatientData d WHERE d.typeOfPatientId = :typeOfPatientId"),
    @NamedQuery(name = "DicomPatientData.findByDob", query = "SELECT d FROM DicomPatientData d WHERE d.dob = :dob"),
    @NamedQuery(name = "DicomPatientData.findBySex", query = "SELECT d FROM DicomPatientData d WHERE d.sex = :sex"),
    @NamedQuery(name = "DicomPatientData.findByOtherPatientId", query = "SELECT d FROM DicomPatientData d WHERE d.otherPatientId = :otherPatientId"),
    @NamedQuery(name = "DicomPatientData.findByOtherPatientNames", query = "SELECT d FROM DicomPatientData d WHERE d.otherPatientNames = :otherPatientNames"),
    @NamedQuery(name = "DicomPatientData.findByComments", query = "SELECT d FROM DicomPatientData d WHERE d.comments = :comments"),
    @NamedQuery(name = "DicomPatientData.findByMothersBirthName", query = "SELECT d FROM DicomPatientData d WHERE d.mothersBirthName = :mothersBirthName")})
public class DicomPatientData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "patientName")
    private String patientName;
    @Column(name = "patientId")
    private String patientId;
    @Column(name = "issuerOfPatientId")
    private String issuerOfPatientId;
    @Column(name = "typeOfPatientId")
    private String typeOfPatientId;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "sex")
    private String sex;
    @Column(name = "otherPatientId")
    private String otherPatientId;
    @Lob
    @Column(name = "otherPatientIdRecords")
    private byte[] otherPatientIdRecords;
    @Column(name = "otherPatientNames")
    private String otherPatientNames;
    @Column(name = "comments")
    private String comments;
    @Column(name = "mothersBirthName")
    private String mothersBirthName;
    @OneToMany(mappedBy = "dicomPatientData")
    private Collection<TDSCase> tDSCaseCollection;

    public DicomPatientData() {
    }

    public DicomPatientData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getIssuerOfPatientId() {
        return issuerOfPatientId;
    }

    public void setIssuerOfPatientId(String issuerOfPatientId) {
        this.issuerOfPatientId = issuerOfPatientId;
    }

    public String getTypeOfPatientId() {
        return typeOfPatientId;
    }

    public void setTypeOfPatientId(String typeOfPatientId) {
        this.typeOfPatientId = typeOfPatientId;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOtherPatientId() {
        return otherPatientId;
    }

    public void setOtherPatientId(String otherPatientId) {
        this.otherPatientId = otherPatientId;
    }

    public byte[] getOtherPatientIdRecords() {
        return otherPatientIdRecords;
    }

    public void setOtherPatientIdRecords(byte[] otherPatientIdRecords) {
        this.otherPatientIdRecords = otherPatientIdRecords;
    }

    public String getOtherPatientNames() {
        return otherPatientNames;
    }

    public void setOtherPatientNames(String otherPatientNames) {
        this.otherPatientNames = otherPatientNames;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMothersBirthName() {
        return mothersBirthName;
    }

    public void setMothersBirthName(String mothersBirthName) {
        this.mothersBirthName = mothersBirthName;
    }

    public Collection<TDSCase> getTDSCaseCollection() {
        return tDSCaseCollection;
    }

    public void setTDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
        this.tDSCaseCollection = tDSCaseCollection;
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
        if (!(object instanceof DicomPatientData)) {
            return false;
        }
        DicomPatientData other = (DicomPatientData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DicomPatientData[id=" + id + "]";
    }

}
