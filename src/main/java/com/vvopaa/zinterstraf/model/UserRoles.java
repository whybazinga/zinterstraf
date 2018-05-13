package com.vvopaa.zinterstraf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vvopaa.zinterstraf.model.enums.UserRoleTypes;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="user_roles")
public class UserRoles extends AbstractEntity implements GrantedAuthority, Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="role", unique = true)
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}
}
