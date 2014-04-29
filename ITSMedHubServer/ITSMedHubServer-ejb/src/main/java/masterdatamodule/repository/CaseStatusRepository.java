/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import masterdatamodule.entity.CaseStatus;

/**
 *
 * @author root
 */
@Stateless
public class CaseStatusRepository extends AbstractRepository<CaseStatus> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaseStatusRepository() {
        super(CaseStatus.class);
    }

    public CaseStatus findByEnglishName(String englishName) {
        return em.createQuery("select c from CaseStatus c where c.englishName like :englishName", CaseStatus.class).setParameter("englishName", englishName).getSingleResult();
    }
}
