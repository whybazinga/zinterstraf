package com.vvopaa.universalsite.repository.mysql;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.UserEntity;
import com.vvopaa.universalsite.repository.UserDao;

@Repository("mysqlUserDao")
public class MysqlUserDao extends AbstractMysqlDao<Integer, UserEntity> implements UserDao {
	
	@Override
	public UserEntity saveUser(String email, String pass) {
		UserEntity existingUser = this.getUserByEmail(email);
		/*
		getSession().createQuery(
				"INSERT INTO link_users_ s.stock_code from stock s where s.stock_code = :stockCode")
				.setParameter("stockCode", "7277");
				List result = query.list();
		*/
        if(existingUser == null) {
        	UserEntity user = new UserEntity();
    		user.setUsername(email);
    		user.setPassword(pass);
    		save(user);
    		return user;
        }
		return null;
	}

	@Override
	public UserEntity getUserById(int id) {
		UserEntity user = getByKey(id);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(builder.equal(root.get("username"), email));
        Query<UserEntity> q = getSession().createQuery(query);
        List<UserEntity> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
			return existingListUsers.get(0);
        }
		return null;
	}

	@Override
	public UserEntity loginUser(String email, String pass) {
		CriteriaBuilder builder = getBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        //query.select(root).where(Restrictions.eq("ssoId", sso));
        query.select(root).where(builder.equal(root.get("email"), email), builder.equal(root.get("password"), pass));
        Query<UserEntity> q = getSession().createQuery(query);
        List<UserEntity> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
        	UserEntity existingUser = existingListUsers.get(0);
        	return existingUser; 
        }
		return null;
	}

}
