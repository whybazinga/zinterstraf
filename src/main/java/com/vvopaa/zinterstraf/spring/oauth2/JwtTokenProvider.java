package com.vvopaa.zinterstraf.spring.oauth2;

import com.vvopaa.zinterstraf.configuration.AppOptions;
import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.model.special.Options;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

  private final Options options;

  @Autowired
  public JwtTokenProvider(AppOptions appOptions) {
    this.options = appOptions.getOptions();
  }

  public String generateToken(Authentication authentication) {

    User userPrincipal = (User) authentication.getPrincipal();

    Date expiryDate = new Date(new Date().getTime() + options.getJwtExpirationMs());

    return Jwts.builder()
      .setSubject(Long.toString(userPrincipal.getId()))
      .setIssuedAt(new Date())
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, options.getJwtSecret())
      .compact();
  }

  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(options.getJwtSecret())
      .parseClaimsJws(token)
      .getBody();

    return Long.parseLong(claims.getSubject());
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
