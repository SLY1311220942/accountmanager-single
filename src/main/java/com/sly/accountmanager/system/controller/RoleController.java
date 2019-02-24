package com.sly.accountmanager.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sly.accountmanager.common.model.ZtreeNode;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.FeignBeanUtils;
import com.sly.accountmanager.common.utils.TokenUtils;
import com.sly.accountmanager.system.model.Role;
import com.sly.accountmanager.system.returncode.RoleReturnCode;
import com.sly.accountmanager.system.service.RoleService;
import com.sly.accountmanager.system.utils.UserServiceZtreeFactory;
import com.sly.accountmanager.system.validate.RoleValidate;
import com.sly.accountmanager.token.SystemToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * 角色controller
 * @author sly
 * @time 2018年12月19日
 */
@Controller
@RequestMapping("/system")
public class RoleController {
	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleValidate roleValidate;
	@Autowired
	private UserServiceZtreeFactory userServiceZtreeFactory;
	
	/**
	 * 去角色管理页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/role/toRoleManage")
	public String toRoleManage(HttpServletRequest request,HttpServletResponse response) {
		return "/system/role/role_manage.jsp";
	}
	
	/**
	 * 去新增角色页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/role/toRoleAdd")
	public String toRoleAdd(HttpServletRequest request,HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_ROLE_ADD_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_ROLE_ADD_TOKEN, token);
		return "/system/role/role_add.jsp";
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @param response
	 * @param role
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/role/roleAdd")
	public BaseResult roleAdd(HttpServletRequest request,HttpServletResponse response,Role role,String token) {
		try {
			//验证token
			String systemRoleAddToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_ROLE_ADD_TOKEN); 
			if(systemRoleAddToken == null || !systemRoleAddToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = roleValidate.validateRoleAdd(role);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_ROLE_MODEL, sessionUser);
			
			BaseResult result = roleService.saveRole(role, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleReturnCode.ROLE_ADD_FAILED);
		}
	}
	
	/**
	 * 去修改角色页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/role/toRoleUpdate")
	public String toRoleUpdate(HttpServletRequest request,HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_ROLE_UPDATE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_ROLE_UPDATE_TOKEN, token);
		return "/system/role/role_update.jsp";
	}
	
	/**
	 * 修改角色
	 * @param request
	 * @param response
	 * @param role
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/role/roleUpdate")
	public BaseResult roleUpdate(HttpServletRequest request,HttpServletResponse response,Role role,String token) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleReturnCode.ROLE_UPDATE_FAILED);
		}
	}
	
	/**
	 * 去角色详情页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/role/toRoleDetail")
	public String toRoleDetail(HttpServletRequest request,HttpServletResponse response,String roleId) {
		request.setAttribute("roleId", roleId);
		return "/system/role/role_detail.jsp";
	}
	
	/**
	 * 获取角色详情页面
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/role/roleDetail")
	public BaseResult roleDetail(HttpServletRequest request,HttpServletResponse response,String roleId) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleReturnCode.ROLE_QUERY_BYID_FAILED);
		}
	}
	
	/**
	 * 分页查询角色列表
	 * @param request
	 * @param response
	 * @param page
	 * @param user
	 * @return
	 * @author sly
	 * @time 2019年1月12日
	 */
	@ResponseBody
	@RequestMapping("/role/findRoleList")
	public BaseResult findRoleList(HttpServletRequest request,HttpServletResponse response,Page page,Role role) {
		try {
			//封装参数
			Map<String, Object> params = new HashMap<>(16);
			params.put("page", page);
			params.put("role", role);
			
			//查询
			BaseResult result = roleService.findRoleList(params);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleReturnCode.ROLE_QUERY_LIST_FAILED);
		}
	}
	
	/**
	 * 查询用户角色树
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	@ResponseBody
	@RequestMapping("/role/findUserAllRoleTree")
	public BaseResult findUserAllRoleTree(HttpServletRequest request, HttpServletResponse response,String userId) {
		try {
			//验证参数
			BaseResult validateResult = roleValidate.validateRoleFindUserAllRoleTree(userId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//查询角色功能树
			BaseResult result1 = roleService.findAllRole();
			BaseResult result2 = roleService.findUserAllRoleIds(userId);
			List<Role> allRoles = FeignBeanUtils.getBaseResultArray("roles", Role.class, result1);
			List<String> userRoleIds = FeignBeanUtils.getBaseResultArray("roleIds", String.class, result2);
			//组装ztree
			List<ZtreeNode> ztreeNodes = userServiceZtreeFactory.getUserRoleZtreeNodes(allRoles,userRoleIds);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "roleTree", ztreeNodes);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, RoleReturnCode.ROLE_QUERY_USERROLETREE_FAILED);
		}
	}
}
