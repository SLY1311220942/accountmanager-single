package com.sly.accountmanager.common.model;

import java.io.Serializable;
import java.util.Map;

/**
 * ztree node
 * 
 * @author sly
 * @time 2018年11月14日
 */
public class ZtreeNode implements Serializable {

	private static final long serialVersionUID = -4881471087352036849L;
	
	/**
	 * 节点id
	 */
	private String id;
	/**
	 * 父节点id
	 */
	private String pid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 是否选中
	 */
	private Boolean checked;
	/**
	 * 是否展开
	 */
	private Boolean open;
	/**
	 * 自定义业务属性
	 */
	private Map<String, Object> attrs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Map<String, Object> getAttrs() {
		return attrs;
	}

	public void setAttrs(Map<String, Object> attrs) {
		this.attrs = attrs;
	}

}
