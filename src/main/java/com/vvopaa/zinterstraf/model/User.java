package com.vvopaa.zinterstraf.model;

import com.vvopaa.zinterstraf.model.enums.UserStatuses;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="oauth_users")
public class User extends AbstractEntity implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Column(name="username", unique = true)
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created;

	@Column(name="updated", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date updated;

	@Column(name="acc_status")
	private int accStatus;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "link_users_roles",
			joinColumns = { @JoinColumn(name = "id_user") },
			inverseJoinColumns = { @JoinColumn(name = "id_role") }
	)
	private Set<UserRoles> userRoles = new HashSet<>();

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(int accStatus) {
		this.accStatus = accStatus;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}
}
