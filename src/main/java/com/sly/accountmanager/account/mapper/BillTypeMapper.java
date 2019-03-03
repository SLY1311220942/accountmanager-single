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
	 * 查询顶层账单类型列表数量(区分用户)
	 * @param params
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	int findTopBillTypeCount(Map<String, Object> params);
	
	/**
	 * 查询顶层账单类型分页列表(区分用户)
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
	
	/**
	 * _查询所以顶层账单类型列表(区分用户)
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	List<BillType> findAllTopBillType(String userId);

	/**
	 * _查询所有账单(不区分用户)
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	List<BillType> loadAllBillType();

	/**
	 * _修改账单类型
	 * @param billType
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	int updateBillType(BillType billType);

	/**
	 * _根据账单类型ID查询账单
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	BillType findBillTypeById(String billTypeId);
	
	/**
	 * _根据ID删除账单类型
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	int deleteBillType(String billTypeId);
	
}
