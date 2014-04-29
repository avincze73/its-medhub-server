/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class ElectronicPatientDataDTO extends base.BaseDTO {

    private String patientIdInHospital;
    private String patientName;
    private String gender;
    private String idOrSimilar;
    private String tbOrNiOrSimilar;
    private Date dob;
    private String address;
    private String contactInfo;
    private String contactTel;
    private String mothersName;
    private String nationality;
    private String other;
    private String patientIdInHospital1Name;
    private String patientIdInHospital2Name;
    private String patientIdInHospital1Value;
    private String patientIdInHospital2Value;
    private String patientIdInHospital1ShortName;
    private String patientIdInHospital2ShortName;


    public ElectronicPatientDataDTO(long id) {
        super(id);
    }

    public ElectronicPatientDataDTO() {
        this(0);
    }

    public ElectronicPatientDataDTO(String patientIdInHospital, String patientName, String gender,
            String idOrSimilar, String tbOrNiOrSimilar, Date dob, String address,
            String contactInfo, String contactTel, String mothersName,
            String nationality, String other) {
        super();
        this.patientIdInHospital = patientIdInHospital;
        this.patientName = patientName;
        this.gender = gender;
        this.idOrSimilar = idOrSimilar;
        this.tbOrNiOrSimilar = tbOrNiOrSimilar;
        this.dob = dob;
        this.address = address;
        this.contactInfo = contactInfo;
        this.contactTel = contactTel;
        this.mothersName = mothersName;
        this.nationality = nationality;
        this.other = other;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ElectronicPatientDataDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldValue = this.address;
        this.address = address;
        propertyChangeSupport.firePropertyChange("address", oldValue, this.address);
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        String oldValue = this.contactInfo;
        this.contactInfo = contactInfo;
        propertyChangeSupport.firePropertyChange("contactInfo", oldValue, this.contactInfo);
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        String oldValue = this.contactTel;
        this.contactTel = contactTel;
        propertyChangeSupport.firePropertyChange("contactTel", oldValue, this.contactTel);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        Date oldValue = this.dob;
        this.dob = dob;
        propertyChangeSupport.firePropertyChange("dob", oldValue, this.dob);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        String oldValue = this.gender;
        this.gender = gender;
        propertyChangeSupport.firePropertyChange("gender", oldValue, this.gender);
    }

    public String getIdOrSimilar() {
        return idOrSimilar;
    }

    public void setIdOrSimilar(String idOrSimilar) {
        String oldValue = this.idOrSimilar;
        this.idOrSimilar = idOrSimilar;
        propertyChangeSupport.firePropertyChange("idOrSimilar", oldValue, this.idOrSimilar);
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        String oldValue = this.mothersName;
        this.mothersName = mothersName;
        propertyChangeSupport.firePropertyChange("mothersName", oldValue, this.mothersName);
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        String oldValue = this.nationality;
        this.nationality = nationality;
        propertyChangeSupport.firePropertyChange("nationality", oldValue, this.nationality);
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        String oldValue = this.other;
        this.other = other;
        propertyChangeSupport.firePropertyChange("other", oldValue, this.other);
    }

    public String getPatientIdInHospital() {
        return patientIdInHospital;
    }

    public void setPatientIdInHospital(String patientIdInHospital) {
        String oldValue = this.patientIdInHospital;
        this.patientIdInHospital = patientIdInHospital;
        propertyChangeSupport.firePropertyChange("patientIdInHospital", oldValue, this.patientIdInHospital);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        String oldValue = this.patientName;
        this.patientName = patientName;
        propertyChangeSupport.firePropertyChange("patientName", oldValue, this.patientName);
    }

    public String getTbOrNiOrSimilar() {
        return tbOrNiOrSimilar;
    }

    public void setTbOrNiOrSimilar(String tbOrNiOrSimilar) {
        String oldValue = this.tbOrNiOrSimilar;
        this.tbOrNiOrSimilar = tbOrNiOrSimilar;
        propertyChangeSupport.firePropertyChange("tbOrNiOrSimilar", oldValue, this.tbOrNiOrSimilar);
    }

    public String getPatientIdInHospital1Name() {
        return patientIdInHospital1Name;
    }

    public void setPatientIdInHospital1Name(String patientIdInHospital1Name) {
        String oldValue = this.patientIdInHospital1Name;
        this.patientIdInHospital1Name = patientIdInHospital1Name;
        propertyChangeSupport.firePropertyChange("patientIdInHospital1Name", oldValue, this.patientIdInHospital1Name);
    }

    public String getPatientIdInHospital1ShortName() {
        return patientIdInHospital1ShortName;
    }

    public void setPatientIdInHospital1ShortName(String patientIdInHospital1ShortName) {
        String oldValue = this.patientIdInHospital1ShortName;
        this.patientIdInHospital1ShortName = patientIdInHospital1ShortName;
        propertyChangeSupport.firePropertyChange("patientIdInHospital1ShortName", oldValue, this.patientIdInHospital1ShortName);
    }

    public String getPatientIdInHospital1Value() {
        return patientIdInHospital1Value;
    }

    public void setPatientIdInHospital1Value(String patientIdInHospital1Value) {
        String oldValue = this.patientIdInHospital1Value;
        this.patientIdInHospital1Value = patientIdInHospital1Value;
        propertyChangeSupport.firePropertyChange("patientIdInHospital1Value", oldValue, this.patientIdInHospital1Value);
    }

    public String getPatientIdInHospital2Name() {
        return patientIdInHospital2Name;
    }

    public void setPatientIdInHospital2Name(String patientIdInHospital2Name) {
        String oldValue = this.patientIdInHospital2Name;
        this.patientIdInHospital2Name = patientIdInHospital2Name;
        propertyChangeSupport.firePropertyChange("patientIdInHospital2Name", oldValue, this.patientIdInHospital2Name);
    }

    public String getPatientIdInHospital2ShortName() {
        return patientIdInHospital2ShortName;
    }

    public void setPatientIdInHospital2ShortName(String patientIdInHospital2ShortName) {
        String oldValue = this.patientIdInHospital2ShortName;
        this.patientIdInHospital2ShortName = patientIdInHospital2ShortName;
        propertyChangeSupport.firePropertyChange("patientIdInHospital2ShortName", oldValue, this.patientIdInHospital2ShortName);
    }

    public String getPatientIdInHospital2Value() {
        return patientIdInHospital2Value;
    }

    public void setPatientIdInHospital2Value(String patientIdInHospital2Value) {
        String oldValue = this.patientIdInHospital2Value;
        this.patientIdInHospital2Value = patientIdInHospital2Value;
        propertyChangeSupport.firePropertyChange("patientIdInHospital2Value", oldValue, this.patientIdInHospital2Value);
    }
}
