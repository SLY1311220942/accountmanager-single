package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * role 返回 code
 * 返回码开头为10001000-10001999
 * @author sly
 * @time 2018年11月14日
 */
public enum RoleReturnCode implements IReturnCode {
	/**
	 * 新增角色失败
	 */
	ROLE_ADD_FAILED(10001000,"新增角色失败!"),
	/**
	 * 删除角色失败
	 */
	ROLE_DELETE_FAILED(10001001,"删除角色失败!"), 
	/**
	 * 修改角色失败
	 */
	ROLE_UPDATE_FAILED(10001002,"修改角色失败!"), 
	/**
	 * 查询角色失败
	 */
	ROLE_QUERY_BYID_FAILED(10001003,"查询角色失败!"), 
	/**
	 * 查询角色列表失败
	 */
	ROLE_QUERY_LIST_FAILED(10001004,"查询角色列表失败!"), 
	/**
	 * 查询角色功能树失败
	 */
	ROLE_QUERY_USERROLETREE_FAILED(10001005,"查询角色功能树失败!"),
	/**
	 * 查询用户角色失败
	 */
	ROLE_QUERY_USERROLE_FAILED(10001006,"查询用户角色失败!"), 
	/**
	 * 查询所有角色失败
	 */
	ROLE_QUERY_ALLROLE_FAILED(10001007,"查询所有角色失败!"),
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
	private RoleReturnCode(int code, String msg) {
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
