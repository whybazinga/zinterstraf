package com.vvopaa.universalsite.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  

	public static boolean isNotEmptyString(String str) {
		return str != null && !str.trim().isEmpty();
	}
	
	public static boolean isStringEmail(String str) {
		if(isNotEmptyString(str)) {
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);  
			Matcher matcher = pattern.matcher(str);
			return matcher.matches();
		}
		return false;
	}
	
	public static boolean isStringProperUserPass(String str) {
		if(isNotEmptyString(str)) {
			return str.length() > 4 && str.length() < 33 ? true : false;
		}
		return false;
	}
	
	public static String getProperString(String str) {
		return isNotEmptyString(str) ? str.trim() : "";
	}
}
