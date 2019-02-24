package com.sly.accountmanager.common.regex;

/**
 * 公共正则验证
 * 
 * @author sly
 * @time 2018年11月12日
 */
public class Regex {

	/**
	 * 手机号正则验证
	 */
	public static final String PHONE_REGEX = "^1(3|4|5|6|7|8)\\d{9}$";
	/**
	 * 座机号正则验证
	 */
	public static final String TEL_REGEX = "^0\\d{2,3}-\\d{7,8}$";
	/**
	 * 邮箱正则验证
	 */
	public static final String EMAIL_REGEX = "^(?=\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$).{1,64}$";
	/**
	 * 性别正则验证
	 */
	public static final String SEX_REGEX = "^M|W$";
	/**
	 * UUID正则验证
	 */
	public static final String UUID_REGEX = "^[\\da-zA-Z]{32}$";
	/**
	 * 日期正则验证 YY-MM-DD
	 */
	public static final String YYMMDD_REGEX = "^((((19|20)\\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$";
	/**
	 * 时间正则验证HH:mm:ss
	 */
	public static final String HHMMSS_REGEX = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
	/**
	 * 时间正则验证YY-MM-DD HH:mm:ss
	 */
	public static final String YYMMDD_HHmmss_REGEX = "^((((19|20)\\d{2})-(0?[13-9]|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))\\s(([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])$";
	
	
}
