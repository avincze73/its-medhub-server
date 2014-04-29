/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class SessionLogEntryDTO extends BaseDTO {

    private Date logged;
    private String actionType;
    private String tableName;
    private String columnName;
    private String fromValue;
    private String toValue;
    private Long recordId;
    private String note;
    private SessionDTO session;

    public SessionLogEntryDTO(long id) {
        super(id);
    }

    public SessionLogEntryDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SessionLogEntryDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        String oldValue = this.actionType;
        this.actionType = actionType;
        propertyChangeSupport.firePropertyChange("actionType", oldValue, this.actionType);
    }

    public Date getLogged() {
        return logged;
    }

    public void setLogged(Date logged) {
        Date oldValue = this.logged;
        this.logged = logged;
        propertyChangeSupport.firePropertyChange("logged", oldValue, this.logged);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        String oldValue = this.note;
        this.note = note;
        propertyChangeSupport.firePropertyChange("note", oldValue, this.note);
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        Long oldValue = this.recordId;
        this.recordId = recordId;
        propertyChangeSupport.firePropertyChange("recordId", oldValue, this.recordId);
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        SessionDTO oldValue = this.session;
        this.session = session;
        propertyChangeSupport.firePropertyChange("session", oldValue, this.session);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        String oldValue = this.tableName;
        this.tableName = tableName;
        propertyChangeSupport.firePropertyChange("tableName", oldValue, this.tableName);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        String oldValue = this.columnName;
        this.columnName = columnName;
        propertyChangeSupport.firePropertyChange("columnName", oldValue, this.columnName);
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
}
