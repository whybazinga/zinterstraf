package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

	@Query(value="SELECT u FROM User u " +
			"INNER JOIN u.userRoles as ulr ON ulr. = lur.id_user " +
			"INNER JOIN UserRoles ur ON ur.id = lur.id_role " +
			"WHERE ur.role = :role")
	List<User> findUsersByRole(@Param("role") String role);
}
