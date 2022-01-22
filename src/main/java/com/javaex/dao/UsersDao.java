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
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
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
	public UsersVo getUser(String id, String password) {
		
		UsersVo uvo = null;
		this.getConnection();
		
		try {

			String query = "";
			query += " select no, id, password, name, gender "; // no 추가
			query += " from users ";
			query += " where id = ? ";
			query += " and password = ? ";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt("no");
				String ID = rs.getString("id");
				String PASSWORD = rs.getString("password");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				
				uvo = new UsersVo(no, ID, PASSWORD, name, gender);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		return uvo;
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
