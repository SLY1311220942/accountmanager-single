package com.sly.accountmanager.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.redisHelper.RedisHelper;

/**
 * _启动监听器
 * @author sly
 * @time 2018年12月10日
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationStartedListener.class);
	
	@Autowired
	private RedisHelper redisHelper;
	/**
	 * _启动完成后执行
	 * @param event
	 * @author sly
	 * @time 2018年12月10日
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null) {
			logger.info("spring启动成功!");
			//加载账单类型
			logger.info("开始加载账单类型...");
			redisHelper.loadAllBillType();
			logger.info("账单类型加载完成!");
			//删除用户菜单信息缓存
			logger.info("开始删除用户菜单信息缓存...");
			redisHelper.deleteAllUserFunc();
			logger.info("删除用户菜单信息缓存完成!");
			//
		}
		
	}

}
