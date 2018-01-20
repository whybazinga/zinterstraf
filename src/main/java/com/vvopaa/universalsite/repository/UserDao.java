package com.vvopaa.universalsite.repository;

import com.vvopaa.universalsite.model.User;

public interface UserDao {

	User saveUser(String email, String pass);
	
	User getUserById(int id);
	
	User getUserByEmail(String email);
	
	User loginUser(String email, String pass);
}
