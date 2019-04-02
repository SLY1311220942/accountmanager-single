package com.sly.accountmanager.account.service.impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sly.accountmanager.account.mapper.BillReportMapper;
import com.sly.accountmanager.account.model.BillReport;
import com.sly.accountmanager.account.returncode.BillReportReturnCode;
import com.sly.accountmanager.account.service.BillReportService;
import com.sly.accountmanager.account.service.BillService;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.Page;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 财务报表service实现
 * 
 * @author sly
 * @time 2019年3月30日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BillReportServiceImpl implements BillReportService {
	private static final Logger logger = LoggerFactory.getLogger(BillService.class);

	@Autowired
	private BillReportMapper billReportMapper;

	/**
	 * 按日期查询财务报表
	 * 
	 * @param billReport
	 * @param page
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	@Override
	public BaseResult findReportList(BillReport billReport, Page page) {
		try {
			int total = billReportMapper.findDateReportCount(billReport);
			List<BillReport> rows = null;
			if (total > 0) {
				rows = billReportMapper.findDateReportList(billReport, page);
			}

			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, total, rows);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReportReturnCode.BILLREPORT_QUERY_DATE_FAILED, e);
		}
	}

}
