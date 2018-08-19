package com.vvopaa.zinterstraf.spring.oauth2;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean(name = "appMessages")
  public MessageSource messageSource() {
    ResourceBundleMessageSource source = new ResourceBundleMessageSource();
    source.setBasename("messages");
    source.setDefaultEncoding("UTF-8");
    return source;
  }

  private final long MAX_AGE_SECS = 3600;
/*
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedOrigins("*")
      .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
      .maxAge(MAX_AGE_SECS);
  }
*/
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/global/**")) {
      registry.addResourceHandler("/global/**")
        .addResourceLocations("classpath:/global/")
        .setCachePeriod(4600);
    }
    if (!registry.hasMappingForPattern("/resources/**")) {
      registry
        .addResourceHandler("/resources/**")
        .addResourceLocations("/resources/");
    }
    /*
    if (!registry.hasMappingForPattern("/static/**")) {
      registry.addResourceHandler("/static/**")
        .addResourceLocations("classpath:/public/")
        .setCachePeriod(4600);
    }
    */
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://petstore.swagger.io","http://zinter.ega.com"));
    configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
