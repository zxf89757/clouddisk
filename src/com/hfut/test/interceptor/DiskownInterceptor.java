package com.hfut.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hfut.test.model.Folder;
import com.hfut.test.model.User;
import com.hfut.test.dao.FolderDao;

public class DiskownInterceptor implements HandlerInterceptor{
	@Autowired
	private FolderDao folderDao;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		System.out.println(url);
		int parentid=Integer.parseInt(url.split("/")[5]);
		Folder parentfolder=folderDao.queryById(parentid);
		User existUser=(User) request.getSession().getAttribute("existUser");
		if(existUser!=null&&existUser.getUserid().equals(parentfolder.getUserid())){
			System.out.println("haha");
			return true;	
		}
		else{
			System.out.println("haha2");
			return false;
		}
	}
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
