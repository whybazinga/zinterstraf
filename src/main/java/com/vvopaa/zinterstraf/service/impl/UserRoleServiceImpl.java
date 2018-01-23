package com.vvopaa.zinterstraf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvopaa.zinterstraf.model.UserRoles;
import com.vvopaa.zinterstraf.repository.mysql.MysqlUserRoleDao;
import com.vvopaa.zinterstraf.service.RoleService;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements RoleService {
	
	@Autowired
    private MysqlUserRoleDao dao;
	
	@Override
	public UserRoles findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserRoles findByRole(String role) {
		return dao.findByRole(role);
	}

	@Override
	public List<UserRoles> findAll() {
		return dao.findAll();
	}

}