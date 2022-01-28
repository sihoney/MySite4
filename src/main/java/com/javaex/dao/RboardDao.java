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
	
	public RboardVo getInfo2(int no) {
		RboardVo rbvo = sqlSession.selectOne("rboardbook.getInfo2", no);
		return rbvo;
	}
	
	public void addReply(RboardVo rbvo) {
		int count = sqlSession.insert("rboardbook.addReply", rbvo);
		System.out.println(count + "건 댓글이 저장되었습니다.");
	}
	
	public void updateOrderNo(RboardVo info) { // groupNo, orderNo, depth, no
		sqlSession.update("rboardbook.updateOrderNo", info);
	}
	
	public void updateOrderNo2(RboardVo rbvo) {
		System.out.println(rbvo); // no, groupNo, orderNo
		sqlSession.update("rboardbook.updateOrderNo2", rbvo);
	}
	
	public void delete(int no) {
		int count = sqlSession.delete("rboardbook.delete", no);
		System.out.println(count + "건이 삭제되었습니다.");
	}
	
	public void modify(RboardVo rbvo) {
		int count = sqlSession.update("rboardbook.modify", rbvo);
		System.out.println(count + "건이 수정되었습니다.");
	}
}
