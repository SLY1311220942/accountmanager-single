package com.sly.accountmanager.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 描述 : 跨站请求防范
 * 
 * @author SLY
 * 
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	HttpServletRequest orgRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.orgRequest = request;
	}

	/**
	 * 获取参数
	 * 
	 * @param parameter
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	/**
	 * 获取单个参数
	 * 
	 * @param parameter
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * 获取请求头
	 * 
	 * @param name
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	/**
	 * 替换
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	private String cleanXSS(String value) {
		String oldValue = new String(value);
		value = IConstants.replaceStr(value);
		if (!oldValue.equals(value)) {
			System.out.println("替换前:" + oldValue);
			System.out.println("替换后:" + value);
		}
		return value;
	}

}
