/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author vincze.attila
 */
public abstract class ListViewModelBase<T extends BaseDTO> extends ViewModelBase {

    protected String message;
    protected Color messageColor;
    protected ObservableList<T> entityList;
    protected T selectedEntity;
    protected String remoteServiceJndiName;
    protected IListViewBase<T> view;
    protected String title;
    //actions
    private Action newAction;
    private Action deleteAction;
    private Action findAction;
    private Action selectAction;
    protected String allRecordsMessage;
    protected String tooManyResultsMessage;
    protected String zeroResultMessage;
    protected String lastSearchMessage;
    protected ListActionModeHandler listModeHandler;


    protected abstract void doNewAction();

    protected abstract void doDeleteAction();

    protected abstract void doSelectAction();

    protected abstract void doFindAction() throws TooManyResultsException, ZeroResultException;

    protected abstract void doFindAllEntities() throws TooManyResultsException, ZeroResultException;

    public ListViewModelBase(final IListViewBase<T> view, final ResourceBundle bundle, String remoteServiceJndiName) {
        super(bundle);
        this.view = view;
        this.remoteServiceJndiName = remoteServiceJndiName;
        setMessageColor(Color.BLACK);
        entityList = ObservableCollections.observableList(new ArrayList<T>());
        listModeHandler = new ListActionModeHandler();
//        allRecordsMessage = "<html><b>" + bundle.getString("lastSearch") + ":</b> " +
//                bundle.getString("allRecords") + "</html>";
        allRecordsMessage = "<html>" + bundle.getString("allRecords") + "</html>";
//        tooManyResultsMessage = "<html><b>" + bundle.getString("lastSearch") + ":</b> <font color=\"#FF0000\">" +
//                bundle.getString("msgTooManyResults") + "</font></html>";
        tooManyResultsMessage = "<html><font color=\"#FF0000\">" +
                bundle.getString("msgTooManyResults") + "</font></html>";
//        zeroResultMessage = "<html><b>" + bundle.getString("lastSearch") + ":</b> <font color=\"#FF0000\">" +
//                bundle.getString("msgZeroResult") + "</font></html>";
        zeroResultMessage = "<html><font color=\"#FF0000\">" +
                bundle.getString("msgZeroResult") + "</font></html>";
        //actions
        newAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doNewAction();
            }
        };


        deleteAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (entityList.size() == 0) {
                    return;
                }
                if (selectedEntity == null) {
                    return;
                }
                if (JOptionPane.showConfirmDialog((Component) view, bundle.getString("deleteItem"), "", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                    return;
                }

                doDeleteAction();
            }
        };

        findAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    entityList.clear();
                    setMessageColor(Color.BLACK);
                    setMessage(allRecordsMessage);
                    doFindAction();
                } catch (TooManyResultsException ex) {
                    Logger.getLogger(ListViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(tooManyResultsMessage);
                    listModeHandler.setMode(DisplayMode.Empty);
                } catch (ZeroResultException ex) {
                    Logger.getLogger(ListViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(zeroResultMessage);
                    listModeHandler.setMode(DisplayMode.Empty);
                }
            }
        };

        selectAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                if (entityList.size() == 0) {
                    return;
                }
                doSelectAction();
            }
        };
        findAllEntities();
    }

    private void findAllEntities() {
        try {
            doFindAllEntities();
            setMessage(allRecordsMessage);
        } catch (TooManyResultsException ex) {
            this.setMessage(tooManyResultsMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (ZeroResultException ex) {
            this.setMessage(zeroResultMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        }
    }

    public Action getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(Action deleteAction) {
        this.deleteAction = deleteAction;
    }

    public ObservableList<T> getEntityList() {
        return entityList;
    }

    public void setEntityList(ObservableList<T> entityList) {
        this.entityList = entityList;
    }

    public Action getFindAction() {
        return findAction;
    }

    public void setFindAction(Action findAction) {
        this.findAction = findAction;
    }

    public Action getSelectAction() {
        return selectAction;
    }

    public void setSelectAction(Action selectAction) {
        this.selectAction = selectAction;
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

    public final void setMessageColor(Color messageColor) {
        Color oldValue = this.messageColor;
        this.messageColor = messageColor;
        propertyChangeSupport.firePropertyChange("messageColor", oldValue, this.messageColor);
    }

    public Action getNewAction() {
        return newAction;
    }

    public void setNewAction(Action newAction) {
        this.newAction = newAction;
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

    public ListActionModeHandler getListModeHandler() {
        return listModeHandler;
    }

    public void setListModeHandler(ListActionModeHandler listModeHandler) {
        this.listModeHandler = listModeHandler;
    }


    
}
