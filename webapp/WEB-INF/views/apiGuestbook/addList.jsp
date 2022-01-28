<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //nav -->
	
		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/include/aside.jsp">
				<c:param name="view" value="guest"></c:param>
			</c:import>

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
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
					<form id="form" action="#" method="GET">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<td><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<td><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="writeBtn" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					 </form>	 
					
					<div id="listArea"></div>
<%-- 					
					<c:forEach items="${requestScope.guestList }" var="gvo">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${gvo.no }</td>
								<td>${gvo.name }</td>
								<td>${gvo.regDate }</td>
								<td><a href="${pageContext.request.contextPath }/guest/deleteForm?no=${gvo.no }">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${gvo.content }</td>
							</tr>
						</table>
						<!-- //guestRead -->					
					</c:forEach> 
--%>		
					
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
<script>
	/* 로딩되기 전에 요청*/
	$("document").ready(function(){
		console.log("ready")
		
		/* 리스트 그리기 */
		fetchList();

	});
	
	/* 저장버튼이 클릭될 떄*/
	$("#form").on("submit", function(e){
		e.preventDefault();
		console.log("submit")
		
		// 폼에 있는 데이터 모으기
		let arr = $("#form").serializeArray()
		
		let rboardVo = {
			name: arr[0].value,
			password: arr[1].value,
			content: arr[2].value
		}
		
		$.ajax(
				{
					url: "${pageContext.request.contextPath}/api/guestbook/write",
					type: "post",
				/*	contentType: "application/json", */
					data: rboardVo, /* 객체 형태로 보내면 query형태로 변환해서 보내준다 */
					
					dateType: "json",
					success: function(result) { // json -> js 변환
						/*성공시 처리해야될 코드 작성*/
						
						console.log(result);
						render(result, 'up');
											
					}, 
					error: function(XHR, status, error) {
						console.log(status + " : " + error);
					}
				}
			);
		
		$("input").val("");
		$("textarea").val("");
	})
	
	// 리스트 출력
	function fetchList(){
		$.ajax(
				{
					url: "${pageContext.request.contextPath}/api/guestbook/list",
					type: "post",
				/*	contentType: "application/json",
					data: {name: "홀길동"}, */
					
					dateType: "json",
					success: function(guestbookList) { // json -> js 변환
						/*성공시 처리해야될 코드 작성*/
						
						for(let rbvo of guestbookList) {
							render(rbvo);
						}
											
					}, 
					error: function(XHR, status, error) {
						console.log(status + " : " + error);
					}
				}
			);
	}
	
	function render(rbvo, updown) {
		
		let str = "";
		str += '<table class="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + rbvo.no + '</td>';
		str += '		<td>' + rbvo.name + '</td>';
		str += '		<td>' + rbvo.regDate + '</td>';
		str += '		<td><a href="${pageContext.request.contextPath }/guest/deleteForm?no='+ rbvo.no +'">[삭제]</a></td>';
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + rbvo.content + '</td>';
		str += '	</tr>';
		str += '</table>';

		if(updown === 'down') {
			$("#listArea").append(str);
		} else {
			$("#listArea").prepend(str);
		}
		
	}
</script>
</html>