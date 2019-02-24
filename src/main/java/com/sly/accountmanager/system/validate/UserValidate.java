package com.sly.accountmanager.system.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.system.UserMessage;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.regex.SystemRegex;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 用户参数验证
 * @author sly
 * @time 2019年1月3日
 */
@Component
public class UserValidate {
	
	/**
	 * 验证新增用户功能参数
	 * @param user
	 * @return
	 * @author sly
	 * @time 2019年1月3日
	 */
	public BaseResult validateUserAdd(User user) {
		//用户名验证
		if(StringUtils.isNotBlank(user.getUsername())) {
			if(!user.getUsername().matches(SystemRegex.USERNAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.USERNAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.USERNAME_IS_NOT_NULL);
		}
		//用户昵称验证
		if(StringUtils.isNotBlank(user.getNickname())) {
			if(!user.getNickname().matches(SystemRegex.NICKNAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.NICKNAME_IS_NOT_VALIDATE);
			}
		}
		
		//真实姓名验证
		if(StringUtils.isNotBlank(user.getRealname())) {
			if(!user.getRealname().matches(SystemRegex.REALNAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.REALNAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.REALNAME_IS_NOT_NULL);
		}
		
		//密码验证
		if(StringUtils.isNotBlank(user.getPassword())) {
			if(!user.getPassword().matches(SystemRegex.PASSWORD_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.PASSWORD_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.PASSWORD_IS_NOT_NULL);
		}
		
		//手机号验证
		if(StringUtils.isNotBlank(user.getPhone())) {
			if(!user.getPhone().matches(Regex.PHONE_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.PHONE_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.PHONE_IS_NOT_NULL);
		}
		
		//电话号码验证
		if(StringUtils.isNoneBlank(user.getTel())) {
			if(user.getTel().matches(Regex.TEL_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.TEL_IS_NOT_VALIDATE);
			}
		}
		
		//邮箱验证
		if(StringUtils.isNotBlank(user.getEmail())) {
			if(!user.getEmail().matches(Regex.EMAIL_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.EMAIL_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.EMAIL_IS_NOT_NULL);
		}
		
		//性别验证
		if(StringUtils.isNotBlank(user.getSex())) {
			if(!user.getSex().matches(Regex.SEX_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.SEX_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.SEX_IS_NOT_NULL);
		}
		
		//用户类型验证
		if(user.getUserTag() != null) {
			if(!(user.getUserTag() + "").matches(SystemRegex.USERTAG_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.USERTAG_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.USERTAG_IS_NOT_NULL);
		}
		
		//用户状态验证
		if(user.getStatus() != null) {
			if(!(user.getStatus() + "").matches(SystemRegex.STATUS_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.STATUS_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, UserMessage.STATUS_IS_NOT_NULL);
		}
		
		//备注验证
		if(StringUtils.isNotBlank(user.getRemark())) {
			if(user.getRemark().length() > 240) {
				return new BaseResult(ResultStatus.FAILED,UserMessage.REMARK_IS_NOT_VALIDATE);
			}
		}
		
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}

}
