package com.sly.accountmanager.system.model;

import java.io.Serializable;

/**
 * _角色功能关系实体类
 * @author sly
 * @time 2018年11月12日
 */
public class RoleFunc implements Serializable {

	private static final long serialVersionUID = 7098669722557099177L;
	
	/**
	 * int(11) NOT NULL角色功能关系id 自增
	 */
	private String roleFuncId;
	
	/**
	 * varchar(32) NULL角色id
	 */
	private String roleId;
	
	/**
	 * varchar(32) NULL功能id
	 */
	private String funcId;
	
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;

	
	public String getRoleFuncId() {
		return roleFuncId;
	}

	public void setRoleFuncId(String roleFuncId) {
		this.roleFuncId = roleFuncId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
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
