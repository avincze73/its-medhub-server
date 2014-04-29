/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.ITSLanguage;

/**
 *
 * @author root
 */
@Stateless
public class LanguageRepository extends AbstractRepository<ITSLanguage> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LanguageRepository() {
        super(ITSLanguage.class);
    }

}
