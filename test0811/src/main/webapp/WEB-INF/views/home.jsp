<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="main" method="post">
아이디 : <input type="text" name="id">
비밀번호 : <input type="password" name="pw">
<input type="submit" value="로그인">
</form>
<button onclick="location.href='regist'">회원가입</button>
</body>
</html>
