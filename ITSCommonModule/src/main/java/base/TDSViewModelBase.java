/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;
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
public class TDSViewModelBase<T extends BaseDTO> extends ViewModelBase {

    protected ITDSBaseView<T> view;
    protected DisplayModeHandler modeHandler;
    protected IRemoteService<T> remoteService;
    protected String remoteServiceJndiName;
    //properties
    protected String searchName;
    protected String searchHungarianName;
    protected String searchEnglishName;
    protected String message;
    protected Color messageColor;
    protected ObservableList<T> entityList;
    protected T selectedEntity;
    protected T newEntity;
    protected T transientEntity;
    //actions
    private Action newAction;
    private Action deleteAction;
    private Action editAction;
    private Action saveAction;
    private Action cancelAction;
    private Action findAction;

    protected void doNewAction() {
    }

    protected void doDeleteAction() {
    }

    protected void doEditAction() {
    }

    protected void doSaveAction() throws PropertyVetoException {
    }

    protected void doCancelAction() {
    }

    protected void doFindAction() throws TooManyResultsException, ZeroResultException {
        List<T> res = remoteService.findByName(searchName);
        entityList.addAll(res);
        setMessageColor(Color.BLACK);
        if (searchName != null && !"".equals(searchName)) {
            setMessage(bundle.getString("lastSearch") + " " + bundle.getString("fldName") + "= " + searchName);
        }
    }

    public TDSViewModelBase(final ITDSBaseView<T> view, final String remoteServiceJndiName, ResourceBundle resourceBundle) {
        super(resourceBundle);
        this.view = view;
        messageColor = new Color(255, 255, 255);
        this.remoteServiceJndiName = remoteServiceJndiName;
        modeHandler = new DisplayModeHandler();
        entityList = ObservableCollections.observableList(new ArrayList<T>());
        //actions
        newAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                view.NewEntity();
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
                    view.FindEntities();
                    //view.setTableSelectionIndex(index - 1);
                    doDeleteAction();
                } catch (NamingException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        editAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (entityList.size() == 0) {
                    return;
                }
                view.EditEntity();
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
                    remoteService = (IRemoteService<T>) new InitialContext().lookup(remoteServiceJndiName);
                    newEntity = view.GetEntity();
                    if (modeHandler.getMode() == DisplayMode.Insert) {
                        entityList.add(newEntity);
                        view.setTableSelectionIndex(entityList.size() - 1);
                    } else {
                        doSaveAction();
                    }
                    long id = remoteService.save(selectedEntity);
                    selectedEntity.setId(id);
                    view.SaveEntity();
                    modeHandler.setMode(DisplayMode.View);
                    setMessage(bundle.getString("tableChanged"));
                    setMessageColor(Color.black);
                } catch (ConstraintException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog((Component) view, ex.getMessage());
                } catch (NamingException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        cancelAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("cancelItem"), "", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION) {
                    return;
                }
                if (modeHandler.getMode() == DisplayMode.Insert) {
                    view.setTableSelectionIndex(entityList.size() - 1);
                }
//                } else {
//                    doCancelAction();
//                }

                view.CancelEntity();
                modeHandler.setMode(DisplayMode.View);

            }
        };

        findAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    remoteService = (IRemoteService<T>) new InitialContext().lookup(remoteServiceJndiName);
                    entityList.clear();
                    view.FindEntities();
                    setMessageColor(Color.BLACK);
                    setMessage(bundle.getString("allRecords"));
                    doFindAction();
                    view.setTableSelectionIndex(0);
                } catch (TooManyResultsException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(bundle.getString("msgTooManyResults"));
                    setMessageColor(Color.RED);
                } catch (ZeroResultException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(bundle.getString("msgZeroResult"));
                    setMessageColor(Color.RED);
                } catch (NamingException ex) {
                    Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        loadAllEntities();

    }

    public void sortEntityList(int index) {
    }

    private void loadAllEntities() {
        try {
            remoteService = (IRemoteService<T>) new InitialContext().lookup(remoteServiceJndiName);
            List<T> res = remoteService.findAll();
            entityList.addAll(res);
            setMessage(bundle.getString("allRecords"));
            setMessageColor(Color.black);
        } catch (TooManyResultsException ex) {
            this.setMessage(bundle.getString("msgTooManyResults"));
            this.setMessageColor(Color.RED);
        } catch (ZeroResultException ex) {
            this.setMessage(bundle.getString("msgZeroResult"));
            this.setMessageColor(Color.RED);
        } catch (NamingException ex) {
            Logger.getLogger(TDSViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DisplayModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(DisplayModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        String oldValue = this.searchName;
        this.searchName = searchName;
        propertyChangeSupport.firePropertyChange("searchName", oldValue, this.searchName);
    }

    public String getSearchEnglishName() {
        return searchEnglishName;
    }

    public void setSearchEnglishName(String searchEnglishName) {
        String oldValue = this.searchEnglishName;
        this.searchEnglishName = searchEnglishName;
        propertyChangeSupport.firePropertyChange("searchEnglishName", oldValue, this.searchEnglishName);
    }

    public String getSearchHungarianName() {
        return searchHungarianName;
    }

    public void setSearchHungarianName(String searchHungarianName) {
        String oldValue = this.searchHungarianName;
        this.searchHungarianName = searchHungarianName;
        propertyChangeSupport.firePropertyChange("searchHungarianName", oldValue, this.searchHungarianName);
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

    public Action getFindAction() {
        return findAction;
    }

    public void setFindAction(Action findAction) {
        this.findAction = findAction;
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
