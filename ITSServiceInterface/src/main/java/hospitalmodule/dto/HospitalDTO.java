/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.dto;

import common.exception.ConstraintException;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterdatamodule.dto.GoverningAuthorityDTO;
import masterdatamodule.dto.RegionDTO;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 */
public class HospitalDTO extends base.BaseDTO {

    private String officialName;
    private String shortName;
    private String abbrevName;
    private String address;
    private RegionDTO region;
    private String govBody;
    private GoverningAuthorityDTO govAuthority;
    private Date addingDate;
    private boolean active;
    private Integer qualityRequirements;
    private Integer timeZoneGmt;
    private List<HospitalContractDTO> hospitalContractList;
    private List<HospitalStaffDTO> hospitalStaffList;
    private List<ContactPersonDTO> contactPersonList;
    //active contract info
    private Date activeContractStarted;
    private Date activeContractEnded;

    public static HospitalDTO createNullObject() {
        HospitalDTO ret = new HospitalDTO();
        ret.setTimeZoneGmt(null);
        ret.setQualityRequirements(null);
        return ret;
    }

    public HospitalDTO() {
        this(0, null, null, null,
                null, new RegionDTO(), null,
                new GoverningAuthorityDTO(), null,
                false, 0, 0);
    }

    public HospitalDTO(long id) {
        this(id, null, null, null,
                null, new RegionDTO(), null,
                new GoverningAuthorityDTO(), null,
                false, 0, 0);
    }

    public HospitalDTO(long id, String officialName, String shortName, String abbrevName,
            String address, RegionDTO region, String govBody,
            GoverningAuthorityDTO govAuthority, Date addingDate,
            boolean active, Integer qualityRequirements, Integer timeZoneGmt) {
        super(id);
        this.officialName = officialName;
        this.shortName = shortName;
        this.abbrevName = abbrevName;
        this.address = address;
        this.region = region;
        this.govBody = govBody;
        this.govAuthority = govAuthority;
        this.addingDate = addingDate;
        this.active = active;
        this.qualityRequirements = qualityRequirements;
        this.timeZoneGmt = timeZoneGmt;
        hospitalContractList = hospitalContractList = new ArrayList<HospitalContractDTO>();
        hospitalStaffList = new ArrayList<HospitalStaffDTO>();
        contactPersonList = new ArrayList<ContactPersonDTO>();
    }


