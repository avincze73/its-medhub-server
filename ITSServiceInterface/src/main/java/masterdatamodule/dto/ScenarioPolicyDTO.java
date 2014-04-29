/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;
import java.beans.PropertyVetoException;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class ScenarioPolicyDTO extends BaseDTO {

    private ScenarioDTO scenario;
    private String policyText;
    private Date added;

    public ScenarioPolicyDTO(int id, ScenarioDTO scenario, String policyText) {
        super(id);
        this.scenario = scenario;
        this.policyText = policyText;
    }

    public ScenarioPolicyDTO(ScenarioDTO scenario, String policyText) {
        super();
        this.scenario = scenario;
        this.policyText = policyText;
    }

    public ScenarioPolicyDTO(ScenarioDTO scenario, String policyText, Date added) {
        super();
        this.scenario = scenario;
        this.policyText = policyText;
        this.added = added;
    }

    
    public ScenarioPolicyDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScenarioPolicyDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getPolicyText() {
        return policyText;
    }

    public void setPolicyText(String policyText) throws PropertyVetoException {
        String oldValue = this.policyText;
        vetoableChangeSupport.fireVetoableChange("policyText", oldValue, this.policyText);
        this.policyText = policyText;
        propertyChangeSupport.firePropertyChange("policyText", oldValue, this.policyText);
    }

    public ScenarioDTO getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioDTO scenario) throws PropertyVetoException {
        ScenarioDTO oldValue = this.scenario;
        vetoableChangeSupport.fireVetoableChange("scenario", oldValue, this.scenario);
        this.scenario = scenario;
        propertyChangeSupport.firePropertyChange("scenario", oldValue, this.scenario);
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }
}
