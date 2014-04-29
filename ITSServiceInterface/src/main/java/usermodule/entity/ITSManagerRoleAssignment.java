/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSManagerRoleAssignment")
public class ITSManagerRoleAssignment extends ITSRoleAssignment {

    @JoinColumn(name = "itsManagerId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSManager itsManager;
    @JoinColumn(name = "itsManagerRoleId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ITSManagerRole itsManagerRole;

    public ITSManagerRoleAssignment() {
        super();
    }

    public ITSManagerRoleAssignment(Long id) {
        super(id);
    }

    public ITSManager getItsManager() {
        return itsManager;
    }

    public void setItsManager(ITSManager itsManager) {
        this.itsManager = itsManager;
    }

    public ITSManagerRole getItsManagerRole() {
        return itsManagerRole;
    }

    public void setItsManagerRole(ITSManagerRole itsManagerRole) {
        this.itsManagerRole = itsManagerRole;
    }
}
