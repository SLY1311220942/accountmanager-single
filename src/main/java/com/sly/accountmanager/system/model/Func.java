package com.sly.accountmanager.system.model;

import java.io.Serializable;
import java.util.List;

/**
 * 功能实体类
 * @author PC
 * @time 2018-11-12
 */
public class Func implements Serializable {

	private static final long serialVersionUID = -1520837006269214546L;
	
	/**
	 * varchar(32) NOT NULL功能id uuid
	 */
	private String funcId;
	/**
	 * varchar(32) NULL添加用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL添加用户名称
	 */
	private String username;
	/**
	 * varchar(32) NULL功能名称
	 */
	private String funcName;
	/**
	 * varchar(32) NULL功能图标
	 */
	private String funcIcon;
	/**
	 * varchar(240) NULL功能url
	 */
	private String funcUrl;
	/**
	 * tinyint(4) NULL功能标签:0.菜单 1.按钮
	 */
	private Integer funcTag;
	/**
	 * varchar(32) NULL父功能id
	 */
	private String parentId;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * varchar(24) NULL修改时间
	 */
	private String updateTime;
	/**
	 * tinyint(4) NULL功能排序
	 */
	private Integer funcSort;
	/**
	 * tinyint(4) NULL是否启用:0.未启用 1.启用
	 */
	private Integer isOpen;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;
	/**
	 * tinyint(4) NULL功能类型:0.系统内置 1.普通功能
	 */
	private Integer funcType;
	/**
	 * varchar(240) NULL备注
	 */
	private String remark;
	
	/**
	 *  拓展属性子功能
	 */
	private List<Func> childrenFunc;
	
	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
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

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncIcon() {
		return funcIcon;
	}

	public void setFuncIcon(String funcIcon) {
		this.funcIcon = funcIcon;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public Integer getFuncTag() {
		return funcTag;
	}

	public void setFuncTag(Integer funcTag) {
		this.funcTag = funcTag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public Integer getFuncSort() {
		return funcSort;
	}

	public void setFuncSort(Integer funcSort) {
		this.funcSort = funcSort;
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

	public Integer getFuncType() {
		return funcType;
	}

	public void setFuncType(Integer funcType) {
		this.funcType = funcType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Func> getChildrenFunc() {
		return childrenFunc;
	}

	public void setChildrenFunc(List<Func> childrenFunc) {
		this.childrenFunc = childrenFunc;
	}

	public String getName() {
		return funcName;
	}
	
	public Integer getIsParent() {
		return funcTag == 0 ? 1 : 0;
	}
}
