package com.vvopaa.zinterstraf.repository;

import java.util.List;

import com.vvopaa.zinterstraf.model.UserRoles;

public interface UserRoleDao {
	List<UserRoles> findAll();
    
    UserRoles findByRole(String role);

    UserRoles findById(int id);
}
