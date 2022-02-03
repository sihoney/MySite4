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
	
	public int delete(int no) {
		int count = sqlSession.delete("board.delete", no);
		System.out.println(count + "건이 삭제되었습니다.");
		return count;
	}
	
	public void updateHit(int no) {
		int count = sqlSession.update("board.updateHit", no);
		System.out.println(count + "회 조회수 증가하였습니다.");
	}
	
	public void updateContent(BoardVo bvo) {
		int count = sqlSession.update("board.updateContent", bvo);
		System.out.println(count + "건이 수정되었습니다.");
	}
}
