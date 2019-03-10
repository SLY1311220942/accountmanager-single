package com.sly.accountmanager.system.service;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * _操作日志service接口
 * @author sly
 * @time 2018年12月22日
 */
public interface OperateLogService {
	/**
	 * _保存操作日志
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年1月20日
	 */
	BaseResult saveOperateLog(OperateLog operateLog);
	
	
}
