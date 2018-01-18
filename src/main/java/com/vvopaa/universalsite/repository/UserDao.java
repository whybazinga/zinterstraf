package com.vvopaa.universalsite.repository;

import com.vvopaa.universalsite.model.User;

public interface UserDao {

	public User saveUser(String email, String pass);
	
	public User getUserById(int id);
	
	public User getUserByEmail(String email);
	
	public User loginUser(String email, String pass);
}
