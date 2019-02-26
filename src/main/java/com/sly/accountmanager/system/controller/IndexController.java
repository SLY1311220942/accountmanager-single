package com.sly.accountmanager.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.service.FuncService;

/**
 * 索引controller
 * @author 13112
 * @time 2018年11月12日
 */
@Controller
public class IndexController {
	@Autowired
	private FuncService funcService;
	
	/**
	 * 跳转至系统管理主页
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	@RequestMapping("/index/home")
	public String home(HttpServletRequest request,HttpServletResponse response) {
		//当前用户功能菜单暂时查数据库
		User user = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
		BaseResult funcsMap = funcService.findUserFunc(user.getUserId(), user.getUserTag());
		request.setAttribute("funcs", funcsMap.getValue("funcs"));
		return "/system/home/home.jsp";
	}
	
	@RequestMapping("/index/homeold")
	public String homeold(HttpServletRequest request,HttpServletResponse response) {
		//当前用户功能菜单暂时查数据库
		User user = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
		BaseResult funcsMap = funcService.findUserFunc(user.getUserId(), user.getUserTag());
		request.setAttribute("funcs", funcsMap.getValue("funcs"));
		return "/system/home/home-old.jsp";
	}
	
	/**
	 * 跳转到欢迎页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@RequestMapping("/index/welcome")
	public String welcome(HttpServletRequest request,HttpServletResponse response) {
		return "/system/home/welcome.jsp";
	}
}