package com.sly.accountmanager.system.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * _文件实体类
 * 
 * @author sly
 * @time 2018年11月20日
 */
public class Attachment implements Serializable {

	private static final long serialVersionUID = 7196570672385361168L;

	/**
	 * varchar(32) NOT NULL主键uuid
	 */
	private String attachmentId;
	/**
	 * text NULL文件名
	 */
	private String attachmentName;
	/**
	 * varchar(32) NULL文件类型
	 */
	private String attachmentType;
	/**
	 * decimal(16,0) NULL上传进度
	 */
	private BigDecimal uploadProcess;
	/**
	 * tinyint(4) NULL传输状态:0.暂停 1.开始
	 */
	private Integer transStatus;
	/**
	 * tinyint(4) NULL文件状态:0.上传中 1.上传完成
	 */
	private Integer attachmentStatus;
	/**
	 * decimal(16,0) NULL文件大小
	 */
	private BigDecimal attachmentSize;
	/**
	 * varchar(32) NULL文件MD5校验码
	 */
	private String attachmentMD5;
	/**
	 * text NULL文件本地位置
	 */
	private String attachmentLocalPath;
	/**
	 * text NULL文件上传服务器位置
	 */
	private String attachmentUploadPath;
	/**
	 * varchar(24) NULL删除时间
	 */
	private String deleteTime;
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
	private String username;
	/**
	 * char(1) NULL逻辑删除:Y.删除 N.未删除
	 */
	private String logicDel;

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public BigDecimal getUploadProcess() {
		return uploadProcess;
	}

	public void setUploadProcess(BigDecimal uploadProcess) {
		this.uploadProcess = uploadProcess;
	}

	public Integer getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(Integer transStatus) {
		this.transStatus = transStatus;
	}

	public Integer getAttachmentStatus() {
		return attachmentStatus;
	}

	public void setAttachmentStatus(Integer attachmentStatus) {
		this.attachmentStatus = attachmentStatus;
	}

	public BigDecimal getAttachmentSize() {
		return attachmentSize;
	}

	public void setAttachmentSize(BigDecimal attachmentSize) {
		this.attachmentSize = attachmentSize;
	}

	public String getAttachmentMD5() {
		return attachmentMD5;
	}

	public void setAttachmentMD5(String attachmentMD5) {
		this.attachmentMD5 = attachmentMD5;
	}

	public String getAttachmentLocalPath() {
		return attachmentLocalPath;
	}

	public void setAttachmentLocalPath(String attachmentLocalPath) {
		this.attachmentLocalPath = attachmentLocalPath;
	}

	public String getAttachmentUploadPath() {
		return attachmentUploadPath;
	}

	public void setAttachmentUploadPath(String attachmentUploadPath) {
		this.attachmentUploadPath = attachmentUploadPath;
	}

	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLogicDel() {
		return logicDel;
	}

	public void setLogicDel(String logicDel) {
		this.logicDel = logicDel;
	}

}
