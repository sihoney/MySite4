package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("guest")
public class GuestbookController {
	
	@Autowired
	GuestbookDao guestDao;
	
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestbookController.addList()");
		
		List<GuestbookVo> guestList = guestDao.getList();
		
		model.addAttribute("guestList", guestList);
		
		return "guestbook/addList";
	}
	
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo gvo) {
		// @ModelAttribute: param과 일치하는 setter를 찾아 값을 넣어준다.(default 생성자를 꼭 정의!)
		System.out.println("GuestbookController.add()");
		
		guestDao.add(gvo);
		
		return "";
	}
}
