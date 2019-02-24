package com.sly.accountmanager.system.controller;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.OperateModel;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.Page;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.RsaUtils;
import com.sly.accountmanager.common.utils.TokenUtils;
import com.sly.accountmanager.system.constant.RsaConstant;
import com.sly.accountmanager.system.returncode.UserReturnCode;
import com.sly.accountmanager.system.service.UserService;
import com.sly.accountmanager.system.validate.UserValidate;
import com.sly.accountmanager.token.SystemToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * 用户controller
 * 
 * @author sly
 * @time 2018年12月19日
 */
@Controller
@RequestMapping("/system")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidate userValidate;
	/**
	 * 去用户管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/user/toUserManage")
	public String toUserManage(HttpServletRequest request, HttpServletResponse response) {
		return "/system/user/user_manage.jsp";
	}

	/**
	 * 去新增用户页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/user/toUserAdd")
	public String toUserAdd(HttpServletRequest request, HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_USER_ADD_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_USER_ADD_TOKEN, token);
		
		Map<String, byte[]> keyMap = RsaUtils.generateKeyBytes();
		PublicKey publicKey = RsaUtils.restorePublicKey(keyMap.get(RsaUtils.PUBLIC_KEY));
		PrivateKey privateKey = RsaUtils.restorePrivateKey(keyMap.get(RsaUtils.PRIVATE_KEY));
		request.setAttribute(RsaConstant.USER_ADD_PUBLICK_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
		request.getSession().setAttribute(RsaConstant.USER_ADD_PRIVATE_KEY,privateKey);
		
		return "/system/user/user_add.jsp";
	}

	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/user/userAdd")
	public BaseResult userAdd(HttpServletRequest request, HttpServletResponse response, User user, String token) {
		try {
			//验证token
			String systemUserAddToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_USER_ADD_TOKEN); 
			if(systemUserAddToken == null || !systemUserAddToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//获取私钥用于解密
			PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute(RsaConstant.USER_ADD_PRIVATE_KEY);
			String username = RsaUtils.rsaDecode(privateKey, Base64.decodeBase64(user.getUsername()));
			String password = RsaUtils.rsaDecode(privateKey, Base64.decodeBase64(user.getPassword()));
			user.setUsername(username);
			user.setPassword(password);
			
			//验证参数
			BaseResult validateResult = userValidate.validateUserAdd(user);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_USER_MODEL, sessionUser);
			
			BaseResult result = userService.saveUser(user, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserReturnCode.USER_ADD_FAILED);
		}
	}

	/**
	 * 去修改用户页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/user/toUserUpdate")
	public String toUserUpdate(HttpServletRequest request, HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_USER_UPDATE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_USER_UPDATE_TOKEN, token);
		return "/system/user/user_update.jsp";
	}

	/**
	 * 修改用户
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/user/userUpdate")
	public BaseResult userUpdate(HttpServletRequest request, HttpServletResponse response, User user, String token) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserReturnCode.USER_UPDATE_FAILED);
		}
	}
	
	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/user/userDelete")
	public BaseResult userDelete(HttpServletRequest request, HttpServletResponse response, String userId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserReturnCode.USER_DELETE_FAILED);
		}
	}
	
	/**
	 * 去用户详情页面
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/user/toUserDetail")
	public String toUserDetail(HttpServletRequest request, HttpServletResponse response, String userId) {
		request.setAttribute("userId", userId);
		return "/system/user/user_detail.jsp";
	}
	
	/**
	 * 获取用户详情
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/user/userDetai")
	public BaseResult userDetai(HttpServletRequest request, HttpServletResponse response, String userId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserReturnCode.USER_QUERY_BYID_FAILED);
		}
	}
	
	/**
	 * 分页查询用户列表
	 * @param request
	 * @param response
	 * @param page
	 * @param user
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	@ResponseBody
	@RequestMapping("/user/findUserList")
	public BaseResult findUserList(HttpServletRequest request, HttpServletResponse response,Page page,User user) {
		try {
			//封装参数
			Map<String, Object> params = new HashMap<>(16);
			params.put("page", page);
			params.put("user", user);
			
			//查询
			BaseResult result = userService.findUserList(params);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserReturnCode.USER_QUERY_LIST_FAILED);
		}
	}
}
