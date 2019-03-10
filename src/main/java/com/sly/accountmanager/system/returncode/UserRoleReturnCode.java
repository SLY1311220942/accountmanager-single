package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * userRole 返回 code
 * _返回码开头为10004000-10004999
 * @author sly
 * @time 2018年11月14日
 */
public enum UserRoleReturnCode implements IReturnCode {
	/**
	 * 保存用户角色关系失败
	 */
	USERROLE_SAVE_USERROLE_FAILED(10004000, "保存用户角色关系失败!"), 
	/**
	 * 保存角色用户关系失败
	 */
	USERROLE_SAVE_ROLEUSER_FAILED(10004001, "保存角色用户关系失败!"), 
	/**
	 * 查询用户角色树失败
	 */
	USERROLE_QUERY_TREE_FAILED(10004002,"查询用户角色树失败!"),
	;

	/**
	 * 错误code
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;
	
	/**
	 * 枚举构造方法
	 * @param code
	 * @param msg
	 */
	private UserRoleReturnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 获取错误code
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public int getCode() {
		return this.code;
	}
	
	/**
	 * 获取错误信息
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public String getMsg() {
		return this.msg;
	}
	
	/**
	 * 获取枚举名称
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public String getName() {
		return this.name();
	}

}
