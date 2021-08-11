<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="utf-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<form action="home" method="post">
아이디 : <input type="text" name="id">
이름 : <input type="text" name="name">
비밀번호 : <input type="password" name="pw">
<input type="submit" value="회원가입">
</form>
</body>
</html>
