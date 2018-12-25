package com.vvopaa.zinterstraf.controllers;

import com.vvopaa.zinterstraf.model.ProTeam;
import com.vvopaa.zinterstraf.service.impl.ProTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("pro-team")
@RequiredArgsConstructor
public class ProTeamController {
  private final ProTeamService proTeamService;

  @GetMapping("all")
  public Flux<ProTeam> getAllTeams() {
    return proTeamService.getAllProTeams();
  }
}
