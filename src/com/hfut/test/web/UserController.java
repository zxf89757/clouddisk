package com.hfut.test.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfut.test.model.Folder;
import com.hfut.test.model.Resourse;
import com.hfut.test.model.User;
import com.hfut.test.model.Userres;
import com.hfut.test.service.FolderService;
import com.hfut.test.service.ResourseService;
import com.hfut.test.service.UserService;
import com.hfut.test.service.UserresService;
import com.hfut.test.utils.Result;

@Controller
@RequestMapping("/user")// url:/模块/资源/{id}/细分 /seckill/list
public class UserController { 
	@Autowired
	private UserService userService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ResourseService resourseService;
	@Autowired
	private UserresService userresService;
	
	@Autowired
	private HttpServletRequest request;
	
	 /**
     * ajax用户注册
     */
    @RequestMapping(value="/regist",method=RequestMethod.POST)
    @ResponseBody
    public Result<User> regist(User user) throws IOException{	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
    	
    	user.setCreatedata(sdf.format(new Date()));
        if(user.getName()==null||user.getName().length()==0){
        	user.setName(user.getUsername());
        }
        user.setPerusedsize(0.0);
        user.setPubusedsize(0.0);
        userService.insertUser(user);
    	 	
    	//生成文件夹
    	Folder folder=new Folder();	    	
    	folder.setFolderdata(sdf.format(new Date()));
    	folder.setFoldername(user.getUsername()+"的根目录");
    	folder.setUserid(user.getUserid());
    	folderService.insertFolder(folder);
      
        //注册成功登陆
		request.getSession().setAttribute("existUser", user);//保存用户到session
		return new Result<User>(true,user);//后面改成重新刷新页面后本句只有前面的true有作用
		/*response.setContentType("text/html;charset=UTF-8");//UTF-8
    	Map<String,Object> map=new HashMap<String,Object>();
		ObjectMapper mapper = new ObjectMapper();
		map.put("valid", true);
		map.put("existUser", user);
		response.getWriter().write(mapper.writeValueAsString(map));//map转换为JSON字符串返回*/
    }
    /**
     * ajax验证用户名
     */
    @RequestMapping(value="/checkname",method=RequestMethod.POST)
    @ResponseBody
    public Result<User> checkname(User user) throws IOException{
    	User existUser = userService.getByUsername(user.getUsername());
		if (existUser != null) {	
			return new Result<User>(false);
		}
		else{
			return new Result<User>(true);
		}
		/*response.setContentType("text/html;charset=UTF-8");// 返回json:{"valid":false}或{"valid":true}
		String value = "{\"valid\":true}";
		response.getWriter().write(value);*/
    }
    /**
     * ajax用户登入
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Result<User> login(User user) throws IOException{
    	
    	User existUser = userService.getByUsername(user.getUsername());
        if(existUser!=null && existUser.getPassword().equals(user.getPassword())){
        	request.getSession().setAttribute("existUser", existUser);//保存用户到session
          	return new Result<User>(true,existUser);
        }
        else{
        	return new Result<User>(false,"用户不存在");
        }
    }
    
    /**
	 * 跳转到网盘
	 */
	@RequestMapping(value="/{username}/diskhome",method = RequestMethod.GET)
    public ModelAndView pubHome(@PathVariable("username") String username){
		ModelAndView mav  = new ModelAndView();
		User userview=userService.getByUsername(username);
		Folder folder= folderService.findRootFolder(userview.getUserid());
		mav.addObject("userview", userview);
		mav.addObject("userroot", folder);
		mav.setViewName("diskhome");
        return mav;
    }
    /**
     * 用户退出
     */
    @RequestMapping("/quit")
    public String quit(){
		// 销毁session
    	request.getSession().invalidate();
		String prePage=request.getHeader("referer")+" ";
		if(prePage==null||prePage.length()==0){
			return "redirect:/index";
		}
		prePage=prePage.split(request.getServerPort()+request.getContextPath()+"/")[1];
		return "redirect:/"+prePage;
	}
    /**
	 * 保存到我的网盘
	 */
	@RequestMapping(value="/addToDisk")
	@ResponseBody
	public Result<Userres> addToDisk(@RequestParam("id") Integer id) throws IOException{
		User user=(User) request.getSession().getAttribute("existUser");
		if(user!=null){
			Resourse res= resourseService.queryById(id);
			Userres userres=new Userres();
			Folder rootFolder=folderService.findRootFolder(user.getUserid());
			userres.setParentid(rootFolder.getFolderid());
			userres.setRes(res);
			userres.setUserresname(res.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
			userres.setUserresupdatetime(sdf.format(new Date()));
			userres.setIslock(1);
			userresService.insertUserres(userres);
			return new Result<Userres>(true);
		}
		return new Result<Userres>(false,"你未登录");
	}
}