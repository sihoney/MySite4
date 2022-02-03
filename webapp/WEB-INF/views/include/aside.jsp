<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:choose>
	<c:when test="${param.view eq 'user' }">
		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside (user)-->
	</c:when>

	<c:when test="${param.view eq 'board'}">
		<div id="aside">
			<h2>게시판</h2>
			<ul>
				<li><a href="">일반게시판</a></li>
				<li><a href="${pageContext.request.contextPath }/rboard/addList">댓글게시판</a></li>
			</ul>
		</div>
		<!-- //aside(board) -->	
	</c:when>
	
	<c:otherwise>
		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li><a href="${pageContext.request.contextPath }/api/guestbook/addList">ajax방명록</a></li>
			</ul>
		</div>
		<!-- //aside(guest) -->	
	</c:otherwise>
</c:choose>



