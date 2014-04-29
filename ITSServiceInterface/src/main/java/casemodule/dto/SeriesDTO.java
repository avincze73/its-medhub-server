/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.ModalityDTO;

/**
 *
 * @author vincze.attila
 */
public class SeriesDTO extends BaseDTO {

    private double initialWorkValue;
    private double initialWorkTimeHospital;
    private double initialWorkTimeRadiologist;
    private Date initialCalculated;
    private double finalWorkValue;
    private double finalWorkTimeHospital;
    private double finalWorkTimeRadiologist;
    private Date finalCalculated;
    private String instanceUid;
    private int seriesNumberValue;
    private boolean seriesNumberState;
    private String laterality;
    private Date seriesDate;
    private Date seriesTime;
    private String performingPhysiciansDicomName;
    private String protocolName;
    private String description;
    private String operatorsDicomName;
    private String bodyPartExamined;
    private String clinicalTrialSeriesId;
    private String clinicalTrialSeriesDescription;
    private String frameOfReferenceUid;
    private String equipmentManufacturer;
    private String equipmentLocationInstitutionName;
    private String equipmentStationName;
    private String equipmentInstitutionalDepartmentName;
    private String equipmentManufacturersModelName;
    private String equipmentDeviceSerialNumber;
    private Date equipmentDateOfLastCalibration;
    private Date equipmentTimeOfLastCalibration;
    private BodyRegionDTO hospitalBodyRegion;
    private BodyRegionDTO radiologistBodyRegion;//törlés
    private BodyRegionDTO supervisorBodyRegion;//törlés
    private Date radiologistBodyRegionAdded;
    private Date supervisorBodyRegionAdded;
    private ModalityDTO modality;
    private StudyDTO study;
    private double seriesMinRawValue;
    private double seriesMaxRawValue;
    private int numImages;
    private double sliceLocation;//újak
    private double sliceThickness;//újak
    private int numberInList;
    private List<DicomImageDTO> dicomImageList;
    private ProcessedDicomSeries processedDicomSeries;

    public SeriesDTO() {
        super();
        dicomImageList = new ArrayList<DicomImageDTO>();
    }

