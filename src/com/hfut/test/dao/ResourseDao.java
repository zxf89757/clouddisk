package com.hfut.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfut.test.model.Resourse;

public interface ResourseDao {
	/**
	 * 插入真实文件
	 */
	void insertRes(Resourse res);

	int getCount(@Param("typeid") Integer typeid);

	List<Resourse> findByPage(@Param("begin") int begin,@Param("end") int end,@Param("typeid") int typeid);

	Resourse queryById(Integer id);
}
