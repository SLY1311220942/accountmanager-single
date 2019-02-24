package com.sly.accountmanager.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.returncode.DicCodeReturnCode;

/**
 * 字典code controller
 * @author sly
 * @time 2018年12月20日
 */
@Controller
@RequestMapping("/system")
public class DicCodeController {
	private Logger logger = Logger.getLogger(DicCodeController.class);
	
	/**
	 * 去字典code管理页面
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@RequestMapping("/dicCode/toDicCodeManage")
	public String toDicCodeManage(HttpServletRequest request,HttpServletResponse response) {
		return "/system/dicCode/dicCodeManage.jsp";
	}
	
	/**
	 * 获取字典树
	 * @param request
	 * @param response
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@ResponseBody
	@RequestMapping("/dicCode/dicCodeTree")
	public BaseResult dicCodeTree(HttpServletRequest request,HttpServletResponse response) {
		try {
			return new BaseResult(ResultStatus.SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, DicCodeReturnCode.DICCODE_QUERY_TREE_FAILED);
		}
	}
}
