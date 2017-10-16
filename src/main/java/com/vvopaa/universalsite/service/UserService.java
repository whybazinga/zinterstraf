package com.vvopaa.universalsite.service;

import com.vvopaa.universalsite.model.UserEntity;

public interface UserService {
	
	public UserEntity saveUser(String login, String pass);
	
	public UserEntity getUserById(int id);
	
	public UserEntity loginUser(String login, String pass);
}
