package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.ProTeam;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProTeamDao extends ReactiveMongoRepository<ProTeam, Long> {

}
