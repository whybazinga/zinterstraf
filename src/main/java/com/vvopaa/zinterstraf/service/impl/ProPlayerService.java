package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProPlayer;
import com.vvopaa.zinterstraf.repository.ProPlayerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProPlayerService {
  private final ProPlayerDao proPlayerDao;

  public Flux<ProPlayer> saveList(List<ProPlayer> entityList) {
    return proPlayerDao.saveAll(entityList);
  }
}
