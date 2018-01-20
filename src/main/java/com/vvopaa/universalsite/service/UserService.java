package com.vvopaa.universalsite.service;

import com.vvopaa.universalsite.model.User;

public interface UserService {
	
	User saveUser(String login, String pass);
	
	User getUserById(int id);
	
	User loginUser(String login, String pass);
	
	User getUserByEmail(String email);
}
