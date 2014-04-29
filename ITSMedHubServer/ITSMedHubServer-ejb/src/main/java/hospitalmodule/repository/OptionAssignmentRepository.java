/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hospitalmodule.repository;

import common.AbstractRepository;
import hospitalmodule.entity.OptionAssignment;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class OptionAssignmentRepository extends AbstractRepository<OptionAssignment> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OptionAssignmentRepository() {
        super(OptionAssignment.class);
    }

}
