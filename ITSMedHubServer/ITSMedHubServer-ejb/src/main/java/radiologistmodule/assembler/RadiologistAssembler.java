/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.assembler;

import radiologistmodule.entity.Company;
import radiologistmodule.entity.CompanyAssignment;
import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.entity.Hospital;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.entity.Country;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.RadAvailabilityDTO;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.dto.SuperVisionDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.dto.WorkPlaceDTO;
import radiologistmodule.entity.ITSRadiologist;
import radiologistmodule.entity.RadAvailability;
import radiologistmodule.entity.RadCompetence;
import radiologistmodule.entity.RegLicQualOwnership;
import radiologistmodule.entity.SuperVision;
import radiologistmodule.entity.WorkPlace;
import usermodule.assembler.UserAssembler;

/**
 *
 * @author vincze.attila
 */
public class RadiologistAssembler {

    public static ITSRadiologist updateEntity(TDSRadiologistDTO dto, ITSRadiologist oldEntity) {
        oldEntity.setAccountNumber(dto.getAccountNumber());
        oldEntity.setItsUser(UserAssembler.updateEntity(dto.getUserInfo(), oldEntity.getItsUser()));
        oldEntity.setCreditPoints(dto.getCreditPoints());
        oldEntity.setCreditValidity(dto.getCreditValidity());
        oldEntity.setDefaultMaxAvailabilityHrsPerWeekDay(dto.getDefaultMaxAvailabilityHrsPerWeekDay());
        oldEntity.setDefaultMaxAvailabilityHrsPerWeekendDay(dto.getDefaultMaxAvailabilityHrsPerWeekendDay());
        oldEntity.setDefaultNormalAvailabilityHrsPerWeekDay(dto.getDefaultNormalAvailabilityHrsPerWeekDay());
        oldEntity.setDefaultNormalAvailabilityHrsPerWeekendDay(dto.getDefaultNormalAvailabilityHrsPerWeekendDay());
        oldEntity.setEnglishAllowed(dto.isEnglishAllowed());
        oldEntity.setEnglishLanguageCompetence(dto.getEnglishLanguageCompetence());
        oldEntity.setEnglishSalaryRate(dto.getEnglishSalaryRate());
        oldEntity.setHungarianLanguageCompetence(dto.getHungarianLanguageCompetence());
        oldEntity.setHungarianSalaryRate(dto.getHungarianSalaryRate());
        oldEntity.setLastCheck(dto.getLastCheck());
        oldEntity.setInProbation(dto.isInProbation());
        oldEntity.setRadInvoiceClosingDay(dto.getRadInvoiceClosingDay());
        oldEntity.setRadPaymentDay(dto.getRadInvoiceClosingDay());
        oldEntity.setReportSendingCode(dto.getReportSendingCode());
        oldEntity.setWorkDemandPriority(dto.getWorkDemandPriority());
        oldEntity.setCanReport(dto.isCanReport());
        return oldEntity;
    }

