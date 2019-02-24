package com.sly.accountmanager.system.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.system.RoleMessage;
import com.sly.accountmanager.common.message.system.UserRoleMessage;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.regex.SystemRegex;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.model.Role;

/**
 * 角色参数验证
 * 
 * @author sly
 * @time 2019年1月12日
 */
@Component
public class RoleValidate {
	/**
	 * 验证新增角色功能参数
	 * @param role
	 * @return
	 * @author sly
	 * @time 2019年1月12日
	 */
	public BaseResult validateRoleAdd(Role role) {
		//角色名称验证
		if(StringUtils.isNotBlank(role.getRoleName())) {
			if(!role.getRoleName().matches(SystemRegex.ROLENAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,RoleMessage.ROLENAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, RoleMessage.ROLENAME_IS_NOT_NULL);
		}
		
		//是否启用验证
		if(role.getIsOpen() != null) {
			if((!(role.getIsOpen() + "").matches(SystemRegex.ISOPEN_REGEX))) {
				return new BaseResult(ResultStatus.FAILED, RoleMessage.ISOPEN_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, RoleMessage.ISOPEN_IS_NOT_NULL);
		}
		
		//备注
		if(StringUtils.isNotBlank(role.getRemark())) {
			if(role.getRemark().length() > 240) {
				return new BaseResult(ResultStatus.FAILED, RoleMessage.REMARK_IS_NOT_VALIDATE);
			}
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

	/**
	 * 验证新增角色功能参数
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	public BaseResult validateRoleFindUserAllRoleTree(String userId) {
		// 用户Id
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
