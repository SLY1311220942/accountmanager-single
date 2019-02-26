package com.sly.accountmanager.account.service;

import java.util.Map;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 账单类型service
 * @author sly
 * @time 2018年11月19日
 */
public interface BillTypeService {
	
	
	
	/**
	 * _查询顶层账单类型列表
	 * @param params(billType,page)
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	BaseResult findTopBillTypeList(Map<String, Object> params);
	
	/**
	 * _保存账单类型
	 * @param billType
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	BaseResult saveBillType(BillType billType, User sessionUser, OperateLog operateLog);
	
}
