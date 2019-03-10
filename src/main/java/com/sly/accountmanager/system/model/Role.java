package com.sly.accountmanager.system.model;

import java.io.Serializable;

/**
 * _角色实体类
 * @author sly
 * @time 2018年11月12日
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 3496713325219586902L;
	
	/**
	 * varchar(32) NOT NULL角色id uuid
	 */
	private String roleId;
	/**
	 * varchar(32) NULL添加用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL添加用户名称
	 */
	private String username;
	/**
	 * varchar(32) NULL角色名称
	 */
	private String roleName;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * varchar(24) NULL修改时间
	 */
	private String updateTime;
	/**
	 * tinyint(4) NULL是否启用:0.关闭 1.启用
	 */
	private Integer isOpen;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;
	/**
	 * varchar(240) NULL备注
	 */
	private String remark;
	
	/**
	 * _拓展字段角色创建起始时间
	 */
	private String startTime;
	/**
	 * _拓展字段角色创建终止时间
	 */
	private String endTime;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getLogicDel() {
		return logicDel;
	}

	public void setLogicDel(String logicDel) {
		this.logicDel = logicDel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	
}
