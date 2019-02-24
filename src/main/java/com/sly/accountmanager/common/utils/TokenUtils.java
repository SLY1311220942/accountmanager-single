package com.sly.accountmanager.common.utils;

import java.util.UUID;

/**
 * token工具类
 * @author sly
 * @time 2018年11月23日
 */
public class TokenUtils {
	/**
	 * 生成token
	 * @return
	 * @author sly
	 * @time 2018年11月23日
	 */
	public static String genToken() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		return uuid;
	}
}
