/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import radiologistmodule.entity.ITSRadiologist;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSRadiologistRoleAssignment")
public class ITSRadiologistRoleAssignment extends ITSRoleAssignment {

    @JoinColumn(name = "itsRadiologistRoleId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologistRole itsRadiologistRole;
    @JoinColumn(name = "itsRadiologistId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSRadiologist itsRadiologist;

    public ITSRadiologistRoleAssignment() {
        super();
    }

    public ITSRadiologistRoleAssignment(Long id) {
        super(id);
    }

    public ITSRadiologist getItsRadiologist() {
        return itsRadiologist;
    }

    public void setItsRadiologist(ITSRadiologist itsRadiologist) {
        this.itsRadiologist = itsRadiologist;
    }

    public ITSRadiologistRole getItsRadiologistRole() {
        return itsRadiologistRole;
    }

    public void setItsRadiologistRole(ITSRadiologistRole itsRadiologistRole) {
        this.itsRadiologistRole = itsRadiologistRole;
    }
}