    @Override
    public HospitalDTO clone() throws CloneNotSupportedException {
        HospitalDTO result = (HospitalDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HospitalDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getAbbrevName() {
        return abbrevName;
    }

    public void setAbbrevName(String abbrevName) {
        String oldValue = this.abbrevName;
        this.abbrevName = abbrevName;
        propertyChangeSupport.firePropertyChange("abbrevName", oldValue, this.abbrevName);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        Date oldValue = this.addingDate;
        this.addingDate = addingDate;
        propertyChangeSupport.firePropertyChange("addingDate", oldValue, this.addingDate);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        String oldValue = this.address;
        this.address = address;
        propertyChangeSupport.firePropertyChange("address", oldValue, this.address);
    }

    public GoverningAuthorityDTO getGovAuthority() {
        return govAuthority;
    }

    public void setGovAuthority(GoverningAuthorityDTO govAuthority) {
        GoverningAuthorityDTO oldValue = this.govAuthority;
        this.govAuthority = govAuthority;
        propertyChangeSupport.firePropertyChange("govAuthority", oldValue, this.govAuthority);
    }

    public String getGovBody() {
        return govBody;
    }

    public void setGovBody(String govBody) {
        String oldValue = this.govBody;
        this.govBody = govBody;
        propertyChangeSupport.firePropertyChange("govBody", oldValue, this.govBody);
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        String oldValue = this.officialName;
        this.officialName = officialName;
        propertyChangeSupport.firePropertyChange("officialName", oldValue, this.officialName);
    }

    public Integer getQualityRequirements() {
        return qualityRequirements;
    }

    public void setQualityRequirements(Integer qualityRequirements) {
        Integer oldValue = this.qualityRequirements;
        this.qualityRequirements = qualityRequirements;
        propertyChangeSupport.firePropertyChange("qualityRequirements", oldValue, this.qualityRequirements);
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        RegionDTO oldValue = this.region;
        this.region = region;
        propertyChangeSupport.firePropertyChange("region", oldValue, this.region);
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        String oldValue = this.shortName;
        this.shortName = shortName;
        propertyChangeSupport.firePropertyChange("shortName", oldValue, this.shortName);
    }

    public List<HospitalContractDTO> getHospitalContractList() {
        return hospitalContractList;
    }

    public void setHospitalContractList(List<HospitalContractDTO> hospitalContractList) {
        List<HospitalContractDTO> oldValue = this.hospitalContractList;
        this.hospitalContractList = hospitalContractList;
        propertyChangeSupport.firePropertyChange("hospitalContractList", oldValue, this.hospitalContractList);
    }

    public List<HospitalStaffDTO> getHospitalStaffList() {
        return hospitalStaffList;
    }

    public void setHospitalStaffList(List<HospitalStaffDTO> hospitalStaffList) {
        List<HospitalStaffDTO> oldValue = this.hospitalStaffList;
        this.hospitalStaffList = hospitalStaffList;
        propertyChangeSupport.firePropertyChange("hospitalStaffList", oldValue, this.hospitalStaffList);
    }

    public Integer getTimeZoneGmt() {
        return timeZoneGmt;
    }

    public void setTimeZoneGmt(Integer timeZoneGmt) {
        Integer oldValue = this.timeZoneGmt;
        this.timeZoneGmt = timeZoneGmt;
        propertyChangeSupport.firePropertyChange("timeZoneGmt", oldValue, this.timeZoneGmt);
    }

    public List<ContactPersonDTO> getContactPersonList() {
        return contactPersonList;
    }

    public void setContactPersonList(List<ContactPersonDTO> contactPersonList) {
        List<ContactPersonDTO> oldValue = this.contactPersonList;
        this.contactPersonList = contactPersonList;
        propertyChangeSupport.firePropertyChange("contactPersonList", oldValue, this.contactPersonList);
    }

    public Date getActiveContractEnded() {
        return activeContractEnded;
    }

    public void setActiveContractEnded(Date activeContractEnded) {
        Date oldValue = this.activeContractEnded;
        this.activeContractEnded = activeContractEnded;
        propertyChangeSupport.firePropertyChange("activeContractEnded", oldValue, this.activeContractEnded);
    }

    public Date getActiveContractStarted() {
        return activeContractStarted;
    }

    public void setActiveContractStarted(Date activeContractStarted) {
        Date oldValue = this.activeContractStarted;
        this.activeContractStarted = activeContractStarted;
        propertyChangeSupport.firePropertyChange("activeContractStarted", oldValue, this.activeContractStarted);
    }

    @Override
    public void Validate(ResourceBundle bundle) throws ConstraintException {
        ArrayList<String> fields = new ArrayList<String>();
        if (officialName == null || "".equals(officialName)) {
            fields.add(bundle.getString("officialName"));
        }
        if (shortName == null || "".equals(shortName)) {
            fields.add(bundle.getString("shortName"));
        }
        if (address == null || "".equals(address)) {
            fields.add(bundle.getString("address"));
        }
        if (region == null || "".equals(region.getName())) {
            fields.add(bundle.getString("region"));
        }
        if (govBody == null || "".equals(govBody)) {
            fields.add(bundle.getString("govBody"));
        }
        if (govAuthority == null || "".equals(govAuthority.getName())) {
            fields.add(bundle.getString("govAuthority"));
        }
        if (fields.size() > 0) {
            throw new ConstraintException(
                    bundle.getString("mustBeFilled") + "\n" + fields.toString());
        }
    }

    public void update(HospitalDTO newItem) {
        setId(newItem.getId());
        setAbbrevName(newItem.getAbbrevName());
        setActive(newItem.isActive());
        setAddingDate(newItem.getAddingDate());
        setAddress(newItem.getAddress());
        setGovAuthority(newItem.getGovAuthority());
        setGovBody(newItem.getGovBody());
        setOfficialName(newItem.getOfficialName());
        setQualityRequirements(newItem.getQualityRequirements());
        setRegion(newItem.getRegion());
        setShortName(newItem.getShortName());
        setHospitalContractList(newItem.getHospitalContractList());
        setHospitalStaffList(newItem.getHospitalStaffList());
    }
}
