/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ITSRoleAssignment extends ITSEntity {

    @Basic(optional = false)
    @Column(name = "added")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date added;
    @Column(name = "ended")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date ended;
    @Column(name = "assignmentStart")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date assignmentStart;
    @Column(name = "plannedEnd")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date plannedEnd;
    @Basic(optional = false)
    @Column(name = "active")
    protected boolean active;

    public ITSRoleAssignment(Long id) {
        super(id);
    }

    public ITSRoleAssignment() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Date getAssignmentStart() {
        return assignmentStart;
    }

    public void setAssignmentStart(Date assignmentStart) {
        this.assignmentStart = assignmentStart;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public Date getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(Date plannedEnd) {
        this.plannedEnd = plannedEnd;
    }
}
