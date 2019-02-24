package com.sly.accountmanager.system.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.message.system.FuncMessage;
import com.sly.accountmanager.common.message.system.RoleFuncMessage;
import com.sly.accountmanager.common.regex.Regex;
import com.sly.accountmanager.common.regex.SystemRegex;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.model.Func;

/**
 * 功能参数验证
 * @author sly
 * @time 2018年12月24日
 */
@Component
public class FuncValidate {
	/**
	 * 验证新增功能参数
	 * @param func
	 * @return
	 * @author sly
	 * @time 2018年12月24日
	 */
	public BaseResult validateFuncAdd(Func func) {
		//父功能ID
		if(StringUtils.isBlank(func.getParentId())) {
			func.setParentId(null);
		}
		//功能名称验证
		if(StringUtils.isNotBlank(func.getFuncName())) {
			if(!func.getFuncName().matches(SystemRegex.FUNCNAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCNAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCNAME_IS_NOT_NULL);
		}
		//功能图标
		if(StringUtils.isNotBlank(func.getFuncIcon())) {
			if(func.getFuncIcon().length() > 32) {
				return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCICON_IS_NOT_VALIDATE);
			}
		}else {
			func.setFuncIcon("");
		}
		//功能Url
		if(StringUtils.isNotBlank(func.getFuncUrl())) {
			if(!func.getFuncUrl().matches(SystemRegex.FUNCURL_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCURL_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCURL_IS_NOT_NULL);
		}
		//功能标签
		if(func.getFuncTag() != null) {
			if(func.getFuncTag() != 0 && func.getFuncTag() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCTAG_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCTAG_IS_NOT_NULL);
		}
		//功能排序
		if(func.getFuncSort() != null) {
			if(!(func.getFuncSort() + "").matches(SystemRegex.FUNCSORT_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCSORT_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCSORT_IS_NOT_NULL);
		}
		//是否启用
		if(func.getIsOpen() != null) {
			if(func.getIsOpen() != 0 && func.getIsOpen() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.ISOPEN_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.ISOPEN_IS_NOT_NULL);
		}
		//功能类型
		if(func.getFuncType() != null) {
			if(func.getFuncType() != 0 && func.getFuncType() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCTYPE_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCTYPE_IS_NOT_NULL);
		}
		//备注
		if(func.getRemark() != null) {
			if(func.getRemark().length() > 240) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.REMARK_IS_NOT_VALIDATE);
			}
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}
	
	/**
	 * 验证修改功能参数
	 * @param func
	 * @return
	 * @author sly
	 * @time 2018年12月31日
	 */
	public BaseResult validateFuncUpdate(Func func) {
		//功能ID
		if(StringUtils.isBlank(func.getFuncId())) {
			return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCID_IS_NOT_NULL);
		}
		//功能名称验证
		if(StringUtils.isNotBlank(func.getFuncName())) {
			if(!func.getFuncName().matches(SystemRegex.FUNCNAME_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCNAME_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCNAME_IS_NOT_NULL);
		}
		//功能图标
		if(StringUtils.isNotBlank(func.getFuncIcon())) {
			if(func.getFuncIcon().length() > 32) {
				return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCICON_IS_NOT_VALIDATE);
			}
		}else {
			func.setFuncIcon("");
		}
		//功能Url
		if(StringUtils.isNotBlank(func.getFuncUrl())) {
			if(!func.getFuncUrl().matches(SystemRegex.FUNCURL_REGEX)) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCURL_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCURL_IS_NOT_NULL);
		}
		//功能标签
		if(func.getFuncTag() != null) {
			if(func.getFuncTag() != 0 && func.getFuncTag() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCTAG_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCTAG_IS_NOT_NULL);
		}
		//功能排序
		if(func.getFuncSort() != null) {
			if(!(func.getFuncSort() + "").matches(SystemRegex.FUNCSORT_REGEX)) {
				return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCSORT_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCSORT_IS_NOT_NULL);
		}
		//是否启用
		if(func.getIsOpen() != null) {
			if(func.getIsOpen() != 0 && func.getIsOpen() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.ISOPEN_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.ISOPEN_IS_NOT_NULL);
		}
		//功能类型
		if(func.getFuncType() != null) {
			if(func.getFuncType() != 0 && func.getFuncType() != 1) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCTYPE_IS_NOT_VALIDATE);
			}
		}else {
			return new BaseResult(ResultStatus.FAILED, FuncMessage.FUNCTYPE_IS_NOT_NULL);
		}
		//备注
		if(func.getRemark() != null) {
			if(func.getRemark().length() > 240) {
				return new BaseResult(ResultStatus.FAILED,FuncMessage.REMARK_IS_NOT_VALIDATE);
			}
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}
	
	/**
	 * 验证查询功能详情参数
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月31日
	 */
	public BaseResult validateFuncDetail(String funcId) {
		if(StringUtils.isBlank(funcId)) {
			return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}
	
	/**
	 * 验证删除功能参数
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	public BaseResult validateFuncDelete(String funcId) {
		if(StringUtils.isBlank(funcId)) {
			return new BaseResult(ResultStatus.FAILED,FuncMessage.FUNCID_IS_NOT_NULL);
		}
		return new BaseResult(ResultStatus.SUCCESS, Message.VALIDATE_PASSED);
	}
	
	/**
	 * 验证查询角色功能树参数
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	public BaseResult validateFuncFindRoleAllFuncTree(String roleId) {
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
