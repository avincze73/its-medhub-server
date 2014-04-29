/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.entity;

import base.ITSEntity;
import masterdatamodule.entity.Scenario;
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
@Table(name = "ScenarioPolicy")
public class ScenarioPolicy extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "policyText")
    private String policyText;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @JoinColumn(name = "scenarioId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Scenario scenario;

    public ScenarioPolicy() {
    }

    public ScenarioPolicy(Long id) {
        super(id);
    }

    public ScenarioPolicy(Long id, String policyText, Date added) {
        this(id, null, policyText, added);
    }

    public ScenarioPolicy(Long id, Scenario scenario, String policyText, Date added) {
        super(id);
        this.scenario = scenario;
        this.policyText = policyText;
        this.added = added;
    }

    public ScenarioPolicy(Scenario scenario, String policyText, Date added) {
        this(null, scenario, policyText, added);
    }

    public String getPolicyText() {
        return policyText;
    }

    public void setPolicyText(String policyText) {
        this.policyText = policyText;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public String toString() {
        return "entity.ScenarioPolicy[id=" + id + "]";
    }
}
