package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	GuestbookDao guestDao;
	
	public List<GuestbookVo> addList() {
		List<GuestbookVo> guestList = guestDao.getList();
		return guestList;
	}
	
	public void add(GuestbookVo gvo) {
		guestDao.add(gvo);
	}
	
	public GuestbookVo addGuestResultVo(GuestbookVo gvo) {
		guestDao.insertSelectKey(gvo);
		
		int no = gvo.getNo();

		return guestDao.getGuestVo(no);
	}
	
	// 방명록 글 삭제
	public void delete(GuestbookVo gvo) {
		guestDao.delete(gvo);
	}
	
	// ajax 방명록 글 삭제
	public String remove(GuestbookVo gvo) {
		int count = guestDao.delete(gvo);
		
		if(count > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
}
