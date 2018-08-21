package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.exception.AppException;
import com.vvopaa.zinterstraf.exception.UsernameAlreadyExistsException;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.UserRole;
import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import com.vvopaa.zinterstraf.payload.ApisResponse;
import com.vvopaa.zinterstraf.payload.JwtAuthenticationResponse;
import com.vvopaa.zinterstraf.payload.SignInRequest;
import com.vvopaa.zinterstraf.payload.SignUpRequest;
import com.vvopaa.zinterstraf.service.converters.jackson.JsonMessageCreator;
import com.vvopaa.zinterstraf.service.impl.UserRoleService;
import com.vvopaa.zinterstraf.service.impl.UserService;
import com.vvopaa.zinterstraf.spring.oauth2.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager  authenticationManager;
  private final UserService userService;
  private final UserRoleService roleService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider tokenProvider;

  public AuthController(AuthenticationManager authenticationManager, UserService userService, UserRoleService roleService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
    this.roleService = roleService;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  @RequestMapping(value = "/sign-in", produces={"application/json"}, method={RequestMethod.POST})
  @ApiOperation(value = "Authenticate user", nickname = "sign-in user")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest signInRequest) {

    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        signInRequest.getUsernameOrEmail(),
        signInRequest.getPassword()
      )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @RequestMapping(value = "/sign-test", produces={"text/plain"}, method={RequestMethod.POST})
  public String getStr(String msg) {
    return msg;
  }

  @RequestMapping(value = "/sign-up", produces={"application/json"}, method={RequestMethod.POST})
  @ApiOperation(value = "Register new user", nickname = "sign-up user",response = ApisResponse.class)
  @ApiResponses(value = { @ApiResponse(code = 500, message = "Something went wrong during request") })
  public ResponseEntity<?> signUpUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    User user = User.create(signUpRequest.getEmail(),signUpRequest.getPassword());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserRole userRole = roleService.findByRole(UserRoleTypes.USER.getValue()).orElseThrow(() -> new AppException("User Role not set."));
    user.setUserRoles(Collections.singleton(userRole));

    User result;
    try {
      result = userService.save(user);
    } catch (UsernameAlreadyExistsException e) {
      return ResponseEntity.ok(new ApisResponse(false, e.getMessage()));
      //return JsonMessageCreator.createSimpleJsonMessage("error");
    }

    URI location = ServletUriComponentsBuilder
      .fromCurrentContextPath().path("/users/{username}")
      .buildAndExpand(result.getUsername()).toUri();

    return ResponseEntity.created(location).body(new ApisResponse(true, "User registered successfully"));
    //return JsonMessageCreator.createSimpleJsonMessage("abc");
  }

}
