package com.sly.accountmanager.system.mapper;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.common.model.User;

/**
 * 用户mapper
 * @author sly
 * @time 2018-11-12
 */
public interface UserMapper {
	/**
	 * 保存用户信息
	 * @param user
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int saveUser(User user);
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	User findUserById(String userId);
	
	/**
	 * 根据条件查询用户数量
	 * @param params : userId,username,nickname,phone,tel,email,sex,startTime,endTime,userTag,status
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	int countUserList(Map<String, Object> params);
	
	/**
	 * 分页查询用户列表
	 * @param params : userId,username,nickname,phone,tel,email,sex,startTime,endTime,userTag,status,startNum,pageSize
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	List<User> findUserList(Map<String, Object> params);
	
	/**
	 * 逻辑删除用户
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	int deleteUser(String userId);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int updateUser(User user);
	
	/**
	 * 根据条件查询用户
	 * @param user
	 * @return
	 * @author sly
	 * @time 2018年12月16日
	 */
	User findUserByCondition(User user);

	
}