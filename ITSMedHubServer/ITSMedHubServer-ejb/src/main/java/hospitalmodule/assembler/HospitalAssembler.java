/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.assembler;

import usermodule.entity.HospitalStaff;
import hospitalmodule.dto.AvailabilityPerPeriodDTO;
import hospitalmodule.dto.AvailabilityPerWeekDTO;
import hospitalmodule.dto.BandInfoDTO;
import hospitalmodule.dto.BodyRegionWithBandDTO;
import hospitalmodule.dto.ContactPersonAssignmentDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.dto.OptionAssignmentDTO;
import hospitalmodule.dto.WorkBandTableDTO;
import hospitalmodule.entity.ContactPerson;
import hospitalmodule.entity.ContactPersonAssignment;
import hospitalmodule.entity.GoverningAuthority;
import hospitalmodule.entity.Hospital;
import hospitalmodule.entity.HospitalContract;
import hospitalmodule.entity.OptionAssignment;
import java.util.ArrayList;
import masterdatamodule.assembler.MasterDataAssembler;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.GoverningAuthorityDTO;
import radiologistmodule.entity.AvailabilityPerPeriod;
import radiologistmodule.entity.AvailabilityPerWeek;
import radiologistmodule.entity.BandInfo;
import radiologistmodule.entity.BodyRegionWithBand;
import radiologistmodule.entity.WorkBandTable;
import usermodule.assembler.UserAssembler;

/**
 *
 * @author vincze.attila
 */
public class HospitalAssembler {

    public static byte[] toByte(double data) {
        return toByte(Double.doubleToRawLongBits(data));
    }

