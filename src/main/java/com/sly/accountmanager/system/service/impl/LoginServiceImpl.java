package com.sly.accountmanager.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.Md5Utils;
import com.sly.accountmanager.common.utils.UUIDUtils;
import com.sly.accountmanager.system.mapper.UserMapper;
import com.sly.accountmanager.system.service.LoginService;

/**
 * 登录service实现
 * @author sly
 * @time 2018年12月13日
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @author sly
	 * @time 2018年12月13日
	 */
	@Override
	public BaseResult login(String username,String password) {
		User user = new User();
		
		//密码MD5加密
		password = Md5Utils.encodeMD5(password,Md5Utils.LOGIN_PASSWORD_SALT);
		user.setUsername(username);
		user.setPassword(password);
		User existUser = userMapper.findUserByCondition(user);
		if(existUser == null) {
			return new BaseResult(400, Message.LOGIN_INFORMATION_ERROR);
		}
		
		BaseResult result = new BaseResult(200);
		result.setValue("existUser", existUser);
		result.setValue(CommonConstant.LOGIN_ID, UUIDUtils.genUUID());
		
		return result;
	}
	
}
