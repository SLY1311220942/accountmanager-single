package com.sly.accountmanager.system.mapper;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.system.model.Role;

/**
 * 角色mapper
 * @author sly
 * @time 2018-11-12
 */
public interface RoleMapper {
	/**
	 * 保存角色信息
	 * @param role
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int saveRole(Role role);
	
	/**
	 * 逻辑删除角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int deleteRole(String roleId);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int updateRole(Role role);
	
	/**
	 * 根据角色id查询角色
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	Role findRoleById(String roleId);
	
	/**
	 * 统计分页查询角色数量
	 * @param params : roleId,userId,username,roleName,isOpen,startTime,endTime,remark
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int countRoleList(Map<String, Object> params);
	
	/**
	 * 分页查询角色列表
	 * @param params : roleId,userId,username,roleName,isOpen,startTime,endTime,remark,startNum,pageSize
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	List<Role> findRoleList(Map<String, Object> params);
	
	/**
	 * 查询所有角色
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	List<Role> findAllRole();
	
	/**
	 * 查询用户角色id
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	List<String> findUserAllRoleIds(String userId);

}
