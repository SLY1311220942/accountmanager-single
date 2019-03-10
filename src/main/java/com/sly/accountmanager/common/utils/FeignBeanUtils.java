package com.sly.accountmanager.common.utils;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.common.result.BaseResult;

/**
 * FeignBean工具类
 * 
 * @author sly
 * @time 2018年12月17日
 */
public class FeignBeanUtils {
	/**
	 * _获取基础结果中的对象
	 * @param key
	 * @param clazz
	 * @param baseResult
	 * @return
	 * @author sly
	 * @time 2018年12月17日
	 */
	public static <T> T getBaseResultObject(String key, Class<T> clazz, BaseResult baseResult) {
		String jsonString = JSON.toJSONString(baseResult.getValue(key));
		T object = JSON.parseObject(jsonString, clazz);
		return object;
	};
	
	/**
	 * _获取参数map中的对象
	 * @param key
	 * @param clazz
	 * @param params
	 * @return
	 * @author sly
	 * @time 2018年12月24日
	 */
	public static <T> T getParamsObject(String key, Class<T> clazz, Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		String jsonString = JSON.toJSONString((Map<String, Object>)params.get(key));
		T object = JSON.parseObject(jsonString, clazz);
		return object;
	};

	/**
	 * _获取基础结果中的list对象
	 * @param key
	 * @param clazz
	 * @param baseResult
	 * @return
	 * @author sly
	 * @time 2018年12月17日
	 */
	public static <T> List<T> getBaseResultArray(String key, Class<T> clazz, BaseResult baseResult) {
		String jsonString = JSON.toJSONString(baseResult.getValue(key));
		List<T> list = JSON.parseArray(jsonString, clazz);
		return list;
	};
	
	/**
	 * _获取参数map中的list对象
	 * @param key
	 * @param clazz
	 * @param params
	 * @return
	 * @author sly
	 * @time 2018年12月24日
	 */
	public static <T> List<T> getParamsArray(String key, Class<T> clazz, Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		String jsonString = JSON.toJSONString((List<Object>)params.get(key));
		List<T> list = JSON.parseArray(jsonString, clazz);
		return list;
	};
}
