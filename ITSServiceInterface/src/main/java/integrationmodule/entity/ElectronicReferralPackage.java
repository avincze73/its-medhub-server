/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationmodule.entity;

import base.ITSEntity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ElectronicReferral")
public class ElectronicReferralPackage extends ITSEntity {

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

    public ElectronicReferralPackage() {
    }

    public ElectronicReferralPackage(Long id) {
        this.id = id;
    }

    public ElectronicReferralPackage(Long id, Date dateOfReferral, String referralText, String referringInstitutionName, String referringDoctorName) {
        this.id = id;
        this.dateOfReferral = dateOfReferral;
        this.referralText = referralText;
        this.referringInstitutionName = referringInstitutionName;
        this.referringDoctorName = referringDoctorName;
    }

    @Override
    public ElectronicReferralPackage clone() throws CloneNotSupportedException {
        ElectronicReferralPackage result = (ElectronicReferralPackage) super.clone();
        return result;
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

}
