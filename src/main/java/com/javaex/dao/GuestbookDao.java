package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	/////////////////////
	// field
	/////////////////////
	@Autowired
	SqlSession sqlSession;
	
	/////////////////////
	// constructor
	/////////////////////
	public GuestbookDao() {
		
	}
	
	
	/////////////////////
	// method
	/////////////////////
	public List<GuestbookVo> getList() {
		System.out.println("GuestbookDao.getList()");
		
		List<GuestbookVo> guestList = sqlSession.selectList("guestbook.getList");
		return guestList;
	}
	
	public void add(GuestbookVo gvo) {
		System.out.println("GuestbookDao.add()");
		
		int count = sqlSession.insert("guestbook.add", gvo);
		System.out.println(count + "건이 저장되었습니다.");
	}

//	public void addGuest(GuestbookVo gvo) {
//		
//		this.getConnection();
//		
//		try {
//			/*********************************
//			 3. SQL문 준비 / 바인딩 / 실행
//			**********************************/
//			String query = "";
//			query += " insert into guestbook ";
//			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
//			
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setString(1, gvo.getName());
//			pstmt.setString(2, gvo.getPassword());
//			pstmt.setString(3, gvo.getContent());
//			
//			int count = pstmt.executeUpdate();
//			
//	        /*****************
//			 4.결과처리
//			******************/
//			System.out.println(count + "건이 저장되었습니다.");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		this.close();
//	}
//	
//	public void deleteGuest(int no) {
//		
//		this.getConnection();
//		
//		try {
//			/*********************************
//			 3. SQL문 준비 / 바인딩 / 실행
//			**********************************/
//			String query = "";
//			query += " delete from guestbook ";
//			query += " where no = ? ";
//			
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setInt(1, no);
//			
//			int count = pstmt.executeUpdate();
//			
//	        /*****************
//			 4.결과처리
//			******************/
//			System.out.println(count + "건이 삭제되었습니다.");
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		this.close();
//	}
//	
//	
//	public int delete(GuestbookVo vo) {
//		
//		int count = 0;
//		this.getConnection();
//		
//		try {
//			
//			String query = "";
//			query += " delete from guestbook ";
//			query += " where no = ? ";
//			query += " and password = ? ";
//			
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setInt(1, vo.getNo());
//			pstmt.setString(2, vo.getPassword());
//			
//			count = pstmt.executeUpdate();
//			
//			System.out.println(count + "건이 삭제되었습니다.");
//			
//		} catch (SQLException e) {
//			System.out.println("error: " + e);
//		}
//		
//		this.close();
//		
//		return count;
//	}
//	 
//	
//	public GuestbookVo getGuest(int no) {
//		
//		GuestbookVo gvo = null;
//		this.getConnection();
//		
//		try {
//	         /*****************************
//	          3. SQL문 준비 / 바인딩 / 실행
//	         ******************************/
//			String query = "";
//			query += " select password ";
//			query += " from guestbook ";
//			query += " where no = ? ";
//			
//			pstmt = conn.prepareStatement(query);
//			
//			pstmt.setInt(1, no);
//			
//			rs = pstmt.executeQuery();
//			
//	         /******************
//	         4.결과처리
//	         *******************/
//			while(rs.next()) {
//				String pw = rs.getString("password");
//				
//				gvo = new GuestbookVo("", pw, "");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		this.close();
//		
//		return gvo;
//	}
}
