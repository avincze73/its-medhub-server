/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import common.ITSCriteria;
import common.QueryObject;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import radiologistmodule.entity.ITSRadiologist;
import usermodule.entity.HospitalStaff;
import usermodule.entity.ITSManager;
import usermodule.entity.UserType;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserService.class);
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    /* ITS Manager */
    public List<ITSManager> getITSManagerList(QueryObject queryObject) throws TooManyResultsException, ZeroResultException {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("name", "m.itsUser.name");
        StringBuilder sb = new StringBuilder("select m from ITSManager m ");
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            if (sb.indexOf("where") == -1) {
                sb.append("where ");
            } else {
                sb.append(" and ");
            }
            sb.append(mapping.get(itsCriteria.getField()));
            sb.append(" ");
            sb.append(itsCriteria.getOperator());
            sb.append(" :");
            sb.append(itsCriteria.getField());
        }
        log.info(sb.toString());
        Query query = em.createQuery(sb.toString(), ITSManager.class);
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            query.setParameter(itsCriteria.getField(), "%" +  itsCriteria.getValue() + "%") ;
            log.info(itsCriteria.getValue());
        }
        return query.getResultList();
    }

    public ITSManager getITSManager(Long id) {
        return null;
    }

    public ITSManager saveITSManager(ITSManager entity) {
        log.info("saving: " + entity);
        if (entity.getId() == null) {
            entity.getItsUser().setAddingDateTime(new Date());
            entity.getItsUser().setUserType(UserType.itsManager.name());
            em.persist(entity);
            em.flush();
            entity.setId(entity.getId());
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public byte[] getITSManagerList(byte[] input) throws TooManyResultsException, ZeroResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] getITSManager(byte[] input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] saveITSManager(byte[] input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /* ITS Manager */
    public List<ITSRadiologist> getITSRadiologistList(QueryObject queryObject) throws TooManyResultsException, ZeroResultException {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("name", "r.itsUser.name");
        StringBuilder sb = new StringBuilder("select r from ITSRadiologist r ");
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            if (sb.indexOf("where") == -1) {
                sb.append("where ");
            } else {
                sb.append(" and ");
            }
            sb.append(mapping.get(itsCriteria.getField()));
            sb.append(" ");
            sb.append(itsCriteria.getOperator());
            sb.append(" :");
            sb.append(itsCriteria.getField());
        }
        log.info(sb.toString());
        Query query = em.createQuery(sb.toString(), ITSRadiologist.class);
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            query.setParameter(itsCriteria.getField(), "%" +  itsCriteria.getValue() + "%") ;
            log.info(itsCriteria.getValue());
        }
        return query.getResultList();
    }

    public ITSRadiologist getITSRadiologist(Long id) {
        return null;
    }

    public ITSRadiologist saveITSRadiologist(ITSRadiologist entity) {
        log.info("saving: " + entity);
        if (entity.getId() == null) {
            entity.getItsUser().setAddingDateTime(new Date());
            entity.getItsUser().setUserType(UserType.itsRadiologist.name());
            em.persist(entity);
            em.flush();
            entity.setId(entity.getId());
        } else {
            em.merge(entity);
        }
        return entity;
    }


    
        /* Hospital staff */
    public List<HospitalStaff> getHospitalStaffList(QueryObject queryObject) throws TooManyResultsException, ZeroResultException {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("name", "h.itsUser.name");
        StringBuilder sb = new StringBuilder("select h from HospitalStaff h ");
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            if (sb.indexOf("where") == -1) {
                sb.append("where ");
            } else {
                sb.append(" and ");
            }
            sb.append(mapping.get(itsCriteria.getField()));
            sb.append(" ");
            sb.append(itsCriteria.getOperator());
            sb.append(" :");
            sb.append(itsCriteria.getField());
        }
        log.info(sb.toString());
        Query query = em.createQuery(sb.toString(), HospitalStaff.class);
        for (ITSCriteria itsCriteria : queryObject.getCriteriaList()) {
            query.setParameter(itsCriteria.getField(), "%" +  itsCriteria.getValue() + "%") ;
            log.info(itsCriteria.getValue());
        }
        return query.getResultList();
    }

    public HospitalStaff getHospitalStaff(Long id) {
        return null;
    }

    public HospitalStaff saveHospitalStaff(HospitalStaff entity) {
        log.info("saving: " + entity);
        if (entity.getId() == null) {
            entity.getItsUser().setAddingDateTime(new Date());
            entity.getItsUser().setUserType(UserType.hospitalStaff.name());
            em.persist(entity);
            em.flush();
            entity.setId(entity.getId());
        } else {
            em.merge(entity);
        }
        return entity;
    }

}
