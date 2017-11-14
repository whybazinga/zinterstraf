package com.vvopaa.universalsite.repository.mysql;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.PersistentLogin;
import com.vvopaa.universalsite.model.UserEntity;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryDao extends AbstractMysqlDao<String, PersistentLogin> implements PersistentTokenRepository {

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
			CriteriaBuilder builder = getBuilder();
			CriteriaQuery<PersistentLogin> query = builder.createQuery(PersistentLogin.class);
	        Root<PersistentLogin> root = query.from(PersistentLogin.class);
	        query.select(root).where(builder.equal(root.get("series"), seriesId));
	        Query<PersistentLogin> result = getSession().createQuery(query);
	        List<PersistentLogin> existingListPersistant = result.getResultList();
	        if (!existingListPersistant.isEmpty()) {
	        	PersistentLogin persistentLogin = existingListPersistant.get(0);
	        	return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
	                    persistentLogin.getToken(), persistentLogin.getLast_used()); 
	        }
            return null;
	}

	@Override
	public void removeUserTokens(String arg0) {
		
		
	}

	@Override
	public void updateToken(String arg0, String arg1, Date arg2) {
		// TODO Auto-generated method stub
		
	}

}
