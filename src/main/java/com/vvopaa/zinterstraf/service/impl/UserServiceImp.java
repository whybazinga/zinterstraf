package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
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

@Service
public class UserServiceImp implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public User saveUser(String login, String pass) throws UsernameAlreadyExistsException {
		User user = new User();
		user.setUsername(login);
		user.setPassword(pass);
		try {
			userDao.save(user);
		} catch (Exception e) {
			throw new UsernameAlreadyExistsException("User already exists");
		}

		return user;
	}

}
