package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {
	
	@Autowired
	FileService fileService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("FileController.form()");
		 
		return "fileupload/form";
	}
	
	@RequestMapping("/result")
	public String result(@RequestParam("file") MultipartFile file,
						 Model  model) {
		System.out.println("FileController.result()");
		
		String saveName = fileService.restore(file);
		
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
	}
}
