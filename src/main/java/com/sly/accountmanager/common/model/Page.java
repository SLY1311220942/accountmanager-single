package com.sly.accountmanager.common.model;

import java.io.Serializable;

/**
 * 分页对象
 * 
 * @author sly
 * @time 2018年11月19日
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 6007911006181757470L;

	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private String sortName;
	private String sortOrder = "asc";

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Page() {
		super();
	}

	public Page(Integer currentPage, Integer pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	/**
	 * 得到limit字句中第一个参数(开始的记录数)
	 * 
	 * @return
	 * @author sly
	 * @time 2018年11月23日
	 */
	public int getStartNum() {
		return (currentPage - 1) * pageSize;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
