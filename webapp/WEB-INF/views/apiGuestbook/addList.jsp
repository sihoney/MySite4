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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
					<form id="form2" action="#" method="GET">
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
									<td><input id="input-uname" type="text" name="name" required></td>
									<td><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="password" required></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5" required></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit2" type="submit">등록</button></td>
								</tr>
							</tbody>
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					 </form>	 
					
					<div id="listArea"></div>
					
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
	
	<!-- 모달창 -->
	<div id="delModal" class="modal" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">비밀번호 입력 모달창</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <input id="modalPassword" type="password" name="password" value=""><br/>
	        <input id="modalNo" type="text" name="no" value="">
 	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
	        <button id="modalBtnDel" type="button" class="btn btn-danger">삭제</button>
	      </div>
	    </div>
	  </div>
	</div>

</body>
<script>
	/* 로딩되기 전(dom이 완성됐을 때)에 요청*/
	$("document").ready(function(){
		console.log("ready")
		
		/* 리스트 그리기 */
		fetchList();

	});
	
	/* 저장버튼이 클릭될 떄 - (1) 파라미터 방식*/
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
					data: rboardVo, /* 객체 형태 --> query형태 */
					
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
	
	/* 저장버튼이 클릭될 때 - (2) json 방식 요청 */
	$("#form2").on("submit", function(e){
		e.preventDefault();
		console.log("submit")
		
		// 폼에 있는 데이터 모으기
		let arr = $("#form2").serializeArray()
		
		let rboardVo = {
			name: arr[0].value,
			password: arr[1].value,
			content: arr[2].value
		}
		
		$.ajax(
				{
					url: "${pageContext.request.contextPath}/api/guestbook/write2",
					type: "post",
				/*	contentType: "application/json", */
					data: JSON.stringify(rboardVo), /* js 객체 --> json */
					
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
	
	
	/* 삭제 팝업 - 삭제버튼을 눌렀을 때 */
	$("#listArea").on("click", ".btnDelPop", function() {
		/* 데이터 수집 */
		var $this = $(this);
		var no = $this.data("no");
		
		/* 초기화 */
		$("#modalPassword").val("")
		$("#modalNo").val(no)
		
		/* 회색 바탕 & 회색 바탕 위 팝업 창 */
		$("#delModal").modal("show")
		
	});

	/* 모달창 삭제버튼을 클릭했을 때*/
	$("#modalBtnDel").on("click", function() {
		console.log("모달창 삭제버튼 클릭")
		
		// 데이터 수집
		var no = $("#modalNo").val()
		var pw = $("#modalPassword").val()
		
		var delInfoVo = {
			no: no,
			password: pw
		}
		
		console.log(delInfoVo)

		// ajax 요청 no password
 		$.ajax({
			url: "${pageContext.request.contextPath}/api/guestbook/remove",
			type: "post",
			//contentType: "application/json",
			data: delInfoVo,
			
			dataType: "json",
			success: function(state) { // json -> js
				
				console.log(state)
				
				if(state === 'success') {
					
					$("#t" + no).remove();
					
					// 모달창 닫기
					$("#delModal").modal("hide");
					
				} else {
					$("#delModal").modal("hide");
					alert("비밀번호를 확인하세요");
				}

			}, 
			error: function(XHR, status, error) {
				console.log(status + " : " + error);
			}
		}) 
		
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
		str += '<table id="t' + rbvo.no + '" class="guestRead">';
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
		str += '		<td><button class="btnDelPop" type="submit" data-no=' + rbvo.no + '>[삭제]</button></td>';
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