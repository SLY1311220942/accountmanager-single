package com.sly.accountmanager.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 用户角色关系mapper
 * @author sly
 * @time 2018-11-12
 */
public interface UserRoleMapper {
	/**
	 * 保存用户角色关系
	 * @param userId
	 * @param roleIds
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	int saveUserRole(@Param("userId") String userId,@Param("roleIds") List<String> roleIds);
	
	/**
	 * 删除用户角色关系
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	int deleteUserRole(String userId);

	

}
