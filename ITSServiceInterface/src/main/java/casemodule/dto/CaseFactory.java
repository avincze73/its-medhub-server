/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import hospitalmodule.dto.HospitalContractDTO;
import java.util.Date;
import java.util.List;
import masterdatamodule.dto.ModeOfAcquisitionDTO;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseFactory {

    public static CaseDTO create(
            int modeOfAcquisitionId, int hospitalContractId,
            int senderHospitalStaffId, int receiverHospitalStaffId,
            PatientAndReferralInfoDTO caseInfo,
            ElectronicPatientDataDTO electronicPatientData,
            DicomPatientDataDTO dicomPatientData,
            ElectronicReferralDTO electronicReferral,
            List<ScannedReferralImageDTO> scannedImageList,
            List<StudyDTO> studyList) {
        CaseDTO dto = new CaseDTO();
        dto.setModeOfAcquisition(new ModeOfAcquisitionDTO(modeOfAcquisitionId));
        dto.setHospitalContract(new HospitalContractDTO(hospitalContractId));
        dto.setSenderHospitalStaff(new HospitalStaffDTO(senderHospitalStaffId));
        dto.setReceiverHospitalStaff(new HospitalStaffDTO(receiverHospitalStaffId));
        
//        caseInfo.setCaseItBelongsTo(dto);
//        dto.setCaseInfo(caseInfo);
//
//        PatientInfoDTO patientInfo = new PatientInfoDTO();
//        patientInfo.setCaseInfo(caseInfo);
//        patientInfo.setAgreementTest(PatientInfoDTO.ok);
//        patientInfo.setAgreementTestDateTime(new Date().toString());
//        patientInfo.setDicomPatientData(dicomPatientData);
//        patientInfo.setElectronicPatientData(electronicPatientData);
//        dto.getCaseInfo().setPatientInfo(patientInfo);
//
//        ReferralInfoDTO referralInfo = new ReferralInfoDTO();
//        referralInfo.setCaseInfo(caseInfo);
//        referralInfo.setElectronicReferral(electronicReferral);
//        dto.getCaseInfo().setReferralInfo(referralInfo);
//
//        ImageReferralDTO imageReferral = new ImageReferralDTO();
//        imageReferral.setImageList(scannedImageList);
//        for (ScannedImageDTO scannedImage : imageReferral.getImageList()) {
//            scannedImage.setImageReferral(imageReferral);
//        }
//        referralInfo.setImageReferral(imageReferral);

//        dto.setStudyList(studyList);
//        for (StudyDTO studyDTO : dto.getStudyList()) {
//            studyDTO.setCaseItBelongsTo(dto);
//        }
        return dto;
    }

    public static CaseDTO create(
            int modeOfAcquisitionId, int hospitalContractId,
            int senderHospitalStaffId, int receiverHospitalStaffId,
            PatientAndReferralInfoDTO caseInfo,
            ElectronicPatientDataDTO electronicPatientData,
            ElectronicReferralDTO electronicReferral,
            List<ScannedReferralImageDTO> scannedImageList) {

        CaseDTO dto = new CaseDTO();
        dto.setModeOfAcquisition(new ModeOfAcquisitionDTO(modeOfAcquisitionId));
        dto.setHospitalContract(new HospitalContractDTO(hospitalContractId));
        dto.setSenderHospitalStaff(new HospitalStaffDTO(senderHospitalStaffId));
        dto.setReceiverHospitalStaff(new HospitalStaffDTO(receiverHospitalStaffId));

//        caseInfo.setCaseItBelongsTo(dto);
//        dto.setCaseInfo(caseInfo);
//
//        PatientInfoDTO patientInfo = new PatientInfoDTO();
//        patientInfo.setCaseInfo(caseInfo);
//        patientInfo.setAgreementTest(PatientInfoDTO.ok);
//        patientInfo.setAgreementTestDateTime(new Date().toString());
//        patientInfo.setElectronicPatientData(electronicPatientData);
//        dto.getCaseInfo().setPatientInfo(patientInfo);
//
//        ReferralInfoDTO referralInfo = new ReferralInfoDTO();
//        referralInfo.setCaseInfo(caseInfo);
//        referralInfo.setElectronicReferral(electronicReferral);
//        dto.getCaseInfo().setReferralInfo(referralInfo);
//
//        ImageReferralDTO imageReferral = new ImageReferralDTO();
//        imageReferral.setImageList(scannedImageList);
//        for (ScannedImageDTO scannedImage : imageReferral.getImageList()) {
//            scannedImage.setImageReferral(imageReferral);
//        }
//        referralInfo.setImageReferral(imageReferral);

        
        return dto;
    }
}
