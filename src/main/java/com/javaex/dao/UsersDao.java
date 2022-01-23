 package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UsersVo;

@Repository
public class UsersDao {

	/////////////////////
	// field
	/////////////////////
	@Autowired
	SqlSession sqlSession;
	
	/////////////////////
	// constructor
	/////////////////////
	public UsersDao() {
	}
	
	/////////////////////
	// method
	/////////////////////
	public UsersVo getUser(UsersVo usersVo) {
		System.out.println("UsersDao.getUser()");
		
		UsersVo authUser = sqlSession.selectOne("userbook.getUser", usersVo);
		
		return authUser;
	}
	
	public void add(UsersVo uvo) {
		System.out.println("UsersDao.add()");
		
		int count = sqlSession.insert("userbook.add", uvo);
		System.out.println(count + "건 저장");
	}
	
	/*
	public int addUser(UsersVo uvo) {
		
		int count = -1;
		this.getConnection();
		
		try {
			
			String query = "";
			query += " insert into users (no, id, password, name, gender) ";
			query += " values (seq_users_id.nextval, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, uvo.getId());
			pstmt.setString(2, uvo.getPassword());
			pstmt.setString(3, uvo.getName());
			pstmt.setString(4, uvo.getGender());
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
		return count;
	}
	*/
	/*
	public int updateUser(UsersVo uvo) {
		int count = 1;
		this.getConnection();
		
		try {
			
			String query = "";
			query += " update users ";
			query += " set password = ? ";
			query += "     ,name = ? ";
			query += "     ,gender = ? ";
			query += " where id = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, uvo.getPassword());
			pstmt.setString(2, uvo.getName());
			pstmt.setString(3, uvo.getGender());
			pstmt.setString(4, uvo.getId());
			
			count = pstmt.executeUpdate();
			
			System.out.println(count + "건이 수정되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		return count;
	}
	*/
}
