package com.sly.accountmanager.system.model;

import java.io.Serializable;

/**
 * 用户角色关系实体类
 * @author sly
 * @time 2018年11月12日
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = -5757433160604768160L;
	
	/**
	 * int(11) NOT NULL用户角色关系id 自增
	 */
	private String userRoleId;
	/**
	 * varchar(32) NULL用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL角色id
	 */
	private String roleId;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLogicDel() {
		return logicDel;
	}

	public void setLogicDel(String logicDel) {
		this.logicDel = logicDel;
	}

}
