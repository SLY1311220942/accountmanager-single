package com.sly.accountmanager.common.regex;

/**
 * 系统正则验证
 * 
 * @author sly
 * @time 2019年1月20日
 */
public class SystemRegex {
	/**
	 * 功能-功能名称正则验证
	 */
	public static final String FUNCNAME_REGEX = "^([\\u4e00-\\u9fa5a-zA-Z0-9_\\-\\(\\)\\/]){1,32}$";
	/**
	 * 功能-功能Url正则验证
	 */
	public static final String FUNCURL_REGEX = "^#$|^([a-zA-Z0-9_/]){1,240}$";
	/**
	 * 功能-功能排序正则验证
	 */
	public static final String FUNCSORT_REGEX = "^[\\d]{1,4}$";
	
	
	/**
	 * 用户-用户名称正则验证
	 */
	public static final String USERNAME_REGEX = "^([\\u4e00-\\u9fa5a-zA-Z0-9]){1,32}$";
	/**
	 * 用户-用户昵称正则验证
	 */
	public static final String NICKNAME_REGEX = "^([\\u4e00-\\u9fa5a-zA-Z0-9]){1,32}$";
	/**
	 * 用户-真实姓名正则验证
	 */
	public static final String REALNAME_REGEX = "^([\\u4e00-\\u9fa5a-zA-Z0-9]){1,32}$";
	/**
	 * 用户-用户密码正则验证
	 */
	public static final String PASSWORD_REGEX = "^(?!^[0-9]+$)(?!^[a-zA-Z]+$)(?!^[_#@.]+$).{8,32}$";
	/**
	 * 用户-用户类型正则验证
	 */
	public static final String USERTAG_REGEX = "^1|2$";
	/**
	 * 用户-用户状态正则验证
	 */
	public static final String STATUS_REGEX = "^0|1|2$";
	
	
	/**
	 * 角色-角色名称正则验证
	 */
	public static final String ROLENAME_REGEX = "^([\\d\\u4E00-\\u9FA5a-zA-Z]){1,32}$";
	/**
	 * 角色-角色是否启用正则验证
	 */
	public static final String ISOPEN_REGEX = "^0|1$";
}
