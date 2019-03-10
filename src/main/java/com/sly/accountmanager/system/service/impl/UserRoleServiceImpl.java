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
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.mapper.UserRoleMapper;
import com.sly.accountmanager.system.returncode.UserRoleReturnCode;
import com.sly.accountmanager.system.service.UserRoleService;

/**
 * _用户角色关系service实现
 * @author sly
 * @time 2018-11-12
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class UserRoleServiceImpl implements UserRoleService {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	/**
	 * _保存用户角色关系
	 * @param roleIds
	 * @param userId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult saveUserRole(List<String> roleIds,String userId,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			userRoleMapper.deleteUserRole(userId);
			if(roleIds != null && roleIds.size() > 0) {
				userRoleMapper.saveUserRole(userId,roleIds);
			}
			
			//记录操作日志
			String content = "角色管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 为用户:" + userId + "绑定如下角色:" + JSON.toJSONString(roleIds);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserRoleReturnCode.USERROLE_SAVE_USERROLE_FAILED, e);
		}
	}

}
