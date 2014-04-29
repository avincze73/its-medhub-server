/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ElectronicPatientData")
@NamedQueries({
    @NamedQuery(name = "ElectronicPatientData.findAll", query = "SELECT e FROM ElectronicPatientData e"),
    @NamedQuery(name = "ElectronicPatientData.findById", query = "SELECT e FROM ElectronicPatientData e WHERE e.id = :id"),
    @NamedQuery(name = "ElectronicPatientData.findByPatientIdInHospital", query = "SELECT e FROM ElectronicPatientData e WHERE e.patientIdInHospital = :patientIdInHospital"),
    @NamedQuery(name = "ElectronicPatientData.findByPatientName", query = "SELECT e FROM ElectronicPatientData e WHERE e.patientName = :patientName"),
    @NamedQuery(name = "ElectronicPatientData.findByGender", query = "SELECT e FROM ElectronicPatientData e WHERE e.gender = :gender"),
    @NamedQuery(name = "ElectronicPatientData.findByIdOrSimilar", query = "SELECT e FROM ElectronicPatientData e WHERE e.idOrSimilar = :idOrSimilar"),
    @NamedQuery(name = "ElectronicPatientData.findByTbOrNiOrSimilar", query = "SELECT e FROM ElectronicPatientData e WHERE e.tbOrNiOrSimilar = :tbOrNiOrSimilar"),
    @NamedQuery(name = "ElectronicPatientData.findByDob", query = "SELECT e FROM ElectronicPatientData e WHERE e.dob = :dob"),
    @NamedQuery(name = "ElectronicPatientData.findByAddress", query = "SELECT e FROM ElectronicPatientData e WHERE e.address = :address"),
    @NamedQuery(name = "ElectronicPatientData.findByContactInfo", query = "SELECT e FROM ElectronicPatientData e WHERE e.contactInfo = :contactInfo"),
    @NamedQuery(name = "ElectronicPatientData.findByContactTel", query = "SELECT e FROM ElectronicPatientData e WHERE e.contactTel = :contactTel"),
    @NamedQuery(name = "ElectronicPatientData.findByMothersName", query = "SELECT e FROM ElectronicPatientData e WHERE e.mothersName = :mothersName"),
    @NamedQuery(name = "ElectronicPatientData.findByNationality", query = "SELECT e FROM ElectronicPatientData e WHERE e.nationality = :nationality"),
    @NamedQuery(name = "ElectronicPatientData.findByOther", query = "SELECT e FROM ElectronicPatientData e WHERE e.other = :other")})
public class ElectronicPatientData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "patientIdInHospital")
    private String patientIdInHospital;
    @Column(name = "patientName")
    private String patientName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "idOrSimilar")
    private String idOrSimilar;
    @Column(name = "tbOrNiOrSimilar")
    private String tbOrNiOrSimilar;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "address")
    private String address;
    @Column(name = "contactInfo")
    private String contactInfo;
    @Column(name = "contactTel")
    private String contactTel;
    @Column(name = "mothersName")
    private String mothersName;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "other")
    private String other;
    @Column(name = "patientIdInHospital1Name")
    private String patientIdInHospital1Name;
    @Column(name = "patientIdInHospital1ShortName")
    private String patientIdInHospital1ShortName;
    @Column(name = "patientIdInHospital1Value")
    private String patientIdInHospital1Value;
    @Column(name = "patientIdInHospital2Name")
    private String patientIdInHospital2Name;
    @Column(name = "patientIdInHospital2ShortName")
    private String patientIdInHospital2ShortName;
    @Column(name = "patientIdInHospital2Value")
    private String patientIdInHospital2Value;
//    @OneToMany(mappedBy = "electronicPatientData")
//    private Collection<TDSCase> tDSCaseCollection;

    public ElectronicPatientData() {
    }

    public ElectronicPatientData(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientIdInHospital() {
        return patientIdInHospital;
    }

    public void setPatientIdInHospital(String patientIdInHospital) {
        this.patientIdInHospital = patientIdInHospital;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdOrSimilar() {
        return idOrSimilar;
    }

    public void setIdOrSimilar(String idOrSimilar) {
        this.idOrSimilar = idOrSimilar;
    }

    public String getTbOrNiOrSimilar() {
        return tbOrNiOrSimilar;
    }

    public void setTbOrNiOrSimilar(String tbOrNiOrSimilar) {
        this.tbOrNiOrSimilar = tbOrNiOrSimilar;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

//    public Collection<TDSCase> getTDSCaseCollection() {
//        return tDSCaseCollection;
//    }
//
//    public void setTDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
//        this.tDSCaseCollection = tDSCaseCollection;
//    }

    public String getPatientIdInHospital1Name() {
        return patientIdInHospital1Name;
    }

    public void setPatientIdInHospital1Name(String patientIdInHospital1Name) {
        this.patientIdInHospital1Name = patientIdInHospital1Name;
    }

    public String getPatientIdInHospital1ShortName() {
        return patientIdInHospital1ShortName;
    }

    public void setPatientIdInHospital1ShortName(String patientIdInHospital1ShortName) {
        this.patientIdInHospital1ShortName = patientIdInHospital1ShortName;
    }

    public String getPatientIdInHospital1Value() {
        return patientIdInHospital1Value;
    }

    public void setPatientIdInHospital1Value(String patientIdInHospital1Value) {
        this.patientIdInHospital1Value = patientIdInHospital1Value;
    }

    public String getPatientIdInHospital2Name() {
        return patientIdInHospital2Name;
    }

    public void setPatientIdInHospital2Name(String patientIdInHospital2Name) {
        this.patientIdInHospital2Name = patientIdInHospital2Name;
    }

    public String getPatientIdInHospital2ShortName() {
        return patientIdInHospital2ShortName;
    }

    public void setPatientIdInHospital2ShortName(String patientIdInHospital2ShortName) {
        this.patientIdInHospital2ShortName = patientIdInHospital2ShortName;
    }

    public String getPatientIdInHospital2Value() {
        return patientIdInHospital2Value;
    }

    public void setPatientIdInHospital2Value(String patientIdInHospital2Value) {
        this.patientIdInHospital2Value = patientIdInHospital2Value;
    }

//    public Collection<TDSCase> gettDSCaseCollection() {
//        return tDSCaseCollection;
//    }
//
//    public void settDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
//        this.tDSCaseCollection = tDSCaseCollection;
//    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicPatientData)) {
            return false;
        }
        ElectronicPatientData other = (ElectronicPatientData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElectronicPatientData[id=" + id + "]";
    }
}
