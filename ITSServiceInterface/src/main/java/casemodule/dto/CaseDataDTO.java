/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import common.exception.ConstraintException;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class CaseDataDTO extends BaseDTO {

    private String[] dicomUniqueIds;
    private String nonDicomCaseIdInHospital;
    private ElectronicPatientDataDTO electronicPatientData;
    private ElectronicReferralDTO electronicReferral;
    private List<String> scannedReferralImageList;
    private List<String> scannedPatientDataImageList;
    private List<String> dicomImageList;
    private String nonDicomCaseIdInHospital1Name;
    private String nonDicomCaseIdInHospital2Name;
    private String nonDicomCaseIdInHospital1ShortName;
    private String nonDicomCaseIdInHospital2ShortName;
    private String nonDicomCaseIdInHospital1Value;
    private String nonDicomCaseIdInHospital2Value;
    //error
    private boolean nonDicomCaseIdInHospitalError;
    //optional
    private boolean nonDicomCaseIdInHospitalOptional;

    public CaseDataDTO(int id) {
        super(id);
        electronicPatientData = new ElectronicPatientDataDTO();
        electronicReferral = new ElectronicReferralDTO();
    }

    public CaseDataDTO() {
        super();
        electronicPatientData = new ElectronicPatientDataDTO();
        electronicReferral = new ElectronicReferralDTO();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CaseDataDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public ElectronicPatientDataDTO getElectronicPatientData() {
        return electronicPatientData;
    }

    public void setElectronicPatientData(ElectronicPatientDataDTO electronicPatientData) {
        this.electronicPatientData = electronicPatientData;
    }

    public ElectronicReferralDTO getElectronicReferral() {
        return electronicReferral;
    }

    public void setElectronicReferral(ElectronicReferralDTO electronicReferral) {
        this.electronicReferral = electronicReferral;
    }

    public String getNonDicomCaseIdInHospital() {
        return nonDicomCaseIdInHospital;
    }

    public void setNonDicomCaseIdInHospital(String nonDicomCaseIdInHospital) {
        this.nonDicomCaseIdInHospital = nonDicomCaseIdInHospital;
    }

    public String[] getDicomUniqueIds() {
        return dicomUniqueIds;
    }

    public void setDicomUniqueIds(String[] dicomUniqueIds) {
        this.dicomUniqueIds = dicomUniqueIds;
    }

    public List<String> getDicomImageList() {
        return dicomImageList;
    }

    public void setDicomImageList(List<String> dicomImageList) {
        this.dicomImageList = dicomImageList;
    }

    public List<String> getScannedPatientDataImageList() {
        return scannedPatientDataImageList;
    }

    public void setScannedPatientDataImageList(List<String> scannedPatientDataImageList) {
        this.scannedPatientDataImageList = scannedPatientDataImageList;
    }

    public List<String> getScannedReferralImageList() {
        return scannedReferralImageList;
    }

    public void setScannedReferralImageList(List<String> scannedReferralImageList) {
        this.scannedReferralImageList = scannedReferralImageList;
    }

    public boolean isNonDicomCaseIdInHospitalError() {
        return nonDicomCaseIdInHospitalError;
    }

    public void setNonDicomCaseIdInHospitalError(boolean nonDicomCaseIdInHospitalError) {
        boolean oldValue = this.nonDicomCaseIdInHospitalError;
        this.nonDicomCaseIdInHospitalError = nonDicomCaseIdInHospitalError;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospitalError", oldValue, this.nonDicomCaseIdInHospitalError);
    }

    public boolean isNonDicomCaseIdInHospitalOptional() {
        return nonDicomCaseIdInHospitalOptional;
    }

    public void setNonDicomCaseIdInHospitalOptional(boolean nonDicomCaseIdInHospitalOptional) {
        boolean oldValue = this.nonDicomCaseIdInHospitalOptional;
        this.nonDicomCaseIdInHospitalOptional = nonDicomCaseIdInHospitalOptional;
        propertyChangeSupport.firePropertyChange("nonDicomCaseIdInHospitalOptional", oldValue, this.nonDicomCaseIdInHospitalOptional);
    }

    public String getNonDicomCaseIdInHospital1Name() {
        return nonDicomCaseIdInHospital1Name;
    }

    public void setNonDicomCaseIdInHospital1Name(String nonDicomCaseIdInHospital1Name) {
        this.nonDicomCaseIdInHospital1Name = nonDicomCaseIdInHospital1Name;
    }

    public String getNonDicomCaseIdInHospital1ShortName() {
        return nonDicomCaseIdInHospital1ShortName;
    }

    public void setNonDicomCaseIdInHospital1ShortName(String nonDicomCaseIdInHospital1ShortName) {
        this.nonDicomCaseIdInHospital1ShortName = nonDicomCaseIdInHospital1ShortName;
    }

    public String getNonDicomCaseIdInHospital1Value() {
        return nonDicomCaseIdInHospital1Value;
    }

    public void setNonDicomCaseIdInHospital1Value(String nonDicomCaseIdInHospital1Value) {
        this.nonDicomCaseIdInHospital1Value = nonDicomCaseIdInHospital1Value;
    }

    public String getNonDicomCaseIdInHospital2Name() {
        return nonDicomCaseIdInHospital2Name;
    }

    public void setNonDicomCaseIdInHospital2Name(String nonDicomCaseIdInHospital2Name) {
        this.nonDicomCaseIdInHospital2Name = nonDicomCaseIdInHospital2Name;
    }

    public String getNonDicomCaseIdInHospital2ShortName() {
        return nonDicomCaseIdInHospital2ShortName;
    }

    public void setNonDicomCaseIdInHospital2ShortName(String nonDicomCaseIdInHospital2ShortName) {
        this.nonDicomCaseIdInHospital2ShortName = nonDicomCaseIdInHospital2ShortName;
    }

    public String getNonDicomCaseIdInHospital2Value() {
        return nonDicomCaseIdInHospital2Value;
    }

    public void setNonDicomCaseIdInHospital2Value(String nonDicomCaseIdInHospital2Value) {
        this.nonDicomCaseIdInHospital2Value = nonDicomCaseIdInHospital2Value;
    }

    @Override
    public void validate() throws ConstraintException {
        //super.validate();
        HashSet<String> errors = new HashSet<String>();
        if (nonDicomCaseIdInHospital1Value == null || "".equals(nonDicomCaseIdInHospital1Value)) {
            setNonDicomCaseIdInHospitalError(true);
            errors.add("Case ID");
        } else {
            setNonDicomCaseIdInHospitalError(false);
        }
        if (dicomImageList.isEmpty()) {
            errors.add("Images");
        }
        if (!errors.isEmpty()) {
            throw new ConstraintException(errors.toString());
        }
    }
}
