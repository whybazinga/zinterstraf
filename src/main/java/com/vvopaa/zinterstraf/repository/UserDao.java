package com.vvopaa.zinterstraf.repository;

import com.vvopaa.zinterstraf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String email);

	@Query(value="SELECT ou FROM oauth_users ou " +
			"INNER JOIN link_users_roles lur ON ou.id = lur.id_user " +
			"INNER JOIN user_roles us ON us.id = lur.id_role " +
			"WHERE us.role = :role", nativeQuery = true)
	List<User> findUsersByRole(@Param("role") String role);
}
