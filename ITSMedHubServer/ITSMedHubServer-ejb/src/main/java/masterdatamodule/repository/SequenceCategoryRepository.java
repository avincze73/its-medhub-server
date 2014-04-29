/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.SequenceCategory;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class SequenceCategoryRepository extends AbstractRepository<SequenceCategory> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SequenceCategoryRepository() {
        super(SequenceCategory.class);
    }

}
