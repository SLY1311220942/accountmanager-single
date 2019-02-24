package com.sly.accountmanager.common.returncode;
/**
 * 返回码接口
 * @author sly
 * @time 2018年11月14日
 */
public interface IReturnCode {
	
	/**
	 * 返回码
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
    int getCode();
	 
    /**
     * 消息
     * @return
     * @author sly
     * @time 2018年11月14日
     */
	 String getMsg();
	 
	 /**
	  * 名称
	  * @return
	  * @author sly
	  * @time 2018年11月14日
	  */
	 String getName();
}
