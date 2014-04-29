/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.BodyRegion;
import masterdatamodule.entity.Modality;
import radiologistmodule.assembler.RadiologistAssembler;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.entity.ITSRadiologist;
import radiologistmodule.entity.RadCompetence;
import radiologistmodule.repository.RadCompetenceRepository;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RadCompetenceService implements RadCompetenceServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "repository")
    private RadCompetenceRepository repository;

    @Override
    public List<RadCompetenceDTO> findAll() throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RadCompetenceDTO> findByName(String name) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RadCompetenceDTO find(long id) {
        RadCompetence item = repository.find(id);
        return RadiologistAssembler.toDTO(item);
    }

    @Override
    public long save(RadCompetenceDTO dto) {
        String strJQL = "select r from RadCompetence r "
                + "where r.tDSRadiologist.id = :id and r.modality.name like :modality and r.bodyRegion.englishName like :bodyRegion";
        RadCompetence item = null;

        try {
            item = em.createQuery(strJQL, RadCompetence.class).setParameter("id", dto.getTdsRadiologist().getId())
                    .setParameter("modality", dto.getModality().getName())
                    .setParameter("bodyRegion", dto.getBodyRegion().getEnglishName()).getSingleResult();
            item.setCompetenceLevel(dto.getCompetenceLevel());
            em.merge(item);
        } catch (NoResultException ex) {
            item = new RadCompetence();
            item.setModality(em.createQuery("select m from Modality m where m.name like :name", Modality.class).setParameter("name", dto.getModality().getName()).getSingleResult());
            item.setBodyRegion(em.createQuery("select b from BodyRegion b where englishName like :name", BodyRegion.class).setParameter("name", dto.getBodyRegion().getEnglishName()).getSingleResult());
            item.setAddingDateTime(new Date());
            item.setCompetenceLevel(dto.getCompetenceLevel());
            item.setValid(true);
            item.setTDSRadiologist(new ITSRadiologist(dto.getTdsRadiologist().getId()));
            em.persist(item);
            em.flush();
        }
        return item.getId();
    }

    @Override
    public void delete(long id) {
        RadCompetence item = repository.find(id);
        repository.remove(item);
    }

    @Override
    public List<RadCompetenceDTO> findByRadiologist(int radiologistId) {
        List<RadCompetenceDTO> dtos = new ArrayList<RadCompetenceDTO>();
        List<RadCompetence> items =
                em.createQuery("SELECT r FROM RadCompetence r WHERE r.tdsRadiologistId = :id")
                .setParameter("id", radiologistId).getResultList();
        for (RadCompetence item : items) {
            dtos.add(RadiologistAssembler.toDTO(item));
        }
        return dtos;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
