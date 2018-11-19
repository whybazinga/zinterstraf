package com.vvopaa.zinterstraf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vvopaa.zinterstraf.model.enums.UserStatuses;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.Column;
import java.util.*;

@Document
public class User extends AbstractEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String username;

	@Column
	@JsonIgnore
	private String password;

	@Column
	private int accStatus;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "link_user_role",
			joinColumns = { @JoinColumn(name = "id_user") },
			inverseJoinColumns = { @JoinColumn(name = "id_role") }
	)
	private Set<UserRole> userRoles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userRoles;
	}

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
		return accStatus != UserStatuses.EXPIRED.getValue();
	}

	@Override
	public boolean isAccountNonLocked() {
		return accStatus != UserStatuses.LOCKED.getValue();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return accStatus != UserStatuses.DISABLED.getValue();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
		this.accStatus = accStatus;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		if (!super.equals(o)) return false;
		User user = (User) o;
		return Objects.equals(username, user.username) &&
			Objects.equals(userRoles, user.userRoles);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), username, userRoles);
	}

	public static User create(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

	public static User create(String username, String password,Set<UserRole> userRoles) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setUserRoles(userRoles);
		return user;
	}
}
