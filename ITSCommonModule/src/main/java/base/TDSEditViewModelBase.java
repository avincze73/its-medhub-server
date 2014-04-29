/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author vincze.attila
 */
public abstract class TDSEditViewModelBase extends ViewModelBase {

    protected String message;
    protected DisplayModeHandler modeHandler;
    protected DisplayModeHandler oldModeHandler;
    protected String title;
    //actions
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;

    protected abstract void doEditAction();

    protected void doSaveAction(){

    }

    protected void doSave() {
    }

    protected abstract void doCancelAction();

    public TDSEditViewModelBase(ResourceBundle bundle) {
        super(bundle);
        modeHandler = new DisplayModeHandler();
        oldModeHandler = new DisplayModeHandler();

        //actions
        editAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                //view.EditEntity();
                doEditAction();
                modeHandler.setMode(DisplayMode.Edit);
            }
        };

        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doSaveAction();
                //modeHandler.setMode(DisplayMode.View);
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doCancelAction();
                modeHandler.setMode(DisplayMode.View);
            }
        };
    }

    public Action getCancelAction() {
        return cancelAction;
    }

    public void setCancelAction(Action cancelAction) {
        this.cancelAction = cancelAction;
    }

    public Action getEditAction() {
        return editAction;
    }

    public void setEditAction(Action editAction) {
        this.editAction = editAction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DisplayModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(DisplayModeHandler modeHandler) {
        DisplayModeHandler oldValue = this.modeHandler;
        this.modeHandler = modeHandler;
        propertyChangeSupport.firePropertyChange("modeHandler", oldValue, this.modeHandler);
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldValue, this.title);
    }

    public DisplayModeHandler getOldModeHandler() {
        return oldModeHandler;
    }

    public void setOldModeHandler(DisplayModeHandler oldModeHandler) {
        DisplayModeHandler oldValue = this.oldModeHandler;
        this.oldModeHandler = oldModeHandler;
        propertyChangeSupport.firePropertyChange("oldModeHandler", oldValue, this.oldModeHandler);
    }
}
