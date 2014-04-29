/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.viewmodel;

import base.DisplayMode;
import base.TDSListViewModelBase;
import casemodule.dto.SystemMessageDTO;
import casemodule.dto.SystemMessageTypeDTO;
import casemodule.iview.ISystemMessageListView;
import casemodule.service.SystemMessageServiceRemote;
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
import usermodule.dto.UserDTO;
import usermodule.service.UserServiceRemote;

/**
 *
 * @author vincze.attila
 */
public class SystemMessageListViewModel extends TDSListViewModelBase {

    private final ISystemMessageListView view;
    private ObservableList<SystemMessageDTO> systemMessageList;
    private SystemMessageDTO selectedSystemMessage;
    private ObservableList<UserDTO> userList;
    private UserDTO selectedUser;
    private ObservableList<SystemMessageTypeDTO> systemMessageTypeList;
    private SystemMessageTypeDTO selectedSystemMessageType;

    public SystemMessageListViewModel(ISystemMessageListView view) {
        super(ResourceBundle.getBundle("casemodule/bundle/CaseModuleBundle"));
        this.view = view;
        setTitle(bundle.getString("caseList"));
        systemMessageList = ObservableCollections.observableList(new ArrayList<SystemMessageDTO>());
        userList = ObservableCollections.observableList(new ArrayList<UserDTO>());
        systemMessageTypeList = ObservableCollections.observableList(new ArrayList<SystemMessageTypeDTO>());
        loadUsers();
        loadSystemMessageTypes();
        loadSystemMessages();
    }

    private void loadUsers() {
        try {
            UserDTO emptyUser = new UserDTO(-1);
            emptyUser.setName("---");
            userList.add(emptyUser);
            //try {
                UserServiceRemote userService =
                        (UserServiceRemote) new InitialContext().lookup("usermodule.service.UserServiceRemote");
                //List<UserDTO> res = userService.getUserList();
                //userList.addAll(1, res);
    //        } catch (TooManyResultsException ex) {
    //            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
    //        } catch (ZeroResultException ex) {
    //            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
    //        } catch (NamingException ex) {
    //        }
    //        }
        } catch (NamingException ex) {
            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSystemMessageTypes() {
        SystemMessageTypeDTO emptyType = new SystemMessageTypeDTO(-1);
        emptyType.setMessageType("---");
        systemMessageTypeList.add(emptyType);
        try {
            SystemMessageServiceRemote service =
                    (SystemMessageServiceRemote) new InitialContext().lookup("casemodule.service.SystemMessageServiceRemote");
            List<SystemMessageTypeDTO> res = service.getSystemMessageTypeList();
            //systemMessageTypeList.clear();
            systemMessageTypeList.addAll(1, res);
        } catch (TooManyResultsException ex) {
            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SystemMessageListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSystemMessages() {
        try {
            SystemMessageServiceRemote service =
                    (SystemMessageServiceRemote) new InitialContext().lookup("casemodule.service.SystemMessageServiceRemote");
            List<SystemMessageDTO> res = service.getSystemMessageList(0, 0);
            systemMessageList.clear();
            systemMessageList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (TooManyResultsException ex) {
            this.setMessage(tooManyResultsMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (ZeroResultException ex) {
            this.setMessage(zeroResultMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            SystemMessageServiceRemote service =
                    (SystemMessageServiceRemote) new InitialContext().lookup("casemodule.service.SystemMessageServiceRemote");
            List<SystemMessageDTO> res = service.getSystemMessageList(
                    (selectedUser == null || selectedUser.getId() == -1) ? 0 : selectedUser.getId(),
                    (selectedSystemMessageType == null  || selectedSystemMessageType.getId() == -1) ? 0 : selectedSystemMessageType.getId());
            systemMessageList.clear();
            systemMessageList.addAll(res);
            setMessage(allRecordsMessage);
        } catch (TooManyResultsException ex) {
            this.setMessage(tooManyResultsMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (ZeroResultException ex) {
            this.setMessage(zeroResultMessage);
            listModeHandler.setMode(DisplayMode.Empty);
        } catch (NamingException ex) {
            Logger.getLogger(CaseListViewModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public SystemMessageDTO getSelectedSystemMessage() {
        return selectedSystemMessage;
    }

    public void setSelectedSystemMessage(SystemMessageDTO selectedSystemMessage) {
        this.selectedSystemMessage = selectedSystemMessage;
    }

    public SystemMessageTypeDTO getSelectedSystemMessageType() {
        return selectedSystemMessageType;
    }

    public void setSelectedSystemMessageType(SystemMessageTypeDTO selectedSystemMessageType) {
        this.selectedSystemMessageType = selectedSystemMessageType;
    }

    public UserDTO getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserDTO selectedUser) {
        this.selectedUser = selectedUser;
    }

    public ObservableList<SystemMessageDTO> getSystemMessageList() {
        return systemMessageList;
    }

    public void setSystemMessageList(ObservableList<SystemMessageDTO> systemMessageList) {
        this.systemMessageList = systemMessageList;
    }

    public ObservableList<SystemMessageTypeDTO> getSystemMessageTypeList() {
        return systemMessageTypeList;
    }

    public void setSystemMessageTypeList(ObservableList<SystemMessageTypeDTO> systemMessageTypeList) {
        this.systemMessageTypeList = systemMessageTypeList;
    }

    public ObservableList<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(ObservableList<UserDTO> userList) {
        this.userList = userList;
    }
}
