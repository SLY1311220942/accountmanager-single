package com.sly.accountmanager.system.service;

import java.util.List;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * _角色功能关系service接口
 * @author sly
 * @time 2018年11月12日
 */
public interface RoleFuncService {
	
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
	BaseResult saveRoleFunc(List<String> funcIds,String roleId,User sessionUser,OperateLog operateLog);
	
	
}
