package com.sly.accountmanager.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sly.accountmanager.common.constant.CommonConstant;
import com.sly.accountmanager.common.constant.ResultStatus;
import com.sly.accountmanager.common.exception.ServiceCustomException;
import com.sly.accountmanager.common.message.Message;
import com.sly.accountmanager.common.model.OperateLog;
import com.sly.accountmanager.common.model.User;
import com.sly.accountmanager.common.model.ZtreeNode;
import com.sly.accountmanager.common.result.BaseResult;
import com.sly.accountmanager.common.utils.DateUtils;
import com.sly.accountmanager.common.utils.UUIDUtils;
import com.sly.accountmanager.system.constant.SystemServiceConstant;
import com.sly.accountmanager.system.mapper.FuncMapper;
import com.sly.accountmanager.system.mapper.OperateLogMapper;
import com.sly.accountmanager.system.mapper.UserMapper;
import com.sly.accountmanager.system.model.Func;
import com.sly.accountmanager.system.returncode.FuncReturnCode;
import com.sly.accountmanager.system.service.FuncService;
import com.sly.accountmanager.system.utils.UserServiceZtreeFactory;

/**
 * 功能service实现
 * @author sly
 * @time 2018-11-12
 */
@Service
@Transactional(rollbackFor = { Exception.class })
public class FuncServiceImpl implements FuncService {
	private Logger logger = Logger.getLogger(FuncService.class);
	
	@Autowired
	private FuncMapper funcMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OperateLogMapper operateLogMapper;
	
	@Autowired
	private UserServiceZtreeFactory userServiceZtreeFactory;
	
	/**
	 * 保存功能信息
	 * @param func
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult saveFunc(Func func,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String createTime = DateUtils.formateTime(new Date());
			func.setFuncId(UUIDUtils.genUUID());
			func.setUserId(sessionUser.getUserId());
			func.setUsername(sessionUser.getUsername());
			func.setCreateTime(createTime);
			func.setUpdateTime(createTime);
			func.setLogicDel(CommonConstant.LOGICDEL_N);
			funcMapper.saveFunc(func);
			
			//记录操作日志
			String content = "功能管理:用户 " + sessionUser.getUsername() + " 于 " + createTime + " 新增功能:" + JSON.toJSONString(func);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.SAVE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_ADD_FAILED, e);
		}
	}
	
	/**
	 * 逻辑删除功能
	 * @param funcId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult deleteFunc(String funcId,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			funcMapper.deleteFunc(funcId);
			return new BaseResult(ResultStatus.SUCCESS, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_DELETE_FAILED, e);
		}
	}
	
	/**
	 * 修改功能
	 * @param func
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult updateFunc(Func func,User sessionUser,OperateLog operateLog) {
		try {
			
			//组装数据
			String updateTime = DateUtils.formateTime(new Date());
			func.setUpdateTime(updateTime);
			funcMapper.updateFunc(func);
			
			//记录操作日志
			String content = "功能管理:用户 " + sessionUser.getUsername() + " 于 " + updateTime + " 修改功能:" + JSON.toJSONString(func);
			operateLog.setOperatorContent(content);
			operateLogMapper.saveOperateLog(operateLog);
			
			return new BaseResult(ResultStatus.SUCCESS, Message.UPDATE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_UPDATE_FAILED, e);
		}
	}
	
	/**
	 * 根据功能id查询功能
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	@Override
	public BaseResult findFuncById(String funcId) {
		try {
			Func func = funcMapper.findFuncById(funcId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "func", func);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_BYID_FAILED, e);
		}
		
	}

	/**
	 * 查询功能树
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult findFuncTree(String userId) {
		try {
			List<ZtreeNode> ztreeNodes = new ArrayList<>();
			User user = userMapper.findUserById(userId);
			Integer funcType = null;
			if(user.getUserTag() == SystemServiceConstant.USER_USERTAG_INNER) {
				//内置用户
				funcType = SystemServiceConstant.FUNC_FUNCTYPE_INNER;
			}else {
				//普通用户
				funcType = SystemServiceConstant.USER_USERTAG_NORMAL;
			}
			//查询所有顶层功能
			List<Func> topFuncs = funcMapper.findTopFunc(funcType);
			//获取所有子节点
			if(topFuncs != null && topFuncs.size() > 0) {
				for (int i = 0; i < topFuncs.size(); i++) {
					Func func = topFuncs.get(i);
					List<Func> childFunc = findChildFunc(func.getFuncId(), funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}
				//组装ztreeNodes
				ztreeNodes = userServiceZtreeFactory.getFuncZtreeNodes(topFuncs);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcTree", ztreeNodes);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_FUNCTREE_FAILED, e);
		}
		
	}
	
	/**
	 * 递归查询功能子节点
	 * @param funcId
	 * @param funcType
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	private List<Func> findChildFunc(String funcId,Integer funcType){
		try {
			List<Func> funcs = funcMapper.findChildFunc(funcId,funcType);
			if(funcs != null && funcs.size() > 0) {
				//子节点list不为空
				for (int i = 0; i < funcs.size(); i++) {
					Func func = funcs.get(i);
					List<Func> childFunc = findChildFunc(func.getFuncId(),funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}	
			}
			return funcs;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_FUNCCHILD_FAILED, e);
		}
	}
	
	/**
	 * 删除功能及其子节点
	 * @param funcId
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult deleteFuncTree(String funcId,User sessionUser,OperateLog operateLog) {
		try {
			
			//获取所有需要删除的功能节点list
			List<Func> funcs = findChildFunc(funcId,0);
			List<Func> allFuncs = new ArrayList<>(); 
			allFuncs = getAllChildFuncList(funcs,allFuncs);
			allFuncs.add(funcMapper.findFuncById(funcId));
			
			//组装操作日志
			String updateTime = DateUtils.formateTime(new Date());
			String content = "功能管理:用户 " + sessionUser.getUsername() + " 于 " + updateTime + " 修改功能:" + JSON.toJSONString(allFuncs);
			operateLog.setOperatorContent(content);
			
			//提交数据
			BaseResult result = ((FuncService)AopContext.currentProxy()).commitDeleteFuncTree(allFuncs, sessionUser, operateLog);
			
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_DELETE_FUNCTREE_FAILED, e);
		}
	}
	
	/**
	 * 获取所有子节点的功能list,非层级结构
	 * @param funcs
	 * @param allFuncs
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	private List<Func> getAllChildFuncList(List<Func> funcs,List<Func> allFuncs){
		for (Func func : funcs) {
			allFuncs.add(func);
			if(func.getChildrenFunc() != null && func.getChildrenFunc().size() > 0) {
				getAllChildFuncList(func.getChildrenFunc(), allFuncs);
			}
		}
		return allFuncs;
	}
	
	/**
	 * 提交删除子节点功能数
	 * @param allFuncs
	 * @param sessionUser
	 * @param operateLog
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	@Override
	public BaseResult commitDeleteFuncTree(List<Func> allFuncs,User sessionUser,OperateLog operateLog) {
		try {
			//未完成
			operateLogMapper.saveOperateLog(operateLog);
			funcMapper.deleteFuncList(allFuncs);
			return new BaseResult(ResultStatus.SUCCESS, Message.DELETE_SUCCESS);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_DELETE_FUNCTREE_FAILED, e);
		}
	}

	/**
	 * 查询用户功能
	 * @param userId
	 * @param funcType
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	@Override
	public BaseResult findUserFunc(String userId,Integer funcType) {
		try {
			List<Func> funcs = funcMapper.findUserTopFunc(userId, funcType);
			//获取所有子节点
			if(funcs != null && funcs.size() > 0) {
				for (int i = 0; i < funcs.size(); i++) {
					Func func = funcs.get(i);
					List<Func> childFunc = findUserChildFunc(userId, func.getFuncId(), funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}
			}

			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcs", funcs);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_USERFUNC_FAILED, e);
		}
	}
	
	/**
	 * 递归查询用户功能子节点
	 * @param userId
	 * @param funcId
	 * @param funcType
	 * @return
	 * @author sly
	 * @time 2018年12月16日
	 */
	private List<Func> findUserChildFunc(String userId,String funcId,Integer funcType){
		try {
			List<Func> funcs = funcMapper.findUserChildFunc(funcId,funcType,userId);
			if(funcs != null && funcs.size() > 0) {
				//子节点list不为空
				for (int i = 0; i < funcs.size(); i++) {
					Func func = funcs.get(i);
					List<Func> childFunc = findUserChildFunc(userId,func.getFuncId(),funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}	
			}
			return funcs;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_USERFUNCCHILD_FAILED, e);
		}
	}

