package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * user返回code
 * 返回码开头为10000000-10000999
 * @author sly
 * @time 2018年11月14日
 */
public enum UserReturnCode implements IReturnCode {
	/**
	 * 保存用户信息失败
	 */
	USER_ADD_FAILED(10000000, "新增用户失败!"),
	/**
	 * 查询用户列表失败
	 */
	USER_QUERY_LIST_FAILED(10000001, "查询用户列表失败!"), 
	/**
	 * 查询用户失败
	 */
	USER_QUERY_BYID_FAILED(10000002, "查询用户失败!"),
	/**
	 * 删除用户失败
	 */
	USER_DELETE_FAILED(10000003, "删除用户失败!"), 
	/**
	 * 修改用户失败
	 */
	USER_UPDATE_FAILED(10000004, "修改用户失败!"), 
	
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
	private UserReturnCode(int code, String msg) {
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
