package com.sly.accountmanager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * xss过滤器
 * @author sly
 * @time 2018年12月12日
 */
public class XssFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("XssFilter初始化...");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XssHttpServletRequestWrapper xssHttpServletRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssHttpServletRequest, response);
	}

	@Override
	public void destroy() {
		logger.info("XssFilter销毁...");
	}

}
