/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import casemodule.entity.TDSCase;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class AutoCaseAssignmentService {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @EJB(name = "caseAssignmentService")
    private CaseAssignmentService caseAssignmentService;

    @Schedule(minute = "*/59", hour = "*/2")
    public void assignCaseFromWaitingQueue() {
        Logger.getLogger(AutoCaseAssignmentService.class.getName()).log(Level.INFO, "trying to assign cases from waiting queue automatically");
        String strJQL = "select c from TDSCase c where c.caseStatus.englishName = :normal or c.caseStatus.englishName = :urgent";
        List<TDSCase> caseList = em.createQuery(strJQL, TDSCase.class).setParameter("normal", "waiting - normal").setParameter("urgent", "waiting - urgent").getResultList();
        for (TDSCase tDSCase : caseList) {
            caseAssignmentService.assignCaseToRadiologistRandomly(tDSCase);
        }
    }
}
