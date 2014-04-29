/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.entity;

import base.ITSEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "ITSManager")
public class ITSManager extends ITSEntity {

    @JoinColumn(name = "itsUserId", referencedColumnName = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ITSUser itsUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itsManager")
    private List<ITSManagerRoleAssignment> itsManagerRoleAssignmentList;

    public ITSManager() {
        this(null);
    }

    public ITSManager(Long id) {
        super(id);
        itsUser = new ITSUser();
        itsManagerRoleAssignmentList = new ArrayList<ITSManagerRoleAssignment>();
    }

    @Override
    public ITSManager clone() throws CloneNotSupportedException {
        ITSManager result = (ITSManager) super.clone();
        result.setItsUser(itsUser.clone());
        return result;
    }

    
    public List<ITSManagerRoleAssignment> getItsManagerRoleAssignmentList() {
        return itsManagerRoleAssignmentList;
    }

    public void setItsManagerRoleAssignmentList(List<ITSManagerRoleAssignment> itsManagerRoleAssignmentList) {
        this.itsManagerRoleAssignmentList = itsManagerRoleAssignmentList;
    }

    public ITSUser getItsUser() {
        return itsUser;
    }

    public void setItsUser(ITSUser itsUser) {
        this.itsUser = itsUser;
    }
}
