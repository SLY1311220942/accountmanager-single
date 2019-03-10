package com.sly.accountmanager.account.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.account.mapper.BillMapper;
import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.account.returncode.BillReturnCode;
import com.sly.accountmanager.account.service.BillService;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.redisHelper.RedisHelper;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.system.mapper.OperateLogMapper;

/**
 * 账单service实现
 * @author sly
 * @time 2018年11月19日
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class BillServiceImpl implements BillService {
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);
	
	@Autowired
	private BillMapper billMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	@Autowired
	private RedisHelper redisHelper;
	
	/**
	 * 保存账单信息
	 * @param bill
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult saveBill(Bill bill,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			bill.setCreateTime(createTime);
			bill.setUpdateTime(createTime);
			bill.setUserId(sessionUser.getUserId());
			bill.setUserName(sessionUser.getUsername());
			bill.setLogicDel(CommonConstant.LOGICDEL_N);
			if(StringUtils.isNotBlank(bill.getBillTypeId())) {
				bill.setBillTypeName(redisHelper.findBillTypeById(bill.getBillTypeId()).getBillTypeName());
			}
			billMapper.saveBill(bill);
			
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 新增账单:" + JSON.toJSONString(bill);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReturnCode.BILL_ADD_FAILED, e);
		}
	}

	/**
	 * 删除账单信息
	 * @param billId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult deleteBill(Integer billId,User sessionUser,OperateLog operateLog) {
		try {
			billMapper.deleteBill(billId);
			//未完成
			return new BaseResult(ResultStatus.SUCCESS, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReturnCode.BILL_DELETE_FAILED, e);
		}
	}

	/**
	 * 修改账单信息
	 * @param bill
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult updateBill(Bill bill,User sessionUser,OperateLog operateLog) {
		try {
			
			Bill oldBill = billMapper.findBillById(bill.getBillId());
			
			//组装数据
			String updateTime = DateUtils.formateTime(new Date());
			bill.setUpdateTime(updateTime);
			if(StringUtils.isNotBlank(bill.getBillTypeId())) {
				bill.setBillTypeName(redisHelper.findBillTypeById(bill.getBillTypeId()).getBillTypeName());
			}
			billMapper.updateBill(bill);
			
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " 
					+ updateTime + " 修改账单修改前为:" + JSON.toJSONString(oldBill) 
					+ "修改后为:" + JSON.toJSONString(bill);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReturnCode.BILL_UPDATE_FAILED, e);
		}
	}

	/**
	 * 根据账单id查询信息
	 * @param billId
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult findBillById(Integer billId) {
		try {
			Bill bill = billMapper.findBillById(billId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "bill", bill);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReturnCode.BILL_QUERY_BYID_FAILED, e);
		}
	}

	/**
	 * 分页查询账单列表
	 * @param params(bill,page)
	 * @return
	 * @author sly
	 * @time 2018年11月19日
	 */
	@Override
	public BaseResult findBillList(@RequestBody Map<String, Object> params) {
		try {
			int total = billMapper.countBillList(params);
			List<Bill> rows = null;
			if(total > 0) {
				rows = billMapper.findBillList(params);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, total, rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReturnCode.BILL_QUERY_LIST_FAILED, e);
		}
	}

	/**
	 * _批量保存账单信息
	 * @param bills
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年3月2日
	 */
	@Override
	public BaseResult batchSaveBill(List<Bill> bills, User sessionUser, OperateLog operateLog) {
		try {
			billMapper.saveBillList(bills);
			//记录操作日志
			String content = "账单管理:用户 " + sessionUser.getUsername() + " 于 " 
					+ bills.get(0).getCreateTime() + " 批量导入账单信息";
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.IMPORT_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return new BaseResult(ResultStatus.FAILED, BillReturnCode.BILL_IMPORT_FAILED);
		}
	}
	
}
