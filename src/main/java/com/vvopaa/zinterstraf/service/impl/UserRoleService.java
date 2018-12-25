package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.UserRole;
import com.vvopaa.zinterstraf.repository.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service("userRoleService")
@Transactional
public class UserRoleService {

  private final UserRoleDao userRoleDao;

  @Autowired
  public UserRoleService(UserRoleDao userRoleDao) {
    this.userRoleDao = userRoleDao;
  }

  public Mono<UserRole> findByRole(String role) {
    return userRoleDao.findByRole(role);
  }

  public Flux<UserRole> findAll() {
    return userRoleDao.findAll();
  }

}
