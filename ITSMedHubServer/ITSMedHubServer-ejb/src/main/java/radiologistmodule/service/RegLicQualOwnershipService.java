/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.assembler.RadiologistAssembler;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.entity.RegLicQualOwnership;
import radiologistmodule.repository.RegLicQualOwnershipRepository;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RegLicQualOwnershipService implements RegLicQualOwnershipServiceRemote {
    @EJB(name = "repository")
    private RegLicQualOwnershipRepository repository;

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public List<RegLicQualOwnershipDTO> findAll() throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegLicQualOwnershipDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RegLicQualOwnershipDTO find(long id) {
        RegLicQualOwnership item = repository.find(id);
        return RadiologistAssembler.toDTO(item);
    }

    @Override
    public long save(RegLicQualOwnershipDTO dto) {
        RegLicQualOwnership item = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
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
        RegLicQualOwnership item = repository.find(id);
        repository.remove(item);
    }

    @Override
    public List<RegLicQualOwnershipDTO> findByRadiologist(int radiologistId) {
        List<RegLicQualOwnershipDTO> dtos = new ArrayList<RegLicQualOwnershipDTO>();
        List<RegLicQualOwnership> items =
                em.createQuery("SELECT r FROM RegLicQualOwnership r WHERE r.tdsRadiologistId = :id")
                .setParameter("id", radiologistId).getResultList();
        for (RegLicQualOwnership item : items) {
            dtos.add(RadiologistAssembler.toDTO(item));
        }
        return dtos;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
