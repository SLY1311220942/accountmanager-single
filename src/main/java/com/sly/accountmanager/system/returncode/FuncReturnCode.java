package com.sly.accountmanager.system.returncode;

import com.sly.accountmanager.common.returncode.IReturnCode;

/**
 * funcs返回code
 * _返回码开头为10002000-10002999 
 * @author sly
 * @time 2018年11月14日
 */
public enum FuncReturnCode implements IReturnCode {
	/**
	 * 新增功能失败
	 */
	FUNC_ADD_FAILED(10002000,"新增功能失败!"), 
	/**
	 * 删除功能失败
	 */
	FUNC_DELETE_FAILED(10002001,"删除功能失败!"), 
	/**
	 * 修改功能失败
	 */
	FUNC_UPDATE_FAILED(10002002,"修改功能失败!"), 
	/**
	 * 查询功能失败
	 */
	FUNC_QUERY_BYID_FAILED(10002003,"查询功能失败!"), 
	/**
	 * 查询功能树失败
	 */
	FUNC_QUERY_FUNCTREE_FAILED(10002004,"查询功能树失败!"), 
	/**
	 * 递归删除功能树失败
	 */
	FUNC_DELETE_FUNCTREE_FAILED(10002005,"删除功能树失败!"), 
	/**
	 * 查询功能子节点失败
	 */
	FUNC_QUERY_FUNCCHILD_FAILED(10002006,"查询功能子节点失败!"), 
	/**
	 * 查询用户功能失败
	 */
	FUNC_QUERY_USERFUNC_FAILED(10002007,"查询用户功能失败!"),
	/**
	 * 查询用户功能子节点失败
	 */
	FUNC_QUERY_USERFUNCCHILD_FAILED(10002007,"查询用户功能子节点失败!"), 
	/**
	 * 查询用户功能菜单失败
	 */
	FUNC_QUERY_USERMEAN_FAILED(10002008,"查询用户功能菜单失败!"), 
	/**
	 * 查询所有功能失败
	 */
	FUNC_QUERY_ALLFUNC_FAILED(10002009,"查询所有功能失败!"), 
	/**
	 * 查询所有功能树失败
	 */
	FUNC_QUERY_ALLFUNCTREE_FAILED(10002010,"查询所有功能树失败!"), 
	/**
	 * 查询所有顶层功能失败
	 */
	FUNC_QUERY_TOPFUNC_FAILED(10002011,"查询所有顶层功能失败!"), 
	/**
	 * 查询角色功能树失败
	 */
	FUNC_QUERY_ROLEFUNCTREE_FAILED(10002012,"查询角色功能树失败!"), 
	/**
	 * 查询角色功能失败
	 */
	FUNC_QUERY_ROLEFUNC_FAILED(10002013,"查询角色功能失败!"),
	
	;

	/**
	 * 错误code
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String msg;
	
	/**
	 * 枚举构造方法
	 * @param code
	 * @param msg
	 */
	private FuncReturnCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 获取错误code
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public int getCode() {
		return this.code;
	}
	
	/**
	 * 获取错误信息
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public String getMsg() {
		return this.msg;
	}
	
	/**
	 * 获取枚举名称
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public String getName() {
		return this.name();
	}

}
