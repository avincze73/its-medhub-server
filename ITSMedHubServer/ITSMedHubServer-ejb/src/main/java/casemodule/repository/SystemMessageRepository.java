/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.repository;

import common.AbstractRepository;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import systemmodule.entity.SystemMessage;

/**
 *
 * @author vincze.attila
 */
@Stateless
public class SystemMessageRepository extends AbstractRepository<SystemMessage> {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemMessageRepository() {
        super(SystemMessage.class);
    }

    public List<SystemMessage> findByUserAndType(long userId, long systemMessageTypeId) throws TooManyResultsException, ZeroResultException {
        String strJQL = "select sm from SystemMessage sm ";
        if (userId != 0) {
            if (strJQL.contains("where")) {
                strJQL = strJQL + " and tDSUser.id = " + userId;
            } else {
                strJQL = strJQL + " where tDSUser.id = " + userId;
            }
        }
        if (systemMessageTypeId != 0) {
            if (strJQL.contains("where")) {
                strJQL = strJQL + " and systemMessageType.id = " + systemMessageTypeId;
            } else {
                strJQL = strJQL + " where systemMessageType.id = " + systemMessageTypeId;
            }
        }
        List<SystemMessage> resultList = em.createQuery(strJQL, SystemMessage.class).getResultList();
        return resultList;
    }
}
