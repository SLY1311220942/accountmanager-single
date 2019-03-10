package com.sly.accountmanager.system.mapper;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.system.model.Role;

/**
 * _角色mapper
 * @author sly
 * @time 2018-11-12
 */
public interface RoleMapper {
	/**
	 * _保存角色信息
	 * @param role
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int saveRole(Role role);
	
	/**
	 * _逻辑删除角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int deleteRole(String roleId);
	
	/**
	 * _修改角色
	 * @param role
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int updateRole(Role role);
	
	/**
	 * _根据角色id查询角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	Role findRoleById(String roleId);
	
	/**
	 * _统计分页查询角色数量
	 * @param params : roleId,userId,username,roleName,isOpen,startTime,endTime,remark
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int countRoleList(Map<String, Object> params);
	
	/**
	 * _分页查询角色列表
	 * @param params : roleId,userId,username,roleName,isOpen,startTime,endTime,remark,startNum,pageSize
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	List<Role> findRoleList(Map<String, Object> params);
	
	/**
	 * _查询所有角色
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	List<Role> findAllRole();
	
	/**
	 * _查询用户角色id
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	List<String> findUserAllRoleIds(String userId);

}
