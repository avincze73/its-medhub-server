/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.dto;

import base.BaseDTO;
import java.util.Date;
import masterdatamodule.dto.ScenarioDTO;

/**
 *
 * @author vincze.attila
 */
public class ScenarioInstanceDTO extends BaseDTO {

    private Date added;
    private String note;
    private ScenarioDTO scenario;
    private CaseDTO tdsCase;
    private Date deactivated;

    public ScenarioInstanceDTO(long id) {
        super(id);
    }

    public ScenarioInstanceDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScenarioInstanceDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        String oldValue = this.note;
        this.note = note;
        propertyChangeSupport.firePropertyChange("note", oldValue, this.note);
    }

    public ScenarioDTO getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioDTO scenario) {
        ScenarioDTO oldValue = this.scenario;
        this.scenario = scenario;
        propertyChangeSupport.firePropertyChange("scenario", oldValue, this.scenario);
    }

    public CaseDTO getTdsCase() {
        return tdsCase;
    }

    public void setTdsCase(CaseDTO tdsCase) {
        CaseDTO oldValue = this.tdsCase;
        this.tdsCase = tdsCase;
        propertyChangeSupport.firePropertyChange("tdsCase", oldValue, this.tdsCase);
    }

    public Date getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Date deactivated) {
        Date oldValue = this.deactivated;
        this.deactivated = deactivated;
        propertyChangeSupport.firePropertyChange("deactivated", oldValue, this.deactivated);
    }
}
