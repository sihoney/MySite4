package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		return guestbookService.addList();
	}
	
	@ResponseBody // 데이터만 보낼 경우
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo gvo) {
		System.out.println("ApiGuestbookController.write()");
		
		return guestbookService.addGuestResultVo(gvo);
	}
	
	@ResponseBody // 데이터만 보낼 경우
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo gvo) { 
		System.out.println("ApiGuestbookController.write2()");

		return guestbookService.addGuestResultVo(gvo);
	}	
	
	@ResponseBody // 데이터만 보낼 경우
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo gvo) {
		System.out.println("ApiGuestbookController.remove()");
		
		return guestbookService.remove(gvo);
	}
}
