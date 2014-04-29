/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class ElectronicReferralDTO extends BaseDTO {

    private String placeOfReferral;
    private Date dateOfReferral;
    private String referralText;
    private String referringInstitutionName;
    private String referringInstitutionAddress;
    private String referringInstitutionCode;
    private String referringInstitutionContacts;
    private String referringInstitutionTel;
    private String referringInstitutionFax;
    private String referringUnitName;
    private String referringUnitAddress;
    private String referringUnitCode;
    private String referringUnitContacts;
    private String referringUnitTel;
    private String referringUnitFax;
    private String referringDoctorName;
    private String referringDoctorCode;
    private String referringDoctorContacts;
    private String referringDoctorTel;
    private String referringDoctorFax;
    private String referralCodeAndDescriptionList;
    private String referralCodeList;
    private String referralDescriptionList;
    //
    private String referralNumber1Name;
    private String referralNumber2Name;
    private String referralNumber1ShortName;
    private String referralNumber2ShortName;
    private String referralNumber1Value;
    private String referralNumber2Value;

    public ElectronicReferralDTO() {
        this(0);
    }

    public ElectronicReferralDTO(long id) {
        super(id);
    }

    public ElectronicReferralDTO(String placeOfReferral, Date dateOfReferral,
            String referralText, String referringInstitutionName,
            String referringInstitutionAddress, String referringInstitutionCode,
            String referringInstitutionContacts, String referringInstitutionTel,
            String referringInstitutionFax, String referringUnitName,
            String referringUnitAddress, String referringUnitCode,
            String referringUnitContacts, String referringUnitTel,
            String referringUnitFax, String referringDoctorName,
            String referringDoctorCode, String referringDoctorContacts,
            String referringDoctorTel, String referringDoctorFax,
            String referralCodeAndDescriptionList, String referralCodeList,
            String referralDescriptionList) {
        super();
        this.placeOfReferral = placeOfReferral;
        this.dateOfReferral = dateOfReferral;
        this.referralText = referralText;
        this.referringInstitutionName = referringInstitutionName;
        this.referringInstitutionAddress = referringInstitutionAddress;
        this.referringInstitutionCode = referringInstitutionCode;
        this.referringInstitutionContacts = referringInstitutionContacts;
        this.referringInstitutionTel = referringInstitutionTel;
        this.referringInstitutionFax = referringInstitutionFax;
        this.referringUnitName = referringUnitName;
        this.referringUnitAddress = referringUnitAddress;
        this.referringUnitCode = referringUnitCode;
        this.referringUnitContacts = referringUnitContacts;
        this.referringUnitTel = referringUnitTel;
        this.referringUnitFax = referringUnitFax;
        this.referringDoctorName = referringDoctorName;
        this.referringDoctorCode = referringDoctorCode;
        this.referringDoctorContacts = referringDoctorContacts;
        this.referringDoctorTel = referringDoctorTel;
        this.referringDoctorFax = referringDoctorFax;
        this.referralCodeAndDescriptionList = referralCodeAndDescriptionList;
        this.referralCodeList = referralCodeList;
        this.referralDescriptionList = referralDescriptionList;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElectronicReferralDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getDateOfReferral() {
        return dateOfReferral;
    }

    public void setDateOfReferral(Date dateOfReferral) {
        Date oldValue = this.dateOfReferral;
        this.dateOfReferral = dateOfReferral;
        propertyChangeSupport.firePropertyChange("dateOfReferral", oldValue, this.dateOfReferral);
    }

    public String getPlaceOfReferral() {
        return placeOfReferral;
    }

    public void setPlaceOfReferral(String placeOfReferral) {
        String oldValue = this.placeOfReferral;
        this.placeOfReferral = placeOfReferral;
        propertyChangeSupport.firePropertyChange("placeOfReferral", oldValue, this.placeOfReferral);
    }

    public String getReferralCodeAndDescriptionList() {
        return referralCodeAndDescriptionList;
    }

    public void setReferralCodeAndDescriptionList(String referralCodeAndDescriptionList) {
        String oldValue = this.referralCodeAndDescriptionList;
        this.referralCodeAndDescriptionList = referralCodeAndDescriptionList;
        propertyChangeSupport.firePropertyChange("referralCodeAndDescriptionList", oldValue, this.referralCodeAndDescriptionList);
    }

    public String getReferralCodeList() {
        return referralCodeList;
    }

    public void setReferralCodeList(String referralCodeList) {
        String oldValue = this.referralCodeList;
        this.referralCodeList = referralCodeList;
        propertyChangeSupport.firePropertyChange("referralCodeList", oldValue, this.referralCodeList);

    }

    public String getReferralDescriptionList() {
        return referralDescriptionList;
    }

    public void setReferralDescriptionList(String referralDescriptionList) {
        String oldValue = this.referralDescriptionList;
        this.referralDescriptionList = referralDescriptionList;
        propertyChangeSupport.firePropertyChange("referralDescriptionList", oldValue, this.referralDescriptionList);
    }

    public String getReferralText() {
        return referralText;
    }

    public void setReferralText(String referralText) {
        String oldValue = this.referralText;
        this.referralText = referralText;
        propertyChangeSupport.firePropertyChange("referralText", oldValue, this.referralText);
    }

    public String getReferringDoctorCode() {
        return referringDoctorCode;
    }

    public void setReferringDoctorCode(String referringDoctorCode) {
        String oldValue = this.referringDoctorCode;
        this.referringDoctorCode = referringDoctorCode;
        propertyChangeSupport.firePropertyChange("referringDoctorCode", oldValue, this.referringDoctorCode);
    }

    public String getReferringDoctorContacts() {
        return referringDoctorContacts;
    }

    public void setReferringDoctorContacts(String referringDoctorContacts) {
        String oldValue = this.referringDoctorContacts;
        this.referringDoctorContacts = referringDoctorContacts;
        propertyChangeSupport.firePropertyChange("referringDoctorContacts", oldValue, this.referringDoctorContacts);
    }

    public String getReferringDoctorFax() {
        return referringDoctorFax;
    }

    public void setReferringDoctorFax(String referringDoctorFax) {
        String oldValue = this.referringDoctorFax;
        this.referringDoctorFax = referringDoctorFax;
        propertyChangeSupport.firePropertyChange("referringDoctorFax", oldValue, this.referringDoctorFax);
    }

    public String getReferringDoctorName() {
        return referringDoctorName;
    }

    public void setReferringDoctorName(String referringDoctorName) {
        String oldValue = this.referringDoctorName;
        this.referringDoctorName = referringDoctorName;
        propertyChangeSupport.firePropertyChange("referringDoctorName", oldValue, this.referringDoctorName);
    }

    public String getReferringDoctorTel() {
        return referringDoctorTel;
    }

    public void setReferringDoctorTel(String referringDoctorTel) {
        String oldValue = this.referringDoctorTel;
        this.referringDoctorTel = referringDoctorTel;
        propertyChangeSupport.firePropertyChange("referringDoctorTel", oldValue, this.referringDoctorTel);
    }

    public String getReferringInstitutionAddress() {
        return referringInstitutionAddress;
    }

    public void setReferringInstitutionAddress(String referringInstitutionAddress) {
        String oldValue = this.referringInstitutionAddress;
        this.referringInstitutionAddress = referringInstitutionAddress;
        propertyChangeSupport.firePropertyChange("referringInstitutionAddress", oldValue, this.referringInstitutionAddress);
    }

    public String getReferringInstitutionCode() {
        return referringInstitutionCode;
    }

    public void setReferringInstitutionCode(String referringInstitutionCode) {
        String oldValue = this.referringInstitutionCode;
        this.referringInstitutionCode = referringInstitutionCode;
        propertyChangeSupport.firePropertyChange("referringInstitutionCode", oldValue, this.referringInstitutionCode);
    }

    public String getReferringInstitutionContacts() {
        return referringInstitutionContacts;
    }

    public void setReferringInstitutionContacts(String referringInstitutionContacts) {
        String oldValue = this.referringInstitutionContacts;
        this.referringInstitutionContacts = referringInstitutionContacts;
        propertyChangeSupport.firePropertyChange("referringInstitutionContacts", oldValue, this.referringInstitutionContacts);
    }

    public String getReferringInstitutionFax() {
        return referringInstitutionFax;
    }

    public void setReferringInstitutionFax(String referringInstitutionFax) {
        String oldValue = this.referringInstitutionFax;
        this.referringInstitutionFax = referringInstitutionFax;
        propertyChangeSupport.firePropertyChange("referringInstitutionFax", oldValue, this.referringInstitutionFax);
    }

    public String getReferringInstitutionName() {
        return referringInstitutionName;
    }

    public void setReferringInstitutionName(String referringInstitutionName) {
        String oldValue = this.referringInstitutionName;
        this.referringInstitutionName = referringInstitutionName;
        propertyChangeSupport.firePropertyChange("referringInstitutionName", oldValue, this.referringInstitutionName);
    }

    public String getReferringInstitutionTel() {
        return referringInstitutionTel;
    }

    public void setReferringInstitutionTel(String referringInstitutionTel) {
        String oldValue = this.referringInstitutionTel;
        this.referringInstitutionTel = referringInstitutionTel;
        propertyChangeSupport.firePropertyChange("referringInstitutionTel", oldValue, this.referringInstitutionTel);
    }

    public String getReferringUnitAddress() {
        return referringUnitAddress;
    }

    public void setReferringUnitAddress(String referringUnitAddress) {
        String oldValue = this.referringUnitAddress;
        this.referringUnitAddress = referringUnitAddress;
        propertyChangeSupport.firePropertyChange("referringUnitAddress", oldValue, this.referringUnitAddress);
    }

    public String getReferringUnitCode() {
        return referringUnitCode;
    }

    public void setReferringUnitCode(String referringUnitCode) {
        String oldValue = this.referringUnitCode;
        this.referringUnitCode = referringUnitCode;
        propertyChangeSupport.firePropertyChange("referringUnitCode", oldValue, this.referringUnitCode);
    }

    public String getReferringUnitContacts() {
        return referringUnitContacts;
    }

    public void setReferringUnitContacts(String referringUnitContacts) {
        String oldValue = this.referringUnitContacts;
        this.referringUnitContacts = referringUnitContacts;
        propertyChangeSupport.firePropertyChange("referringUnitContacts", oldValue, this.referringUnitContacts);
    }

    public String getReferringUnitFax() {
        return referringUnitFax;
    }

    public void setReferringUnitFax(String referringUnitFax) {
        String oldValue = this.referringUnitFax;
        this.referringUnitFax = referringUnitFax;
        propertyChangeSupport.firePropertyChange("referringUnitFax", oldValue, this.referringUnitFax);
    }

    public String getReferringUnitName() {
        return referringUnitName;
    }

    public void setReferringUnitName(String referringUnitName) {
        String oldValue = this.referringUnitName;
        this.referringUnitName = referringUnitName;
        propertyChangeSupport.firePropertyChange("referringUnitName", oldValue, this.referringUnitName);
    }

    public String getReferringUnitTel() {
        return referringUnitTel;
    }

    public void setReferringUnitTel(String referringUnitTel) {
        String oldValue = this.referringUnitTel;
        this.referringUnitTel = referringUnitTel;
        propertyChangeSupport.firePropertyChange("referringUnitTel", oldValue, this.referringUnitTel);
    }

    public String getReferralNumber1Name() {
        return referralNumber1Name;
    }

    public void setReferralNumber1Name(String referralNumber1Name) {
        String oldValue = this.referralNumber1Name;
        this.referralNumber1Name = referralNumber1Name;
        propertyChangeSupport.firePropertyChange("referralNumber1Name", oldValue, this.referralNumber1Name);
    }

    public String getReferralNumber1ShortName() {
        return referralNumber1ShortName;
    }

    public void setReferralNumber1ShortName(String referralNumber1ShortName) {
        String oldValue = this.referralNumber1ShortName;
        this.referralNumber1ShortName = referralNumber1ShortName;
        propertyChangeSupport.firePropertyChange("referralNumber1ShortName", oldValue, this.referralNumber1ShortName);
    }

    public String getReferralNumber1Value() {
        return referralNumber1Value;
    }

    public void setReferralNumber1Value(String referralNumber1Value) {
        String oldValue = this.referralNumber1Value;
        this.referralNumber1Value = referralNumber1Value;
        propertyChangeSupport.firePropertyChange("referralNumber1Value", oldValue, this.referralNumber1Value);
    }

    public String getReferralNumber2Name() {
        return referralNumber2Name;
    }

    public void setReferralNumber2Name(String referralNumber2Name) {
        String oldValue = this.referralNumber2Name;
        this.referralNumber2Name = referralNumber2Name;
        propertyChangeSupport.firePropertyChange("referralNumber2Name", oldValue, this.referralNumber2Name);
    }

    public String getReferralNumber2ShortName() {
        return referralNumber2ShortName;
    }

    public void setReferralNumber2ShortName(String referralNumber2ShortName) {
        String oldValue = this.referralNumber2ShortName;
        this.referralNumber2ShortName = referralNumber2ShortName;
        propertyChangeSupport.firePropertyChange("referralNumber2ShortName", oldValue, this.referralNumber2ShortName);
    }

    public String getReferralNumber2Value() {
        return referralNumber2Value;
    }

    public void setReferralNumber2Value(String referralNumber2Value) {
        String oldValue = this.referralNumber2Value;
        this.referralNumber2Value = referralNumber2Value;
        propertyChangeSupport.firePropertyChange("referralNumber2Value", oldValue, this.referralNumber2Value);
    }


}
