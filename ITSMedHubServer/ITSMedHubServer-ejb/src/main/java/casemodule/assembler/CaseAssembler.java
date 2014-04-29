/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.assembler;

import casemodule.dto.CaseDTO;
import casemodule.dto.DataProcLogDTO;
import casemodule.dto.DataProcLogEntryDTO;
import casemodule.dto.DicomImageDTO;
import casemodule.dto.DicomPatientDataDTO;
import casemodule.dto.ElectronicPatientDataDTO;
import casemodule.dto.ElectronicReferralDTO;
import casemodule.dto.FlagAssignmentDTO;
import casemodule.dto.HistoryCaseDTO;
import casemodule.dto.ImagePatientDataDTO;
import casemodule.dto.ImageReferralDTO;
import casemodule.dto.ImageReportDTO;
import casemodule.dto.ReferralInfoDTO;
import casemodule.dto.ScannedPatientDataImageDTO;
import casemodule.dto.ScannedReferralImageDTO;
import casemodule.dto.ScannedReportImageDTO;
import casemodule.dto.ScenarioInstanceDTO;
import casemodule.dto.SeriesDTO;
import casemodule.dto.StudyDTO;
import casemodule.dto.SystemMessageDTO;
import casemodule.dto.SystemMessageTypeDTO;
import casemodule.entity.DataProcLogEntry;
import casemodule.entity.DicomImage;
import casemodule.entity.DicomPatientData;
import casemodule.entity.ElectronicPatientData;
import casemodule.entity.ElectronicReferral;
import casemodule.entity.FlagAssignment;
import casemodule.entity.HistoryCase;
import casemodule.entity.ImagePatientData;
import casemodule.entity.ImageReferral;
import casemodule.entity.ReferralInfo;
import casemodule.entity.ScannedPatientDataImage;
import casemodule.entity.ScannedReferralImage;
import casemodule.entity.ScannedReportImage;
import casemodule.entity.ScenarioInstance;
import casemodule.entity.Series;
import casemodule.entity.TDSCase;
import casemodule.entity.TDSStudy;
import casemodule.helper.SeriesGroup;
import casemodule.helper.StudyWithBodyRegionPM;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import integrationmodule.entity.ElectronicPatientDataPackage;
import integrationmodule.entity.ElectronicReferralPackage;
import integrationmodule.entity.PatientDataFile;
import integrationmodule.entity.ReferralFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.dto.CaseFlagDTO;
import masterdatamodule.entity.CaseFlag;
import masterdatamodule.entity.Scenario;
import radiologistmodule.assembler.RadiologistAssembler;
import radiologistmodule.dto.TDSRadiologistDTO;
import reportingmodule.assembler.ReportingAssembler;
import reportingmodule.entity.Reporting;
import systemmodule.entity.SystemMessage;
import systemmodule.entity.SystemMessageType;
import tdsdicominterface.DicomDataSet;
import usermodule.assembler.UserAssembler;
import usermodule.dto.HospitalStaffDTO;

/**
 *
 * @author vincze.attila
 */
public class CaseAssembler {

