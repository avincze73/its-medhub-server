/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import base.service.EntityServiceBase;
import common.ITSCriteria;
import common.QueryObject;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import usermodule.entity.ITSManager;
import usermodule.entity.UserType;

/**
 *
 * @author tds
 */
@Stateless
@LocalBean
public class ManagerService extends EntityServiceBase<ITSManager> {

    public ManagerService() {
        super(ITSManager.class);
    }

    @Override
    protected void updateEntity(ITSManager entity) {
        super.updateEntity(entity);
        entity.getItsUser().setAddingDateTime(new Date());
        entity.getItsUser().setUserType(UserType.itsManager.name());
    }
    
    public List<ITSManager> getList(QueryObject queryObject) throws TooManyResultsException, ZeroResultException {
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
}
