package com.hfut.test.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfut.test.model.Resourse;
import com.hfut.test.service.ResourseService;
import com.hfut.test.utils.Result;

@Controller
@RequestMapping("/userres")
public class UserresController {
	@Autowired
	private ResourseService resourseService;
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping("/{parentid}/download/{id}")
	@ResponseBody
    public Result<Resourse> fileDownload(@PathVariable("id") Integer id) throws Exception{ 
		//准备工作：res,name,path
		Resourse res=resourseService.queryById(id);
		int idx = res.getUrl().lastIndexOf("/");
		String name = res.getUrl().substring(idx + 1, res.getUrl().length());
		idx=name.indexOf('_');
		name=name.substring(idx+1, name.length());
        //String path = "F:\\clouddiskresroot\\"+res.getUrl();
		String path = request.getServletContext().getRealPath("/") +res.getUrl();
        
        //设置相关响应头
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="+URLEncoder.encode(name, "UTF-8"));
        
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        byte[] buffer = new byte[1024];
        
        BufferedInputStream in=new BufferedInputStream(new FileInputStream(new File(path))); 
        OutputStream out = response.getOutputStream();  
 
        int b=in.read(buffer);  
        while(b != -1) {  
            out.write(buffer,0,b);
            b = in.read(buffer);
        }  
        in.close();  
        out.close(); 
        out.flush();
		return null;
    }
}
