package com.vvopaa.zinterstraf.repository.mysql;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMysqlDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;
   
    @SuppressWarnings("unchecked")
	public AbstractMysqlDao() {
        this.persistentClass = (Class<T>) ((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    T getByKey(PK key) {
        return getSession().get(persistentClass, key);
    }
 
    void persist(T entity) {
        getSession().persist(entity);
    }

    void save(T entity) {
    	getSession().persist(entity);
    }

    void update(T entity) {
        getSession().update(entity);
    }

    void delete(T entity) {
        getSession().delete(entity);
    }

    T getObjectByParameters(Map<String, String> params) {
        Predicate[] array = new Predicate[params.size()];
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            array[i] = builder.equal(root.get(entry.getKey()), entry.getValue());
            ++i;
        }
        query.select(root).where(array);
        List<T> existingObjectList = getSession().createQuery(query).getResultList();
        return existingObjectList.isEmpty() ? null : existingObjectList.get(0);
    }

    List<T> getObjectListByParameters(Map<String, String> params) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        if(params.isEmpty()) {
            query.select(root);
        } else {
            Predicate[] array = new Predicate[params.size()];
            int i = 0;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                array[i] = builder.equal(root.get(entry.getKey()), entry.getValue());
                ++i;
            }
            query.select(root).where(array);
        }
        return getSession().createQuery(query).getResultList();
    }
}
