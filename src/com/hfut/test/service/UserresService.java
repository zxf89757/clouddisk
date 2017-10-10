package com.hfut.test.service;

import java.util.List;

import com.hfut.test.model.Userres;

public interface UserresService {

	List<Userres> getUserresList(Integer parentid, Boolean hasAuthority);

	void insertUserres(Userres userres);

	void deleteUserres(Integer parentid, Integer id);

	void updateUserres(Userres userres);
	
}