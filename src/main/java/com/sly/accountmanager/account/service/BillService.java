package com.sly.accountmanager.account.service;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 账单service 接口
 * @author sly
 * @time 2018年11月19日
 */
public interface BillService {
	
	/**
	 * 保存账单信息
	 * @param bill
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月25日
	 */
	BaseResult saveBill(Bill bill,User sessionUser,OperateLog operateLog);
	
	/**
	 * 删除账单信息
	 * @param billId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	BaseResult deleteBill(Integer billId, User sessionUser, OperateLog operateLog);
	
	/**
	 * 修改账单信息
	 * @param bill
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	BaseResult updateBill(Bill bill,User sessionUser,OperateLog operateLog);
	
	/**
	 * 根据账单id查询信息
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	BaseResult findBillById(Integer billId);
	
	/**
	 * 分页查询账单列表
	 * @param params(bill,page)
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	BaseResult findBillList(Map<String, Object> params);
	
	/**
	 * 批量保存账单信息
	 * @param bills
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	BaseResult batchSaveBill(List<Bill> bills, User sessionUser, OperateLog operateLog);
}
