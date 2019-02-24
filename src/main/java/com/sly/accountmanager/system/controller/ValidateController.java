package com.sly.accountmanager.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.utils.vcode.BaseCaptcha;
import com.sly.accountmanager.utils.vcode.GifCaptcha;
import com.sly.accountmanager.utils.vcode.SpecCaptcha;

/**
 * 验证码生成controller
 * @author ShengLingyi
 *
 */
@Controller
@RequestMapping("/system")
public class ValidateController {
	private Logger syslogs = Logger.getLogger(this.getClass());
	
	private static final String LOGINTYPE = "l";
	private static final String REGISTTYPE = "r";
	/**
	 * 获取验证码（Gif版本）
	 * 
	 * @param response
	 */
	@RequestMapping("/validate/getGifCode")
	public void getGifCode(HttpServletResponse response,
			HttpServletRequest request,String type) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/gif");
			/**
			 * gif格式动画验证码 宽，高，位数。
			 */
			BaseCaptcha captcha = new GifCaptcha(146, 33, 4);
			// 输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			// 存入Session
			//l是登录验证码,r是注册验证码
			
			if(type != null && LOGINTYPE.equals(type)){
				session.setAttribute(CommonConstant.LOGIN_VALIDATECODE_KEY, captcha.text().toLowerCase());
			}else if(type != null && REGISTTYPE.equals(type)){
				session.setAttribute(CommonConstant.REGISTER_VALIDATECODE_KEY, captcha.text().toLowerCase());
			}
			
		} catch (Exception e) {
			syslogs.error("获取GIF验证码失败!" + e.getStackTrace());
			throw new RuntimeException("获取GIF验证码失败!");
		}
	}

	/**
	 * 获取验证码（jpg版本）
	 * 
	 * @param response
	 */
	@RequestMapping("/validate/getJPGCode")
	public void getJPGCode(HttpServletResponse response,
			HttpServletRequest request,String type) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpg");
			/**
			 * jgp格式验证码 宽，高，位数。
			 */
			BaseCaptcha captcha = new SpecCaptcha(146, 33, 4);
			// 输出
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);
			// 存入Session
			//l是登录验证码,r是注册验证码
			if(type != null && LOGINTYPE.equals(type)){
				session.setAttribute(CommonConstant.LOGIN_VALIDATECODE_KEY, captcha.text().toLowerCase());
			}else if(type != null && REGISTTYPE.equals(type)){
				session.setAttribute(CommonConstant.REGISTER_VALIDATECODE_KEY, captcha.text().toLowerCase());
			}
		} catch (Exception e) {
			syslogs.error("获取JPG验证码失败!" + e.getStackTrace());
			throw new RuntimeException("获取JPG验证码失败!");
		}
	}
}
