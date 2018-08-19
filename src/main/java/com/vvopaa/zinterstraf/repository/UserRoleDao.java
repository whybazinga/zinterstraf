package com.vvopaa.zinterstraf.repository;


import com.vvopaa.zinterstraf.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleDao extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByRole(String role);
}
