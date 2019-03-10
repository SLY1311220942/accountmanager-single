package com.sly.accountmanager.system.model;

import java.io.Serializable;

/**
 * _字典code实体类
 * 
 * @author sly
 * @time 2018年11月19日
 */
public class DicCode implements Serializable {

	private static final long serialVersionUID = -78691212467203249L;
	/**
	 * int(11) NOT NULL码表id 自增
	 */
	private Integer dicCodeId;
	/**
	 * varchar(32) NULL码值
	 */
	private String dicCode;
	/**
	 * varchar(32) NULL父码值
	 */
	private String parentDicCode;
	/**
	 * varchar(240) NULL中文名称
	 */
	private String cnName;
	/**
	 * varchar(240) NULL英文名称
	 */
	private String enName;
	/**
	 * varchar(240) NULL值
	 */
	private String value;
	/**
	 * tinyint(4) NULL排序
	 */
	private Integer sort;
	/**
	 * varchar(240) NULL备注
	 */
	private String remark;
	/**
	 * tinyint(4) NULL是否启用:0.关闭 1.启用
	 */
	private Integer isOpen;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;
	/**
	 * varchar(32) NULL备用字段1
	 */
	private String projectKz1;
	/**
	 * varchar(32) NULL备用字段2
	 */
	private String projectKz2;
	/**
	 * varchar(32) NULL备用字段3
	 */
	private String projectKz3;

	public Integer getDicCodeId() {
		return dicCodeId;
	}

	public void setDicCodeId(Integer dicCodeId) {
		this.dicCodeId = dicCodeId;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public String getParentDicCode() {
		return parentDicCode;
	}

	public void setParentDicCode(String parentDicCode) {
		this.parentDicCode = parentDicCode;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getProjectKz1() {
		return projectKz1;
	}

	public void setProjectKz1(String projectKz1) {
		this.projectKz1 = projectKz1;
	}

	public String getProjectKz2() {
		return projectKz2;
	}

	public void setProjectKz2(String projectKz2) {
		this.projectKz2 = projectKz2;
	}

	public String getProjectKz3() {
		return projectKz3;
	}

	public void setProjectKz3(String projectKz3) {
		this.projectKz3 = projectKz3;
	}

}
