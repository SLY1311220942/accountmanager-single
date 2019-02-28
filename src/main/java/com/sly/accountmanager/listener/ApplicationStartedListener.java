package com.sly.accountmanager.listener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.redisHelper.RedisHelper;

/**
 * 启动监听器
 * @author sly
 * @time 2018年12月10日
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = Logger.getLogger(ApplicationStartedListener.class);
	
	@Autowired
	private RedisHelper redisHelper;
	/**
	 * 启动完成后执行
	 * @param event
	 * @author sly
	 * @time 2018年12月10日
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//我也不知道为什么要向上获取2层父容器对象,网上都是一层
		if(event.getApplicationContext().getParent() == null) {
			logger.info("启动成功!");
			//加载账单类型
			logger.info("开始加载账单类型...");
			redisHelper.loadAllBillType();
			logger.info("账单类型加载完成!");
			//
			
			
		}
		
	}

}
