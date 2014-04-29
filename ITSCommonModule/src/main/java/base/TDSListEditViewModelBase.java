/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author vincze.attila
 */
public abstract class TDSListEditViewModelBase extends ViewModelBase {

    protected DisplayModeHandler modeHandler;
    protected String remoteServiceJndiName;
    protected int selectedIndex;
    //actions
    private Action newAction;
    private Action deleteAction;
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;

    public TDSListEditViewModelBase(ResourceBundle bundle) {
        super(bundle);
        modeHandler = new DisplayModeHandler();
        //actions
        newAction = new AbstractAction()    {

            public void actionPerformed(ActionEvent e) {
                doNewAction();
                modeHandler.setMode(DisplayMode.Insert);
            }
        };

        deleteAction = new AbstractAction()    {

            public void actionPerformed(ActionEvent e) {
                doDeleteAction();
            }
        };

        editAction = new AbstractAction()    {

            public void actionPerformed(ActionEvent e) {
                doEditAction();
                modeHandler.setMode(DisplayMode.Edit);
            }
        };

        saveAction = new AbstractAction()    {

            public void actionPerformed(ActionEvent e) {
                doSaveAction();
                //modeHandler.setMode(DisplayMode.View);
            }
        };

        cancelAction = new AbstractAction()    {

            public void actionPerformed(ActionEvent e) {
                doCancelAction();
                
            }
        };

    }

    protected void doNewAction() {
    }

    protected void doDeleteAction() {
    }

    protected void doEditAction() {
    }

    protected abstract void doSaveAction();

    protected void doCancelAction() {
    }

    public DisplayModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(DisplayModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public Action getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(Action cancelAction) {
        this.cancelAction = cancelAction;
    }

    public Action getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(Action deleteAction) {
        this.deleteAction = deleteAction;
    }

    public Action getEditAction() {
        return editAction;
    }

    public void setEditAction(Action editAction) {
        this.editAction = editAction;
    }

    public Action getNewAction() {
        return newAction;
    }

    public void setNewAction(Action newAction) {
        this.newAction = newAction;
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        int oldValue = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        propertyChangeSupport.firePropertyChange("selectedIndex", oldValue, this.selectedIndex);
    }
}
