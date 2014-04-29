/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.controller;

import common.ITSListEditController;
import common.QueryObject;
import common.controller.MenuController;
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
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.richfaces.component.UIExtendedDataTable;
import usermodule.entity.HospitalStaff;
import usermodule.service.UserService;

/**
 *
 * @author itsadmin
 */
@Named
@SessionScoped
public class HospitalStaffController extends ITSListEditController<HospitalStaff> {

    @EJB
    private UserService userService;
    private String searchName;
    private String searchCriteria;
    @Inject
    private MenuController menuController;

    public HospitalStaffController() {
        super(HospitalStaff.class);
        title1 = "HOSPITALSTAFF LIST";
        title2 = "";
    }

    @PostConstruct
    public void init() {
        if (entityList == null) {
            try {
                setEntityList(userService.getHospitalStaffList(new QueryObject()));
                getEntityList().addAll(userService.getHospitalStaffList(new QueryObject()));
                getEntityList().addAll(userService.getHospitalStaffList(new QueryObject()));
                getEntityList().addAll(userService.getHospitalStaffList(new QueryObject()));
                getEntityList().addAll(userService.getHospitalStaffList(new QueryObject()));
                if (!entityList.isEmpty()) {
                    setSelectedEntity(entityList.get(0));
                    System.out.println(selectedEntity.getItsUser().getLoginName());
                }
            } catch (TooManyResultsException ex) {
                Logger.getLogger(HospitalStaffController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ZeroResultException ex) {
                Logger.getLogger(HospitalStaffController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<HospitalStaff> getEntityList() {
        if (entityList == null) {
            setEntityList(new ArrayList<HospitalStaff>());
        }
        return entityList;
    }

    public void selectionListener(AjaxBehaviorEvent event) {

        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                setSelectedEntity((HospitalStaff) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
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
        throw new UnsupportedOperationException("Not supported yet.");
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

    @Override
    public void select() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String navigateToEdit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected EntityService<HospitalStaff> getService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
