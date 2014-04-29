/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.entity;

import base.ITSEntity;
import masterdatamodule.entity.Region;
import radiologistmodule.entity.WorkPlace;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import usermodule.entity.HospitalStaff;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "Hospital")
public class Hospital extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "officialName")
    private String officialName;
    @Basic(optional = false)
    @Column(name = "shortName")
    private String shortName;
    @Column(name = "abbrevName")
    private String abbrevName;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "govBody")
    private String govBody;
    @Basic(optional = false)
    @Column(name = "addingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date addingDate;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "qualityRequirements")
    private int qualityRequirements;
    @Column(name = "timeZoneGmt")
    private Integer timeZoneGmt;
    @JoinColumn(name = "governingAuthorityId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GoverningAuthority governingAuthority;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Collection<HospitalStaff> hospitalStaffCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Collection<WorkPlace> workPlaceCollection;
    @JoinColumn(name = "regionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Region region;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Collection<HospitalContract> hospitalContractCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Collection<ContactPerson> contactPersonCollection;

    public Hospital() {
    }

    public Hospital(Long id) {
        super(id);
    }

    public Hospital(Long id, String officialName, String shortName, String address, String govBody, Date addingDate, boolean active, int qualityRequirements) {
        super(id);
        this.officialName = officialName;
        this.shortName = shortName;
        this.address = address;
        this.govBody = govBody;
        this.addingDate = addingDate;
        this.active = active;
        this.qualityRequirements = qualityRequirements;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        this.abbrevName = abbrevName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGovBody() {
        return govBody;
    }

    public void setGovBody(String govBody) {
        this.govBody = govBody;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getQualityRequirements() {
        return qualityRequirements;
    }

    public void setQualityRequirements(int qualityRequirements) {
        this.qualityRequirements = qualityRequirements;
    }

    public Integer getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(Integer timeZoneGmt) {
        this.timeZoneGmt = timeZoneGmt;
    }

    public Collection<HospitalStaff> getHospitalStaffCollection() {
        return hospitalStaffCollection;
    }

    public void setHospitalStaffCollection(Collection<HospitalStaff> hospitalStaffCollection) {
        this.hospitalStaffCollection = hospitalStaffCollection;
    }

    public Collection<WorkPlace> getWorkPlaceCollection() {
        return workPlaceCollection;
    }

    public void setWorkPlaceCollection(Collection<WorkPlace> workPlaceCollection) {
        this.workPlaceCollection = workPlaceCollection;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public GoverningAuthority getGoverningAuthority() {
        return governingAuthority;
    }

    public void setGoverningAuthority(GoverningAuthority governingAuthority) {
        this.governingAuthority = governingAuthority;
    }

    public Collection<HospitalContract> getHospitalContractCollection() {
        return hospitalContractCollection;
    }

    public void setHospitalContractCollection(Collection<HospitalContract> hospitalContractCollection) {
        this.hospitalContractCollection = hospitalContractCollection;
    }

    public Collection<ContactPerson> getContactPersonCollection() {
        return contactPersonCollection;
    }

    public void setContactPersonCollection(Collection<ContactPerson> contactPersonCollection) {
        this.contactPersonCollection = contactPersonCollection;
    }
}
