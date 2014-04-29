/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import common.dto.ClientIdentifierDTO;
import common.dto.ITSApplicationType;
import common.exception.UserAlreadyLoggedInException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import usermodule.dto.SessionEndingType;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSUser;
import usermodule.entity.UserType;
import util.CryptographicUtil;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
//@PersistenceContext(name = "persistence/LogicalName", unitName = "ITSMedHubServerPU")
public class UserSessionService implements UserSessionServiceRemote {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    protected EntityManager em;
    @Resource
    private SessionContext ctx;
    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());

    @Override
    public void login(ClientIdentifierDTO clientIdentifierDTO) throws UserAlreadyLoggedInException {

        log.info("UserSessionService.login leaving");
        String strJQL = "select u from ITSUser u where u.loginName = :loginName";
        ITSUser itsUser = em.createQuery(strJQL, ITSUser.class).setParameter("loginName", ctx.getCallerPrincipal().getName()).getSingleResult();
        log.info(itsUser);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Logger Util Info");

        if (UserType.itsRadiologist.name().equals(itsUser.getUserType())
                && !ITSApplicationType.itsRadiologistClient.name().equals(clientIdentifierDTO.getItsApplication())) {
            throw new EJBException("Invalid user of " + ITSApplicationType.itsRadiologistClient.name());
        } else if (UserType.hospitalStaff.name().equals(itsUser.getUserType())
                && !ITSApplicationType.itsHospitalClient.name().equals(clientIdentifierDTO.getItsApplication())) {
            throw new EJBException("Invalid user of " + ITSApplicationType.itsHospitalClient.name());
        } else if (UserType.itsManager.name().equals(itsUser.getUserType())
                && !ITSApplicationType.itsManagerClient.name().equals(clientIdentifierDTO.getItsApplication())) {
            throw new EJBException("Invalid user of " + ITSApplicationType.itsManagerClient.name());
        }

        strJQL = "select s from ITSSession s where s.itsUser.id = :userId and s.itsApplication = :itsApplication and s.ended is null";
        //strJQL = "select s from ITSSession s where s.itsUser.id = :userId and s.itsApplication = :itsApplication";
        ITSSession itsSession = null;
        try {
            itsSession = em.createQuery(strJQL, ITSSession.class).setParameter("userId", itsUser.getId()).setParameter("itsApplication", clientIdentifierDTO.getItsApplication()).getSingleResult();

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
        itsSession = new ITSSession();
        itsSession.setStarted(new Date());
        itsSession.setItsUser(itsUser);
        itsSession.setClientHostIpAddress(clientIdentifierDTO.getHostIpAddress());
        itsSession.setClientHostMacAddress(clientIdentifierDTO.getHostMacAddress());
        itsSession.setClientHostName(clientIdentifierDTO.getHostName());
        itsSession.setItsApplication(clientIdentifierDTO.getItsApplication());
        em.persist(itsSession);
        //sessionRepository.create(tdsSession);


        //throw new UnsupportedOperationException("Not supported yet.");
        log.info("login method is called.");
        log.info(clientIdentifierDTO.getHostIpAddress());
        log.info("UserSessionService.login leaving");
    }

    @Override
    public void logout(String itsApplication) {
        log.info("UserSessionService.logout entering");
        String strJQL = "select u.id from ITSUser u where u.loginName = :loginName";
        long itsUserId = em.createQuery(strJQL, Long.class).setParameter("loginName", ctx.getCallerPrincipal().getName()).getSingleResult();
        strJQL = "select max(s.id) from ITSSession s where s.itsUser.id = :userId and s.itsApplication = :itsApplication";
        long sessionId = em.createQuery(strJQL, Long.class).setParameter("userId", itsUserId).setParameter("itsApplication", itsApplication).getSingleResult();
        ITSSession itsSession = em.find(ITSSession.class, sessionId);
        itsSession.setEnded(new Date());
        itsSession.setEndingType(SessionEndingType.logout.name());
        em.merge(itsSession);
        log.info("UserSessionService.logout leaving");
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void login(byte[] clientIdentifierDTO) throws UserAlreadyLoggedInException {
        log.info("secret login method is called.");
        login((ClientIdentifierDTO) CryptographicUtil.create().decrypt(clientIdentifierDTO));
    }

    @Override
    public void logout(byte[] itsApplication) {
        logout((String) CryptographicUtil.create().decrypt(itsApplication));
    }

    
    
    
    
    

}
