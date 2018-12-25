package com.vvopaa.zinterstraf.util;

public class StringUtil {

  private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

  public static boolean isNotEmptyString(String str) {
    return str != null && !str.trim().isEmpty();
  }

}
