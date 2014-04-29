/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import common.dto.ClientIdentifierDTO;
import common.exception.UserAlreadyLoggedInException;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSUser;
import usermodule.repository.SessionRepository;

/**
 *
 * @author vincze.attila
 */
@Stateful
public class ManagerClientSessionService implements ManagerClientSessionServiceRemote {

    @EJB
    private SessionRepository sessionRepository;
    @Resource
    private SessionContext ctx;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    @Override
    public void login(ClientIdentifierDTO clientIdentifierDTO) {
        System.out.println(ctx.getCallerPrincipal().getName());

        String strJQL = "select u from TDSUser u where u.loginName = :loginName";
        ITSUser tdsUser = em.createQuery(strJQL, ITSUser.class).setParameter("loginName", ctx.getCallerPrincipal().getName()).getSingleResult();
        ITSSession tdsSession = new ITSSession();
        tdsSession.setStarted(new Date());
        tdsSession.setItsUser(tdsUser);
        tdsSession.setClientHostIpAddress(clientIdentifierDTO.getHostIpAddress());
        tdsSession.setClientHostMacAddress(clientIdentifierDTO.getHostMacAddress());
        tdsSession.setClientHostName(clientIdentifierDTO.getHostName());
        tdsSession.setItsApplication(clientIdentifierDTO.getItsApplication());
        sessionRepository.create(tdsSession);
    }

    @Override
    public void logout(String tdsApplication) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void login(byte[] clientIdentifierDTO) throws UserAlreadyLoggedInException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void logout(byte[] itsApplication) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
