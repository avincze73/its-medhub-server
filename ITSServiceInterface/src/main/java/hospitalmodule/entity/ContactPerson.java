/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.entity;

import base.ITSEntity;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ContactPerson")
public class ContactPerson extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @Column(name = "positionInDept")
    private String positionInDept;
    @Basic(optional = false)
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @Column(name = "fax")
    private String fax;
    @Basic(optional = false)
    @Column(name = "placeOfFax")
    private String placeOfFax;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "skype")
    private String skype;
    @Basic(optional = false)
    @Column(name = "correspAddress")
    private String correspAddress;
    //
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hospital hospital;
    //
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contactPerson")
    private Collection<ContactPersonAssignment> contactPersonAssignmentCollection;
    

    public ContactPerson() {
    }

    public ContactPerson(Long id) {
        super(id);
    }

    public ContactPerson(Long id, String name, String department, String positionInDept, String tel, String fax, String placeOfFax, String email, String skype, String correspAddress) {
        super(id);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPositionInDept() {
        return positionInDept;
    }

    public void setPositionInDept(String positionInDept) {
        this.positionInDept = positionInDept;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPlaceOfFax() {
        return placeOfFax;
    }

    public void setPlaceOfFax(String placeOfFax) {
        this.placeOfFax = placeOfFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getCorrespAddress() {
        return correspAddress;
    }

    public void setCorrespAddress(String correspAddress) {
        this.correspAddress = correspAddress;
    }

    public Collection<ContactPersonAssignment> getContactPersonAssignmentCollection() {
        return contactPersonAssignmentCollection;
    }

    public void setContactPersonAssignmentCollection(Collection<ContactPersonAssignment> contactPersonAssignmentCollection) {
        this.contactPersonAssignmentCollection = contactPersonAssignmentCollection;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
