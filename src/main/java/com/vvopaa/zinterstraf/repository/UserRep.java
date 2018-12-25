package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRep extends ReactiveMongoRepository<User, Long> {
  Mono<User> findByUsername(String username);
}
