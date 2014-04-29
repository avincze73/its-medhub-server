/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import common.DisplayMode;
import common.ITSCriteria;
import common.ITSListEditController;
import common.QueryObject;
import common.controller.NavigationData;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import common.service.EntityService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;
import usermodule.entity.ITSManager;
import usermodule.service.ManagerService;
import util.CryptographicUtil;

/**
 *
 * @author tds
 */
@Named
@SessionScoped
public class ManagerController extends ITSListEditController<ITSManager> {

    @EJB
    private ManagerService service;
    private String searchName;
    private String searchCriteria;
//    @NotNull(message="?")
    @Size(min = 1, message = "?")
    private String actualPassword;
//    @NotNull(message="?")
    @Size(min = 1, message = "?")
    private String newPassword;
//    @NotNull(message="?")
    @Size(min = 1, message = "?")
    private String newPasswordAgain;

    public ManagerController() {
        super(ITSManager.class);
        title1 = "ITSMANAGER LIST";
        title2 = "";
    }
    
    private boolean errorOccured;

    @Override
    public String insert() {
        menuController.getNavigationStack().push(new NavigationData("itsmanager - new", "/pages/user/managerEdit"));
        menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
        menuController.setTitle1("ITS MANAGER");
        menuController.setTitle2("new...");
        return super.insert();
    }

    @Override
    public String navigateToEdit() {
        try {
            menuController.getNavigationStack().push(new NavigationData("itsmanager - " + getSelectedEntity().getItsUser().getName(), "/pages/user/managerEdit"));
            menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
            menuController.setTitle1("ITS MANAGER");
            menuController.setTitle2(getSelectedEntity().getItsUser().getName());
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                    + menuController.getNavigationStack().peek().getLastPage() + ".jsf?faces-redirect=true");

        } catch (IOException ex) {
            Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;//men
    }

    @Override
    public void search() {
        try {
            log.info("do search...");
            getEntityList().clear();
            QueryObject qo = new QueryObject();
            if (searchName != null && !"".equals(searchName)) {
                qo.getCriteriaList().add(new ITSCriteria("name", "like", searchName));
            }
            setSelectedEntity(new ITSManager());
            setSelectedId(null);
            getEntityList().addAll(service.getList(qo));
            if (!entityList.isEmpty()) {
                setSelectedEntity(entityList.get(0));
                setSelectedId(getSelectedEntity().getId());
            } else {
                setSelectedEntity(new ITSManager());
            }

            log.info("end search...");
        } catch (TooManyResultsException ex) {
            Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void doConfirm()
    {
        log.info("confirmed");
        if (getModeHandler().getMode().equals(DisplayMode.Edit)){
            save();
        }
    }

    public void initPasswordModification() {
        log.info("initPasswordModification");
        setActualPassword("");
        setNewPassword("");
        setNewPasswordAgain("");
    }

    public void modifyPassword() {
        log.info("modify password");
        selectedEntity.getItsUser().setPassword(CryptographicUtil.create().getPasswordHash(newPassword));
        log.info(actualPassword);
        log.info(newPassword);
        log.info(newPasswordAgain);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully changed!", "Successfully changed!"));
    }

    public void createPassword() {
        log.info("create password");
        selectedEntity.getItsUser().setPassword(CryptographicUtil.create().getPasswordHash(newPassword));
        log.info(newPassword);
        log.info(newPasswordAgain);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully changed!", "Successfully changed!"));
    }

    public boolean hasPassword() {
        log.info("password: " + selectedEntity.getItsUser().getPassword());
        return selectedEntity.getItsUser().getPassword() != null;
    }
    
     @Override
    public void save() {
        if (!hasPassword()) {
            setErrorOccured(true);
            FacesContext.getCurrentInstance().addMessage("messagePopup",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "set password!", "set password!"));
            log.info(FacesContext.getCurrentInstance().getMessageList().get(0).getDetail());
        } else {
            setErrorOccured(false);
            super.save();
        }
    }

    @AssertTrue(message = "Different passwords entered!")
    public boolean isPasswordsEquals() {
        return newPassword.equals(newPasswordAgain);
    }

    @Override
    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected EntityService<ITSManager> getService() {
        return service;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getActualPassword() {
        return actualPassword;
    }

    public void setActualPassword(String actualPassword) {
        this.actualPassword = actualPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }

    public boolean isErrorOccured() {
        return errorOccured;
    }

    public void setErrorOccured(boolean errorOccured) {
        this.errorOccured = errorOccured;
    }
    
}
