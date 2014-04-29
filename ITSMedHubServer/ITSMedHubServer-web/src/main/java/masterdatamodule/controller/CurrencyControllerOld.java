/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import common.DisplayMode;
import common.controller.MenuController;
import common.ModeHandler;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import masterdatamodule.entity.Currency;
import masterdatamodule.service.CurrencyService;

/**
 *
 * @author vincze.attila
 */
@Named
@SessionScoped
public class CurrencyControllerOld implements Serializable, Cloneable {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CurrencyControllerOld.class);
    @EJB
    private CurrencyService service;
    private List<Currency> entityList;
    private Currency selectedEntity;
    private Currency originalEntity;
    private int size = 200;
    @Inject
    private MenuController menuController;
    @Inject
    private ModeHandler modeHandler;

    public void edit() {
        modeHandler.setMode(DisplayMode.Edit);

    }

    public void save() {
        modeHandler.setMode(DisplayMode.View);
    }

    public void cancel() {
        modeHandler.setMode(DisplayMode.View);
    }

    public void add() {
        modeHandler.setMode(DisplayMode.Edit);
    }

    public void closeDetails(){
        log.info(size);
        setSize(30);
    }
    
    public void confirm(ActionEvent actionEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "System Error", "Please try again later.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void ss(){
        log.info("ss");
    }

    public List<Currency> getEntityList() {
        if (entityList == null) {
            //setEntityList(service.getList());
        }
        
        return entityList;
    }

    public void setEntityList(List<Currency> entityList) {
        this.entityList = entityList;
    }

    public Currency getOriginalEntity() {
        return originalEntity;
    }

    public void setOriginalEntity(Currency originalEntity) {
        this.originalEntity = originalEntity;
    }

    public Currency getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Currency selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public ModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(ModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    
}
