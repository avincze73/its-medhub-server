/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "DataProcLogEntry")
@NamedQueries({
    @NamedQuery(name = "DataProcLogEntry.findAll", query = "SELECT d FROM DataProcLogEntry d"),
    @NamedQuery(name = "DataProcLogEntry.findById", query = "SELECT d FROM DataProcLogEntry d WHERE d.id = :id"),
    @NamedQuery(name = "DataProcLogEntry.findByEntryGeneration", query = "SELECT d FROM DataProcLogEntry d WHERE d.entryGeneration = :entryGeneration"),
    @NamedQuery(name = "DataProcLogEntry.findByEntry", query = "SELECT d FROM DataProcLogEntry d WHERE d.entry = :entry"),
    @NamedQuery(name = "DataProcLogEntry.findByEntryType", query = "SELECT d FROM DataProcLogEntry d WHERE d.entryType = :entryType"),
    @NamedQuery(name = "DataProcLogEntry.findBySender", query = "SELECT d FROM DataProcLogEntry d WHERE d.sender = :sender"),
    @NamedQuery(name = "DataProcLogEntry.findByNote", query = "SELECT d FROM DataProcLogEntry d WHERE d.note = :note"),
    @NamedQuery(name = "DataProcLogEntry.findByDisplayedByUser", query = "SELECT d FROM DataProcLogEntry d WHERE d.displayedByUser = :displayedByUser"),
    @NamedQuery(name = "DataProcLogEntry.findByDisplayedByClient", query = "SELECT d FROM DataProcLogEntry d WHERE d.displayedByClient = :displayedByClient"),
    @NamedQuery(name = "DataProcLogEntry.findByTableName", query = "SELECT d FROM DataProcLogEntry d WHERE d.tableName = :tableName"),
    @NamedQuery(name = "DataProcLogEntry.findByColumnName", query = "SELECT d FROM DataProcLogEntry d WHERE d.columnName = :columnName"),
    @NamedQuery(name = "DataProcLogEntry.findByFromValue", query = "SELECT d FROM DataProcLogEntry d WHERE d.fromValue = :fromValue"),
    @NamedQuery(name = "DataProcLogEntry.findByToValue", query = "SELECT d FROM DataProcLogEntry d WHERE d.toValue = :toValue")})
public class DataProcLogEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "entryGeneration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryGeneration;
    @Column(name = "entry")
    private String entry;
    @Column(name = "entryType")
    private String entryType;
    @Column(name = "sender")
    private String sender;
    @Column(name = "note")
    private String note;
    @Column(name = "displayedByUser")
    private String displayedByUser;
    @Column(name = "displayedByClient")
    private String displayedByClient;
    @Column(name = "recordUid")
    private String recordUid;
    @Column(name = "tableName")
    private String tableName;
    @Column(name = "columnName")
    private String columnName;
    @Column(name = "fromValue")
    private String fromValue;
    @Column(name = "toValue")
    private String toValue;
    @JoinColumn(name = "dataProcLogId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DataProcLog dataProcLog;

    public DataProcLogEntry() {
    }

    public DataProcLogEntry(Long id) {
        this.id = id;
    }

    public DataProcLogEntry(Long id, Date entryGeneration) {
        this.id = id;
        this.entryGeneration = entryGeneration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntryGeneration() {
        return entryGeneration;
    }

    public void setEntryGeneration(Date entryGeneration) {
        this.entryGeneration = entryGeneration;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDisplayedByUser() {
        return displayedByUser;
    }

    public void setDisplayedByUser(String displayedByUser) {
        this.displayedByUser = displayedByUser;
    }

    public String getDisplayedByClient() {
        return displayedByClient;
    }

    public void setDisplayedByClient(String displayedByClient) {
        this.displayedByClient = displayedByClient;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public DataProcLog getDataProcLog() {
        return dataProcLog;
    }

    public void setDataProcLog(DataProcLog dataProcLog) {
        this.dataProcLog = dataProcLog;
    }

    public String getRecordUid() {
        return recordUid;
    }

    public void setRecordUid(String recordUid) {
        this.recordUid = recordUid;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataProcLogEntry)) {
            return false;
        }
        DataProcLogEntry other = (DataProcLogEntry) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DataProcLogEntry[id=" + id + "]";
    }

}
