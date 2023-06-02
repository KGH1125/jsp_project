<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<style type="text/css">
table {
	border-collapse: collapse;
}
td{
padding: 3px;

}

</style>
</head>
<body>
	<h3>회원목록</h3>
	<table border=1>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이메일</th>
				<th>가입일</th>
				<th>최종 로그아웃</th>
				<th>등급</th>
			</tr>
		</thead>
		<c:forEach items="${list }" var="tmp">
			<tr>
				<td>${tmp.id }</td>
				<td>${tmp.email }</td>
				<td>${tmp.reg_date }</td>
				<td>${tmp.last_logout }</td>
				<td>${tmp.grade }</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/brd/page"><button type="button">글목록</button></a>
</body>
</html>