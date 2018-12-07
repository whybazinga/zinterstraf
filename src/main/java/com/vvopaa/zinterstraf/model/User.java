package com.vvopaa.zinterstraf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vvopaa.zinterstraf.model.enums.UserStatuses;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;
import java.util.stream.Collectors;

@Document
@Setter
@NoArgsConstructor
public class User extends AbstractEntity implements UserDetails {
	private String username;

	private String password;

	private int accStatus;

	private boolean enabled;

	private Set<UserRole> roles = new HashSet<>();

	public User(String username) {
		this.username = username;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, Set<UserRole> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(authority -> new SimpleGrantedAuthority(authority.getRole())).collect(Collectors.toSet());
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}
}
