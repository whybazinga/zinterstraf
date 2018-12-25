package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.ProPlayer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface ProPlayerDao extends ReactiveMongoRepository<ProPlayer, Long> {

}
