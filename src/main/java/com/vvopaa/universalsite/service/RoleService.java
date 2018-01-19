package com.vvopaa.universalsite.service;

import java.util.List;

import com.vvopaa.universalsite.model.UserRoles;

public interface RoleService {
	UserRoles findById(int id);
	 
    UserRoles findByRole(String role);
     
    List<UserRoles> findAll();
}
