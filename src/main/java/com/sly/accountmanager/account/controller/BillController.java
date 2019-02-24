package com.sly.accountmanager.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.account.returncode.BillReturnCode;
import com.sly.accountmanager.account.service.BillService;
import com.sly.accountmanager.account.validate.BillValidate;
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
 * 账单controller
 * @author sly
 * @time 2018年11月25日
 */
@Controller
@RequestMapping("/account")
public class BillController {
	private Logger logger = Logger.getLogger(BillController.class);
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private BillValidate billValidate;
	
	/**
	 * 去账单管理页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年11月25日
	 */
	@RequestMapping("/bill/toBillManage")
	public String toBillManage(HttpServletRequest request,HttpServletResponse response) {
		return "/account/bill/bill_manage.jsp";
	}
	
	/**
	 * 分页查询账单列表
	 * @param request
	 * @param response
	 * @param bill
	 * @param page
	 * @return
	 * @author sly
	 * @time 2019年1月20日
	 */
	@ResponseBody
	@RequestMapping("/bill/findBillList")
	public BaseResult findBillList(HttpServletRequest request,HttpServletResponse response,Bill bill,Page page) {
		try {
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			bill.setUserId(sessionUser.getUserId());
			
			//处理参数
			
			
			
			//封装参数
			Map<String, Object> params = new HashMap<>(16);
			params.put("bill", bill);
			params.put("page", page);
			
			//查询
			BaseResult result = billService.findBillList(params);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_QUERY_LIST_FAILED);
		}
	}
	
	/**
	 * 去新增账单
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年1月20日
	 */
	@RequestMapping("/bill/toBillAdd")
	public String toBillAdd(HttpServletRequest request,HttpServletResponse response) {
		String token = TokenUtils.genToken();
		request.getSession().setAttribute(AccountToken.ACCOUNT_BILL_ADD_TOKEN, token);
		request.setAttribute(AccountToken.ACCOUNT_BILL_ADD_TOKEN, token);
		return "/account/bill/bill_add.jsp";
	}
	
	
	/**
	 * 新增账单
	 * @param request
	 * @param response
	 * @param bill
	 * @param token
	 * @return
	 * @author sly
	 * @time 2019年1月21日
	 */
	@ResponseBody
	@RequestMapping("/bill/billAdd")
	public BaseResult billAdd(HttpServletRequest request,HttpServletResponse response,Bill bill,String token) {
		try {
			//验证token
			String accountBillAddToken = (String) request.getSession().getAttribute(AccountToken.ACCOUNT_BILL_ADD_TOKEN); 
			if(accountBillAddToken == null || !accountBillAddToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = billValidate.validateBillAdd(bill);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_USER_MODEL, sessionUser);
			
			BaseResult result = billService.saveBill(bill, sessionUser, operateLog);
			
			//添加成功移除token
			if(result.getStatus() == ResultStatus.SUCCESS) {
				request.getSession().removeAttribute(AccountToken.ACCOUNT_BILL_ADD_TOKEN);
			}
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_ADD_FAILED);
		}
	}
	
	/**
	 * 逻辑删除账单
	 * @param request
	 * @param response
	 * @param billId
	 * @param token
	 * @return
	 * @author sly
	 * @time 2019年2月8日
	 */
	@ResponseBody
	@RequestMapping("/bill/billDelete")
	public BaseResult billDelete(HttpServletRequest request,HttpServletResponse response,Integer billId,String token) {
		try {
			//验证参数
			BaseResult validateResult = billValidate.validateBillDelete(billId);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_USER_MODEL, sessionUser);
			
			BaseResult result = billService.deleteBill(billId, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_DELETE_FAILED);
		}
	}
	
	/**
	 * 去修改账单
	 * @param request
	 * @param response
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2019年2月8日
	 */
	@RequestMapping("/bill/toBillUpdate")
	public String toBillUpdate(HttpServletRequest request,HttpServletResponse response,String billId) {
		String token = TokenUtils.genToken();
		request.setAttribute("billId", billId);
		request.setAttribute(AccountToken.ACCOUNT_BILL_UPDATE_TOKEN, token);
		request.getSession().setAttribute(AccountToken.ACCOUNT_BILL_UPDATE_TOKEN, token);
		return "/account/bill/bill_update.jsp";
	}
	
	/**
	 * 修改账单
	 * @param request
	 * @param response
	 * @param bill
	 * @param token
	 * @return
	 * @author sly
	 * @time 2019年2月11日
	 */
	@ResponseBody
	@RequestMapping("/bill/billUpdate")
	public BaseResult billUpdate(HttpServletRequest request,HttpServletResponse response,Bill bill,String token) {
		try {
			//验证token
			String accountBillUpdateToken = (String) request.getSession().getAttribute(AccountToken.ACCOUNT_BILL_UPDATE_TOKEN); 
			if(accountBillUpdateToken == null || !accountBillUpdateToken.equals(token)) {
				return new BaseResult(ResultStatus.FAILED, Message.TOKEN_OUT);
			}
			
			//验证参数
			BaseResult validateResult = billValidate.validateBillUpdate(bill);
			if(validateResult.getStatus() != 200) {
				return validateResult;
			}
			
			//封装参数
			User sessionUser = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			OperateLog operateLog = new OperateLog(NetWorkUtils.getBrowserInfo(request),
					NetWorkUtils.getClientIp(request), OperateModel.SYSTEM_USER_MODEL, sessionUser);
			
			BaseResult result = billService.updateBill(bill, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_UPDATE_FAILED);
		}
	}
	
	/**
	 * 去账单详情页
	 * @param request
	 * @param response
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2019年2月11日
	 */
	@RequestMapping("/bill/toBillDetail")
	public String toBillDetail(HttpServletRequest request,HttpServletResponse response,String billId) {
		request.setAttribute("billId", billId);
		return "/account/bill/bill_detail.jsp";
	}
	
	/**
	 * 根据id查询账单信息
	 * @param request
	 * @param response
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2019年2月11日
	 */
	@ResponseBody
	@RequestMapping("/bill/findBillById")
	public BaseResult findBillById(HttpServletRequest request,HttpServletResponse response,Integer billId) {
		try {
			BaseResult result = billService.findBillById(billId);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_QUERY_BYID_FAILED);
		}
	}
}
