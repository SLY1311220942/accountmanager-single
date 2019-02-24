package com.sly.accountmanager.account.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具类
 * @author sly
 * @time 2019年1月12日
 */
public class ExcelUtils {
	private static Logger logger = Logger.getLogger(ExcelUtils.class);
	public static final String EXCEL_XLS = "xls";
	public static final String EXCEL_XLSX = "xlsx";
	
	/**
	 * 测试类
	 * @param args
	 * @author sly
	 * @time 2019年1月12日
	 */
	public void test() {
		//组装测试数据
		Map<String, String> params = new HashMap<>(16);
		params.put("theme_1", "主题一");
		params.put("theme_2", "主题二");
		params.put("theme_3", "主题三");
		params.put("theme_5", "主题五");
		params.put("theme_6", "主题六");
		
		Workbook wb = null;
		InputStream inputStream = null;
		File file = new File("D:\\测试\\Excel值替换模板.xlsx");
		OutputStream outputStream = null;
		try {
			if(file.getName().endsWith(EXCEL_XLS)) {
				inputStream = new FileInputStream(file);
				wb = new HSSFWorkbook(inputStream);
			}else if(file.getName().endsWith(EXCEL_XLSX)) {
				inputStream = new FileInputStream(file);
				wb = new XSSFWorkbook(inputStream);
			}else {
				System.out.println("不是Excel");
				return ;
			}
			
			//获取字节数组 Excel宽度为9
			byte[] bytes = getExcelBinary(wb, 9, params);
			outputStream = new FileOutputStream(new File("D:\\测试\\Excel值替换结果.xlsx"));
			outputStream.write(bytes);
			
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(ExceptionUtils.getStackTrace(e));
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Excel值替换
	 * @param wb
	 * @param width
	 * @param params
	 * @return
	 * @author sly
	 * @time 2019年1月12日
	 */
	public static byte[] getExcelBinary(Workbook wb,int width,Map<String, String> params) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			Sheet sheet = wb.getSheetAt(0);
			int numMergedRegions = sheet.getNumMergedRegions();
			List<Cell> cells = new ArrayList<>();
			//合并的单元格
			List<CellRangeAddress> caList = new ArrayList<CellRangeAddress>();
			for (int i = 0; i < numMergedRegions; i++) {
				CellRangeAddress ca = sheet.getMergedRegion(i);
				caList.add(ca);
				int firstRow = ca.getFirstRow();
				Row row = sheet.getRow(firstRow);
				Cell cell = row.getCell(ca.getFirstColumn());
				if(cell != null) {
					cells.add(cell);
				}
				
			}
			//获取高度Excel模板
			int height = sheet.getLastRowNum() + 1;
			
			//获取普通单元格
			for (int i = 0; i < height; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < width; j++) {
					if (!isCombineCell(caList, j, i)) {
						Cell cell = row.getCell(j);
						if (StringUtils.isNotBlank(getCellValue(cell))) {
							cells.add(cell);
						}
					}
				}
			}
			////替换占位符的值
			for (int i = 0; i < cells.size(); i++) {
				Cell cell = cells.get(i);
				if(cell != null) {
					String cellValue = getCellValue(cell);
					List<String> placeHolders = getAllPlaceHolder(cellValue);
					for (int j = 0; j < placeHolders.size(); j++) {
						String placeHolder = placeHolders.get(j);
						String value = params.get(placeHolder);
						if(value != null) {
							cellValue = cellValue.replaceAll("\\$\\{" + placeHolder + "\\}", value);
						}else {
							cellValue = cellValue.replaceAll("\\$\\{" + placeHolder + "\\}", "");
						}
					}
					setCellValue(cell, cellValue);
					cellValue = getCellValue(cell);
				}
			}
			
			wb.write(byteArrayOutputStream);
			@SuppressWarnings("null")
			byte[] byteArray = byteArrayOutputStream.toByteArray();
			return byteArray;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException("Excel替换值失败:" + ExceptionUtils.getStackTrace(e));
		} finally {
			if(wb != null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	/**
	 * 获取单元格值
	 * @param cell
	 * @return
	 * @author sly
	 * @time 2018年11月30日
	 */
	public static String getCellValue(Cell cell){    
	    if(cell == null) return "";    
	    if(cell.getCellTypeEnum() == CellType.STRING){    
	        return cell.getStringCellValue();    
	    }else if(cell.getCellTypeEnum() == CellType.BOOLEAN){    
	        return String.valueOf(cell.getBooleanCellValue());    
	    }else if(cell.getCellTypeEnum() == CellType.FORMULA){    
	        return cell.getCellFormula() ;    
	    }else if(cell.getCellTypeEnum() == CellType.NUMERIC){    
	        return String.valueOf(cell.getNumericCellValue());    
	    }
	    return "";    
	}
	
	/**
	 * 设置单元格值
	 * @param cell
	 * @param value
	 * @author sly
	 * @time 2018年11月30日
	 */
	public static void setCellValue(Cell cell,String value){    
	    cell.setCellValue(value);
	}
	
	/**
	 * 替换单元格值
	 * @param value
	 * @param params
	 * @return
	 * @author sly
	 * @time 2018年11月30日
	 */
	public static String replaceValue(String value,Map<String, String> params) {
		if(StringUtils.isNotBlank(value)) {
			return value;
		}
		return value;
	}
	
	/**
	 * 判断该格子是否为合并单元格
	 * @param caList
	 * @param x
	 * @param y
	 * @return
	 * @author sly
	 * @time 2018年11月30日
	 */
	public static boolean isCombineCell(List<CellRangeAddress> caList,int x,int y) {
		int cax1 = 0;
		int cax2 = 0;
		int cay1 = 0;
		int cay2 = 0;
		for (int i = 0; i < caList.size(); i++) {
			CellRangeAddress ca = caList.get(i);
			cax1 = ca.getFirstColumn();
			cax2 = ca.getLastColumn();
			cay1 = ca.getFirstRow();
			cay2 = ca.getLastRow();
			if(x >= cax1 && x <= cax2) {
				if(y >= cay1 && y <= cay2) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取占位符
	 * @param str
	 * @return
	 * @author sly
	 * @time 2018年11月30日
	 */
	public static List<String> getAllPlaceHolder(String str) {
		List<String> list = new ArrayList<>();
		if(StringUtils.isNotBlank(str)) {
			Pattern pattern = Pattern.compile("\\$\\{([^}]*)\\}");
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				list.add(matcher.group(1));
			}
		}
		
		return list;
	}
}
