package com.sly.accountmanager.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.account.mapper.BillMapper;
import com.sly.accountmanager.account.mapper.BillTypeMapper;
import com.sly.accountmanager.account.model.BillType;
import com.sly.accountmanager.account.returncode.BillTypeReturnCode;
import com.sly.accountmanager.account.service.BillService;
import com.sly.accountmanager.account.service.BillTypeService;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.redisHelper.RedisHelper;
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
	
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);
	
	@Autowired
	private BillTypeMapper billTypeMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	@Autowired
	private BillMapper billMapper;
	
	@Autowired
	private RedisHelper redisHelper;
	
	
	/**
	 * _查询顶层账单类型列表(区分用户)
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
			
			//更新redis缓存
			redisHelper.putBillType(billType);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_ADD_FAILED, e);
		}
	}

	/**
	 * _查询所以顶层账单类型列表(区分用户)
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	@Override
	public BaseResult findAllTopBillType(String userId) {
		try {
			List<BillType> rows = billTypeMapper.findAllTopBillType(userId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, rows.size(), rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_QUERY_ALLTOPLIST_FAILED, e);
		}
	}

	/**
	 * _查询所有账单(不区分用户)
	 * @return
	 * @author sly
	 * @time 2019年2月27日
	 */
	@Override
	public BaseResult loadAllBillType() {
		try {
			List<BillType> rows = billTypeMapper.loadAllBillType();
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, rows.size(), rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_QUERY_ALL_FAILED, e);
		}
		
	}

	/**
	 * _修改账单类型信息
	 * @param billType
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	@Override
	public BaseResult updateBillType(BillType billType, User sessionUser, OperateLog operateLog) {
		try {
			String updateTime = DateUtils.formateTime(new Date());
			//修改前
			BillType oldBillType = billTypeMapper.findBillTypeById(billType.getBillTypeId());
			//修改账单类型
			billTypeMapper.updateBillType(billType);
			//修改后
			BillType newBillType = billTypeMapper.findBillTypeById(billType.getBillTypeId());
			//修改相关账单的账单类型名称
			billMapper.updateBillTypeName(billType);
			
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " + updateTime + " 修改账单类型,修改前:"
					+ JSON.toJSONString(oldBillType) + "修改后:" + JSON.toJSONString(newBillType);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			//更新redis缓存
			redisHelper.putBillType(newBillType);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_UPDATE_FAILED, e);
		}
		
	}


	/**
	 * _根据ID查询账单类型详情
	 * @param billTypeId
	 * @return
	 * @author sly
	 * @time 2019年3月1日
	 */
	@Override
	public BaseResult findBillTypeById(String billTypeId) {
		try {
			BillType billType = billTypeMapper.findBillTypeById(billTypeId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "billType", billType);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_QUERY_BYID_FAILED, e);
		}
	}


	/**
	 * _删除账单类型信息
	 * @param billTypeId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	@Override
	public BaseResult deleteBillType(String billTypeId, User sessionUser, OperateLog operateLog) {
		try {
			BillType billType = billTypeMapper.findBillTypeById(billTypeId);
			//删除账单类型
			billTypeMapper.deleteBillType(billTypeId);
			//删除账单的账单类型名称
			billMapper.deleteBillTypeName(billTypeId);
			
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " + DateUtils.formateTime(new Date())
					+ " 删除账单类型,删除的账单类型为:" + JSON.toJSONString(billType);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillTypeReturnCode.BILLTYPE_DELETE_FAILED, e);
		}
	}
	
}
