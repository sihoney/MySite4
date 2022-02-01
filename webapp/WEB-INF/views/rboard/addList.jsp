<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- // header, nav -->
	
		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/include/aside.jsp">
				<c:param name="view" value="board"></c:param>
			</c:import>
			<!-- aside -->
			
			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>댓글 게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath }/rboard/write" method="GET">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
							</colgroup>
							<tbody>
								<tr>
									<td><label class="form-text" for="input-uname">Title</label></td>
									<td><input id="input-uname" type="text" name="title"></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
					</form>	

					<table class="guestRead">
						<colgroup>
							<col style="width: 5%;">
							<col style="width: 45%;">
							<col style="width: 10%;">
							<col style="width: 5%;">
							<col style="width: 5%;">
							<col style="width: 5%;">
							<col style="width: 20%;">
						</colgroup>	
						<thead style="background-color: lightgray">
							<tr>
								<td>no</td>
								<td>title</td>
								<td>name</td>
								<td>group_no</td>
								<td>order_no</td>
								<td>depth</td>
								<td>관리</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.rboardList }" var="rbvo">
								<tr>
									<td>${rbvo.no }</td>
								
									<td style="padding-left: ${5 + 20 * rbvo.depth}px">
										<c:choose>
											<c:when test="${rbvo.depth > 0}">--></c:when>
										</c:choose>
										
										${rbvo.title }
									</td>
									
									<td>${rbvo.name }</td>								
									<td>${rbvo.groupNo }</td>
									<td>${rbvo.orderNo }</td>
									<td>${rbvo.depth }</td>
									
									<td>
										<a href="${pageContext.request.contextPath }/rboard/writeForm?no=${rbvo.no}">[답글 작성]</a>
										
										<c:choose>
											<c:when test="${sessionScope.authUser.no eq rbvo.userNo}">
												<a href="${pageContext.request.contextPath }/rboard/delete?no=${rbvo.no}&groupNo=${rbvo.groupNo}&orderNo=${rbvo.orderNo}">[삭제]</a>
												<a href="${pageContext.request.contextPath }/rboard/modifyForm?no=${rbvo.no}">[수정]</a>												
											</c:when>
										</c:choose>
										
									</td>
								</tr>					
							</c:forEach>						
						</tbody>		
					</table>		
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>
</html>