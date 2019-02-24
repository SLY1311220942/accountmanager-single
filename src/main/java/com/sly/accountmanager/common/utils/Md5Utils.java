package com.sly.accountmanager.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * MD5工具类
 * @author sly
 * @time 2018年11月12日
 */
public class Md5Utils {
	public static final String LOGIN_PASSWORD_SALT = "LOGIN_PASSWORD_SALT";
	
	/**
	 * md5加密
	 * @param text
	 * @param salt
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	public static String encodeMD5(String text,String salt) {
		return StringUtils.upperCase(DigestUtils.md5Hex(text + salt));
	}
	
	/*
	 * public static void main(String[] args) {
	 * System.out.println(encodeMD5("31415926sly", LOGIN_PASSWORD_SALT)); }
	 */
}
