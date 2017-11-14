package com.vvopaa.universalsite.service;

import java.util.List;

import com.vvopaa.universalsite.model.UserRole;

public interface RoleService {
	UserRole findById(int id);
	 
    UserRole findByRole(String role);
     
    List<UserRole> findAll();
}
