package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("rboard")
public class RboardController {
	
	@Autowired
	RboardService rboardService;

	@RequestMapping("addList")
	public String addList(Model model) {
		System.out.println("RboardController.addList()");
		
		List<RboardVo> rboardList = rboardService.getList();
		
		model.addAttribute("rboardList", rboardList);
		
		return "rboard/addList";
	}

	// 새글 작성
	@RequestMapping("write")
	public String write(@ModelAttribute RboardVo rbvo, HttpSession session) { //title, content
		System.out.println("RboardController.write()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		
		if(authUser != null) {
			rbvo.setUserNo(authUser.getNo()); // userNo, title
			
			rboardService.add(rbvo); 
			
			return "redirect:/rboard/addList";
		} else {
			return "redirect:/user/loginForm";
		}
	}
	
	// 댓글 폼
	@RequestMapping("writeForm")
	public String writeForm(Model model,
							@RequestParam int no) {
		System.out.println("RboardController.writeForm()");
		
		model.addAttribute("no", no);
		
		return "rboard/writeForm";
	}
	
	// 댓글 작성하기
	@RequestMapping("reply")             // no(머리 댓글 번호), title(댓글)
	public String reply(@ModelAttribute RboardVo rbvo,
						HttpSession session) { 
		System.out.println("RboardController.reply()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		
		if(authUser != null) {
			
			rbvo.setUserNo(authUser.getNo());
			rboardService.reply(rbvo); // no, title, userNo
			
			return "redirect:/rboard/addList";
			
		} else {
			return "redirect:/user/loginForm";
		}

	}
}
