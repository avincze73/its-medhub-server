/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
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
public abstract class TDSListViewModelBase extends ViewModelBase {

    protected String message;
    protected String title;
    protected String remoteServiceJndiName;
    protected String info;
    protected String searchCriteria;
    //actions
    protected Action newAction;
    protected Action findAllAction;
    protected Action deleteAction;
    protected Action findAction;
    protected Action selectAction;
    protected Action searchAction;
    protected Action getAllAction;
    protected String allRecordsMessage;
    protected String tooManyResultsMessage;
    protected String zeroResultMessage;
    protected String lastSearchMessage;
    protected ListActionModeHandler listModeHandler;

    protected void doNewAction() {
    }

    protected void doFindAllAction() {
    }

    protected void doDeleteAction() {
    }

    protected void doSelectAction() {
    }

    protected void doSearchAction() {
    }

    protected void doGetAllAction() {
    }

    protected void doFindAction() throws TooManyResultsException, ZeroResultException {
    }

    public TDSListViewModelBase(final ResourceBundle bundle) {
        super(bundle);
        listModeHandler = new ListActionModeHandler();
        allRecordsMessage = "<html><b>" + bundle.getString("allRecords") + "</html>";
        tooManyResultsMessage = "<html><font color=\"#FF0000\">"
                + bundle.getString("msgTooManyResults") + "</font></b></html>";
        zeroResultMessage = "<html><b><font color=\"#FF0000\">"
                + bundle.getString("msgZeroResult") + "</font>ccc</html>";

        //actions
        newAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doNewAction();
            }
        };

        findAllAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doFindAllAction();
            }
        };

        deleteAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doDeleteAction();
            }
        };

        getAllAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doGetAllAction();
            }
        };

        searchAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doSearchAction();
            }
        };

        findAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                try {
                    setMessage(allRecordsMessage);
                    doFindAction();
                } catch (TooManyResultsException ex) {
                    Logger.getLogger(TDSListViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(tooManyResultsMessage);
                    listModeHandler.setMode(DisplayMode.Empty);
                } catch (ZeroResultException ex) {
                    Logger.getLogger(TDSListViewModelBase.class.getName()).log(Level.SEVERE, null, ex);
                    setMessage(zeroResultMessage);
                    listModeHandler.setMode(DisplayMode.Empty);
                }
            }
        };

        selectAction = new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doSelectAction();
            }
        };

    }

    public String getAllRecordsMessage() {
        return allRecordsMessage;
    }

    public void setAllRecordsMessage(String allRecordsMessage) {
        this.allRecordsMessage = allRecordsMessage;
    }

    public Action getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(Action deleteAction) {
        this.deleteAction = deleteAction;
    }

    public Action getFindAction() {
        return findAction;
    }

    public void setFindAction(Action findAction) {
        this.findAction = findAction;
    }

    public String getLastSearchMessage() {
        return lastSearchMessage;
    }

    public void setLastSearchMessage(String lastSearchMessage) {
        this.lastSearchMessage = lastSearchMessage;
    }

    public ListActionModeHandler getListModeHandler() {
        return listModeHandler;
    }

    public void setListModeHandler(ListActionModeHandler listModeHandler) {
        this.listModeHandler = listModeHandler;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldValue = this.message;
        this.message = message;
        propertyChangeSupport.firePropertyChange("message", oldValue, this.message);
    }

    public Action getNewAction() {
        return newAction;
    }

    public void setNewAction(Action newAction) {
        this.newAction = newAction;
    }

    public Action getSelectAction() {
        return selectAction;
    }

    public void setSelectAction(Action selectAction) {
        this.selectAction = selectAction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        propertyChangeSupport.firePropertyChange("title", oldValue, this.title);
    }

    public String getTooManyResultsMessage() {
        return tooManyResultsMessage;
    }

    public void setTooManyResultsMessage(String tooManyResultsMessage) {
        this.tooManyResultsMessage = tooManyResultsMessage;
    }

    public String getZeroResultMessage() {
        return zeroResultMessage;
    }

    public void setZeroResultMessage(String zeroResultMessage) {
        this.zeroResultMessage = zeroResultMessage;
    }

    public String getRemoteServiceJndiName() {
        return remoteServiceJndiName;
    }

    public void setRemoteServiceJndiName(String remoteServiceJndiName) {
        this.remoteServiceJndiName = remoteServiceJndiName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        String oldValue = this.info;
        this.info = info;
        propertyChangeSupport.firePropertyChange("info", oldValue, this.info);
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        String oldValue = this.searchCriteria;
        this.searchCriteria = searchCriteria;
        propertyChangeSupport.firePropertyChange("searchCriteria", oldValue, this.searchCriteria);
    }

    public Action getFindAllAction() {
        return findAllAction;
    }

    public void setFindAllAction(Action findAllAction) {
        this.findAllAction = findAllAction;
    }

    public Action getSearchAction() {
        return searchAction;
    }

    public void setSearchAction(Action searchAction) {
        this.searchAction = searchAction;
    }

    public Action getGetAllAction() {
        return getAllAction;
    }

    public void setGetAllAction(Action getAllAction) {
        this.getAllAction = getAllAction;
    }

    
}
