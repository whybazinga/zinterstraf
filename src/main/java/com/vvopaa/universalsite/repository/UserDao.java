package com.vvopaa.universalsite.repository;

import com.vvopaa.universalsite.model.UserEntity;

public interface UserDao {

	public UserEntity saveUser(String email, String pass);
	
	public UserEntity getUserById(int id);
	
	public UserEntity getUserByEmail(String email);
	
	public UserEntity loginUser(String email, String pass);
}
