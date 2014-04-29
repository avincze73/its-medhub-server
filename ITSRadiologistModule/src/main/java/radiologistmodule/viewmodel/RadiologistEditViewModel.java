/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.IListEditViewBase;
import base.TDSEditViewModelBase;
import event.CloseActiveViewEvent;
import event.ITSEventManager;
import event.UpdateRadiologistInListEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.RadAvailabilityDTO;
import radiologistmodule.dto.RadCompetenceDTO;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.dto.SuperVisionDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.dto.WorkPlaceDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadiologistServiceRemote;
import usermodule.dto.TDSRadiologistRoleAssignmentDTO;

/**
 *
 * @author vincze.attila
 */
public class RadiologistEditViewModel extends TDSEditViewModelBase {

    private IRadiologistEditView view;
    protected TDSRadiologistDTO selectedEntity;
    protected TDSRadiologistDTO newEntity;
    private RadCompetenceViewModel radCompetenceViewModel;
    private RegLicQualOwnershipViewModel regLicQualOwnershipViewModel;
    private TDSRadiologistRoleAssignmentViewModel tdsRadiologistRoleAssignmentViewModel;
    private SuperVisionViewModel superVisionViewModel;
    private CompanyAssignmentViewModel companyAssignmentViewModel;
    private WorkplaceViewModel workplaceViewModel;
    private RadAvailabilityViewModel radAvailabilityViewModel;
    private RadiologistServiceRemote service;
    private String remoteServiceJndiName;

