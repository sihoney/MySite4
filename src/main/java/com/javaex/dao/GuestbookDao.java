package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public GuestbookDao() {
		
	}

	public List<GuestbookVo> getList() {
		System.out.println("GuestbookDao.getList()");
		
		return sqlSession.selectList("guestbook.getList");
	}
	
	public void add(GuestbookVo gvo) {
		System.out.println("GuestbookDao.add()");
		
		int count = sqlSession.insert("guestbook.add", gvo);
		System.out.println(count + "건이 저장되었습니다.");
	}
	
	public int delete(GuestbookVo gvo) {
		System.out.println("GuestbookDao.delete()");
		
		int count = sqlSession.delete("guestbook.delete", gvo);
		System.out.println(count + "건이 삭제되었습니다.");
		
		return count;
	}
	
	public void insertSelectKey(GuestbookVo gvo) {

		int count = sqlSession.insert("guestbook.insertSelectKey", gvo);
		System.out.println(count + "건이 저장되었습니다.");
	}
	
	public GuestbookVo getGuestVo(int no) {
		return sqlSession.selectOne("guestbook.getGuestVo", no);
	}

}