    public static TDSRadiologistDTO toDTO(ITSRadiologist entity) {
        TDSRadiologistDTO dto = new TDSRadiologistDTO(entity.getId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setUserInfo(UserAssembler.toDTO(entity.getItsUser()));
        dto.setCreditPoints(entity.getCreditPoints());
        dto.setCreditValidity(entity.getCreditValidity());
        dto.setDefaultMaxAvailabilityHrsPerWeekDay(entity.getDefaultMaxAvailabilityHrsPerWeekDay());
        dto.setDefaultMaxAvailabilityHrsPerWeekendDay(entity.getDefaultMaxAvailabilityHrsPerWeekendDay());
        dto.setDefaultNormalAvailabilityHrsPerWeekDay(entity.getDefaultNormalAvailabilityHrsPerWeekDay());
        dto.setDefaultNormalAvailabilityHrsPerWeekendDay(entity.getDefaultNormalAvailabilityHrsPerWeekendDay());
        dto.setEnglishAllowed(entity.getEnglishAllowed());
        dto.setEnglishLanguageCompetence(entity.getEnglishLanguageCompetence());
        dto.setEnglishSalaryRate(entity.getEnglishSalaryRate());
        dto.setHungarianLanguageCompetence(entity.getHungarianLanguageCompetence());
        dto.setHungarianSalaryRate(entity.getHungarianSalaryRate());
        dto.setLastCheck(entity.getLastCheck());
        dto.setInProbation(entity.getInProbation());
        dto.setRadInvoiceClosingDay(entity.getRadInvoiceClosingDay());
        dto.setRadPaymentDay(entity.getRadInvoiceClosingDay());
        dto.setReportSendingCode(entity.getReportSendingCode());
        dto.setWorkDemandPriority(entity.getWorkDemandPriority());
        dto.setCanReport(entity.isCanReport());

        for (RadCompetence radCompetence : entity.getRadCompetenceCollection()) {
            dto.getRadCompetenceList().add(RadiologistAssembler.toDTO(radCompetence));
        }

        for (RegLicQualOwnership regLicQualOwnership : entity.getRegLicQualOwnershipCollection()) {
            dto.getRegLicQualOwnershipList().add(RadiologistAssembler.toDTO(regLicQualOwnership));
        }

        for (CompanyAssignment companyAssignment : entity.getCompanyAssignmentCollection()) {
            dto.getCompanyAssignmentList().add(RadiologistAssembler.toDTO(companyAssignment));
        }

        for (SuperVision superVision : entity.getSuperVisionCollection()) {
            dto.getSupervisedRadList().add(RadiologistAssembler.toDTO(superVision));
        }

        for (WorkPlace workPlace : entity.getWorkPlaceCollection()) {
            dto.getWorkPlaceList().add(toDTO(workPlace));
        }

        for (RadAvailability radAvailability : entity.getRadAvailabilityCollection()) {
            dto.getAvailabilityList().add(toDTO(radAvailability));
        }

        return dto;
    }

    public static TDSRadiologistDTO toDraftDTO(ITSRadiologist entity) {
        TDSRadiologistDTO dto = new TDSRadiologistDTO(entity.getId());
        dto.setUserInfo(UserAssembler.toDTO(entity.getItsUser()));
        return dto;
    }

    public static RadCompetenceDTO toDTO(RadCompetence entity) {
        RadCompetenceDTO dto = new RadCompetenceDTO(entity.getId());
        dto.setCompetenceLevel(entity.getCompetenceLevel());
        dto.setAddingDateTime(entity.getAddingDateTime());
        dto.setBodyRegion(MasterDataAssembler.toDTO(entity.getBodyRegion()));
        dto.setModality(MasterDataAssembler.toDTO(entity.getModality()));
        dto.setValid(entity.getValid());
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        return dto;
    }

    public static RadCompetence toEntity(RadCompetenceDTO dto) {
        RadCompetence entity = new RadCompetence();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAddingDateTime(dto.getAddingDateTime());
        entity.setValid(dto.isValid());
        entity.setCompetenceLevel(dto.getCompetenceLevel());
        entity.setModality(MasterDataAssembler.toEntity(dto.getModality()));
        entity.setBodyRegion(MasterDataAssembler.toEntity(dto.getBodyRegion()));
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        return entity;
    }

    public static RegLicQualOwnership toEntity(RegLicQualOwnershipDTO dto) {
        RegLicQualOwnership entity = new RegLicQualOwnership();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setValidBegin(dto.getValidBegin());
        entity.setValidEnd(dto.getValidEnd());
        entity.setCertificateNumber(dto.getCertificateNumber());
        entity.setActive(dto.isActive());
        entity.setRegLicQual(MasterDataAssembler.toEntity(dto.getRegLicQual()));
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        return entity;
    }

    public static RegLicQualOwnershipDTO toDTO(RegLicQualOwnership entity) {
        RegLicQualOwnershipDTO dto = new RegLicQualOwnershipDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setCertificateNumber(entity.getCertificateNumber());
        dto.setValidBegin(entity.getValidBegin());
        dto.setValidEnd(entity.getValidEnd());
        dto.setRegLicQual(MasterDataAssembler.toDTO(entity.getRegLicQual()));
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        return dto;
    }

    public static RadAvailabilityDTO toDTO(RadAvailability entity) {
        RadAvailabilityDTO dto = new RadAvailabilityDTO(entity.getId());
        dto.setAdded(entity.getAdded());
        dto.setConfirmedByPM(entity.getConfirmedByPM());
        dto.setConfirmedByRad(entity.getConfirmedByRad());
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        dto.setValid(entity.getValid());
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        dto.setNormalAvailabilityPerWeek(HospitalAssembler.toDTO(entity.getAvailabilityPerWeek1()));
        dto.setMaxAvailabilityPerWeek(HospitalAssembler.toDTO(entity.getAvailabilityPerWeek()));
        return dto;
    }

    public static RadAvailability toEntity(RadAvailabilityDTO dto) {
        RadAvailability entity = new RadAvailability();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAdded(dto.getAdded());
        entity.setConfirmedByPM(dto.isConfirmedByPM());
        entity.setConfirmedByRad(dto.isConfirmedByRad());
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        entity.setValid(dto.isValid());
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        entity.setAvailabilityPerWeek(HospitalAssembler.toEntity(dto.getMaxAvailabilityPerWeek()));
        entity.setAvailabilityPerWeek1(HospitalAssembler.toEntity(dto.getNormalAvailabilityPerWeek()));
        return entity;
    }

    public static CompanyDTO toDTO(Company entity) {
        CompanyDTO dto = new CompanyDTO(entity.getId());
        dto.setAccountNumber(entity.getAccountNumber());
        dto.setAddress(entity.getAddress());
        dto.setCountry(MasterDataAssembler.toDTO(entity.getCountry()));
        dto.setName(entity.getName());
        dto.setTaxAuthorityNumber(entity.getTaxAuthorityNumber());
        return dto;
    }

    public static Company toEntity(CompanyDTO dto) {
        Company entity = new Company();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setAddress(dto.getAddress());
        entity.setCountry(new Country(dto.getCountry().getId()));
        entity.setName(dto.getName());
        entity.setTaxAuthorityNumber(dto.getTaxAuthorityNumber());
        return entity;
    }

    public static SuperVisionDTO toDTO(SuperVision entity) {
        SuperVisionDTO dto = new SuperVisionDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setAdded(entity.getAdded());
        dto.setStarted(entity.getStarted());
        dto.setEnded(entity.getEnded());
        dto.setSupervisor(new TDSRadiologistDTO(entity.getSupervisor().getId()));
        dto.setSupervised(new TDSRadiologistDTO(entity.getSupervised().getId()));
        dto.getSupervised().setUserInfo(UserAssembler.toDTO(entity.getSupervised().getItsUser()));
        return dto;
    }

    public static SuperVision toEntity(SuperVisionDTO dto) {
        SuperVision entity = new SuperVision();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setActive(dto.isActive());
        entity.setAdded(dto.getAdded());
        entity.setStarted(dto.getStarted());
        entity.setEnded(dto.getEnded());
        entity.setSupervisor(new ITSRadiologist(dto.getSupervisor().getId()));
        entity.setSupervised(new ITSRadiologist(dto.getSupervised().getId()));
        return entity;
    }

    public static CompanyAssignmentDTO toDTO(CompanyAssignment entity) {
        CompanyAssignmentDTO dto = new CompanyAssignmentDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setCompany(toDTO(entity.getCompany()));
        dto.setEnded(entity.getEnded());
        dto.setStarted(entity.getStarted());
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        return dto;
    }

    public static CompanyAssignment toEntity(CompanyAssignmentDTO dto) {
        CompanyAssignment entity = new CompanyAssignment();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());

        }
        entity.setActive(dto.isActive());
        entity.setCompany(toEntity(dto.getCompany()));
        entity.setEnded(dto.getEnded());
        entity.setStarted(dto.getStarted());
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        return entity;
    }

    public static WorkPlaceDTO toDTO(WorkPlace entity) {
        WorkPlaceDTO dto = new WorkPlaceDTO(entity.getId());
        dto.setAdded(entity.getAdded());
        dto.setFromDate(entity.getFromDate());
        dto.setToDate(entity.getToDate());
        dto.setHospital(HospitalAssembler.toDTO(entity.getHospital()));
        dto.setTdsRadiologist(new TDSRadiologistDTO(entity.getTDSRadiologist().getId()));
        return dto;
    }

    public static WorkPlace toEntity(WorkPlaceDTO dto) {
        WorkPlace entity = new WorkPlace();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAdded(dto.getAdded());
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        entity.setHospital(new Hospital(dto.getHospital().getId()));
        entity.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
        return entity;
    }


}
