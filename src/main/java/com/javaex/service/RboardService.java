package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {

	@Autowired
	RboardDao rboardDao;
	
	public List<RboardVo> getList() {
		List<RboardVo> rboardList = rboardDao.getList();
		
		return rboardList;
	}
	
	public void add(RboardVo rbvo) { // userNo, title
		
		System.out.println(rbvo);
		
		rboardDao.add(rbvo);
	}
	
	public RboardVo getInfo(int no) {
		RboardVo rbvo = rboardDao.getInfo(no);
		return rbvo;
	}

	public void reply(RboardVo rbvo) { // no(머리 댓글), title, userNo
		
		RboardVo info = rboardDao.getInfo(rbvo.getNo()); // groupNo, orderNo, depth, no(머리댓글의 정보)
		info.setTitle(rbvo.getTitle());
		info.setUserNo(rbvo.getUserNo());		
		
		// groupNo=8, orderNo=1, depth=0
		
		rboardDao.updateOrderNo(info);// groupNo, orderNo, depth, no, title, userNo
		
		rboardDao.addReply(info); // [머리댓글 정보] groupNo, orderNo, depth, no, [작성한 댓글 정보]title, userNo
	}
}
