/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class DataProcLogEntryDTO extends BaseDTO {

    private DataProcLogDTO dataProcLog;
    private Date entryGeneration;
    private String entry;
    private String entryType;
    private String sender;
    private String note;
    private String displayedByUser;
    private String displayedByClient;
    private String recordUid;
    private String tableName;
    private String columnName;
    private String fromValue;
    private String toValue;

    public DataProcLogEntryDTO(int id) {
        super(id);
    }

    public DataProcLogEntryDTO(long id) {
        super(id);
    }

    public DataProcLogEntryDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataProcLogEntryDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public DataProcLogDTO getDataProcLog() {
        return dataProcLog;
    }

    public void setDataProcLog(DataProcLogDTO dataProcLog) {
        DataProcLogDTO oldValue = this.dataProcLog;
        this.dataProcLog = dataProcLog;
        propertyChangeSupport.firePropertyChange("dataProcLog", oldValue, this.dataProcLog);
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        String oldValue = this.entry;
        this.entry = entry;
        propertyChangeSupport.firePropertyChange("entry", oldValue, this.entry);
    }

    public Date getEntryGeneration() {
        return entryGeneration;
    }

    public void setEntryGeneration(Date entryGeneration) {
        Date oldValue = this.entryGeneration;
        this.entryGeneration = entryGeneration;
        propertyChangeSupport.firePropertyChange("entryGeneration", oldValue, this.entryGeneration);
    }

    public String getDisplayedByClient() {
        return displayedByClient;
    }

    public void setDisplayedByClient(String displayedByClient) {
        String oldValue = this.displayedByClient;
        this.displayedByClient = displayedByClient;
        propertyChangeSupport.firePropertyChange("displayedByClient", oldValue, this.displayedByClient);
    }

    public String getDisplayedByUser() {
        return displayedByUser;
    }

    public void setDisplayedByUser(String displayedByUser) {
        String oldValue = this.displayedByUser;
        this.displayedByUser = displayedByUser;
        propertyChangeSupport.firePropertyChange("displayedByUser", oldValue, this.displayedByUser);
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        String oldValue = this.entryType;
        this.entryType = entryType;
        propertyChangeSupport.firePropertyChange("entryType", oldValue, this.entryType);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        String oldValue = this.note;
        this.note = note;
        propertyChangeSupport.firePropertyChange("note", oldValue, this.note);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        String oldValue = this.sender;
        this.sender = sender;
        propertyChangeSupport.firePropertyChange("sender", oldValue, this.sender);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        String oldValue = this.columnName;
        this.columnName = columnName;
        propertyChangeSupport.firePropertyChange("columnName", oldValue, this.columnName);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        String oldValue = this.tableName;
        this.tableName = tableName;
        propertyChangeSupport.firePropertyChange("tableName", oldValue, this.tableName);
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        String oldValue = this.fromValue;
        this.fromValue = fromValue;
        propertyChangeSupport.firePropertyChange("fromValue", oldValue, this.fromValue);
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        String oldValue = this.toValue;
        this.toValue = toValue;
        propertyChangeSupport.firePropertyChange("toValue", oldValue, this.toValue);
    }

    public String getRecordUid() {
        return recordUid;
    }

    public void setRecordUid(String recordUid) {
        String oldValue = this.recordUid;
        this.recordUid = recordUid;
        propertyChangeSupport.firePropertyChange("recordUid", oldValue, this.recordUid);
    }
}
