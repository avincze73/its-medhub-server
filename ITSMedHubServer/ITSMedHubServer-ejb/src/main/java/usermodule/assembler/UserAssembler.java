/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.assembler;

import usermodule.entity.HospitalStaff;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.entity.Hospital;
import java.util.ArrayList;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.dto.LanguageDTO;
import masterdatamodule.entity.ITSLanguage;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.entity.ITSRadiologist;
import usermodule.dto.HospitalStaffDTO;
import usermodule.dto.RoleDTO;
import usermodule.dto.SessionDTO;
import usermodule.dto.SessionLogEntryDTO;
import usermodule.dto.TDSManagerDTO;
import usermodule.dto.TDSManagerRoleAssignmentDTO;
import usermodule.dto.TDSManagerRoleDTO;
import usermodule.dto.TDSRadiologistRoleDTO;
import usermodule.dto.UserDTO;
import usermodule.entity.HospitalStaffRole;
import usermodule.entity.ITSManager;
import usermodule.entity.ITSManagerRole;
import usermodule.entity.ITSManagerRoleAssignment;
import usermodule.entity.ITSRadiologistRole;
import usermodule.entity.ITSRole;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSSessionLogEntry;
import usermodule.entity.ITSUser;
/**
 *
 * @author vincze.attila
 */
public class UserAssembler {

    public static ITSUser updateEntity(UserDTO dto, ITSUser oldEntity) {
        oldEntity.setActive(dto.isActive());
        oldEntity.setAddingDateTime(dto.getAddingDateTime());
        oldEntity.setGetsSystemEmails(dto.isGetsSystemEmails());
        oldEntity.setLoginName(dto.getLoginName());
        oldEntity.setMsn(dto.getMsn());
        oldEntity.setName(dto.getName());
        oldEntity.setPassword(dto.getPassword());
        oldEntity.setPlaceOfFax(dto.getPlaceOfFax());
        oldEntity.setSex(dto.getSex());
        oldEntity.setSkype(dto.getSkype());
        oldEntity.setItsEmail(dto.getTdsEmail());
        oldEntity.setWorkEmail(dto.getWorkEmail());
        oldEntity.setWorkFax(dto.getWorkFax());
        oldEntity.setWorkTel(dto.getWorkTel());
        oldEntity.setUserType(dto.getUserType());
        return oldEntity;
    }

    public static ITSUser toEntity(UserDTO dto) {
        ITSUser entity = new ITSUser();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setActive(dto.isActive());
        entity.setAddingDateTime(dto.getAddingDateTime());
        entity.setGetsSystemEmails(dto.isGetsSystemEmails());
        entity.setLoginName(dto.getLoginName());
        entity.setMsn(dto.getMsn());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setPlaceOfFax(dto.getPlaceOfFax());
        entity.setSex(dto.getSex());
        entity.setSkype(dto.getSkype());
        entity.setItsEmail(dto.getTdsEmail());
        entity.setWorkEmail(dto.getWorkEmail());
        entity.setWorkFax(dto.getWorkFax());
        entity.setWorkTel(dto.getWorkTel());
        entity.setUserType(dto.getUserType());
        entity.setTimeZoneGmt(dto.getTimeZoneGmt());
        return entity;
    }

    public static UserDTO toDTO(ITSUser entity) {
        UserDTO dto = new UserDTO(entity.getId());
        dto.setActive(entity.getActive());
        dto.setAddingDateTime(entity.getAddingDateTime());
        dto.setGetsSystemEmails(entity.getGetsSystemEmails());
        dto.setLoginName(entity.getLoginName());
        dto.setMsn(entity.getMsn());
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setPlaceOfFax(entity.getPlaceOfFax());
        dto.setSex(entity.getSex());
        dto.setSkype(entity.getSkype());
        dto.setTdsEmail(entity.getItsEmail());
        dto.setWorkEmail(entity.getWorkEmail());
        dto.setWorkFax(entity.getWorkFax());
        dto.setWorkTel(entity.getWorkTel());
        dto.setUserType(entity.getUserType());
        dto.setTimeZoneGmt(entity.getTimeZoneGmt());
        return dto;
    }

