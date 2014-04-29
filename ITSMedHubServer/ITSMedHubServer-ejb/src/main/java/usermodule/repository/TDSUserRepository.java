/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.repository;

import common.AbstractRepository;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSUser;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class TDSUserRepository extends AbstractRepository<ITSUser> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TDSUserRepository() {
        super(ITSUser.class);
    }

    public List<ITSUser> find(String name, String userType) throws TooManyResultsException, ZeroResultException {
        String strJQL = "select u from TDSUser u ";
        if (name != null && !"".equals(name)) {
            if (strJQL.contains("where")) {
                strJQL = strJQL + " and UPPER(u.name) like \'%" + name + "%\'";
            } else {
                strJQL = strJQL + " where UPPER(u.name) like \'%" + name + "%\'";
            }
        }
        if (userType != null && !"".equals(userType)) {
            if (strJQL.contains("where")) {
                strJQL = strJQL + " and u.userType like \'%" + userType + "%\'";
            } else {
                strJQL = strJQL + " where u.userType like \'%" + userType + "%\'";
            }
        }
        List<ITSUser> entities = em.createQuery(strJQL, ITSUser.class).getResultList();
        if (entities.isEmpty()) {
            throw new ZeroResultException();
        } else if (entities.size() > getMaxRecordNumber()) {
            throw new TooManyResultsException();
        }
        return entities;
    }

    public List<ITSSession> findSessionsByUser(long userId){
        String strJQL = "select s from ITSSession s where s.tDSUser.id = " + userId;
        List<ITSSession> entities = em.createQuery(strJQL, ITSSession.class).getResultList();
        return entities;
    }
}
