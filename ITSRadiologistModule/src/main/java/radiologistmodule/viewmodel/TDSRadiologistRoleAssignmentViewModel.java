/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.TDSListEditViewModelBase;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.iview.IRadiologistEditView;
import usermodule.dto.TDSRadiologistRoleAssignmentDTO;
import usermodule.dto.TDSRadiologistRoleDTO;
import usermodule.service.RoleServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class TDSRadiologistRoleAssignmentViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected TDSRadiologistRoleAssignmentDTO selectedEntity;
    protected TDSRadiologistRoleAssignmentDTO newEntity;
    protected ObservableList<TDSRadiologistRoleAssignmentDTO> entityList;
    protected ObservableList<TDSRadiologistRoleDTO> roleList;
    private String remoteServiceJndiName;
    private long tdsRadiologistId;

    public TDSRadiologistRoleAssignmentViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RadAvailabilityServiceRemote";
        selectedEntity = new TDSRadiologistRoleAssignmentDTO();
        entityList = ObservableCollections.observableList(new ArrayList<TDSRadiologistRoleAssignmentDTO>());
        roleList = ObservableCollections.observableList(new ArrayList<TDSRadiologistRoleDTO>());
        loadRoles();
    }

    private void loadRoles() {
        try {
            RoleServiceRemote roleServiceRemote = (RoleServiceRemote) new InitialContext().lookup("usermodule.service.RoleServiceRemote");
            List<TDSRadiologistRoleDTO> roles = roleServiceRemote.getTDSRadiologistRoles();
            roleList.addAll(roles);
        } catch (NamingException ex) {
            Logger.getLogger(TDSRadiologistRoleAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doSaveAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<TDSRadiologistRoleAssignmentDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<TDSRadiologistRoleAssignmentDTO> entityList) {
        ObservableList<TDSRadiologistRoleAssignmentDTO> oldValue = this.entityList;
        this.entityList = entityList;
        propertyChangeSupport.firePropertyChange("entityList", oldValue, this.entityList);
    }

    public TDSRadiologistRoleAssignmentDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(TDSRadiologistRoleAssignmentDTO newEntity) {
        TDSRadiologistRoleAssignmentDTO oldValue = this.newEntity;
        this.newEntity = newEntity;
        propertyChangeSupport.firePropertyChange("newEntity", oldValue, this.newEntity);
    }

    public TDSRadiologistRoleAssignmentDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(TDSRadiologistRoleAssignmentDTO selectedEntity) {
        TDSRadiologistRoleAssignmentDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }

    public ObservableList<TDSRadiologistRoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(ObservableList<TDSRadiologistRoleDTO> roleList) {
        this.roleList = roleList;
    }

    


}
