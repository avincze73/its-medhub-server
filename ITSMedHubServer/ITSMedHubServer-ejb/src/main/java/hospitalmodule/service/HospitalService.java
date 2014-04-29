/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.dto.OptionAssignmentDTO;
import hospitalmodule.entity.ContactPerson;
import hospitalmodule.entity.Hospital;
import hospitalmodule.entity.HospitalContract;
import hospitalmodule.entity.OptionAssignment;
import hospitalmodule.repository.HospitalRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.ITSConstant;
import usermodule.dto.SessionLogEntryActionType;
import usermodule.dto.SessionLogEntryDTO;
import usermodule.service.SessionLogService;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class HospitalService implements HospitalServiceRemote {

    @EJB(name = "repository")
    private HospitalRepository repository;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "sessionLogService")
    private SessionLogService sessionLogService;

    @Override
    public List<HospitalDTO> findAll() throws TooManyResultsException, ZeroResultException {
        List<HospitalDTO> dtos = new ArrayList<HospitalDTO>();
        List<Hospital> items = repository.findAll();
        for (Hospital item : items) {
            dtos.add(HospitalAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public List<HospitalDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        List<HospitalDTO> dtos = new ArrayList<HospitalDTO>();
        List<Hospital> items = repository.findByAttribute("officialName", name);
        for (Hospital item : items) {
            dtos.add(HospitalAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public HospitalDTO find(long id) {
        Hospital item = repository.find(id);
        return HospitalAssembler.toDTO(item);
    }

    @Override
    public long save(HospitalDTO dto) {
        long res = dto.getId();
        Hospital oldHospital;
        if (dto.getId() == 0) {
            oldHospital = new Hospital();
            oldHospital.setAddingDate(new Date());
        } else {
            oldHospital = repository.find(dto.getId());
        }
        Hospital item = HospitalAssembler.updateEntity(dto, oldHospital);
        if (dto.getId() == 0) {
            repository.create(item);
            res = repository.findSingleByAttribute("officialName", dto.getOfficialName()).getId();
        } else {
            repository.edit(item);
        }
        return res;
    }

    @Override
    public void delete(long id) {
        Hospital item = repository.find(id);
        repository.remove(item);
    }

    @Override
    public List<ContactPersonDTO> getContactPersonListByHospital(long hospitalId) {
        List<ContactPersonDTO> dtos = new ArrayList<ContactPersonDTO>();
        List<ContactPerson> ret =
                em.createQuery("SELECT cp FROM ContactPerson cp WHERE cp.hospital.id = :id", ContactPerson.class).setParameter("id", hospitalId).getResultList();
        for (ContactPerson workBandTable : ret) {
            dtos.add(HospitalAssembler.toDTO(workBandTable));
        }
        return dtos;

    }

    @Override
    public void activate(long hospitalId, boolean active) {
        Hospital entity = em.find(Hospital.class, hospitalId);
        entity.setActive(active);
        em.merge(entity);
        sessionLogService.save(SessionLogEntryActionType.mod, "Hospital", "active", new Boolean(!active).toString(), new Boolean(active).toString(), hospitalId, "");
    }

    @Override
    public List<HospitalDTO> getHospitalList() throws TooManyResultsException, ZeroResultException {
        List<HospitalDTO> dtos = new ArrayList<HospitalDTO>();
        List<Hospital> entities = em.createNamedQuery("Hospital.findAll", Hospital.class).getResultList();
        for (Hospital entity : entities) {
            dtos.add(HospitalAssembler.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<HospitalDTO> getHospitalNameList(){
        List<HospitalDTO> dtos = new ArrayList<HospitalDTO>();
        List<Hospital> entities = em.createNamedQuery("Hospital.findAll", Hospital.class).getResultList();
        for (Hospital entity : entities) {
            dtos.add(HospitalAssembler.toMinimalDTO(entity));
        }
        return dtos;
    }

    @Override
    public List<HospitalDTO> getHospitalList(String abbrevName) throws TooManyResultsException, ZeroResultException {
        List<HospitalDTO> dtos = new ArrayList<HospitalDTO>();
        String strJQL = "SELECT h FROM Hospital h ";
        if (abbrevName != null && !"".equals(abbrevName)) {
            strJQL = strJQL + " where UPPER(h.abbrevName) like \'%" + abbrevName.toUpperCase() + "%\'";
        }
        List<Hospital> entities = em.createQuery(strJQL, Hospital.class).getResultList();
        int maxRecordNumber = em.createNamedQuery("TDSConstant.findByName", ITSConstant.class).setParameter("name", "Max record number").getSingleResult().getConstantValue();
        if (entities.isEmpty()) {
            throw new ZeroResultException();
        } else if (entities.size() > maxRecordNumber) {
            throw new TooManyResultsException();
        }
        for (Hospital entity : entities) {
            dtos.add(HospitalAssembler.toDTO(entity));
        }
        return dtos;
    }

    @Override
    public HospitalDTO getHospital(long id) {
        Hospital entity = em.find(Hospital.class, id);
        return HospitalAssembler.toDTO(entity);
    }

    @Override
    public HospitalDTO saveHospital(HospitalDTO dto) {
        if (dto.getId() == 0) {
            dto.setAddingDate(new Date());
        }
        Hospital item = HospitalAssembler.toEntity(dto);
        HospitalDTO originalDTO = (dto.getId() == 0 ? null : getHospital(dto.getId()));
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            dto.setId(item.getId());
        } else {
            em.merge(item);
        }
        for (SessionLogEntryDTO sessionLogEntryDTO : dto.getModifications(originalDTO)) {
            System.out.println(sessionLogEntryDTO.getColumnName());
            sessionLogEntryDTO.setRecordId(dto.getId());
            sessionLogService.save(sessionLogEntryDTO);
        }
        return dto;
    }

    @Override
    public HospitalContractDTO getHospitalContract(long id) {
        HospitalContract entity = em.find(HospitalContract.class, id);
        return HospitalAssembler.toDTO(entity);
    }

    @Override
    public HospitalContractDTO saveHospitalContract(HospitalContractDTO dto) {
        HospitalContract item = HospitalAssembler.toEntity(dto);
        HospitalContractDTO originalDTO = (dto.getId() == 0 ? null : getHospitalContract(dto.getId()));
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            dto.setId(item.getId());
        } else {
            em.merge(item);
        }
        for (SessionLogEntryDTO sessionLogEntryDTO : dto.getModifications(originalDTO)) {
            System.out.println(sessionLogEntryDTO.getColumnName());
            sessionLogEntryDTO.setRecordId(dto.getId());
            sessionLogService.save(sessionLogEntryDTO);
        }
        return dto;
    }

    @Override
    public OptionAssignmentDTO saveOptionAssignment(OptionAssignmentDTO dto) {
        if (dto.getId() == 0) {
        }
        OptionAssignment item = HospitalAssembler.toEntity(dto);
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            dto.setId(item.getId());
        } else {
            em.merge(item);
        }
        return dto;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
