/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vincze.attila
 */
public class DataProcLogDTO extends BaseDTO {

    private CaseDTO caseItBelongsTo;
    private Date started;
    private List<DataProcLogEntryDTO> entryList;

    public DataProcLogDTO() {
        this(0, null, null);
    }

    public DataProcLogDTO(long id) {
        this(id, null, null);
    }

    public DataProcLogDTO(long id, Date started) {
        this(id, null, started);
    }

    public DataProcLogDTO(long id, CaseDTO caseItBelongsTo, Date started) {
        super(id);
        this.caseItBelongsTo = caseItBelongsTo;
        this.started = started;
        entryList = new ArrayList<DataProcLogEntryDTO>();
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataProcLogDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public List<DataProcLogEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<DataProcLogEntryDTO> entryList) {
        List<DataProcLogEntryDTO> oldValue = this.entryList;
        this.entryList = entryList;
        propertyChangeSupport.firePropertyChange("entryList", oldValue, this.entryList);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }
}
