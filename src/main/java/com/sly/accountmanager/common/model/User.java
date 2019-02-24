package com.sly.accountmanager.common.model;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * @author sly
 * @time 2018年11月12日
 */
public class User implements Serializable {

	private static final long serialVersionUID = -1519276022351715154L;
	/**
	 * varchar(32) NOT NULL用户id uuid
	 */
	private String userId;
	/**
	 * varchar(32) NULL用户名称
	 */
	private String username;
	/**
	 * varchar(32) NULL密码
	 */
	private String password;
	/**
	 * varchar(32) NULL用户昵称
	 */
	private String nickname;
	/**
	 * varchar(32) NULL真实姓名
	 */
	private String realname;
	/**
	 * varchar(32) NULL手机号
	 */
	private String phone;
	/**
	 * varchar(32) NULL座机号
	 */
	private String tel;
	/**
	 * varchar(64) NULL邮箱
	 */
	private String email;
	/**
	 * char(1) NULL性别:M.男 W.女
	 */
	private String sex;
	/**
	 * varchar(24) NULL创建时间
	 */
	private String createTime;
	/**
	 * varchar(24) NULL更新时间
	 */
	private String updateTime;
	/**
	 * tinyint(4) NULL用户类型:1.系统内置用户 2.普通用户
	 */
	private Integer userTag;
	/**
	 * tinyint(4) NULL用户状态:0.未激活 1.激活 2.封禁
	 */
	private Integer status;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;
	/**
	 * varchar(240) NULL备注
	 */
	private String remark;
	
	/**
	 * _拓展字段用户创建起始时间
	 */
	private String startTime;
	/**
	 * _拓展字段用户创建终止时间
	 */
	private String endTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public Integer getUserTag() {
		return userTag;
	}

	public void setUserTag(Integer userTag) {
		this.userTag = userTag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
