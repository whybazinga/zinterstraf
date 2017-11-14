package com.vvopaa.universalsite.repository.mysql;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.UserRole;
import com.vvopaa.universalsite.repository.UserRoleDao;

@Repository("userRoleDao")
public class MysqlUserRoleDao extends AbstractMysqlDao<Integer, UserRole>implements UserRoleDao {

	@Override
	public List<UserRole> findAll() {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        query.select(root);
        Query<UserRole> result = getSession().createQuery(query);
        List<UserRole> allRoles = result.getResultList();
        if (!allRoles.isEmpty()) {
        	return allRoles; 
        }
		return null;
	}

	@Override
	public UserRole findByRole(String role) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserRole> query = builder.createQuery(UserRole.class);
        Root<UserRole> root = query.from(UserRole.class);
        
        query.select(root).where(builder.equal(root.get("role"), role));
        Query<UserRole> q = getSession().createQuery(query);
        List<UserRole> foundRole = q.getResultList();
        if (!foundRole.isEmpty()) {
        	UserRole resultRole = foundRole.get(0);
        	return resultRole; 
        }
		return null;
	}

	@Override
	public UserRole findById(int id) {
		return getByKey(id);
	}

}
