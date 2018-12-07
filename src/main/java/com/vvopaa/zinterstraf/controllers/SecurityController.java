package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.payload.ApisResponse;
import com.vvopaa.zinterstraf.payload.JwtAuthResponse;
import com.vvopaa.zinterstraf.payload.SignInRequest;
import com.vvopaa.zinterstraf.payload.SignUpRequest;
import com.vvopaa.zinterstraf.service.impl.SecurityService;
import com.vvopaa.zinterstraf.spring.oauth2.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class SecurityController {
  private final SecurityService securityService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public SecurityController(SecurityService securityService, PasswordEncoder passwordEncoder,
                            JwtTokenProvider jwtTokenProvider) {
    this.securityService = securityService;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @PostMapping("sign-in")
  public Mono<ResponseEntity<JwtAuthResponse>> signInUser(@Valid @RequestBody SignInRequest signInRequest) {
    return securityService.findByUsername(signInRequest.getUsernameOrEmail()).map(userDetails ->
      passwordEncoder.encode(signInRequest.getPassword()).equals(userDetails.getPassword())
         ? ResponseEntity.ok(new JwtAuthResponse(jwtTokenProvider.generateToken(userDetails)))
         : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
    );
  }

  @PostMapping("sign-up")
  public Mono<ResponseEntity<ApisResponse>> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest,
                                                       ServerHttpRequest serverHttpRequest) {
    return securityService.saveUser(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()))
       .onErrorResume(throwable -> Mono.error(new UsernameAlreadyExistsException(signUpRequest.getEmail(),throwable)))
       .map(userMono -> UriComponentsBuilder
          .fromHttpRequest(serverHttpRequest)
          .path("/users/{username}")
          .buildAndExpand(signUpRequest.getEmail()).toUri())
       .map(uri -> ResponseEntity.created(uri).body(new ApisResponse()));
  }

}
