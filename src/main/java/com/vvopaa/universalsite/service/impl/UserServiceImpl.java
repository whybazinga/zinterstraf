package com.vvopaa.universalsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvopaa.universalsite.model.UserEntity;
import com.vvopaa.universalsite.repository.UserDao;
import com.vvopaa.universalsite.service.UserService;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	//@Qualifier("mysqlUserDao") 
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public UserEntity saveUser(String login, String pass) {
		
		pass = passwordEncoder.encode(pass);
		UserEntity savedUser = userDao.saveUser(login, pass);
		
		return savedUser;
	}

	@Override
	public UserEntity getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity loginUser(String login, String pass) {
		pass = passwordEncoder.encode(pass);
		UserEntity loggedUser = userDao.loginUser(login, pass);
		
		return loggedUser;
	}

}
