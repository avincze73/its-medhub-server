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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import masterdatamodule.dto.CountryDTO;
import masterdatamodule.service.CountryServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.CompanyAssignmentDTO;
import radiologistmodule.dto.CompanyDTO;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistEditView;
import radiologistmodule.service.RadiologistServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class CompanyAssignmentViewModel extends TDSListEditViewModelBase {

    private IRadiologistEditView view;
    protected CompanyAssignmentDTO selectedEntity;
    protected CompanyAssignmentDTO newEntity;
    protected ObservableList<CompanyAssignmentDTO> entityList;
    protected ObservableList<CountryDTO> countryList;
    private ObservableList<CompanyDTO> companyList;
    private CompanyDTO selectedCompany;
    private CompanyDTO newCompany;
    private long tdsRadiologistId;
    //company management
    protected DisplayModeHandler companyModeHandler;
    private Action newCompanyAction;
    private Action editCompanyAction;
    private Action saveCompanyAction;
    private Action cancelCompanyAction;

    public CompanyAssignmentViewModel(final IRadiologistEditView view) {
        super(ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistEditBundle"));
        this.view = view;
        companyModeHandler = new DisplayModeHandler();
        remoteServiceJndiName = "radiologistmodule.service.RadiologistServiceRemote";
        selectedEntity = new CompanyAssignmentDTO();
        selectedCompany = new CompanyDTO();
        entityList = ObservableCollections.observableList(new ArrayList<CompanyAssignmentDTO>());
        countryList = ObservableCollections.observableList(new ArrayList<CountryDTO>());
        companyList = ObservableCollections.observableList(new ArrayList<CompanyDTO>());

        newCompanyAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                newCompany = new CompanyDTO();
                //newCompany.setCountry(countryList.get(0));
                setSelectedCompany(newCompany);
                companyModeHandler.setMode(DisplayMode.Insert);
                view.newEntity(CompanyDTO.class);

            }
        };


        editCompanyAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                companyModeHandler.setMode(DisplayMode.Edit);
                view.editEntity(CompanyDTO.class);

            }
        };

        saveCompanyAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        return;
                    }

                    //newCompany = (CompanyDTO) view.getEntity(CompanyDTO.class);
                    if (companyModeHandler.getMode() == DisplayMode.Insert) {
                        companyList.add(selectedCompany);
                        //setSelectedCompany(newCompany);
                    } else {
                        //setSelectedCompany(newCompany);
                        //CompanyDTO selectedItem = companyList.get(companyList.indexOf(selectedCompany));

                    }
                    RadiologistServiceRemote service =
                            (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
                    long id = service.saveCompany(selectedCompany);
                    selectedCompany.setId(id);
                    view.saveEntity(CompanyDTO.class);
                    companyModeHandler.setMode(DisplayMode.View);
                } catch (NamingException ex) {
                    Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        cancelCompanyAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                companyModeHandler.setMode(DisplayMode.View);
                view.cancelEntity(CompanyDTO.class);
            }
        };

        loadCountries();
        loadCompanies();
    }

    private void loadCompanies() {
        try {
            RadiologistServiceRemote service = (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
            List<CompanyDTO> res = service.getCompanyList();
            companyList.addAll(res);
        } catch (NamingException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCountries() {
        try {
            CountryServiceRemote service = (CountryServiceRemote) new InitialContext().lookup("masterdatamodule.service.CountryServiceRemote");
            List<CountryDTO> res = service.findAll();
            countryList.addAll(res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doCancelAction() {
        super.doCancelAction();
        view.cancelEntity(CompanyAssignmentDTO.class);
    }

    @Override
    protected void doDeleteAction() {
        super.doDeleteAction();
    }

    @Override
    protected void doEditAction() {
        super.doEditAction();
        view.editEntity(CompanyAssignmentDTO.class);
    }

    @Override
    protected void doNewAction() {
        super.doNewAction();
        view.newEntity(CompanyAssignmentDTO.class);
    }

    @Override
    protected void doSaveAction() {
        try {
            if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                return;
            }
            newEntity = (CompanyAssignmentDTO) view.getEntity(CompanyAssignmentDTO.class);
            newEntity.setTdsRadiologist(new TDSRadiologistDTO(tdsRadiologistId));
            if (modeHandler.getMode() == DisplayMode.Insert) {
                entityList.add(newEntity);
                view.setTableSelectionIndex(entityList.size() - 1, CompanyAssignmentDTO.class);
            } else {
                setSelectedEntity(newEntity);
                CompanyAssignmentDTO selectedItem = entityList.get(entityList.indexOf(selectedEntity));

            }
            RadiologistServiceRemote service =
                    (RadiologistServiceRemote) new InitialContext().lookup("radiologistmodule.service.RadiologistServiceRemote");
            long id = service.saveCompanyAssignment(selectedEntity);
            selectedEntity.setId(id);
            view.saveEntity(CompanyAssignmentDTO.class);
            modeHandler.setMode(DisplayMode.View);
        } catch (ConstraintException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CompanyAssignmentViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<CompanyDTO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(ObservableList<CompanyDTO> companyList) {
        this.companyList = companyList;
    }

    public ObservableList<CountryDTO> getCountryList() {
        return countryList;
    }

    public void setCountryList(ObservableList<CountryDTO> countryList) {
        this.countryList = countryList;
    }

    public ObservableList<CompanyAssignmentDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<CompanyAssignmentDTO> entityList) {
        this.entityList = entityList;
    }

    public CompanyDTO getNewCompany() {
        return newCompany;
    }

    public void setNewCompany(CompanyDTO newCompany) {
        CompanyDTO oldValue = this.newCompany;
        this.newCompany = newCompany;
        propertyChangeSupport.firePropertyChange("newCompany", oldValue, this.newCompany);
    }

    public CompanyAssignmentDTO getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(CompanyAssignmentDTO newEntity) {
        this.newEntity = newEntity;
    }

    public CompanyDTO getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(CompanyDTO selectedCompany) {
        CompanyDTO oldValue = this.selectedCompany;
        this.selectedCompany = selectedCompany;
        propertyChangeSupport.firePropertyChange("selectedCompany", oldValue, this.selectedCompany);
    }

    public CompanyAssignmentDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(CompanyAssignmentDTO selectedEntity) {
        CompanyAssignmentDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getTdsRadiologistId() {
        return tdsRadiologistId;
    }

    public void setTdsRadiologistId(long tdsRadiologistId) {
        this.tdsRadiologistId = tdsRadiologistId;
    }

    public Action getCancelCompanyAction() {
        return cancelCompanyAction;
    }

    public void setCancelCompanyAction(Action cancelCompanyAction) {
        this.cancelCompanyAction = cancelCompanyAction;
    }

    public DisplayModeHandler getCompanyModeHandler() {
        return companyModeHandler;
    }

    public void setCompanyModeHandler(DisplayModeHandler companyModeHandler) {
        this.companyModeHandler = companyModeHandler;
    }

    public Action getEditCompanyAction() {
        return editCompanyAction;
    }

    public void setEditCompanyAction(Action editCompanyAction) {
        this.editCompanyAction = editCompanyAction;
    }

    public Action getNewCompanyAction() {
        return newCompanyAction;
    }

    public void setNewCompanyAction(Action newCompanyAction) {
        this.newCompanyAction = newCompanyAction;
    }

    public Action getSaveCompanyAction() {
        return saveCompanyAction;
    }

    public void setSaveCompanyAction(Action saveCompanyAction) {
        this.saveCompanyAction = saveCompanyAction;
    }
}
