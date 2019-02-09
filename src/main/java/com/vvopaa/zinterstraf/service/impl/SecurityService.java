package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import com.vvopaa.zinterstraf.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;


@Service
public class SecurityService implements ReactiveUserDetailsService {
  private final UserRep userRep;
  private final UserRoleService userRoleService;

  @Autowired
  public SecurityService(UserRep userRep, UserRoleService userRoleService) {
    this.userRep = userRep;
    this.userRoleService = userRoleService;
  }


  @Override
  public Mono<UserDetails> findByUsername(String name) {
    return userRep.findByUsername(name).cast(UserDetails.class);
  }

  public Mono<User> saveUser(String email, String password) {
    return userRep.findByUsername(email)
      .flatMap(user ->
        user == null ?
          userRoleService.findByRole(UserRoleTypes.USER.getValue())
            .flatMap(userRole ->
              userRep.save(new User(email, password, Collections.singleton(userRole)))) :
          Mono.error(new UsernameAlreadyExistsException(user.getUsername()))
      );
      // flatmap doesn't go if nothing is found
  }

  public Mono<User> saveUserTest(String email, String password) {
    return userRep.save(new User(email, password));
  }
}
