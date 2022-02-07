package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> getList() {
		return sqlSession.selectList("board.getList");
	}
	
	// 글 리스트 가져오기(리스트 + 페이징)
	public List<BoardVo> getList2(int startNum, int endNum){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return sqlSession.selectList("board.getList2", map);
	}
	
	public BoardVo getBoardVo(int no) {
		return sqlSession.selectOne("board.getBoardVo", no);
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
	
	public int totalCnt() {
		return sqlSession.selectOne("board.totalCnt");
	}
}
