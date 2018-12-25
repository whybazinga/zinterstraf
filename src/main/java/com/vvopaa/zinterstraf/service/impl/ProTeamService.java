package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProTeam;
import com.vvopaa.zinterstraf.repository.ProTeamDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProTeamService {
  private final ProTeamDao proTeamdao;

  public Flux<ProTeam> saveList(List<ProTeam> entityList) {
    return proTeamdao.saveAll(entityList);
  }

  public Flux<ProTeam> getAllProTeams() {
    return proTeamdao.findAll();
  }
}
