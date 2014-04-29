/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.dto.DataProcLogEntryType;
import casemodule.entity.DataProcLog;
import casemodule.entity.DataProcLogEntry;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vincze.attila
 */
@Stateless
@LocalBean
public class DataProcLogService {

    @PersistenceContext(unitName = "ITSMedHubServerPU")
    private EntityManager em;
    @Resource
    private SessionContext ctx;

    public void saveEntry(long dataProcLogId, DataProcLogEntryType entryType, String entry) {
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(entryType.name());
        dataProcLogEntry.setEntry(entry);
        dataProcLogEntry.setDataProcLog(new DataProcLog(dataProcLogId));
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        em.persist(dataProcLogEntry);
    }

    public void saveEntry(DataProcLog dataProcLog, DataProcLogEntryType entryType, String entry) {
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(entryType.name());
        dataProcLogEntry.setEntry(entry);
        dataProcLogEntry.setDataProcLog(dataProcLog);
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        em.persist(dataProcLogEntry);
    }

    public void saveEntry(DataProcLog dataProcLog, DataProcLogEntryType entryType,
            String tableName, String columnName, String recordUid, String fromValue,
            String toValue) {
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(entryType.name());
        dataProcLogEntry.setDataProcLog(dataProcLog);
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        dataProcLogEntry.setTableName(tableName);
        dataProcLogEntry.setColumnName(columnName);
        dataProcLogEntry.setRecordUid(recordUid);
        dataProcLogEntry.setFromValue(fromValue);
        dataProcLogEntry.setToValue(toValue);
        //dataProcLogEntry.setEntry(tableName + ": " + recordUid);
        em.persist(dataProcLogEntry);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void saveEntry(DataProcLog dataProcLog, String note) {
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(DataProcLogEntryType.UserComment.name());
        dataProcLogEntry.setDataProcLog(dataProcLog);
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        dataProcLogEntry.setNote(note);
        dataProcLogEntry.setEntry("TDSCase: " + dataProcLog.getTDSCase().getId());
        em.persist(dataProcLogEntry);
    }

    public void saveEntry(DataProcLog dataProcLog, String note, String entry) {
        DataProcLogEntry dataProcLogEntry = new DataProcLogEntry();
        dataProcLogEntry.setEntryGeneration(new Date());
        dataProcLogEntry.setEntryType(DataProcLogEntryType.UserComment.name());
        dataProcLogEntry.setDataProcLog(dataProcLog);
        dataProcLogEntry.setSender(ctx.getCallerPrincipal().getName());
        dataProcLogEntry.setNote(note);
        dataProcLogEntry.setEntry("TDSCase: " + dataProcLog.getTDSCase().getId() + "-" + entry);
        em.persist(dataProcLogEntry);
    }
}
