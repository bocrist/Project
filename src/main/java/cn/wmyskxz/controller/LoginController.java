package cn.wmyskxz.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.wmyskxz.entity.User;
import cn.wmyskxz.service.UserService;

@Controller
public class LoginController {
	@Autowired
    private UserService userService;
    @RequestMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("userpasswd") String userpasswd,
            Model model) {
        String error = null;
        if (username != null && userpasswd != null) {
        	/*
        	@SuppressWarnings("deprecation")
			Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        	// 创建SecurityManager (根据配置创建SecurityManager实例)
        	SecurityManager security = (SecurityManager) factory.getInstance();
        	SecurityUtils.setSecurityManager((org.apache.shiro.mgt.SecurityManager) security);
*/
            //初始化
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, userpasswd);
            try {
                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
                subject.login(token);
                return "success";
            }catch (Exception e){
                e.printStackTrace();
                error = "未知错误，错误信息：" + e.getMessage();
            }
        } else {
            error = "请输入用户名和密码";
        }
        //登录失败，跳转到login页面，这里不做登录成功的处理，由
        model.addAttribute("error", error);
        return "fail";
    }

	@RequestMapping("/outLogin")
	public String doLogout() {
		//退出操作后一定要重定向页面
		Subject subject = SecurityUtils.getSubject(); 
	    subject.logout();
	    return "index";
     }
	
    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }
    

    
    @RequestMapping("/doRegist")
    public String addUser(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        
        String username = request.getParameter("username");
        String userpasswd = request.getParameter("userpasswd");   
        user.setUserName(username);
        user.setUserPasswd(userpasswd);


        userService.addUser(user);

        return "index";
    }
}