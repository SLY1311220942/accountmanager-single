package com.sly.accountmanager.system.controller;

import java.util.List;

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
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.model.ZtreeNode;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.FeignBeanUtils;
import com.sly.accountmanager.common.utils.TokenUtils;
import com.sly.accountmanager.system.model.Func;
import com.sly.accountmanager.system.returncode.FuncReturnCode;
import com.sly.accountmanager.system.service.FuncService;
import com.sly.accountmanager.system.utils.UserServiceZtreeFactory;
import com.sly.accountmanager.system.validate.FuncValidate;
import com.sly.accountmanager.token.SystemToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * 系统功能controller
 * 
 * @author sly
 * @time 2018年12月18日
 */
@Controller
@RequestMapping("/system")
public class FuncController {
	private Logger logger = Logger.getLogger(FuncController.class);

	@Autowired
	private FuncService funcService;
	
	@Autowired
	private FuncValidate funcValidate;
	@Autowired
	private UserServiceZtreeFactory userServiceZtreeFactory;
	
	/**
	 *   获取所有功能ztree树
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/func/findAllFuncTree")
	public BaseResult findAllFuncTree(HttpServletRequest request, HttpServletResponse response) {
		try {
			BaseResult result = funcService.findAllFuncTree();
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_ALLFUNCTREE_FAILED);
		}
	}
	
	/**
	 *  获取所有顶层功能列表
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/func/findAllTopFunc")
	public BaseResult findTopFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			BaseResult result = funcService.findAllTopFunc();
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_TOPFUNC_FAILED);
		}
	}
	
	/**
	 * 获取所有子功能
	 * @param request
	 * @param response
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/func/findChildFunc")
	public Object findChildFunc(HttpServletRequest request, HttpServletResponse response,String funcId) {
		try {
			BaseResult result = funcService.findAllChildFunc(funcId);
			List<Func> funcs = FeignBeanUtils.getBaseResultArray("funcs", Func.class, result);
			return funcs;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_FUNCCHILD_FAILED);
		}
	}
	
	/**
	 * 获取用户功能列表菜单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月18日
	 */
	@ResponseBody
	@RequestMapping("/func/findUserMean")
	public BaseResult findUserMean(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			int funcType = 1;
			if (user.getUserTag() == 1) {
				// 系统内置用户
				funcType = 0;
			}
			
			String adminName = "AdminSLY";
			
			if (adminName.equals(user.getUsername())) {
				// 如果是管理员返回所有功能
				BaseResult result = funcService.findAllFunc();
				return result;
			}
			BaseResult result = funcService.findUserFunc(user.getUserId(), funcType);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_USERMEAN_FAILED);
		}
	}

	/**
	 * 去功能管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/func/toFuncManage")
	public String toFuncManage(HttpServletRequest request, HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_FUNC_DELETE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_FUNC_DELETE_TOKEN, token);
		return "/system/func/func_manage.jsp";
	}

	/**
	 * 去功能新增页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/func/toFuncAdd")
	public String toFuncAdd(HttpServletRequest request, HttpServletResponse response,String funcId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_FUNC_ADD_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_FUNC_ADD_TOKEN, token);
		request.setAttribute("parentId", funcId);
		return "/system/func/func_add.jsp";
	}

	/**
	 * 新增功能
	 * 
	 * @param request
	 * @param response
	 * @param func
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/func/funcAdd")
	public BaseResult funcAdd(HttpServletRequest request, HttpServletResponse response, Func func, String token) {
		try {
			//验证token
			String systemFuncAddToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_FUNC_ADD_TOKEN); 
			if(systemFuncAddToken == null || !systemFuncAddToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = funcValidate.validateFuncAdd(func);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_FUNC_MODEL, sessionUser);
			
			BaseResult result = funcService.saveFunc(func, sessionUser, operateLog);
			
			//新增成功清除token
			if(result.getStatus() == ResultStatus.SUCCESS) {
				request.getSession().removeAttribute(SystemToken.SYSTEM_FUNC_ADD_TOKEN);
			}
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_ADD_FAILED);
		}
	}

	/**
	 * 去功能修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@RequestMapping("/func/toFuncUpdate")
	public String toFuncUpdate(HttpServletRequest request, HttpServletResponse response,String funcId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(SystemToken.SYSTEM_FUNC_UPDATE_TOKEN, token);
		request.setAttribute(SystemToken.SYSTEM_FUNC_UPDATE_TOKEN, token);
		request.setAttribute("funcId", funcId);
		return "/system/func/func_update.jsp";
	}

	/**
	 * 修改功能
	 * 
	 * @param request
	 * @param response
	 * @param func
	 * @param token
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/func/funcUpdate")
	public BaseResult funcUpdate(HttpServletRequest request, HttpServletResponse response, Func func, String token) {
		try {
			//验证token
			String systemFuncUpdateToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_FUNC_UPDATE_TOKEN); 
			if(systemFuncUpdateToken == null || !systemFuncUpdateToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = funcValidate.validateFuncUpdate(func);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_FUNC_MODEL, sessionUser);
			
			BaseResult result = funcService.updateFunc(func, sessionUser, operateLog);
			
			//新增成功清除token
			if(result.getStatus() == ResultStatus.SUCCESS) {
				request.getSession().removeAttribute(SystemToken.SYSTEM_FUNC_UPDATE_TOKEN);
			}
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_UPDATE_FAILED);
		}
	}

	/**
	 * 删除功能
	 * 
	 * @param request
	 * @param response
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/func/funcDelete")
	public BaseResult funcDelete(HttpServletRequest request, HttpServletResponse response, String funcId,String token) {
		try {
			//验证token
			String systemFuncUpdateToken = (String) request.getSession().getAttribute(SystemToken.SYSTEM_FUNC_DELETE_TOKEN); 
			if(systemFuncUpdateToken == null || !systemFuncUpdateToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = funcValidate.validateFuncDelete(funcId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_FUNC_MODEL, sessionUser);
			
			BaseResult result = funcService.deleteFuncTree(funcId, sessionUser, operateLog);
			
			//新增成功重置token
			if(result.getStatus() == ResultStatus.SUCCESS) {
				String newToken = TokenUtils.genToken();
				request.getSession().setAttribute(SystemToken.SYSTEM_FUNC_DELETE_TOKEN, newToken);
				result.setValue(SystemToken.SYSTEM_FUNC_DELETE_TOKEN, newToken);
			}
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_DELETE_FAILED);
		}
	}

	/**
	 * 获取功能详情
	 * 
	 * @param request
	 * @param response
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月19日
	 */
	@ResponseBody
	@RequestMapping("/func/funcDetail")
	public BaseResult funcDetail(HttpServletRequest request, HttpServletResponse response, String funcId) {
		try {
			//验证参数
			BaseResult validateResult = funcValidate.validateFuncDetail(funcId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			//查询功能详情
			BaseResult result = funcService.findFuncById(funcId);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_BYID_FAILED);
		}
	}
	
	/**
	 * 查询角色功能树
	 * @param request
	 * @param response
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月14日
	 */
	@ResponseBody
	@RequestMapping("/func/findRoleAllFuncTree")
	public BaseResult findRoleAllFuncTree(HttpServletRequest request, HttpServletResponse response,String roleId) {
		try {
			//验证参数
			BaseResult validateResult = funcValidate.validateFuncFindRoleAllFuncTree(roleId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			//查询角色功能树
			BaseResult result1 = funcService.findAllFunc();
			BaseResult result2 = funcService.findRoleAllFuncIds(roleId);
			List<Func> allFuncs = FeignBeanUtils.getBaseResultArray("funcs", Func.class, result1);
			List<String> roleFuncIds = FeignBeanUtils.getBaseResultArray("funcIds", String.class, result2);
			//组装ztree
			List<ZtreeNode> ztreeNodes = userServiceZtreeFactory.getRoleFuncZtreeNodes(allFuncs,roleFuncIds);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcTree", ztreeNodes);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, FuncReturnCode.FUNC_QUERY_ROLEFUNCTREE_FAILED);
		}
	}
}
