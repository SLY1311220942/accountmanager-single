package com.sly.accountmanager.common.message.system;

public class RoleMessage {
	/**
	 * 角色名称只能是中文数字字母，最大长度32位!
	 */
	public static final String ROLENAME_IS_NOT_VALIDATE = "角色名称只能是中文数字字母，最大长度32位!";
	/**
	 * 角色名称不能为空
	 */
	public static final String ROLENAME_IS_NOT_NULL = "角色名称不能为空!";
	/**
	 * 是否启用只能是启用和禁用
	 */
	public static final String ISOPEN_IS_NOT_VALIDATE = "是否启用只能是启用和禁用!";
	/**
	 * 是否启用不能为空
	 */
	public static final String ISOPEN_IS_NOT_NULL = "是否启用不能为空!";
	/**
	 * 备注信息不得超过240个字符
	 */
	public static final String REMARK_IS_NOT_VALIDATE = "备注信息不得超过240个字符!";

}