	/**
	 * 查询全部功能
	 * @return
	 * @author sly
	 * @time 2018年12月18日
	 */
	@Override
	public BaseResult findAllFunc() {
		try {
			//查询所有包括系统内置
			int funcType = 0;
			//查询所有顶层功能
			List<Func> funcs = funcMapper.findTopFunc(funcType);
			//获取所有子节点
			if(funcs != null && funcs.size() > 0) {
				for (int i = 0; i < funcs.size(); i++) {
					Func func = funcs.get(i);
					List<Func> childFunc = findChildFunc(func.getFuncId(), funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcs", funcs);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_ALLFUNC_FAILED, e);
		}
	}

	/**
	 * 查询全部功能树
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@Override
	public BaseResult findAllFuncTree() {
		try {
			List<ZtreeNode> ztreeNodes = new ArrayList<>();
			//查询所有包括系统内置
			int funcType = 0;
			//查询所有顶层功能
			List<Func> funcs = funcMapper.findTopFunc(funcType);
			//获取所有子节点
			if(funcs != null && funcs.size() > 0) {
				for (int i = 0; i < funcs.size(); i++) {
					Func func = funcs.get(i);
					List<Func> childFunc = findChildFunc(func.getFuncId(), funcType);
					if(childFunc != null && childFunc.size() > 0) {
						func.setChildrenFunc(childFunc);
					}
				}
				//组装ztreeNodes
				ztreeNodes = userServiceZtreeFactory.getFuncZtreeNodes(funcs);
			}
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcTree", ztreeNodes);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_ALLFUNCTREE_FAILED, e);
		}
	}
	
	/**
	 * 查询所有顶层功能
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@Override
	public BaseResult findAllTopFunc() {
		try {
			//查询所有包括系统内置
			int funcType = 0;
			//查询所有顶层功能
			List<Func> funcs = funcMapper.findTopFunc(funcType);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcs", funcs);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_TOPFUNC_FAILED, e);
		}
	}
	
	/**
	 * 查询所有子功能
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年12月20日
	 */
	@Override
	public BaseResult findAllChildFunc(String funcId) {
		try {
			//查询所有包括系统内置
			int funcType = 0;
			List<Func> funcs = funcMapper.findChildFunc(funcId, funcType);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcs", funcs);
		}catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_FUNCCHILD_FAILED, e);
		}
	}

	/**
	 * 查询角色的功能
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月14日
	 */
	@Override
	public BaseResult findRoleAllFuncIds(String roleId) {
		try {
			List<String> funcIds = funcMapper.findRoleAllFuncIds(roleId);
			return new BaseResult(ResultStatus.SUCCESS, Message.QUERY_SUCCESS, "funcIds", funcIds);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new ServiceCustomException(FuncReturnCode.FUNC_QUERY_ROLEFUNC_FAILED, e);
		}
	}

	
	
}
