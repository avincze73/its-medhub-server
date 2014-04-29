/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class HistoryCaseDTO extends BaseDTO {

    private byte[] finalizedReport;
    private ImageReportDTO imageReport;
    private DicomPatientDataDTO dicomPatientData;
    private List<ReferralInfoDTO> referralInfoList;
    private String studyStructure;

    public HistoryCaseDTO(long id) {
        super(id);
        referralInfoList = new ArrayList<ReferralInfoDTO>();
    }

    public HistoryCaseDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HistoryCaseDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public List<ReferralInfoDTO> getReferralInfoList() {
        return referralInfoList;
    }

    public void setReferralInfoList(List<ReferralInfoDTO> referralInfoList) {
        List<ReferralInfoDTO> oldValue = this.referralInfoList;
        this.referralInfoList = referralInfoList;
        propertyChangeSupport.firePropertyChange("referralInfoList", oldValue, this.referralInfoList);
    }

    public byte[] getFinalizedReport() {
        return finalizedReport;
    }

    public void setFinalizedReport(byte[] finalizedReport) {
        byte[] oldValue = this.finalizedReport;
        this.finalizedReport = finalizedReport;
        propertyChangeSupport.firePropertyChange("finalizedReport", oldValue, this.finalizedReport);
    }

    public ImageReportDTO getImageReport() {
        return imageReport;
    }

    public void setImageReport(ImageReportDTO imageReport) {
        ImageReportDTO oldValue = this.imageReport;
        this.imageReport = imageReport;
        propertyChangeSupport.firePropertyChange("imageReport", oldValue, this.imageReport);
    }

    public DicomPatientDataDTO getDicomPatientData() {
        return dicomPatientData;
    }

    public void setDicomPatientData(DicomPatientDataDTO dicomPatientData) {
        DicomPatientDataDTO oldValue = this.dicomPatientData;
        this.dicomPatientData = dicomPatientData;
        propertyChangeSupport.firePropertyChange("dicomPatientData", oldValue, this.dicomPatientData);
    }

    public String getStudyStructure() {
        return studyStructure;
    }

    public void setStudyStructure(String studyStructure) {
        this.studyStructure = studyStructure;
    }

    
}
