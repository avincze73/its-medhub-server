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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ElectronicReferral")
@NamedQueries({
    @NamedQuery(name = "ElectronicReferral.findAll", query = "SELECT e FROM ElectronicReferral e"),
    @NamedQuery(name = "ElectronicReferral.findById", query = "SELECT e FROM ElectronicReferral e WHERE e.id = :id"),
    @NamedQuery(name = "ElectronicReferral.findByPlaceOfReferral", query = "SELECT e FROM ElectronicReferral e WHERE e.placeOfReferral = :placeOfReferral"),
    @NamedQuery(name = "ElectronicReferral.findByDateOfReferral", query = "SELECT e FROM ElectronicReferral e WHERE e.dateOfReferral = :dateOfReferral"),
    @NamedQuery(name = "ElectronicReferral.findByReferralText", query = "SELECT e FROM ElectronicReferral e WHERE e.referralText = :referralText"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionName", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionName = :referringInstitutionName"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionAddress", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionAddress = :referringInstitutionAddress"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionCode", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionCode = :referringInstitutionCode"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionContacts", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionContacts = :referringInstitutionContacts"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionTel", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionTel = :referringInstitutionTel"),
    @NamedQuery(name = "ElectronicReferral.findByReferringInstitutionFax", query = "SELECT e FROM ElectronicReferral e WHERE e.referringInstitutionFax = :referringInstitutionFax"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitName", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitName = :referringUnitName"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitAddress", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitAddress = :referringUnitAddress"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitCode", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitCode = :referringUnitCode"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitContacts", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitContacts = :referringUnitContacts"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitTel", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitTel = :referringUnitTel"),
    @NamedQuery(name = "ElectronicReferral.findByReferringUnitFax", query = "SELECT e FROM ElectronicReferral e WHERE e.referringUnitFax = :referringUnitFax"),
    @NamedQuery(name = "ElectronicReferral.findByReferringDoctorName", query = "SELECT e FROM ElectronicReferral e WHERE e.referringDoctorName = :referringDoctorName"),
    @NamedQuery(name = "ElectronicReferral.findByReferringDoctorCode", query = "SELECT e FROM ElectronicReferral e WHERE e.referringDoctorCode = :referringDoctorCode"),
    @NamedQuery(name = "ElectronicReferral.findByReferringDoctorContacts", query = "SELECT e FROM ElectronicReferral e WHERE e.referringDoctorContacts = :referringDoctorContacts"),
    @NamedQuery(name = "ElectronicReferral.findByReferringDoctorTel", query = "SELECT e FROM ElectronicReferral e WHERE e.referringDoctorTel = :referringDoctorTel"),
    @NamedQuery(name = "ElectronicReferral.findByReferringDoctorFax", query = "SELECT e FROM ElectronicReferral e WHERE e.referringDoctorFax = :referringDoctorFax"),
    @NamedQuery(name = "ElectronicReferral.findByReferralCodeAndDescriptionList", query = "SELECT e FROM ElectronicReferral e WHERE e.referralCodeAndDescriptionList = :referralCodeAndDescriptionList"),
    @NamedQuery(name = "ElectronicReferral.findByReferralCodeList", query = "SELECT e FROM ElectronicReferral e WHERE e.referralCodeList = :referralCodeList"),
    @NamedQuery(name = "ElectronicReferral.findByReferralDescriptionList", query = "SELECT e FROM ElectronicReferral e WHERE e.referralDescriptionList = :referralDescriptionList")})
