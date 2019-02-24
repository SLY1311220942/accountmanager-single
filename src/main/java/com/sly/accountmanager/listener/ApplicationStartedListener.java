package com.sly.accountmanager.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 启动监听器
 * @author sly
 * @time 2018年12月10日
 */
@Component
public class ApplicationStartedListener implements ApplicationListener<ContextRefreshedEvent> {
	
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
			System.out.println("启动成功!");
		}
		
	}

}
