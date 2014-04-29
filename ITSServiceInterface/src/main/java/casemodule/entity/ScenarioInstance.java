/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.entity;

import masterdatamodule.entity.Scenario;
import casemodule.entity.TDSCase;
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
@Table(name = "ScenarioInstance")
@NamedQueries({
    @NamedQuery(name = "ScenarioInstance.findAll", query = "SELECT s FROM ScenarioInstance s"),
    @NamedQuery(name = "ScenarioInstance.findById", query = "SELECT s FROM ScenarioInstance s WHERE s.id = :id"),
    @NamedQuery(name = "ScenarioInstance.findByAdded", query = "SELECT s FROM ScenarioInstance s WHERE s.added = :added"),
    @NamedQuery(name = "ScenarioInstance.findByNote", query = "SELECT s FROM ScenarioInstance s WHERE s.note = :note"),
    @NamedQuery(name = "ScenarioInstance.findByDeactivated", query = "SELECT s FROM ScenarioInstance s WHERE s.deactivated = :deactivated")})
public class ScenarioInstance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date added;
    @Column(name = "note")
    private String note;
    @Column(name = "deactivated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivated;
    @JoinColumn(name = "scenarioId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Scenario scenario;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TDSCase tDSCase;

    public ScenarioInstance() {
    }

    public ScenarioInstance(Long id) {
        this.id = id;
    }

    public ScenarioInstance(Long id, Date added) {
        this.id = id;
        this.added = added;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(Date deactivated) {
        this.deactivated = deactivated;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
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
        if (!(object instanceof ScenarioInstance)) {
            return false;
        }
        ScenarioInstance other = (ScenarioInstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ScenarioInstance[id=" + id + "]";
    }

}
