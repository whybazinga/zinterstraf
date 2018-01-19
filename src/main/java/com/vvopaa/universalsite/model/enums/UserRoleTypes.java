package com.vvopaa.universalsite.model.enums;

import java.io.Serializable;

public enum UserRoleTypes implements Serializable{
    USER("user"),
    MODERATOR("moderator"),
    ADMIN("admin");

	private final String userProfileType;

	private UserRoleTypes(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getValue() {
		return userProfileType;
	}

}
