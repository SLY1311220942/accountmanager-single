package com.sly.accountmanager.system.service;

import java.util.Map;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.model.Role;

/**
 * _角色service接口
 * @author sly
 * @time 2018年11月12日
 */
public interface RoleService {
	/**
	 * _保存角色信息
	 * @param role
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult saveRole(Role role,User sessionUser,OperateLog operateLog);
	
	/**
	 * _逻辑删除角色
	 * @param roleId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult deleteRole(String roleId,User sessionUser,OperateLog operateLog);
	
	/**
	 * _修改角色
	 * @param role
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult updateRole(Role role,User sessionUser,OperateLog operateLog);
	
	/**
	 * _根据角色id查询角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult findRoleById(String roleId);
	
	/**
	 * _分页查询角色列表
	 * @param params(page,role)
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult findRoleList(Map<String, Object> params);
	
	/**
	 * _获取全部角色
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	BaseResult findAllRole();

	/**
	 * _查询用户角色树
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	BaseResult findUserAllRoleIds(String userId);
	
	
	
}
