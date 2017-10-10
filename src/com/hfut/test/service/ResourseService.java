package com.hfut.test.service;

import com.hfut.test.model.Resourse;
import com.hfut.test.utils.PageBean;

public interface ResourseService {
	
	void insertRes(Resourse res);

	PageBean<Resourse> getResList(Integer page, Integer typeid);

	Resourse queryById(Integer id);
}