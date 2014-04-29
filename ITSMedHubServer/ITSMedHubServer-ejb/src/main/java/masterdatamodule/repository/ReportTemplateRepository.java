/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.repository;

import casemodule.entity.ReportTemplate;
import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class ReportTemplateRepository extends AbstractRepository<ReportTemplate> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReportTemplateRepository() {
        super(ReportTemplate.class);
    }
}
