/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.helper;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class StudyWithBodyRegionPM extends BaseDTO {

    private long studyId;
    private String studyUid;
    private String accessionNumber;
    private Date studyDate;
    private int studyNumberInList;
    private long seriesId;
    private String seriesUid;
    private int seriesNumberInList;
    private long modalityId;
    private String modalityName;
    private String info;
    private long hospitalBodyRegionId;
    private String hospitalBodyRegionName;
    private long radiologistBodyRegionId;
    private String radiologistBodyRegionName;

    public StudyWithBodyRegionPM(long id) {
        super(id);
        info = "Correction:";
    }

    public StudyWithBodyRegionPM() {
        this(0);
    }



    public long getHospitalBodyRegionId() {
        return hospitalBodyRegionId;
    }

    public void setHospitalBodyRegionId(long hospitalBodyRegionId) {
        long oldValue = this.hospitalBodyRegionId;
        this.hospitalBodyRegionId = hospitalBodyRegionId;
        propertyChangeSupport.firePropertyChange("hospitalBodyRegionId", oldValue, this.hospitalBodyRegionId);
    }

    public String getHospitalBodyRegionName() {
        return hospitalBodyRegionName;
    }

    public void setHospitalBodyRegionName(String hospitalBodyRegionName) {
        String oldValue = this.hospitalBodyRegionName;
        this.hospitalBodyRegionName = hospitalBodyRegionName;
        propertyChangeSupport.firePropertyChange("hospitalBodyRegionName", oldValue, this.hospitalBodyRegionName);
    }

    public long getModalityId() {
        return modalityId;
    }

    public void setModalityId(long modalityId) {
        long oldValue = this.modalityId;
        this.modalityId = modalityId;
        propertyChangeSupport.firePropertyChange("modalityId", oldValue, this.modalityId);
    }

    public String getModalityName() {
        return modalityName;
    }

    public void setModalityName(String modalityName) {
        String oldValue = this.modalityName;
        this.modalityName = modalityName;
        propertyChangeSupport.firePropertyChange("modalityName", oldValue, this.modalityName);
    }

    public long getRadiologistBodyRegionId() {
        return radiologistBodyRegionId;
    }

    public void setRadiologistBodyRegionId(long radiologistBodyRegionId) {
        long oldValue = this.radiologistBodyRegionId;
        this.radiologistBodyRegionId = radiologistBodyRegionId;
        propertyChangeSupport.firePropertyChange("radiologistBodyRegionId", oldValue, this.radiologistBodyRegionId);
    }

    public String getRadiologistBodyRegionName() {
        return radiologistBodyRegionName;
    }

    public void setRadiologistBodyRegionName(String radiologistBodyRegionName) {
        String oldValue = this.radiologistBodyRegionName;
        this.radiologistBodyRegionName = radiologistBodyRegionName;
        propertyChangeSupport.firePropertyChange("radiologistBodyRegionName", oldValue, this.radiologistBodyRegionName);
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        long oldValue = this.seriesId;
        this.seriesId = seriesId;
        propertyChangeSupport.firePropertyChange("seriesId", oldValue, this.seriesId);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        String oldValue = this.info;
        this.info = info;
        propertyChangeSupport.firePropertyChange("info", oldValue, this.info);
    }

    public String getSeriesUid() {
        return seriesUid;
    }

    public void setSeriesUid(String seriesUid) {
        String oldValue = this.seriesUid;
        this.seriesUid = seriesUid;
        propertyChangeSupport.firePropertyChange("seriesUid", oldValue, this.seriesUid);
    }

    public long getStudyId() {
        return studyId;
    }

    public void setStudyId(long studyId) {
        long oldValue = this.studyId;
        this.studyId = studyId;
        propertyChangeSupport.firePropertyChange("studyId", oldValue, this.studyId);
    }

    public String getStudyUid() {
        return studyUid;
    }

    public void setStudyUid(String studyUid) {
        String oldValue = this.studyUid;
        this.studyUid = studyUid;
        propertyChangeSupport.firePropertyChange("studyUid", oldValue, this.studyUid);
    }

    public String getSeriesNumber() {
        return "Series " + seriesNumberInList;
    }

    public String getStudyNumber() {
        return "Study " + studyNumberInList;
    }

    public int getSeriesNumberInList() {
        return seriesNumberInList;
    }

    public void setSeriesNumberInList(int seriesNumberInList) {
        int oldValue = this.seriesNumberInList;
        this.seriesNumberInList = seriesNumberInList;
        propertyChangeSupport.firePropertyChange("seriesNumberInList", oldValue, this.seriesNumberInList);
    }

    public int getStudyNumberInList() {
        return studyNumberInList;
    }

    public void setStudyNumberInList(int studyNumberInList) {
        int oldValue = this.studyNumberInList;
        this.studyNumberInList = studyNumberInList;
        propertyChangeSupport.firePropertyChange("studyNumberInList", oldValue, this.studyNumberInList);
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        String oldValue = this.accessionNumber;
        this.accessionNumber = accessionNumber;
        propertyChangeSupport.firePropertyChange("accessionNumber", oldValue, this.accessionNumber);
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        Date oldValue = this.studyDate;
        this.studyDate = studyDate;
        propertyChangeSupport.firePropertyChange("studyDate", oldValue, this.studyDate);
    }

    
}
