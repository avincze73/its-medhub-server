/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import masterdatamodule.entity.Scenario;

/**
 *
 * @author root
 */
@Stateless
public class ScenarioRepository extends AbstractRepository<Scenario> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScenarioRepository() {
        super(Scenario.class);
    }

    public List<Scenario> findAllForRadiologist(){
        return em.createQuery("SELECT s FROM Scenario s WHERE s.radiologistStarts = :radiologistStarts", Scenario.class)
                .setParameter("radiologistStarts", true).getResultList();
    }

}
