package com.sly.accountmanager.account.model;

import java.io.Serializable;

/**
 * 财务报告模型类(用于封装返回数据)
 * 
 * @author sly
 * @time 2019年3月30日
 */
public class BillReport implements Serializable {
	private static final long serialVersionUID = -7850687928280970896L;
	/** 统计方式(0日期,1类型) */
	private String statisticType;
	/** 日期时间 */
	private String dateTime;
	/** 日期时间类型 */
	private String dataTimeType;
	/** 账单类别ID */
	private String billTypeId;
	/** 账单类别名称 */
	private String billTypeName;
	/** 汇总金额 */
	private String billAmount;
	/** 账单笔数 */
	private String billCount;
	/** 统计开始时间 */
	private String beginTime;
	/** 统计结束时间 */
	private String endTime;
	/** 用户id */
	private String userId;

	public String getStatisticType() {
		return statisticType;
	}

	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}

	public String getDateTime() {
		return dateTime;
	}
	
	public String getDataTimeType() {
		return dataTimeType;
	}

	public String getDataTimeTypeStr() {
		if("0".equals(dataTimeType)) {
			//年
			return "%Y";
		}else if("2".equals(dataTimeType)) {
			//月
			return "%Y-%m";
		}else if("4".equals(dataTimeType)) {
			//日
			return "%Y-%m-%d";
		}else {
			return "%Y-%m";
		}
	}

	public void setDataTimeType(String dataTimeType) {
		this.dataTimeType = dataTimeType;
	}

	public String getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(String billTypeId) {
		this.billTypeId = billTypeId;
	}

	public String getBillTypeName() {
		return billTypeName;
	}

	public void setBillTypeName(String billTypeName) {
		this.billTypeName = billTypeName;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}

	public String getBillCount() {
		return billCount;
	}

	public void setBillCount(String billCount) {
		this.billCount = billCount;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
