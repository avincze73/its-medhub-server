/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.repository;

import casemodule.entity.TDSCase;
import common.AbstractRepository;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class TDSCaseRepository extends AbstractRepository<TDSCase> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @Resource
    private SessionContext ctx;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDSCaseRepository() {
        super(TDSCase.class);
    }

    public List<TDSCase> getWaitingCaseList() {
        String strJQL = "select c from TDSCase c "
                + "where c.caseStatus.englishName like :englishName";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("englishName", "waiting%").getResultList();
        return caseList;
    }

    public List<TDSCase> getNotWaitingCaseList() {
        String strJQL = "select c from TDSCase c "
                + "where c.caseStatus.englishName not like :englishName";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("englishName", "waiting%").getResultList();
        return caseList;
    }

    public List<TDSCase> findByCaseStatus(String englisName) {
        String strJQL = "select c from TDSCase c "
                + "where c.caseStatus.englishName like :englishName";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("englishName", englisName).getResultList();
        return caseList;
    }
}
