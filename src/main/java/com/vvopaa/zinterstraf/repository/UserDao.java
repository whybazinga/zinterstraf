package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String email);

	@Query("SELECT ou FROM oauth_users ou " +
			"INNER JOIN link_users_roles lur ON ou.id = lur.user_id " +
			"INNER JOIN user_roles us ON us.id = lur.role_id " +
			"WHERE us.role = :role")
	List<User> findUsersByRole(@Param("role") String role);
}
