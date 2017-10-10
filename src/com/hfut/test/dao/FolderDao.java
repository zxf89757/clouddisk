package com.hfut.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfut.test.model.Folder;
import com.hfut.test.model.FolderTree;

public interface FolderDao {
	/**
	 * 找根目录
	 */
	Folder findRootFolder(Integer userid);
	
	/**
	 * 插入目录
	 */
	void insertFolder(Folder folder);

	/**
	 * 删除目录
	 */
	void deleteFolder(Integer folderid);
	
	/**
	 * 修改目录
	 */
	void updateFolder(Folder folder);
	
	/**
	 * 查询本级目录(hasAuthority认证用户角色)
	 */
	List<Folder> getFolderList(@Param("parentid") Integer parentid,@Param("hasAuthority") Boolean hasAuthority);
	
	/**
	 * 通过Id查询目录
	 */
	Folder queryById(Integer folderid);

	List<FolderTree> getTree(int parentid);
}