    public static byte[] toByteArray(double[] data) {
        if (data == null) {
            return null;
        }
        byte[] byts = new byte[data.length * 8];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(toByte(data[i]), 0, byts, i * 8, 8);
        }
        return byts;
    }

    public static long toLong(byte[] data) {
        if (data == null || data.length != 8) {
            return 0x0;
        }
        return (long) ((long) (0xff & data[0]) << 56
                | (long) (0xff & data[1]) << 48
                | (long) (0xff & data[2]) << 40
                | (long) (0xff & data[3]) << 32
                | (long) (0xff & data[4]) << 24
                | (long) (0xff & data[5]) << 16
                | (long) (0xff & data[6]) << 8
                | (long) (0xff & data[7]) << 0);
    }

    public static double toDouble(byte[] data) {
        if (data == null || data.length != 8) {
            return 0x0;
        }
        return Double.longBitsToDouble(toLong(data));
    }

    public static double[] toDoubleArray(byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length % 8 != 0) {
            return null;
        }
        double[] dbls = new double[data.length / 8];
        for (int i = 0; i < dbls.length; i++) {
            dbls[i] = toDouble(new byte[]{
                        data[(i * 8)],
                        data[(i * 8) + 1],
                        data[(i * 8) + 2],
                        data[(i * 8) + 3],
                        data[(i * 8) + 4],
                        data[(i * 8) + 5],
                        data[(i * 8) + 6],
                        data[(i * 8) + 7],});
        }
        return dbls;
    }

    public static AvailabilityPerWeekDTO toDTO(AvailabilityPerWeek entity) {
        return new AvailabilityPerWeekDTO(
                entity.getId(), entity.getSunday(), entity.getMonday(),
                entity.getTuesday(), entity.getWednesday(),
                entity.getThursday(), entity.getFriday(),
                entity.getSaturday());
    }

    public static AvailabilityPerWeek toEntity(AvailabilityPerWeekDTO dto) {
        AvailabilityPerWeek entity = new AvailabilityPerWeek();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setMonday(dto.getMonday());
        entity.setTuesday(dto.getTuesday());
        entity.setWednesday(dto.getWednesday());
        entity.setThursday(dto.getThursday());
        entity.setFriday(dto.getFriday());
        entity.setSaturday(dto.getSaturday());
        entity.setSunday(dto.getSunday());
        return entity;
    }

    public static AvailabilityPerPeriodDTO toDTO(AvailabilityPerPeriod entity) {
        AvailabilityPerPeriodDTO dto =
                new AvailabilityPerPeriodDTO(
                entity.getId(), entity.getSunday(), entity.getMonday(),
                entity.getTuesday(), entity.getWednesday(),
                entity.getThursday(), entity.getFriday(),
                entity.getSaturday());

        dto.setStarted(entity.getStarted());
        dto.setEnded(entity.getEnded());
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));
        return dto;
    }

    public static AvailabilityPerPeriod toEntity(AvailabilityPerPeriodDTO dto) {
        AvailabilityPerPeriod entity = new AvailabilityPerPeriod(dto.getSunday(), dto.getMonday(),
                dto.getTuesday(), dto.getWednesday(),
                dto.getThursday(), dto.getFriday(),
                dto.getSaturday(), dto.getStarted(), dto.getEnded());
        entity.setHospitalContract(new HospitalContract(dto.getHospitalContract().getId()));
        return entity;
    }

    public static Hospital updateEntity(HospitalDTO dto, Hospital oldEntity) {
        oldEntity.setAbbrevName(dto.getAbbrevName());
        oldEntity.setActive(dto.isActive());
        oldEntity.setAddingDate(dto.getAddingDate());
        oldEntity.setAddress(dto.getAddress());
        oldEntity.setGovBody(dto.getGovBody());
        oldEntity.setGoverningAuthority(new GoverningAuthority(dto.getGovAuthority().getId(), dto.getGovAuthority().getName()));
        oldEntity.setOfficialName(dto.getOfficialName());
        oldEntity.setQualityRequirements(dto.getQualityRequirements());
        oldEntity.setShortName(dto.getShortName());
        oldEntity.setRegion(MasterDataAssembler.toEntity(dto.getRegion()));
        return oldEntity;
    }

    public static Hospital toEntity(HospitalDTO dto) {
        Hospital entity = new Hospital();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setAbbrevName(dto.getAbbrevName());
        entity.setActive(dto.isActive());
        entity.setAddingDate(dto.getAddingDate());
        entity.setAddress(dto.getAddress());
        entity.setGovBody(dto.getGovBody());
        entity.setGoverningAuthority(new GoverningAuthority(dto.getGovAuthority().getId(), dto.getGovAuthority().getName()));
        entity.setOfficialName(dto.getOfficialName());
        entity.setQualityRequirements(dto.getQualityRequirements());
        entity.setShortName(dto.getShortName());
        entity.setRegion(MasterDataAssembler.toEntity(dto.getRegion()));
        entity.setTimeZoneGmt(dto.getTimeZoneGmt());
        return entity;
    }

    public static HospitalDTO toDTO(Hospital entity) {
        HospitalDTO dto = new HospitalDTO(entity.getId());
        dto.setAbbrevName(entity.getAbbrevName());
        dto.setActive(entity.getActive());
        dto.setAddingDate(entity.getAddingDate());
        dto.setAddress(entity.getAddress());
        dto.setGovAuthority(new GoverningAuthorityDTO(entity.getGoverningAuthority().getId(),
                entity.getGoverningAuthority().getName()));
        dto.setGovBody(entity.getGovBody());
        dto.setOfficialName(entity.getOfficialName());
        dto.setQualityRequirements(entity.getQualityRequirements());
        dto.setShortName(entity.getShortName());
        dto.setRegion(MasterDataAssembler.toDTO(entity.getRegion()));
        dto.setTimeZoneGmt(entity.getTimeZoneGmt());

        for (HospitalContract hospitalContract : entity.getHospitalContractCollection()) {
            dto.getHospitalContractList().add(toDTO(hospitalContract));
            if (hospitalContract.getActive()) {
                dto.setActiveContractStarted(hospitalContract.getStartDateTime());
                dto.setActiveContractEnded(hospitalContract.getStopDateTime());
            }
        }

        for (HospitalStaff hospitalStaff : entity.getHospitalStaffCollection()) {
            dto.getHospitalStaffList().add(UserAssembler.toDTO(hospitalStaff));
        }

        return dto;
    }

    public static HospitalDTO toMinimalDTO(Hospital entity) {
        HospitalDTO dto = new HospitalDTO(entity.getId());
        dto.setAbbrevName(entity.getAbbrevName());
        return dto;
    }

    public static HospitalContract updateEntity(HospitalContractDTO dto, HospitalContract oldEntity) {
        oldEntity.setActive(dto.isActive());
        oldEntity.setAdminFeeForCheckingUselessCase(dto.getAdminFeeForCheckingUselessCase());
        oldEntity.setAdminFeeForManuallyEnteringData(dto.getAdminFeeForManuallyEnteringData());
        oldEntity.setClosenessToDeadlineDaysWithConsHosp(dto.getClosenessToDeadlineDaysWithConsHosp());
        oldEntity.setContractCode(dto.getContractCode());
        oldEntity.setContractType(MasterDataAssembler.toEntity(dto.getContractType()));
        oldEntity.setCredit(dto.getCredit());
        oldEntity.setCurrency(MasterDataAssembler.toEntity(dto.getCurrency()));
        oldEntity.setIntendedEndDateTime(dto.getIntendedEndDateTime());
        oldEntity.setInvoicePeriodDay(dto.getInvoicePeriodDay());
        oldEntity.setInvoiceProductionDay(dto.getInvoiceProductionDay());
        oldEntity.setLastCaseAcceptanceDateTime(dto.getLastCaseAcceptanceDateTime());
        oldEntity.setAvailabilityPerWeek(toEntity(dto.getMaxRequiredAvailabilityPerWeek()));
        oldEntity.setAvailabilityPerWeek1(toEntity(dto.getMinRequiredAvailabilityPerWeek()));
        oldEntity.setNormalClosenessToDeadlineDaysHosp(dto.getNormalClosenessToDeadlineDaysHosp());
        oldEntity.setNormalWorkTimeDays(dto.getNormalWorkTimeDays());
        oldEntity.setSignedDate(dto.getSignedDate());
        oldEntity.setSignersName(dto.getSignersName());
        oldEntity.setStartDateTime(dto.getStartDateTime());
        oldEntity.setStopDateTime(dto.getStopDateTime());
        oldEntity.setWorkTimeDaysWithConsultation(dto.getWorkTimeDaysWithConsultation());
        return oldEntity;
    }

    public static HospitalContract toEntity(HospitalContractDTO dto) {
        HospitalContract entity = new HospitalContract();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setHospital(new Hospital(dto.getHospital().getId()));
        entity.setActive(dto.isActive());
        entity.setAdminFeeForCheckingUselessCase(dto.getAdminFeeForCheckingUselessCase());
        entity.setAdminFeeForManuallyEnteringData(dto.getAdminFeeForManuallyEnteringData());
        entity.setClosenessToDeadlineDaysWithConsHosp(dto.getClosenessToDeadlineDaysWithConsHosp());
        entity.setContractCode(dto.getContractCode());
        entity.setContractType(MasterDataAssembler.toEntity(dto.getContractType()));
        entity.setCredit(dto.getCredit());
        entity.setCurrency(MasterDataAssembler.toEntity(dto.getCurrency()));
        entity.setIntendedEndDateTime(dto.getIntendedEndDateTime());
        entity.setInvoicePeriodDay(dto.getInvoicePeriodDay());
        entity.setInvoiceProductionDay(dto.getInvoiceProductionDay());
        entity.setLastCaseAcceptanceDateTime(dto.getLastCaseAcceptanceDateTime());
        entity.setAvailabilityPerWeek(toEntity(dto.getMaxRequiredAvailabilityPerWeek()));
        entity.setAvailabilityPerWeek1(toEntity(dto.getMinRequiredAvailabilityPerWeek()));
        entity.setNormalClosenessToDeadlineDaysHosp(dto.getNormalClosenessToDeadlineDaysHosp());
        entity.setNormalWorkTimeDays(dto.getNormalWorkTimeDays());
        entity.setSignedDate(dto.getSignedDate());
        entity.setSignersName(dto.getSignersName());
        entity.setStartDateTime(dto.getStartDateTime());
        entity.setStopDateTime(dto.getStopDateTime());
        entity.setWorkTimeDaysWithConsultation(dto.getWorkTimeDaysWithConsultation());
        return entity;
    }

    public static HospitalContractDTO toDTO(HospitalContract entity) {
        HospitalContractDTO dto = new HospitalContractDTO(entity.getId());
        dto.setHospital(new HospitalDTO(entity.getHospital().getId()));
        dto.getHospital().setAbbrevName(entity.getHospital().getAbbrevName());
        dto.setActive(entity.getActive());
        dto.setAdminFeeForCheckingUselessCase(entity.getAdminFeeForCheckingUselessCase());
        dto.setAdminFeeForManuallyEnteringData(entity.getAdminFeeForManuallyEnteringData());
        dto.setClosenessToDeadlineDaysWithConsHosp(entity.getClosenessToDeadlineDaysWithConsHosp());
        dto.setContractCode(entity.getContractCode());
        dto.setContractType(MasterDataAssembler.toDTO(entity.getContractType()));
        dto.setCredit(entity.getCredit());
        dto.setCurrency(new CurrencyDTO(entity.getCurrency().getId(), entity.getCurrency().getName()));
        dto.setIntendedEndDateTime(entity.getIntendedEndDateTime());
        dto.setInvoicePeriodDay(entity.getInvoicePeriodDay());
        dto.setInvoiceProductionDay(entity.getInvoiceProductionDay());
        dto.setLastCaseAcceptanceDateTime(entity.getLastCaseAcceptanceDateTime());
        dto.setMaxRequiredAvailabilityPerWeek(toDTO(entity.getAvailabilityPerWeek()));
        dto.setMinRequiredAvailabilityPerWeek(toDTO(entity.getAvailabilityPerWeek1()));
        dto.setNormalClosenessToDeadlineDaysHosp(entity.getNormalClosenessToDeadlineDaysHosp());
        dto.setNormalWorkTimeDays(entity.getNormalWorkTimeDays());
        dto.setSignedDate(entity.getSignedDate());
        dto.setSignersName(entity.getSignersName());
        dto.setStartDateTime(entity.getStartDateTime());
        dto.setStopDateTime(entity.getStopDateTime());
        dto.setWorkTimeDaysWithConsultation(entity.getWorkTimeDaysWithConsultation());

        for (WorkBandTable workBandTable : entity.getWorkBandTableCollection()) {
            dto.getWorkBandTableList().add(toDTO(workBandTable));
        }
        for (OptionAssignment optionAssignment : entity.getOptionAssignmentCollection()) {
            dto.getOptionList().add(toDTO(optionAssignment));
        }

        for (ContactPersonAssignment contactPersonAssignment : entity.getContactPersonAssignmentCollection()) {
            dto.getContactPersonAssignmentList().add(toDTO(contactPersonAssignment));
        }

        for (AvailabilityPerPeriod availabilityPerPeriod : entity.getAvailabilityPerPeriodCollection()) {
            dto.getAvailabilityPerPeriodList().add(toDTO(availabilityPerPeriod));
        }


        return dto;
    }

    public static OptionAssignmentDTO toDTO(OptionAssignment entity) {
        OptionAssignmentDTO dto = new OptionAssignmentDTO(entity.getId());
        dto.setValidStart(entity.getValidStart());
        dto.setInactivation(entity.getInactivation());
        dto.setOption(MasterDataAssembler.toDTO(entity.getHospContrOption()));
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));
        return dto;
    }

    public static OptionAssignment toEntity(OptionAssignmentDTO dto) {
        OptionAssignment entity = new OptionAssignment();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setValidStart(dto.getValidStart());
        entity.setInactivation(dto.getInactivation());
        entity.setHospContrOption(MasterDataAssembler.toEntity(dto.getOption()));
        entity.setHospitalContract(new HospitalContract(dto.getHospitalContract().getId()));
        return entity;
    }

    public static WorkBandTableDTO toDTO(WorkBandTable entity) {
        WorkBandTableDTO dto = new WorkBandTableDTO(entity.getId());
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));
        dto.setBandNumber(entity.getBandNumber());
        dto.setModality(MasterDataAssembler.toDTO(entity.getModality()));
        for (BandInfo bandInfo : entity.getBandInfoCollection()) {
            dto.getBandInfoList().add(toDTO(bandInfo));
        }
        for (BodyRegionWithBand bodyRegionWithBand : entity.getBodyRegionWithBandCollection()) {
            dto.getBodyRegionWithBandList().add(toDTO(bodyRegionWithBand));
        }
        return dto;
    }

    public static WorkBandTable toEntity(WorkBandTableDTO dto) {
        WorkBandTable entity = new WorkBandTable();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setHospitalContract(new HospitalContract(dto.getHospitalContract().getId()));
        entity.setModality(MasterDataAssembler.toEntity(dto.getModality()));
        entity.setBandNumber(dto.getBandNumber());
        entity.setBandInfoCollection(new ArrayList<BandInfo>());
        for (BandInfoDTO bandInfoDTO : dto.getBandInfoList()) {
            entity.getBandInfoCollection().add(toEntity(bandInfoDTO));
        }
        entity.setBodyRegionWithBandCollection(new ArrayList<BodyRegionWithBand>());
        for (BodyRegionWithBandDTO bodyRegionWithBandDTO : dto.getBodyRegionWithBandList()) {
            entity.getBodyRegionWithBandCollection().add(toEntity(bodyRegionWithBandDTO));
        }
        return entity;
    }

    public static WorkBandTable updateEntity(WorkBandTableDTO dto, WorkBandTable oldEntity) {
        oldEntity.setHospitalContract(new HospitalContract(dto.getHospitalContract().getId()));
        oldEntity.setModality(MasterDataAssembler.toEntity(dto.getModality()));
        oldEntity.setBandNumber(dto.getBandNumber());
        if (oldEntity.getBandInfoCollection() == null) {
            oldEntity.setBandInfoCollection(new ArrayList<BandInfo>());
        } else {
            oldEntity.getBandInfoCollection().clear();
        }
        for (BandInfoDTO bandInfoDTO : dto.getBandInfoList()) {
            oldEntity.getBandInfoCollection().add(toEntity(bandInfoDTO));
        }

        if (oldEntity.getBodyRegionWithBandCollection() == null) {
            oldEntity.setBodyRegionWithBandCollection(new ArrayList<BodyRegionWithBand>());
        } else {
            oldEntity.getBodyRegionWithBandCollection().clear();
        }
        for (BodyRegionWithBandDTO bodyRegionWithBandDTO : dto.getBodyRegionWithBandList()) {
            oldEntity.getBodyRegionWithBandCollection().add(toEntity(bodyRegionWithBandDTO));
        }
        return oldEntity;
    }

    public static ContactPerson toEntity(ContactPersonDTO dto) {
        ContactPerson entity = new ContactPerson();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setCorrespAddress(dto.getCorrespAddress());
        entity.setDepartment(dto.getDepartment());
        entity.setEmail(dto.getEmail());
        entity.setFax(dto.getFax());
        entity.setName(dto.getName());
        entity.setPlaceOfFax(dto.getPlaceOfFax());
        entity.setPositionInDept(dto.getPositionInDept());
        entity.setSkype(dto.getSkype());
        entity.setTel(dto.getTel());
        entity.setHospital(new Hospital(dto.getHospital().getId()));
        return entity;
    }

    public static ContactPersonDTO toDTO(ContactPerson entity) {
        ContactPersonDTO dto = new ContactPersonDTO(entity.getId());
        dto.setCorrespAddress(entity.getCorrespAddress());
        dto.setDepartment(entity.getDepartment());
        dto.setEmail(entity.getEmail());
        dto.setFax(entity.getFax());
        dto.setName(entity.getName());
        dto.setPlaceOfFax(entity.getPlaceOfFax());
        dto.setPositionInDept(entity.getPositionInDept());
        dto.setSkype(entity.getSkype());
        dto.setTel(entity.getTel());
        dto.setHospital(new HospitalDTO(entity.getHospital().getId()));
        return dto;
    }

    public static ContactPersonAssignment toEntity(ContactPersonAssignmentDTO dto) {
        ContactPersonAssignment entity = new ContactPersonAssignment();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setContactPerson(new ContactPerson(dto.getContactPerson().getId()));
        entity.setHospitalContract(new HospitalContract(dto.getHospitalContract().getId()));
        entity.setContactPosition(dto.getContactPosition());
        entity.setContactType(dto.getContactType());
        entity.setFinished(dto.getFinished());
        entity.setInactivation(dto.getInactivation());
        entity.setStarted(dto.getStarted());
        return entity;
    }

    public static ContactPersonAssignmentDTO toDTO(ContactPersonAssignment entity) {
        ContactPersonAssignmentDTO dto = new ContactPersonAssignmentDTO(entity.getId());
        dto.setContactPerson(toDTO(entity.getContactPerson()));
        dto.setHospitalContract(new HospitalContractDTO(entity.getHospitalContract().getId()));
        dto.setContactPosition(entity.getContactPosition());
        dto.setContactType(entity.getContactType());
        dto.setFinished(entity.getFinished());
        dto.setInactivation(entity.getInactivation());
        dto.setStarted(entity.getStarted());
        return dto;
    }

    public static BandInfoDTO toDTO(BandInfo entity) {
        BandInfoDTO dto = new BandInfoDTO(entity.getId());
        dto.setBandNumber(entity.getBandNumber());
        dto.setPriceForExtraWork(entity.getPriceForExtraWork());
        dto.setPriceForNormalWork(entity.getPriceForNormalWork());
        dto.setPriceForPayAsYouGoWork(entity.getPriceForPayAsYouGoWork());
        dto.setWorkTimeMinute(entity.getWorkTimeMinute());
        dto.setWorkBandTable(new WorkBandTableDTO(entity.getWorkBandTable().getId()));
        return dto;
    }

    public static BandInfo toEntity(BandInfoDTO dto) {
        BandInfo entity = new BandInfo();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setBandNumber(dto.getBandNumber());
        entity.setPriceForExtraWork(dto.getPriceForExtraWork());
        entity.setPriceForNormalWork(dto.getPriceForNormalWork());
        entity.setPriceForPayAsYouGoWork(dto.getPriceForPayAsYouGoWork());
        entity.setWorkTimeMinute(dto.getWorkTimeMinute());
        entity.setWorkBandTable(new WorkBandTable(dto.getWorkBandTable().getId()));
        return entity;
    }

    public static BodyRegionWithBandDTO toDTO(BodyRegionWithBand entity) {
        BodyRegionWithBandDTO dto = new BodyRegionWithBandDTO(entity.getId());
        dto.setBandAssignment(entity.getBandAssignment());
        dto.setWorkBandTable(new WorkBandTableDTO(entity.getWorkBandTable().getId()));
        dto.setBodyRegion(MasterDataAssembler.toDTO(entity.getBodyRegion()));
        return dto;
    }

    public static BodyRegionWithBand toEntity(BodyRegionWithBandDTO dto) {
        BodyRegionWithBand entity = new BodyRegionWithBand();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setBandAssignment(dto.getBandAssignment());
        entity.setWorkBandTable(new WorkBandTable(dto.getWorkBandTable().getId()));
        entity.setBodyRegion(MasterDataAssembler.toEntity(dto.getBodyRegion()));
        return entity;
    }
}
