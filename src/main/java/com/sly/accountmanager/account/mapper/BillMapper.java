package com.sly.accountmanager.account.mapper;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.account.model.BillType;

/**
 * 账单mapper
 * @author sly
 * @time 2018年11月19日
 */
public interface BillMapper {
	/**
	 * 保存账单信息
	 * @param bill
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	int saveBill(Bill bill);
	
	/**
	 * 逻辑删除账单信息
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	int deleteBill(Integer billId);
	
	/**
	 * 修改账单信息
	 * @param bill
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	int updateBill(Bill bill);
	
	/**
	 * 根据账单id查询信息
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	Bill findBillById(Integer billId);
	
	/**
	 * 统计分页查询账单列表数量
	 * @param params
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	int countBillList(Map<String, Object> params);
	
	/**
	 * 分页查询账单列表
	 * @param params
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	List<Bill> findBillList(Map<String, Object> params);
	
	/**
	 * 修改账单的账单类型名称
	 * @param billType
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	int updateBillTypeName(BillType billType);
	
	/**
	 * 删除账单的账单类型名称
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	int deleteBillTypeName(String billTypeId);
	
	/**
	 * 批量保存账单信息
	 * @param bills
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	int saveBillList(List<Bill> bills);
	
}