    public static ElectronicPatientDataDTO toDTO(ElectronicPatientData entity) {
        ElectronicPatientDataDTO dto = new ElectronicPatientDataDTO(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setContactInfo(entity.getContactInfo());
        dto.setContactTel(entity.getContactTel());
        dto.setDob(entity.getDob());
        dto.setGender(entity.getGender());
        dto.setIdOrSimilar(entity.getIdOrSimilar());
        dto.setMothersName(entity.getMothersName());
        dto.setNationality(entity.getNationality());
        dto.setOther(entity.getOther());
        dto.setPatientIdInHospital(entity.getPatientIdInHospital());
        dto.setPatientName(entity.getPatientName());
        dto.setTbOrNiOrSimilar(entity.getTbOrNiOrSimilar());
        dto.setPatientIdInHospital1Name(entity.getPatientIdInHospital1Name());
        dto.setPatientIdInHospital1ShortName(entity.getPatientIdInHospital1ShortName());
        dto.setPatientIdInHospital1Value(entity.getPatientIdInHospital1Value());
        dto.setPatientIdInHospital2Name(entity.getPatientIdInHospital2Name());
        dto.setPatientIdInHospital2ShortName(entity.getPatientIdInHospital2ShortName());
        dto.setPatientIdInHospital2Value(entity.getPatientIdInHospital2Value());
        return dto;
    }

    public static ElectronicPatientData toEntity(ElectronicPatientDataDTO dto) {
        ElectronicPatientData entity = new ElectronicPatientData();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAddress(dto.getAddress());
        entity.setContactInfo(dto.getContactInfo());
        entity.setContactTel(dto.getContactTel());
        entity.setDob(dto.getDob());
        entity.setGender(dto.getGender());
        entity.setIdOrSimilar(dto.getIdOrSimilar());
        entity.setMothersName(dto.getMothersName());
        entity.setNationality(dto.getNationality());
        entity.setOther(dto.getOther());
        entity.setPatientIdInHospital(dto.getPatientIdInHospital());
        entity.setPatientName(dto.getPatientName());
        entity.setTbOrNiOrSimilar(dto.getTbOrNiOrSimilar());
        entity.setPatientIdInHospital1Name(dto.getPatientIdInHospital1Name());
        entity.setPatientIdInHospital1ShortName(dto.getPatientIdInHospital1ShortName());
        entity.setPatientIdInHospital1Value(dto.getPatientIdInHospital1Value());
        entity.setPatientIdInHospital2Name(dto.getPatientIdInHospital2Name());
        entity.setPatientIdInHospital2ShortName(dto.getPatientIdInHospital2ShortName());
        entity.setPatientIdInHospital2Value(dto.getPatientIdInHospital2Value());
        return entity;
    }
    
    public static ElectronicPatientData toEntity(ElectronicPatientDataPackage dataPackage) {
        ElectronicPatientData entity = new ElectronicPatientData();
        if (dataPackage.getId() != 0) {
            entity.setId(dataPackage.getId());
        }
        entity.setAddress(dataPackage.getAddress());
        entity.setContactInfo(dataPackage.getContactInfo());
        entity.setContactTel(dataPackage.getContactTel());
        entity.setDob(dataPackage.getDob());
        entity.setGender(dataPackage.getGender());
        entity.setIdOrSimilar(dataPackage.getIdOrSimilar());
        entity.setMothersName(dataPackage.getMothersName());
        entity.setNationality(dataPackage.getNationality());
        entity.setOther(dataPackage.getOther());
        entity.setPatientIdInHospital(dataPackage.getPatientIdInHospital());
        entity.setPatientName(dataPackage.getPatientName());
        entity.setTbOrNiOrSimilar(dataPackage.getTbOrNiOrSimilar());
        entity.setPatientIdInHospital1Name(dataPackage.getPatientIdInHospital1Name());
        entity.setPatientIdInHospital1ShortName(dataPackage.getPatientIdInHospital1ShortName());
        entity.setPatientIdInHospital1Value(dataPackage.getPatientIdInHospital1Value());
        entity.setPatientIdInHospital2Name(dataPackage.getPatientIdInHospital2Name());
        entity.setPatientIdInHospital2ShortName(dataPackage.getPatientIdInHospital2ShortName());
        entity.setPatientIdInHospital2Value(dataPackage.getPatientIdInHospital2Value());
        return entity;
    }
    

    public static DicomPatientDataDTO toDTO(DicomPatientData entity) {
        DicomPatientDataDTO dto = new DicomPatientDataDTO(entity.getId());
        dto.setComments(entity.getComments());
        dto.setDob(entity.getDob());
        dto.setIssuerOfPatientId(entity.getIssuerOfPatientId());
        dto.setMothersBirthName(entity.getMothersBirthName());
        dto.setOtherPatientId(entity.getOtherPatientId());
        try {
            dto.setOtherPatientIdRecordsArray(entity.getOtherPatientIdRecords());
        } catch (IOException ex) {
            Logger.getLogger(CaseAssembler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CaseAssembler.class.getName()).log(Level.SEVERE, null, ex);
        }
        dto.setOtherPatientNames(entity.getOtherPatientNames());
        dto.setPatientId(entity.getPatientId());
        dto.setPatientName(entity.getPatientName());
        dto.setSex(entity.getSex());
        dto.setTypeOfPatientId(entity.getTypeOfPatientId());

        return dto;
    }

    public static DicomPatientData toEntity(DicomPatientDataDTO dto) {
        DicomPatientData entity = new DicomPatientData();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setComments(dto.getComments());
        entity.setDob(dto.getDob());
        entity.setIssuerOfPatientId(dto.getIssuerOfPatientId());
        entity.setMothersBirthName(dto.getMothersBirthName());
        entity.setOtherPatientId(dto.getOtherPatientId());
        try {
            entity.setOtherPatientIdRecords(dto.getOtherPatientIdRecordsArray());
        } catch (IOException ex) {
            Logger.getLogger(CaseAssembler.class.getName()).log(Level.SEVERE, null, ex);
        }
        entity.setOtherPatientNames(dto.getOtherPatientNames());
        entity.setPatientId(dto.getPatientId());
        entity.setPatientName(dto.getPatientName());
        entity.setSex(dto.getSex());
        entity.setTypeOfPatientId(dto.getTypeOfPatientId());
        return entity;
    }

    public static ElectronicReferralDTO toDTO(ElectronicReferral entity) {
        ElectronicReferralDTO dto = new ElectronicReferralDTO(entity.getId());
        dto.setDateOfReferral(entity.getDateOfReferral());
        dto.setPlaceOfReferral(entity.getPlaceOfReferral());
        dto.setReferralCodeAndDescriptionList(entity.getReferralCodeAndDescriptionList());
        dto.setReferralCodeList(entity.getReferralCodeList());
        dto.setReferralDescriptionList(entity.getReferralDescriptionList());
        dto.setReferralText(entity.getReferralText());
        dto.setReferringDoctorCode(entity.getReferringDoctorCode());
        dto.setReferringDoctorContacts(entity.getReferringDoctorContacts());
        dto.setReferringDoctorFax(entity.getReferringDoctorFax());
        dto.setReferringDoctorName(entity.getReferringDoctorName());
        dto.setReferringDoctorTel(entity.getReferringDoctorTel());
        dto.setReferringInstitutionAddress(entity.getReferringInstitutionAddress());
        dto.setReferringInstitutionCode(entity.getReferringInstitutionCode());
        dto.setReferringInstitutionContacts(entity.getReferringInstitutionContacts());
        dto.setReferringInstitutionFax(entity.getReferringInstitutionFax());
        dto.setReferringInstitutionName(entity.getReferringInstitutionName());
        dto.setReferringInstitutionTel(entity.getReferringInstitutionTel());
        dto.setReferringUnitAddress(entity.getReferringUnitAddress());
        dto.setReferringUnitCode(entity.getReferringUnitCode());
        dto.setReferringUnitContacts(entity.getReferringUnitContacts());
        dto.setReferringUnitFax(entity.getReferringUnitFax());
        dto.setReferringUnitName(entity.getReferringUnitName());
        dto.setReferringUnitTel(entity.getReferringUnitTel());

        dto.setReferralNumber1Name(entity.getReferralNumber1Name());
        dto.setReferralNumber1ShortName(entity.getReferralNumber1ShortName());
        dto.setReferralNumber1Value(entity.getReferralNumber1Value());
        dto.setReferralNumber2Name(entity.getReferralNumber2Name());
        dto.setReferralNumber2ShortName(entity.getReferralNumber2ShortName());
        dto.setReferralNumber2Value(entity.getReferralNumber2Value());
        return dto;
    }

    public static ElectronicReferral toEntity(ElectronicReferralDTO dto) {
        ElectronicReferral entity = new ElectronicReferral();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setDateOfReferral(dto.getDateOfReferral());
        entity.setPlaceOfReferral(dto.getPlaceOfReferral());
        entity.setReferralCodeAndDescriptionList(dto.getReferralCodeAndDescriptionList());
        entity.setReferralCodeList(dto.getReferralCodeList());
        entity.setReferralDescriptionList(dto.getReferralDescriptionList());
        entity.setReferralText(dto.getReferralText());
        entity.setReferringDoctorCode(dto.getReferringDoctorCode());
        entity.setReferringDoctorContacts(dto.getReferringDoctorContacts());
        entity.setReferringDoctorFax(dto.getReferringDoctorFax());
        entity.setReferringDoctorName(dto.getReferringDoctorName());
        entity.setReferringDoctorTel(dto.getReferringDoctorTel());
        entity.setReferringInstitutionAddress(dto.getReferringInstitutionAddress());
        entity.setReferringInstitutionCode(dto.getReferringInstitutionCode());
        entity.setReferringInstitutionContacts(dto.getReferringInstitutionContacts());
        entity.setReferringInstitutionFax(dto.getReferringInstitutionFax());
        entity.setReferringInstitutionName(dto.getReferringInstitutionName());
        entity.setReferringInstitutionTel(dto.getReferringInstitutionTel());
        entity.setReferringUnitAddress(dto.getReferringUnitAddress());
        entity.setReferringUnitCode(dto.getReferringUnitCode());
        entity.setReferringUnitContacts(dto.getReferringUnitContacts());
        entity.setReferringUnitFax(dto.getReferringUnitFax());
        entity.setReferringUnitName(dto.getReferringUnitName());
        entity.setReferringUnitTel(dto.getReferringUnitTel());

        entity.setReferralNumber1Name(dto.getReferralNumber1Name());
        entity.setReferralNumber1ShortName(dto.getReferralNumber1ShortName());
        entity.setReferralNumber1Value(dto.getReferralNumber1Value());
        entity.setReferralNumber2Name(dto.getReferralNumber2Name());
        entity.setReferralNumber2ShortName(dto.getReferralNumber2ShortName());
        entity.setReferralNumber2Value(dto.getReferralNumber2Value());
        return entity;
    }
    
    public static ElectronicReferral toEntity(ElectronicReferralPackage dataPackage) {
        ElectronicReferral entity = new ElectronicReferral();
        if (dataPackage.getId() != 0) {
            entity.setId(dataPackage.getId());
        }
        entity.setDateOfReferral(dataPackage.getDateOfReferral());
        entity.setPlaceOfReferral(dataPackage.getPlaceOfReferral());
        entity.setReferralCodeAndDescriptionList(dataPackage.getReferralCodeAndDescriptionList());
        entity.setReferralCodeList(dataPackage.getReferralCodeList());
        entity.setReferralDescriptionList(dataPackage.getReferralDescriptionList());
        entity.setReferralText(dataPackage.getReferralText());
        entity.setReferringDoctorCode(dataPackage.getReferringDoctorCode());
        entity.setReferringDoctorContacts(dataPackage.getReferringDoctorContacts());
        entity.setReferringDoctorFax(dataPackage.getReferringDoctorFax());
        entity.setReferringDoctorName(dataPackage.getReferringDoctorName());
        entity.setReferringDoctorTel(dataPackage.getReferringDoctorTel());
        entity.setReferringInstitutionAddress(dataPackage.getReferringInstitutionAddress());
        entity.setReferringInstitutionCode(dataPackage.getReferringInstitutionCode());
        entity.setReferringInstitutionContacts(dataPackage.getReferringInstitutionContacts());
        entity.setReferringInstitutionFax(dataPackage.getReferringInstitutionFax());
        entity.setReferringInstitutionName(dataPackage.getReferringInstitutionName());
        entity.setReferringInstitutionTel(dataPackage.getReferringInstitutionTel());
        entity.setReferringUnitAddress(dataPackage.getReferringUnitAddress());
        entity.setReferringUnitCode(dataPackage.getReferringUnitCode());
        entity.setReferringUnitContacts(dataPackage.getReferringUnitContacts());
        entity.setReferringUnitFax(dataPackage.getReferringUnitFax());
        entity.setReferringUnitName(dataPackage.getReferringUnitName());
        entity.setReferringUnitTel(dataPackage.getReferringUnitTel());

        entity.setReferralNumber1Name(dataPackage.getReferralNumber1Name());
        entity.setReferralNumber1ShortName(dataPackage.getReferralNumber1ShortName());
        entity.setReferralNumber1Value(dataPackage.getReferralNumber1Value());
        entity.setReferralNumber2Name(dataPackage.getReferralNumber2Name());
        entity.setReferralNumber2ShortName(dataPackage.getReferralNumber2ShortName());
        entity.setReferralNumber2Value(dataPackage.getReferralNumber2Value());
        return entity;
    }

    public static ImageReferralDTO toDTO(ImageReferral entity) {
        ImageReferralDTO dto = new ImageReferralDTO(entity.getId());
        for (ScannedReferralImage scannedReferralImage : entity.getScannedReferralImageList()) {
            ScannedReferralImageDTO scannedReferralImageDTO = toDTO(scannedReferralImage);
            dto.getImageList().add(scannedReferralImageDTO);
        }
        return dto;
    }

    public static ImageReferral toEntity(ImageReferralDTO dto) {
        ImageReferral entity = new ImageReferral(dto.getId());
        for (ScannedReferralImageDTO scannedReferralImageDTO : dto.getImageList()) {
            ScannedReferralImage scannedReferralImage = toEntity(scannedReferralImageDTO);
            entity.getScannedReferralImageList().add(scannedReferralImage);
        }
        return entity;
    }
    
    public static ScannedReferralImage toEntity(ReferralFile referralFile) {
        ScannedReferralImage entity = new ScannedReferralImage();
        if (referralFile.getId() != 0) {
            //entity.setId(referralFile.getId());
        }
        entity.setScannedImage(referralFile.getFileContent());
        return entity;
    }

    
    public static ScannedPatientDataImage toEntity(PatientDataFile patientDataFile) {
        ScannedPatientDataImage entity = new ScannedPatientDataImage();
        if (patientDataFile.getId() != 0) {
            //entity.setId(patientDataFile.getId());
        }
        entity.setScannedImage(patientDataFile.getFileContent());
        return entity;
    }
    
    
    public static ImagePatientDataDTO toDTO(ImagePatientData entity) {
        ImagePatientDataDTO dto = new ImagePatientDataDTO(entity.getId());
        for (ScannedPatientDataImage scannedPatientDataImage : entity.getScannedPatientDataImageList()) {
            ScannedPatientDataImageDTO scannedPatientDataImageDTO = toDTO(scannedPatientDataImage);
            dto.getImageList().add(scannedPatientDataImageDTO);
        }
        return dto;
    }

    public static ImagePatientData toEntity(ImagePatientDataDTO dto) {
        ImagePatientData entity = new ImagePatientData(dto.getId());
        for (ScannedPatientDataImageDTO scannedPatientDataImageDTO : dto.getImageList()) {
            ScannedPatientDataImage scannedPatientDataImage = toEntity(scannedPatientDataImageDTO);
            entity.getScannedPatientDataImageList().add(scannedPatientDataImage);
        }
        return entity;
    }

    public static ReferralInfoDTO toDTO(ReferralInfo entity) {
        ReferralInfoDTO dto = new ReferralInfoDTO(entity.getId());
        dto.setCaseItBelongsTo(new CaseDTO(entity.getTDSCase().getId()));
        if (entity.getElectronicReferral() != null) {
            dto.setElectronicReferral(toDTO(entity.getElectronicReferral()));
        }
        if (entity.getImageReferral() != null) {
            dto.setImageReferral(toDTO(entity.getImageReferral()));
        }
        return dto;
    }

    public static CaseDTO toDTO(TDSCase entity, boolean isDraft) {
        CaseDTO dto = new CaseDTO(entity.getId());
        dto.setTransferredToServer(entity.getTransferredToServer());
        dto.setTransferStarted(entity.getTransferStarted());
        dto.setTdsCaseUniqueId(entity.getTdsCaseUniqueId());
        dto.setAcceptedAndAssigned(entity.getAcceptedAndAssigned());
        dto.setConfirmedDone(entity.getConfirmedDone());
        dto.setReadyDisplayedOnHC(entity.getReadyDisplayedOnHC());
        dto.setHospTakes(entity.getHospTakes());
        dto.setRejectedByTDS(entity.getRejectedByTDS());
        dto.setHospitalCaseIdDicomAttributeCode(entity.getHospitalCaseIdDicomAttributeCode());
        dto.setHospitalCaseIdDicomAttributeValue(entity.getHospitalCaseIdDicomAttributeValue());
        dto.setCaseStatus(MasterDataAssembler.toDTO(entity.getCaseStatus()));
        dto.setModeOfAcquisition(MasterDataAssembler.toDTO(entity.getModeOfAcquisition()));
        dto.setSenderHospitalStaff(UserAssembler.toDTO(entity.getHospitalStaff()));
        dto.setFinalizedReport(entity.getFinalizedReport());
        dto.setUrgency(entity.getUrgency());
        dto.setDeadLine(entity.getDeadLine());
        if (entity.getHospitalStaff1() != null) {
            dto.setReceiverHospitalStaff(UserAssembler.toDTO(entity.getHospitalStaff1()));
        }
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));

