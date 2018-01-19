package com.vvopaa.universalsite.repository;

import java.util.List;

import com.vvopaa.universalsite.model.UserRoles;

public interface UserRoleDao {
	List<UserRoles> findAll();
    
    UserRoles findByRole(String role);
     
    UserRoles findById(int id);
}
