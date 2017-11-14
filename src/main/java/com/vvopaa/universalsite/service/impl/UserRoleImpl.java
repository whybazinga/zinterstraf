package com.vvopaa.universalsite.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvopaa.universalsite.model.UserRole;
import com.vvopaa.universalsite.repository.mysql.MysqlUserRoleDao;
import com.vvopaa.universalsite.service.RoleService;

@Service("userRoleService")
@Transactional
public class UserRoleImpl implements RoleService {
	
	@Autowired
    private MysqlUserRoleDao dao;
	
	@Override
	public UserRole findById(int id) {
		return dao.findById(id);
	}

	@Override
	public UserRole findByRole(String role) {
		return dao.findByRole(role);
	}

	@Override
	public List<UserRole> findAll() {
		return dao.findAll();
	}

}
