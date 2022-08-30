<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>3. 등록, 수정페이지</h3>
<c:choose>
	<c:when test="${b == null}">
		<form action="./save" method="post">
	</c:when>
	<c:when test="${b != null}">
		<form action="../save" method="post">
			<input type="hidden" name="boardIdx" value="${b.BOARD_IDX}">
	</c:when>
</c:choose>

	<table>
		<tr>
			<td>이름: </td>
			<td><input type="text" name="userName" <c:if test="${b != null}">value="${b.USER_NAME}"</c:if>></td>
		</tr>
		<tr>
			<td>아이디: </td>
			<td><input type="text" name="userId" <c:if test="${b != null}">value="${b.USER_ID}"</c:if>></td>
		</tr>
		<tr>
			<td>내용: </td>
			<td><input type="text" name="boardContent" <c:if test="${b != null}">value="${b.BOARD_CONTENT}"</c:if>></td>
		</tr>
	</table>
	<button type="submit">
		<c:choose>
			<c:when test="${b == null}">저장</c:when>
			<c:when test="${b != null}">수정</c:when>
		</c:choose>
	</button>
	<c:choose>
		<c:when test="${b != null}">
			<button type="button"><a href="../delete/${b.BOARD_IDX}">삭제</a></button>
		</c:when>
	</c:choose>

</form>
<hr>
<br>
</body>
</html>
