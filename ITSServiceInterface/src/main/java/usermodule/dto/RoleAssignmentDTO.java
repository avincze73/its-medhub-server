/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.dto;

import base.BaseDTO;
import java.util.Date;

/**
 *
 * @author vincze.attila
 */
public class RoleAssignmentDTO extends BaseDTO {

    private Date added;
    private Date ended;
    private Date assignmentStart;//assignmentStart a time: 03:00:00, rendszerkonstans
    private Date plannedEnd;//
    private boolean active;

    public RoleAssignmentDTO(int id) {
        super(id);
    }

    public RoleAssignmentDTO() {
        this(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RoleAssignmentDTO)) {
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

    public Date getAssignmentStart() {
        return assignmentStart;
    }

    public void setAssignmentStart(Date assignmentStart) {
        Date oldValue = this.assignmentStart;
        this.assignmentStart = assignmentStart;
        propertyChangeSupport.firePropertyChange("assignmentStart", oldValue, this.assignmentStart);
    }

    public Date getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(Date plannedEnd) {
        Date oldValue = this.plannedEnd;
        this.plannedEnd = plannedEnd;
        propertyChangeSupport.firePropertyChange("plannedEnd", oldValue, this.plannedEnd);
    }
}
