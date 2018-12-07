package com.vvopaa.zinterstraf.spring.oauth2;

import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public AuthenticationManager(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }


  @Override
  @SuppressWarnings("unchecked")
  public Mono<Authentication> authenticate(Authentication authentication) {
    String authToken = authentication.getCredentials().toString();
    if (jwtTokenProvider.validateToken(authToken)) {
      Claims claims = jwtTokenProvider.getClaimsFromToken(authToken);
      Set<String> roles = (Set<String>) claims.get(JwtTokenProvider.CLAIMS_ROLE_KEY, Set.class).stream()
        .map(role -> UserRoleTypes.valueOf(String.valueOf(role)).getValue());
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        claims.getSubject(),
        null,
        roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
      );
      return Mono.just(auth);
    } else {
      return Mono.empty();
    }
  }
}
