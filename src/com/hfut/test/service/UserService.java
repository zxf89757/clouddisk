package com.hfut.test.service;

import com.hfut.test.model.User;

public interface UserService {

	User getByUsername(String username);

	void insertUser(User user);

	User getByID(Integer userid);
}