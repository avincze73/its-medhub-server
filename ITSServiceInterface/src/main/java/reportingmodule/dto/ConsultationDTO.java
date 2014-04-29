/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.dto;

import base.BaseDTO;
import casemodule.dto.CaseDTO;
import java.util.Date;
import masterdatamodule.dto.WayOfClosingDTO;
import radiologistmodule.dto.TDSRadiologistDTO;

/**
 *
 * @author vincze.attila
 */
public class ConsultationDTO extends BaseDTO {

    private Date requested;
    private String requestText;
    private Date accepted;
    private Date done;
    private Date doneConfirmed;
    private String unfinishedText;
    private String finishedAndSignedText;
    private boolean opened;
    private WayOfClosingDTO wayOfClosing;
    private CaseDTO caseItBelongsTo;
    private TDSRadiologistDTO tdsRadiologist;

    public ConsultationDTO(int id) {
        super(id);
    }

    public ConsultationDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConsultationDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAccepted() {
        return accepted;
    }

    public void setAccepted(Date accepted) {
        Date oldValue = this.accepted;
        this.accepted = accepted;
        propertyChangeSupport.firePropertyChange("accepted", oldValue, this.accepted);
    }

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public Date getDone() {
        return done;
    }

    public void setDone(Date done) {
        Date oldValue = this.done;
        this.done = done;
        propertyChangeSupport.firePropertyChange("done", oldValue, this.done);
    }

    public Date getDoneConfirmed() {
        return doneConfirmed;
    }

    public void setDoneConfirmed(Date doneConfirmed) {
        Date oldValue = this.doneConfirmed;
        this.doneConfirmed = doneConfirmed;
        propertyChangeSupport.firePropertyChange("doneConfirmed", oldValue, this.doneConfirmed);
    }

    public String getFinishedAndSignedText() {
        return finishedAndSignedText;
    }

    public void setFinishedAndSignedText(String finishedAndSignedText) {
        String oldValue = this.finishedAndSignedText;
        this.finishedAndSignedText = finishedAndSignedText;
        propertyChangeSupport.firePropertyChange("finishedAndSignedText", oldValue, this.finishedAndSignedText);
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        boolean oldValue = this.opened;
        this.opened = opened;
        propertyChangeSupport.firePropertyChange("opened", oldValue, this.opened);
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        String oldValue = this.requestText;
        this.requestText = requestText;
        propertyChangeSupport.firePropertyChange("requestText", oldValue, this.requestText);
    }

    public Date getRequested() {
        return requested;
    }

    public void setRequested(Date requested) {
        Date oldValue = this.requested;
        this.requested = requested;
        propertyChangeSupport.firePropertyChange("requested", oldValue, this.requested);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }

    public String getUnfinishedText() {
        return unfinishedText;
    }

    public void setUnfinishedText(String unfinishedText) {
        String oldValue = this.unfinishedText;
        this.unfinishedText = unfinishedText;
        propertyChangeSupport.firePropertyChange("unfinishedText", oldValue, this.unfinishedText);
    }

    public WayOfClosingDTO getWayOfClosing() {
        return wayOfClosing;
    }

    public void setWayOfClosing(WayOfClosingDTO wayOfClosing) {
        WayOfClosingDTO oldValue = this.wayOfClosing;
        this.wayOfClosing = wayOfClosing;
        propertyChangeSupport.firePropertyChange("wayOfClosing", oldValue, this.wayOfClosing);
    }
}
