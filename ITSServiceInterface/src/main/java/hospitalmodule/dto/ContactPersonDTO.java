/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class ContactPersonDTO extends BaseDTO {

    private String name;
    private String department;
    private String positionInDept;
    private String tel;
    private String fax;
    private String placeOfFax;
    private String email;
    private String skype;
    private String correspAddress;
    private HospitalDTO hospital;

    public ContactPersonDTO(long id) {
        super(id);
    }

    public ContactPersonDTO() {
        super();
    }

    public ContactPersonDTO(String name, String department,
            String positionInDept, String tel, String fax, String placeOfFax,
            String email, String skype, String correspAddress) {
        super();
        this.name = name;
        this.department = department;
        this.positionInDept = positionInDept;
        this.tel = tel;
        this.fax = fax;
        this.placeOfFax = placeOfFax;
        this.email = email;
        this.skype = skype;
        this.correspAddress = correspAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ContactPersonDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getCorrespAddress() {
        return correspAddress;
    }

    public void setCorrespAddress(String correspAddress) {
        String oldValue = this.correspAddress;
        this.correspAddress = correspAddress;
        propertyChangeSupport.firePropertyChange("correspAddress", oldValue, this.correspAddress);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        String oldValue = this.department;
        this.department = department;
        propertyChangeSupport.firePropertyChange("department", oldValue, this.department);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldValue = this.email;
        this.email = email;
        propertyChangeSupport.firePropertyChange("email", oldValue, this.email);
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        String oldValue = this.fax;
        this.fax = fax;
        propertyChangeSupport.firePropertyChange("fax", oldValue, this.fax);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);
    }

    public String getPlaceOfFax() {
        return placeOfFax;
    }

    public void setPlaceOfFax(String placeOfFax) {
        String oldValue = this.placeOfFax;
        this.placeOfFax = placeOfFax;
        propertyChangeSupport.firePropertyChange("placeOfFax", oldValue, this.placeOfFax);
    }

    public String getPositionInDept() {
        return positionInDept;
    }

    public void setPositionInDept(String positionInDept) {
        String oldValue = this.positionInDept;
        this.positionInDept = positionInDept;
        propertyChangeSupport.firePropertyChange("positionInDept", oldValue, this.positionInDept);
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        String oldValue = this.skype;
        this.skype = skype;
        propertyChangeSupport.firePropertyChange("skype", oldValue, this.skype);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        String oldValue = this.tel;
        this.tel = tel;
        propertyChangeSupport.firePropertyChange("tel", oldValue, this.tel);
    }

    public HospitalDTO getHospital() {
        return hospital;
    }

    public void setHospital(HospitalDTO hospital) {
        HospitalDTO oldValue = this.hospital;
        this.hospital = hospital;
        propertyChangeSupport.firePropertyChange("hospital", oldValue, this.hospital);
    }
}
