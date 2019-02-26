package com.sly.accountmanager.account.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.account.BillTypeMessage;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * _账单类型验证类
 * @author sly
 * @time 2019年2月26日
 */
@Component
public class BillTypeValidate {
	
	/**
	 * _新增账单类型验证
	 * @param billType
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	public BaseResult validateBillTypeAdd(BillType billType) {
		if(StringUtils.isNotBlank(billType.getBillTypeName())) {
			if(billType.getBillTypeName().length() > 32) {
				return new BaseResult(ResultStatus.FAILED, BillTypeMessage.BILLTYPENAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED,BillTypeMessage.BILLTYPENAME_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS,Message.VALIDATE_PASSED);
	}

}
