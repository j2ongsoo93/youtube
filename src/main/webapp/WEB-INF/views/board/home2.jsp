<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>1. 리스트페이지</h3>
<form action="./list" method="post">
	<input type="text" name="keyword"><input type="submit" value="검색"><button type="button"><a href="./insert">등록</a></button>
</form>
<table border="1">
	<thead>
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>내용</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="list" items="${list}">
			<tr>
				<td>${list.BOARD_IDX}</td>
				<td>${list.USER_ID}</td>
				<td>${list.USER_NAME}</td>
				<td><a href="./${list.BOARD_IDX}">${list.BOARD_CONTENT}</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<hr>
<br>
</body>
</html>
