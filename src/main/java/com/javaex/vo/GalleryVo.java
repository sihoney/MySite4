package com.javaex.vo;

import org.springframework.web.multipart.MultipartFile;

public class GalleryVo {

	private MultipartFile file;
	private String content;
	private int no;
	private int userNo;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String userName;
	
	public GalleryVo() {
		super();
	}
	
	public GalleryVo(MultipartFile file, String content, int no, int userNo, String filePath, String orgName,
			String saveName, long fileSize, String userName) {
		super();
		this.file = file;
		this.content = content;
		this.no = no;
		this.userNo = userNo;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.userName = userName;
	}

	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getUserName() {
		return userName;
	}
	public void setNumber(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "GalleryVo [file=" + file + ", content=" + content + ", no=" + no + ", userNo=" + userNo + ", filePath="
				+ filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", number=" + fileSize + "]";
	}
		
}
