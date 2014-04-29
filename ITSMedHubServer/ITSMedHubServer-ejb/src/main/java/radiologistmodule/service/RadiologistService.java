/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.SuperVisionDTO;
import radiologistmodule.dto.WorkPlaceDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.assembler.RadiologistAssembler;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.entity.Company;
import radiologistmodule.entity.CompanyAssignment;
import radiologistmodule.entity.ITSRadiologist;
import radiologistmodule.entity.SuperVision;
import radiologistmodule.entity.WorkPlace;
import radiologistmodule.repository.TDSRadiologistRepository;
import usermodule.entity.ITSUser;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RadiologistService implements RadiologistServiceRemote {
    @EJB(name = "repository")
    private TDSRadiologistRepository repository;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;


    @Override
    public List<TDSRadiologistDTO> findAll() throws TooManyResultsException, ZeroResultException {
        List<TDSRadiologistDTO> dtos = new ArrayList<TDSRadiologistDTO>();
        List<ITSRadiologist> items = repository.findAll();
        for (ITSRadiologist item : items) {
            dtos.add(RadiologistAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public List<TDSRadiologistDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TDSRadiologistDTO find(long id) {
        ITSRadiologist item = repository.find(id);
        return RadiologistAssembler.toDTO(item);
    }

    @Override
    public long save(TDSRadiologistDTO dto) {
        long res = dto.getId();
        ITSRadiologist oldRadiologist;
        if (dto.getId() == 0) {
            oldRadiologist = new ITSRadiologist();
            oldRadiologist.setItsUser(new ITSUser());
            dto.getUserInfo().setAddingDateTime(new Date());
        } else {
            oldRadiologist = repository.find(dto.getId());
        }
        ITSRadiologist item = RadiologistAssembler.updateEntity(dto, oldRadiologist);
        if (dto.getId() == 0) {
            repository.create(item);
            res = item.getId();
        } else {
            repository.edit(item);
        }
        return res;
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<TDSRadiologistDTO> getDraftRadiogistList() {
        List<TDSRadiologistDTO> dtos = new ArrayList<TDSRadiologistDTO>();
        List<ITSRadiologist> items = em.createNamedQuery("TDSRadiologist.findAll", ITSRadiologist.class).getResultList();
        for (ITSRadiologist item : items) {
            dtos.add(RadiologistAssembler.toDraftDTO(item));
        }
        return dtos;
    }

    @Override
    public long saveSuperVision(SuperVisionDTO dto) {
        SuperVision item = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }

    @Override
    public List<CompanyDTO> getCompanyList() {
        List<CompanyDTO> dtos = new ArrayList<CompanyDTO>();
        List<Company> items = em.createNamedQuery("Company.findAll", Company.class).getResultList();
        for (Company item : items) {
            dtos.add(RadiologistAssembler.toDTO(item));
        }
        return dtos;
    }

    @Override
    public long saveCompanyAssignment(CompanyAssignmentDTO dto) {
        CompanyAssignment item = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }

    @Override
    public long saveCompany(CompanyDTO dto) {
        Company item = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }

    @Override
    public long saveWorkPlace(WorkPlaceDTO dto) {
        WorkPlace item = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(item);
            em.flush();
            res = item.getId();
        } else {
            em.merge(item);
        }
        return res;
    }


    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
