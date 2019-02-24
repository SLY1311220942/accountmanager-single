package com.sly.accountmanager.common.model;

import java.io.Serializable;
import java.util.Date;

import com.sly.accountmanager.common.utils.DateUtils;

/**
 * 操作日志实体类
 * 
 * @author sly
 * @time 2018年12月22日
 */
public class OperateLog implements Serializable {
	private static final long serialVersionUID = 8563626776446875826L;
	/**
	 * int(11) NOT NULL操作日志主键自增
	 */
	private Integer operateLogId;
	/**
	 * varchar(32) NULL操作用户id
	 */
	private String userId;
	/**
	 * varchar(32) NULL操作用户用户名
	 */
	private String username;
	/**
	 * varchar(32) NULL操作浏览器
	 */
	private String operatorBroswer;
	/**
	 * text NULL操作内容
	 */
	private String operatorContent;
	/**
	 * varchar(56) NULL操作IP
	 */
	private String operatorIP;
	/**
	 * varchar(32) NULL操作模块
	 */
	private String operateModel;
	/**
	 * varchar(24) NULL操作时间
	 */
	private String createTime;
	
	/**
	 * 构造方法
	 * @author sly
	 * @time 2018年12月22日
	 */
	public OperateLog() {

	}

	/**
	 * 构造方法
	 * @param operatorBroswer
	 * @param operatorIP
	 * @param operateModel
	 * @author sly
	 * @time 2018年12月22日
	 */
	public OperateLog(String operatorBroswer, String operatorIP, String operateModel,User user) {
		this.operatorBroswer = operatorBroswer;
		this.operatorIP = operatorIP;
		this.operateModel = operateModel;
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.createTime = DateUtils.formateTime(new Date());
	}

	public Integer getOperateLogId() {
		return operateLogId;
	}

	public void setOperateLogId(Integer operateLogId) {
		this.operateLogId = operateLogId;
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

	public String getOperatorBroswer() {
		return operatorBroswer;
	}

	public void setOperatorBroswer(String operatorBroswer) {
		this.operatorBroswer = operatorBroswer;
	}

	public String getOperatorContent() {
		return operatorContent;
	}

	public void setOperatorContent(String operatorContent) {
		this.operatorContent = operatorContent;
	}

	public String getOperatorIP() {
		return operatorIP;
	}

	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}

	public String getOperateModel() {
		return operateModel;
	}

	public void setOperateModel(String operateModel) {
		this.operateModel = operateModel;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
