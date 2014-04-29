/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package usermodule.repository;

import common.AbstractRepository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import usermodule.entity.ITSSession;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class SessionRepository extends AbstractRepository<ITSSession> {
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionRepository() {
        super(ITSSession.class);
    }

}
