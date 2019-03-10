package com.sly.accountmanager.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.account.returncode.BillTypeReturnCode;
import com.sly.accountmanager.account.service.BillTypeService;
import com.sly.accountmanager.account.validate.BillTypeValidate;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.OperateModel;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.Page;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.TokenUtils;
import com.sly.accountmanager.token.AccountToken;
import com.sly.accountmanager.utils.NetWorkUtils;

/**
 * _账单类型controller
 * 
 * @author sly
 * @time 2018年12月20日
 */
@Controller
@RequestMapping("/account")
public class BillTypeController {
	private static final Logger logger = LoggerFactory.getLogger(BillTypeController.class);

	@Autowired
	private BillTypeService billTypeService;

	@Autowired
	private BillTypeValidate billTypeValidate;

	/**
	 * _去账单类型管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	@RequestMapping("/billType/toBillTypeManage")
	public String toBillTypeManage(HttpServletRequest request, HttpServletResponse response) {
		return "/account/billType/billType_manage.jsp";
	}

	/**
	 * _查询顶层账单类型列表
	 * 
	 * @param request
	 * @param response
	 * @param billType
	 * @param page
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	@ResponseBody
	@RequestMapping("/billType/findTopBillTypeList")
	public BaseResult findTopBillTypeList(HttpServletRequest request, HttpServletResponse response, BillType billType,
			Page page) {
		try {
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			billType.setUserId(sessionUser.getUserId());

			// 封装参数
			Map<String, Object> params = new HashMap<>(16);
			params.put("billType", billType);
			params.put("page", page);

			// 查询
			BaseResult result = billTypeService.findTopBillTypeList(params);

			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_QUERY_TOPLIST_FAILED);
		}
	}

	/**
	 * _查询所以顶层账单类型列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	@ResponseBody
	@RequestMapping("/billType/findAllTopBillType")
	public BaseResult findAllTopBillType(HttpServletRequest request, HttpServletResponse response) {
		try {
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			// 查询
			BaseResult result = billTypeService.findAllTopBillType(sessionUser.getUserId());

			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_QUERY_ALLTOPLIST_FAILED);
		}

	}

	/**
	 * _去新增账单类型页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	@RequestMapping("/billType/toBillTypeAdd")
	public String toBillTypeAdd(HttpServletRequest request, HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(AccountToken.ACCOUNT_BILLTYPE_ADD_TOKEN, token);
		request.setAttribute(AccountToken.ACCOUNT_BILLTYPE_ADD_TOKEN, token);
		return "/account/billType/billType_add.jsp";
	}

	/**
	 * 新增账单类型页面
	 * @param request
	 * @param response
	 * @param billType
	 * @param token
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	@ResponseBody
	@RequestMapping("/billType/billTypeAdd")
	public BaseResult billTypeAdd(HttpServletRequest request, HttpServletResponse response, BillType billType,
			String token) {
		try {
			// 验证token
			String accountBillTypeAddToken = (String) request.getSession()
					.getAttribute(AccountToken.ACCOUNT_BILLTYPE_ADD_TOKEN);
			if (accountBillTypeAddToken == null || !accountBillTypeAddToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}

			// 验证参数
			BaseResult validateResult = billTypeValidate.validateBillTypeAdd(billType);
			if (validateResult.getStatus() != 200) {
				return validateResult;
			}

			// 封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.ACCOUNT_BILLTYPE_MODEL, sessionUser);

			BaseResult result = billTypeService.saveBillType(billType, sessionUser, operateLog);

			// 添加成功移除token
			if (result.getStatus() == ResultStatus.SUCCESS) {
				request.getSession().removeAttribute(AccountToken.ACCOUNT_BILLTYPE_ADD_TOKEN);
			}

			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_ADD_FAILED);
		}
	}
	
	
	/**
	 * _去修改账单类型页面
	 * @param request
	 * @param response
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	@RequestMapping("/billType/toBillTypeUpdate")
	public String toBillTypeUpdate(HttpServletRequest request, HttpServletResponse response,String billTypeId) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(AccountToken.ACCOUNT_BILLTYPE_UPDATE_TOKEN, token);
		request.setAttribute(AccountToken.ACCOUNT_BILLTYPE_UPDATE_TOKEN, token);
		request.setAttribute("billTypeId", billTypeId);
		return "/account/billType/billType_update.jsp";
	}
	
	/**
	 * _修改账单类型
	 * @param request
	 * @param response
	 * @param billType
	 * @param token
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	@ResponseBody
	@RequestMapping("/billType/billTypeUpdate")
	public BaseResult billTypeUpdate(HttpServletRequest request, HttpServletResponse response,BillType billType,
			String token) {
		try {
			// 验证token
			String accountBillTypeUpdateToken = (String) request.getSession()
					.getAttribute(AccountToken.ACCOUNT_BILLTYPE_UPDATE_TOKEN);
			if (accountBillTypeUpdateToken == null || !accountBillTypeUpdateToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			// 验证参数
			BaseResult validateResult = billTypeValidate.validateBillTypeUpdate(billType);
			if (validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			// 封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.ACCOUNT_BILLTYPE_MODEL, sessionUser);
			
			BaseResult result = billTypeService.updateBillType(billType, sessionUser, operateLog);
			
			// 添加成功移除token
			if (result.getStatus() == ResultStatus.SUCCESS) {
				request.getSession().removeAttribute(AccountToken.ACCOUNT_BILLTYPE_UPDATE_TOKEN);
			}
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_UPDATE_FAILED);
		}

	}
	
	/**
	 * _根据ID查询账单类型详情
	 * @param request
	 * @param response
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	@ResponseBody
	@RequestMapping("/billType/findBillTypeById")
	public BaseResult findBillTypeById(HttpServletRequest request, HttpServletResponse response,String billTypeId) {
		try {
			BaseResult result = billTypeService.findBillTypeById(billTypeId);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_QUERY_BYID_FAILED);
		}
	}
	
	/**
	 * _删除账单类型信息
	 * @param request
	 * @param response
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	@ResponseBody
	@RequestMapping("/billType/billTypeDelete")
	public BaseResult billTypeDelete(HttpServletRequest request, HttpServletResponse response,String billTypeId) {
		try {
			//验证参数
			BaseResult validateResult = billTypeValidate.validateBillTypeDelete(billTypeId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			// 封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.ACCOUNT_BILLTYPE_MODEL, sessionUser);
			
			BaseResult result = billTypeService.deleteBillType(billTypeId,sessionUser,operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillTypeReturnCode.BILLTYPE_DELETE_FAILED);
		}
	}
}
