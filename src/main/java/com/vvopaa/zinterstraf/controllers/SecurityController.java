package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.exception.AppException;
import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import com.vvopaa.zinterstraf.payload.ApisResponse;
import com.vvopaa.zinterstraf.payload.JwtAuthenticationResponse;
import com.vvopaa.zinterstraf.payload.SignInRequest;
import com.vvopaa.zinterstraf.payload.SignUpRequest;
import com.vvopaa.zinterstraf.service.impl.UserRoleService;
import com.vvopaa.zinterstraf.service.impl.UserService;
import com.vvopaa.zinterstraf.spring.oauth2.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Collections;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
@RequestMapping("auth")
public class SecurityController {
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider tokenProvider;
  private final UserService userService;
  private final UserRoleService roleService;
  private final PasswordEncoder passwordEncoder;

  public SecurityController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                            UserService userService, UserRoleService roleService, PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.tokenProvider = tokenProvider;
    this.userService = userService;
    this.roleService = roleService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("sign-in")
  public Mono<ResponseEntity> signInUser(@Valid @RequestBody SignInRequest signInRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        signInRequest.getUsernameOrEmail(),
        signInRequest.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return Mono.just(ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
  }


}
