package com.sly.accountmanager.account.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sly.accountmanager.account.model.Bill;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.utils.DateUtils;

/**
 * 账单Excel工具类
 * 
 * @author sly
 * @time 2019年3月2日
 */
public class BillExcelUtils {

	/**
	 * 读取输入流的账单信息
	 * 
	 * @param suffix
	 * @param sessionUser
	 * @param inputStream
	 * @return
	 * @author sly
	 * @throws Exception
	 * @time 2019年3月2日
	 */
	public static List<Bill> loadBillExcel(String suffix, User sessionUser, InputStream inputStream) throws Exception {
		Workbook wb = null;
		if(suffix.endsWith(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		}else if(suffix.endsWith(".xlsx")){
			wb = new XSSFWorkbook(inputStream);
		}
		
		Sheet sheet = wb.getSheetAt(0);

		// 获取合并单元格和合并单元格的区域
		List<CellRangeAddress> caList = ExcelUtils.getCombineRange(sheet);

		// 获取高度
		int height = sheet.getLastRowNum() + 1;

		List<Bill> bills = new ArrayList<>();
		String createTime = DateUtils.formateTime(new Date());

		for (int i = 1; i < height; i++) {
			Row row = sheet.getRow(i);
			String billTime = "";
			String billAmount = ExcelUtils.getCellValue(row.getCell(1));
			String revexpType = ExcelUtils.getCellValue(row.getCell(2));
			String billDetail = ExcelUtils.getCellValue(row.getCell(3));
			String remark = ExcelUtils.getCellValue(row.getCell(4));

			if (!ExcelUtils.isCombineCell(caList, 0, i)) {
				Cell cell = row.getCell(0);
				billTime = ExcelUtils.getCellValue(cell);
			} else {
				// 合并单元格
				billTime = ExcelUtils.getCombineCellValue(sheet, 0, i);
			}

			Bill bill = new Bill();

			// 账单时间
			bill.setBillTime(billTime);
			// 账单金额
			bill.setBillAmount(new BigDecimal(billAmount));
			// 收支类型
			bill.setRevexpType(new BigDecimal(revexpType).intValue());
			// 账单摘要
			bill.setBillDetail(billDetail);
			// 备注
			bill.setRemark(remark);
			// 创建时间
			bill.setCreateTime(createTime);
			// 更新时间
			bill.setUpdateTime(createTime);
			// 用户ID
			bill.setUserId(sessionUser.getUserId());
			// 用户名称
			bill.setUserName(sessionUser.getUsername());
			// 逻辑删除
			bill.setLogicDel(CommonConstant.LOGICDEL_N);
			bills.add(bill);
		}

		return bills;
	}

}
