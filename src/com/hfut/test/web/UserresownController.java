package com.hfut.test.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfut.test.model.Resourse;
import com.hfut.test.model.Userres;
import com.hfut.test.service.UserresService;
import com.hfut.test.utils.Result;

@Controller
@RequestMapping("/userresown")
public class UserresownController {
	@Autowired
	private UserresService userresService;
	
	/**
	 * 删除文件
	 */
	@RequestMapping(value="/{parentid}/deleteUserres/{id}")
	@ResponseBody
	public Result<Userres> delete(@PathVariable("parentid") Integer parentid,@PathVariable("id") Integer id) throws IOException{	
		userresService.deleteUserres(parentid,id);
		return new Result<Userres>(true);	
	}
	/**
	 * 修改文件
	 */
	@RequestMapping(value="/{parentid}/updateUserres/{id}")
	@ResponseBody
	public Result<Userres> update(Userres userres,@PathVariable("id") Integer id) throws IOException{
		Resourse res=new Resourse();
		res.setId(id);
		userres.setRes(res);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		userres.setUserresupdatetime(sdf.format(new Date()));
		userresService.updateUserres(userres);
		return new Result<Userres>(true);
		
	}
}
