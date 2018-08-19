package com.vvopaa.zinterstraf.service.impl;

import com.vvopaa.zinterstraf.model.ProSchedule;
import com.vvopaa.zinterstraf.repository.ProScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ProScheduleService {

  @Autowired
  private ProScheduleDao proScheduleDao;

  public List<ProSchedule> saveList(List<ProSchedule> entityList) {
    return Optional.ofNullable(entityList).isPresent() ? proScheduleDao.saveAll(entityList) : null;
  }
}
