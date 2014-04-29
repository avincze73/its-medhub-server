/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import event.ShowWaitingCursorEvent;
import event.ITSEventManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;
import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.error.ErrorInfo;

/**
 *
 * @author vincze.attila
 */
public abstract class TDSListEditViewModel<T extends BaseDTO> extends ViewModelBase {

    protected abstract class Worker extends SwingWorker<T, Void> {

        protected abstract T save() throws NamingException;

        @Override
        protected T doInBackground() throws Exception {
            ITSEventManager.fireEvent(new ShowWaitingCursorEvent(true, "Mentés..."));
            ((JInternalFrame) view).getGlassPane().setVisible(true);
            return save();
        }

        @Override
        protected void done() {
            try {
                super.done();
                T updated = get();
                setEditedEntity(null);
                setEditedEntity(updated);
                //editedEntity.setId(updated.getId());
                if (modeHandler.getMode().equals(DisplayMode.Insert)) {
                    entityList.add(editedEntity);
                    setSelectedIndex(entityList.size() - 1);
                } else {
                    entityList.set(entityList.indexOf(selectedEntity), editedEntity);
                }
                modeHandler.setMode(DisplayMode.View);
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExecutionException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                ITSEventManager.fireEvent(new ShowWaitingCursorEvent(false));
                ((JInternalFrame) view).getGlassPane().setVisible(false);
            }

        }
    }
    protected DisplayModeHandler modeHandler;
    protected String remoteServiceJndiName;
    protected Integer selectedIndex;
    //actions
    private Action newAction;
    private Action deleteAction;
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;
    protected ITDSListEditView view;
    protected T selectedEntity;
    protected T editedEntity;
    protected ObservableList<T> entityList;
    protected long ownerId;

    protected abstract void doSaveAction();

    protected abstract void doEditAction();

    public TDSListEditViewModel(ResourceBundle bundle, final ITDSListEditView view, String remoteServiceJndiName) {
        super(bundle);
        this.view = view;
        this.remoteServiceJndiName = remoteServiceJndiName;
        entityList = ObservableCollections.observableList(new ArrayList<T>());
        modeHandler = new DisplayModeHandler();
        selectedIndex = null;
        newAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Új?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
                setSelectedIndex(-1);
                modeHandler.setMode(DisplayMode.Insert);
            }
        };

        deleteAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doDeleteAction();
            }
        };


        editAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doEditAction();
                modeHandler.setMode(DisplayMode.Edit);
            }
        };

        saveAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Mentés?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
                try {
                    editedEntity.validate();
                    doSaveAction();
                } catch (ConstraintException ex) {
                    Logger.getLogger(TDSListEditViewModel.class.getName()).log(Level.SEVERE, null, ex);
                    JXErrorPane.showDialog((Component) view, new ErrorInfo("Kötelező mezők!", ex.getMessage(), null, "ConstraintError", ex, Level.ALL, null));
                }
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, "Mégsem?", "", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                    return;
                }
                if (modeHandler.getMode().equals(DisplayMode.Insert)) {
                    setSelectedIndex(entityList.size() - 1);
                } else {
                    setEditedEntity(null);
                    setEditedEntity(selectedEntity);
                }
                modeHandler.setMode(DisplayMode.View);
            }
        };

    }

    protected void doNewAction() {
    }

    protected void doDeleteAction() {
    }

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

    public Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(Integer selectedIndex) {
        Integer oldValue = this.selectedIndex;
        this.selectedIndex = selectedIndex;
        propertyChangeSupport.firePropertyChange("selectedIndex", oldValue, this.selectedIndex);
    }

    public T getEditedEntity() {
        return editedEntity;
    }

    public void setEditedEntity(T editedEntity) {
        T oldValue = this.editedEntity;
        this.editedEntity = editedEntity;
        propertyChangeSupport.firePropertyChange("editedEntity", oldValue, this.editedEntity);
    }

    public ObservableList<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<T> entityList) {
        this.entityList = entityList;
    }

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        T oldValue = this.selectedEntity;
        this.selectedEntity = selectedEntity;
        propertyChangeSupport.firePropertyChange("selectedEntity", oldValue, this.selectedEntity);
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    
}
