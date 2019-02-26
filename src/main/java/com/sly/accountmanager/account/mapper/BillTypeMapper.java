package com.sly.accountmanager.account.mapper;

import java.util.List;
import java.util.Map;

import com.sly.accountmanager.account.model.BillType;

/**
 * 账单类型mapper
 * @author sly
 * @time 2018年11月19日
 */
public interface BillTypeMapper {
	/**
	 * 查询顶层账单类型列表数量
	 * @param params
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	int findTopBillTypeCount(Map<String, Object> params);
	
	/**
	 * 查询顶层账单类型分页列表
	 * @param params
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	List<BillType> findTopBillTypeList(Map<String, Object> params);
	
	/**
	 * 保存账单类型
	 * @param billType
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	int saveBillType(BillType billType);
	
}
