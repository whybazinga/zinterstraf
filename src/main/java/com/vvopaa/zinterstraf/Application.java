package com.vvopaa.zinterstraf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication/*(exclude = {
  MessageSourceAutoConfiguration.class,
  PropertyPlaceholderAutoConfiguration.class
})*/
@EnableReactiveMongoRepositories
@EnableMongoAuditing
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

