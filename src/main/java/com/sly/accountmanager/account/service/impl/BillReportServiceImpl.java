package com.sly.accountmanager.account.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	 * 查询财务报表
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

	/**
	 * 查询财务图表详情数据
	 * @param billReport
	 * @return
	 * @author sly
	 * @time 2019年4月5日
	 */
	@Override
	public BaseResult findBillReportChartDetail(BillReport billReport) {
		try {
			//查询
			List<BillReport> billReports = billReportMapper.findBillReportChartDetail(billReport);
			
			//提取x轴名称
			List<String> xAxisDatas = new ArrayList<String>();
			if("0".equals(billReport.getStatisticType())) {
				xAxisDatas = billReports.stream().map(b -> b.getDateTime()).collect(Collectors.toList());
			}else if("1".equals(billReport.getStatisticType())){
				xAxisDatas = billReports.stream().map(b -> b.getBillTypeName()).collect(Collectors.toList());
			}
			
			//组装数据
			List<Map<String, Object>> series = new ArrayList<>();
			List<String> billAmounts = billReports.stream().map(b -> b.getBillAmount()).collect(Collectors.toList());
			List<String> billCount = billReports.stream().map(b -> b.getBillCount()).collect(Collectors.toList());
			Map<String, Object> billAmountMap = new HashMap<String, Object>(16);
			billAmountMap.put("name", "金额");
			billAmountMap.put("data", billAmounts);
			Map<String, Object> billCountMap = new HashMap<String, Object>(16);
			billCountMap.put("name", "数量");
			billCountMap.put("data", billCount);
			series.add(billAmountMap);
			series.add(billCountMap);
			List<String> lengedData = new ArrayList<>();
			lengedData.add("金额");
			lengedData.add("数量");
			
			//返回结果
			BaseResult result = new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS);
			result.setValue("xAxisDatas", xAxisDatas);
			result.setValue("series", series);
			result.setValue("lengedData", lengedData);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(BillReportReturnCode.BILLREPORT_QUERY_DETAIL_FAILED, e);
		}
	}

}
