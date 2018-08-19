package com.vvopaa.zinterstraf.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserRole extends AbstractEntity implements GrantedAuthority {

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
