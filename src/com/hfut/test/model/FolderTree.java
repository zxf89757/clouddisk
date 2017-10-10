package com.hfut.test.model;

import java.util.ArrayList;
import java.util.List;

public class FolderTree {
	private Integer folderid;
	private Integer parentid;
	private String foldername;
	private String folderdata;
	private Integer islock;
	private Integer userid;
	private List<FolderTree> list= new ArrayList<FolderTree>();
	public Integer getFolderid() {
		return folderid;
	}
	public void setFolderid(Integer folderid) {
		this.folderid = folderid;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getFolderdata() {
		return folderdata;
	}
	public void setFolderdata(String folderdata) {
		this.folderdata = folderdata;
	}
	public Integer getIslock() {
		return islock;
	}
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public List<FolderTree> getList() {
		return list;
	}
	public void setList(List<FolderTree> list) {
		this.list = list;
	}
	
}
