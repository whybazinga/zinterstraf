package com.vvopaa.universalsite.model.enums;

import java.io.Serializable;

public enum UserProfileTypes implements Serializable{
    USER,
    DBA,
    ADMIN;
     
	String userProfileType;

	public String getUserProfileType() {
		return userProfileType;
	}

	public void setUserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}
	
	
}
