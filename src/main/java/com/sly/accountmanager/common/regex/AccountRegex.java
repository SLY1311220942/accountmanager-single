package com.sly.accountmanager.common.regex;

/**
 * 账单正则验证
 * 
 * @author sly
 * @time 2019年1月20日
 */
public class AccountRegex {
	/**
	 * 账单-账单金额正则验证
	 */
	public static final String BILLAMOUNT_REGEX = "(^[\\d]{1,10}$)|(^[\\d]{1,10}.[\\d]{1,2}$)";
	/**
	 * 账单-收支类型正则验证
	 */
	public static final String REVEXPTYPE_REGEX = "^0|1$";
}
