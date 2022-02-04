package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public void add(GalleryVo glvo) {
		
		int count = sqlSession.insert("gallery.add", glvo);
		System.out.println(count + "건이 저장되었습니다.");
	}
	
	public List<GalleryVo> getList() {
		return sqlSession.selectList("gallery.getList");
	}
	
	public GalleryVo getGalleryVo(int no) {
		return sqlSession.selectOne("gallery.getGalleryVo", no);
	}
}
