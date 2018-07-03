package com.vvopaa.zinterstraf.repository;


import com.vvopaa.zinterstraf.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRoles, Long> {

    UserRoles findByRole(String role);
}
