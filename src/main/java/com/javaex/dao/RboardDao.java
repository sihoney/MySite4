package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {

	@Autowired
	SqlSession sqlSession;
	
	public List<RboardVo> getList() {
		List<RboardVo> rboardList = sqlSession.selectList("rboardbook.getList");
		return rboardList;
	}
	
	public void add(RboardVo rbvo) {
		int count = sqlSession.insert("rboardbook.add", rbvo);
		System.out.println(count + "건이 저장되었습니다.");
	}
	
	public RboardVo getInfo(int no) {
		RboardVo rbvo = sqlSession.selectOne("rboardbook.getInfo", no);
		return rbvo;
	}
	
	public void addReply(RboardVo rbvo) {
		int count = sqlSession.insert("rboardbook.addReply", rbvo);
		System.out.println(count + "건 댓글이 저장되었습니다.");
	}
	
	public void updateOrderNo(RboardVo info) { // groupNo, orderNo, depth, no
		sqlSession.update("rboardbook.updateOrderNo", info);
	}
}
