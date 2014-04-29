/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.assembler;

import casemodule.entity.ReportTemplate;
import hospitalmodule.entity.ContractType;
import masterdatamodule.dto.BodyRegionDTO;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.dto.ContractTypeDTO;
import masterdatamodule.dto.CountryDTO;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.HospContrOptionDTO;
import masterdatamodule.dto.LanguageDTO;
import masterdatamodule.dto.ModalityDTO;
import masterdatamodule.dto.ModeOfAcquisitionDTO;
import masterdatamodule.dto.RegLicQualDTO;
import masterdatamodule.dto.RegLicQualTypeDTO;
import masterdatamodule.dto.RegionDTO;
import masterdatamodule.dto.ReportTemplateDTO;
import masterdatamodule.dto.ScenarioDTO;
import masterdatamodule.dto.TDSConstantDTO;
import masterdatamodule.dto.WayOfClosingDTO;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.CaseStatus;
import masterdatamodule.entity.Country;
import masterdatamodule.entity.Currency;
import masterdatamodule.entity.HospContrOption;
import masterdatamodule.entity.ITSConstant;
import masterdatamodule.entity.ITSLanguage;
import masterdatamodule.entity.Modality;
import masterdatamodule.entity.ModeOfAcquisition;
import masterdatamodule.entity.RegLicQual;
import masterdatamodule.entity.RegLicQualType;
import masterdatamodule.entity.Region;
import masterdatamodule.entity.Scenario;
import masterdatamodule.entity.WayOfClosing;

/**
 *
 * @author vincze.attila
 */
public class MasterDataAssembler {

    public static ReportTemplateDTO toDTO(ReportTemplate entity) {
        return new ReportTemplateDTO(entity.getId(), entity.getName(), entity.getDescription());
    }

