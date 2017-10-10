package com.hfut.test.model;

public class Userres {
	@Override
	public String toString() {
		return "Userres [res=" + res + ", parentid=" + parentid
				+ ", userresname=" + userresname + ", userresupdatetime="
				+ userresupdatetime + ", islock=" + islock + "]";
	}
	private Resourse res;
	private Integer parentid;
	private String userresname;
	private String userresupdatetime;
	private Integer islock;
	public Resourse getRes() {
		return res;
	}
	public void setRes(Resourse res) {
		this.res = res;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getUserresname() {
		return userresname;
	}
	public void setUserresname(String userresname) {
		this.userresname = userresname;
	}
	public String getUserresupdatetime() {
		return userresupdatetime;
	}
	public void setUserresupdatetime(String userresupdatetime) {
		this.userresupdatetime = userresupdatetime;
	}
	public Integer getIslock() {
		return islock;
	}
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	
	
}
