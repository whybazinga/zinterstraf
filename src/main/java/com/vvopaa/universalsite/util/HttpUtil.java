package com.vvopaa.universalsite.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	private static final String AJAX_HEADER = "XMLHttpRequest";
	private static final String HEADER_KEY = "X-Requested-With";
	
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader(HEADER_KEY);
		return requestedWith != null ? AJAX_HEADER.equals(requestedWith) : false;
	}
}
