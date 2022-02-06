package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UsersDao;
import com.javaex.vo.UsersVo;

@Service
public class UserService {

	@Autowired
	private UsersDao usersDao;
	
	// 로그인
	public UsersVo login(UsersVo uvo) { 
		return usersDao.getUser(uvo);
	}
	
	public void join(UsersVo uvo) {
		usersDao.add(uvo);
	}
	
	public UsersVo modifyForm(UsersVo authUser) {
		return usersDao.getUser2(authUser);
	}
	
	public void modify(UsersVo uvo) {
		usersDao.update(uvo);
	}
}
