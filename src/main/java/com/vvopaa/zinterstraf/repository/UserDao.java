package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	/*
	@Query(value="SELECT u FROM User u " +
			"INNER JOIN link_users_roles lur ON lur.id_user = u.id " +
			"INNER JOIN UserRole ur ON ur.id = lur.id_role " +
			"WHERE ur.role = :role")
	List<User> findUsersByRole(@Param("role") String role);
	*/
}
