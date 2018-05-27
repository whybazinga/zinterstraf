package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProTeam;
import com.vvopaa.zinterstraf.repository.ProTeamDao;
import com.vvopaa.zinterstraf.service.ProTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProTeamServiceImp implements ProTeamService {

  @Autowired
  private ProTeamDao proTeamdao;

  @Override
  public ProTeam saveProTeamByBuilder(ProTeam.ProTeamBuilder teamBuilder) {
    return proTeamdao.save(Optional.ofNullable(teamBuilder).orElseThrow(IllegalArgumentException::new).build());
  }
}
