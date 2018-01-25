package com.vvopaa.zinterstraf.repository;

import java.util.List;

import com.vvopaa.zinterstraf.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDao extends JpaRepository<UserRoles, Integer> {

    UserRoles findByRole(String role);

}
