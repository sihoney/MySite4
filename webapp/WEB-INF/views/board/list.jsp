<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->

		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/include/aside.jsp">
				<c:param name="view" value="board"></c:param>
			</c:import>

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="" method="">
							<div class="form-group text-right">
								<input type="text">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pMap.boardList }" var="bvo">
									<tr id="tr-${bvo.no }">
										<td>${bvo.no }</td>
										<td class="text-left"><a href="${pageContext.request.contextPath }/board/read?no=${bvo.no}">${bvo.title }</a></td>
										<td>${bvo.name }</td>
										<td>${bvo.hit }</td>
										<td>${bvo.regDate }</td>
										<!-- 자신이 작성한 글에만 취소 버튼이 보임 -->
										<c:choose>
											<c:when test="${bvo.name eq sessionScope.authUser.name}">
												<td><button class="delBtn" type="submit" data-no="${bvo.no }">[삭제]</button></td>
											</c:when>
										</c:choose>
										
									</tr>
								</c:forEach>
								
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${pMap.prev eq true }">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.startPageBtnNo - 1}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${pMap.startPageBtnNo }" end="${pMap.endPageBtnNo }" step="1" var="page">
									<!-- 현재 페이지만 볼드 처리 -->
									<li class="active"><a  href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page }</a></li>
								</c:forEach>

								<c:if test="${pMap.next eq true }">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${pMap.endPageBtnNo + 1}">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<!-- 로그인한 사용자만 글쓰기 버튼이 보인다. -->
						<c:choose>
							<c:when test="${not empty sessionScope.authUser }">
								<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
							</c:when>
						</c:choose>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->
		

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>
<script>
	/* 삭제버튼 클릭 */
	$(".delBtn").on("click", function() {
		/* 데이터 수집 */
		var $this = $(this);
		var no = $this.data("no");
		
		var noObj = {no: no};
		 
		/* DB에서 삭제 */
		$.ajax(
				{
					url: "${pageContext.request.contextPath}/board/delete2",
					type: "post",
					data: noObj, 
					
					dataType: "json",
					success: function(result) { // json --> js object
						
						/* 화면에서 삭제 */
						console.log(result + "건이 삭제되었습니다");
						$("#tr-" + no).remove();
					}, 
					error: function(XHR, status, error) {
						console.log(status + " : " + error);
					}
				}
		);
				
	});
</script>
</html>
