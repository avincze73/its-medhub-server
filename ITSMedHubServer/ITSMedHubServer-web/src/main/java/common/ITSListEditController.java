/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import base.ITSEntity;
import common.controller.MenuController;
import common.service.EntityService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author itsadmin
 */
public abstract class ITSListEditController<T extends ITSEntity> implements Serializable, Cloneable {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getCanonicalName());
    protected List<T> entityList;
    protected T selectedEntity;
    protected T originalEntity;
    protected Class<T> entityClass;
    protected Collection<Object> selection;
    protected Long selectedId;
    protected String title1;
    protected String title2;
    protected boolean saving;
    @Inject
    protected ModeHandler modeHandler;
    @Inject
    protected MenuController menuController;

    public ITSListEditController() {
    }

    public ITSListEditController(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    public void init() {
        if (entityList == null) {
            setEntityList(getService().getList());
            if (!entityList.isEmpty()) {
                log.info(entityList.size());
                setSelectedEntity(entityList.get(0));
                setSelectedId(entityList.get(0).getId());
            }
        }
    }

    public void getAll() {
        try {
            setSelectedEntity(entityClass.newInstance());
            setSelectedId(getSelectedEntity().getId());
            setEntityList(getService().getList());
            if (!entityList.isEmpty()) {
                log.info(entityList.size());
                setSelectedEntity(entityList.get(0));
                setSelectedId(entityList.get(0).getId());
            }
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ITSListEditController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ITSListEditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void select() {
        log.info(selectedId);
        for (T entity : entityList) {
            if (entity.getId().equals(selectedId)) {
                setSelectedEntity(entity);
            }
        }
    }

    public abstract String navigateToEdit();

    public String insert() {
        try {
            log.info("insert");
            setSelectedEntity(entityClass.newInstance());
            setSelectedId(getSelectedEntity().getId());
            modeHandler.setMode(DisplayMode.Insert);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return menuController.getNavigationStack().peek().getLastPage() + ".jsf?faces-redirect=true";
    }

    public void edit() {
        try {
            log.info("edit");
            originalEntity = (T) selectedEntity.clone();
            modeHandler.setMode(DisplayMode.Edit);
        } catch (CloneNotSupportedException ex) {
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() {
        log.info("save");
        if (modeHandler.getMode().equals(DisplayMode.Insert)) {
            setSelectedEntity(getService().save(selectedEntity));
            log.info(selectedEntity.getId());
            entityList.add(getSelectedEntity());
            //setSelectedEntity(entityList.get(entityList.size() - 1));
            setSelectedId(getSelectedEntity().getId());
            log.info(selectedId);
        } else {
            setSelectedEntity(getService().save(selectedEntity));
        }
        modeHandler.setMode(DisplayMode.View);
    }

    public void cancel() {
        log.info("cancel");
        
        if (modeHandler.getMode().equals(DisplayMode.Insert)) {
            try {
                modeHandler.setMode(DisplayMode.View);
                setSelectedEntity(entityList.get(0));
                setSelectedId(entityList.get(0).getId());
                log.info("itt vagyok");
                FacesContext.getCurrentInstance().getExternalContext().redirect(
                        FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                        + menuController.back());
                
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ITSListEditController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            modeHandler.setMode(DisplayMode.View);
            setSelectedEntity(originalEntity);
        }
        
    }

    public abstract void search();

    public abstract void refresh();

    protected abstract EntityService<T> getService();

    public List<T> getEntityList() {
        if (entityList == null) {
            setEntityList(new ArrayList<T>());
        }
        return entityList;
    }

    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public T getOriginalEntity() {
        return originalEntity;
    }

    public void setOriginalEntity(T originalEntity) {
        this.originalEntity = originalEntity;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public T getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(T selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public Long getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(Long selectedId) {
        this.selectedId = selectedId;
    }

    public Collection<Object> getSelection() {
        return selection;
    }

    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
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
}
