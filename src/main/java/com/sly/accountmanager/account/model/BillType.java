package com.sly.accountmanager.account.model;

import java.io.Serializable;

/**
 * 账单类型实体类
 * 
 * @author sly
 * @time 2018年11月19日
 */
public class BillType implements Serializable {

	private static final long serialVersionUID = 127954599049365301L;

	/**
	 * varchar(32) NOT NULL主键自增
	 */
	private String billTypeId;
	/**
	 * varchar(32) NULL账单类型名称
	 */
	private String billTypeName;
	/**
	 * varchar(32) NULL账单类型父id
	 */
	private String parentTypeId;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * varchar(32) NULL用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL用户名称
	 */
	private String userName;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;

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

	public String getParentTypeId() {
		return parentTypeId;
	}

	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

}
