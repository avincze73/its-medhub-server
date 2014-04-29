/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public abstract class ListEditViewModelBase<T extends BaseDTO> extends ViewModelBase {

    protected IListEditViewBase view;
    protected ListActionModeHandler modeHandler;
    protected IRemoteService<T> remoteService;
    protected String remoteServiceJndiName;
    //properties
    protected ObservableList<T> entityList;
    protected T selectedEntity;
    protected T newEntity;
    //actions
    private Action newAction;
    private Action deleteAction;
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;

    protected void doNewAction() {
    }

    protected void doDeleteAction() {
    }

    protected void doEditAction() {
    }

    protected abstract void doSaveAction();

    protected void doCancelAction() {
    }

    public ListEditViewModelBase(final IListEditViewBase view,
            final String remoteServiceJndiName,
            ResourceBundle resourceBundle) {
        super(resourceBundle);
        this.view = view;
        this.remoteServiceJndiName = remoteServiceJndiName;
        modeHandler = new ListActionModeHandler();
        entityList = ObservableCollections.observableList(new ArrayList<T>());
        //actions
        newAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                view.newEntity(selectedEntity.getClass());
                modeHandler.setMode(DisplayMode.Insert);
                doNewAction();
            }
        };

        deleteAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (entityList.size() == 0) {
                        return;
                    }
                    if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("deleteItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        return;
                    }
                    if (selectedEntity == null) {
                        return;
                    }
                    remoteService = (IRemoteService<T>) new InitialContext().lookup(remoteServiceJndiName);
                    remoteService.delete(selectedEntity.getId());
                    int index = entityList.indexOf(selectedEntity);
                    entityList.remove(selectedEntity);
                    //view.findEntities(selectedEntity.getClass());
                    //view.setTableSelectionIndex(index - 1);
                    doDeleteAction();
                } catch (NamingException ex) {
                    Logger.getLogger(ListEditViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        editAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (entityList.size() == 0) {
                    return;
                }
                view.editEntity(selectedEntity.getClass());
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

                    newEntity = (T) view.getEntity(selectedEntity.getClass());
                    newEntity.Validate(bundle);
                    if (modeHandler.getMode() == DisplayMode.Insert) {
                        entityList.add(newEntity);
                        view.setTableSelectionIndex(entityList.size() - 1, selectedEntity.getClass());
                    } else {
                        doSaveAction();
                    }
                    remoteService = (IRemoteService<T>) new InitialContext().lookup(remoteServiceJndiName);
                    long id = remoteService.save(selectedEntity);
                    selectedEntity.setId(id);
                    view.saveEntity(selectedEntity.getClass());
                    modeHandler.setMode(DisplayMode.View);
                } catch (ConstraintException ex) {
                    Logger.getLogger(ListEditViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog((Component) view, ex.getMessage());
                } catch (NamingException ex) {
                    Logger.getLogger(ListEditViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("cancelItem"), "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
                    return;
                }
                if (modeHandler.getMode() == DisplayMode.Insert) {
                    view.setTableSelectionIndex(entityList.size() - 1, selectedEntity.getClass());
                }
//                } else {
//                    doCancelAction();
//                }

                view.cancelEntity(selectedEntity.getClass());
                modeHandler.setMode(DisplayMode.View);

            }
        };

    }

   

    public ListActionModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(ListActionModeHandler modeHandler) {
        this.modeHandler = modeHandler;
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
}
