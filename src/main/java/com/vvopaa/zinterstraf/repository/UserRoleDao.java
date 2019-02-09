package com.vvopaa.zinterstraf.repository;


import com.vvopaa.zinterstraf.model.UserRole;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface UserRoleDao extends ReactiveMongoRepository<UserRole, String> {
  Mono<UserRole> findByRole(String role);
}
