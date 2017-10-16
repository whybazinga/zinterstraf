package com.vvopaa.universalsite.repository.mysql;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.vvopaa.universalsite.model.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryDao extends AbstractMysqlDao<String, PersistentLogin> implements PersistentTokenRepository {

	@Override
	public void createNewToken(PersistentRememberMeToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUserTokens(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateToken(String arg0, String arg1, Date arg2) {
		// TODO Auto-generated method stub
		
	}

}
