package com.sly.accountmanager.system.controller;

import java.security.PrivateKey;
import java.security.PublicKey;
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

import com.sly.accountmanager.common.WebRedisHelper;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.FeignBeanUtils;
import com.sly.accountmanager.common.utils.RsaUtils;
import com.sly.accountmanager.system.constant.RsaConstant;
import com.sly.accountmanager.system.returncode.LoginReturnCode;
import com.sly.accountmanager.system.service.LoginService;

/**
 * 登录controller
 * @author 13112
 * @time 2018年11月25日
 */
@Controller
@RequestMapping("/system")
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private WebRedisHelper webRedisHelper;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年11月25日
	 */
	@RequestMapping("/login/toLogin")
	public String toLogin(HttpServletRequest request,HttpServletResponse response) {
		Map<String, byte[]> keyMap = RsaUtils.generateKeyBytes();
		
		PublicKey publicKey = RsaUtils.restorePublicKey(keyMap.get(RsaUtils.PUBLIC_KEY));
		PrivateKey privateKey = RsaUtils.restorePrivateKey(keyMap.get(RsaUtils.PRIVATE_KEY));
		request.setAttribute(RsaConstant.PUBLICK_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
		request.getSession().setAttribute(RsaConstant.PRIVATE_KEY,privateKey);
		return "/system/login/login.jsp";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param a997d08b3 用户名
	 * @param a3dcbf670 密码
	 * @param a1ee97457 验证码
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	@ResponseBody
	@RequestMapping("/login/login")
	public BaseResult login(HttpServletRequest request,HttpServletResponse response,String a997d08b3,String a3dcbf670,String a1ee97457) {
		try {
			
		
			//获取私钥用于解密
			PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute(RsaConstant.PRIVATE_KEY);
			
			//验证验证码
			String validateCode = RsaUtils.rsaDecode(privateKey, Base64.decodeBase64(a1ee97457));
			String verifyCode = (String) request.getSession().getAttribute(CommonConstant.LOGIN_VALIDATECODE_KEY);
			if(verifyCode == null || !verifyCode.equals(validateCode)) {
				return new BaseResult(ResultStatus.FAILED, Message.VERIFY_ERROR);
			}
			
			String username = RsaUtils.rsaDecode(privateKey, Base64.decodeBase64(a997d08b3));
			String password = RsaUtils.rsaDecode(privateKey, Base64.decodeBase64(a3dcbf670));
			BaseResult result = loginService.login(username,password);
			//登录成功
			if(result.getStatus() == ResultStatus.SUCCESS) {
				//防止sessionId  劫持  登录成功后清除sessionId
				request.getSession().invalidate();
				//保存用户数据到session
				request.getSession().setAttribute(CommonConstant.SESSION_USER, result.getValue(CommonConstant.SESSION_USER));
				//保存登录id同步到redis用于单用户登录
				String loginId = (String) result.getValue(CommonConstant.LOGIN_ID);
				User existUser = FeignBeanUtils.getBaseResultObject("existUser", User.class, result);
				request.getSession().setAttribute(CommonConstant.LOGIN_ID, loginId);
				request.getSession().setAttribute(CommonConstant.SESSION_USER,existUser);
				webRedisHelper.putValue(existUser.getUserId(),CommonConstant.LOGIN_ID);
			}else {
				//重新生成公钥和私钥
				Map<String, byte[]> keyMap = RsaUtils.generateKeyBytes();
				PublicKey publicKey2 = RsaUtils.restorePublicKey(keyMap.get(RsaUtils.PUBLIC_KEY));
				PrivateKey privateKey2 = RsaUtils.restorePrivateKey(keyMap.get(RsaUtils.PRIVATE_KEY));
				request.getSession().setAttribute(RsaConstant.PRIVATE_KEY,privateKey2);
				//将公钥返回页面
				result.setValue("publicKey", Base64.encodeBase64String(publicKey2.getEncoded()));
			}
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, LoginReturnCode.LOGIN_FAILE);
		}
	}
	
}
