/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import reportingmodule.entity.Reporting;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class ReportingRepository extends AbstractRepository<Reporting> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportingRepository() {
        super(Reporting.class);
    }

    public Reporting findActiveOfCase(long caseId) {
        String strJQL = "select r from Reporting r "
                + "where r.tDSCase.id = :caseId and r.active = true";
        return em.createQuery(strJQL, Reporting.class).setParameter("caseId", caseId).getSingleResult();
    }
}