public class ElectronicReferral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "placeOfReferral")
    private String placeOfReferral;
    @Column(name = "dateOfReferral")
    @Temporal(TemporalType.DATE)
    private Date dateOfReferral;
    @Column(name = "referralText")
    private String referralText;
    @Column(name = "referringInstitutionName")
    private String referringInstitutionName;
    @Column(name = "referringInstitutionAddress")
    private String referringInstitutionAddress;
    @Column(name = "referringInstitutionCode")
    private String referringInstitutionCode;
    @Column(name = "referringInstitutionContacts")
    private String referringInstitutionContacts;
    @Column(name = "referringInstitutionTel")
    private String referringInstitutionTel;
    @Column(name = "referringInstitutionFax")
    private String referringInstitutionFax;
    @Column(name = "referringUnitName")
    private String referringUnitName;
    @Column(name = "referringUnitAddress")
    private String referringUnitAddress;
    @Column(name = "referringUnitCode")
    private String referringUnitCode;
    @Column(name = "referringUnitContacts")
    private String referringUnitContacts;
    @Column(name = "referringUnitTel")
    private String referringUnitTel;
    @Column(name = "referringUnitFax")
    private String referringUnitFax;
    @Column(name = "referringDoctorName")
    private String referringDoctorName;
    @Column(name = "referringDoctorCode")
    private String referringDoctorCode;
    @Column(name = "referringDoctorContacts")
    private String referringDoctorContacts;
    @Column(name = "referringDoctorTel")
    private String referringDoctorTel;
    @Column(name = "referringDoctorFax")
    private String referringDoctorFax;
    @Column(name = "referralCodeAndDescriptionList")
    private String referralCodeAndDescriptionList;
    @Column(name = "referralCodeList")
    private String referralCodeList;
    @Column(name = "referralDescriptionList")
    private String referralDescriptionList;
    @Column(name = "referralNumber1Name")
    private String referralNumber1Name;
    @Column(name = "referralNumber1ShortName")
    private String referralNumber1ShortName;
    @Column(name = "referralNumber1Value")
    private String referralNumber1Value;
    @Column(name = "referralNumber2Name")
    private String referralNumber2Name;
    @Column(name = "referralNumber2ShortName")
    private String referralNumber2ShortName;
    @Column(name = "referralNumber2Value")
    private String referralNumber2Value;
