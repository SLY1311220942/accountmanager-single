package com.sly.accountmanager.common.redisHelper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.account.service.BillTypeService;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * redis帮助类
 * 
 * @author sly
 * @time 2019年2月27日
 */
@Component
public class RedisHelper {

	@Autowired
	private BillTypeService billTypeService;

	@Autowired
	private RedisTemplate<String, Serializable> redisTemplate;

	/**
	 * 加载所有账单类型到缓存
	 * 
	 * @author sly
	 * @time 2019年2月27日
	 */
	@SuppressWarnings("unchecked")
	public void loadAllBillType() {
		Map<String, Object> billTypeMap = new HashMap<>(128);
		BaseResult baseResult = billTypeService.loadAllBillType();

		List<BillType> billTypes = (List<BillType>) baseResult.getRows();

		for (BillType billType : billTypes) {
			billTypeMap.put(billType.getBillTypeId(), billType);
		}

		// 先清空键,然后再绑定
		redisTemplate.delete("SYSTEM_BILLTYPES");
		redisTemplate.boundHashOps("SYSTEM_BILLTYPES").putAll(billTypeMap);

	}

	/**
	 * 获取缓存账单类型对象
	 * 
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	public BillType findBillTypeById(String billTypeId) {
		BillType billType = (BillType) redisTemplate.boundHashOps("SYSTEM_BILLTYPES").get(billTypeId);
		return billType;
	}

	/**
	 * 更新/添加缓存账单类型对象
	 * 
	 * @param billType
	 * @author sly
	 * @time 2019年2月27日
	 */
	public void putBillType(BillType billType) {
		redisTemplate.boundHashOps("SYSTEM_BILLTYPES").put(billType.getBillTypeId(), billType);
	}
}
