/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class PatientAndReferralInfoDTO extends BaseDTO {

    public static final String toDO = "toDO";
    public static final String ok = "ok";
    public static final String mismatch = "mismatch";
    public static final String uncertain = "uncertain";
    private CaseDTO caseItBelongsTo;
    private String nonDicomCaseIdInHospital;//ez nem kell majd
    private String nonDicomCaseIdInHospital1Name;
    private String nonDicomCaseIdInHospital2Name;
    private String nonDicomCaseIdInHospital1Value;
    private String nonDicomCaseIdInHospital2Value;
    private String agreementTest; //dicom-on belüli dolgok
    private Date agreementTestDateTime;//dicom-on belüli dolgok
    private String agreementTestNotes;//dicom-on belüli dolgok
    private DicomPatientDataDTO dicomPatientData;
    private ElectronicPatientDataDTO electronicPatientData;
    private ImagePatientDataDTO imagePatientData;//ez valószínűleg nem kell majd
    private ImageReferralDTO imageReferral;
    private ElectronicReferralDTO electronicReferral;
    private String dicomVsElectronicPatinetData;
    private String dicomVsElectronicReferral;
    private String dicomVsImagePatientData;
    private String dicomVsImageReferral;

    public PatientAndReferralInfoDTO(int id) {
        super(id);
    }

    public PatientAndReferralInfoDTO() {
        super();
    }

    public PatientAndReferralInfoDTO(String nonDicomCaseIdInHospital) {
        super();
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
    }

    public PatientAndReferralInfoDTO(int id, CaseDTO caseItBelongsTo, String nonDicomCaseIdInHospital) {
        super(id);
        this.caseItBelongsTo = caseItBelongsTo;
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
    }

    public PatientAndReferralInfoDTO(CaseDTO caseItBelongsTo, String nonDicomCaseIdInHospital) {
        super();
        this.caseItBelongsTo = caseItBelongsTo;
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PatientAndReferralInfoDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public String getNonDicomCaseIdInHospital() {
        return nonDicomCaseIdInHospital;
    }

    public void setNonDicomCaseIdInHospital(String nonDicomCaseIdInHospital) {
        String oldValue = this.nonDicomCaseIdInHospital;
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospital", oldValue, this.nonDicomCaseIdInHospital);
    }

    public String getAgreementTest() {
        return agreementTest;
    }

    public void setAgreementTest(String agreementTest) {
        String oldValue = this.agreementTest;
        this.agreementTest = agreementTest;
        propertyChangeSupport.firePropertyChange("agreementTest", oldValue, this.agreementTest);
    }

    public Date getAgreementTestDateTime() {
        return agreementTestDateTime;
    }

    public void setAgreementTestDateTime(Date agreementTestDateTime) {
        Date oldValue = this.agreementTestDateTime;
        this.agreementTestDateTime = agreementTestDateTime;
        propertyChangeSupport.firePropertyChange("agreementTestDateTime", oldValue, this.agreementTestDateTime);
    }

    public DicomPatientDataDTO getDicomPatientData() {
        return dicomPatientData;
    }

    public void setDicomPatientData(DicomPatientDataDTO dicomPatientData) {
        DicomPatientDataDTO oldValue = this.dicomPatientData;
        this.dicomPatientData = dicomPatientData;
        propertyChangeSupport.firePropertyChange("dicomPatientData", oldValue, this.dicomPatientData);
    }

    public ElectronicPatientDataDTO getElectronicPatientData() {
        return electronicPatientData;
    }

    public void setElectronicPatientData(ElectronicPatientDataDTO electronicPatientData) {
        ElectronicPatientDataDTO oldValue = this.electronicPatientData;
        this.electronicPatientData = electronicPatientData;
        propertyChangeSupport.firePropertyChange("electronicPatientData", oldValue, this.electronicPatientData);
    }

    public ElectronicReferralDTO getElectronicReferral() {
        return electronicReferral;
    }

    public void setElectronicReferral(ElectronicReferralDTO electronicReferral) {
        ElectronicReferralDTO oldValue = this.electronicReferral;
        this.electronicReferral = electronicReferral;
        propertyChangeSupport.firePropertyChange("electronicReferral", oldValue, this.electronicReferral);
    }

    public ImageReferralDTO getImageReferral() {
        return imageReferral;
    }

    public void setImageReferral(ImageReferralDTO imageReferral) {
        ImageReferralDTO oldValue = this.imageReferral;
        this.imageReferral = imageReferral;
        propertyChangeSupport.firePropertyChange("imageReferral", oldValue, this.imageReferral);
    }

    public ImagePatientDataDTO getImagePatientData() {
        return imagePatientData;
    }

    public void setImagePatientData(ImagePatientDataDTO imagePatientData) {
        ImagePatientDataDTO oldValue = this.imagePatientData;
        this.imagePatientData = imagePatientData;
        propertyChangeSupport.firePropertyChange("imagePatientData", oldValue, this.imagePatientData);
    }

    public String getAgreementTestNotes() {
        return agreementTestNotes;
    }

    public void setAgreementTestNotes(String agreementTestNotes) {
        String oldValue = this.agreementTestNotes;
        this.agreementTestNotes = agreementTestNotes;
        propertyChangeSupport.firePropertyChange("agreementTestNotes", oldValue, this.agreementTestNotes);
    }
}
