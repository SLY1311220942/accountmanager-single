package com.sly.accountmanager.account.service;

import java.util.Map;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 账单类型service
 * 
 * @author sly
 * @time 2018年11月19日
 */
public interface BillTypeService {

	/**
	 * _查询顶层账单类型列表(区分用户)
	 * 
	 * @param params(billType,page)
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	BaseResult findTopBillTypeList(Map<String, Object> params);

	/**
	 * _保存账单类型
	 * 
	 * @param billType
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	BaseResult saveBillType(BillType billType, User sessionUser, OperateLog operateLog);

	/**
	 * _查询所以顶层账单类型列表(区分用户)
	 * 
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	BaseResult findAllTopBillType(String userId);

	/**
	 * _查询所有账单(不区分用户)
	 * 
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	BaseResult loadAllBillType();
	
	/**
	 * _需改账单类型信息
	 * @param billType
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	BaseResult updateBillType(BillType billType, User sessionUser, OperateLog operateLog);
	
	/**
	 * _根据ID查询账单类型详情
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	BaseResult findBillTypeById(String billTypeId);
	
	/**
	 * _删除账单类型信息
	 * @param billTypeId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	BaseResult deleteBillType(String billTypeId, User sessionUser, OperateLog operateLog);

}
