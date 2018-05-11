package com.vvopaa.zinterstraf.service;

import java.util.List;

import com.vvopaa.zinterstraf.model.UserRoles;

public interface RoleService {
  UserRoles findById(int id);

  UserRoles findByRole(String role);

  List<UserRoles> findAll();
}
