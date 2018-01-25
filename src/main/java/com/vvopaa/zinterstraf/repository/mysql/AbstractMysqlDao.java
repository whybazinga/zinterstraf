package com.vvopaa.zinterstraf.repository.mysql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMysqlDao<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@Autowired
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
	public AbstractMysqlDao() {
        this.persistentClass = (Class<T>) ((java.lang.reflect.ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    T getObjectByParameters(Map<String, String> params) {
        Predicate[] array = new Predicate[params.size()];
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        int i = 0;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            array[i] = builder.equal(root.get(entry.getKey()), entry.getValue());
            ++i;
        }
        query.select(root).where(array);
        List<T> existingObjectList = entityManager.createQuery(query).getResultList();
        return existingObjectList.isEmpty() ? null : existingObjectList.get(0);
    }

    List<T> getObjectListByParameters(Map<String, String> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
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
        return entityManager.createQuery(query).getResultList();
    }
}
