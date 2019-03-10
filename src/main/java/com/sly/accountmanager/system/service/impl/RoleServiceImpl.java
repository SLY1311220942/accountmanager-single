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
import com.sly.accountmanager.common.redisHelper.RedisHelper;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.common.utils.UUIDUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.mapper.RoleMapper;
import com.sly.accountmanager.system.model.Role;
import com.sly.accountmanager.system.returncode.RoleReturnCode;
import com.sly.accountmanager.system.service.RoleService;

/**
 * _角色service实现
 * @author sly
 * @time 2018-11-12
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class RoleServiceImpl implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private OperateLogMapper operateLogMapper;
	@Autowired
	private RedisHelper redisHelper;
	
	/**
	 * _保存角色信息
	 * @param role
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult saveRole(Role role,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			role.setRoleId(UUIDUtils.genUUID());
			role.setUserId(sessionUser.getUserId());
			role.setUsername(sessionUser.getUsername());
			role.setCreateTime(createTime);
			role.setUpdateTime(createTime);
			role.setLogicDel(CommonConstant.LOGICDEL_N);
			roleMapper.saveRole(role);
			
			//记录操作日志
			String content = "角色管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 新增角色:" + JSON.toJSONString(role);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_ADD_FAILED, e);
		}
		
	}

	/**
	 * _逻辑删除角色
	 * @param roleId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult deleteRole(String roleId,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			roleMapper.deleteRole(roleId);
			redisHelper.deleteAllUserFunc();
			return new BaseResult(ResultStatus.SUCCESS, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_DELETE_FAILED, e);
		}
		
	}

	/**
	 * _修改角色
	 * @param role
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult updateRole(Role role,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			roleMapper.updateRole(role);
			return new BaseResult(ResultStatus.SUCCESS, Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_UPDATE_FAILED, e);
		}
		
	}

	/**
	 * _根据角色id查询角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult findRoleById(String roleId) {
		try {
			Role role = roleMapper.findRoleById(roleId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS,"role",role);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_QUERY_BYID_FAILED, e);
		}
	}

	/**
	 * _分页查询角色列表
	 * @param params(page,role)
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult findRoleList(Map<String, Object> params) {
		try {
			int total = roleMapper.countRoleList(params);
			List<Role> rows = null;
			if(total > 0) {
				rows = roleMapper.findRoleList(params);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS,total,rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_QUERY_LIST_FAILED, e);
		}
	}
	
	/**
	 * _获取全部角色
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	@Override
	public BaseResult findAllRole() {
		try {
			List<Role> roles = roleMapper.findAllRole();
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "roles", roles);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_QUERY_ALLROLE_FAILED, e);
		}
	}
	
	/**
	 * _查询用户角色树
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	@Override
	public BaseResult findUserAllRoleIds(String userId) {
		try {
			List<String> roleIds = roleMapper.findUserAllRoleIds(userId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "roleIds", roleIds);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(RoleReturnCode.ROLE_QUERY_USERROLE_FAILED, e);
		}

	}

	

}
