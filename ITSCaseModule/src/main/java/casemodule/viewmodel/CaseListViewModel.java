/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.TDSListViewModelBase;
import casemodule.dto.CaseDTO;
import casemodule.iview.ICaseListView;
import casemodule.service.McCaseServiceRemote;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import event.EditCaseEvent;
import event.ShowProgressMessageEvent;
import event.TDSEventManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import masterdatamodule.dto.CaseStatusDTO;
import masterdatamodule.service.CaseStatusServiceRemote;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public class CaseListViewModel extends TDSListViewModelBase {

    private void initActions(final ICaseListView view) {
        rejectAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedCase == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, "Reject?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }
                try {
                    service = (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
                    service.rejectCase(selectedCase.getId());
                    loadCases();
                } catch (NamingException ex) {
                    Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        dataProcLogAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (selectedCase == null) {
                    return;
                }
                TDSEventManager.fireShowProgressMessageEvent(new ShowProgressMessageEvent("Eset letöltése folyamatban..."));
                TDSEventManager.fireEditCaseEvent(new EditCaseEvent(getSelectedCase().getId()));
            }
        };
    }

    private class CaseListUpdater implements Runnable {

        public void run() {
            System.out.println(new Date());
            loadCases();
        }
    }
    private final ICaseListView view;
    private ObservableList<CaseDTO> caseList;
    protected Integer selectedIndex;
    private CaseDTO selectedCase;
    private McCaseServiceRemote service;
    private ObservableList<CaseStatusDTO> caseStatusList;
    private CaseStatusDTO selectedCaseStatus;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    private Action rejectAction;
    private Action dataProcLogAction;

    public CaseListViewModel(final ICaseListView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"));
        this.view = view;
        caseList = ObservableCollections.observableList(new ArrayList<CaseDTO>());
        caseStatusList = ObservableCollections.observableList(new ArrayList<CaseStatusDTO>());
        selectedIndex = 0;
        loadCaseStatuses();
        loadCases();
        initActions(view);

        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new CaseListUpdater(), 10, 10, TimeUnit.MINUTES);
    }

    private void loadCaseStatuses() {
        try {
            CaseStatusServiceRemote caseStatusService = (CaseStatusServiceRemote) new InitialContext().lookup("masterdatamodule.service.CaseStatusServiceRemote");
            List<CaseStatusDTO> res = caseStatusService.getList();
            caseStatusList.clear();
            caseStatusList.addAll(res);
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadCases() {
        try {
            service = (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            List<CaseDTO> res = service.getCaseList();
            caseList.clear();
            caseList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doSelectAction() {
        scheduledThreadPoolExecutor.shutdown();
        TDSEventManager.fireShowProgressMessageEvent(new ShowProgressMessageEvent("Eset letöltése folyamatban..."));
        TDSEventManager.fireEditCaseEvent(new EditCaseEvent(selectedCase));
    }

    @Override
    protected void doFindAction() throws TooManyResultsException, ZeroResultException {
        if (selectedCaseStatus == null) {
            return;
        }
        try {
            service = (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            List<CaseDTO> res = service.getCaseListByCaseStatus(selectedCaseStatus.getEnglishName());
            caseList.clear();
            caseList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<CaseDTO> getCaseList() {
        return caseList;
    }

    public void setCaseList(ObservableList<CaseDTO> caseList) {
        this.caseList = caseList;
    }

    public McCaseServiceRemote getService() {
        return service;
    }

    public void setService(McCaseServiceRemote service) {
        this.service = service;
    }

    public CaseDTO getSelectedCase() {
        return selectedCase;
    }

    public void setSelectedCase(CaseDTO selectedCase) {
        CaseDTO oldValue = this.selectedCase;
        this.selectedCase = selectedCase;
        propertyChangeSupport.firePropertyChange("selectedCase", oldValue, this.selectedCase);
    }

    public ObservableList<CaseStatusDTO> getCaseStatusList() {
        return caseStatusList;
    }

    public void setCaseStatusList(ObservableList<CaseStatusDTO> caseStatusList) {
        this.caseStatusList = caseStatusList;
    }

    public CaseStatusDTO getSelectedCaseStatus() {
        return selectedCaseStatus;
    }

    public void setSelectedCaseStatus(CaseStatusDTO selectedCaseStatus) {
        this.selectedCaseStatus = selectedCaseStatus;
    }

    public Action getDataProcLogAction() {
        return dataProcLogAction;
    }

    public void setDataProcLogAction(Action dataProcLogAction) {
        this.dataProcLogAction = dataProcLogAction;
    }

    public Action getRejectAction() {
        return rejectAction;
    }

    public void setRejectAction(Action rejectAction) {
        this.rejectAction = rejectAction;
    }

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex) {
        Integer oldValue = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        propertyChangeSupport.firePropertyChange("selectedIndex", oldValue, this.selectedIndex);
    }
}
