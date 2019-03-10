package com.sly.accountmanager.system.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.system.RoleFuncMessage;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * _角色功能参数验证
 * 
 * @author sly
 * @time 2019年1月15日
 */
@Component
public class RoleFuncValidate {

	/**
	 * _验证角色分配功能参数
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月15日
	 */
	public BaseResult validateDistributeRoleFunc(String roleId) {
		// 功能Id
		if (StringUtils.isNotBlank(roleId)) {
			if (!roleId.matches(Regex.UUID_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, RoleFuncMessage.ROLEID_IS_NOT_VALIDATE);
			}
		} else {
			return new BaseResult(ResultStatus.FAILED, RoleFuncMessage.ROLEID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

}
