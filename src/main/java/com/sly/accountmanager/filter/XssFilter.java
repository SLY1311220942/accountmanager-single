package com.sly.accountmanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * xss过滤器
 * @author sly
 * @time 2018年12月12日
 */
public class XssFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("XssFilter初始化...");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("执行过滤...");
		XssHttpServletRequestWrapper xssHttpServletRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssHttpServletRequest, response);
	}

	@Override
	public void destroy() {
		System.out.println("XssFilter销毁...");

	}

}
