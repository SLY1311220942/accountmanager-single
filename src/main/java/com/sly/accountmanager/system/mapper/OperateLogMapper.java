package com.sly.accountmanager.system.mapper;

import com.sly.accountmanager.common.model.OperateLog;

/**
 * 操作日志mapper
 * @author sly
 * @time 2018年12月22日
 */
public interface OperateLogMapper {
	
	/**
	 * 保存操作日志
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年12月22日
	 */
	int saveOperateLog(OperateLog operateLog);
	
}
