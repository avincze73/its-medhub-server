/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.IListEditViewBase;
import base.ITDSBaseView;
import base.ListEditViewModelBase;
import base.TDSListEditViewModelBase;
import common.exception.ConstraintException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
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
import masterdatamodule.dto.CountryDTO;
import masterdatamodule.dto.CurrencyDTO;
import masterdatamodule.dto.RegLicQualDTO;
import masterdatamodule.dto.RegLicQualTypeDTO;
import masterdatamodule.dto.RegionDTO;
import masterdatamodule.service.RegLicQualServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.RegLicQualOwnershipDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RegLicQualOwnershipServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class RegLicQualOwnershipViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected RegLicQualOwnershipDTO selectedEntity;
    protected RegLicQualOwnershipDTO newEntity;
    protected ObservableList<RegLicQualOwnershipDTO> entityList;
    private long tdsRadiologistId;
    private String remoteServiceJndiName;
    private ObservableList<RegLicQualDTO> regLicQualList;

    public RegLicQualOwnershipViewModel(IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        remoteServiceJndiName = "radiologistmodule.service.RegLicQualOwnershipServiceRemote";
        selectedEntity = new RegLicQualOwnershipDTO();
        entityList = ObservableCollections.observableList(new ArrayList<RegLicQualOwnershipDTO>());
        regLicQualList = ObservableCollections.observableList(new ArrayList<RegLicQualDTO>());
        loadRegLicQuals();
    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(RegLicQualOwnershipDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(RegLicQualOwnershipDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(RegLicQualOwnershipDTO.class);
    }

    @Override
    protected void doSaveAction() {

        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            newEntity = (RegLicQualOwnershipDTO) view.getEntity(RegLicQualOwnershipDTO.class);
            newEntity.setTdsRadiologist(new TDSRadiologistDTO(tdsRadiologistId));
            newEntity.Validate(bundle);
            if (modeHandler.getMode() == DisplayMode.Insert) {
                newEntity.setValidBegin(new Date());
                entityList.add(newEntity);
                view.setTableSelectionIndex(entityList.size() - 1, RegLicQualOwnershipDTO.class);
            } else {
                RegLicQualOwnershipDTO selectedItem = entityList.get(entityList.indexOf(selectedEntity));
                selectedItem.setActive(newEntity.isActive());
                selectedItem.setCertificateNumber(newEntity.getCertificateNumber());
                selectedItem.setRegLicQual(newEntity.getRegLicQual());
                selectedItem.setValidBegin(newEntity.getValidBegin());
                selectedItem.setValidEnd(newEntity.getValidEnd());
            }
            RegLicQualOwnershipServiceRemote service =
                    (RegLicQualOwnershipServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            long id = service.save(selectedEntity);
            selectedEntity.setId(id);
            view.saveEntity(RegLicQualOwnershipDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (ConstraintException ex) {
            Logger.getLogger(RegLicQualOwnershipViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegLicQualOwnershipViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadRegLicQuals() {
        try {
            RegLicQualServiceRemote regLicQualService = (RegLicQualServiceRemote) new InitialContext().lookup("masterdatamodule.service.RegLicQualServiceRemote");
            List<RegLicQualDTO> res = regLicQualService.findAll();
            regLicQualList.addAll(res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(RegLicQualOwnershipViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(RegLicQualOwnershipViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(RegLicQualOwnershipViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<RegLicQualDTO> getRegLicQualList() {
        return regLicQualList;
    }

    public void setRegLicQualList(ObservableList<RegLicQualDTO> regLicQualList) {
        this.regLicQualList = regLicQualList;
    }

    public ObservableList<RegLicQualOwnershipDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<RegLicQualOwnershipDTO> entityList) {
        ObservableList<RegLicQualOwnershipDTO> oldValue = this.entityList;
        this.entityList = entityList;
        propertyChangeSupport.firePropertyChange("entityList", oldValue, this.entityList);
    }

    public RegLicQualOwnershipDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(RegLicQualOwnershipDTO newEntity) {
        RegLicQualOwnershipDTO oldValue = this.newEntity;
        this.newEntity = newEntity;
        propertyChangeSupport.firePropertyChange("newEntity", oldValue, this.newEntity);
    }

    public RegLicQualOwnershipDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(RegLicQualOwnershipDTO selectedEntity) {
        RegLicQualOwnershipDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }

    
}
