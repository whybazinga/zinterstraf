package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.repository.SecurityRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SecurityService implements ReactiveUserDetailsService {
  private final SecurityRep securityRep;

  @Autowired
  public SecurityService(SecurityRep securityRep) {
    this.securityRep = securityRep;
  }


  @Override
  public Mono<UserDetails> findByUsername(String name) {
    return securityRep.findByUsername(name).cast(UserDetails.class);
  }
}
