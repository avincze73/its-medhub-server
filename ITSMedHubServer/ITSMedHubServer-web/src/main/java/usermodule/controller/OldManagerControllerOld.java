/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import common.ITSCriteria;
import common.QueryObject;
import common.controller.MenuController;
import common.controller.NavigationData;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import common.DisplayMode;
import common.ModeHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.richfaces.component.UIExtendedDataTable;
import usermodule.entity.ITSManager;
import usermodule.service.UserService;

/**
 *
 * @author vincze.attila
 */
//@Named
//@SessionScoped
public class OldManagerControllerOld implements Serializable, Cloneable {

    @EJB
    private UserService userService;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(OldManagerControllerOld.class);
    private List<ITSManager> entityList;
    private ITSManager selectedEntity;
    private ITSManager originalEntity;
    private Collection<Object> selection;
    private Long selectedId;
    private String title1;
    private String title2;
    private boolean saving;
    private String searchName;
    private int scrollHeight = 5;
    private String tableTitle;
    private String searchCriteria;
    //private UIExtendedDataTable dataTable;
    //password modification
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
    @Inject
    private ModeHandler modeHandler;

    public OldManagerControllerOld() {
        title1 = "ITS MANAGER LIST";
        title2 = "";
        tableTitle = "All";
        //searchCriteria = "aaa";
    }

    @PostConstruct
    public void init() {
        if (entityList == null) {
            try {
                setEntityList(userService.getITSManagerList(new QueryObject()));
                if (!entityList.isEmpty()) {
                    setSelectedEntity(entityList.get(0));
                    System.out.println(selectedEntity.getItsUser().getLoginName());
                }
            } catch (TooManyResultsException ex) {
                Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ZeroResultException ex) {
                Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String select(ITSManager manager) throws IOException {
        log.info(manager);

        menuController.getNavigationStack().push(new NavigationData("itsmanager - " + getSelectedEntity().getItsUser().getName(), "managerEdit2"));
        menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
        //setSelectedEntity(manager);
        setTitle1("ITS MANAGER");
        setTitle2(getSelectedEntity().getItsUser().getName());
        FacesContext.getCurrentInstance().getExternalContext().redirect(menuController.getNavigationStack().peek().getLastPage() + ".jsf");
        return null;//menuController.getNavigationStack().peek().getLastPage() + "?faces-redirect=true";
    }

    public String insert() {
        log.info("inserting...");
        menuController.getNavigationStack().push(new NavigationData("itsmanager - new", "managerEdit2"));
        menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
        setSelectedEntity(new ITSManager());
        setTitle1("ITS MANAGER");
        setTitle2("New...");
        modeHandler.setMode(DisplayMode.Insert);
        return menuController.getNavigationStack().peek().getLastPage();
    }

    public List<ITSManager> getEntityList() {
        if (entityList == null) {
            setEntityList(new ArrayList<ITSManager>());
        }
//        if (entityList == null) {
//            try {
//                setEntityList(userService.getITSManagerList(new QueryObject()));
//                //getEntityList().addAll(userService.getITSManagerList(new QueryObject()));
//                if (!entityList.isEmpty()) {
//                    setSelectedEntity(entityList.get(0));
//                    System.out.println(selectedEntity.getItsUser().getLoginName());
//                }
//
//            } catch (TooManyResultsException ex) {
//                Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ZeroResultException ex) {
//                Logger.getLogger(ManagerController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return entityList;
    }


    public void selectionListener(AjaxBehaviorEvent event) {

        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                setSelectedEntity((ITSManager) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
    }

    
    public void refresh() {
    }


    public void edit() {
        try {
            originalEntity = selectedEntity.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
        }
        modeHandler.setMode(DisplayMode.Edit);
    }

    public String back() {
        menuController.getNavigationStack().pop();
        log.info(menuController.getNavigationStack().peek().getLastPage());
        menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
        return menuController.getNavigationStack().peek().getLastPage();
    }

    public void save() {
        log.info("saving");
        setSelectedEntity(userService.saveITSManager(selectedEntity));
        if (!entityList.contains(selectedEntity)) {
            entityList.add(selectedEntity);
        } else {
            int index = entityList.indexOf(selectedEntity);
            entityList.remove(selectedEntity);
            entityList.add(index, selectedEntity);
        }
        modeHandler.setMode(DisplayMode.View);
    }

    public void cancel() {
        String res = null;
        log.info("canceling");
        if (modeHandler.getMode() == DisplayMode.Insert) {
            try {
                menuController.getNavigationStack().pop();
                menuController.setNavigationPath(menuController.getNavigationStack().peek().getNavigationPath());
                FacesContext.getCurrentInstance().getExternalContext().redirect(menuController.getNavigationStack().peek().getLastPage() + ".jsf");
            } catch (IOException ex) {
                Logger.getLogger(OldManagerControllerOld.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } else {
            setSelectedEntity(originalEntity);
            log.info(originalEntity.getItsUser().getSkype());
        }

        modeHandler.setMode(DisplayMode.View);
        log.info("canceling2");
    }

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
                log.info("...");
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

    @AssertTrue(message = "Different passwords entered!")
    public boolean isPasswordsEquals() {
        log.info(newPassword);
        log.info(actualPassword);
        return true;
        //return newPassword.equals(newPasswordAgain);
    }

    public void actualPasswordValidator(FacesContext facesContext,
            UIComponent uiComponent,
            Object newValue) {
        log.info(actualPassword);
        if (!"tds1".equals(actualPassword)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Input", "message"));
        }
    }

    public void modifyPassword() {
        log.info("modify password");
    }

    public void initPasswordModification() {
        log.info("initPasswordModification");
        setActualPassword("");
        setNewPassword("");
        setNewPasswordAgain("");
    }

    public void setEntityList(List<ITSManager> entityList) {
        this.entityList = entityList;
    }

    public ITSManager getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(ITSManager selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public ModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(ModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        log.info("setting");
        this.menuController = menuController;
    }

    public Collection<Object> getSelection() {
        return selection;
    }

    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }

    public ITSManager getOriginalEntity() {
        return originalEntity;
    }

    public void setOriginalEntity(ITSManager originalEntity) {
        this.originalEntity = originalEntity;
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

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public int getScrollHeight() {
        return scrollHeight;
    }

    public void setScrollHeight(int scrollHeight) {
        this.scrollHeight = scrollHeight;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getTableTitle() {
        return tableTitle;
    }

    public void setTableTitle(String tableTitle) {
        this.tableTitle = tableTitle;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
