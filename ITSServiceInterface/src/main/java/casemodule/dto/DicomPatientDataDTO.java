/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class DicomPatientDataDTO extends BaseDTO {

    private String patientName;
    private String patientId; //TAJ szám
    //private String patientIdName;
    private String issuerOfPatientId;
    private String typeOfPatientId;
    private Date dob;
    private String sex;
    private String otherPatientId;
    private String[] otherPatientIdRecords;
    private String otherPatientNames;
    private String comments;
    private String mothersBirthName;


    public DicomPatientDataDTO(long id) {
        super(id);
    }

    public DicomPatientDataDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DicomPatientDataDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        String oldValue = this.comments;
        this.comments = comments;
        propertyChangeSupport.firePropertyChange("comments", oldValue, this.comments);
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        Date oldValue = this.dob;
        this.dob = dob;
        propertyChangeSupport.firePropertyChange("dob", oldValue, this.dob);
    }

    public String getIssuerOfPatientId() {
        return issuerOfPatientId;
    }

    public void setIssuerOfPatientId(String issuerOfPatientId) {
        String oldValue = this.issuerOfPatientId;
        this.issuerOfPatientId = issuerOfPatientId;
        propertyChangeSupport.firePropertyChange("issuerOfPatientId", oldValue, this.issuerOfPatientId);
    }

    public String getMothersBirthName() {
        return mothersBirthName;
    }

    public void setMothersBirthName(String mothersBirthName) {
        String oldValue = this.mothersBirthName;
        this.mothersBirthName = mothersBirthName;
        propertyChangeSupport.firePropertyChange("mothersBirthName", oldValue, this.mothersBirthName);
    }

    public String getOtherPatientNames() {
        return otherPatientNames;
    }

    public void setOtherPatientNames(String otherPatientNames) {
        String oldValue = this.otherPatientNames;
        this.otherPatientNames = otherPatientNames;
        propertyChangeSupport.firePropertyChange("otherPatientNames", oldValue, this.otherPatientNames);
    }

    public String getOtherPatientId() {
        return otherPatientId;
    }

    public void setOtherPatientId(String otherPatientId) {
        String oldValue = this.otherPatientId;
        this.otherPatientId = otherPatientId;
        propertyChangeSupport.firePropertyChange("otherPatientId", oldValue, this.otherPatientId);
    }

    public String[] getOtherPatientIdRecords() {
        return otherPatientIdRecords;
    }

    public void setOtherPatientIdRecords(String[] otherPatientIdRecords) {
        String[] oldValue = this.otherPatientIdRecords;
        this.otherPatientIdRecords = otherPatientIdRecords;
        propertyChangeSupport.firePropertyChange("otherPatientIdRecords", oldValue, this.otherPatientIdRecords);
    }

    public byte[] getOtherPatientIdRecordsArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(otherPatientIdRecords);
        return baos.toByteArray();
    }

    public void setOtherPatientIdRecordsArray(byte[] array) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(array));
        otherPatientIdRecords = (String[]) ois.readObject();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        String oldValue = this.patientId;
        this.patientId = patientId;
        propertyChangeSupport.firePropertyChange("patientId", oldValue, this.patientId);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        String oldValue = this.patientName;
        this.patientName = patientName;
        propertyChangeSupport.firePropertyChange("patientName", oldValue, this.patientName);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        String oldValue = this.sex;
        this.sex = sex;
        propertyChangeSupport.firePropertyChange("sex", oldValue, this.sex);
    }

    public String getTypeOfPatientId() {
        return typeOfPatientId;
    }

    public void setTypeOfPatientId(String typeOfPatientId) {
        String oldValue = this.typeOfPatientId;
        this.typeOfPatientId = typeOfPatientId;
        propertyChangeSupport.firePropertyChange("typeOfPatientId", oldValue, this.typeOfPatientId);
    }
}
