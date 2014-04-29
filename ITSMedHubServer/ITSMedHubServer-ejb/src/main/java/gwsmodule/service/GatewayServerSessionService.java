/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gwsmodule.service;

import common.RemoteMessage;
import common.dto.ClientIdentifierDTO;
import common.exception.UserAlreadyLoggedInException;
import common.exception.UserNotDefinedException;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import usermodule.dto.SessionEndingType;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSUser;
import usermodule.entity.InvalidLogin;
import util.CryptographicUtil;

/**
 *
 * @author itsadmin
 */
@Stateless
public class GatewayServerSessionService implements GatewayServerSessionServiceRemote {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    protected EntityManager em;
    @Resource
    private SessionContext ctx;

    @Override
    public RemoteMessage getPassword(RemoteMessage input) throws UserNotDefinedException, UserAlreadyLoggedInException {
        log.info("GatewayServerSessionService.getPassword entering...");
        String userName = (String) CryptographicUtil.create().decrypt(input.getMessage());
        log.info(userName);
        String strJQL = "select u from ITSUser u where u.loginName = :loginName";
        ITSUser itsUser = null;
        try {
            itsUser = em.createQuery(strJQL, ITSUser.class).setParameter("loginName", userName).getSingleResult();

        } catch (NoResultException ex) {
            throw new UserNotDefinedException();
        } catch (NonUniqueResultException ex) {
            throw new UserNotDefinedException();
        }

        strJQL = "select s from ITSSession s where s.itsUser.id = :userId and s.ended is null";
        ITSSession itsSession = null;
        try {
            itsSession = em.createQuery(strJQL, ITSSession.class).setParameter("userId", itsUser.getId()).getSingleResult();

        } catch (NoResultException ex) {
            //Logger.getLogger(CaseTransferService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonUniqueResultException ex) {
        } catch (Exception ex) {
        }
        if (itsSession != null) {
            throw new UserAlreadyLoggedInException(
                    new ClientIdentifierDTO(itsSession.getClientHostName(),
                    itsSession.getClientHostIpAddress(),
                    itsSession.getClientHostMacAddress(),
                    itsSession.getItsApplication()),
                    ctx.getCallerPrincipal().getName());
        }

        RemoteMessage output = new RemoteMessage(CryptographicUtil.create().encrypt(itsUser.getPassword()));
        log.info("GatewayServerSessionService.getPassword leaving...");
        return output;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void startITSSession() {
        log.info("startITSSession entering...");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        ITSSession itsSession = new ITSSession();
        itsSession.setStarted(new Date());
        itsSession.setItsUser(em.find(ITSUser.class, 1L));
        itsSession.setClientHostIpAddress("ip");
        itsSession.setClientHostMacAddress("mac");
        itsSession.setClientHostName("host");
        itsSession.setItsApplication("app" + session.getId());
        em.persist(itsSession);
        log.info("startITSSession leaving...");
    }

    @Override
    public void endITSSession(String sessionId, Object endingType) {
        try {
            String strJQL = "select s from ITSSession s where s.httpSessionId = :sessionId";
            ITSSession itsSession = em.createQuery(strJQL, ITSSession.class).setParameter("sessionId", sessionId).getSingleResult();
            itsSession.setEnded(new Date());
            itsSession.setEndingType(SessionEndingType.logout.name());
            em.merge(itsSession);
        } catch (NoResultException e) {
        }

    }

    @Override
    public void loginSucceeded(RemoteMessage input) {
        ClientIdentifierDTO clientIdentifierDTO = (ClientIdentifierDTO) CryptographicUtil.create().decrypt(input.getMessage());
        String strJQL = "select u from ITSUser u where u.loginName = :loginName";
        ITSUser itsUser = em.createQuery(strJQL, ITSUser.class).setParameter("loginName", clientIdentifierDTO.getLoginName()).getSingleResult();
        ITSSession itsSession = new ITSSession();
        itsSession.setStarted(new Date());
        itsSession.setItsUser(itsUser);
        itsSession.setClientHostIpAddress(clientIdentifierDTO.getHostIpAddress());
        itsSession.setClientHostMacAddress(clientIdentifierDTO.getHostMacAddress());
        itsSession.setClientHostName(clientIdentifierDTO.getHostName());
        itsSession.setItsApplication(clientIdentifierDTO.getItsApplication());
        itsSession.setHttpSessionId(clientIdentifierDTO.getSessionId());
        em.persist(itsSession);
    }

    @Override
    public void loginFailed(RemoteMessage input) {
        ClientIdentifierDTO clientIdentifierDTO = (ClientIdentifierDTO) CryptographicUtil.create().decrypt(input.getMessage());

        InvalidLogin invalidLogin = new InvalidLogin();
        invalidLogin.setClientHostIpAddress(clientIdentifierDTO.getHostIpAddress());
        invalidLogin.setClientHostName(clientIdentifierDTO.getHostName());
        invalidLogin.setClientHostMacAddress(clientIdentifierDTO.getHostMacAddress());
        invalidLogin.setLoginName(clientIdentifierDTO.getLoginName());
        invalidLogin.setItsApplication(clientIdentifierDTO.getItsApplication());
        invalidLogin.setIncidentDate(new Date());
        em.persist(invalidLogin);
    }
}
