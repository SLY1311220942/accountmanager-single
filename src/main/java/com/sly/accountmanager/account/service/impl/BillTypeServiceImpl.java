package com.sly.accountmanager.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.account.mapper.BillTypeMapper;
import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.account.returncode.BillReturnCode;
import com.sly.accountmanager.account.returncode.BillTypeReturnCode;
import com.sly.accountmanager.account.service.BillService;
import com.sly.accountmanager.account.service.BillTypeService;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.common.utils.UUIDUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;

/**
 * 账单类型service实现
 * @author sly
 * @time 2018年11月19日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class BillTypeServiceImpl implements BillTypeService {
	
	private Logger logger = Logger.getLogger(BillService.class);
	
	@Autowired
	private BillTypeMapper billTypeMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	
	/**
	 * _查询顶层账单类型列表
	 * @param params(billType,page)
	 * @return
	 * @author sly
	 * @time 2019年2月25日
	 */
	@Override
	public BaseResult findTopBillTypeList(Map<String, Object> params) {
		try {
			int total = billTypeMapper.findTopBillTypeCount(params);
			List<BillType> rows = null;
			if(total > 0) {
				rows = billTypeMapper.findTopBillTypeList(params);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, total, rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_QUERY_TOPLIST_FAILED, e);
		}
	}

	
	/**
	 * _保存账单类型
	 * @param billType
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年2月26日
	 */
	@Override
	public BaseResult saveBillType(BillType billType, User sessionUser, OperateLog operateLog) {
		try {
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			billType.setBillTypeId(UUIDUtils.genUUID());
			billType.setCreateTime(createTime);
			billType.setLogicDel(CommonConstant.LOGICDEL_N);
			billType.setUserId(sessionUser.getUserId());
			billType.setUserName(sessionUser.getUsername());
			billTypeMapper.saveBillType(billType);
			
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 新增账单类型:" + JSON.toJSONString(billType);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_ADD_FAILED, e);
		}
	}
	
}
