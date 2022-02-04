package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {

	@Autowired
	GalleryService galleryService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("GalleryController.list()");
		
		List<GalleryVo> galleryList = galleryService.getList();
		
		model.addAttribute("galleryList", galleryList);
		
		return "gallery/list";
	}
	
	@RequestMapping("/add")
	public String add(@ModelAttribute GalleryVo glvo, HttpSession session) {
		System.out.println("GalleryController.add()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/user/loginForm";
		} else {
			glvo.setUserNo(authUser.getNo());
			
			galleryService.restore(glvo); // userNo, content, file
			
			return "redirect:/gallery/list";
		}
	}
	
	@ResponseBody
	@RequestMapping("/getGalleryVo")
	public GalleryVo getGalleryVo(@ModelAttribute GalleryVo glvo) {
		System.out.println("GalleryController.getGalleryVo()");
		
		GalleryVo glvoInfo = galleryService.getGalleryVo(glvo.getNo());
		
		return glvoInfo;
	}
}
