package com.sly.accountmanager.account.service;

import com.sly.accountmanager.account.model.BillReport;
import com.sly.accountmanager.common.model.Page;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * 财务报表service接口
 * @author sly
 * @time 2019年3月30日
 */
public interface BillReportService {
	/**
	 * 查询财务报表
	 * @param billReport
	 * @param page
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	BaseResult findReportList(BillReport billReport, Page page);
	
	/**
	 * 查询财务图表详情数据
	 * @param billReport
	 * @return
	 * @author sly
	 * @time 2019年4月5日
	 */
	BaseResult findBillReportChartDetail(BillReport billReport);

}
