/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class SuperVisionDTO extends BaseDTO {

    public Date started;
    public Date ended;
    public Date added;
    public boolean active;
    public TDSRadiologistDTO supervisor;
    public TDSRadiologistDTO supervised;

    public SuperVisionDTO(long id) {
        super(id);
    }

    public SuperVisionDTO() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof SuperVisionDTO)) {
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

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        Date oldValue = this.added;
        this.added = added;
        propertyChangeSupport.firePropertyChange("added", oldValue, this.added);
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        Date oldValue = this.ended;
        this.ended = ended;
        propertyChangeSupport.firePropertyChange("ended", oldValue, this.ended);
    }

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        Date oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
    }

    public TDSRadiologistDTO getSupervised() {
        return supervised;
    }

    public void setSupervised(TDSRadiologistDTO supervised) {
        TDSRadiologistDTO oldValue = this.supervised;
        this.supervised = supervised;
        propertyChangeSupport.firePropertyChange("supervised", oldValue, this.supervised);
    }

    public TDSRadiologistDTO getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(TDSRadiologistDTO supervisor) {
        TDSRadiologistDTO oldValue = this.supervisor;
        this.supervisor = supervisor;
        propertyChangeSupport.firePropertyChange("supervisor", oldValue, this.supervisor);
    }
}
