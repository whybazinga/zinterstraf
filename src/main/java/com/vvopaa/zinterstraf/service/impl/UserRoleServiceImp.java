package com.vvopaa.zinterstraf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.vvopaa.zinterstraf.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvopaa.zinterstraf.model.UserRoles;
import com.vvopaa.zinterstraf.service.RoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImp implements RoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserRoles findByRole(String role) {
		return userRoleDao.findByRole(role);
	}

	@Override
	public List<UserRoles> findAll() {
		return userRoleDao.findAll();
	}

}
