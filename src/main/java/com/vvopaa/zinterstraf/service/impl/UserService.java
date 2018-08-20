package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("UserDetailsMain")
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUsername(username).get();
	}

	public User save(User user) throws UsernameAlreadyExistsException {
		if(userDao.findByUsername(user.getUsername()).isPresent()) throw new UsernameAlreadyExistsException(user.getUsername());
		return userDao.save(user);
	}

	/**
	 * This method is used by JWTAuthenticationFilter. It Gets user by id
	 * @param id - user Id
	 * @return user or error
 	 */
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		User user = Optional.ofNullable(userDao.getOne(id)).orElseThrow(
			() -> new UsernameNotFoundException("User not found with id : " + id)
		);
		return user;
	}

	public User getUserByUsername(String username) {
	  Optional<User> userOpt = userDao.findByUsername(username);
		return userOpt.orElse(null);
	}
}