    public SeriesDTO(long id) {
        super(id);
        dicomImageList = new ArrayList<DicomImageDTO>();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SeriesDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getFinalCalculated() {
        return finalCalculated;
    }

    public void setFinalCalculated(Date finalCalculated) {
        Date oldValue = this.finalCalculated;
        this.finalCalculated = finalCalculated;
        propertyChangeSupport.firePropertyChange("finalCalculated", oldValue, this.finalCalculated);
    }

    public double getFinalWorkTimeHospital() {
        return finalWorkTimeHospital;
    }

    public void setFinalWorkTimeHospital(double finalWorkTimeHospital) {
        double oldValue = this.finalWorkTimeHospital;
        this.finalWorkTimeHospital = finalWorkTimeHospital;
        propertyChangeSupport.firePropertyChange("finalWorkTimeHospital", oldValue, this.finalWorkTimeHospital);
    }

    public double getFinalWorkTimeRadiologist() {
        return finalWorkTimeRadiologist;
    }

    public void setFinalWorkTimeRadiologist(double finalWorkTimeRadiologist) {
        double oldValue = this.finalWorkTimeRadiologist;
        this.finalWorkTimeRadiologist = finalWorkTimeRadiologist;
        propertyChangeSupport.firePropertyChange("finalWorkTimeRadiologist", oldValue, this.finalWorkTimeRadiologist);
    }

    public double getFinalWorkValue() {
        return finalWorkValue;
    }

    public void setFinalWorkValue(double finalWorkValue) {
        double oldValue = this.finalWorkValue;
        this.finalWorkValue = finalWorkValue;
        propertyChangeSupport.firePropertyChange("finalWorkValue", oldValue, this.finalWorkValue);
    }

    public Date getInitialCalculated() {
        return initialCalculated;
    }

    public void setInitialCalculated(Date initialCalculated) {
        Date oldValue = this.initialCalculated;
        this.initialCalculated = initialCalculated;
        propertyChangeSupport.firePropertyChange("initialCalculated", oldValue, this.initialCalculated);
    }

    public double getInitialWorkTimeHospital() {
        return initialWorkTimeHospital;
    }

    public void setInitialWorkTimeHospital(double initialWorkTimeHospital) {
        double oldValue = this.initialWorkTimeHospital;
        this.initialWorkTimeHospital = initialWorkTimeHospital;
        propertyChangeSupport.firePropertyChange("initialWorkTimeHospital", oldValue, this.initialWorkTimeHospital);
    }

    public double getInitialWorkTimeRadiologist() {
        return initialWorkTimeRadiologist;
    }

    public void setInitialWorkTimeRadiologist(double initialWorkTimeRadiologist) {
        double oldValue = this.initialWorkTimeRadiologist;
        this.initialWorkTimeRadiologist = initialWorkTimeRadiologist;
        propertyChangeSupport.firePropertyChange("initialWorkTimeRadiologist", oldValue, this.initialWorkTimeRadiologist);
    }

    public double getInitialWorkValue() {
        return initialWorkValue;
    }

    public void setInitialWorkValue(double initialWorkValue) {
        double oldValue = this.initialWorkValue;
        this.initialWorkValue = initialWorkValue;
        propertyChangeSupport.firePropertyChange("initialWorkValue", oldValue, this.initialWorkValue);
    }

    public StudyDTO getStudy() {
        return study;
    }

    public void setStudy(StudyDTO study) {
        StudyDTO oldValue = this.study;
        this.study = study;
        propertyChangeSupport.firePropertyChange("study", oldValue, this.study);
    }

    public List<DicomImageDTO> getDicomImageList() {
        return dicomImageList;
    }

    public void setDicomImageList(List<DicomImageDTO> dicomImageList) {
        List<DicomImageDTO> oldValue = this.dicomImageList;
        this.dicomImageList = dicomImageList;
        propertyChangeSupport.firePropertyChange("dicomImageList", oldValue, this.dicomImageList);
    }

    public BodyRegionDTO getHospitalBodyRegion() {
        return hospitalBodyRegion;
    }

    public void setHospitalBodyRegion(BodyRegionDTO hospitalBodyRegion) {
        BodyRegionDTO oldValue = this.hospitalBodyRegion;
        this.hospitalBodyRegion = hospitalBodyRegion;
        propertyChangeSupport.firePropertyChange("hospitalBodyRegion", oldValue, this.hospitalBodyRegion);
    }

    public BodyRegionDTO getRadiologistBodyRegion() {
        return radiologistBodyRegion;
    }

    public void setRadiologistBodyRegion(BodyRegionDTO radiologistBodyRegion) {
        BodyRegionDTO oldValue = this.radiologistBodyRegion;
        this.radiologistBodyRegion = radiologistBodyRegion;
        propertyChangeSupport.firePropertyChange("radiologistBodyRegion", oldValue, this.radiologistBodyRegion);
    }

    public BodyRegionDTO getSupervisorBodyRegion() {
        return supervisorBodyRegion;
    }

    public void setSupervisorBodyRegion(BodyRegionDTO supervisorBodyRegion) {
        BodyRegionDTO oldValue = this.supervisorBodyRegion;
        this.supervisorBodyRegion = supervisorBodyRegion;
        propertyChangeSupport.firePropertyChange("supervisorBodyRegion", oldValue, this.supervisorBodyRegion);
    }

    public Date getRadiologistBodyRegionAdded() {
        return radiologistBodyRegionAdded;
    }

    public void setRadiologistBodyRegionAdded(Date radiologistBodyRegionAdded) {
        Date oldValue = this.radiologistBodyRegionAdded;
        this.radiologistBodyRegionAdded = radiologistBodyRegionAdded;
        propertyChangeSupport.firePropertyChange("radiologistBodyRegionAdded", oldValue, this.radiologistBodyRegionAdded);
    }

    public Date getSupervisorBodyRegionAdded() {
        return supervisorBodyRegionAdded;
    }

    public void setSupervisorBodyRegionAdded(Date supervisorBodyRegionAdded) {
        Date oldValue = this.supervisorBodyRegionAdded;
        this.supervisorBodyRegionAdded = supervisorBodyRegionAdded;
        propertyChangeSupport.firePropertyChange("supervisorBodyRegionAdded", oldValue, this.supervisorBodyRegionAdded);
    }

    public ModalityDTO getModality() {
        return modality;
    }

    public void setModality(ModalityDTO modality) {
        ModalityDTO oldValue = this.modality;
        this.modality = modality;
        propertyChangeSupport.firePropertyChange("modality", oldValue, this.modality);
    }

    public String getBodyPartExamined() {
        return bodyPartExamined;
    }

    public void setBodyPartExamined(String bodyPartExamined) {
        String oldValue = this.bodyPartExamined;
        this.bodyPartExamined = bodyPartExamined;
        propertyChangeSupport.firePropertyChange("bodyPartExamined", oldValue, this.bodyPartExamined);
    }

    public String getClinicalTrialSeriesDescription() {
        return clinicalTrialSeriesDescription;
    }

    public void setClinicalTrialSeriesDescription(String clinicalTrialSeriesDescription) {
        String oldValue = this.clinicalTrialSeriesDescription;
        this.clinicalTrialSeriesDescription = clinicalTrialSeriesDescription;
        propertyChangeSupport.firePropertyChange("clinicalTrialSeriesDescription", oldValue, this.clinicalTrialSeriesDescription);
    }

    public String getClinicalTrialSeriesId() {
        return clinicalTrialSeriesId;
    }

    public void setClinicalTrialSeriesId(String clinicalTrialSeriesId) {
        String oldValue = this.clinicalTrialSeriesId;
        this.clinicalTrialSeriesId = clinicalTrialSeriesId;
        propertyChangeSupport.firePropertyChange("clinicalTrialSeriesId", oldValue, this.clinicalTrialSeriesId);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }

    public Date getEquipmentDateOfLastCalibration() {
        return equipmentDateOfLastCalibration;
    }

    public void setEquipmentDateOfLastCalibration(Date equipmentDateOfLastCalibration) {
        Date oldValue = this.equipmentDateOfLastCalibration;
        this.equipmentDateOfLastCalibration = equipmentDateOfLastCalibration;
        propertyChangeSupport.firePropertyChange("equipmentDateOfLastCalibration", oldValue, this.equipmentDateOfLastCalibration);
    }

    public String getEquipmentDeviceSerialNumber() {
        return equipmentDeviceSerialNumber;
    }

    public void setEquipmentDeviceSerialNumber(String equipmentDeviceSerialNumber) {
        String oldValue = this.equipmentDeviceSerialNumber;
        this.equipmentDeviceSerialNumber = equipmentDeviceSerialNumber;
        propertyChangeSupport.firePropertyChange("equipmentDeviceSerialNumber", oldValue, this.equipmentDeviceSerialNumber);
    }

    public String getEquipmentInstitutionalDepartmentName() {
        return equipmentInstitutionalDepartmentName;
    }

    public void setEquipmentInstitutionalDepartmentName(String equipmentInstitutionalDepartmentName) {
        String oldValue = this.equipmentInstitutionalDepartmentName;
        this.equipmentInstitutionalDepartmentName = equipmentInstitutionalDepartmentName;
        propertyChangeSupport.firePropertyChange("equipmentInstitutionalDepartmentName", oldValue, this.equipmentInstitutionalDepartmentName);
    }

    public String getEquipmentLocationInstitutionName() {
        return equipmentLocationInstitutionName;
    }

    public void setEquipmentLocationInstitutionName(String equipmentLocationInstitutionName) {
        String oldValue = this.equipmentLocationInstitutionName;
        this.equipmentLocationInstitutionName = equipmentLocationInstitutionName;
        propertyChangeSupport.firePropertyChange("equipmentLocationInstitutionName", oldValue, this.equipmentLocationInstitutionName);
    }

    public String getEquipmentManufacturer() {
        return equipmentManufacturer;
    }

    public void setEquipmentManufacturer(String equipmentManufacturer) {
        String oldValue = this.equipmentManufacturer;
        this.equipmentManufacturer = equipmentManufacturer;
        propertyChangeSupport.firePropertyChange("equipmentManufacturer", oldValue, this.equipmentManufacturer);
    }

    public String getEquipmentManufacturersModelName() {
        return equipmentManufacturersModelName;
    }

    public void setEquipmentManufacturersModelName(String equipmentManufacturersModelName) {
        String oldValue = this.equipmentManufacturersModelName;
        this.equipmentManufacturersModelName = equipmentManufacturersModelName;
        propertyChangeSupport.firePropertyChange("equipmentManufacturersModelName", oldValue, this.equipmentManufacturersModelName);
    }

    public String getEquipmentStationName() {
        return equipmentStationName;
    }

    public void setEquipmentStationName(String equipmentStationName) {
        String oldValue = this.equipmentStationName;
        this.equipmentStationName = equipmentStationName;
        propertyChangeSupport.firePropertyChange("equipmentStationName", oldValue, this.equipmentStationName);
    }

    public Date getEquipmentTimeOfLastCalibration() {
        return equipmentTimeOfLastCalibration;
    }

    public void setEquipmentTimeOfLastCalibration(Date equipmentTimeOfLastCalibration) {
        Date oldValue = this.equipmentTimeOfLastCalibration;
        this.equipmentTimeOfLastCalibration = equipmentTimeOfLastCalibration;
        propertyChangeSupport.firePropertyChange("equipmentTimeOfLastCalibration", oldValue, this.equipmentTimeOfLastCalibration);
    }

    public String getFrameOfReferenceUid() {
        return frameOfReferenceUid;
    }

    public void setFrameOfReferenceUid(String frameOfReferenceUid) {
        String oldValue = this.frameOfReferenceUid;
        this.frameOfReferenceUid = frameOfReferenceUid;
        propertyChangeSupport.firePropertyChange("frameOfReferenceUid", oldValue, this.frameOfReferenceUid);
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        String oldValue = this.instanceUid;
        this.instanceUid = instanceUid;
        propertyChangeSupport.firePropertyChange("instanceUid", oldValue, this.instanceUid);
    }

    public String getLaterality() {
        return laterality;
    }

    public void setLaterality(String laterality) {
        String oldValue = this.laterality;
        this.laterality = laterality;
        propertyChangeSupport.firePropertyChange("laterality", oldValue, this.laterality);
    }

    public String getOperatorsDicomName() {
        return operatorsDicomName;
    }

    public void setOperatorsDicomName(String operatorsDicomName) {
        String oldValue = this.operatorsDicomName;
        this.operatorsDicomName = operatorsDicomName;
        propertyChangeSupport.firePropertyChange("operatorsDicomName", oldValue, this.operatorsDicomName);
    }

    public String getPerformingPhysiciansDicomName() {
        return performingPhysiciansDicomName;
    }

    public void setPerformingPhysiciansDicomName(String performingPhysiciansDicomName) {
        String oldValue = this.performingPhysiciansDicomName;
        this.performingPhysiciansDicomName = performingPhysiciansDicomName;
        propertyChangeSupport.firePropertyChange("performingPhysiciansDicomName", oldValue, this.performingPhysiciansDicomName);
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        String oldValue = this.protocolName;
        this.protocolName = protocolName;
        propertyChangeSupport.firePropertyChange("protocolName", oldValue, this.protocolName);
    }

    public Date getSeriesDate() {
        return seriesDate;
    }

    public void setSeriesDate(Date seriesDate) {
        Date oldValue = this.seriesDate;
        this.seriesDate = seriesDate;
        propertyChangeSupport.firePropertyChange("seriesDate", oldValue, this.seriesDate);
    }

    public boolean isSeriesNumberState() {
        return seriesNumberState;
    }

    public void setSeriesNumberState(boolean seriesNumberState) {
        boolean oldValue = this.seriesNumberState;
        this.seriesNumberState = seriesNumberState;
        propertyChangeSupport.firePropertyChange("seriesNumberState", oldValue, this.seriesNumberState);
    }

    public int getSeriesNumberValue() {
        return seriesNumberValue;
    }

    public void setSeriesNumberValue(int seriesNumberValue) {
        int oldValue = this.seriesNumberValue;
        this.seriesNumberValue = seriesNumberValue;
        propertyChangeSupport.firePropertyChange("seriesNumberValue", oldValue, this.seriesNumberValue);
    }

    public Date getSeriesTime() {
        return seriesTime;
    }

    public void setSeriesTime(Date seriesTime) {
        Date oldValue = this.seriesTime;
        this.seriesTime = seriesTime;
        propertyChangeSupport.firePropertyChange("seriesTime", oldValue, this.seriesTime);
    }

    public ProcessedDicomSeries getProcessedDicomSeries() {
        return processedDicomSeries;
    }

    public void setProcessedDicomSeries(ProcessedDicomSeries processedDicomSeries) {
        this.processedDicomSeries = processedDicomSeries;
    }

    public double getSeriesMaxRawValue() {
        return seriesMaxRawValue;
    }

    public void setSeriesMaxRawValue(double seriesMaxRawValue) {
        this.seriesMaxRawValue = seriesMaxRawValue;
    }

    public double getSeriesMinRawValue() {
        return seriesMinRawValue;
    }

    public void setSeriesMinRawValue(double seriesMinRawValue) {
        this.seriesMinRawValue = seriesMinRawValue;
    }

    public int getNumImages() {
        return dicomImageList.size();
    }

    public int getNumberInList() {
        return numberInList;
    }

    public void setNumberInList(int numberInList) {
        int oldValue = this.numberInList;
        this.numberInList = numberInList;
        propertyChangeSupport.firePropertyChange("numberInList", oldValue, this.numberInList);
    }

    public String getSeriesNumber() {
        return "Series " + numberInList;
    }
}
