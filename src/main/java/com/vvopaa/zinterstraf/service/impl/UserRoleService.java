package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.UserRole;
import com.vvopaa.zinterstraf.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("userRoleService")
@Transactional
public class UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;

	public Optional<UserRole> findByRole(String role) {
		return userRoleDao.findByRole(role);
	}

	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

}
