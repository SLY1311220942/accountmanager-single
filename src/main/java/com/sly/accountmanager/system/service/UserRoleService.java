package com.sly.accountmanager.system.service;

import java.util.List;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 用户角色关系service接口
 * @author sly
 * @time 2018年11月12日
 */
public interface UserRoleService {
	/**
	 * 保存用户角色关系
	 * @param roleIds
	 * @param userId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	BaseResult saveUserRole(List<String> roleIds,String userId,User sessionUser,OperateLog operateLog); 
}
