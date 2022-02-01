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
		rboardDao.add(rbvo);
	}
	
	public RboardVo getInfo(int no) {
		RboardVo rbvo = rboardDao.getInfo(no);
		return rbvo;
	}
	
	public RboardVo getInfo2(int no) {
		RboardVo rbvo = rboardDao.getInfo2(no);
		return rbvo;
	}

	public void reply(RboardVo rbvo) { // no(머리 댓글), title, userNo
		
		RboardVo info = rboardDao.getInfo(rbvo.getNo()); // (머리댓글의 정보) groupNo, orderNo, depth, no
		info.setTitle(rbvo.getTitle());
		info.setUserNo(rbvo.getUserNo());		

		rboardDao.updateOrderNo(info);// groupNo, orderNo, depth, no, title, userNo
		rboardDao.addReply(info); // [머리댓글 정보] groupNo, orderNo, depth, no, [작성한 댓글 정보]title, userNo
	}
	
	public void delete(RboardVo rbvo) {
		rboardDao.updateOrderNo2(rbvo);
		rboardDao.delete(rbvo.getNo());
	}
	
	public void modify(RboardVo rbvo) {
		rboardDao.modify(rbvo);
	}
	
}
