package com.hfut.test.service;

import java.util.List;

import com.hfut.test.model.Folder;
import com.hfut.test.model.FolderTree;

public interface FolderService {

	Folder findRootFolder(Integer userid);
	
	void insertFolder(Folder folder);

	void deleteFolder(Integer folderid);

	void updateFolder(Folder folder);

	List<Folder> getFolderList(Integer parentid, Boolean hasAuthority);
	
	Folder queryById(Integer folderid);

	List<FolderTree> getTree(int parentid);

}
