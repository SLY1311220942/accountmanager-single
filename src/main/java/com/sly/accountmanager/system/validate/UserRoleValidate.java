package com.sly.accountmanager.system.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.system.UserRoleMessage;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 用户角色参数验证
 * @author sly
 * @time 2019年1月16日
 */
@Component
public class UserRoleValidate {
	
	/**
	 * 验证角色分配功能参数
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	public BaseResult validateDistributeUserRole(String userId) {
		// 功能Id
		if (StringUtils.isNotBlank(userId)) {
			if (!userId.matches(Regex.UUID_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, UserRoleMessage.USERID_IS_NOT_VALIDATE);
			}
		} else {
			return new BaseResult(ResultStatus.FAILED, UserRoleMessage.USERID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}
}
