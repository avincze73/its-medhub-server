/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.TDSListEditViewModelBase;
import common.exception.ConstraintException;
import hospitalmodule.dto.AvailabilityPerWeekDTO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.RadAvailabilityDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadAvailabilityServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class RadAvailabilityViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected RadAvailabilityDTO selectedEntity;
    protected RadAvailabilityDTO newEntity;
    protected ObservableList<RadAvailabilityDTO> entityList;
    private String remoteServiceJndiName;
    private long tdsRadiologistId;

    public RadAvailabilityViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RadAvailabilityServiceRemote";
        selectedEntity = new RadAvailabilityDTO();
        entityList = ObservableCollections.observableList(new ArrayList<RadAvailabilityDTO>());
    }

    @Override
    protected void doSaveAction() {
        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            newEntity = (RadAvailabilityDTO) view.getEntity(RadAvailabilityDTO.class);
            newEntity.setTdsRadiologist(new TDSRadiologistDTO(tdsRadiologistId));
            newEntity.Validate(bundle);
            if (modeHandler.getMode() == DisplayMode.Insert) {
                newEntity.setAdded(new Date());
                entityList.add(newEntity);
                view.setTableSelectionIndex(entityList.size() - 1, RadAvailabilityDTO.class);
            } else {
                RadAvailabilityDTO selectedItem = entityList.get(entityList.indexOf(selectedEntity));
                selectedItem.setValid(newEntity.isValid());
            }
            RadAvailabilityServiceRemote service =
                    (RadAvailabilityServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            long id = service.save(selectedEntity);
            selectedEntity.setId(id);
            view.saveEntity(RadAvailabilityDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (ConstraintException ex) {
            Logger.getLogger(RadAvailabilityViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RadAvailabilityViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(RadAvailabilityDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(RadAvailabilityDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(RadAvailabilityDTO.class);
    }


    public ObservableList<RadAvailabilityDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<RadAvailabilityDTO> entityList) {
        ObservableList<RadAvailabilityDTO> oldValue = this.entityList;
        this.entityList = entityList;
        propertyChangeSupport.firePropertyChange("entityList", oldValue, this.entityList);
    }

    public RadAvailabilityDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(RadAvailabilityDTO newEntity) {
        RadAvailabilityDTO oldValue = this.newEntity;
        this.newEntity = newEntity;
        propertyChangeSupport.firePropertyChange("newEntity", oldValue, this.newEntity);

    }

    public RadAvailabilityDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(RadAvailabilityDTO selectedEntity) {
        RadAvailabilityDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
        if (this.selectedEntity == null) return;
        if (this.selectedEntity.getMaxAvailabilityList() == null){
            this.selectedEntity.setMaxAvailabilityList(ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>()));
        }
        if (this.selectedEntity.getNormalAvailabilityList() == null){
            this.selectedEntity.setNormalAvailabilityList(ObservableCollections.observableList(new ArrayList<AvailabilityPerWeekDTO>()));
        }
        this.selectedEntity.getMaxAvailabilityList().clear();
        this.selectedEntity.getMaxAvailabilityList().add(this.selectedEntity.getMaxAvailabilityPerWeek());
        this.selectedEntity.getNormalAvailabilityList().clear();
        this.selectedEntity.getNormalAvailabilityList().add(this.selectedEntity.getNormalAvailabilityPerWeek());
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }
}
