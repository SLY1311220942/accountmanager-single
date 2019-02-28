package com.sly.accountmanager.account.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * BillType 返回code 10008000-10008999
 * 
 * @author sly
 * @time 2018年11月19日
 */
public enum BillTypeReturnCode implements IReturnCode {
	/**
	 * 查询顶层账单类型列表失败
	 */
	BILLTYPE_QUERY_TOPLIST_FAILED(10008000, "查询顶层账单类型列表失败!"),
	/**
	 * 保存账单类型信息失败
	 */
	BILLTYPE_ADD_FAILED(10008001, "保存账单类型信息失败!"),
	/**
	 * 查询所有顶层账单类型列表失败
	 */
	BILLTYPE_QUERY_ALLTOPLIST_FAILED(10008002, "查询所有顶层账单类型列表失败!"),
	/**
	 * 查询所有单类型列表失败
	 */
	BILLTYPE_QUERY_ALL_FAILED(10008003, "查询所有单类型列表失败!"),

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
	 * 
	 * @param code
	 * @param msg
	 */
	private BillTypeReturnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 获取错误code
	 * 
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
	 * 
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
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public String getName() {
		return this.name();
	}
}
