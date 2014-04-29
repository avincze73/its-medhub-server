/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.entity.RadCompetence;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class RadCompetenceRepository extends AbstractRepository<RadCompetence> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RadCompetenceRepository() {
        super(RadCompetence.class);
    }

}
