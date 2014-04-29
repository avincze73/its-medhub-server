/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import masterdatamodule.entity.ITSConstant;

/**
 *
 * @author root
 */
public abstract class AbstractRepository<T> {

    private Class<T> entityClass;

    public AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public int getMaxRecordNumber() {
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery();
//        Root<TDSConstant> entity = cq.from(TDSConstant.class);
//        Path attribute = entity.get("name");
//        cq.where(cb.equal(attribute, "Max record number"));
//        TypedQuery<TDSConstant> q = getEntityManager().createQuery(cq);
//        return q.getSingleResult().getValue();
        ITSConstant tdsConstant =
                getEntityManager().createNamedQuery("ITSConstant.findByName", ITSConstant.class).setParameter("name", "Max record number").getSingleResult();
        return tdsConstant.getConstantValue();
    }

    public List<T> findAll() throws TooManyResultsException, ZeroResultException {
        long count = count();
        long maxRecordNumber = getMaxRecordNumber();
        if (count > maxRecordNumber) {
            throw new TooManyResultsException();
        }
        if (count == 0) {
            throw new ZeroResultException();
        }
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> getList() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findAllWithoutMaxRecordNumberCheck() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findByName(String name) throws TooManyResultsException, ZeroResultException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entity = cq.from(entityClass);
        Path attr;
        Predicate restriction = cb.conjunction();
        if (name != null && !name.equals("")) {
            attr = entity.get("name");
            restriction = cb.and(restriction, cb.like(cb.lower(attr), "%" + name.toLowerCase() + "%"));
        }
        cq.where(restriction);
        cq.select(cb.count(entity));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        long count = q.getSingleResult();
        long maxRecordNumber = getMaxRecordNumber();
        if (count > maxRecordNumber) {
            throw new TooManyResultsException();
        }
        if (count == 0) {
            throw new ZeroResultException();
        }
        cq.select(entity);
        TypedQuery<T> res = getEntityManager().createQuery(cq);
        return res.getResultList();
    }

    public List<T> findByAttribute(String attributeName, String attributeValue) throws TooManyResultsException, ZeroResultException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entity = cq.from(entityClass);
        Path attr;
        Predicate restriction = cb.conjunction();
        if (attributeValue != null && !attributeValue.equals("")) {
            attr = entity.get("attributeName");
            restriction = cb.and(restriction, cb.like(cb.lower(attr), "%" + attributeValue.toLowerCase() + "%"));
        }
        cq.where(restriction);
        cq.select(cb.count(entity));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        long count = q.getSingleResult();
        long maxRecordNumber = getMaxRecordNumber();
        if (count > maxRecordNumber) {
            throw new TooManyResultsException();
        }
        if (count == 0) {
            throw new ZeroResultException();
        }
        cq.select(entity);
        TypedQuery<T> res = getEntityManager().createQuery(cq);
        return res.getResultList();
    }

    public List<T> findByHungarianNameAndEnglishName(String hungarianName, String englishName) throws TooManyResultsException, ZeroResultException {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entity = cq.from(entityClass);
        Path attr;
        Predicate restriction = cb.conjunction();
        if (hungarianName != null && !hungarianName.equals("")) {
            attr = entity.get("hungarianName");
            restriction = cb.and(restriction, cb.like(cb.lower(attr), "%" + hungarianName.toLowerCase() + "%"));
        }
        if (englishName != null && !englishName.equals("")) {
            attr = entity.get("englishName");
            restriction = cb.and(restriction, cb.like(cb.lower(attr), "%" + englishName.toLowerCase() + "%"));
        }
        cq.where(restriction);
        cq.select(cb.count(entity));
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        long count = q.getSingleResult();
        long maxRecordNumber = getMaxRecordNumber();
        if (count > maxRecordNumber) {
            throw new TooManyResultsException();
        }
        if (count == 0) {
            throw new ZeroResultException();
        }
        cq.select(entity);
        TypedQuery<T> res = getEntityManager().createQuery(cq);
        return res.getResultList();
    }

    public long findByNameCount(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> entity = cq.from(entityClass);
        cq.select(cb.count(entity));
        Path nameAttr;
        if (name != null) {
            nameAttr = entity.get("name");
            cq.where(cb.like(cb.lower(nameAttr), "%" + name.toLowerCase() + "%"));
        }
        TypedQuery<Long> q = getEntityManager().createQuery(cq);
        return q.getSingleResult();
    }

    public T findSingleByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entity = cq.from(entityClass);
        Path attribute = entity.get("name");
        cq.where(cb.equal(attribute, name));
        TypedQuery q = getEntityManager().createQuery(cq);
        return (T) q.getSingleResult();
    }

    public T findSingleByAttribute(String attributeName, String attributeValue) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<T> entity = cq.from(entityClass);
        Path attribute = entity.get(attributeName);
        cq.where(cb.equal(attribute, attributeValue));
        TypedQuery q = getEntityManager().createQuery(cq);
        return (T) q.getSingleResult();
    }
}
