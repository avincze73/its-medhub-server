/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.assembler.RadiologistAssembler;
import radiologistmodule.dto.RadAvailabilityDTO;
import radiologistmodule.entity.RadAvailability;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RadAvailabilityService implements RadAvailabilityServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public RadAvailabilityDTO find(long id) {
        RadAvailability entity = em.find(RadAvailability.class, id);
        return RadiologistAssembler.toDTO(entity);
    }

    @Override
    public long save(RadAvailabilityDTO dto) {
        RadAvailability entitiy = RadiologistAssembler.toEntity(dto);
        long res = dto.getId();
        if (dto.getId() == 0) {
            em.persist(entitiy);
            em.flush();
            res = entitiy.getId();
        } else {
            em.merge(entitiy);
            em.flush();
        }
        return res;
    }

    @Override
    public void delete(long id) {
        RadAvailability entity = em.find(RadAvailability.class, id);
        em.remove(em.merge(entity));
    }

    @Override
    public List<RadAvailabilityDTO> findByRadiologist(int radiologistId) {
        List<RadAvailabilityDTO> dtos = new ArrayList<RadAvailabilityDTO>();
        List<RadAvailability> entities =
                em.createQuery("SELECT r FROM RadAvailability r WHERE r.tdsRadiologistId = :id")
                .setParameter("id", radiologistId).getResultList();
        for (RadAvailability entity : entities) {
            dtos.add(RadiologistAssembler.toDTO(entity));
        }
        return dtos;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
