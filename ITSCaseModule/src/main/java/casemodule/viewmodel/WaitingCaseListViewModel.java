/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package casemodule.viewmodel;

import base.TDSListViewModelBase;
import casemodule.dto.CaseDTO;
import casemodule.iview.IWaitingCaseListView;
import casemodule.service.McCaseServiceRemote;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
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
public class WaitingCaseListViewModel extends TDSListViewModelBase{

    private final IWaitingCaseListView view;
    private ObservableList<CaseDTO> caseList;
    private McCaseServiceRemote service;

    public WaitingCaseListViewModel(IWaitingCaseListView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"));
        this.view = view;
        setTitle(bundle.getString("caseList"));
        caseList = ObservableCollections.observableList(new ArrayList<CaseDTO>());
        loadCases();
    }

    private void loadCases() {
        try {
            service = (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            List<CaseDTO> res = service.getWaitingCaseList();
            caseList.clear();
            caseList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (NamingException ex) {
            Logger.getLogger(WaitingCaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doNewAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doDeleteAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doSelectAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doFindAction() throws TooManyResultsException, ZeroResultException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public ObservableList<CaseDTO> getCaseList() {
        return caseList;
    }

    public void setCaseList(ObservableList<CaseDTO> caseList) {
        this.caseList = caseList;
    }

    
}
