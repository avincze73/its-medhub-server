/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.TDSListEditViewModelBase;
import common.exception.ConstraintException;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.SuperVisionDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadiologistServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class SuperVisionViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected SuperVisionDTO selectedEntity;
    protected SuperVisionDTO newEntity;
    protected ObservableList<SuperVisionDTO> entityList;
    protected ObservableList<TDSRadiologistDTO> tdsRadiologistList;
    private long tdsRadiologistId;

    public SuperVisionViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RadiologistServiceRemote";
        selectedEntity = new SuperVisionDTO();
        entityList = ObservableCollections.observableList(new ArrayList<SuperVisionDTO>());
        tdsRadiologistList = ObservableCollections.observableList(new ArrayList<TDSRadiologistDTO>());
        loadTDSRadiologists();
    }

    public final void loadTDSRadiologists() {
        try {
            RadiologistServiceRemote service = (RadiologistServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            List<TDSRadiologistDTO> res = service.getDraftRadiogistList();
            tdsRadiologistList.clear();
//            for (TDSRadiologistDTO tDSRadiologistDTO : res) {
//                if (tDSRadiologistDTO.getId() == tdsRadiologistId){
//                    res.remove(tDSRadiologistDTO);
//                }
//            }
            tdsRadiologistList.addAll(res);
        } catch (NamingException ex) {
            Logger.getLogger(SuperVisionViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(SuperVisionDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(SuperVisionDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(SuperVisionDTO.class);
    }

    @Override
    protected void doSaveAction() {
        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            newEntity = (SuperVisionDTO) view.getEntity(SuperVisionDTO.class);
            newEntity.setSupervisor(new TDSRadiologistDTO(tdsRadiologistId));
            newEntity.setAdded(new Date());
            if (modeHandler.getMode() == DisplayMode.Insert) {
                entityList.add(newEntity);
                view.setTableSelectionIndex(entityList.size() - 1, SuperVisionDTO.class);
            } else {
                setSelectedEntity(newEntity);
                SuperVisionDTO selectedItem = entityList.get(entityList.indexOf(selectedEntity));

            }
            RadiologistServiceRemote service =
                    (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
            long id = service.saveSuperVision(selectedEntity);
            selectedEntity.setId(id);
            view.saveEntity(SuperVisionDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (ConstraintException ex) {
            Logger.getLogger(SuperVisionViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SuperVisionViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SuperVisionViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<SuperVisionDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<SuperVisionDTO> entityList) {
        this.entityList = entityList;
    }

    public SuperVisionDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(SuperVisionDTO newEntity) {
        this.newEntity = newEntity;
    }

    public SuperVisionDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(SuperVisionDTO selectedEntity) {
        SuperVisionDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }

    public ObservableList<TDSRadiologistDTO> getTdsRadiologistList() {
        return tdsRadiologistList;
    }

    public void setTdsRadiologistList(ObservableList<TDSRadiologistDTO> tdsRadiologistList) {
        this.tdsRadiologistList = tdsRadiologistList;
    }
}
