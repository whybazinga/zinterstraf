package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;

public interface UserDao {

	User saveUser(String email, String pass);
	
	User getUserByEmail(String email);
	
	User loginUser(String email, String pass);
}
