package com.sly.accountmanager.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.sly.accountmanager.common.constant.UserAgent;

/**
 * 网络工具类
 * 
 * @author sly
 * @time 2018年12月17日
 */
public class NetWorkUtils {
	
	private static final String UNKOWN = "unkown";
	/**
	 * 获取访问ip
	 * 
	 * @param request
	 * @return
	 * @author sly
	 * @time 2018年12月17日
	 */
	public static String getClientIp(HttpServletRequest request) {
		String header = request.getHeader("X-Real-IP");
		if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase(UNKOWN)) {
			return header;
		}
		header = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase(UNKOWN)) {
			return header;
		}
		header = request.getHeader("Proxy-Client-IP");
		if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase(UNKOWN)) {
			return header;
		}
		header = request.getHeader("WL-Proxy-Client-IP");
		if (StringUtils.isNoneBlank(header) && !header.equalsIgnoreCase(UNKOWN)) {
			return header;
		}
		return request.getRemoteAddr();
	}

	/**
	 * 获取访问浏览器类型
	 * 
	 * @param request
	 * @return
	 * @author sly
	 * @time 2018年12月17日
	 */
	public static String getBrowserInfo(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		if (userAgent == null) {
			return "";
		}
		userAgent = userAgent.toUpperCase();
		if (userAgent.contains(UserAgent.MSIE) || userAgent.contains(UserAgent.TRIDENT)) {
			return "IE";
		}
		else if (userAgent.contains(UserAgent.CHROME)) {
			return "CHROME";
		} else if (userAgent.contains(UserAgent.FIREFOX)) {
			return "FIREFOX";
		} else if (userAgent.contains(UserAgent.OPERA)) {
			return "OPERA";
		} else if (userAgent.contains(UserAgent.SAFARI)) {
			return "SAFARI";
		} else if (userAgent.contains(UserAgent.QQBROWSER)) {
			return "QQBROWSER";
		}
		return "";
	}
}
