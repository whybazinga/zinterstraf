package com.vvopaa.universalsite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vvopaa.universalsite.model.enums.UserRoleTypes;

@Entity
@Table(name="user_roles")
public class UserRoles extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	 
    @Column(name="role", unique = true)
    private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
