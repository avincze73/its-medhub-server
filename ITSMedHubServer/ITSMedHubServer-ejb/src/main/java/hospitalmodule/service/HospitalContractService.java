/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import hospitalmodule.assembler.HospitalAssembler;
import hospitalmodule.dto.ContactPersonAssignmentDTO;
import hospitalmodule.dto.ContactPersonDTO;
import hospitalmodule.dto.HospitalContractDTO;
import hospitalmodule.entity.ContactPerson;
import hospitalmodule.entity.ContactPersonAssignment;
import hospitalmodule.entity.Hospital;
import hospitalmodule.entity.HospitalContract;
import hospitalmodule.repository.HospitalContractRepository;
import hospitalmodule.repository.HospitalRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class HospitalContractService implements HospitalContractServiceRemote {

    @EJB(name = "hospitalRepository")
    private HospitalRepository hospitalRepository;
    @EJB(name = "repository")
    private HospitalContractRepository repository;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public List<HospitalContractDTO> findAll() throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<HospitalContractDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HospitalContractDTO find(long id) {
        HospitalContract item = repository.find(id);
        return HospitalAssembler.toDTO(item);
    }

    @Override
    public long save(HospitalContractDTO dto) {
        long res = dto.getId();
        Hospital hospital = hospitalRepository.find(dto.getHospital().getId());
        HospitalContract oldHospitalContract;
        if (dto.getId() == 0) {
            oldHospitalContract = new HospitalContract();
            oldHospitalContract.setHospital(hospital);

        } else {
            oldHospitalContract = repository.find(dto.getId());
        }
        HospitalContract item = HospitalAssembler.updateEntity(dto, oldHospitalContract);
        if (dto.getId() == 0) {
            repository.create(item);
            res = repository.findSingleByAttribute("contractCode", dto.getContractCode()).getId();
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
    public long saveContactPersonAssignment(ContactPersonAssignmentDTO dto) {
        ContactPersonAssignment item = HospitalAssembler.toEntity(dto);
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
    public long saveContactPerson(ContactPersonDTO dto) {
        ContactPerson item = HospitalAssembler.toEntity(dto);
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
