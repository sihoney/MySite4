package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UsersDao;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UsersDao usersDao;
	
	// 로그인 폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		return "user/loginForm";
	}
	
	@RequestMapping(value="login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UsersVo userVo) {
		System.out.println("UserController.login()");
		
		UsersVo authUser = usersDao.getUser(userVo);
		System.out.println(authUser);
		
		// HttpSession에 authUser 저장하기
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/joinForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("UserController.joinForm()");
		
		return "user/joinForm";
	}
	
	@RequestMapping(value="/join", method= {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UsersVo userVo) {
		System.out.println("UserController.join()");
		
		usersDao.add(userVo);
		
		return "user/joinOk";
	}
	
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("UserController.modifyForm()");
		
		return "user/modifyForm";
	}
}
