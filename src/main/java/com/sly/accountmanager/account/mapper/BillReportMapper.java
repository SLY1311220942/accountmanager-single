package com.sly.accountmanager.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sly.accountmanager.account.model.BillReport;
import com.sly.accountmanager.common.model.Page;

/**
 * 财务报表mapper
 * @author sly
 * @time 2019年3月30日
 */
public interface BillReportMapper {
	/**
	 * 获取按日期统计的财务报表list数量
	 * @param billReport
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	int findDateReportCount(@Param("billReport") BillReport billReport);
	
	/**
	 * 获取按日期统计的财务报表list
	 * @param billReport
	 * @param page
	 * @return
	 * @author sly
	 * @time 2019年3月30日
	 */
	List<BillReport> findDateReportList(@Param("billReport") BillReport billReport,@Param("page") Page page);

}
