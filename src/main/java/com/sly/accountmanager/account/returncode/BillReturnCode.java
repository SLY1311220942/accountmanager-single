package com.sly.accountmanager.account.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * bill返回code 
 * 返回码开头为10007000-10007999
 * @author sly
 * @time 2018年11月19日
 */
public enum BillReturnCode implements IReturnCode {

	/**
	 * 保存账单信息失败
	 */
	BILL_ADD_FAILED(10007000, "保存账单信息失败!"),
	/**
	 * 删除账单信息失败
	 */
	BILL_DELETE_FAILED(10007001, "删除账单信息失败!"),
	/**
	 * 修改账单信息失败
	 */
	BILL_UPDATE_FAILED(10007002, "修改账单信息失败!"),
	/**
	 * 查询账单失败
	 */
	BILL_QUERY_BYID_FAILED(10007003, "查询账单失败!"),
	/**
	 * 查询账单列表失败
	 */
	BILL_QUERY_LIST_FAILED(10007004, "查询账单列表失败!"), 
	/**
	 * 导入账单失败
	 */
	BILL_IMPORT_FAILED(10007005,"导入账单失败!"),
	
	
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
	private BillReturnCode(int code, String msg) {
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
