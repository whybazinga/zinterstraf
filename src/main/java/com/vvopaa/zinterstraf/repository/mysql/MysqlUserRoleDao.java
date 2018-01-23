package com.vvopaa.zinterstraf.repository.mysql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.zinterstraf.model.UserRoles;
import com.vvopaa.zinterstraf.repository.UserRoleDao;

@Repository("userRoleDao")
public class MysqlUserRoleDao extends AbstractMysqlDao<Integer, UserRoles> implements UserRoleDao {

	@Override
	public List<UserRoles> findAll() {
        return getObjectListByParameters(new HashMap<>());
	}

	@Override
	public UserRoles findById(int key) {
		return getByKey(key);
	}

	@Override
	public UserRoles findByRole(String role) {
        Map<String, String> map = new HashMap<>();
        map.put("role", role);
		return getObjectByParameters(map);
	}

}
