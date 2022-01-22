<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
 <c:choose>
	<c:when test="${empty sessionScope.authUser }">
		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
	</c:when>
	<c:otherwise>
		<div id="footer">
			Copyright ⓒ 2020 ${sessionScope.authUser.name}. All right reserved
		</div>		
	</c:otherwise>
</c:choose>

<!-- <div id="footer">
	Copyright ⓒ 2020 황일영. All right reserved
</div> -->