package com.sly.accountmanager.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * _角色功能关系mapper
 * @author sly
 * @time 2018-11-12
 */
public interface RoleFuncMapper {
	
	/**
	 * _保存角色功能关系
	 * @param roleId
	 * @param funcIds
	 * @return
	 * @author sly
	 * @time 2018年11月18日
	 */
	int saveRoleFunc(@Param("roleId") String roleId,@Param("funcIds") List<String> funcIds);
	
	/**
	 * _删除角色功能关系
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月15日
	 */
	int deleteRoleFunc(String roleId);

}
