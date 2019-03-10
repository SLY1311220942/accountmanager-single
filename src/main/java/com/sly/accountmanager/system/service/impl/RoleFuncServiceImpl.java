package com.sly.accountmanager.system.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.redisHelper.RedisHelper;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.mapper.RoleFuncMapper;
import com.sly.accountmanager.system.returncode.RoleFuncReturnCode;
import com.sly.accountmanager.system.service.RoleFuncService;

/**
 * _角色功能关系service实现
 * @author sly
 * @time 2018-11-12
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class RoleFuncServiceImpl implements RoleFuncService {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleFuncService.class);
	
	@Autowired
	private RoleFuncMapper roleFuncMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	@Autowired
	private RedisHelper redisHelper;
	
	
	/**
	 * _保存角色功能关系
	 * @param funcIds
	 * @param roleId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月18日
	 */
	@Override
	public BaseResult saveRoleFunc(List<String> funcIds,String roleId,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			roleFuncMapper.deleteRoleFunc(roleId);
			if(funcIds != null && funcIds.size() > 0) {
				roleFuncMapper.saveRoleFunc(roleId,funcIds);
			}
			
			
			//记录操作日志
			String content = "角色管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 为角色:" + roleId + "绑定如下功能:" + JSON.toJSONString(funcIds);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			redisHelper.deleteAllUserFunc();
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleFuncReturnCode.ROLEFUNC_SAVE_ROLEFUNC_FAILED, e);
		}
	
	}

}
