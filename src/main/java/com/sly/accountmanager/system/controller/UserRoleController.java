package com.sly.accountmanager.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.OperateModel;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.TokenUtils;
import com.sly.accountmanager.system.returncode.UserRoleReturnCode;
import com.sly.accountmanager.system.service.UserRoleService;
import com.sly.accountmanager.system.validate.UserRoleValidate;
import com.sly.accountmanager.token.SystemToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * 用户角色关系controller
 * @author sly
 * @time 2018年12月19日
 */
@Controller
@RequestMapping("/system")
public class UserRoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private UserRoleValidate userRoleValidate;
	
	/**
	 * 去分配用户角色页面
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/userRole/toDistributeUserRole")
	public String toDistributeUserRole(HttpServletRequest request,HttpServletResponse response,String userId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_USERROLE_DISTRIBUTE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_USERROLE_DISTRIBUTE_TOKEN, token);
		request.setAttribute("userId", userId);
		return "/system/userRole/userRole_distribute.jsp";
	}
	
	/**
	 * 分配用户角色
	 * @param request
	 * @param response
	 * @param userId
	 * @param roleIds
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/userRole/distributeUserRole")
	public BaseResult distributeUserRole(HttpServletRequest request,HttpServletResponse response,String userId,String roleIds,String token) {
		try {
			//验证token
			String systemUserRoleDistributeToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_USERROLE_DISTRIBUTE_TOKEN); 
			if(systemUserRoleDistributeToken == null || !systemUserRoleDistributeToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//获取角色id list
			List<String> roleIdList = JSON.parseArray(roleIds, String.class);
			
			//验证参数
			BaseResult validateResult = userRoleValidate.validateDistributeUserRole(userId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_ROLEFUNC_MODEL, sessionUser);
			
			BaseResult result = userRoleService.saveUserRole(roleIdList, userId, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserRoleReturnCode.USERROLE_SAVE_USERROLE_FAILED);
		}
		
	}
	
	/**
	 * 去分配角色用户页面
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/userRole/toDistributeRoleUser")
	public String toDistributeRoleUser(HttpServletRequest request,HttpServletResponse response,String roleId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_ROLEUSER_DISTRIBUTE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_ROLEUSER_DISTRIBUTE_TOKEN, token);
		request.setAttribute("roleId", roleId);
		return "/system/userRole/roleUser_distribute.jsp";
	}
	
	/**
	 * 分配角色用户
	 * @param request
	 * @param response
	 * @param roleId
	 * @param userIds
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/userRole/distributeRoleUser")
	public BaseResult distributeRoleUser(HttpServletRequest request,HttpServletResponse response,String roleId,String userIds) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserRoleReturnCode.USERROLE_SAVE_ROLEUSER_FAILED);
		}
	}
	
	/**
	 * 获取用户角色树
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/userRole/userRoleTree")
	public BaseResult userRoleTree(HttpServletRequest request,HttpServletResponse response,String userId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, UserRoleReturnCode.USERROLE_QUERY_TREE_FAILED);
		}
	}
}
