/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import hospitalmodule.entity.Hospital;
import java.util.ArrayList;
import masterdatamodule.entity.ITSLanguage;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "HospitalStaff")
public class HospitalStaff extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "positionInHosp")
    private String positionInHosp;
    @Basic(optional = false)
    @Column(name = "roleWithITS")
    private String roleWithITS;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "hospitalStaffList")
    private List<ITSLanguage> languageList;
    @JoinColumn(name = "hospitalId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Hospital hospital;
    @JoinColumn(name = "itsUserId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ITSUser itsUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospitalStaff")
    private List<HospitalStaffRoleAssignment> hospitalStaffRoleAssignmentList;

    public HospitalStaff() {
        this(null, null, null);
    }

    public HospitalStaff(Long id) {
        this(id, null, null);
    }

    public HospitalStaff(Long id, String positionInHosp, String roleWithITS) {
        super(id);
        this.positionInHosp = positionInHosp;
        this.roleWithITS = roleWithITS;
        languageList = new ArrayList<ITSLanguage>();
    }

    @Override
    public HospitalStaff clone() throws CloneNotSupportedException {
        HospitalStaff result = (HospitalStaff) super.clone();
        result.setItsUser(itsUser.clone());
        return result;
    }
    
    
    public String getPositionInHosp() {
        return positionInHosp;
    }

    public void setPositionInHosp(String positionInHosp) {
        this.positionInHosp = positionInHosp;
    }

    public String getRoleWithITS() {
        return roleWithITS;
    }

    public void setRoleWithITS(String roleWithITS) {
        this.roleWithITS = roleWithITS;
    }

    public List<ITSLanguage> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<ITSLanguage> languageList) {
        this.languageList = languageList;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<HospitalStaffRoleAssignment> getHospitalStaffRoleAssignmentList() {
        return hospitalStaffRoleAssignmentList;
    }

    public void setHospitalStaffRoleAssignmentList(List<HospitalStaffRoleAssignment> hospitalStaffRoleAssignmentList) {
        this.hospitalStaffRoleAssignmentList = hospitalStaffRoleAssignmentList;
    }

    public ITSUser getItsUser() {
        return itsUser;
    }

    public void setItsUser(ITSUser itsUser) {
        this.itsUser = itsUser;
    }
}
