package com.sly.accountmanager.common.message.account;

public class BillMessage {
	/**
	 * 账单金额不超过10位整数,最多保留2位小数
	 */
	public static final String BILLAMOUNT_IS_NOT_VALIDATE = "账单金额不超过10位整数,最多保留2位小数!";
	/**
	 * 账单金额不能为空
	 */
	public static final String BILLAMOUNT_IS_NOT_NULL = "账单金额不能为空!";
	/**
	 * 收支类型不能为空
	 */
	public static final String REVEXPTYPE_IS_NOT_NULL = "收支类型不能为空!";
	/**
	 * 收支类型只能为收入和支出
	 */
	public static final String REVEXPTYPE_IS_NOT_VALIDATE = "收支类型只能为收入和支出!";
	/**
	 * 账单时间不能为空
	 */
	public static final String BILLTIME_IS_NOT_NULL = "账单时间不能为空!";
	/**
	 * 账单时间格式错误
	 */
	public static final String BILLTIME_IS_NOT_VALIDATE = "账单时间格式错误!";
	/**
	 * 账单详情最多240个字符
	 */
	public static final String BILLDETAIL_IS_NOT_VALIDATE = "账单详情最多240个字符!";
	/**
	 * 账单详情不能为空
	 */
	public static final String BILLDETAIL_IS_NOT_NULL = "账单详情不能为空!";
	/**
	 * 账单备注最多240个字符
	 */
	public static final String BILLREMARK_IS_NOT_VALIDATE = "账单备注最多240个字符!";
	/**
	 * 账单ID不合法
	 */
	public static final String BILLID_IS_NOT_VALIDATE = "账单ID不合法!";
	/**
	 * 账单ID不能为空
	 */
	public static final String BILLID_IS_NOT_NULL = "账单ID不能为空!";

}
