package com.hfut.test.model;

public class Resourse {
	private Integer id;
	private Integer userid;
	private String name;
	private Integer typeid;
	private String url;
	private String createdata;
	private String describe;
	private Double size;
	private Integer savedtime;
	@Override
	public String toString() {
		return "Resourse [id=" + id + ", userid=" + userid + ", name=" + name
				+ ", typeid=" + typeid + ", url=" + url + ", createdata="
				+ createdata + ", describe=" + describe + ", size=" + size
				+ ", savedtime=" + savedtime + ", share=" + share + "]";
	}
	private Integer share;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreatedata() {
		return createdata;
	}
	public void setCreatedata(String createdata) {
		this.createdata = createdata;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public Integer getSavedtime() {
		return savedtime;
	}
	public void setSavedtime(Integer savedtime) {
		this.savedtime = savedtime;
	}
	public Integer getShare() {
		return share;
	}
	public void setShare(Integer share) {
		this.share = share;
	}

}
