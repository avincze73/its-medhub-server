/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import usermodule.dto.SessionLogEntryActionType;
import usermodule.dto.SessionLogEntryDTO;
import usermodule.entity.ITSSession;
import usermodule.entity.ITSSessionLogEntry;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class SessionLogService {

    @Resource
    private SessionContext ctx;
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;

    public ITSSession getActiveSession() {
        String strJQL = "select max(s.id) from TDSSession s where s.tDSUser.loginName like :loginName";
        long sessionId = em.createQuery(strJQL, Long.class).setParameter("loginName", ctx.getCallerPrincipal().getName()).getSingleResult();
        return new ITSSession(sessionId);
    }

    public void save(SessionLogEntryActionType actionType, String tableName, String columnName, String fromValue, String toValue,
            long recordId, String note) {
        ITSSessionLogEntry log = new ITSSessionLogEntry();
        log.setItsSession(getActiveSession());
        log.setLogged(new Date());
        log.setActionType(actionType.name());
        log.setNote(note);
        log.setRecordId(recordId);
        log.setTableName(tableName);
        log.setColumnName(columnName);
        log.setToValue(toValue);
        log.setFromValue(fromValue);
        em.persist(log);
    }

    public void save(SessionLogEntryDTO dto) {
        ITSSessionLogEntry log = new ITSSessionLogEntry();
        log.setItsSession(getActiveSession());
        log.setLogged(new Date());
        log.setActionType(dto.getActionType());
        log.setNote(dto.getNote());
        log.setRecordId(dto.getRecordId());
        log.setTableName(dto.getTableName());
        log.setColumnName(dto.getColumnName());
        log.setToValue(dto.getToValue());
        log.setFromValue(dto.getFromValue());
        em.persist(log);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
