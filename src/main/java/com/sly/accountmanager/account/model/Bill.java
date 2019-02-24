package com.sly.accountmanager.account.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 账单实体类
 * 
 * @author sly
 * @time 2018年11月19日
 */
public class Bill implements Serializable {

	private static final long serialVersionUID = 5168911892146760466L;

	/**
	 * int(11) NOT NULL主键自增
	 */
	private Integer billId;
	/**
	 * tinyint(4) NULL收支类型:0.支出 1.收入
	 */
	private Integer revexpType;
	/**
	 * varchar(32) NULL账单类型code
	 */
	private String billTypeId;
	/**
	 * varchar(32) NULL账单类型名称
	 */
	private String billTypeName;
	/**
	 * varchar(24) NULL账单时间
	 */
	private String billTime;
	/**
	 * decimal(16,2) NULL账单金额
	 */
	private BigDecimal billAmount;
	/**
	 * varchar(240) NULL账单摘要
	 */
	private String billDetail;
	/**
	 * varchar(240) NULL备注
	 */
	private String remark;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * varchar(24) NULL更新时间
	 */
	private String updateTime;
	/**
	 * varchar(24) NULL删除时间
	 */
	private String deleteTime;
	/**
	 * varchar(32) NULL用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL用户名
	 */
	private String userName;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;
	
	/**
	 * _拓展字段 账单起始时间
	 */
	private String startBillTime;
	/**
	 * _拓展字段 账单截止时间
	 */
	private String endBillTime;
	/**
	 * _拓展字段 账单创建起始时间
	 */
	private String startCreateTime;
	/**
	 * _拓展字段 账单创建截止时间
	 */
	private String endCreateTime;
	/**
	 * _拓展字段 账单删除起始时间
	 */
	private String startDeleteTime;
	/**
	 * _拓展字段 账单删除截止时间
	 */
	private String endDeleteTime;
	/**
	 * _拓展字段 最小账单金额
	 */
	private BigDecimal minBillAmount;
	/**
	 * _拓展字段 最大账单金额
	 */
	private BigDecimal maxBillAmount;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getRevexpType() {
		return revexpType;
	}

	public void setRevexpType(Integer revexpType) {
		this.revexpType = revexpType;
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

	public String getBillTime() {
		return billTime;
	}

	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public String getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(String billDetail) {
		this.billDetail = billDetail;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogicDel() {
		return logicDel;
	}

	public void setLogicDel(String logicDel) {
		this.logicDel = logicDel;
	}

	public String getStartBillTime() {
		return startBillTime;
	}

	public void setStartBillTime(String startBillTime) {
		this.startBillTime = startBillTime;
	}

	public String getEndBillTime() {
		return endBillTime;
	}

	public void setEndBillTime(String endBillTime) {
		this.endBillTime = endBillTime;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getStartDeleteTime() {
		return startDeleteTime;
	}

	public void setStartDeleteTime(String startDeleteTime) {
		this.startDeleteTime = startDeleteTime;
	}

	public String getEndDeleteTime() {
		return endDeleteTime;
	}

	public void setEndDeleteTime(String endDeleteTime) {
		this.endDeleteTime = endDeleteTime;
	}

	public BigDecimal getMinBillAmount() {
		return minBillAmount;
	}

	public void setMinBillAmount(BigDecimal minBillAmount) {
		this.minBillAmount = minBillAmount;
	}

	public BigDecimal getMaxBillAmount() {
		return maxBillAmount;
	}

	public void setMaxBillAmount(BigDecimal maxBillAmount) {
		this.maxBillAmount = maxBillAmount;
	}
	
	

}
