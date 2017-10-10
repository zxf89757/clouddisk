package com.hfut.test.utils;

import java.util.List;

public class PageBean<T> {
	private int page;	// 当前页数
//	private int totalPage; // 总页数
	private int totalCount; // 总记录数
	private int limit;	// 每页显示的记录数
	private List<T> list; // 每页显示数据的集合.
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalCount=" + totalCount
				+ ", limit=" + limit + ", list=" + list + "]";
	}
	/*
	 * 计算页数
	 */
	public int getTotalPage() {
		int totalPage=totalCount/limit;
		return totalCount%limit==0 ? totalPage : totalPage+1;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}