package com.hfut.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfut.test.dao.ResourseDao;
import com.hfut.test.model.Resourse;
import com.hfut.test.service.ResourseService;
import com.hfut.test.utils.PageBean;

@Service
public class ResourseServiceImpl implements ResourseService{
	@Autowired
	private ResourseDao resourseDao;
	
	public void insertRes(Resourse res) {
		resourseDao.insertRes(res);
	}

	public PageBean<Resourse> getResList(Integer page, Integer typeid) {
		PageBean<Resourse> pageBean=new PageBean<Resourse>();
		pageBean.setPage(page);
		int limit = 20;
		pageBean.setLimit(limit);
		pageBean.setTotalCount(resourseDao.getCount(typeid));
		int begin = (page - 1) * limit+1;
		List<Resourse> list = resourseDao.findByPage(begin,begin+limit-1,typeid);
		pageBean.setList(list);
		return pageBean;
	}

	public Resourse queryById(Integer id) {
		return resourseDao.queryById(id);
	}

}