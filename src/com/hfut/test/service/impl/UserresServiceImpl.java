package com.hfut.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfut.test.dao.UserresDao;
import com.hfut.test.model.Userres;
import com.hfut.test.service.UserresService;

@Service
public class UserresServiceImpl implements UserresService{
	@Autowired
	private UserresDao userresDao;

	public List<Userres> getUserresList(Integer parentid, Boolean hasAuthority) {
		return userresDao.getUserresList(parentid,hasAuthority);
	}

	public void insertUserres(Userres userres) {
		userresDao.insertUserres(userres);
	}

	public void deleteUserres(Integer parentid, Integer id) {
		userresDao.deleteUserres(parentid,id);
	}

	public void updateUserres(Userres userres) {
		userresDao.updateUserres(userres);
	}
}