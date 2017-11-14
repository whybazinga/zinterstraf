package com.vvopaa.universalsite.repository;

import java.util.List;

import com.vvopaa.universalsite.model.UserRole;

public interface UserRoleDao {
	List<UserRole> findAll();
    
    UserRole findByRole(String role);
     
    UserRole findById(int id);
}
