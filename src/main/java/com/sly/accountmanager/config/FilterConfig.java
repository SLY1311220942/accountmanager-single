package com.sly.accountmanager.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sly.accountmanager.filter.XssFilter;

/**
 * 过滤器配置类
 * @author sly
 * @time 2018年12月12日
 */
@Configuration
public class FilterConfig {
	/**
	 * xss过滤器注册
	 * @return
	 * @author sly
	 * @time 2018年12月11日
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean xssFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("xssFilter");
		return registration;
	}
}
