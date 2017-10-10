package com.hfut.test.dao;

import com.hfut.test.model.User;

public interface UserDao {	 
    /**
     * 通过用户名查询用户
     */
	User queryByUsername(String username);

	/**
     * 添加新用户
     */
	void insertUser(User user);

	User getByID(Integer userid);
 
}