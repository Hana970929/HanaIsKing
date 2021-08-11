<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>	
	<h1>회원 관리</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>PW</td>
			<td>NAME</td>
			<td>POINT</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:forEach var="m" items="${list}">
			<tr>
				<td>${m.id}</td>
				<td>${m.pw}</td>
				<td>${m.name}</td>
				<td>${m.point}</td>
				<td><button onclick="location.href='update?id=${m.id}'">수정</button><br/></td>
				<td><button onclick="location.href='delete?id=${m.id}'">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	<h1>스케줄러 관리</h1>
	<button onclick="location.href='start'">스케줄러(20초마다 1포인트 증가) 실행 시작</button>
	<button onclick="location.href='end'">스케줄러 실행 종료</button>
</body>
</html>
