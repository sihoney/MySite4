package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> getList() {
		List<BoardVo> boardList = sqlSession.selectList("board.getList");
		return boardList;
	}
	
	public BoardVo getBoardVo(int no) {
		BoardVo bvo = sqlSession.selectOne("board.getBoardVo", no);
		return bvo;
	}
	
	public void add(BoardVo bvo) {
		int count = sqlSession.insert("board.add", bvo);
		System.out.println(count + "건이 저장되었습니다.");
	}
	
	public void delete(int no) {
		int count = sqlSession.delete("board.delete", no);
		System.out.println(count + "건이 삭제되었습니다.");
	}
	
	public void updateHit(int no) {
		sqlSession.update("board.updateHit", no);
	}
}
