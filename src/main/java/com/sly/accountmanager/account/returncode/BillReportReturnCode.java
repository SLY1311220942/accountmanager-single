package com.sly.accountmanager.account.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * billReport返回code 
 * 返回码开头为10010000-10010999
 * @author sly
 * @time 2018年11月19日
 */
public enum BillReportReturnCode implements IReturnCode {
	/** 按日期查询报表失败! */
	BILLREPORT_QUERY_DATE_FAILED(10010000,"按日期查询报表失败!"),

	
	
	
	
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
	private BillReportReturnCode(int code, String msg) {
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
