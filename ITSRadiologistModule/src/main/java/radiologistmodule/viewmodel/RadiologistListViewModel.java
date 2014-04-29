/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package radiologistmodule.viewmodel;

import base.DisplayMode;
import base.TDSListViewModelBase;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import event.EditRadiologistEvent;
import event.ITSEventManager;
import event.UpdateRadiologistInListEvent;
import event.UpdateRadiologistInListEventListener;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import radiologistmodule.dto.TDSRadiologistDTO;
import radiologistmodule.iview.IRadiologistListView;
import radiologistmodule.service.RadiologistServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class RadiologistListViewModel extends TDSListViewModelBase
        implements UpdateRadiologistInListEventListener {

    private final IRadiologistListView view;
    private String searchName;
    private RadiologistServiceRemote service;
    protected ObservableList<TDSRadiologistDTO> entityList;
    protected TDSRadiologistDTO selectedEntity;

    public RadiologistListViewModel(IRadiologistListView view) {
        super(java.util.ResourceBundle.getBundle("radiologistmodule/bundle/RadiologistListBundle"));
        this.view = view;
        entityList = ObservableCollections.observableList(new ArrayList<TDSRadiologistDTO>());
        remoteServiceJndiName =  "radiologistmodule.service.RadiologistServiceRemote";
        lastSearchMessage = "<html>"
                + bundle.getString("searchName") + " <i>\"%s\"</i></html>";
        setTitle(bundle.getString("radiologistList"));
        ITSEventManager.getEventListenerList().add(UpdateRadiologistInListEventListener.class, this);
        loadTdsRadiologists();
    }

    @Override
    protected void doNewAction() {
        if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("newItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        ITSEventManager.fireEditRadiologistEvent(new EditRadiologistEvent(new TDSRadiologistDTO()));
    }

    @Override
    protected void doDeleteAction() {
        if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("deleteItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            service = (RadiologistServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            service.delete(selectedEntity.getId());
            entityList.remove(selectedEntity);
        } catch (NamingException ex) {
            Logger.getLogger(RadiologistListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doSelectAction() {
        ITSEventManager.fireEditRadiologistEvent(new EditRadiologistEvent(selectedEntity));
    }

    @Override
    protected void doFindAction() throws TooManyResultsException, ZeroResultException {
//        try {
//            service = (RadiologistServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
//            List<TDSRadiologistDTO> res = service.findByName(searchName);
//            entityList.addAll(res);
//            listModeHandler.setMode(DisplayMode.View);
//            view.setTableSelectionIndex(0);
//            setMessage(String.format(lastSearchMessage, searchName == null ? "" : searchName));
//        } catch (NamingException ex) {
//            Logger.getLogger(RadiologistListViewModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    
    private void loadTdsRadiologists() {
        try {
            service = (RadiologistServiceRemote) new InitialContext().lookup(remoteServiceJndiName);
            List<TDSRadiologistDTO> res = service.findAll();
            entityList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (TooManyResultsException ex) {
            this.setMessage(tooManyResultsMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (ZeroResultException ex) {
            this.setMessage(zeroResultMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (NamingException ex) {
            Logger.getLogger(RadiologistListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        String oldValue = this.searchName;
        this.searchName = searchName;
        propertyChangeSupport.firePropertyChange("searchName", oldValue, this.searchName);
    }

    public void eventOccured(UpdateRadiologistInListEvent event) {
        TDSRadiologistDTO dto = (TDSRadiologistDTO) event.getSource();
        if (!entityList.contains(dto)){
            entityList.add(dto);
        } else {
            int index = entityList.indexOf(dto);
            entityList.remove(dto);
            entityList.add(index, dto);
        }
    }

    public ObservableList<TDSRadiologistDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<TDSRadiologistDTO> entityList) {
        this.entityList = entityList;
    }

    public TDSRadiologistDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(TDSRadiologistDTO selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
    
    
}
