package com.sly.accountmanager.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期utils
 * @author sly
 * @time 2018年12月22日
 */
public class DateUtils {
	/**
	 * 格式化日期 输出格式: 2006-4-16
	 * @param date
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	public static String formateDate(Date date) {
		String format = DateFormat.getDateInstance(DateFormat.DEFAULT).format(date);
		return format;
	}
	
	/**
	 * 格式化时间 输出格式: 2006-01-01 00:00:00
	 * @param date
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	public static String formateTime(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = simpleDateFormat.format(date);
		return format;
	}
}
