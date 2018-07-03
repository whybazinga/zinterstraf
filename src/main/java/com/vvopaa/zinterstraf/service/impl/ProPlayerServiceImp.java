package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProPlayer;
import com.vvopaa.zinterstraf.repository.ProPlayerDao;
import com.vvopaa.zinterstraf.service.ProPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProPlayerServiceImp implements ProPlayerService {

  @Autowired
  private ProPlayerDao proPlayerDao;

  @Override
  public List<ProPlayer> saveList(List<ProPlayer> entityList) {
    return Optional.ofNullable(entityList).isPresent() ? proPlayerDao.saveAll(entityList) : null;
  }
}
