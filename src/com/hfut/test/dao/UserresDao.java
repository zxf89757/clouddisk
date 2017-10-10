package com.hfut.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfut.test.model.Userres;

public interface UserresDao {

	/**
	 * 根据父极文件夹查询文件
	 * hasAuthority：用户认证信息
	 */
	List<Userres> getUserresList(@Param("parentid") Integer parentid,@Param("hasAuthority") Boolean hasAuthority);

	void insertUserres(Userres userres);

	void deleteUserres(@Param("parentid") Integer parentid,@Param("id") Integer id);

	void updateUserres(Userres userres);

	Userres queryBy(@Param("parentid") int parentid, @Param("id") int id);

}
