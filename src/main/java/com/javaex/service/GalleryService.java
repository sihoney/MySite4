package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	GalleryDao galleryDao;
	
	public void restore(GalleryVo glvo) {
		
		MultipartFile file = glvo.getFile();
		String saveDir = "C:\\javaStudy\\file\\upload";
		
		// 원본 파일 이름
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일패스 생성
		String filePath = saveDir + "\\" + saveName;
		
		// 파일 사이즈
		long fileSize = file.getSize();
		
		glvo.setOrgName(orgName);
		glvo.setSaveName(saveName);
		glvo.setFilePath(filePath);
		glvo.setFileSize(fileSize);
		
		// DB에 저장
		galleryDao.add(glvo);
		
		// 파일 저장
		try {
			byte[] fileData = file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<GalleryVo> getList() {
		return galleryDao.getList();
	}
	
	public GalleryVo getGalleryVo(int no) {
		return galleryDao.getGalleryVo(no);
	}
	
	public void delete(int no) {
		galleryDao.delete(no);
	}
}
