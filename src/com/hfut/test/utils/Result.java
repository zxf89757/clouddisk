package com.hfut.test.utils;

public class Result<T> {

	private boolean valid;// 是否成功标志

	private T data;// 成功时返回的数据

	private String error;// 错误信息

	public Result() {
	}
	
	// 只返回是否成功标志
	public Result(boolean valid) {
		this.valid = valid;
	}
	
	// 成功时的构造器
	public Result(boolean valid, T data) {
		this.valid = valid;
		this.data = data;
	}

	// 错误时的构造器
	public Result(boolean valid, String error) {
		this.valid = valid;
		this.error = error;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "JsonResult [valid=" + valid + ", data=" + data + ", error=" + error + "]";
	}

}
