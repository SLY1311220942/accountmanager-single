package com.sly.accountmanager.system.service.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.returncode.OperateLogReturnCode;
import com.sly.accountmanager.system.service.OperateLogService;

/**
 * _操作日志service实现
 * @author sly
 * @time 2018年12月22日
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class OperateLogServiceImpl implements OperateLogService {
	private static final Logger logger = LoggerFactory.getLogger(OperateLogService.class);
	
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	/**
	 * _保存操作日志
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年1月20日
	 */
	@Override
	public BaseResult saveOperateLog(OperateLog operateLog) {
		try {
			operateLogMapper.saveOperateLog(operateLog);
			return new BaseResult(ResultStatus.FAILED, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(OperateLogReturnCode.OPERATELOG_ADD_FAILED,e);
		}
	}
	
	
}
