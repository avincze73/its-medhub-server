/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.controller;

import base.ITSEntity;
import common.DisplayMode;
import common.ModeHandler;
import common.service.EntityService;
import java.io.Serializable;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author tds
 */
public abstract class MasterDataControllerBase<T extends ITSEntity> implements Serializable, Cloneable {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getCanonicalName());
    protected List<T> entityList;
    protected T selectedEntity;
    protected T originalEntity;
    protected Long selectedId;
    protected Class<T> entityClass;
    @Inject
    protected ModeHandler modeHandler;

    public MasterDataControllerBase() {
    }

    
    public MasterDataControllerBase(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    
    protected abstract EntityService<T> getService();

    public void insert() {
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
            entityList.add(getService().save(selectedEntity));
            setSelectedEntity(entityList.get(entityList.size() - 1));
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
            setSelectedEntity(entityList.get(0));
            setSelectedId(entityList.get(0).getId());
        } else {
            setSelectedEntity(originalEntity);
        }
        modeHandler.setMode(DisplayMode.View);
    }

    @PostConstruct
    public void init(){
        if (entityList == null) {
            setEntityList(getService().getList());
            if (!entityList.isEmpty()) {
                log.info(entityList.size());
                setSelectedEntity(entityList.get(0));
                setSelectedId(entityList.get(0).getId());
            }
        }
    };

    public void select() {
        log.info(selectedId);
        for (T entity : entityList) {
            if (entity.getId().equals(selectedId)) {
                setSelectedEntity(entity);
            }
        }
    }

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

    public ModeHandler getModeHandler() {
        return modeHandler;
    }

    public void setModeHandler(ModeHandler modeHandler) {
        this.modeHandler = modeHandler;
    }

    public T getOriginalEntity() {
        return originalEntity;
    }

    public void setOriginalEntity(T originalEntity) {
        this.originalEntity = originalEntity;
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
}
