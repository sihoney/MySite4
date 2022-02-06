 package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UsersVo;

@Repository
public class UsersDao {

	@Autowired
	SqlSession sqlSession;
	
	public UsersDao() {}
	
	public UsersVo getUser(UsersVo usersVo) {
		System.out.println("UsersDao.getUser()");
		
		return sqlSession.selectOne("userbook.getUser", usersVo);
	}
	
	public void add(UsersVo uvo) {
		System.out.println("UsersDao.add()");
		
		int count = sqlSession.insert("userbook.add", uvo);
		System.out.println(count + "건 저장");
	}
	
	public UsersVo getUser2(UsersVo uvo) {
		System.out.println("UsersDao.getUser2()");
		
		return sqlSession.selectOne("userbook.getUser2", uvo);
	}
	
	public void update(UsersVo uvo) {
		System.out.println("UsersDao.update()");
		
		int count = sqlSession.update("userbook.update", uvo);
		System.out.println(count + "건이 수정되었습니다.");
	}
}
