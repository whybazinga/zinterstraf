package com.vvopaa.universalsite.repository.mysql;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.UserRoles;
import com.vvopaa.universalsite.repository.UserRoleDao;

@Repository("userRoleDao")
public class MysqlUserRoleDao extends AbstractMysqlDao<Integer, UserRoles>implements UserRoleDao {

	@Override
	public List<UserRoles> findAll() {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserRoles> query = builder.createQuery(UserRoles.class);
        Root<UserRoles> root = query.from(UserRoles.class);
        query.select(root);
        Query<UserRoles> result = getSession().createQuery(query);
        List<UserRoles> allRoles = result.getResultList();
        if (!allRoles.isEmpty()) {
        	return allRoles; 
        }
		return null;
	}

	@Override
	public UserRoles findByRole(String role) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserRoles> query = builder.createQuery(UserRoles.class);
        Root<UserRoles> root = query.from(UserRoles.class);
        
        query.select(root).where(builder.equal(root.get("role"), role));
        Query<UserRoles> q = getSession().createQuery(query);
        List<UserRoles> foundRole = q.getResultList();
        if (!foundRole.isEmpty()) {
        	UserRoles resultRole = foundRole.get(0);
        	return resultRole; 
        }
		return null;
	}

	@Override
	public UserRoles findById(int id) {
		return getByKey(id);
	}

}
