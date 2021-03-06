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
	public GalleryVo getGalleryVo(@ModelAttribute GalleryVo glvo, HttpSession session) {
		System.out.println("GalleryController.getGalleryVo()");
		
		GalleryVo glvoInfo = galleryService.getGalleryVo(glvo.getNo());
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");

		if(authUser != null) {
			glvoInfo.setNo(authUser.getNo()); // session 정보를 GalleryVo no에 담아서 보냈다
		}
		
		return glvoInfo;
	}
	
	@RequestMapping("/delete")
	public void delete(@ModelAttribute GalleryVo glvo) {
		System.out.println("GalleryController.delete()");
	
		galleryService.delete(glvo.getNo());
	}
}
