package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("ApiGuestbookController.addList()");
		
		return "apiGuestbook/addList";
	}
	
	@ResponseBody // 데이터만 보낼 경우
	@RequestMapping("/list")
	public List<GuestbookVo> list() {
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.addList();
		
		return guestbookList;
	}
	
	@ResponseBody // 데이터만 보낼 경우
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo gvo) {
		System.out.println("ApiGuestbookController.write()");
		
		GuestbookVo gbvo = guestbookService.addGuestResultVo(gvo);
		return gbvo;
	}
}
