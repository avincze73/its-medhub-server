/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import common.ITSCriteria;
import common.ITSListEditController;
import common.QueryObject;
import common.controller.MenuController;
import common.controller.NavigationData;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import common.service.EntityService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.richfaces.component.UIExtendedDataTable;
import usermodule.entity.ITSManager;
import usermodule.service.UserService;

/**
 *
 * @author itsadmin
 */
@Named
@SessionScoped
public class OldManagerControllerOld2 extends ITSListEditController<ITSManager> {

    @EJB
    private UserService userService;
    private String searchName;
    private String searchCriteria;
    @NotNull
    @Size(min = 1)
    private String actualPassword;
    @NotNull
    @Size(min = 1, message = "a")
    private String newPassword;
    @NotNull
    @Size(min = 1, message = "b")
    private String newPasswordAgain;
    @Inject
    private MenuController menuController;

    public OldManagerControllerOld2() {
        super(ITSManager.class);
        title1 = "ITSMANAGER LIST";
        title2 = "";
    }
    
    

    @PostConstruct
    public void init() {
        if (entityList == null) {
            try {
                setEntityList(userService.getITSManagerList(new QueryObject()));
                //getEntityList().addAll(userService.getITSManagerList(new QueryObject()));
                //getEntityList().remove(0);
                //getEntityList().remove(0);
                if (!entityList.isEmpty()) {
                    setSelectedEntity(entityList.get(0));
                    System.out.println(selectedEntity.getItsUser().getLoginName());
                }
            } catch (TooManyResultsException ex) {
                Logger.getLogger(OldManagerControllerOld2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ZeroResultException ex) {
                Logger.getLogger(OldManagerControllerOld2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<ITSManager> getEntityList() {
        if (entityList == null) {
            setEntityList(new ArrayList<ITSManager>());
        }
        return entityList;
    }

    public void selectionListener(AjaxBehaviorEvent event) {

        log.info("itt");
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                setSelectedEntity((ITSManager) dataTable.getRowData());
                log.info(originalKey);
            }
        }
        dataTable.setRowKey(originalKey);
    }

    @Override
    public void select() {
        log.info(selectedId);
        for (ITSManager iTSManager : entityList) {
            if (iTSManager.getId().equals(selectedId)) {
                setSelectedEntity(iTSManager);
            }
        }
    }

    @Override
    public String navigateToEdit() {
        try {
            menuController.getNavigationStack().push(new NavigationData("itsmanager - " + getSelectedEntity().getItsUser().getName(), "managerEdit2"));
            menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
            //setSelectedEntity(manager);
            setTitle1("ITS MANAGER");
            setTitle2(getSelectedEntity().getItsUser().getName());
            FacesContext.getCurrentInstance().getExternalContext().redirect(menuController.getNavigationStack().peek().getLastPage() + ".jsf");

        } catch (IOException ex) {
            Logger.getLogger(OldManagerControllerOld2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;//men
    }

    @Override
    public String insert() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void edit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("Not supported yet.");
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
            getEntityList().addAll(userService.getITSManagerList(qo));
            if (!entityList.isEmpty()) {
                setSelectedEntity(entityList.get(0));
            } else {
                setSelectedEntity(new ITSManager());
            }
        } catch (TooManyResultsException ex) {
            Logger.getLogger(OldManagerControllerOld2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZeroResultException ex) {
            Logger.getLogger(OldManagerControllerOld2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void refresh() {
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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

    @Override
    protected EntityService<ITSManager> getService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
