package com.sly.accountmanager.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sly.accountmanager.system.model.Func;

/**
 * _功能mapper
 * @author sly
 * @time 2018-11-12
 */
public interface FuncMapper {
	/**
	 * _保存功能信息
	 * @param func
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int saveFunc(Func func);
	
	/**
	 * _逻辑删除功能
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int deleteFunc(String funcId);
	
	/**
	 * _修改功能
	 * @param func
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	int updateFunc(Func func);
	
	/**
	 * _根据功能id查询功能
	 * @param funcId
	 * @return
	 * @author sly
	 * @time 2018年11月13日
	 */
	Func findFuncById(String funcId);
	
	/**
	 * _查询顶层功能 
	 * @param funcType 如果是普通类型不查询系统内置,如果是系统内置则查询所有
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	List<Func> findTopFunc(@Param("funcType") Integer funcType);
	
	/**
	 * _查询子节点
	 * @param funcId
	 * @param funcType 如果是普通类型不查询系统内置,如果是系统内置则查询所有
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	List<Func> findChildFunc(@Param("funcId") String funcId,@Param("funcType") Integer funcType);
	
	/**
	 * _查询用户的功能列表父节点
	 * @param userId
	 * @param funcType
	 * @return
	 * @author sly
	 * @time 2018年12月16日
	 */
	List<Func> findUserTopFunc(@Param("userId") String userId,@Param("funcType") Integer funcType);
	
	/**
	 * _查询用户子节点
	 * @param funcId
	 * @param funcType
	 * @param userId
	 * @return
	 * @author sly
	 * @time 2018年12月16日
	 */
	List<Func> findUserChildFunc(
			@Param("funcId") String funcId,
			@Param("funcType") Integer funcType,
			@Param("userId") String userId);
	
	/**
	 * _批量删除功能
	 * @param allFuncs
	 * @return
	 * @author sly
	 * @time 2019年1月2日
	 */
	int deleteFuncList(List<Func> allFuncs);

	/**
	 *  _查询查询角色功能id
	 * @param roleId
	 * @return
	 * @author sly
	 * @time 2019年1月14日
	 */
	List<String> findRoleAllFuncIds(String roleId);

	


}
