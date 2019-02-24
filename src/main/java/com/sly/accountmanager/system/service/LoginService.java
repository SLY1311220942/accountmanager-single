package com.sly.accountmanager.system.service;

import com.sly.accountmanager.common.result.BaseResult;

/**
 * 登录service
 * @author sly
 * @time 2018年12月13日
 */
public interface LoginService {
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @author sly
	 * @time 2018年12月13日
	 */
	BaseResult login(String username,String password);
	
}
