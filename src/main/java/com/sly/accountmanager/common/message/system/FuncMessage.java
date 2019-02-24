package com.sly.accountmanager.common.message.system;

/**
 * 功能message
 * @author sly
 * @time 2018年12月24日
 */
public class FuncMessage {
	/**
	 * 功能名称不能为空
	 */
	public static final String FUNCNAME_IS_NOT_NULL = "功能名称不能为空!";
	/**
	 * 功能名只能输入中文、数字、字母,长度为1到32个字符
	 */
	public static final String FUNCNAME_IS_NOT_VALIDATE = "功能名称只能输入中文、数字、字母,长度为1到32个字符!";
	/**
	 * 功能Url不能为空
	 */
	public static final String FUNCURL_IS_NOT_NULL = "功能Url不能为空!";
	/**
	 * 功能Url只能输入数字、字母、/、_,长度为1到240个字符
	 */
	public static final String FUNCURL_IS_NOT_VALIDATE = "功能Url只能输入数字、字母、/、_,长度为1到240个字符!";
	/**
	 * 功能标签不能为空
	 */
	public static final String FUNCTAG_IS_NOT_NULL = "功能标签不能为空!";
	/**
	 * 功能标签只能为菜单或按钮
	 */
	public static final String FUNCTAG_IS_NOT_VALIDATE = "功能标签只能为菜单或按钮!";
	/**
	 * 是否启用不能为空
	 */
	public static final String ISOPEN_IS_NOT_NULL = "是否启用不能为空!";
	/**
	 * 是否启用只能为启用和未启用
	 */
	public static final String ISOPEN_IS_NOT_VALIDATE = "是否启用只能为启用和未启用!";
	/**
	 * 功能类型不能为空
	 */
	public static final String FUNCTYPE_IS_NOT_NULL = "功能类型不能为空!";
	/**
	 * 功能类型只能为系统内置和普通功能
	 */
	public static final String FUNCTYPE_IS_NOT_VALIDATE = "功能类型只能为系统内置和普通功能!";
	/**
	 * 备注不超过240个字符
	 */
	public static final String REMARK_IS_NOT_VALIDATE = "备注不超过240个字符!";
	/**
	 * 功能图标不超过32个字符
	 */
	public static final String FUNCICON_IS_NOT_VALIDATE = "功能图标不超过32个字符!";
	/**
	 * 功能排序不能为空
	 */
	public static final String FUNCSORT_IS_NOT_NULL = "功能排序不能为空!";
	/**
	 * 功能排序为大于0的正整数最多4位
	 */
	public static final String FUNCSORT_IS_NOT_VALIDATE = "功能排序为大于0的正整数,最多4位!";
	/**
	 * 功能ID不能为空
	 */
	public static final String FUNCID_IS_NOT_NULL = "功能ID不能为空!";
	/**
	 * 角色ID不能为空
	 */
	public static final String ROLEID_IS_NOT_NULL = "角色ID不能为空!";
}
