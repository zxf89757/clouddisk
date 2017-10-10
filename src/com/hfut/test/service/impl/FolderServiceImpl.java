package com.hfut.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfut.test.dao.FolderDao;
import com.hfut.test.model.Folder;
import com.hfut.test.model.FolderTree;
import com.hfut.test.service.FolderService;

@Service
public class FolderServiceImpl implements FolderService{
	@Autowired
	private FolderDao folderDao;
	
	public Folder findRootFolder(Integer userid) {
		return folderDao.findRootFolder(userid);
	}
	
	public void insertFolder(Folder folder) {
		folderDao.insertFolder(folder);
	}
	
	public void deleteFolder(Integer folderid) {
		folderDao.deleteFolder(folderid);
	}

	public void updateFolder(Folder folder) {
		folderDao.updateFolder(folder);
	}

	public List<Folder> getFolderList(Integer parentid, Boolean hasAuthority) {
		return folderDao.getFolderList(parentid,hasAuthority);
	}

	public Folder queryById(Integer folderid) {
		return folderDao.queryById(folderid);
	}

	public List<FolderTree> getTree(int parentid) {
		List<FolderTree> list=folderDao.getTree(parentid);
		for(FolderTree e :list){
			e.setList(getTree(e.getFolderid()));
		}
		return list;
	}

}