    public static HospitalStaffDTO toDTO(HospitalStaff entity) {
        HospitalStaffDTO dto = new HospitalStaffDTO(entity.getId());
        System.out.println(entity.getHospital());
        dto.setHospital(new HospitalDTO(entity.getHospital().getId()));
        dto.getHospital().setAbbrevName(entity.getHospital().getAbbrevName());
        dto.setPositionInHosp(entity.getPositionInHosp());
        dto.setRoleWithTDS(entity.getRoleWithITS());
        dto.setUserInfo(toDTO(entity.getItsUser()));
        for (ITSLanguage language : entity.getLanguageList()) {
            dto.getLanguageList().add(MasterDataAssembler.toDTO(language));
        }
        return dto;
    }

    public static HospitalStaff updateEntity(HospitalStaffDTO dto, HospitalStaff oldEntity) {
        oldEntity.setHospital(new Hospital(dto.getHospital().getId()));
        oldEntity.setPositionInHosp(dto.getPositionInHosp());
        oldEntity.setRoleWithITS(dto.getRoleWithTDS());
        oldEntity.setItsUser(updateEntity(dto.getUserInfo(), oldEntity.getItsUser()));
        if (oldEntity.getLanguageList() == null) {
            //oldEntity.setLanguageList(new HashSet<ITSLanguage>());
        }
        oldEntity.getLanguageList().clear();
        for (LanguageDTO languageDTO : dto.getLanguageList()) {
            oldEntity.getLanguageList().add(MasterDataAssembler.toEntity(languageDTO));
        }
        return oldEntity;
    }

    public static TDSManagerDTO toDTO(ITSManager entity) {
        TDSManagerDTO dto = new TDSManagerDTO(entity.getId());
        dto.setUserInfo(toDTO(entity.getItsUser()));
        for (ITSManagerRoleAssignment tDSManagerRoleAssignment : entity.getItsManagerRoleAssignmentList()) {
            dto.getRoleAssignmentList().add(toDTO(tDSManagerRoleAssignment));
        }
        return dto;
    }

    public static TDSRadiologistDTO toDTO(ITSRadiologist entity) {
        TDSRadiologistDTO dto = new TDSRadiologistDTO(entity.getId());
        dto.setUserInfo(toDTO(entity.getItsUser()));
        dto.setAccountNumber(entity.getAccountNumber());
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
        return dto;
    }

    public static ITSManager toEntity(TDSManagerDTO dto) {
        ITSManager entity = new ITSManager();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setItsUser(toEntity(dto.getUserInfo()));
        return entity;
    }

    public static ITSRadiologist toEntity(TDSRadiologistDTO dto) {
        ITSRadiologist entity = new ITSRadiologist();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setItsUser(toEntity(dto.getUserInfo()));
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setCreditPoints(dto.getCreditPoints());
        entity.setCreditValidity(dto.getCreditValidity());
        entity.setDefaultMaxAvailabilityHrsPerWeekDay(dto.getDefaultMaxAvailabilityHrsPerWeekDay());
        entity.setDefaultMaxAvailabilityHrsPerWeekendDay(dto.getDefaultMaxAvailabilityHrsPerWeekendDay());
        entity.setDefaultNormalAvailabilityHrsPerWeekDay(dto.getDefaultNormalAvailabilityHrsPerWeekDay());
        entity.setDefaultNormalAvailabilityHrsPerWeekendDay(dto.getDefaultNormalAvailabilityHrsPerWeekendDay());
        entity.setEnglishAllowed(dto.isEnglishAllowed());
        entity.setEnglishLanguageCompetence(dto.getEnglishLanguageCompetence());
        entity.setEnglishSalaryRate(dto.getEnglishSalaryRate());
        entity.setHungarianLanguageCompetence(dto.getHungarianLanguageCompetence());
        entity.setHungarianSalaryRate(dto.getHungarianSalaryRate());
        entity.setLastCheck(dto.getLastCheck());
        entity.setInProbation(dto.isInProbation());
        entity.setRadInvoiceClosingDay(dto.getRadInvoiceClosingDay());
        entity.setRadPaymentDay(dto.getRadInvoiceClosingDay());
        entity.setReportSendingCode(dto.getReportSendingCode());
        entity.setWorkDemandPriority(dto.getWorkDemandPriority());
        entity.setCanReport(dto.isCanReport());
        return entity;
    }

