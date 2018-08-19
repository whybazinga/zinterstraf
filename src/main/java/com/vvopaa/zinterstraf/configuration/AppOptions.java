package com.vvopaa.zinterstraf.configuration;

import com.vvopaa.zinterstraf.model.special.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/custom.properties")
public class AppOptions {

  private final Environment env;

  @Autowired
  public AppOptions(Environment env) {
    this.env = env;
  }

  @Bean
  public Options getOptions() {
    Options opt = new Options();
    opt.setImagePath(env.getProperty("images.path"));
    opt.setJwtSecret(env.getProperty("oauth.jwtSecret"));
    opt.setJwtExpirationMs(Integer.parseInt(env.getProperty("oauth.jwtExpirationInMs")));
    return opt;
  }
}