    public RadiologistEditViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RadiologistServiceRemote";
        radCompetenceViewModel = new RadCompetenceViewModel(view);
        regLicQualOwnershipViewModel = new RegLicQualOwnershipViewModel(view);
        tdsRadiologistRoleAssignmentViewModel = new TDSRadiologistRoleAssignmentViewModel(view);
        superVisionViewModel = new SuperVisionViewModel(view);
        companyAssignmentViewModel = new CompanyAssignmentViewModel(view);
        workplaceViewModel = new WorkplaceViewModel(view);
        radAvailabilityViewModel = new RadAvailabilityViewModel(view);
        selectedEntity = new TDSRadiologistDTO();
    }

    @Override
    protected void doEditAction() {
    }

    @Override
    protected void doSaveAction() {
        if (JOptionPane.showConfirmDialog((Component) view, "Mentés?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            service = (RadiologistServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            long id = service.save(selectedEntity);
            selectedEntity.setId(id);
            loadRadiologist(selectedEntity);
            ITSEventManager.fireUpdateRadiologistInListEvent(new UpdateRadiologistInListEvent(selectedEntity));
        } catch (NamingException ex) {
            Logger.getLogger(RadiologistEditViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doCancelAction() {
        if (selectedEntity.getId() == 0) {
            modeHandler.setMode(DisplayMode.View);
            ITSEventManager.fireCloseActiveViewEvent(new CloseActiveViewEvent(""));
        } else {
            loadRadiologist(selectedEntity);
        }
    }

    public void saveModeHandler() {
        setOldModeHandler(modeHandler);
    }

    public void restoreModeHandler() {
        setModeHandler(oldModeHandler);
    }

    public void loadRadiologist(TDSRadiologistDTO radiologist) {
        radCompetenceViewModel.getEntityList().clear();
        radCompetenceViewModel.setTdsRadiologistId(radiologist.getId());
        regLicQualOwnershipViewModel.getEntityList().clear();
        regLicQualOwnershipViewModel.setTdsRadiologistId(radiologist.getId());
        tdsRadiologistRoleAssignmentViewModel.getEntityList().clear();
        tdsRadiologistRoleAssignmentViewModel.setTdsRadiologistId(radiologist.getId());
        superVisionViewModel.getEntityList().clear();
        superVisionViewModel.setTdsRadiologistId(radiologist.getId());
        superVisionViewModel.loadTDSRadiologists();
        companyAssignmentViewModel.getEntityList().clear();
        companyAssignmentViewModel.setTdsRadiologistId(radiologist.getId());
        workplaceViewModel.getEntityList().clear();
        workplaceViewModel.setTdsRadiologistId(radiologist.getId());
        radAvailabilityViewModel.getEntityList().clear();
        radAvailabilityViewModel.setTdsRadiologistId(radiologist.getId());
        setSelectedEntity(radiologist);
        if (selectedEntity.getId() == 0) {
            modeHandler.setMode(DisplayMode.Insert);
            radCompetenceViewModel.getModeHandler().setMode(DisplayMode.Disabled);
            regLicQualOwnershipViewModel.getModeHandler().setMode(DisplayMode.Disabled);
            setTitle(bundle.getString("newRadiologist"));
        } else {
            modeHandler.setMode(DisplayMode.View);
            radCompetenceViewModel.setEntityList(
                    ObservableCollections.observableList(new ArrayList<RadCompetenceDTO>(selectedEntity.getRadCompetenceList())));
            regLicQualOwnershipViewModel.getEntityList().addAll(selectedEntity.getRegLicQualOwnershipList());
            superVisionViewModel.getEntityList().addAll(selectedEntity.getSupervisedRadList());
            companyAssignmentViewModel.getEntityList().addAll(selectedEntity.getCompanyAssignmentList());
            workplaceViewModel.getEntityList().addAll(selectedEntity.getWorkPlaceList());
            radAvailabilityViewModel.getEntityList().addAll(selectedEntity.getAvailabilityList());
            setTitle(selectedEntity.getUserInfo().getName());
            if (selectedEntity.getRadCompetenceList().isEmpty()) {
                radCompetenceViewModel.getModeHandler().setMode(DisplayMode.View);
            } else {
                radCompetenceViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, RadCompetenceDTO.class);
            }
            if (selectedEntity.getRegLicQualOwnershipList().isEmpty()) {
                regLicQualOwnershipViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                regLicQualOwnershipViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, RegLicQualOwnershipDTO.class);
            }
            if (selectedEntity.getRoleAssignmentList().isEmpty()) {
                tdsRadiologistRoleAssignmentViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                tdsRadiologistRoleAssignmentViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, TDSRadiologistRoleAssignmentDTO.class);
            }
            if (selectedEntity.getSupervisedRadList().isEmpty()) {
                superVisionViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                superVisionViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, SuperVisionDTO.class);
            }
            if (selectedEntity.getCompanyAssignmentList().isEmpty()) {
                companyAssignmentViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                companyAssignmentViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, CompanyAssignmentDTO.class);
            }
            if (selectedEntity.getWorkPlaceList().isEmpty()) {
                workplaceViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                workplaceViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, WorkPlaceDTO.class);
            }
            if (selectedEntity.getAvailabilityList().isEmpty()) {
                radAvailabilityViewModel.getModeHandler().setMode(DisplayMode.Empty);
            } else {
                radAvailabilityViewModel.getModeHandler().setMode(DisplayMode.View);
                ((IListEditViewBase) view).setTableSelectionIndex(0, RadAvailabilityDTO.class);
            }
        }
    }

    public RadCompetenceViewModel getRadCompetenceViewModel() {
        return radCompetenceViewModel;
    }

    public void setRadCompetenceViewModel(RadCompetenceViewModel radCompetenceViewModel) {
        this.radCompetenceViewModel = radCompetenceViewModel;
    }

    public RegLicQualOwnershipViewModel getRegLicQualOwnershipViewModel() {
        return regLicQualOwnershipViewModel;
    }

    public void setRegLicQualOwnershipViewModel(RegLicQualOwnershipViewModel regLicQualOwnershipViewModel) {
        this.regLicQualOwnershipViewModel = regLicQualOwnershipViewModel;
    }

    public TDSRadiologistDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(TDSRadiologistDTO newEntity) {
        TDSRadiologistDTO oldValue = this.newEntity;
        this.newEntity = newEntity;
        propertyChangeSupport.firePropertyChange("newEntity", oldValue, this.newEntity);
    }

    public TDSRadiologistDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(TDSRadiologistDTO selectedEntity) {
        TDSRadiologistDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public TDSRadiologistRoleAssignmentViewModel getTdsRadiologistRoleAssignmentViewModel() {
        return tdsRadiologistRoleAssignmentViewModel;
    }

    public void setTdsRadiologistRoleAssignmentViewModel(TDSRadiologistRoleAssignmentViewModel tdsRadiologistRoleAssignmentViewModel) {
        this.tdsRadiologistRoleAssignmentViewModel = tdsRadiologistRoleAssignmentViewModel;
    }

    public SuperVisionViewModel getSuperVisionViewModel() {
        return superVisionViewModel;
    }

    public void setSuperVisionViewModel(SuperVisionViewModel superVisionViewModel) {
        this.superVisionViewModel = superVisionViewModel;
    }

    public CompanyAssignmentViewModel getCompanyAssignmentViewModel() {
        return companyAssignmentViewModel;
    }

    public void setCompanyAssignmentViewModel(CompanyAssignmentViewModel companyAssignmentViewModel) {
        this.companyAssignmentViewModel = companyAssignmentViewModel;
    }

    public WorkplaceViewModel getWorkplaceViewModel() {
        return workplaceViewModel;
    }

    public void setWorkplaceViewModel(WorkplaceViewModel workplaceViewModel) {
        this.workplaceViewModel = workplaceViewModel;
    }

    public RadAvailabilityViewModel getRadAvailabilityViewModel() {
        return radAvailabilityViewModel;
    }

    public void setRadAvailabilityViewModel(RadAvailabilityViewModel radAvailabilityViewModel) {
        this.radAvailabilityViewModel = radAvailabilityViewModel;
    }
}
