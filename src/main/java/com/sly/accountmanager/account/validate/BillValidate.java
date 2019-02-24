package com.sly.accountmanager.account.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.account.BillMessage;
import com.sly.accountmanager.common.regex.AccountRegex;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.result.BaseResult;

@Component
public class BillValidate {
	/**
	 * 验证新增账单功能参数
	 * @param bill
	 * @return
	 * @author sly
	 * @time 2019年1月20日
	 */
	public BaseResult validateBillAdd(Bill bill) {
		// 验证收支类型
		if(bill.getRevexpType() != null) {
			if(!bill.getRevexpType().toString().matches(AccountRegex.REVEXPTYPE_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.REVEXPTYPE_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, BillMessage.REVEXPTYPE_IS_NOT_NULL);
		}
		// 验证账单金额
		if(bill.getBillAmount() != null) {
			if(!bill.getBillAmount().toString().matches(AccountRegex.BILLAMOUNT_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.BILLAMOUNT_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, BillMessage.BILLAMOUNT_IS_NOT_NULL);
		}
		// 验证账单时间
		if(StringUtils.isNotBlank(bill.getBillTime())) {
			if (!bill.getBillTime().matches(Regex.YYMMDD_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.BILLTIME_IS_NOT_VALIDATE);
			}
			
		}else {
			return new BaseResult(ResultStatus.FAILED, BillMessage.BILLTIME_IS_NOT_NULL);
		}
		// 验证账单详情
		if(StringUtils.isNotBlank(bill.getBillDetail())) {
			if(bill.getBillDetail().length() > 240) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.BILLDETAIL_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, BillMessage.BILLDETAIL_IS_NOT_NULL);
		}
		// 验证账单备注
		if(StringUtils.isNotBlank(bill.getRemark())) {
			if(bill.getRemark().length() > 240) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.BILLREMARK_IS_NOT_VALIDATE);
			}
		}
		
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

	/**
	 * 验证逻辑删除账单功能参数
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2019年2月8日
	 */
	public BaseResult validateBillDelete(Integer billId) {
		// 验证账单ID
		if (billId != null) {
			/*if(!billId.matches(Regex.UUID_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, BillMessage.BILLID_IS_NOT_VALIDATE);
			}*/
		}else {
			return new BaseResult(ResultStatus.FAILED, BillMessage.BILLID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

	/**
	 * 验证修改账单功能参数
	 * @param bill
	 * @return
	 * @author sly
	 * @time 2019年2月8日
	 */
	public BaseResult validateBillUpdate(Bill bill) {

		if(bill.getBillId() != null) {
			
		}else {
			
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

}
