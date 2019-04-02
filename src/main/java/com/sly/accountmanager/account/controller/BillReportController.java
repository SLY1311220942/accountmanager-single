package com.sly.accountmanager.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.account.model.BillReport;
import com.sly.accountmanager.account.returncode.BillReportReturnCode;
import com.sly.accountmanager.account.service.BillReportService;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.model.Page;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * _财务报表管理controller
 * @author sly
 * @time 2019年3月30日
 */
@Controller
@RequestMapping("/account")
public class BillReportController {
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	
	@Autowired
	private BillReportService billReportService;
	
	/**
	 * _去财务报表管理页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	@RequestMapping("/billReport/toBillReportManage")
	public String toBillReportManage(HttpServletRequest request,HttpServletResponse response) {
		return "/account/billReport/billReport_manage.jsp";
	}
	
	/**
	 * _按日期查询报表list
	 * @param request
	 * @param response
	 * @param billReport
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	@ResponseBody
	@RequestMapping("/billReport/findReportList")
	public BaseResult findReportList(HttpServletRequest request,HttpServletResponse response,BillReport billReport,Page page) {
		try {
			if(StringUtils.isNotBlank(billReport.getEndTime())) {
				billReport.setEndTime(billReport.getEndTime() + " 23:59:59.999");
			}
			
			User user = (User) request.getSession().getAttribute(CommonConstant.SESSION_USER);
			billReport.setUserId(user.getUserId());
			BaseResult result = billReportService.findReportList(billReport,page);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReportReturnCode.BILLREPORT_QUERY_DATE_FAILED);
		}
	}
	
	/**
	 * 去财务报表详情页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2019年3月31日
	 */
	@RequestMapping("/billReport/toReportDetail")
	public String toReportDetail(HttpServletRequest request,HttpServletResponse response) {
		return "/account/billReport/billReport_detail.jsp";
	}
}