    public static ReportTemplate toEntity(ReportTemplateDTO dto) {
        ReportTemplate entity = new ReportTemplate(dto.getId() == 0 ? null : dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setName(dto.getName());
        return entity;
    }

    public static RegLicQualDTO toDTO(RegLicQual entity) {
        RegLicQualDTO dto = new RegLicQualDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        if (entity.getRegion() != null) {
            dto.setRegion(toDTO(entity.getRegion()));
        }
        dto.setType(toDTO(entity.getRegLicQualType()));
        return dto;
    }

    public static RegLicQual toEntity(RegLicQualDTO dto) {
        RegLicQual entity = new RegLicQual(dto.getId() == 0 ? null : dto.getId(), dto.getName());
        entity.setRegLicQualType(toEntity(dto.getType()));
        entity.setRegion(toEntity(dto.getRegion()));
        return entity;
    }

    public static RegionDTO toDTO(Region entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCountry(toDTO(entity.getCountry()));
        return dto;
    }

    public static Region toEntity(RegionDTO dto) {
        Region entity = new Region(
                dto.getId() == 0 ? null : dto.getId(), dto.getName());
        entity.setCountry(toEntity(dto.getCountry()));
        return entity;
    }

    public static CountryDTO toDTO(Country entity) {
        CountryDTO dto = new CountryDTO();
        dto.setId(entity.getId());
        dto.setEnglishName(entity.getEnglishName());
        dto.setHungarianName(entity.getHungarianName());
        dto.setCurrency(toDTO(entity.getCurrency()));
        return dto;
    }

    public static Country toEntity(CountryDTO dto) {
        Country entity = new Country(
                dto.getId() == 0 ? null : dto.getId(), dto.getEnglishName(), dto.getHungarianName());
        entity.setCurrency(toEntity(dto.getCurrency()));
        return entity;
    }

    public static ContractTypeDTO toDTO(ContractType entity) {
        ContractTypeDTO dto = new ContractTypeDTO();
        dto.setId(entity.getId());
        dto.setEnglishName(entity.getEnglishName());
        dto.setHungarianName(entity.getHungarianName());
        return dto;
    }

    public static ContractType toEntity(ContractTypeDTO dto) {
        ContractType entity = new ContractType(dto.getId());
        return entity;
    }

    public static CurrencyDTO toDTO(Currency entity) {
        CurrencyDTO dto = new CurrencyDTO(entity.getId(), entity.getName());
        return dto;
    }

    public static Currency toEntity(CurrencyDTO dto) {
        Currency entity = new Currency(dto.getId() == 0 ? null : dto.getId(), dto.getName());
        return entity;
    }

    public static WayOfClosingDTO toDTO(WayOfClosing entity) {
        WayOfClosingDTO dto = new WayOfClosingDTO(entity.getId(), entity.getName());
        return dto;
    }

    public static WayOfClosing toEntity(WayOfClosingDTO dto) {
        WayOfClosing entity = new WayOfClosing(dto.getId() == 0 ? null : dto.getId(), dto.getName());
        return entity;
    }

    public static RegLicQualTypeDTO toDTO(RegLicQualType entity) {
        RegLicQualTypeDTO dto = new RegLicQualTypeDTO(entity.getId(), entity.getName());
        return dto;
    }

    public static RegLicQualType toEntity(RegLicQualTypeDTO dto) {
        RegLicQualType entity = new RegLicQualType(dto.getId() == 0 ? null : dto.getId(), dto.getName());
        return entity;
    }

    public static BodyRegion toEntity(BodyRegionDTO dto) {
        BodyRegion entity = new BodyRegion(dto.getId() == 0 ? null : dto.getId());
        entity.setEnglishName(dto.getEnglishName());
        entity.setHungarianName(dto.getHungarianName());
        entity.setDicomName(dto.getDicomName());
        entity.setSnomedCode(dto.getSnomedCode());
        entity.setGroupNumber(dto.getGroupNumber());
        return entity;
    }

    public static BodyRegionDTO toDTO(BodyRegion entity) {
        return new  BodyRegionDTO(
                entity.getId(), entity.getEnglishName(),
                entity.getHungarianName(), entity.getDicomName(),
                entity.getSnomedCode(), entity.getGroupNumber());
    }

    public static ModalityDTO toDTO(Modality entity) {
        return new ModalityDTO(entity.getId(), entity.getName());
    }

    public static Modality toEntity(ModalityDTO dto) {
        return new Modality(dto.getId() == 0 ? null : dto.getId(), dto.getName());
    }

    public static CaseStatusDTO toDTO(CaseStatus entity) {
        CaseStatusDTO dto = new CaseStatusDTO(entity.getId(), entity.getEnglishName(), entity.getHungarianName());
        dto.setHospitalSeesEng1(entity.getHospitalSeesEng1());
        dto.setHospitalSeesEng2(entity.getHospitalSeesEng2());
        dto.setTdsManagerSeesEng(entity.getItsManagerSeesEng());
        dto.setTdsRadiologistSeesEng(entity.getItsRadiologistSeesEng());
        return dto;
    }

    public static CaseStatus toEntity(CaseStatusDTO dto) {
        CaseStatus entity = new CaseStatus();
        if (dto.getId() != 0) {
            entity.setId(dto.getId());
        }
        entity.setEnglishName(dto.getEnglishName());
        entity.setHungarianName(dto.getHungarianName());
        entity.setHospitalSeesEng1(dto.getHospitalSeesEng1());
        entity.setHospitalSeesEng2(dto.getHospitalSeesEng2());
        entity.setItsManagerSeesEng(dto.getTdsManagerSeesEng());
        entity.setItsRadiologistSeesEng(dto.getTdsRadiologistSeesEng());
        return entity;
    }

    public static ModeOfAcquisitionDTO toDTO(ModeOfAcquisition entity) {
        return new ModeOfAcquisitionDTO(entity.getId(), entity.getName(), entity.getCode());
    }

    public static ModeOfAcquisition toEntity(ModeOfAcquisitionDTO dto) {
        return new ModeOfAcquisition(dto.getId() == 0 ? null : dto.getId(), dto.getName(), dto.getCode());
    }

    public static LanguageDTO toDTO(ITSLanguage entity) {
        return new LanguageDTO(entity.getId(), entity.getEnglishName(), entity.getHungarianName());
    }

    public static ITSLanguage toEntity(LanguageDTO dto) {
        return new ITSLanguage(dto.getId() == 0 ? null : dto.getId(), dto.getEnglishName(), dto.getHungarianName());
    }

    public static HospContrOptionDTO toDTO(HospContrOption entity) {
        HospContrOptionDTO dto = new HospContrOptionDTO(
                entity.getId(), entity.getName(), entity.getTypes(),
                entity.getExplanation());
        dto.setDoubleParameter(entity.getDoubleParameter());
        dto.setLongParameter(entity.getLongParameter());
        dto.setStringParameter(entity.getStringParameter());
        return dto;
    }

    public static HospContrOption toEntity(HospContrOptionDTO dto) {
        HospContrOption entity = new HospContrOption(
                dto.getId() == 0 ? null : dto.getId(), dto.getName(), dto.getTypes(),
                dto.getExplanation());
        entity.setDoubleParameter(dto.getDoubleParameter());
        entity.setLongParameter(dto.getLongParameter());
        entity.setStringParameter(dto.getStringParameter());
        return entity;
    }

    public static ScenarioDTO toDTO(Scenario entity) {
        ScenarioDTO dto = new ScenarioDTO();
        dto.setId(entity.getId());
        dto.setEnglishName(entity.getEnglishName());
        dto.setHungarianName(entity.getHungarianName());
        dto.setBehaviour(entity.getBehaviour());
        dto.setCategory(entity.getCategory());
        dto.setRadiologistStarts(entity.getRadiologistStarts());
        return dto;
    }

    public static Scenario toEntity(ScenarioDTO dto) {
        Scenario entity = new Scenario(dto.getId() == 0 ? null : dto.getId());
        entity.setEnglishName(dto.getEnglishName());
        entity.setHungarianName(dto.getHungarianName());
        entity.setBehaviour(dto.getBehaviour());
        entity.setCategory(dto.getCategory());
        entity.setRadiologistStarts(dto.isRadiologistStarts());
        return entity;
    }

    public static TDSConstantDTO toDTO(ITSConstant entity) {
        return new TDSConstantDTO(entity.getId(), entity.getName(), entity.getConstantValue(), entity.getDescription());
    }

    public static ITSConstant toEntity(TDSConstantDTO dto) {
        return new ITSConstant(dto.getId() == 0 ? null : dto.getId(), dto.getName(), dto.getConstantValue(), dto.getDescription());
    }
}
