/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author vincze.attila
 */
public abstract class EditViewModelBase<T extends BaseDTO> extends ViewModelBase {

    protected String message;
    protected Color messageColor;
    protected T selectedEntity;
    protected T newEntity;
    protected String remoteServiceJndiName;
    protected DisplayModeHandler modeHandler;
    protected IEditViewBase<T> view;
    protected String title;

    //actions
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;

    protected abstract void doEditAction();

    protected abstract void doSaveAction();

    protected abstract void doCancelAction();

    public EditViewModelBase(final IEditViewBase<T> view, final ResourceBundle bundle, String remoteServiceJndiName) {
        super(bundle);
        this.view = view;
        this.remoteServiceJndiName = remoteServiceJndiName;
        messageColor = new Color(255, 255, 255);
        modeHandler = new DisplayModeHandler();

        //actions
        editAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                //view.EditEntity();
                modeHandler.setMode(DisplayMode.Edit);
                doEditAction();
            }
        };

        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("saveItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        return;
                    }
                    selectedEntity.Validate(bundle);
                    //newEntity = view.GetEntity();
                    doSaveAction();
                    //view.SaveEntity();
                    modeHandler.setMode(DisplayMode.View);
                } catch (ConstraintException ex) {
                    Logger.getLogger(EditViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog((Component) view, ex.getMessage());
                }
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("cancelItem"), "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
                    return;
                }
                //view.CancelEntity();
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
        String oldValue = this.message;
        this.message = message;
        propertyChangeSupport.firePropertyChange("message", oldValue, this.message);
    }

    public Color getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(Color messageColor) {
        Color oldValue = this.messageColor;
        this.messageColor = messageColor;
        propertyChangeSupport.firePropertyChange("messageColor", oldValue, this.messageColor);
    }

    public DisplayModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(DisplayModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public T getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(T newEntity) {
        this.newEntity = newEntity;
    }

    public String getRemoteServiceJndiName() {
        return remoteServiceJndiName;
    }

    public void setRemoteServiceJndiName(String remoteServiceJndiName) {
        this.remoteServiceJndiName = remoteServiceJndiName;
    }

    public Action getSaveAction() {
        return saveAction;
    }

    public void setSaveAction(Action saveAction) {
        this.saveAction = saveAction;
    }

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        T oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
