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
		return boardDao.getList();
	}
	
	public BoardVo getContent(int no) {
		boardDao.updateHit(no);
		return boardDao.getBoardVo(no);
	}
	
	public void write(BoardVo bvo) {
		boardDao.add(bvo);
	}
	
	public int delete(int no) {
		return boardDao.delete(no);
	}
	
	public void updateContent(BoardVo bvo) {
		boardDao.updateContent(bvo);
	}
}
