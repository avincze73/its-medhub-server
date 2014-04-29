/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import masterdatamodule.entity.Modality;
import masterdatamodule.entity.BodyRegion;
import casemodule.entity.TDSStudy;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Series")
@NamedQueries({
    @NamedQuery(name = "Series.findAll", query = "SELECT s FROM Series s"),
    @NamedQuery(name = "Series.findById", query = "SELECT s FROM Series s WHERE s.id = :id"),
    @NamedQuery(name = "Series.findByInitialWorkValue", query = "SELECT s FROM Series s WHERE s.initialWorkValue = :initialWorkValue"),
    @NamedQuery(name = "Series.findByInitialWorkTimeHospital", query = "SELECT s FROM Series s WHERE s.initialWorkTimeHospital = :initialWorkTimeHospital"),
    @NamedQuery(name = "Series.findByInitialWorkTimeRadiologist", query = "SELECT s FROM Series s WHERE s.initialWorkTimeRadiologist = :initialWorkTimeRadiologist"),
    @NamedQuery(name = "Series.findByInitialCalculated", query = "SELECT s FROM Series s WHERE s.initialCalculated = :initialCalculated"),
    @NamedQuery(name = "Series.findByFinalWorkValue", query = "SELECT s FROM Series s WHERE s.finalWorkValue = :finalWorkValue"),
    @NamedQuery(name = "Series.findByFinalWorkTimeHospital", query = "SELECT s FROM Series s WHERE s.finalWorkTimeHospital = :finalWorkTimeHospital"),
    @NamedQuery(name = "Series.findByFinalWorkTimeRadiologist", query = "SELECT s FROM Series s WHERE s.finalWorkTimeRadiologist = :finalWorkTimeRadiologist"),
    @NamedQuery(name = "Series.findByFinalCalculated", query = "SELECT s FROM Series s WHERE s.finalCalculated = :finalCalculated"),
    @NamedQuery(name = "Series.findByInstanceUid", query = "SELECT s FROM Series s WHERE s.instanceUid = :instanceUid"),
    @NamedQuery(name = "Series.findBySeriesNumberValue", query = "SELECT s FROM Series s WHERE s.seriesNumberValue = :seriesNumberValue"),
    @NamedQuery(name = "Series.findBySeriesNumberState", query = "SELECT s FROM Series s WHERE s.seriesNumberState = :seriesNumberState"),
    @NamedQuery(name = "Series.findByLaterality", query = "SELECT s FROM Series s WHERE s.laterality = :laterality"),
    @NamedQuery(name = "Series.findBySeriesDate", query = "SELECT s FROM Series s WHERE s.seriesDate = :seriesDate"),
    @NamedQuery(name = "Series.findBySeriesTime", query = "SELECT s FROM Series s WHERE s.seriesTime = :seriesTime"),
    @NamedQuery(name = "Series.findByPerformingPhysiciansDicomName", query = "SELECT s FROM Series s WHERE s.performingPhysiciansDicomName = :performingPhysiciansDicomName"),
    @NamedQuery(name = "Series.findByProtocolName", query = "SELECT s FROM Series s WHERE s.protocolName = :protocolName"),
    @NamedQuery(name = "Series.findByDescription", query = "SELECT s FROM Series s WHERE s.description = :description"),
    @NamedQuery(name = "Series.findByOperatorsDicomName", query = "SELECT s FROM Series s WHERE s.operatorsDicomName = :operatorsDicomName"),
    @NamedQuery(name = "Series.findByBodyPartExamined", query = "SELECT s FROM Series s WHERE s.bodyPartExamined = :bodyPartExamined"),
    @NamedQuery(name = "Series.findByClinicalTrialSeriesId", query = "SELECT s FROM Series s WHERE s.clinicalTrialSeriesId = :clinicalTrialSeriesId"),
    @NamedQuery(name = "Series.findByClinicalTrialSeriesDescription", query = "SELECT s FROM Series s WHERE s.clinicalTrialSeriesDescription = :clinicalTrialSeriesDescription"),
    @NamedQuery(name = "Series.findByFrameOfReferenceUid", query = "SELECT s FROM Series s WHERE s.frameOfReferenceUid = :frameOfReferenceUid"),
    @NamedQuery(name = "Series.findByEquipmentManufacturer", query = "SELECT s FROM Series s WHERE s.equipmentManufacturer = :equipmentManufacturer"),
    @NamedQuery(name = "Series.findByEquipmentLocationInstitutionName", query = "SELECT s FROM Series s WHERE s.equipmentLocationInstitutionName = :equipmentLocationInstitutionName"),
    @NamedQuery(name = "Series.findByEquipmentStationName", query = "SELECT s FROM Series s WHERE s.equipmentStationName = :equipmentStationName"),
    @NamedQuery(name = "Series.findByEquipmentInstitutionalDepartmentName", query = "SELECT s FROM Series s WHERE s.equipmentInstitutionalDepartmentName = :equipmentInstitutionalDepartmentName"),
    @NamedQuery(name = "Series.findByEquipmentManufacturersModelName", query = "SELECT s FROM Series s WHERE s.equipmentManufacturersModelName = :equipmentManufacturersModelName"),
    @NamedQuery(name = "Series.findByEquipmentDeviceSerialNumber", query = "SELECT s FROM Series s WHERE s.equipmentDeviceSerialNumber = :equipmentDeviceSerialNumber"),
    @NamedQuery(name = "Series.findByEquipmentDateOfLastCalibration", query = "SELECT s FROM Series s WHERE s.equipmentDateOfLastCalibration = :equipmentDateOfLastCalibration"),
    @NamedQuery(name = "Series.findByEquipmentTimeOfLastCalibration", query = "SELECT s FROM Series s WHERE s.equipmentTimeOfLastCalibration = :equipmentTimeOfLastCalibration"),
    @NamedQuery(name = "Series.findByRadiologistBodyRegionAdded", query = "SELECT s FROM Series s WHERE s.radiologistBodyRegionAdded = :radiologistBodyRegionAdded"),
    @NamedQuery(name = "Series.findBySupervisorBodyRegionAdded", query = "SELECT s FROM Series s WHERE s.supervisorBodyRegionAdded = :supervisorBodyRegionAdded")})
