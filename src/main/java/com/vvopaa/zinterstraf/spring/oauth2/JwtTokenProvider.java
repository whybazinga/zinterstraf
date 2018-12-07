package com.vvopaa.zinterstraf.spring.oauth2;

import com.vvopaa.zinterstraf.configuration.AppOptions;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.special.Options;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenProvider {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);
  static final String CLAIMS_ROLE_KEY = "role";

  private final Options options;

  @Autowired
  public JwtTokenProvider(AppOptions appOptions) {
    this.options = appOptions.getOptions();
  }

  private Map<String, Object> generateClaims(UserDetails user) {
    Map<String, Object> claims = new HashMap<>();
    claims.put(CLAIMS_ROLE_KEY, Set.of(user.getAuthorities()));
    return claims;
  }

  Claims getClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(options.getJwtSecret()).parseClaimsJws(token).getBody();
  }

  public String generateToken(UserDetails userPrincipal) {
    final Date createdDate = new Date();
    final Date expiryDate = new Date(createdDate.getTime() + options.getJwtExpirationMs());
    return Jwts.builder()
      .setClaims(generateClaims(userPrincipal))
      .setSubject(userPrincipal.getUsername())
      .setIssuedAt(createdDate)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, options.getJwtSecret())
      .compact();
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(options.getJwtSecret()).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException ex) {
      LOGGER.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      LOGGER.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      LOGGER.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      LOGGER.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      LOGGER.error("JWT claims string is empty.");
    }
    return false;
  }
}
