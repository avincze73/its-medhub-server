/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import radiologistmodule.entity.WorkBandTable;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class WorkBandTableRepository extends AbstractRepository<WorkBandTable> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkBandTableRepository() {
        super(WorkBandTable.class);
    }

}