public class Series implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "initialWorkValue")
    private Double initialWorkValue;
    @Column(name = "initialWorkTimeHospital")
    private Double initialWorkTimeHospital;
    @Column(name = "initialWorkTimeRadiologist")
    private Double initialWorkTimeRadiologist;
    @Column(name = "initialCalculated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date initialCalculated;
    @Column(name = "finalWorkValue")
    private Double finalWorkValue;
    @Column(name = "finalWorkTimeHospital")
    private Double finalWorkTimeHospital;
    @Column(name = "finalWorkTimeRadiologist")
    private Double finalWorkTimeRadiologist;
    @Column(name = "finalCalculated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalCalculated;
    @Column(name = "instanceUid")
    private String instanceUid;
    @Column(name = "seriesNumberValue")
    private Integer seriesNumberValue;
    @Column(name = "seriesNumberState")
    private Boolean seriesNumberState;
    @Column(name = "laterality")
    private String laterality;
    @Column(name = "seriesDate")
    @Temporal(TemporalType.DATE)
    private Date seriesDate;
    @Column(name = "seriesTime")
    @Temporal(TemporalType.TIME)
    private Date seriesTime;
    @Column(name = "performingPhysiciansDicomName")
    private String performingPhysiciansDicomName;
    @Column(name = "protocolName")
    private String protocolName;
    @Column(name = "description")
    private String description;
    @Column(name = "operatorsDicomName")
    private String operatorsDicomName;
    @Column(name = "bodyPartExamined")
    private String bodyPartExamined;
    @Column(name = "clinicalTrialSeriesId")
    private String clinicalTrialSeriesId;
    @Column(name = "clinicalTrialSeriesDescription")
    private String clinicalTrialSeriesDescription;
    @Column(name = "frameOfReferenceUid")
    private String frameOfReferenceUid;
    @Column(name = "equipmentManufacturer")
    private String equipmentManufacturer;
    @Column(name = "equipmentLocationInstitutionName")
    private String equipmentLocationInstitutionName;
    @Column(name = "equipmentStationName")
    private String equipmentStationName;
    @Column(name = "equipmentInstitutionalDepartmentName")
    private String equipmentInstitutionalDepartmentName;
    @Column(name = "equipmentManufacturersModelName")
    private String equipmentManufacturersModelName;
    @Column(name = "equipmentDeviceSerialNumber")
    private String equipmentDeviceSerialNumber;
    @Column(name = "equipmentDateOfLastCalibration")
    @Temporal(TemporalType.DATE)
    private Date equipmentDateOfLastCalibration;
    @Column(name = "equipmentTimeOfLastCalibration")
    @Temporal(TemporalType.TIME)
    private Date equipmentTimeOfLastCalibration;
    @Column(name = "radiologistBodyRegionAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date radiologistBodyRegionAdded;
    @Column(name = "supervisorBodyRegionAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supervisorBodyRegionAdded;
    @Basic(optional = false)
    @Column(name = "numberInList")
    private Integer numberInList;
    @Column(name = "seriesMinRawValue")
    private Double seriesMinRawValue;
    @Column(name = "seriesMaxRawValue")
    private Double seriesMaxRawValue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "series")
    private Collection<DicomImage> dicomImageCollection;
    @JoinColumn(name = "radiologistBodyRegionId", referencedColumnName = "id")
    @ManyToOne
    private BodyRegion bodyRegion;
    @JoinColumn(name = "hospitalBodyRegionId", referencedColumnName = "id")
    @ManyToOne
    private BodyRegion bodyRegion1;
    @JoinColumn(name = "modalityId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modality modality;
    @JoinColumn(name = "supervisorBodyRegionId", referencedColumnName = "id")
    @ManyToOne
    private BodyRegion bodyRegion2;
    @JoinColumn(name = "studyId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSStudy tDSStudy;

    public Series() {
    }

    public Series(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getInitialWorkValue() {
        return initialWorkValue;
    }

    public void setInitialWorkValue(Double initialWorkValue) {
        this.initialWorkValue = initialWorkValue;
    }

    public Double getInitialWorkTimeHospital() {
        return initialWorkTimeHospital;
    }

    public void setInitialWorkTimeHospital(Double initialWorkTimeHospital) {
        this.initialWorkTimeHospital = initialWorkTimeHospital;
    }

    public Double getInitialWorkTimeRadiologist() {
        return initialWorkTimeRadiologist;
    }

    public void setInitialWorkTimeRadiologist(Double initialWorkTimeRadiologist) {
        this.initialWorkTimeRadiologist = initialWorkTimeRadiologist;
    }

    public Date getInitialCalculated() {
        return initialCalculated;
    }

    public void setInitialCalculated(Date initialCalculated) {
        this.initialCalculated = initialCalculated;
    }

    public Double getFinalWorkValue() {
        return finalWorkValue;
    }

    public void setFinalWorkValue(Double finalWorkValue) {
        this.finalWorkValue = finalWorkValue;
    }

    public Double getFinalWorkTimeHospital() {
        return finalWorkTimeHospital;
    }

    public void setFinalWorkTimeHospital(Double finalWorkTimeHospital) {
        this.finalWorkTimeHospital = finalWorkTimeHospital;
    }

    public Double getFinalWorkTimeRadiologist() {
        return finalWorkTimeRadiologist;
    }

    public void setFinalWorkTimeRadiologist(Double finalWorkTimeRadiologist) {
        this.finalWorkTimeRadiologist = finalWorkTimeRadiologist;
    }

    public Date getFinalCalculated() {
        return finalCalculated;
    }

    public void setFinalCalculated(Date finalCalculated) {
        this.finalCalculated = finalCalculated;
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public Integer getSeriesNumberValue() {
        return seriesNumberValue;
    }

    public void setSeriesNumberValue(Integer seriesNumberValue) {
        this.seriesNumberValue = seriesNumberValue;
    }

    public Boolean getSeriesNumberState() {
        return seriesNumberState;
    }

    public void setSeriesNumberState(Boolean seriesNumberState) {
        this.seriesNumberState = seriesNumberState;
    }

    public String getLaterality() {
        return laterality;
    }

    public void setLaterality(String laterality) {
        this.laterality = laterality;
    }

    public Date getSeriesDate() {
        return seriesDate;
    }

    public void setSeriesDate(Date seriesDate) {
        this.seriesDate = seriesDate;
    }

    public Date getSeriesTime() {
        return seriesTime;
    }

    public void setSeriesTime(Date seriesTime) {
        this.seriesTime = seriesTime;
    }

    public String getPerformingPhysiciansDicomName() {
        return performingPhysiciansDicomName;
    }

    public void setPerformingPhysiciansDicomName(String performingPhysiciansDicomName) {
        this.performingPhysiciansDicomName = performingPhysiciansDicomName;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperatorsDicomName() {
        return operatorsDicomName;
    }

    public void setOperatorsDicomName(String operatorsDicomName) {
        this.operatorsDicomName = operatorsDicomName;
    }

    public String getBodyPartExamined() {
        return bodyPartExamined;
    }

    public void setBodyPartExamined(String bodyPartExamined) {
        this.bodyPartExamined = bodyPartExamined;
    }

    public String getClinicalTrialSeriesId() {
        return clinicalTrialSeriesId;
    }

    public void setClinicalTrialSeriesId(String clinicalTrialSeriesId) {
        this.clinicalTrialSeriesId = clinicalTrialSeriesId;
    }

    public String getClinicalTrialSeriesDescription() {
        return clinicalTrialSeriesDescription;
    }

    public void setClinicalTrialSeriesDescription(String clinicalTrialSeriesDescription) {
        this.clinicalTrialSeriesDescription = clinicalTrialSeriesDescription;
    }

    public String getFrameOfReferenceUid() {
        return frameOfReferenceUid;
    }

    public void setFrameOfReferenceUid(String frameOfReferenceUid) {
        this.frameOfReferenceUid = frameOfReferenceUid;
    }

    public String getEquipmentManufacturer() {
        return equipmentManufacturer;
    }

    public void setEquipmentManufacturer(String equipmentManufacturer) {
        this.equipmentManufacturer = equipmentManufacturer;
    }

    public String getEquipmentLocationInstitutionName() {
        return equipmentLocationInstitutionName;
    }

    public void setEquipmentLocationInstitutionName(String equipmentLocationInstitutionName) {
        this.equipmentLocationInstitutionName = equipmentLocationInstitutionName;
    }

    public String getEquipmentStationName() {
        return equipmentStationName;
    }

    public void setEquipmentStationName(String equipmentStationName) {
        this.equipmentStationName = equipmentStationName;
    }

    public String getEquipmentInstitutionalDepartmentName() {
        return equipmentInstitutionalDepartmentName;
    }

    public void setEquipmentInstitutionalDepartmentName(String equipmentInstitutionalDepartmentName) {
        this.equipmentInstitutionalDepartmentName = equipmentInstitutionalDepartmentName;
    }

    public String getEquipmentManufacturersModelName() {
        return equipmentManufacturersModelName;
    }

    public void setEquipmentManufacturersModelName(String equipmentManufacturersModelName) {
        this.equipmentManufacturersModelName = equipmentManufacturersModelName;
    }

    public String getEquipmentDeviceSerialNumber() {
        return equipmentDeviceSerialNumber;
    }

    public void setEquipmentDeviceSerialNumber(String equipmentDeviceSerialNumber) {
        this.equipmentDeviceSerialNumber = equipmentDeviceSerialNumber;
    }

    public Date getEquipmentDateOfLastCalibration() {
        return equipmentDateOfLastCalibration;
    }

    public void setEquipmentDateOfLastCalibration(Date equipmentDateOfLastCalibration) {
        this.equipmentDateOfLastCalibration = equipmentDateOfLastCalibration;
    }

    public Date getEquipmentTimeOfLastCalibration() {
        return equipmentTimeOfLastCalibration;
    }

    public void setEquipmentTimeOfLastCalibration(Date equipmentTimeOfLastCalibration) {
        this.equipmentTimeOfLastCalibration = equipmentTimeOfLastCalibration;
    }

    public Date getRadiologistBodyRegionAdded() {
        return radiologistBodyRegionAdded;
    }

    public void setRadiologistBodyRegionAdded(Date radiologistBodyRegionAdded) {
        this.radiologistBodyRegionAdded = radiologistBodyRegionAdded;
    }

    public Date getSupervisorBodyRegionAdded() {
        return supervisorBodyRegionAdded;
    }

    public void setSupervisorBodyRegionAdded(Date supervisorBodyRegionAdded) {
        this.supervisorBodyRegionAdded = supervisorBodyRegionAdded;
    }

    public Collection<DicomImage> getDicomImageCollection() {
        return dicomImageCollection;
    }

    public void setDicomImageCollection(Collection<DicomImage> dicomImageCollection) {
        this.dicomImageCollection = dicomImageCollection;
    }

    public BodyRegion getBodyRegion() {
        return bodyRegion;
    }

    public void setBodyRegion(BodyRegion bodyRegion) {
        this.bodyRegion = bodyRegion;
    }

    public BodyRegion getBodyRegion1() {
        return bodyRegion1;
    }

    public void setBodyRegion1(BodyRegion bodyRegion1) {
        this.bodyRegion1 = bodyRegion1;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public BodyRegion getBodyRegion2() {
        return bodyRegion2;
    }

    public void setBodyRegion2(BodyRegion bodyRegion2) {
        this.bodyRegion2 = bodyRegion2;
    }

    public TDSStudy getTDSStudy() {
        return tDSStudy;
    }

    public void setTDSStudy(TDSStudy tDSStudy) {
        this.tDSStudy = tDSStudy;
    }

    public Integer getNumberInList() {
        return numberInList;
    }

    public void setNumberInList(Integer numberInList) {
        this.numberInList = numberInList;
    }

    public Double getSeriesMaxRawValue() {
        return seriesMaxRawValue;
    }

    public void setSeriesMaxRawValue(Double seriesMaxRawValue) {
        this.seriesMaxRawValue = seriesMaxRawValue;
    }

    public Double getSeriesMinRawValue() {
        return seriesMinRawValue;
    }

    public void setSeriesMinRawValue(Double seriesMinRawValue) {
        this.seriesMinRawValue = seriesMinRawValue;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Series)) {
            return false;
        }
        Series other = (Series) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Series[id=" + id + "]";
    }
}
