package com.vvopaa.universalsite.repository.mysql;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.UserEntity;
import com.vvopaa.universalsite.repository.UserDao;

@Repository
public class MysqlUserDao extends AbstractMysqlDao<Integer, UserEntity> implements UserDao {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public UserEntity saveUser(String email, String pass) {
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity existingUser;
		existingUser = this.getUserByEmail(email);
        if(existingUser == null) {
        	UserEntity user = new UserEntity();
    		user.setEmail(email);
    		user.setPassword(pass);
    		session.save(user);
    		return user;
        }
		return null;
	}

	@Override
	public UserEntity getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity user = session.get(UserEntity.class, id);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root).where(builder.equal(root.get("email"), email));
        Query<UserEntity> q = session.createQuery(query);
        List<UserEntity> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
        	UserEntity existingUser = existingListUsers.get(0);
        	return existingUser; 
        }
		return null;
	}

	@Override
	public UserEntity loginUser(String email, String pass) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        //query.select(root).where(Restrictions.eq("ssoId", sso));
        query.select(root).where(builder.equal(root.get("email"), email), builder.equal(root.get("password"), pass));
        Query<UserEntity> q = session.createQuery(query);
        List<UserEntity> existingListUsers = q.getResultList();
        if (!existingListUsers.isEmpty()) {
        	UserEntity existingUser = existingListUsers.get(0);
        	return existingUser; 
        }
		return null;
	}

}
