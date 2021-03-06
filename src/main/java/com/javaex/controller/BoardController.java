package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value="/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(Model model, @RequestParam(value="crtPage", required=false, defaultValue="1") int crtPage) {
		System.out.println("BoardController.list2()");
		
		// 해당 페이지의 글 리스트 10개
		Map<String, Object> pMap = boardService.getBoardList2(crtPage);
		
		model.addAttribute("pMap", pMap);
		
		return "board/list";
	}	
	
	@RequestMapping(value="/read", method= {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam int no, Model model) {
		System.out.println("BoardController.read()");
		
		// jsp에 정보 넘기기(name, hit, regDate, title, content + no)
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
	public String write(@ModelAttribute BoardVo bvo, HttpSession session) { // (title, content + name / hit, regDate)
		System.out.println("BoardController.write()");
		
		UsersVo authUser = (UsersVo) session.getAttribute("authUser");
		bvo.setUserNo(authUser.getNo());
		
		boardService.write(bvo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete")
	public int delete(@ModelAttribute BoardVo bvo) {
		System.out.println("BoardController.delete()");

		int count = boardService.delete(bvo.getNo());
		
		return count;
	}
	
	// ajax
	@ResponseBody
	@RequestMapping("/delete2") 
	public int delete2(@ModelAttribute BoardVo bvo) {
		System.out.println("BoardController.delete2()");
		
		return boardService.delete(bvo.getNo());
	}
	
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam int no, Model model) {
		System.out.println("BoardController.modifyForm()");
		
		BoardVo bvo = boardService.getContent(no);
		
		model.addAttribute("bvo", bvo);
		
		return "board/modifyForm";
	}
	
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo bvo) { //(title, content, no)
		System.out.println("BoardController.modify()");
		
		boardService.updateContent(bvo);
		
		return "redirect:/board/list";
	}
}
