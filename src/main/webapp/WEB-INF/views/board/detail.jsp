<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>상세페이지</title>
</head>
<body>
<h3>2. 상세페이지</h3>
<table>
	<tr>
		<td>이름: </td>
		<td>${b.USER_NAME}</td>
	</tr>
	<tr>
		<td>아이디: </td>
		<td>${b.USER_ID}</td>
	</tr>
	<tr>
		<td>내용: </td>
		<td>${b.BOARD_CONTENT}</td>
	</tr>
</table>
<button type="button"><a href="../boardFree/list">리스트로</a></button>
<button type="button"><a href="./update/${b.BOARD_IDX}">수정페이지로</a></button>
</body>
</html>
