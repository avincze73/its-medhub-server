/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.HospContrOption;

/**
 *
 * @author root
 */
@Stateless
public class HospContrOptionRepository extends AbstractRepository<HospContrOption> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospContrOptionRepository() {
        super(HospContrOption.class);
    }

}
