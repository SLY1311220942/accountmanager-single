package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * OperateLog 返回 code
 * 返回码开头为10009000-10009999
 * @author sly
 * @time 2018年12月22日
 */
public enum OperateLogReturnCode implements IReturnCode {
	/**
	 * 保存操作日志信息失败
	 */
	OPERATELOG_ADD_FAILED(10009000, "保存操作日志信息失败!"),
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
	private OperateLogReturnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 获取错误code
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	@Override
	public int getCode() {
		return this.code;
	}
	
	/**
	 * 获取错误信息
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	@Override
	public String getMsg() {
		return this.msg;
	}
	
	/**
	 * 获取枚举名称
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	@Override
	public String getName() {
		return this.name();
	}
}
