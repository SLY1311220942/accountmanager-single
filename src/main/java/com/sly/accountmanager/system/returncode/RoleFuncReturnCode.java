package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * roleFunc 返回 code
 * 返回码开头为10003000-10003999
 * @author sly
 * @time 2018年11月14日
 */
public enum RoleFuncReturnCode implements IReturnCode {
	/**
	 * 保存角色功能关系失败
	 */
	ROLEFUNC_SAVE_ROLEFUNC_FAILED(100030000,"保存角色功能关系失败!"), 
	/**
	 * 保存功能角色关系失败
	 */
	ROLEFUNC_SAVE_FUNCROLE_FAILED(100030001,"保存功能角色关系失败!"), 
	/**
	 * 查询角色功能树失败
	 */
	ROLEFUNC_QUERY_ROLEFUNCTREE_FAILED(100030002,"查询角色功能树失败!"), 
	/**
	 * 查询功能角色树失败
	 */
	ROLEFUNC_QUERY_FUNCROLETREE_FAILED(100030003,"查询功能角色树失败!"),
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
	private RoleFuncReturnCode(int code, String msg) {
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
