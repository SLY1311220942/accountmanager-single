package com.sly.accountmanager.common.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * 公共返回对象
 * 
 * @author sly
 * @time 2018年11月12日
 */
public class BaseResult implements Serializable {

	private static final long serialVersionUID = -1937095802242495571L;

	/**
	 * 数据map
	 */
	private Map<String, Object> dataMap = new HashMap<String, Object>(16);

	/**
	 * 空参构造方法
	 */
	public BaseResult() {

	}

	/**
	 * 构造方法
	 * 
	 * @param status
	 */
	public BaseResult(Integer status) {
		setStatus(status);
	}

	/**
	 * 构造方法
	 * 
	 * @param status
	 * @param message
	 */
	public BaseResult(Integer status, String message) {
		setStatus(status);
		setMessage(message);
	}
	
	public BaseResult(Integer status, IReturnCode returnCode) {
		setStatus(status);
		setMessage(returnCode.getMsg() + "错误码:" + returnCode.getCode());
	}

	/**
	 * 构造方法
	 * 
	 * @param status
	 * @param message
	 * @param key
	 * @param value
	 */
	public BaseResult(Integer status, String message, String key, Object value) {
		setStatus(status);
		setMessage(message);
		setValue(key, value);
	}

	/**
	 * 构造方法
	 * 
	 * @param status
	 * @param message
	 * @param dataMap
	 */
	public BaseResult(Integer status, String message, Map<String, Object> dataMap) {
		setDataMap(dataMap);
		setStatus(status);
		setMessage(message);
	}

	/**
	 * 构造方法
	 * 
	 * @param status
	 * @param message
	 * @param total
	 * @param rows
	 */
	public BaseResult(Integer status, String message, Integer total, List<?> rows) {
		setStatus(status);
		setMessage(message);
		setTotal(total);
		setRows(rows);
	}

	/**
	 * 获取返回状态
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public Integer getStatus() {
		return (Integer) dataMap.get("status");
	}

	/**
	 * 设置返回状态
	 * 
	 * @param status
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setStatus(Integer status) {
		dataMap.put("status", status);
	}

	/**
	 * 获取返回信息
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public String getMessage() {
		return (String) dataMap.get("message");
	}

	/**
	 * 设置返回信息
	 * 
	 * @param message
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setMessage(String message) {
		dataMap.put("message", message);
	}

	/**
	 * 获取返回分页数据
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public List<?> getRows() {
		return  (List<?>) dataMap.get("rows");
	}

	/**
	 * 设置返回分页数据
	 * 
	 * @param rows
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setRows(List<?> rows) {
		dataMap.put("rows", rows);
	}

	/**
	 * 获取返回分页数据数量
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public Integer getTotal() {
		return (Integer) dataMap.get("total");
	}

	/**
	 * 设置返回分页数据数量
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setTotal(Integer total) {
		dataMap.put("total", total);
	}

	/**
	 * 获取数据map
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月12日
	 */
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	/**
	 * 设置数据map
	 * 
	 * @param dataMap
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setDataMap(Map<String, Object> dataMap) {
		Set<String> keySet = dataMap.keySet();
		for (String key : keySet) {
			this.dataMap.put(key, dataMap.get(key));
		}
	}

	/**
	 * 向数据map存放数据
	 * 
	 * @param key
	 * @param value
	 * @author sly
	 * @time 2018年11月12日
	 */
	public void setValue(String key, Object value) {
		dataMap.put(key, value);
	}
	
	/**
	 * 从数据map取数据
	 * @param key
	 * @return
	 * @author sly
	 * @time 2018年12月13日
	 */
	public Object getValue(String key) {
		return dataMap.get(key);
	}
}
