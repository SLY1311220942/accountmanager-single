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
import com.sly.accountmanager.system.returncode.RoleFuncReturnCode;
import com.sly.accountmanager.system.service.RoleFuncService;
import com.sly.accountmanager.system.validate.RoleFuncValidate;
import com.sly.accountmanager.token.SystemToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * 角色功能controller
 * @author sly
 * @time 2018年12月19日
 */
@Controller
@RequestMapping("/system")
public class RoleFuncController {
	private static final Logger logger = LoggerFactory.getLogger(RoleFuncController.class);
	
	@Autowired
	private RoleFuncService roleFuncService;
	
	@Autowired
	private RoleFuncValidate roleFuncValidate;
	
	/**
	 * 去分配角色功能页面
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@RequestMapping("/roleFunc/toDistributeRoleFunc")
	public String toDistributeRoleFunc(HttpServletRequest request,HttpServletResponse response,String roleId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_ROLEFUNC_DISTRIBUTE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_ROLEFUNC_DISTRIBUTE_TOKEN, token);
		request.setAttribute("roleId", roleId);
		return "/system/roleFunc/roleFunc_distribute.jsp";
	}
	
	/**
	 * 分配角色功能
	 * @param request
	 * @param response
	 * @param roleId
	 * @param funcIds
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/roleFunc/distributeRoleFunc")
	public BaseResult distributeRoleFunc(HttpServletRequest request,HttpServletResponse response,String roleId,String funcIds,String token) {
		try {
			//验证token
			String systemRoleFuncDistributeToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_ROLEFUNC_DISTRIBUTE_TOKEN); 
			if(systemRoleFuncDistributeToken == null || !systemRoleFuncDistributeToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//获取功能id list
			List<String> funcIdList = JSON.parseArray(funcIds, String.class);
			
			//验证参数
			BaseResult validateResult = roleFuncValidate.validateDistributeRoleFunc(roleId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_ROLEFUNC_MODEL, sessionUser);
			
			BaseResult result = roleFuncService.saveRoleFunc(funcIdList, roleId, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleFuncReturnCode.ROLEFUNC_SAVE_ROLEFUNC_FAILED);
		}
	}
	
	/**
	 * 去分配功能角色页面
	 * @param request
	 * @param response
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@RequestMapping("/roleFunc/toDistributeFuncRole")
	public String toDistributeFuncRole(HttpServletRequest request,HttpServletResponse response,String funcId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_FUNCROLE_DISTRIBUTE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_FUNCROLE_DISTRIBUTE_TOKEN, token);
		request.setAttribute("funcId", funcId);
		return "/system/roleFunc/funcRole_distribute.jsp";
	}
	
	/**
	 * 分配功能角色
	 * @param request
	 * @param response
	 * @param funcId
	 * @param roleIds
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/roleFunc/distributeFuncRole")
	public BaseResult distributeFuncRole(HttpServletRequest request,HttpServletResponse response,String funcId,String roleIds) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleFuncReturnCode.ROLEFUNC_SAVE_FUNCROLE_FAILED);
		}
	}
	
	/**
	 * 获取角色功能树
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/roleFunc/roleFuncTree")
	public BaseResult roleFuncTree(HttpServletRequest request,HttpServletResponse response,String roleId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleFuncReturnCode.ROLEFUNC_QUERY_ROLEFUNCTREE_FAILED);
		}
	}
	
	/**
	 * 获取功能角色树
	 * @param request
	 * @param response
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/roleFunc/funcRoleTree")
	public BaseResult funcRoleTree(HttpServletRequest request,HttpServletResponse response,String funcId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleFuncReturnCode.ROLEFUNC_QUERY_FUNCROLETREE_FAILED);
		}
	}
}
