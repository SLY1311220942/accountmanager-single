package com.sly.accountmanager.common.message.system;

/**
 * 用户message
 * @author sly
 * @time 2019年1月3日
 */
public class UserMessage {
	/**
	 * 用户名不能为空
	 */
	public static final String USERNAME_IS_NOT_NULL = "用户名不能为空!";
	/**
	 * 用户名称只能输入中文、数字、字母,长度为1到32个字符
	 */
	public static final String USERNAME_IS_NOT_VALIDATE = "用户名称只能输入中文、数字、字母,长度为1到32个字符!";
	/**
	 * 用户昵称只能输入中文、数字、字母,长度为1到32个字符
	 */
	public static final String NICKNAME_IS_NOT_VALIDATE = "用户昵称只能输入中文、数字、字母,长度为1到32个字符!";
	/**
	 * 真实姓名只能输入中文、数字、字母,长度为1到32个字符
	 */
	public static final String REALNAME_IS_NOT_VALIDATE = "真实姓名只能输入中文、数字、字母,长度为1到32个字符!";
	/**
	 * 真实姓名不能为空
	 */
	public static final String REALNAME_IS_NOT_NULL = "真实姓名不能为空!";
	/**
	 * 密码至少包括字母、数字、特殊字符中的2种组合，长度为8到32个字符
	 */
	public static final String PASSWORD_IS_NOT_VALIDATE = "密码至少包括字母、数字、特殊字符中的2种组合，长度为8到32个字符!";
	/**
	 * 密码不能为空
	 */
	public static final String PASSWORD_IS_NOT_NULL = "密码不能为空!";
	/**
	 * 请输入正确的手机号
	 */
	public static final String PHONE_IS_NOT_VALIDATE = "请输入正确的手机号!";
	/**
	 * 手机号不能为空
	 */
	public static final String PHONE_IS_NOT_NULL = "手机号不能为空!";
	/**
	 * 请输入正确的座机号
	 */
	public static final String TEL_IS_NOT_VALIDATE = "请输入正确的座机号!";
	/**
	 * 请输入正确的邮箱格式，长度最大为64个字符
	 */
	public static final String EMAIL_IS_NOT_VALIDATE = "请输入正确的邮箱格式，长度最大为64个字符!";
	/**
	 * 邮箱不能为空
	 */
	public static final String EMAIL_IS_NOT_NULL = "邮箱不能为空!";
	/**
	 * 用户性别只能为男或女
	 */
	public static final String SEX_IS_NOT_VALIDATE = "用户性别只能为男或女!";
	/**
	 * 用户性别不能为空
	 */
	public static final String SEX_IS_NOT_NULL = "用户性别不能为空!";
	/**
	 * 用户类型只能为系统内置用户和普通用户
	 */
	public static final String USERTAG_IS_NOT_VALIDATE = "用户类型只能为系统内置用户和普通用户!";
	/**
	 * 用户类型不能为空
	 */
	public static final String USERTAG_IS_NOT_NULL = "用户类型不能为空!";
	/**
	 * 请选择正确的用户状态
	 */
	public static final String STATUS_IS_NOT_VALIDATE = "请选择正确的用户状态!";
	/**
	 * 用户状态不能为空
	 */
	public static final String STATUS_IS_NOT_NULL = "用户状态不能为空!";
	/**
	 * 备注信息不得超过240个字符
	 */
	public static final String REMARK_IS_NOT_VALIDATE = "备注信息不得超过240个字符!";

}
