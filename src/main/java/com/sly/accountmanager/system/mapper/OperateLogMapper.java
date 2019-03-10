package com.sly.accountmanager.system.mapper;

import com.sly.accountmanager.common.model.OperateLog;

/**
 * _操作日志mapper
 * @author sly
 * @time 2018年12月22日
 */
public interface OperateLogMapper {
	
	/**
	 * _保存操作日志
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	int saveOperateLog(OperateLog operateLog);
	
}
