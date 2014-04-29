/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.TDSEditViewModelBase;
import casemodule.dto.CaseDTO;
import casemodule.iview.ICaseEditView;
import casemodule.service.CaseServiceRemote;
import casemodule.service.McCaseServiceRemote;
import event.HideProgressMessageEvent;
import event.TDSEventManager;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author vincze.attila
 */
public class CaseEditViewModel extends TDSEditViewModelBase {

    private final ICaseEditView view;
    private CaseDTO tdsCase;

    public CaseEditViewModel(ICaseEditView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"));
        this.view = view;
        tdsCase = new CaseDTO();
    }

    @Override
    protected void doEditAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doSaveAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void doCancelAction() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void loadCase(CaseDTO selectedCaseDTO) {
        try {
            McCaseServiceRemote service =
                    (McCaseServiceRemote) new InitialContext().lookup("casemodule.service.McCaseServiceRemote");
            CaseDTO retCase = service.getCase(selectedCaseDTO.getId());
            setTdsCase(retCase);
            setTitle(tdsCase.getTdsCaseUniqueId());
            TDSEventManager.fireHideProgressMessageEvent(new HideProgressMessageEvent(""));
        } catch (NamingException ex) {
            Logger.getLogger(CaseEditViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CaseDTO getTdsCase() {
        return tdsCase;
    }

    public void setTdsCase(CaseDTO tdsCase) {
        CaseDTO oldValue = this.tdsCase;
        this.tdsCase = tdsCase;
        propertyChangeSupport.firePropertyChange("tdsCase", oldValue, this.tdsCase);
    }
}
