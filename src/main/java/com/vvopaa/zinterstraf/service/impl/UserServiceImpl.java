package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvopaa.zinterstraf.repository.UserDao;
import com.vvopaa.zinterstraf.repository.UserRoleDao;
import com.vvopaa.zinterstraf.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {
	@Qualifier("mysqlUserDao")
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRole;
	/*
	@Autowired
    private PasswordEncoder passwordEncoder;
	*/

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.getUserByEmail(username);
	}

	@Override
	@Transactional
	public User saveUser(String login, String pass) {
		
		//pass = passwordEncoder.encode(pass);
		User savedUser = userDao.saveUser(login, pass);
		
		
		return savedUser;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loginUser(String login, String pass) {
		//pass = passwordEncoder.encode(pass);
		User loggedUser = userDao.loginUser(login, pass);
		
		return loggedUser;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userDao.getUserByEmail(email);
		return user;
	}

}
