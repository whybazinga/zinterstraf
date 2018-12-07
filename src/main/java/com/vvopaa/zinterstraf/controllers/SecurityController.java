package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.payload.JwtAuthResponse;
import com.vvopaa.zinterstraf.payload.SignInRequest;
import com.vvopaa.zinterstraf.service.impl.SecurityService;
import com.vvopaa.zinterstraf.spring.oauth2.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
@RequestMapping("auth")
public class SecurityController {
  private final SecurityService securityService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public SecurityController(SecurityService securityService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
    this.securityService = securityService;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @PostMapping("sign-in")
  public Mono<ResponseEntity<JwtAuthResponse>> signInUser(@Valid @RequestBody SignInRequest signInRequest) {
    return securityService.findByUsername(signInRequest.getUsernameOrEmail()).map(userDetails -> {
      if(passwordEncoder.encode(signInRequest.getPassword()).equals(userDetails.getPassword())) {
        return ResponseEntity.ok(new JwtAuthResponse(jwtTokenProvider.generateToken(userDetails)));
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    });
  }


}
