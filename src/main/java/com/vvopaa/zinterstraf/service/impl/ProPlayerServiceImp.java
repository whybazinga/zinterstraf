package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProPlayer;
import com.vvopaa.zinterstraf.repository.ProPlayerDao;
import com.vvopaa.zinterstraf.service.ProPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProPlayerServiceImp implements ProPlayerService {

  @Autowired
  private ProPlayerDao proPlayerDao;


  @Override
  public ProPlayer saveProPlayerByBuilder(ProPlayer.ProPlayerBuilder playerBuilder) {

    return proPlayerDao.save(Optional.ofNullable(playerBuilder).orElseThrow(IllegalArgumentException::new).build());
  }
}
