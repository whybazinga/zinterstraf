package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.model.User;
import com.vvopaa.zinterstraf.service.impl.SecurityService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class ViewController {
  private final SecurityService securityService;

  @GetMapping("/")
  public Mono<String> startPage() {
    return Mono.just("classpath:/public/index.html");
  }

  @GetMapping("/testify")
  @ResponseBody
  public Mono<User> putUser() {
    Mono<User> userTest = securityService.saveUser("kokotvdupe@mail.com","roflanebalo1");
    return userTest;
  }
}
