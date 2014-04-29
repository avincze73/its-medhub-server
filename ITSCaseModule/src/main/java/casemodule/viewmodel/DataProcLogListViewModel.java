/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.ViewModelBase;
import casemodule.dto.DataProcLogEntryDTO;
import casemodule.iview.IDataProcLogListView;
import casemodule.service.McCaseServiceRemote;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import event.HideProgressMessageEvent;
import event.ShowWaitingCursorEvent;
import event.TDSEventManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingWorker;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class DataProcLogListViewModel extends ViewModelBase {

    private IDataProcLogListView view;
    private ObservableList<DataProcLogEntryDTO> entityList;
    private DataProcLogEntryDTO selectedEntity;
    private DataProcLogEntryDTO detailedEntity;
    protected Integer selectedIndex;
    protected String info;
    protected String searchCriteria;
    protected String allRecordsMessage;
    protected String tooManyResultsMessage;
    protected String zeroResultMessage;
    protected String lastSearchMessage;
    protected Action findAllAction;
    protected Action findAction;

    public DataProcLogListViewModel(final IDataProcLogListView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/DataProcLogListBundle"));
        this.view = view;
        entityList = ObservableCollections.observableList(new ArrayList<DataProcLogEntryDTO>());
        allRecordsMessage = "Összes adatkezelési log";
        tooManyResultsMessage = "Túl sok adatkezelési log!";
        zeroResultMessage = "Nincs eredmény";
        selectedIndex = 0;

        loadDataProcLog();
        findAllAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doFindAllAction();
            }
        };

        findAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
            }
        };

    }

    private void doFindAllAction() {
        new SwingWorker<List<DataProcLogEntryDTO>, Void>() {

            @Override
            protected List<DataProcLogEntryDTO> doInBackground() throws Exception {
                TDSEventManager.fireEvent(new ShowWaitingCursorEvent(true, "Keresés..."));
                setInfo("Összes adatkezelési log");
                setSearchCriteria("");
                McCaseServiceRemote service = (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
                entityList.clear();
                List<DataProcLogEntryDTO> res = service.getDataProcLogEntryList();
                return res;
            }

            @Override
            protected void done() {
                try {
                    super.done();
                    TDSEventManager.fireEvent(new ShowWaitingCursorEvent(false));
                    List<DataProcLogEntryDTO> res = get();
                    entityList.addAll(res);
                    //view.selectFirstRecord();
                } catch (InterruptedException ex) {
                    Logger.getLogger(DataProcLogListViewModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    if (ex.getCause() instanceof TooManyResultsException) {
                        setInfo(tooManyResultsMessage);
                    } else if (ex.getCause() instanceof ZeroResultException) {
                        setInfo(zeroResultMessage);
                    }
                    Logger.getLogger(DataProcLogListViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public final void loadDataProcLog() {
        try {
            setInfo("");
            setSearchCriteria("");
            McCaseServiceRemote service =
                    (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            List<DataProcLogEntryDTO> retList = service.getDataProcLogEntryList();
            entityList.clear();
            entityList.addAll(retList);
            setInfo(allRecordsMessage);
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

        if (selectedEntity == null) {
            setDetailedEntity(new DataProcLogEntryDTO());
        } else {
            setDetailedEntity(selectedEntity);
        }
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        String oldValue = this.info;
        this.info = info;
        propertyChangeSupport.firePropertyChange("info", oldValue, this.info);
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        String oldValue = this.searchCriteria;
        this.searchCriteria = searchCriteria;
        propertyChangeSupport.firePropertyChange("searchCriteria", oldValue, this.searchCriteria);
    }

    public Action getFindAction() {
        return findAction;
    }

    public void setFindAction(Action findAction) {
        this.findAction = findAction;
    }

    public Action getFindAllAction() {
        return findAllAction;
    }

    public void setFindAllAction(Action findAllAction) {
        this.findAllAction = findAllAction;
    }

    public DataProcLogEntryDTO getDetailedEntity() {
        return detailedEntity;
    }

    public void setDetailedEntity(DataProcLogEntryDTO detailedEntity) {
        DataProcLogEntryDTO oldValue = this.detailedEntity;
        this.detailedEntity = detailedEntity;
        propertyChangeSupport.firePropertyChange("detailedEntity", oldValue, this.detailedEntity);
    }
}
