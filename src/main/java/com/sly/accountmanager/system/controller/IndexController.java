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
 * _索引controller
 * @author 13112
 * @time 2018年11月12日
 */
@Controller
public class IndexController {
	@Autowired
	private FuncService funcService;
	
	/**
	 * _跳转至系统管理主页
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
	
	/**
	 * _跳转到欢迎页面
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
	
	/**
	 * _去无权限页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月10日
	 */
	@RequestMapping("/index/noPermission")
	public String noPermission(HttpServletRequest request,HttpServletResponse response) {
		return "/error/noPermission.jsp";
	}
	
	/**
	 * _去404页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月10日
	 */
	@RequestMapping("/index/error_404")
	public String error_404(HttpServletRequest request,HttpServletResponse response) {
		return "/error/404.jsp";
	}
	
	/**
	 * _去500页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月10日
	 */
	@RequestMapping("/index/error_500")
	public String error_500(HttpServletRequest request,HttpServletResponse response) {
		return "/error/500.jsp";
	}
	
	/**
	 * _去没有session页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月10日
	 */
	@RequestMapping("/index/noSession")
	public String noSession(HttpServletRequest request,HttpServletResponse response) {
		return "/error/noSession.jsp";
	}
}
