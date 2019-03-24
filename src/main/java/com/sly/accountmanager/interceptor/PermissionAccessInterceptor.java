package com.sly.accountmanager.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.annotation.PermissionAccess;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.redisHelper.RedisHelper;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.model.Func;
import com.sly.accountmanager.system.service.FuncService;

/**
 * _权限拦截器
 * 
 * @author sly
 * @time 2019年3月9日
 */
@Component
public class PermissionAccessInterceptor implements HandlerInterceptor {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PermissionAccessInterceptor.class);

	@Autowired
	private FuncService funcService;

	@Autowired
	private RedisHelper redisHelper;

	/**
	 * _拦截前操作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 * @author sly
	 * @time 2019年3月9日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		PermissionAccess permissionAccess = handlerMethod.getMethodAnnotation(PermissionAccess.class);
		if (permissionAccess == null) {
			// 没有加权限拦截注解 放行
			return true;
		} else {
			// 验证该用户是否拥有该连接访问权限
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			if ("AdminSLY".equals(sessionUser.getUsername())) {
				// 超级管理员直接放行
				return true;
			}

			String contextPath = request.getContextPath();
			String uri = request.getRequestURI();
			String requestPage = uri.replaceFirst(contextPath, "").replaceAll("/+", "/");

			// 获取用户权限
			int funcType = 1;
			if (sessionUser.getUserTag() == 1) {
				// 系统内置用户
				funcType = 0;
			}

			// 获取用户所有URL
			BaseResult result = redisHelper.findUserFuc(sessionUser.getUserId());
			List<String> urls = new ArrayList<>();
			if (result == null) {
				result = funcService.findUserFunc(sessionUser.getUserId(), funcType);
				redisHelper.putUserFunc(sessionUser.getUserId(), result);
			}
			List<Func> funcs = (List<Func>) result.getValue("funcs");
			urls = getAllUserUrl(funcs, urls);
			
			if (urls.contains(requestPage)) {
				// 有权限放行
				return true;
			} else {
				// 无权限
				String header = request.getHeader("X-Requested-With");
				boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
				if (isAjax) {// 说明是发出的ajax 请求
					String evalStr = "location='" + basePath + "/index/noPermission'";
					BaseResult baseResult = new BaseResult(ResultStatus.SESSION_OUT, Message.LOGIN_OUTTIME, "evalStr", evalStr);
					response.getWriter().write(JSON.toJSONString(baseResult));
					return false;
				} else {
					response.sendRedirect(basePath + "/index/noPermission");
					return false;
				}
			}

		}

	}

	/**
	 * _递归获取用户URL
	 * 
	 * @param funcs
	 * @param urls
	 * @return
	 * @author sly
	 * @time 2019年3月10日
	 */
	private List<String> getAllUserUrl(List<Func> funcs, List<String> urls) {
		for (Func func : funcs) {
			urls.add(func.getFuncUrl());
			if (func.getChildrenFunc() != null && func.getChildrenFunc().size() > 0) {
				getAllUserUrl(func.getChildrenFunc(), urls);
			}
		}

		return urls;
	}

	/**
	 * _拦截后操作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 * @author sly
	 * @time 2019年3月9日
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * _拦截完成操作
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 * @author sly
	 * @time 2019年3月9日
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
