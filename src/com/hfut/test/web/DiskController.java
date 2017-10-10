package com.hfut.test.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hfut.test.model.Folder;
import com.hfut.test.model.Userres;
import com.hfut.test.service.FolderService;
import com.hfut.test.service.UserresService;

@Controller
@RequestMapping("/diskhome")// url:/模块/资源/{id}/细分 /seckill/list
public class DiskController { 
	@Autowired
	private FolderService folderService;
	@Autowired
	private UserresService userresService;
	
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 查看(文件夹+文件)
	 */
	@RequestMapping(value="/{username}/detail/{parentid}")
	@ResponseBody
    public void pubHome(@PathVariable("parentid") Integer parentid) throws JsonProcessingException, IOException{
		List<Folder> folderList;
		List<Userres> resList = null;
		folderList=folderService.getFolderList(parentid,(Boolean) request.getAttribute("hasAuthority"));
		resList=userresService.getUserresList(parentid,(Boolean) request.getAttribute("hasAuthority"));
		
		response.setContentType("text/html;charset=UTF-8");//UTF-8
    	Map<String,Object> map=new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("folderList", folderList);
		map.put("resList", resList);
		response.getWriter().write(mapper.writeValueAsString(map));//map转换为JSON字符串返回
    }
}