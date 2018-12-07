package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import com.vvopaa.zinterstraf.repository.SecurityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Service
public class SecurityService implements ReactiveUserDetailsService {
  private final SecurityRep securityRep;
  private final UserRoleService userRoleService;

  @Autowired
  public SecurityService(SecurityRep securityRep, UserRoleService userRoleService) {
    this.securityRep = securityRep;
    this.userRoleService = userRoleService;
  }


  @Override
  public Mono<UserDetails> findByUsername(String name) {
    return securityRep.findByUsername(name).cast(UserDetails.class);
  }

  public Mono<User> saveUser(String email, String password) {
    return userRoleService.findByRole(UserRoleTypes.USER.getValue())
       .flatMap(userRole -> securityRep.save(new User(email,password,Collections.singleton(userRole))));
  }
}