        dto.setAgreementTest(entity.getAgreementTest());
        dto.setAgreementTestDateTime(entity.getAgreementTestDateTime());
        dto.setNonDicomCaseIdInHospital1Name(entity.getNonDicomCaseIdInHospital1Name());
        dto.setNonDicomCaseIdInHospital1ShortName(entity.getNonDicomCaseIdInHospital1ShortName());
        dto.setNonDicomCaseIdInHospital1Value(entity.getNonDicomCaseIdInHospital1Value());
        dto.setNonDicomCaseIdInHospital2Name(entity.getNonDicomCaseIdInHospital2Name());
        dto.setNonDicomCaseIdInHospital2ShortName(entity.getNonDicomCaseIdInHospital2ShortName());
        dto.setNonDicomCaseIdInHospital2Value(entity.getNonDicomCaseIdInHospital2Value());

        if (entity.getElectronicPatientData() != null) {
            dto.setElectronicPatientData(toDTO(entity.getElectronicPatientData()));
        }

        if (entity.getDicomPatientData() != null) {
            dto.setDicomPatientData(toDTO(entity.getDicomPatientData()));
        }
        if (entity.getImagePatientData() != null) {
            dto.setImagePatientData(toDTO(entity.getImagePatientData()));
        }

        System.out.println(entity.getReferralInfoCollection() != null);
        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                System.out.println(referralInfo.getId());
                dto.getReferralInfoList().add(toDTO(referralInfo));
            }
        }

        for (Reporting reporting : entity.getReportingCollection()) {
            if (reporting.getActive()) {
                dto.getReportingList().add(ReportingAssembler.toDTO(reporting));
                dto.setUnfinalizedReport(reporting.getUnfinishedText());
            }
        }
        for (FlagAssignment flagAssignment : entity.getFlagAssignmentCollection()) {
            if (flagAssignment.getActive()) {
                dto.getFlagAssignmentList().add(CaseAssembler.toDTO(flagAssignment));
            }
        }

        for (ScenarioInstance scenarioInstance : entity.getScenarioInstanceCollection()) {
            dto.getScenarioInstanceList().add(toDTO(scenarioInstance));
        }

        HashSet<String> modalities = new HashSet<String>();
        HashSet<String> bodyRegions = new HashSet<String>();
        HashSet<String> studyDates = new HashSet<String>();
        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                if (referralInfo.gettDSStudyCollection() != null) {
                    for (TDSStudy tDSStudy : referralInfo.gettDSStudyCollection()) {
                        studyDates.add(tDSStudy.getStudyDate().toString());
                        if (tDSStudy.getSeriesCollection() != null) {
                            for (Series series : tDSStudy.getSeriesCollection()) {
                                modalities.add(series.getModality().getName());
                                bodyRegions.add(series.getBodyRegion1().getEnglishName());
                            }
                        }
                    }
                }

            }
        }

