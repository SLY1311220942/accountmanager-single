package com.sly.accountmanager.system.service;

import java.util.List;

import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.system.model.Func;

/**
 * 功能service接口
 * 
 * @author sly
 * @time 2018年11月12日
 */
public interface FuncService {

	/**
	 * 保存功能信息
	 * 
	 * @param func
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult saveFunc(Func func, User sessionUser, OperateLog operateLog);

	/**
	 * 逻辑删除功能
	 * 
	 * @param funcId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult deleteFunc(String funcId, User sessionUser, OperateLog operateLog);

	/**
	 * 修改功能
	 * 
	 * @param func
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult updateFunc(Func func, User sessionUser, OperateLog operateLog);

	/**
	 * 根据功能id查询功能
	 * 
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	BaseResult findFuncById(String funcId);

	/**
	 * 查询功能树
	 * 
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult findFuncTree(String userId);

	/**
	 * 删除功能及其子节点
	 * 
	 * @param funcId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	BaseResult deleteFuncTree(String funcId, User sessionUser, OperateLog operateLog);

	/**
	 * 提交删除子节点功能数
	 * 
	 * @param allFuncs
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	BaseResult commitDeleteFuncTree(List<Func> allFuncs, User sessionUser, OperateLog operateLog);

	/**
	 * 查询用户功能
	 * 
	 * @param userId
	 * @param funcType
	 * @return
	 * @author sly
	 * @time 2018年12月16日
	 */
	BaseResult findUserFunc(String userId,Integer funcType);

	/**
	 * 获取全部功能
	 * 
	 * @return
	 * @author sly
	 * @time 2018年12月18日
	 */
	BaseResult findAllFunc();

	/**
	 * 获取全部功能树
	 * 
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	BaseResult findAllFuncTree();

	/**
	 * 查询所有顶层功能
	 * 
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	BaseResult findAllTopFunc();

	/**
	 * 查询所有子功能
	 * 
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	BaseResult findAllChildFunc(String funcId);

	/**
	 * 查询角色功能树
	 * 
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月14日
	 */
	BaseResult findRoleAllFuncIds(String roleId);

}
