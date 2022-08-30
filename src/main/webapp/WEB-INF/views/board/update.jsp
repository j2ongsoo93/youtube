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
<form action="../save" method="post">
	<input type="hidden" value="${b.BOARD_IDX}">
	<table>
		<tr>
			<td>이름: </td>
			<td><input type="text" name="userName" value="${b.USER_NAME}"></td>
		</tr>
		<tr>
			<td>아이디: </td>
			<td><input type="text" name="userId" value="${b.USER_ID}"></td>
		</tr>
		<tr>
			<td>내용: </td>
			<td><input type="text" name="boardContent" value="${b.BOARD_CONTENT}"></td>
		</tr>
	</table>
	<button type="submit">수정</button>
	<button type="button">삭제</button>
</form>
<hr>
<br>
</body>
</html>