//        if (entity.getTDSStudyCollection() != null) {
//            for (TDSStudy tDSStudy : entity.getTDSStudyCollection()) {
//                studyDates.add(tDSStudy.getStudyDate().toString());
//                if (tDSStudy.getSeriesCollection() != null) {
//                    for (Series series : tDSStudy.getSeriesCollection()) {
//                        modalities.add(series.getModality().getName());
//                        bodyRegions.add(series.getBodyRegion1().getEnglishName());
//                    }
//                }
//            }
//        }
        dto.setModalities(modalities.toString());
        dto.setBodyRegions(bodyRegions.toString());
        dto.setStudyDates(studyDates.toString());

        //study structure
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        ArrayList<StudyWithBodyRegionPM> studyWithBodyRegionList = new ArrayList<StudyWithBodyRegionPM>();
        for (TDSStudy study : entity.getReferralInfoCollection().get(0).gettDSStudyCollection()) {
            StudyWithBodyRegionPM pmStudy = new StudyWithBodyRegionPM();
            pmStudy.setStudyNumberInList(study.getNumberInList());
            sb.append("<tr>");
            sb.append("<td>" + pmStudy.getStudyNumber() + "</td>");
            sb.append("<td></td>");
            sb.append("<td></td>");
            sb.append("<td></td>");
            sb.append("</tr>");
            for (Series series : study.getSeriesCollection()) {
                StudyWithBodyRegionPM pm = new StudyWithBodyRegionPM();
                pm.setStudyId(study.getId());
                pm.setStudyUid(study.getInstanceUid());
                pm.setSeriesId(series.getId());
                pm.setSeriesUid(series.getInstanceUid());
                pm.setModalityId(series.getModality().getId());
                pm.setModalityName(series.getModality().getName());
                pm.setHospitalBodyRegionId(series.getBodyRegion1().getId());
                pm.setHospitalBodyRegionName(series.getBodyRegion1().getEnglishName());
                pm.setStudyNumberInList(study.getNumberInList());
                pm.setSeriesNumberInList(series.getNumberInList());
                studyWithBodyRegionList.add(pm);
                sb.append("<tr>");
                sb.append("<td></td>");
                sb.append("<td>" + pm.getModalityName() + "</td>");
                sb.append("<td>" + pm.getHospitalBodyRegionName() + "</td>");
                sb.append("<td>" + pm.getSeriesNumber() + "</td>");
                sb.append("</tr>");
            }
        }
        String studyGroup = "";
        List<SeriesGroup> seriesGroups = new ArrayList<SeriesGroup>();
        for (StudyWithBodyRegionPM studyWithBodyRegionPM : studyWithBodyRegionList) {
            SeriesGroup sg = new SeriesGroup(
                    studyWithBodyRegionPM.getStudyUid(),
                    studyWithBodyRegionPM.getModalityName(),
                    studyWithBodyRegionPM.getHospitalBodyRegionName());
            if (!seriesGroups.contains(sg)) {
                seriesGroups.add(sg);
            } else {
                seriesGroups.get(seriesGroups.indexOf(sg)).setSeriesNumber(
                        seriesGroups.get(seriesGroups.indexOf(sg)).getSeriesNumber() + 1);
            }
        }

        sb.append("</body></html>");
        //dto.setStudyStructure(sb.toString());
        dto.setStudyStructure(createStudyStructure(entity.getReferralInfoCollection().get(0).gettDSStudyCollection()));

        //preparing history case
        //System.out.println(entity.getHospitalCaseHistoryList());
        if (entity.getHospitalCaseHistoryList() != null) {
            dto.setHospitalCaseHistoryList(new ArrayList<HistoryCaseDTO>());
            for (HistoryCase historyCase : entity.getHospitalCaseHistoryList()) {
                HistoryCaseDTO historyCaseDTO = toDTO(historyCase);
                historyCaseDTO.setStudyStructure(
                        createHistoryStudyStructure(historyCase.getReferralInfoList().get(0).gettDSStudyCollection()));
                dto.getHospitalCaseHistoryList().add(historyCaseDTO);
            }
        }


        if (isDraft) {
            return dto;
        }

        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                ReferralInfoDTO riDTO = toDTO(referralInfo);
                riDTO.setCaseItBelongsTo(dto);
                dto.getReferralInfoList().add(riDTO);
                if (referralInfo.gettDSStudyCollection() != null) {
                    for (TDSStudy tDSStudy : referralInfo.gettDSStudyCollection()) {
                        StudyDTO studyDTO = toDTO(tDSStudy);
                        //studyDTO.setReferralInfo(toDTO(referralInfo));
                        studyDTO.setReferralInfo(riDTO);
                        int index = dto.getReferralInfoList().indexOf(riDTO);
                        dto.getReferralInfoList().get(index).getStudyList().add(studyDTO);
                    }
                }
            }
        }

