/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.DisplayModeHandler;
import base.TDSListEditViewModelBase;
import common.exception.ConstraintException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import hospitalmodule.dto.HospitalDTO;
import hospitalmodule.service.HospitalServiceRemote;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.dto.WorkPlaceDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadiologistServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class WorkplaceViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected WorkPlaceDTO selectedEntity;
    protected WorkPlaceDTO newEntity;
    protected ObservableList<WorkPlaceDTO> entityList;
    protected ObservableList<HospitalDTO> hospitalList;
    private HospitalDTO selectedHospital;
    private HospitalDTO newHospital;
    private long tdsRadiologistId;
    //company management
    protected DisplayModeHandler hospitalModeHandler;
    private Action editHospitalAction;
    private Action saveHospitalAction;
    private Action cancelHospitalAction;

    public WorkplaceViewModel(final IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        hospitalModeHandler = new DisplayModeHandler();
        remoteServiceJndiName = "radiologistmodule.service.RadiologistServiceRemote";
        selectedEntity = new WorkPlaceDTO();
        selectedHospital = new HospitalDTO();
        entityList = ObservableCollections.observableList(new ArrayList<WorkPlaceDTO>());
        hospitalList = ObservableCollections.observableList(new ArrayList<HospitalDTO>());



        editHospitalAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                hospitalModeHandler.setMode(DisplayMode.Edit);
                view.editEntity(HospitalDTO.class);

            }
        };

        saveHospitalAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
            }
        };

        cancelHospitalAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                hospitalModeHandler.setMode(DisplayMode.View);
                view.cancelEntity(HospitalDTO.class);
            }
        };

        loadHospitals();
    }

    private void loadHospitals() {
        try {
            HospitalServiceRemote service = (HospitalServiceRemote) new InitialContext().lookup("hospitalmodule.service.HospitalServiceRemote");
            List<HospitalDTO> res = service.findAll();
            hospitalList.addAll(res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(WorkPlaceDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(WorkPlaceDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(WorkPlaceDTO.class);
    }

    @Override
    protected void doSaveAction() {
        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            newEntity = (WorkPlaceDTO) view.getEntity(WorkPlaceDTO.class);
            newEntity.setTdsRadiologist(new TDSRadiologistDTO(tdsRadiologistId));
            newEntity.setAdded(new Date());
            if (modeHandler.getMode() == DisplayMode.Insert) {
                entityList.add(newEntity);
                view.setTableSelectionIndex(entityList.size() - 1, WorkPlaceDTO.class);
            } else {
                setSelectedEntity(newEntity);
                //WorkPlaceDTO selectedItem = entityList.get(entityList.indexOf(selectedEntity));
            }
            RadiologistServiceRemote service =
                    (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
            long id = service.saveWorkPlace(selectedEntity);
            selectedEntity.setId(id);
            view.saveEntity(WorkPlaceDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (ConstraintException ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(WorkplaceViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Action getCancelHospitalAction() {
        return cancelHospitalAction;
    }

    public void setCancelHospitalAction(Action cancelHospitalAction) {
        this.cancelHospitalAction = cancelHospitalAction;
    }

    public Action getEditHospitalAction() {
        return editHospitalAction;
    }

    public void setEditHospitalAction(Action editHospitalAction) {
        this.editHospitalAction = editHospitalAction;
    }

    public ObservableList<WorkPlaceDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<WorkPlaceDTO> entityList) {
        this.entityList = entityList;
    }

    public ObservableList<HospitalDTO> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(ObservableList<HospitalDTO> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public DisplayModeHandler getHospitalModeHandler() {
        return hospitalModeHandler;
    }

    public void setHospitalModeHandler(DisplayModeHandler hospitalModeHandler) {
        this.hospitalModeHandler = hospitalModeHandler;
    }

    public WorkPlaceDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(WorkPlaceDTO newEntity) {
        this.newEntity = newEntity;
    }

    public HospitalDTO getNewHospital() {
        return newHospital;
    }

    public void setNewHospital(HospitalDTO newHospital) {
        this.newHospital = newHospital;
    }

    public Action getSaveHospitalAction() {
        return saveHospitalAction;
    }

    public void setSaveHospitalAction(Action saveHospitalAction) {
        this.saveHospitalAction = saveHospitalAction;
    }

    public WorkPlaceDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(WorkPlaceDTO selectedEntity) {
        WorkPlaceDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public HospitalDTO getSelectedHospital() {
        return selectedHospital;
    }

    public void setSelectedHospital(HospitalDTO selectedHospital) {
        HospitalDTO oldValue = this.selectedHospital;
        this.selectedHospital = selectedHospital;
        propertyChangeSupport.firePropertyChange("selectedHospital", oldValue, this.selectedHospital);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }
}
