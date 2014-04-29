/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.service;

import base.ITSEntity;
import common.service.EntityService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tds
 */
public abstract class MasterDataServiceBase<T extends ITSEntity> implements EntityService<T> {

    protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(this.getClass().getName());
    @PersistenceContext(unitName = "ITSMedHubServerPU")
    protected EntityManager em;
    protected Class<T> entityClass;
    protected String orderBy;

    public MasterDataServiceBase(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.orderBy = "id";
    }

    public MasterDataServiceBase(Class<T> entityClass, String orderBy) {
        this.entityClass = entityClass;
        this.orderBy = orderBy;
    }
        

    @Override
    public List<T> getList() {
        return em.createQuery("select item from " + getEntityName(entityClass) + " item order by item." + orderBy, entityClass).getResultList();
    }

    @Override
    public T save(T entity) {
        log.info("saving: " + entity);
        if (entity.getId() == null) {
            em.persist(entity);
            em.flush();
            entity.setId(entity.getId());
        } else {
            em.merge(entity);
        }
        return entity;
    }
    
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    
    @Override
    public T find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private String getEntityName(Class c) {
        String FQClassName = c.getName();
        int firstChar;
        firstChar = FQClassName.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            FQClassName = FQClassName.substring(firstChar);
        }
        return FQClassName;
    }
}
