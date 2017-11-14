package com.vvopaa.universalsite.model.enums;

import java.io.Serializable;

public enum UserRoleTypes implements Serializable{
    USER,
    MODERATOR,
    ADMIN;
     
	String userProfileType;

	public String getUserProfileType() {
		return userProfileType;
	}

	public void setUserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	
}
