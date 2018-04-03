package com.vvopaa.zinterstraf.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	public static boolean isNotEmptyString(String str) {
		return str != null && !str.trim().isEmpty();
	}

	public static boolean isStringProperUserPass(String str) {
		return isNotEmptyString(str) && str.length() > 4 && str.length() < 33;
	}
	
	public static String getProperString(String str) {
		return isNotEmptyString(str) ? str.trim() : "";
	}
}
