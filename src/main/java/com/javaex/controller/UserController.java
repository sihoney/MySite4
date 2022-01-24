package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// 로그인 폼
	@RequestMapping(value="/loginForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("UserController.loginForm()");
		return "user/loginForm";
	}
	
	@RequestMapping(value="login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UsersVo userVo, HttpSession session) {
		System.out.println("UserController.login()");
		
		UsersVo authUser = userService.login(userVo);
		System.out.println(authUser);
		
		if(authUser != null) {  // 로그인 성공
			System.out.println("로그인 성공");
			
			session.setAttribute("authUser", authUser);
			return "redirect:/main";			
		} else {  // 로그인 실패
			System.out.println("로그인 실패");
			
			return "redirect:/user/loginForm?result=fail";
		}

	}
	
	@RequestMapping(value="logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		session.removeAttribute("authUser");
		
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
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	@RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("UserController.modifyForm()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser"); // no, name  (+ gender, password, id)
		
		UsersVo authUserInfo = userService.modifyForm(authUser);
		model.addAttribute("authUserInfo", authUserInfo);
		
		return "user/modifyForm";
	}
	
	@RequestMapping(value="/modify", method= {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UsersVo uvo, HttpSession session) { // uvo (password, name, gender)
		System.out.println("UserContorller.modify()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		uvo.setNo(authUser.getNo());
		
		// db 업데이트
		userService.modify(uvo);
		
		// http session 업데이트
		session.removeAttribute("authUser");
		session.setAttribute("authUser", authUser);
		
		return "redirect:/main";
	}
}
