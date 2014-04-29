/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radiologistmodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.entity.ITSRadiologist;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class TDSRadiologistRepository extends AbstractRepository<ITSRadiologist> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDSRadiologistRepository() {
        super(ITSRadiologist.class);
    }

}
