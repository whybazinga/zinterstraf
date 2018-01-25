package com.vvopaa.zinterstraf.repository.mysql;
/*
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.vvopaa.zinterstraf.model.User;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.zinterstraf.repository.UserDao;

@Repository("mysqlUserDao")
public class MysqlUserDao extends AbstractMysqlDao<Integer, User> implements UserDao {
	
	@Override
	public User saveUser(String email, String pass) {
        User user = this.getUserByEmail(email);
        if(user == null) {      // Stream + lambda!
            user = new User();
    		user.setUsername(email);
    		user.setPassword(pass);
    		save(user);
        }
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		Map<String, String> map = new HashMap<>();
		map.put("username", email);
		return getObjectByParameters(map);
	}

	@Override
	public User loginUser(String email, String pass) {
        Map<String, String> map = new HashMap<>();
        map.put("username", email);
        map.put("password", pass);
        return getObjectByParameters(map);
	}

}
*/