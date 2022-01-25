package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> listForm() {
		List<BoardVo> boardList = boardDao.getList();
		return boardList;
	}
	
	public BoardVo getContent(int no) {
		BoardVo bvo = boardDao.getBoardVo(no);
		
		boardDao.updateHit(no);
		
		return bvo;
	}
	
	public void write(BoardVo bvo) {
		boardDao.add(bvo);
	}
	
	public void delete(int no) {
		boardDao.delete(no);
	}
	
	public void updateContent(BoardVo bvo) {
		boardDao.updateContent(bvo);
	}
}
