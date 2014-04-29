/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.ViewModelBase;
import casemodule.dto.DataProcLogEntryDTO;
import casemodule.iview.IDataProcLogView;
import casemodule.service.McCaseServiceRemote;
import event.HideProgressMessageEvent;
import event.TDSEventManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class DataProcLogViewModel extends ViewModelBase {

    private IDataProcLogView view;
    private ObservableList<DataProcLogEntryDTO> entityList;
    private DataProcLogEntryDTO selectedEntity;
    protected Integer selectedIndex;
    protected String allRecordsMessage;
    protected String tooManyResultsMessage;
    protected String zeroResultMessage;
    protected String lastSearchMessage;

    public DataProcLogViewModel(final IDataProcLogView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/DataProcLogBundle"));
        this.view = view;
        entityList = ObservableCollections.observableList(new ArrayList<DataProcLogEntryDTO>());
        allRecordsMessage = "Összes adatkezelési log";
        tooManyResultsMessage = "Túl sok adatkezelési log!";
        zeroResultMessage = "Nincs eredmény";
    }

    public void loadDataProcLog(long caseId) {
        try {
            McCaseServiceRemote service =
                    (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            List<DataProcLogEntryDTO> retList = service.getDataProcLogEntryList(caseId);
            entityList.clear();
            entityList.addAll(retList);
            TDSEventManager.fireHideProgressMessageEvent(new HideProgressMessageEvent(""));
        } catch (NamingException ex) {
            Logger.getLogger(DataProcLogViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex) {
        Integer oldValue = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        propertyChangeSupport.firePropertyChange("selectedIndex", oldValue, this.selectedIndex);
    }

    public ObservableList<DataProcLogEntryDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<DataProcLogEntryDTO> entityList) {
        this.entityList = entityList;
    }

    public DataProcLogEntryDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(DataProcLogEntryDTO selectedEntity) {
        DataProcLogEntryDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }
}
