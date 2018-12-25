package com.vvopaa.zinterstraf.spring.swagger;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.vvopaa.zinterstraf.controllers")) //RequestHandlerSelectors.any()
      .paths(PathSelectors.any())
      .build()
      .apiInfo(apiInfo())
      .securitySchemes(Collections.singletonList(apiKey()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("EGA swagger API").version("1.0.0").build();
  }

  private ApiKey apiKey() {
    return new ApiKey(
      "Authorization", // name: My key - Authorization
      "api_key", // keyname: api_key
      "header");
  }
}
*/