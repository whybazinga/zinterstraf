package com.vvopaa.universalsite.repository.mysql;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.vvopaa.universalsite.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.repository.UserDao;

@Repository("mysqlUserDao")
public class MysqlUserDao extends AbstractMysqlDao<Integer, User> implements UserDao {
	
	@Override
	public User saveUser(String email, String pass) {
		User existingUser = this.getUserByEmail(email);
		/*
		getSession().createQuery(
				"INSERT INTO link_users_ s.stock_code from stock s where s.stock_code = :stockCode")
				.setParameter("stockCode", "7277");
				List result = query.list();
		*/
        if(existingUser == null) {
        	User user = new User();
    		user.setUsername(email);
    		user.setPassword(pass);
    		save(user);
    		return user;
        }
		return null;
	}

	@Override
	public User getUserById(int id) {
		User user = getByKey(id);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("username"), email));
        Query<User> q = getSession().createQuery(query);
        List<User> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
			return existingListUsers.get(0);
        }
		return null;
	}

	@Override
	public User loginUser(String email, String pass) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        //query.select(root).where(Restrictions.eq("ssoId", sso));
        query.select(root).where(builder.equal(root.get("email"), email), builder.equal(root.get("password"), pass));
        Query<User> q = getSession().createQuery(query);
        List<User> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
        	User existingUser = existingListUsers.get(0);
        	return existingUser; 
        }
		return null;
	}

}
