/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import casemodule.entity.TDSCase;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class PerformaceTestService implements PerformaceTestServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public void connect() {
        List<TDSCase> entities = em.createNamedQuery("TDSCase.findAll", TDSCase.class).getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
