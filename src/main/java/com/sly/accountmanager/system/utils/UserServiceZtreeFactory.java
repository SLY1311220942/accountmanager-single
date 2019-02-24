package com.sly.accountmanager.system.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sly.accountmanager.common.model.ZtreeNode;
import com.sly.accountmanager.system.model.Func;
import com.sly.accountmanager.system.model.Role;

/**
 * user-service ztree 组装工厂
 * @author sly
 * @time 2018年11月14日
 */
@Component
public class UserServiceZtreeFactory {
	
	/**
	 * 获取完整功能ztree 不带checkbox
	 * @param funcList
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	public List<ZtreeNode> getFuncZtreeNodes(List<Func> funcList){
		List<ZtreeNode> ztreeNodes = new ArrayList<ZtreeNode>();
		assembleFuncZtreeNodes(null,funcList,ztreeNodes);
		return ztreeNodes;
	}
	/**
	 * 组装完整功能ztree节点list
	 * @param funcList
	 * @author sly
	 * @time 2018年11月14日
	 */
	private void assembleFuncZtreeNodes(String pid,List<Func> funcList,List<ZtreeNode> znodeList){
		for (int i = 0; i < funcList.size(); i++) {
			Func func = funcList.get(i);
			ZtreeNode ztreeNode = new ZtreeNode();
			ztreeNode.setId(func.getFuncId());
			ztreeNode.setPid(pid);
			ztreeNode.setName(func.getFuncName());
			ztreeNode.setOpen(false);
			znodeList.add(ztreeNode);
			
			List<Func> childrenFunc = func.getChildrenFunc();
			if(childrenFunc != null && childrenFunc.size() > 0) {
				assembleFuncZtreeNodes(func.getFuncId(),childrenFunc,znodeList);
			}
		}
		
	}
	
	/**
	 * 获取用户功能ztree
	 * @param funcList
	 * @param userFuncIds
	 * @return
	 * @author sly
	 * @time 2018年11月14日
	 */
	public List<ZtreeNode> getUserFuncZtreeNodes(List<Func> funcList,List<String> userFuncIds){
		List<ZtreeNode> ztreeNodes = new ArrayList<ZtreeNode>();
		assembleUserFuncZtreeNodes(null,funcList,userFuncIds,ztreeNodes);
		return ztreeNodes;
	}
	
	/**
	 * 组装用户功能ztree节点list
	 * @param pid
	 * @param funcList
	 * @param userFuncIds
	 * @param znodeList
	 * @author sly
	 * @time 2018年11月14日
	 */
	private void assembleUserFuncZtreeNodes(String pid,List<Func> funcList,List<String> userFuncIds,List<ZtreeNode> znodeList){
		for (int i = 0; i < funcList.size(); i++) {
			Func func = funcList.get(i);
			List<Func> childrenFunc = func.getChildrenFunc();
			
			ZtreeNode ztreeNode = new ZtreeNode();
			ztreeNode.setId(func.getFuncId());
			ztreeNode.setPid(pid);
			ztreeNode.setName(func.getFuncName());
			ztreeNode.setOpen(true);
			if (userFuncIds.contains(func.getFuncId())) {
				ztreeNode.setChecked(true);
			}
			ztreeNode.setOpen(false);
			znodeList.add(ztreeNode);
			
			if(childrenFunc != null && childrenFunc.size() > 0) {
				assembleUserFuncZtreeNodes(func.getFuncId(),childrenFunc,userFuncIds,znodeList);
			}
		}
		
	}
	
	/**
	 * 获取角色功能ztree
	 * @param allFuncs
	 * @param roleFuncIds
	 * @return
	 * @author sly
	 * @time 2019年1月14日
	 */
	public List<ZtreeNode> getRoleFuncZtreeNodes(List<Func> allFuncs, List<String> roleFuncIds) {
		List<ZtreeNode> znodeList = new ArrayList<ZtreeNode>();
		assembleRoleFuncZtreeNodes(null,allFuncs,roleFuncIds,znodeList);
		return znodeList;
	}
	
	/**
	 * 组装角色功能ztree节点list
	 * @param object
	 * @param allFuncs
	 * @param roleFuncIds
	 * @param ztreeNodes
	 * @author sly
	 * @time 2019年1月14日
	 */
	private void assembleRoleFuncZtreeNodes(String pid, List<Func> allFuncs, List<String> roleFuncIds,
			List<ZtreeNode> znodeList) {
		for (int i = 0; i < allFuncs.size(); i++) {
			Func func = allFuncs.get(i);
			List<Func> childrenFunc = func.getChildrenFunc();
			
			ZtreeNode ztreeNode = new ZtreeNode();
			ztreeNode.setId(func.getFuncId());
			ztreeNode.setPid(pid);
			ztreeNode.setName(func.getFuncName());
			ztreeNode.setOpen(true);
			if (roleFuncIds.contains(func.getFuncId())) {
				ztreeNode.setChecked(true);
			}
			ztreeNode.setOpen(false);
			znodeList.add(ztreeNode);
			
			if(childrenFunc != null && childrenFunc.size() > 0) {
				assembleUserFuncZtreeNodes(func.getFuncId(),childrenFunc,roleFuncIds,znodeList);
			}
		}
		
	}
	
	/**
	 * 获取用户角色ztree
	 * @param allRoles
	 * @param userRoleIds
	 * @return
	 * @author sly
	 * @time 2019年1月16日
	 */
	public List<ZtreeNode> getUserRoleZtreeNodes(List<Role> allRoles, List<String> userRoleIds) {
		List<ZtreeNode> znodeList = new ArrayList<ZtreeNode>();
		assembleUserRoleZtreeNodes(null,allRoles,userRoleIds,znodeList);
		return znodeList;
	}
	
	/**
	 * 组装用户角色ztree节点list
	 * @param pid
	 * @param allRoles
	 * @param userRoleIds
	 * @param znodeList
	 * @author sly
	 * @time 2019年1月16日
	 */
	private void assembleUserRoleZtreeNodes(String pid, List<Role> allRoles, List<String> userRoleIds,
			List<ZtreeNode> znodeList) {
		for (int i = 0; i < allRoles.size(); i++) {
			Role role = allRoles.get(i);
			ZtreeNode ztreeNode = new ZtreeNode();
			ztreeNode.setId(role.getRoleId());
			ztreeNode.setPid(pid);
			ztreeNode.setName(role.getRoleName());
			ztreeNode.setOpen(true);
			if (userRoleIds.contains(role.getRoleId())) {
				ztreeNode.setChecked(true);
			}
			ztreeNode.setOpen(false);
			znodeList.add(ztreeNode);
			
		}
		
	}
}
