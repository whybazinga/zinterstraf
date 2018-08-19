package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProTeam;
import com.vvopaa.zinterstraf.repository.ProTeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProTeamService {

  @Autowired
  private ProTeamDao proTeamdao;

  public List<ProTeam> saveList(List<ProTeam> entityList) {
    return Optional.ofNullable(entityList).isPresent() ? proTeamdao.saveAll(entityList) : null;
  }
}
