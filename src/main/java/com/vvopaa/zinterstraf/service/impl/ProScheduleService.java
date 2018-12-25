package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProSchedule;
import com.vvopaa.zinterstraf.repository.ProScheduleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProScheduleService {
  private final ProScheduleDao proScheduleDao;

  public Flux<ProSchedule> saveList(List<ProSchedule> entityList) {
    return proScheduleDao.saveAll(entityList);
  }
}
