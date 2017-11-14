package com.vvopaa.universalsite.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="users")
public class UserEntity extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private int accstatus;
	
	@Column(name="created")
	private Date created;
	
	@Column(name="updated")
	private Date updated;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
		name = "link_users_roles", 
		joinColumns = { @JoinColumn(name = "id_user") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_role") }
    )
    private Set<UserRole> userRoles = new HashSet<UserRole>();
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccstatus() {
		return accstatus;
	}

	public void setAccstatus(int accstatus) {
		this.accstatus = accstatus;
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
	
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + accstatus;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		result = prime * result + ((userRoles == null) ? 0 : userRoles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (accstatus != other.accstatus)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		return true;
	}
	
}
