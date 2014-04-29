/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSSessionLogEntry")
public class ITSSessionLogEntry extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "logged")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logged;
    @Basic(optional = false)
    @Column(name = "actionType")
    private String actionType;
    @Column(name = "tableName")
    private String tableName;
    @Column(name = "recordId")
    private Long recordId;
    @Column(name = "note")
    private String note;
    @Column(name = "columnName")
    private String columnName;
    @Column(name = "fromValue")
    private String fromValue;
    @Column(name = "toValue")
    private String toValue;
    @JoinColumn(name = "itsSessionId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSSession itsSession;

    public ITSSessionLogEntry() {
    }

    public ITSSessionLogEntry(Long id) {
        super(id);
    }

    public ITSSessionLogEntry(Long id, Date logged, String actionType) {
        super(id);
        this.logged = logged;
        this.actionType = actionType;
    }

    public Date getLogged() {
        return logged;
    }

    public void setLogged(Date logged) {
        this.logged = logged;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String fromValue) {
        this.fromValue = fromValue;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String toValue) {
        this.toValue = toValue;
    }

    public ITSSession getItsSession() {
        return itsSession;
    }

    public void setItsSession(ITSSession itsSession) {
        this.itsSession = itsSession;
    }
}
