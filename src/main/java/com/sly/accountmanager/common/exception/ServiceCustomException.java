package com.sly.accountmanager.common.exception;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * service自定义异常
 * @author sly
 * @time 2018年11月14日
 */
public class ServiceCustomException extends RuntimeException {

	private static final long serialVersionUID = 630930517125178198L;
	
	private Integer customStatus;
	private String customMessage;
	private  IReturnCode returnCode;
	
	/**
	 * 构造方法
	 * @author sly
	 * @time 2018年11月14日
	 */
	public ServiceCustomException() {
		
	}
	
	/**
	 * 构造方法
	 * @param customStatus
	 * @author sly
	 * @time 2018年11月14日
	 */
	public ServiceCustomException(Integer customStatus) {
		this.customStatus = customStatus;
	}
	
	/**
	 * 构造方法
	 * @param customStatus
	 * @param customMessage
	 * @author sly
	 * @time 2018年11月14日
	 */
	public ServiceCustomException(Integer customStatus, String customMessage) {
		this.customStatus = customStatus;
		this.customMessage = customMessage;
	}
	
	/**
	 * 构造方法
	 * @param customStatus
	 * @param customMessage
	 * @param cause
	 * @author sly
	 * @time 2018年11月14日
	 */
	public ServiceCustomException(Integer customStatus, String customMessage,Throwable cause) {
		super(cause);
		this.customStatus = customStatus;
		this.customMessage = customMessage;
	}
	
	/**
	 * 构造方法
	 * @param returnCode
	 * @param cause
	 * @author sly
	 * @time 2018年11月14日
	 */
	public ServiceCustomException(IReturnCode returnCode,Throwable cause) {
		super(cause);
		setReturnCode(returnCode);
	}

	/**
	 * 获取自定义状态
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	public Integer getCustomStatus() {
		return customStatus;
	}
	
	/**
	 * 设置自定义状态
	 * @param customStatus
	 * @author sly
	 * @time 2018年11月14日
	 */
	public void setCustomStatus(Integer customStatus) {
		this.customStatus = customStatus;
	}

	/**
	 * 获取自定义信息
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	public String getCustomMessage() {
		return customMessage;
	}

	/**
	 * 设置自定义信息
	 * @param customMessage
	 * @author sly
	 * @time 2018年11月14日
	 */
	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	
	/**
	 * 获取ReturnCode
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	public IReturnCode getReturnCode() {
		return returnCode;
	}
	
	/**
	 * 设置ReturnCode
	 * @param returnCode
	 * @author sly
	 * @time 2018年11月14日
	 */
	public void setReturnCode(IReturnCode returnCode) {
		this.customStatus=returnCode.getCode();
		this.customMessage=returnCode.getMsg();
		this.returnCode = returnCode;
	}
}
