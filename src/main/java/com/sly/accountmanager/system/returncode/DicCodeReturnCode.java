package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * dicCode返回code 
 * _返回码开头为10005000-10005999
 * @author sly
 * @time 2018年11月14日
 */
public enum DicCodeReturnCode implements IReturnCode {
	/**
	 * 查询字典树失败
	 */
	DICCODE_QUERY_TREE_FAILED(10005000, "查询字典树失败!"),;
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
	private DicCodeReturnCode(int code, String msg) {
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
