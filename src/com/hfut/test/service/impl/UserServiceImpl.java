package com.hfut.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfut.test.dao.UserDao;
import com.hfut.test.model.User;
import com.hfut.test.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	
	public User getByUsername(String username) {
		return userDao.queryByUsername(username);
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	public User getByID(Integer userid) {
		return userDao.getByID(userid);
	}

}