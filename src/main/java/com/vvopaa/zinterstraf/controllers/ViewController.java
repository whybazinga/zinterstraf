package com.vvopaa.zinterstraf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class ViewController {

  @GetMapping("/")
  public Mono<String> startPage() {
    return Mono.just("index.html");
  }

}
