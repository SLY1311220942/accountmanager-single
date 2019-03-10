package com.sly.accountmanager.common.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author sly
 * @time 2018年11月12日
 */
public class UUIDUtils {
	
	/**
	 * _生成uuid
	 * @return
	 * @author sly
	 * @time 2018年11月23日
	 */
	public static String genUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		return uuid;
	}
}
