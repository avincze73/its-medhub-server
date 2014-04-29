/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reportingmodule.viewmodel;

import base.ViewModelBase;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import event.ITSEventManager;
import event.ShowWaitingCursorEvent;
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
import reportingmodule.dto.AutoFunctionLogEntryDTO;
import reportingmodule.iview.IAutoFunctionLogListView;
import reportingmodule.service.AutoFunctionLogServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class AutoFunctionLogListViewModel extends ViewModelBase {

    private IAutoFunctionLogListView view;
    private ObservableList<AutoFunctionLogEntryDTO> entityList;
    private AutoFunctionLogEntryDTO selectedEntity;
    private AutoFunctionLogEntryDTO detailedEntity;
    protected Integer selectedIndex;
    protected String info;
    protected String searchCriteria;
    protected String allRecordsMessage;
    protected String tooManyResultsMessage;
    protected String zeroResultMessage;
    protected String lastSearchMessage;
    protected Action findAllAction;
    protected Action findAction;

    public AutoFunctionLogListViewModel(final IAutoFunctionLogListView view) {
        super(ResourceBundle.getBundle("reportingmodule/bundle/AutoFunctionLogListBundle"));
        this.view = view;
        entityList = ObservableCollections.observableList(new ArrayList<AutoFunctionLogEntryDTO>());
        allRecordsMessage = "Összes autofunction log";
        tooManyResultsMessage = "Túl sok autofunction log!";
        zeroResultMessage = "Nincs eredmény";
        selectedIndex = 0;

        loadAutoFunctionLog();
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
        new SwingWorker<List<AutoFunctionLogEntryDTO>, Void>() {

            @Override
            protected List<AutoFunctionLogEntryDTO> doInBackground() throws Exception {
                ITSEventManager.fireEvent(new ShowWaitingCursorEvent(true, "Keresés..."));
                setInfo("Összes adatkezelési log");
                setSearchCriteria("");
                AutoFunctionLogServiceRemote service = (AutoFunctionLogServiceRemote) new InitialContext().lookup("reportingmodule.service.AutoFunctionLogServiceRemote");
                entityList.clear();
                List<AutoFunctionLogEntryDTO> res = service.getList();
                return res;
            }

            @Override
            protected void done() {
                try {
                    super.done();
                    ITSEventManager.fireEvent(new ShowWaitingCursorEvent(false));
                    List<AutoFunctionLogEntryDTO> res = get();
                    entityList.addAll(res);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AutoFunctionLogListViewModel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    if (ex.getCause() instanceof TooManyResultsException) {
                        setInfo(tooManyResultsMessage);
                    } else if (ex.getCause() instanceof ZeroResultException) {
                        setInfo(zeroResultMessage);
                    }
                    Logger.getLogger(AutoFunctionLogListViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.execute();
    }

    public final void loadAutoFunctionLog() {
        try {
            setInfo("");
            setSearchCriteria("");
            AutoFunctionLogServiceRemote service = (AutoFunctionLogServiceRemote) new InitialContext().lookup("reportingmodule.service.AutoFunctionLogServiceRemote");
            List<AutoFunctionLogEntryDTO> res = service.getList();
            entityList.clear();
            entityList.addAll(res);
            setInfo(allRecordsMessage);
        } catch (NamingException ex) {
            Logger.getLogger(AutoFunctionLogListViewModel.class.getName()).log(Level.SEVERE, null, ex);
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

    public ObservableList<AutoFunctionLogEntryDTO> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<AutoFunctionLogEntryDTO> entityList) {
        this.entityList = entityList;
    }

    public AutoFunctionLogEntryDTO getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(AutoFunctionLogEntryDTO selectedEntity) {
        AutoFunctionLogEntryDTO oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);

        if (selectedEntity == null) {
            setDetailedEntity(new AutoFunctionLogEntryDTO());
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

    public AutoFunctionLogEntryDTO getDetailedEntity() {
        return detailedEntity;
    }

    public void setDetailedEntity(AutoFunctionLogEntryDTO detailedEntity) {
        AutoFunctionLogEntryDTO oldValue = this.detailedEntity;
        this.detailedEntity = detailedEntity;
        propertyChangeSupport.firePropertyChange("detailedEntity", oldValue, this.detailedEntity);
    }
}