//    @OneToMany(mappedBy = "electronicReferral")
//    private Collection<TDSCase> tDSCaseCollection;

    public ElectronicReferral() {
    }

    public ElectronicReferral(Long id) {
        this.id = id;
    }

    public ElectronicReferral(Long id, Date dateOfReferral, String referralText, String referringInstitutionName, String referringDoctorName) {
        this.id = id;
        this.dateOfReferral = dateOfReferral;
        this.referralText = referralText;
        this.referringInstitutionName = referringInstitutionName;
        this.referringDoctorName = referringDoctorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceOfReferral() {
        return placeOfReferral;
    }

    public void setPlaceOfReferral(String placeOfReferral) {
        this.placeOfReferral = placeOfReferral;
    }

    public Date getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(Date dateOfReferral) {
        this.dateOfReferral = dateOfReferral;
    }

    public String getReferralText() {
        return referralText;
    }

    public void setReferralText(String referralText) {
        this.referralText = referralText;
    }

    public String getReferringInstitutionName() {
        return referringInstitutionName;
    }

    public void setReferringInstitutionName(String referringInstitutionName) {
        this.referringInstitutionName = referringInstitutionName;
    }

    public String getReferringInstitutionAddress() {
        return referringInstitutionAddress;
    }

    public void setReferringInstitutionAddress(String referringInstitutionAddress) {
        this.referringInstitutionAddress = referringInstitutionAddress;
    }

    public String getReferringInstitutionCode() {
        return referringInstitutionCode;
    }

    public void setReferringInstitutionCode(String referringInstitutionCode) {
        this.referringInstitutionCode = referringInstitutionCode;
    }

    public String getReferringInstitutionContacts() {
        return referringInstitutionContacts;
    }

    public void setReferringInstitutionContacts(String referringInstitutionContacts) {
        this.referringInstitutionContacts = referringInstitutionContacts;
    }

    public String getReferringInstitutionTel() {
        return referringInstitutionTel;
    }

    public void setReferringInstitutionTel(String referringInstitutionTel) {
        this.referringInstitutionTel = referringInstitutionTel;
    }

    public String getReferringInstitutionFax() {
        return referringInstitutionFax;
    }

    public void setReferringInstitutionFax(String referringInstitutionFax) {
        this.referringInstitutionFax = referringInstitutionFax;
    }

    public String getReferringUnitName() {
        return referringUnitName;
    }

    public void setReferringUnitName(String referringUnitName) {
        this.referringUnitName = referringUnitName;
    }

    public String getReferringUnitAddress() {
        return referringUnitAddress;
    }

    public void setReferringUnitAddress(String referringUnitAddress) {
        this.referringUnitAddress = referringUnitAddress;
    }

    public String getReferringUnitCode() {
        return referringUnitCode;
    }

    public void setReferringUnitCode(String referringUnitCode) {
        this.referringUnitCode = referringUnitCode;
    }

    public String getReferringUnitContacts() {
        return referringUnitContacts;
    }

    public void setReferringUnitContacts(String referringUnitContacts) {
        this.referringUnitContacts = referringUnitContacts;
    }

    public String getReferringUnitTel() {
        return referringUnitTel;
    }

    public void setReferringUnitTel(String referringUnitTel) {
        this.referringUnitTel = referringUnitTel;
    }

    public String getReferringUnitFax() {
        return referringUnitFax;
    }

    public void setReferringUnitFax(String referringUnitFax) {
        this.referringUnitFax = referringUnitFax;
    }

    public String getReferringDoctorName() {
        return referringDoctorName;
    }

    public void setReferringDoctorName(String referringDoctorName) {
        this.referringDoctorName = referringDoctorName;
    }

    public String getReferringDoctorCode() {
        return referringDoctorCode;
    }

    public void setReferringDoctorCode(String referringDoctorCode) {
        this.referringDoctorCode = referringDoctorCode;
    }

    public String getReferringDoctorContacts() {
        return referringDoctorContacts;
    }

    public void setReferringDoctorContacts(String referringDoctorContacts) {
        this.referringDoctorContacts = referringDoctorContacts;
    }

    public String getReferringDoctorTel() {
        return referringDoctorTel;
    }

    public void setReferringDoctorTel(String referringDoctorTel) {
        this.referringDoctorTel = referringDoctorTel;
    }

    public String getReferringDoctorFax() {
        return referringDoctorFax;
    }

    public void setReferringDoctorFax(String referringDoctorFax) {
        this.referringDoctorFax = referringDoctorFax;
    }

    public String getReferralCodeAndDescriptionList() {
        return referralCodeAndDescriptionList;
    }

    public void setReferralCodeAndDescriptionList(String referralCodeAndDescriptionList) {
        this.referralCodeAndDescriptionList = referralCodeAndDescriptionList;
    }

    public String getReferralCodeList() {
        return referralCodeList;
    }

    public void setReferralCodeList(String referralCodeList) {
        this.referralCodeList = referralCodeList;
    }

    public String getReferralDescriptionList() {
        return referralDescriptionList;
    }

    public void setReferralDescriptionList(String referralDescriptionList) {
        this.referralDescriptionList = referralDescriptionList;
    }

//    public Collection<TDSCase> getTDSCaseCollection() {
//        return tDSCaseCollection;
//    }
//
//    public void setTDSCaseCollection(Collection<TDSCase> tDSCaseCollection) {
//        this.tDSCaseCollection = tDSCaseCollection;
//    }

    public String getReferralNumber1Name() {
        return referralNumber1Name;
    }

    public void setReferralNumber1Name(String referralNumber1Name) {
        this.referralNumber1Name = referralNumber1Name;
    }

    public String getReferralNumber1ShortName() {
        return referralNumber1ShortName;
    }

    public void setReferralNumber1ShortName(String referralNumber1ShortName) {
        this.referralNumber1ShortName = referralNumber1ShortName;
    }

    public String getReferralNumber1Value() {
        return referralNumber1Value;
    }

    public void setReferralNumber1Value(String referralNumber1Value) {
        this.referralNumber1Value = referralNumber1Value;
    }

    public String getReferralNumber2Name() {
        return referralNumber2Name;
    }

    public void setReferralNumber2Name(String referralNumber2Name) {
        this.referralNumber2Name = referralNumber2Name;
    }

    public String getReferralNumber2ShortName() {
        return referralNumber2ShortName;
    }

    public void setReferralNumber2ShortName(String referralNumber2ShortName) {
        this.referralNumber2ShortName = referralNumber2ShortName;
    }

    public String getReferralNumber2Value() {
        return referralNumber2Value;
    }

    public void setReferralNumber2Value(String referralNumber2Value) {
        this.referralNumber2Value = referralNumber2Value;
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
        if (!(object instanceof ElectronicReferral)) {
            return false;
        }
        ElectronicReferral other = (ElectronicReferral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ElectronicReferral[id=" + id + "]";
    }
}
