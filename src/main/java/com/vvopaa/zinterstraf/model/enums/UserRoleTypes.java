package com.vvopaa.zinterstraf.model.enums;

import java.io.Serializable;

public enum UserRoleTypes implements Serializable {
  USER("ROLE_USER"),
  MODERATOR("ROLE_MODERATOR"),
  ADMIN("ROLE_ADMIN");

  private final String userProfileType;

  private UserRoleTypes(String userProfileType) {
    this.userProfileType = userProfileType;
  }

  public String getValue() {
    return userProfileType;
  }

}
