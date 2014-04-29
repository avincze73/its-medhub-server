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
public class ReportingDTO extends BaseDTO {

    private Date assigned;
    private boolean active;
    private Date done;
    private Date doneConfirmed;
    private Date ready;
    private String unfinishedText;
    private String finishedAndSignedText;
    private Boolean opened;
    private WayOfClosingDTO wayOfClosing;
    private CaseDTO caseItBelongsTo;
    private TDSRadiologistDTO tdsRadiologist;
    private String technicalDetails;
    private String medicalHistory;
    private String description;
    private String conclusion;

    public ReportingDTO(long id) {
        super(id);
    }

    public ReportingDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportingDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        boolean oldValue = this.active;
        this.active = active;
        propertyChangeSupport.firePropertyChange("active", oldValue, this.active);
    }

    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        Date oldValue = this.assigned;
        this.assigned = assigned;
        propertyChangeSupport.firePropertyChange("assigned", oldValue, this.assigned);
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

    public CaseDTO getCaseItBelongsTo() {
        return caseItBelongsTo;
    }

    public void setCaseItBelongsTo(CaseDTO caseItBelongsTo) {
        CaseDTO oldValue = this.caseItBelongsTo;
        this.caseItBelongsTo = caseItBelongsTo;
        propertyChangeSupport.firePropertyChange("caseItBelongsTo", oldValue, this.caseItBelongsTo);
    }

    public TDSRadiologistDTO getTdsRadiologist() {
        return tdsRadiologist;
    }

    public void setTdsRadiologist(TDSRadiologistDTO tdsRadiologist) {
        TDSRadiologistDTO oldValue = this.tdsRadiologist;
        this.tdsRadiologist = tdsRadiologist;
        propertyChangeSupport.firePropertyChange("tdsRadiologist", oldValue, this.tdsRadiologist);
    }

    public Boolean isOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        Boolean oldValue = this.opened;
        this.opened = opened;
        propertyChangeSupport.firePropertyChange("opened", oldValue, this.opened);
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        String oldValue = this.conclusion;
        this.conclusion = conclusion;
        propertyChangeSupport.firePropertyChange("conclusion", oldValue, this.conclusion);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldValue = this.description;
        this.description = description;
        propertyChangeSupport.firePropertyChange("description", oldValue, this.description);
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        String oldValue = this.medicalHistory;
        this.medicalHistory = medicalHistory;
        propertyChangeSupport.firePropertyChange("medicalHistory", oldValue, this.medicalHistory);
    }

    public Date getReady() {
        return ready;
    }

    public void setReady(Date ready) {
        Date oldValue = this.ready;
        this.ready = ready;
        propertyChangeSupport.firePropertyChange("ready", oldValue, this.ready);
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public void setTechnicalDetails(String technicalDetails) {
        String oldValue = this.technicalDetails;
        this.technicalDetails = technicalDetails;
        propertyChangeSupport.firePropertyChange("technicalDetails", oldValue, this.technicalDetails);
    }


}
