package com.hfut.test.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hfut.test.model.Folder;
import com.hfut.test.model.Resourse;
import com.hfut.test.model.User;
import com.hfut.test.model.Userres;
import com.hfut.test.service.FolderService;
import com.hfut.test.service.ResourseService;
import com.hfut.test.service.UserresService;
import com.hfut.test.utils.Result;
import com.hfut.test.utils.VerifyFileType;

@Controller
@RequestMapping("/diskownhome")
public class DiskownController {
	@Autowired
	private FolderService folderService;
	@Autowired
	private UserresService userresService;
	@Autowired
	private ResourseService resourseService;
	
	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 新建文件夹
	 */
	@RequestMapping(value="/{username}/insertFolder/{parentid}")
	@ResponseBody
	public Result<Folder> insert(@RequestParam("foldername") String foldername,@PathVariable("parentid") Integer parentid) throws IOException{
		Folder folder=new Folder();
		folder.setParentid(parentid);
		folder.setFoldername(foldername);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		folder.setFolderdata(sdf.format(new Date()));
		folder.setUserid(((User)request.getSession().getAttribute("existUser")).getUserid());
		folder.setIslock(1);
		folderService.insertFolder(folder);
		return  new Result<Folder>(true,folder);	
	}
	/**
	 * 删除文件夹
	 */
	@RequestMapping(value="/{username}/deleteFolder/{folderid}")
	@ResponseBody
	public Result<Folder> delete(@PathVariable("folderid") Integer folderid) throws IOException{	
		folderService.deleteFolder(folderid);
		return new Result<Folder>(true);	
	}
	/**
	 * 修改文件夹
	 */
	@RequestMapping(value="/{username}/updateFolder/{folderid}")
	@ResponseBody
	public Result<Folder> update(Folder folder) throws IOException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		folder.setFolderdata(sdf.format(new Date()));
		folderService.updateFolder(folder);
		return new Result<Folder>(true);
		
	}
	
////////////////////////////////////////	
	/**
	 * 上传文件
	 * @throws Exception 
	 */
	@RequestMapping(value="/{username}/upload/{parentid}")
	@ResponseBody
	public Result<Userres> upload(@RequestParam("fileupload") CommonsMultipartFile file,@RequestParam("parentid") Integer parentid,@RequestParam("share") Integer share) throws Exception{
		//文件存储路径：/WEB-INF/res/hex.charAt(0)/hex.charAt(1)/UUIDUtils.getUUID()+"_"+uploadFileName;
		String root=request.getServletContext().getRealPath("/WEB-INF/res");
		String path=VerifyFileType.savaFile(root, file);//"WEB-INF/res/" +
		
		User existUser=(User) request.getSession().getAttribute("existUser");
		File newFile=new File(root+"/"+path);
		Resourse res=new Resourse();
		res.setUserid(existUser.getUserid());
		res.setName(file.getOriginalFilename());
		res.setTypeid(VerifyFileType.getTypeid(newFile));
		res.setUrl("WEB-INF/res/" +path);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		res.setCreatedata(sdf.format(new Date()));
		//res.setDescribe(request.getParameter("describe"));
		res.setSize((double) file.getSize()/(1024*1024));
		res.setSavedtime(0);
		res.setShare(share);
		resourseService.insertRes(res);
		
		Userres userres=new Userres();
		userres.setRes(res);
		userres.setParentid(parentid);
		userres.setUserresname(file.getOriginalFilename());
		userres.setUserresupdatetime(sdf.format(new Date()));
		userres.setIslock(~share);
		userresService.insertUserres(userres);
		return new Result<Userres>(true,userres);
		
		/*String root="F:\\clouddiskresroot";
		String path=VerifyFileType.savaFile(root, file);//"WEB-INF/res/" +
		
		User existUser=(User) request.getSession().getAttribute("existUser");
		File newFile=new File(root+"/"+path);
		Resourse res=new Resourse();
		res.setUserid(existUser.getUserid());
		res.setName(file.getOriginalFilename());
		res.setTypeid(VerifyFileType.getTypeid(newFile));
		res.setUrl(path);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
		res.setCreatedata(sdf.format(new Date()));
		//res.setDescribe(request.getParameter("describe"));
		res.setSize((double) file.getSize()/(1024*1024));
		res.setSavedtime(0);
		res.setShare(share);
		resourseService.insertRes(res);
		
		Userres userres=new Userres();
		userres.setRes(res);
		userres.setParentid(parentid);
		userres.setUserresname(file.getOriginalFilename());
		userres.setUserresupdatetime(sdf.format(new Date()));
		userres.setIslock(share);
		userresService.insertUserres(userres);
		return new Result<Userres>(true,userres);*/
	}
	/**
	 * 获取文件夹树
	 */
/*	@RequestMapping(value="/{username}/addToDisk/{parentid}")
	@ResponseBody
	public Result<List<FolderTree>> addToDisk(@PathVariable("parentid") Integer parentid) throws IOException{
		List<FolderTree> list= folderService.getTree(100);
		return new Result<List<FolderTree>>(true,list);
		
		response.setContentType("text/html;charset=UTF-8");//UTF-8
    	Map<String,Object> map=new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("folderList", list);
		response.getWriter().write(mapper.writeValueAsString(map));//map转换为JSON字符串返回
	
		
	}*/
}
