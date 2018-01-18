package com.vvopaa.universalsite.service;

import com.vvopaa.universalsite.model.User;

public interface UserService {
	
	public User saveUser(String login, String pass);
	
	public User getUserById(int id);
	
	public User loginUser(String login, String pass);
	
	public User getUserByEmail(String email);
}
