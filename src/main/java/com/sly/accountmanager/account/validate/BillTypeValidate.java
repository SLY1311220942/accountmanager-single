package com.sly.accountmanager.account.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.account.BillTypeMessage;
import com.sly.accountmanager.common.regex.Regex;
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
		//账单类型名称验证
		if(StringUtils.isNotBlank(billType.getBillTypeName())) {
			if(billType.getBillTypeName().length() > 32) {
				return new BaseResult(ResultStatus.FAILED, BillTypeMessage.BILLTYPENAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED,BillTypeMessage.BILLTYPENAME_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS,Message.VALIDATE_PASSED);
	}

	/**
	 * _修改账单类型验证
	 * @param billType
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	public BaseResult validateBillTypeUpdate(BillType billType) {
		//账单类型id验证
		if(StringUtils.isNotBlank(billType.getBillTypeId())) {
			if (!billType.getBillTypeId().matches(Regex.UUID_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillTypeMessage.BILLTYPEID_IS_NOT_VALIDATE);
			}
		} else {
			return new BaseResult(ResultStatus.FAILED,BillTypeMessage.BILLTYPEID_IS_NOT_NULL);
		}
		//账单类型名称验证
		if(StringUtils.isNotBlank(billType.getBillTypeName())) {
			if(billType.getBillTypeName().length() > 32) {
				return new BaseResult(ResultStatus.FAILED, BillTypeMessage.BILLTYPENAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED,BillTypeMessage.BILLTYPENAME_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS,Message.VALIDATE_PASSED);
	}

	/**
	 * _删除账单类型验证
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	public BaseResult validateBillTypeDelete(String billTypeId) {
		//账单类型id验证
		if(StringUtils.isNotBlank(billTypeId)) {
			if (!billTypeId.matches(Regex.UUID_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillTypeMessage.BILLTYPEID_IS_NOT_VALIDATE);
			}
		} else {
			return new BaseResult(ResultStatus.FAILED,BillTypeMessage.BILLTYPEID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS,Message.VALIDATE_PASSED);
	}

}
