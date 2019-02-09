package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.ProSchedule;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProScheduleDao extends ReactiveMongoRepository<ProSchedule, String> {

}
