package com.hfut.test.model;

public class User {
	private Integer userid;
	private String username;
	private String password;
	private String email;
	private String name;
	private Integer age;
	private String gender;
	private String createdata;
	private Double pubusedsize;
	private Double  perusedsize;
	public Integer getUserid() {
		return userid;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", name="
				+ name + ", age=" + age + ", gender=" + gender
				+ ", createdata=" + createdata + ", pubusedsize=" + pubusedsize
				+ ", perusedsize=" + perusedsize + "]";
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCreatedata() {
		return createdata;
	}
	public void setCreatedata(String createdata) {
		this.createdata = createdata;
	}
	public Double getPubusedsize() {
		return pubusedsize;
	}
	public void setPubusedsize(Double pubusedsize) {
		this.pubusedsize = pubusedsize;
	}
	public Double getPerusedsize() {
		return perusedsize;
	}
	public void setPerusedsize(Double perusedsize) {
		this.perusedsize = perusedsize;
	}
	
}