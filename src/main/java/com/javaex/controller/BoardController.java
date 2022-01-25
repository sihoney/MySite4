package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UsersVo;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("BoardController.list()");
		
		List<BoardVo> boardList = boardService.listForm();
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam int no, Model model) {
		System.out.println("BoardController.read()");
		
		// jsp에 정보 넘기기(name, hit, regDate, title, content)
		BoardVo boardVo = boardService.getContent(no);
		model.addAttribute("boardVo", boardVo);
		
		return "board/read";
	}
	
	@RequestMapping("/writeForm")
	public String writeForm(HttpSession session) {
		System.out.println("BoardController.writeForm()");
		
		if(session.getAttribute("authUser") != null) {
			return "board/writeForm";
		} else {
			return "redirect:/main";
		}
	}
	
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo bvo, HttpSession session) { 
		// (title, content + name / hit, regDate)
		System.out.println("BoardController.write()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		bvo.setUserNo(authUser.getNo());
		
		boardService.write(bvo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int no) {
		System.out.println("BoardController.delete()");
		
		boardService.delete(no);
		
		return "board/list";
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm() {
		System.out.println("BoardController.modifyForm()");
		
		
		return "board/modifyForm";
	}
}