    public static HospitalStaff toEntity(HospitalStaffDTO dto) {
        HospitalStaff entity = new HospitalStaff();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setItsUser(toEntity(dto.getUserInfo()));
        entity.setHospital(new Hospital(dto.getHospital().getId()));
        entity.setPositionInHosp(dto.getPositionInHosp());
        entity.setRoleWithITS(dto.getRoleWithTDS());
        entity.setLanguageList(new ArrayList<ITSLanguage>());
        for (LanguageDTO languageDTO : dto.getLanguageList()) {
            entity.getLanguageList().add(MasterDataAssembler.toEntity(languageDTO));
        }
        return entity;
    }

    public static RoleDTO toDTO(ITSRole entity) {
        RoleDTO dto = new RoleDTO(entity.getId());
        dto.setAbbreviation(entity.getAbbreviation());
        dto.setDescription(entity.getDescription());
        dto.setEnglishPublicAlias(entity.getEnglishPublicAlias());
        dto.setHungarianPublicAlias(entity.getHungarianPublicAlias());
        dto.setName(entity.getName());
        dto.setRoleBoundaries(entity.getRoleBoundaries());
        dto.setCanHave(entity.getCanHave());
//        for (ITSRole role : entity.getTDSRoleCollection()) {
//            dto.getPrerequisites().add(new RoleDTO(role.getId()));
//        }
//        for (ITSRole role : entity.getTDSRoleCollection2()) {
//            dto.getWhoCanAssignThisRoleToUser().add(new RoleDTO(role.getId()));
//        }
        return dto;
    }

    public static ITSRole toEntity(RoleDTO dto) {
        ITSRole entity = null;
        if ("tdsManager".equals(dto.getCanHave())) {
            entity = new ITSManagerRole();
        } else if ("tdsRadiologist".equals(dto.getCanHave())) {
            entity = new ITSRadiologistRole();
        } else {
            entity = new HospitalStaffRole();
        }
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAbbreviation(dto.getAbbreviation());
        entity.setDescription(dto.getDescription());
        entity.setEnglishPublicAlias(dto.getEnglishPublicAlias());
        entity.setHungarianPublicAlias(dto.getHungarianPublicAlias());
        entity.setName(dto.getName());
        entity.setRoleBoundaries(dto.getRoleBoundaries());
        //entity.setCanHave(dto.getCanHave());
//        entity.setItsRoleCollection(new ArrayList<ITSRole>());
//        entity.setTDSRoleCollection2(new ArrayList<ITSRole>());
//        for (RoleDTO role : dto.getWhoCanAssignThisRoleToUser()) {
//            entity.getTDSRoleCollection2().add(new ITSRole(role.getId()));
//        }
//        for (RoleDTO role : dto.getPrerequisites()) {
//            entity.getTDSRoleCollection().add(new ITSRole(role.getId()));
//        }
        return entity;
    }

    public static TDSRadiologistRoleDTO toDTO(ITSRadiologistRole entity) {
        TDSRadiologistRoleDTO dto = new TDSRadiologistRoleDTO(entity.getId());
        dto.setAbbreviation(entity.getAbbreviation());
        dto.setDescription(entity.getDescription());
        dto.setEnglishPublicAlias(entity.getEnglishPublicAlias());
        dto.setHungarianPublicAlias(entity.getHungarianPublicAlias());
        dto.setName(entity.getName());
        dto.setRoleBoundaries(entity.getRoleBoundaries());
        return dto;
    }

