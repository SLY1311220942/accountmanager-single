package com.sly.accountmanager.system.service;

import java.util.Map;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 用户service接口
 * @author sly
 * @time 2018年11月12日
 */
public interface UserService {
	/**
	 * 保存用户信息
	 * @param params(user)
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult saveUser(User user,User sessionUser,OperateLog operateLog);
	
	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	BaseResult findUserById(String userId);
	
	/**
	 * 分页查询用户列表
	 * @param params(page,user)
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	BaseResult findUserList(Map<String, Object> params);
	
	/**
	 * 逻辑删除用户
	 * @param userId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	BaseResult deleteUser(String userId,User sessionUser,OperateLog operateLog);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult updateUser(User user,User sessionUser,OperateLog operateLog);
}