//        if (entity.getTDSStudyCollection() != null) {
//            for (TDSStudy tDSStudy : entity.getTDSStudyCollection()) {
//                StudyDTO studyDTO = toDTO(tDSStudy);
//                studyDTO.setCaseItBelongsTo(dto);
//                dto.getStudyList().add(studyDTO);
//            }
//        }

        return dto;
    }

    public static String createHistoryStudyStructure(List<TDSStudy> studyList) {
        String ret = null;
        StringBuilder sb = new StringBuilder();
        for (TDSStudy study : studyList) {
            HashMap<StudyStructureHelper, Integer> seriesMap = new HashMap<StudyStructureHelper, Integer>();
            sb.append("Accession number");
            sb.append("\t");
            sb.append(study.getAccessionNumber());
            sb.append("\t");
            sb.append("Study date");
            sb.append("\t");
            sb.append(study.getStudyDate());
            sb.append("\t");
            for (Series series : study.getSeriesCollection()) {
                StudyStructureHelper studyStructureHelper =
                        new StudyStructureHelper(study.getAccessionNumber(),
                        study.getStudyDate(), series.getModality().getName(),
                        series.getBodyRegion1().getEnglishName());
                if (seriesMap.containsKey(studyStructureHelper)) {
                    Integer number = seriesMap.get(studyStructureHelper);
                    seriesMap.put(studyStructureHelper, number + 1);

                } else {
                    seriesMap.put(studyStructureHelper, new Integer(1));
                }
            }
            Set<StudyStructureHelper> set = seriesMap.keySet();
            boolean first = true;
            for (StudyStructureHelper studyStructureHelper : set) {
                if (first) {
                    first = false;
                } else {
                    sb.append("\t");
                    sb.append("\t");
                    sb.append("\t");
                    sb.append("\t");
                }
                sb.append(studyStructureHelper.modality);
                sb.append("\t");
                sb.append(studyStructureHelper.bodyRegion);
                sb.append("\t");
                sb.append(seriesMap.get(studyStructureHelper));
                sb.append(" series");
            }
        }
//        StringBuilder sb = new StringBuilder();
//        sb.append("<html><body>");
//        sb.append("<table style=\"font-family: Arial; font-size: 8px;\">");
//
//        Set<StudyStructureHelper> set = seriesMap.keySet();
//        for (StudyStructureHelper studyStructureHelper : set) {
//            sb.append("<tr>");
//            sb.append("<td width=\"100\">Accession number</td>");
//            sb.append("<td>" + studyStructureHelper.accessionNumber + "</td>");
//            sb.append("<td>Study date</td>");
//            sb.append("<td>" + studyStructureHelper.studyDate + "</td>");
//            sb.append("<td  width=\"20\">" + studyStructureHelper.modality + "</td>");
//            sb.append("<td>" + studyStructureHelper.bodyRegion + "</td>");
//            sb.append("<td>" + seriesMap.get(studyStructureHelper) + " series" + "</td>");
//            sb.append("</tr>");
//        }
//        sb.append("</table>");
//        sb.append("</body></html>");
        return sb.toString();
    }

    public static String createStudyStructure(List<TDSStudy> studyList) {
        return createStudyStructure(studyList, false);
    }

    public static String createStudyStructure(List<TDSStudy> studyList, boolean hospitalClient) {
        String ret = null;
        StringBuilder sb = new StringBuilder();
        int studyNumber = 0;
        for (TDSStudy study : studyList) {
            sb.append("Study ");
            sb.append(++studyNumber);
            sb.append(hospitalClient ? "\n\t" : "");
            sb.append(hospitalClient ? "" : " (");
            sb.append("Accession number - ");
            sb.append(study.getAccessionNumber());
            sb.append(hospitalClient ? "\n\t" : ", ");
            sb.append("Study date - ");
            sb.append(study.getStudyDate());
            sb.append(hospitalClient ? "\n\t" : ")\n   ");
            HashMap<StudyStructureHelper, Integer> seriesMap = new HashMap<StudyStructureHelper, Integer>();
            for (Series series : study.getSeriesCollection()) {
                StudyStructureHelper studyStructureHelper =
                        new StudyStructureHelper(study.getAccessionNumber(),
                        study.getStudyDate(), series.getModality().getName(),
                        series.getBodyRegion1().getEnglishName());
                if (seriesMap.containsKey(studyStructureHelper)) {
                    Integer number = seriesMap.get(studyStructureHelper);
                    seriesMap.put(studyStructureHelper, number + 1);

                } else {
                    seriesMap.put(studyStructureHelper, new Integer(1));
                }
            }
            Set<StudyStructureHelper> set = seriesMap.keySet();
            for (StudyStructureHelper studyStructureHelper : set) {
                sb.append("\t");
                sb.append(studyStructureHelper.modality);
                sb.append("\t");
                sb.append(studyStructureHelper.bodyRegion);
                sb.append(hospitalClient ? "" : "\t");
                sb.append(hospitalClient ? "" : seriesMap.get(studyStructureHelper));
                sb.append(hospitalClient ? "\n" : " series\n");
            }
        }
        return sb.toString();
    }

    public static HistoryCaseDTO toDTO(HistoryCase entity) {
        HistoryCaseDTO dto = new HistoryCaseDTO();
        dto.setFinalizedReport(entity.getFinalizedReport());
        dto.setDicomPatientData(toDTO(entity.getDicomPatientData()));
        if (!entity.getReferralInfoList().isEmpty()) {
            dto.getReferralInfoList().add(new ReferralInfoDTO());
            if (entity.getReferralInfoList().get(0).getElectronicReferral() != null) {
                dto.getReferralInfoList().get(0).setElectronicReferral(toDTO(entity.getReferralInfoList().get(0).getElectronicReferral()));
            }
            if (entity.getReferralInfoList().get(0).getImageReferral() != null) {
                dto.getReferralInfoList().get(0).setImageReferral(new ImageReferralDTO());
                for (ScannedReferralImage scannedReferralImage : entity.getReferralInfoList().get(0).getImageReferral().getScannedReferralImageList()) {
                    ScannedReferralImageDTO scannedReferralImageDTO = new ScannedReferralImageDTO();
                    scannedReferralImageDTO.setScannedImage(scannedReferralImage.getScannedImage());
                    dto.getReferralInfoList().get(0).getImageReferral().getImageList().add(scannedReferralImageDTO);
                }
            }
        }
        if (entity.getImageReport() != null) {
            dto.setImageReport(new ImageReportDTO());
            dto.getImageReport().setImageList(new ArrayList<ScannedReportImageDTO>());
            for (ScannedReportImage scannedReportImage : entity.getImageReport().getScannedReportImageList()) {
                ScannedReportImageDTO scannedReportImageDTO = new ScannedReportImageDTO();
                scannedReportImageDTO.setScannedImage(scannedReportImage.getScannedImage());
                dto.getImageReport().getImageList().add(scannedReportImageDTO);
            }
        }
        return dto;
    }

    public static CaseDTO toHcDTO(TDSCase entity) {
        CaseDTO dto = new CaseDTO(entity.getId());
        dto.setTransferredToServer(entity.getTransferredToServer());
        dto.setTransferStarted(entity.getTransferStarted());
        dto.setTdsCaseUniqueId(entity.getTdsCaseUniqueId());
        dto.setAcceptedAndAssigned(entity.getAcceptedAndAssigned());
        dto.setConfirmedDone(entity.getConfirmedDone());
        dto.setReadyDisplayedOnHC(entity.getReadyDisplayedOnHC());
        dto.setHospTakes(entity.getHospTakes());
        dto.setRejectedByTDS(entity.getRejectedByTDS());
        dto.setHospitalCaseIdDicomAttributeCode(entity.getHospitalCaseIdDicomAttributeCode());
        dto.setHospitalCaseIdDicomAttributeValue(entity.getHospitalCaseIdDicomAttributeValue());
        dto.setCaseStatus(MasterDataAssembler.toDTO(entity.getCaseStatus()));
        dto.setModeOfAcquisition(MasterDataAssembler.toDTO(entity.getModeOfAcquisition()));
        dto.setSenderHospitalStaff(UserAssembler.toDTO(entity.getHospitalStaff()));
        dto.setFinalizedReport(entity.getFinalizedReport());
        dto.setDeadLine(entity.getDeadLine());
        if (entity.getHospitalStaff1() != null) {
            dto.setReceiverHospitalStaff(UserAssembler.toDTO(entity.getHospitalStaff1()));
        }
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));

        dto.setAgreementTest(entity.getAgreementTest());
        dto.setAgreementTestDateTime(entity.getAgreementTestDateTime());
        dto.setNonDicomCaseIdInHospital1Name(entity.getNonDicomCaseIdInHospital1Name());
        dto.setNonDicomCaseIdInHospital1ShortName(entity.getNonDicomCaseIdInHospital1ShortName());
        dto.setNonDicomCaseIdInHospital1Value(entity.getNonDicomCaseIdInHospital1Value());
        dto.setNonDicomCaseIdInHospital2Name(entity.getNonDicomCaseIdInHospital2Name());
        dto.setNonDicomCaseIdInHospital2ShortName(entity.getNonDicomCaseIdInHospital2ShortName());
        dto.setNonDicomCaseIdInHospital2Value(entity.getNonDicomCaseIdInHospital2Value());


        for (Reporting reporting : entity.getReportingCollection()) {
            if (reporting.getActive()) {
                dto.getReportingList().add(ReportingAssembler.toDTO(reporting));
                dto.setAssignedRadiologist(new TDSRadiologistDTO(reporting.getTDSRadiologist().getId()));
                //dto.getAssignedRadiologist().getUserInfo().setName(reporting.getTDSRadiologist().getItsUser().getName());
            }
        }

        for (FlagAssignment flagAssignment : entity.getFlagAssignmentCollection()) {
            if (flagAssignment.getActive()) {
                dto.getFlagAssignmentList().add(CaseAssembler.toDTO(flagAssignment));
            }
        }

        if (entity.getElectronicPatientData() != null) {
            dto.setElectronicPatientData(toDTO(entity.getElectronicPatientData()));
        }

        if (entity.getDicomPatientData() != null) {
            dto.setDicomPatientData(toDTO(entity.getDicomPatientData()));
        }
        if (entity.getImagePatientData() != null) {
            dto.setImagePatientData(toDTO(entity.getImagePatientData()));
        }

        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                dto.getReferralInfoList().add(toDTO(referralInfo));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<tr>");
        HashSet<String> modalities = new HashSet<String>();
        HashSet<String> bodyRegions = new HashSet<String>();
        HashSet<String> studyDates = new HashSet<String>();
        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                if (referralInfo.gettDSStudyCollection() != null) {
                    for (TDSStudy tDSStudy : referralInfo.gettDSStudyCollection()) {
                        studyDates.add(tDSStudy.getStudyDate().toString());
                        sb.append("<td>" + tDSStudy.getStudyDate().toString() + "</td>");
                        if (tDSStudy.getSeriesCollection() != null) {
                            for (Series series : tDSStudy.getSeriesCollection()) {
                                modalities.add(series.getModality().getName());
                                bodyRegions.add(series.getBodyRegion1().getEnglishName());
                                sb.append("<td>" + series.getModality().getName() + "</td>");
                                sb.append("<td>" + series.getBodyRegion1().getEnglishName() + "</td>");
                            }
                        }
                    }
                }

            }
        }
        sb.append("</tr>");
        sb.append("</body></html>");
        dto.setModalities(modalities.toString());
        dto.setBodyRegions(bodyRegions.toString());
        dto.setStudyDates(studyDates.toString());
        //dto.setStudyStructure(sb.toString());

        dto.setStudyStructure(createStudyStructure(entity.getReferralInfoCollection().get(0).gettDSStudyCollection(), true));
        HashSet<String> scenarios = new HashSet<String>();
        for (ScenarioInstance scenarioInstance : entity.getScenarioInstanceCollection()) {
            scenarios.add(scenarioInstance.getScenario().getCategory());
        }
        dto.setScenarios(scenarios.toString());
        return dto;
    }

    public static CaseDTO toMcDTO(TDSCase entity) {
        CaseDTO dto = new CaseDTO(entity.getId());
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));
        dto.getHospitalContract().setHospital(new HospitalDTO(entity.getHospitalContract().getHospital().getId()));
        dto.getHospitalContract().getHospital().setAbbrevName(entity.getHospitalContract().getHospital().getAbbrevName());

        dto.setUrgency(entity.getUrgency());
        dto.setTransferredToServer(entity.getTransferredToServer());
        dto.setDeadLine(entity.getDeadLine());
        dto.setCaseStatus(MasterDataAssembler.toDTO(entity.getCaseStatus()));


        dto.setFinalizedReport(entity.getFinalizedReport());


        for (Reporting reporting : entity.getReportingCollection()) {
            if (reporting.getActive()) {
                dto.getReportingList().add(ReportingAssembler.toDTO(reporting));
                dto.setAssignedRadiologist(new TDSRadiologistDTO(reporting.getTDSRadiologist().getId()));
                dto.getAssignedRadiologist().getUserInfo().setName(reporting.getTDSRadiologist().getItsUser().getName());
                //        dto.setFinalizedReport(reporting.getUnfinishedText());
            }
        }

        HashSet<String> modalities = new HashSet<String>();
        HashSet<String> bodyRegions = new HashSet<String>();
        HashSet<String> studyDates = new HashSet<String>();
        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                if (referralInfo.gettDSStudyCollection() != null) {
                    for (TDSStudy tDSStudy : referralInfo.gettDSStudyCollection()) {
                        studyDates.add(tDSStudy.getStudyDate().toString());
                        if (tDSStudy.getSeriesCollection() != null) {
                            for (Series series : tDSStudy.getSeriesCollection()) {
                                modalities.add(series.getModality().getName());
                                bodyRegions.add(series.getBodyRegion1().getEnglishName());
                            }
                        }
                    }
                }

            }
        }
        dto.setModalities(modalities.toString());
        dto.setBodyRegions(bodyRegions.toString());
        dto.setStudyDates(studyDates.toString());
        return dto;
    }

    public static CaseDTO toAnonymDTO(TDSCase entity) {
        CaseDTO dto = new CaseDTO(entity.getId());
        dto.setTransferredToServer(entity.getTransferredToServer());
        dto.setTransferStarted(entity.getTransferStarted());
        dto.setTdsCaseUniqueId("***");
        dto.setAcceptedAndAssigned(entity.getAcceptedAndAssigned());
        dto.setConfirmedDone(entity.getConfirmedDone());
        dto.setReadyDisplayedOnHC(entity.getReadyDisplayedOnHC());
        dto.setHospTakes(entity.getHospTakes());
        dto.setDeadLine(entity.getDeadLine());
        dto.setRejectedByTDS(entity.getRejectedByTDS());
        dto.setHospitalCaseIdDicomAttributeCode(entity.getHospitalCaseIdDicomAttributeCode());
        dto.setHospitalCaseIdDicomAttributeValue(entity.getHospitalCaseIdDicomAttributeValue());
        dto.setCaseStatus(MasterDataAssembler.toDTO(entity.getCaseStatus()));
        dto.setModeOfAcquisition(MasterDataAssembler.toDTO(entity.getModeOfAcquisition()));
        dto.setSenderHospitalStaff(new HospitalStaffDTO());
        dto.setDeadLine(entity.getDeadLine());

        dto.setAgreementTest(entity.getAgreementTest());
        dto.setAgreementTestDateTime(entity.getAgreementTestDateTime());
        dto.setNonDicomCaseIdInHospital1Name(entity.getNonDicomCaseIdInHospital1Name());
        dto.setNonDicomCaseIdInHospital1ShortName(entity.getNonDicomCaseIdInHospital1ShortName());
        dto.setNonDicomCaseIdInHospital1Value(entity.getNonDicomCaseIdInHospital1Value());
        dto.setNonDicomCaseIdInHospital2Name(entity.getNonDicomCaseIdInHospital2Name());
        dto.setNonDicomCaseIdInHospital2ShortName(entity.getNonDicomCaseIdInHospital2ShortName());
        dto.setNonDicomCaseIdInHospital2Value(entity.getNonDicomCaseIdInHospital2Value());

        dto.setDicomPatientData(new DicomPatientDataDTO());

        HashSet<String> modalities = new HashSet<String>();
        HashSet<String> bodyRegions = new HashSet<String>();
        HashSet<String> studyDates = new HashSet<String>();
        if (entity.getReferralInfoCollection() != null) {
            for (ReferralInfo referralInfo : entity.getReferralInfoCollection()) {
                if (referralInfo.gettDSStudyCollection() != null) {
                    for (TDSStudy tDSStudy : referralInfo.gettDSStudyCollection()) {
                        studyDates.add(tDSStudy.getStudyDate().toString());
                        if (tDSStudy.getSeriesCollection() != null) {
                            for (Series series : tDSStudy.getSeriesCollection()) {
                                modalities.add(series.getModality().getName());
                                bodyRegions.add(series.getBodyRegion1().getEnglishName());
                            }
                        }
                    }
                }

            }
        }
        dto.setModalities(modalities.toString());
        dto.setBodyRegions(bodyRegions.toString());
        dto.setStudyDates(studyDates.toString());

        return dto;
    }

    public static ScenarioInstanceDTO toDTO(ScenarioInstance entity) {
        ScenarioInstanceDTO dto = new ScenarioInstanceDTO(entity.getId());
        dto.setAdded(entity.getAdded());
        dto.setNote(entity.getNote());
        dto.setDeactivated(entity.getDeactivated());
        dto.setScenario(MasterDataAssembler.toDTO(entity.getScenario()));
        dto.setTdsCase(new CaseDTO(entity.getTDSCase().getId()));
        return dto;
    }

    public static ScenarioInstance toEntity(ScenarioInstanceDTO dto) {
        ScenarioInstance entity = new ScenarioInstance();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAdded(dto.getAdded());
        entity.setNote(dto.getNote());
        entity.setDeactivated(dto.getDeactivated());
        entity.setScenario(new Scenario(dto.getScenario().getId()));
        entity.setTDSCase(new TDSCase(dto.getTdsCase().getId()));
        return entity;
    }

    public static StudyDTO toDTO(TDSStudy entity) {
        StudyDTO dto = new StudyDTO(entity.getId());
        dto.setInstanceUid(entity.getInstanceUid());
        dto.setStudyId(entity.getStudyId());
        dto.setStudyDate(entity.getStudyDate());
        dto.setStudyTime(entity.getStudyTime());
        dto.setDescription(entity.getDescription());
        dto.setReferringPhysyciansDicomName(entity.getReferringPhysyciansDicomName());
        dto.setAdmittingDiagnoseDescription(entity.getAdmittingDiagnoseDescription());
        dto.setAdditionalPatientHistory(entity.getAdditionalPatientHistory());
        dto.setPatientMedicalAlerts(entity.getPatientMedicalAlerts());
        dto.setSmokingStatus(entity.getSmokingStatus());
        dto.setAccessionNumber(entity.getAccessionNumber());
        dto.setNumberInList(entity.getNumberInList());
        for (Series series : entity.getSeriesCollection()) {
            SeriesDTO seriesDTO = toDTO(series);
            seriesDTO.setStudy(dto);
            dto.getSeriesList().add(seriesDTO);
        }
        return dto;
    }

    public static SeriesDTO toDTO(Series entity) {
        SeriesDTO dto = new SeriesDTO(entity.getId());
        dto.setInitialWorkValue(entity.getInitialWorkValue() == null ? 0 : entity.getInitialWorkValue());
        dto.setInitialWorkTimeHospital(entity.getInitialWorkTimeHospital() == null ? 0 : entity.getInitialWorkTimeHospital());
        dto.setInitialWorkTimeRadiologist(entity.getInitialWorkTimeRadiologist() == null ? 0 : entity.getInitialWorkTimeRadiologist());
        dto.setInitialCalculated(entity.getInitialCalculated());
        dto.setFinalWorkValue(entity.getFinalWorkValue() == null ? 0 : entity.getFinalWorkValue());
        dto.setFinalWorkTimeHospital(entity.getFinalWorkTimeHospital() == null ? 0 : entity.getFinalWorkTimeHospital());
        dto.setFinalWorkTimeRadiologist(entity.getFinalWorkTimeRadiologist() == null ? 0 : entity.getFinalWorkTimeRadiologist());
        dto.setFinalCalculated(entity.getFinalCalculated());
        dto.setInstanceUid(entity.getInstanceUid());
        dto.setSeriesNumberValue(entity.getSeriesNumberValue());
        dto.setSeriesNumberState(entity.getSeriesNumberState());
        dto.setLaterality(entity.getLaterality());
        dto.setSeriesDate(entity.getSeriesDate());
        dto.setSeriesTime(entity.getSeriesTime());
        dto.setSeriesMaxRawValue(entity.getSeriesMaxRawValue() == null ? 0 : entity.getSeriesMaxRawValue());
        dto.setSeriesMinRawValue(entity.getSeriesMinRawValue() == null ? 0 : entity.getSeriesMinRawValue());
        dto.setPerformingPhysiciansDicomName(entity.getPerformingPhysiciansDicomName());
        dto.setProtocolName(entity.getProtocolName());
        dto.setDescription(entity.getDescription());
        dto.setOperatorsDicomName(entity.getOperatorsDicomName());
        dto.setBodyPartExamined(entity.getBodyPartExamined());
        dto.setClinicalTrialSeriesDescription(entity.getClinicalTrialSeriesDescription());
        dto.setClinicalTrialSeriesId(entity.getClinicalTrialSeriesId());
        dto.setFrameOfReferenceUid(entity.getFrameOfReferenceUid());
        dto.setEquipmentDateOfLastCalibration(entity.getEquipmentDateOfLastCalibration());
        dto.setEquipmentDeviceSerialNumber(entity.getEquipmentDeviceSerialNumber());
        dto.setEquipmentInstitutionalDepartmentName(entity.getEquipmentLocationInstitutionName());
        dto.setEquipmentManufacturer(entity.getEquipmentManufacturer());
        dto.setEquipmentManufacturersModelName(entity.getEquipmentManufacturersModelName());
        dto.setEquipmentStationName(entity.getEquipmentStationName());
        dto.setEquipmentTimeOfLastCalibration(entity.getEquipmentTimeOfLastCalibration());
        dto.setRadiologistBodyRegionAdded(entity.getRadiologistBodyRegionAdded());
        dto.setSupervisorBodyRegionAdded(entity.getSupervisorBodyRegionAdded());
        dto.setModality(MasterDataAssembler.toDTO(entity.getModality()));
        dto.setHospitalBodyRegion(MasterDataAssembler.toDTO(entity.getBodyRegion1()));
        if (entity.getBodyRegion() != null) {
            dto.setRadiologistBodyRegion(MasterDataAssembler.toDTO(entity.getBodyRegion()));
        }
        if (entity.getBodyRegion2() != null) {
            dto.setSupervisorBodyRegion(MasterDataAssembler.toDTO(entity.getBodyRegion2()));
        }

        for (DicomImage dicomImageDataSet : entity.getDicomImageCollection()) {
            DicomImageDTO dicomImageDataSetDTO = toDTO(dicomImageDataSet);
            dicomImageDataSetDTO.setSeries(dto);
            dto.getDicomImageList().add(dicomImageDataSetDTO);
        }

        return dto;
    }

    public static DicomImageDTO toDTO(DicomImage entity) {
        DicomImageDTO dto = new DicomImageDTO(entity.getId());
        dto.setDicomUniqueId(entity.getDicomUniqueId());
        dto.setInstanceNumber(entity.getInstanceNumber());
        dto.setInstanceUid(entity.getInstanceUid());
        dto.setSopAuthorizationComment(entity.getSopAuthorizationComment());
        dto.setSopAuthorizationDateTime(entity.getSopAuthorizationDateTime());
        dto.setSopClassUid(entity.getSopClassUid());
        dto.setSopInstanceStatus(entity.getSopInstanceStatus());
        dto.setTimeZoneOffsetFromUtc(entity.getTimeZoneOffsetFromUtc());
        dto.setSeries(new SeriesDTO(entity.getSeries().getId()));
        //dto.setDicomDataSetArray(entity.getDicomDataSet());
        return dto;
    }

    public static TDSCase updateEntity(CaseDTO dto, TDSCase oldEntity) {
        oldEntity.setCaseStatus(MasterDataAssembler.toEntity(dto.getCaseStatus()));
        oldEntity.setModeOfAcquisition(MasterDataAssembler.toEntity(dto.getModeOfAcquisition()));
        oldEntity.setHospitalStaff(UserAssembler.updateEntity(dto.getSenderHospitalStaff(), oldEntity.getHospitalStaff()));
        oldEntity.setHospitalStaff1(UserAssembler.updateEntity(dto.getSenderHospitalStaff(), oldEntity.getHospitalStaff1()));
        return oldEntity;
    }

    public static byte[] toByteArray(DicomDataSet dicomDataSet) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(dicomDataSet);
        return baos.toByteArray();
    }

    public static DicomDataSet toDicomDataSet(byte[] array) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(array));
        return (DicomDataSet) ois.readObject();
    }

    public static byte[] toByteArray(String[] stringArray) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(stringArray);
        return baos.toByteArray();
    }

    public static String[] toStringArray(byte[] array) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(array));
        return (String[]) ois.readObject();
    }

    public static ScannedReferralImageDTO toDTO(ScannedReferralImage entity) {
        ScannedReferralImageDTO dto = new ScannedReferralImageDTO(entity.getId());
        //dto.setFileName(entity.getFileName());
        //dto.setNumInList(entity.getNumInList());
        dto.setScannedImage(entity.getScannedImage());
        //dto.setImageReferral(new ImageReferralDTO(entity.getImageReferral().getId()));
        return dto;
    }

    public static ScannedReferralImage toEntity(ScannedReferralImageDTO dto) {
        ScannedReferralImage entity = new ScannedReferralImage();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        //entity.setFileName(dto.getFileName());
        //entity.setNumInList(dto.getNumInList());
        entity.setScannedImage(dto.getScannedImage());
        return entity;
    }

    public static ScannedPatientDataImageDTO toDTO(ScannedPatientDataImage entity) {
        ScannedPatientDataImageDTO dto = new ScannedPatientDataImageDTO(entity.getId());
        dto.setScannedImage(entity.getScannedImage());
        //dto.setImagePatientData(new ImagePatientDataDTO(entity.getImagePatientData().getId()));
        return dto;
    }

    public static ScannedPatientDataImage toEntity(ScannedPatientDataImageDTO dto) {
        ScannedPatientDataImage entity = new ScannedPatientDataImage();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setScannedImage(dto.getScannedImage());
        return entity;
    }

    public static SystemMessageTypeDTO toDTO(SystemMessageType entity) {
        SystemMessageTypeDTO dto = new SystemMessageTypeDTO(entity.getId());
        dto.setMessageType(entity.getMessageType());
        dto.setMessageClass(entity.getMessageClass());
        dto.setPriority(entity.getPriority());
        return dto;
    }

    public static SystemMessageDTO toDTO(SystemMessage entity) {
        SystemMessageDTO dto = new SystemMessageDTO(entity.getId());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setEmailWasSent(entity.getEmailWasSent());
        dto.setMessageText(entity.getMessageText());
        dto.setMessageType(toDTO(entity.getSystemMessageType()));
        dto.setRecipient(UserAssembler.toDTO(entity.getTDSUser()));
        dto.setRelatedCase(toDTO(entity.getTDSCase(), true));
        dto.setSent(entity.getSent());
        if (entity.getTDSRadiologist() != null) {
            dto.setRelatedRadiologist(RadiologistAssembler.toDTO(entity.getTDSRadiologist()));
        }
        return dto;
    }

    public static FlagAssignmentDTO toDTO(FlagAssignment entity) {
        FlagAssignmentDTO dto = new FlagAssignmentDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setAdded(entity.getAdded());
        dto.setCaseItBelongsTo(new CaseDTO(entity.getTDSCase().getId()));
        dto.setFlag(new CaseFlagDTO(entity.getCaseFlag().getId(), entity.getCaseFlag().getEnglishName(), entity.getCaseFlag().getHungarianName()));
        return dto;
    }

    public static FlagAssignment toEntity(FlagAssignmentDTO dto) {
        FlagAssignment entity = new FlagAssignment();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setActive(dto.isActive());
        entity.setAdded(dto.getAdded());
        entity.setTDSCase(new TDSCase(dto.getCaseItBelongsTo().getId()));
        entity.setCaseFlag(new CaseFlag(entity.getCaseFlag().getId(), entity.getCaseFlag().getEnglishName(), entity.getCaseFlag().getHungarianName()));
        return entity;
    }

    public static DataProcLogEntryDTO toDTO(DataProcLogEntry entity) {
        DataProcLogEntryDTO dto = new DataProcLogEntryDTO(entity.getId());
        dto.setColumnName(entity.getColumnName());
        dto.setDisplayedByClient(entity.getDisplayedByClient());
        dto.setDisplayedByUser(entity.getDisplayedByUser());
        dto.setEntry(entity.getEntry());
        dto.setEntryGeneration(entity.getEntryGeneration());
        dto.setEntryType(entity.getEntryType());
        dto.setFromValue(entity.getFromValue());
        dto.setNote(entity.getNote());
        dto.setRecordUid(entity.getRecordUid());
        dto.setSender(entity.getSender());
        dto.setTableName(entity.getTableName());
        dto.setToValue(entity.getToValue());
        dto.setDataProcLog(
                new DataProcLogDTO(
                entity.getDataProcLog().getId(),
                new CaseDTO(entity.getDataProcLog().getTDSCase().getId(), entity.getDataProcLog().getTDSCase().getTdsCaseUniqueId()),
                entity.getDataProcLog().getStarted()));
        return dto;
    }
}