    public static TDSManagerRoleDTO toDTO(ITSManagerRole entity) {
        TDSManagerRoleDTO dto = new TDSManagerRoleDTO(entity.getId());
        dto.setAbbreviation(entity.getAbbreviation());
        dto.setDescription(entity.getDescription());
        dto.setEnglishPublicAlias(entity.getEnglishPublicAlias());
        dto.setHungarianPublicAlias(entity.getHungarianPublicAlias());
        dto.setName(entity.getName());
        dto.setRoleBoundaries(entity.getRoleBoundaries());
        return dto;
    }

    public static TDSManagerRoleAssignmentDTO toDTO(ITSManagerRoleAssignment entity) {
        TDSManagerRoleAssignmentDTO dto = new TDSManagerRoleAssignmentDTO();
        dto.setId(entity.getId());
        dto.setActive(entity.isActive());
        dto.setAdded(entity.getAdded());
        dto.setAssignmentStart(entity.getAssignmentStart());
        dto.setEnded(entity.getEnded());
        dto.setPlannedEnd(entity.getPlannedEnd());
        dto.setTdsManager(new TDSManagerDTO(entity.getItsManager().getId()));
        dto.setTdsManagerRole(new TDSManagerRoleDTO(entity.getItsManagerRole().getId()));
        dto.getTdsManagerRole().setName(entity.getItsManagerRole().getName());
        return dto;
    }

    public static ITSManagerRoleAssignment toEntity(TDSManagerRoleAssignmentDTO dto) {
        ITSManagerRoleAssignment entity = new ITSManagerRoleAssignment();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setActive(dto.isActive());
        entity.setAdded(dto.getAdded());
        entity.setAssignmentStart(dto.getAssignmentStart());
        entity.setEnded(dto.getEnded());
        entity.setPlannedEnd(dto.getPlannedEnd());
        entity.setItsManager(new ITSManager(dto.getTdsManager().getId()));
        entity.setItsManagerRole(new ITSManagerRole(dto.getTdsManagerRole().getId()));
        return entity;
    }

    public static SessionLogEntryDTO toDTO(ITSSessionLogEntry entity) {
        SessionLogEntryDTO dto = new SessionLogEntryDTO(entity.getId());
        dto.setActionType(entity.getActionType());
        dto.setColumnName(entity.getColumnName());
        dto.setFromValue(entity.getFromValue());
        dto.setLogged(entity.getLogged());
        dto.setNote(entity.getNote());
        dto.setRecordId(entity.getRecordId());
        dto.setSession(new SessionDTO(entity.getItsSession().getId()));
        dto.setTableName(entity.getTableName());
        dto.setToValue(entity.getToValue());
        return dto;
    }

    public static SessionDTO toDTO(ITSSession entity) {
        SessionDTO dto = new SessionDTO(entity.getId());
        dto.setClientHostIpAddress(entity.getClientHostIpAddress());
        dto.setClientHostMacAddress(entity.getClientHostMacAddress());
        dto.setClientHostName(entity.getClientHostName());
        dto.setEnded(entity.getEnded());
        dto.setEndingType(entity.getEndingType());
        dto.setStarted(entity.getStarted());
        dto.setTdsApplication(entity.getItsApplication());
        dto.setTdsUser(new UserDTO(entity.getItsUser().getId()));
        dto.getTdsUser().setLoginName(entity.getItsUser().getLoginName());
        dto.getTdsUser().setName(entity.getItsUser().getName());

        for (ITSSessionLogEntry tDSSessionLogEntry : entity.getSessionLogEntryList()) {
            dto.getEntryList().add(toDTO(tDSSessionLogEntry));
        }
        return dto;
    }
}
