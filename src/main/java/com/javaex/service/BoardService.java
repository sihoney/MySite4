package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public Map<String, Object> getBoardList2(int crtPage) {
		/////////////////////
		//// 리스트 가져오기 
		/////////////////////

		// 페이지 당 글 개수
		int listCnt = 10;
		
		// 시작글 번호
		int startNum = (crtPage - 1)* listCnt + 1;
		
		// 마지막글 번호
		int endNum = startNum + listCnt - 1;
		
		List<BoardVo> boardList = boardDao.getList2(startNum, endNum);
		/////////////////////
		//// 페이징 
		/////////////////////	
		int totalCnt = boardDao.totalCnt();
		
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// 마지막 버튼 번호
		int endPageBtnNo = (int) (Math.ceil(crtPage / (double)pageBtnCount)) * pageBtnCount;
		
		// 시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		// 다음 화살표 유무
		boolean next = true;
		if(endPageBtnNo * listCnt < totalCnt) {
			next = true;
		} else { // 다음 화살표가 안보이면 마지막 버튼 값을 다시 계산
			endPageBtnNo = (int) Math.ceil(totalCnt / (double)listCnt);
		}
		
		// 이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
 		
		/////////////////////
		//// 포장 
		/////////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardList", boardList);
		pMap.put("prev", prev);
		pMap.put("next", next);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		
		System.out.println(pMap);
		
		return pMap;
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
