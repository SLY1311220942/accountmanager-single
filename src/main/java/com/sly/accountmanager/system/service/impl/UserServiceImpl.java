package com.sly.accountmanager.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.common.utils.Md5Utils;
import com.sly.accountmanager.common.utils.UUIDUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.mapper.UserMapper;
import com.sly.accountmanager.system.returncode.UserReturnCode;
import com.sly.accountmanager.system.service.UserService;

/**
 * _用户service实现
 * 
 * @author sly
 * @time 2018-11-12
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	/**
	 * _新增用户信息
	 * @param user
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult saveUser(User user,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			String password = user.getPassword();
			password = Md5Utils.encodeMD5(password,Md5Utils.LOGIN_PASSWORD_SALT);
			user.setUserId(UUIDUtils.genUUID());
			user.setPassword(password);
			user.setCreateTime(createTime);
			user.setUpdateTime(createTime);
			user.setLogicDel(CommonConstant.LOGICDEL_N);
			userMapper.saveUser(user);
			
			//记录操作日志
			String content = "用户管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 新增用户:" + JSON.toJSONString(user);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserReturnCode.USER_ADD_FAILED,e);
		}
	}
	
	/**
	 * _根据用户id查询用户
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年2月19日
	 */
	@Override
	public BaseResult findUserById(String userId) {
		try {
			User user = userMapper.findUserById(userId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "user", user);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserReturnCode.USER_QUERY_BYID_FAILED,e);
		}
		
	}

	/**
	 * _分页查询用户列表
	 * 
	 * @param params(page,user)
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	@Override
	public BaseResult findUserList(Map<String, Object> params) {
		try {
			int total = userMapper.countUserList(params);
			List<User> rows = null;
			if(total > 0) {
				rows = userMapper.findUserList(params);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, total, rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserReturnCode.USER_QUERY_LIST_FAILED,e);
		}
		
	}

	/**
	 * _逻辑删除用户
	 * @param userId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	@Override
	public BaseResult deleteUser(String userId,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			userMapper.deleteUser(userId);
			return new BaseResult(ResultStatus.FAILED, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserReturnCode.USER_DELETE_FAILED,e);
		}
		
	}
	
	/**
	 * _修改用户信息
	 * @param user
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult updateUser(User user,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			userMapper.updateUser(user);
			return new BaseResult(ResultStatus.FAILED, Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(UserReturnCode.USER_UPDATE_FAILED,e);
		}
	}

	

}
