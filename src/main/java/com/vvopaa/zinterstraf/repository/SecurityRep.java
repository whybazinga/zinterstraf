package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRep extends ReactiveMongoRepository<User, Long> {

}
