package com.vvopaa.universalsite.repository.mysql;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMysqlDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;
    
   
    @SuppressWarnings("unchecked")
	public AbstractMysqlDao() {
        this.persistentClass = (Class<T>) ((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @Autowired
    private SessionFactory sessionFactory;
 
    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }
    
    protected CriteriaBuilder getBuilder() {
    	return getSession().getCriteriaBuilder();
    }
    
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
    
    public void save(T entity) {
    	getSession().save(entity);
    }
    
    public void update(T entity) {
        getSession().update(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    protected CriteriaQuery<T> getQuery(){
        return getBuilder().createQuery(persistentClass);
    }
	
}
